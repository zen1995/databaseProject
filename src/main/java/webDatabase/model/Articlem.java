package webDatabase.model;

import java.awt.Paint;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import webDatabase.database.DBConnection;
import webDatabase.database.DatabaseHelper;
import webDatabase.database.DatabaseResult;
import webDatabase.database.Pair;

public class Articlem {

	public static List<Map<String, Object>> getAllArticle() throws SQLException {
		DatabaseResult result = DatabaseHelper.search("article");
		return convertArticle(result.getData());
	}

	public static List<Map<String, Object>> getAllArticle(String byType) throws SQLException {
		byType = byType.toLowerCase();
		DatabaseResult result;
		if (byType.equals("bylike")) {
			result = DatabaseHelper.search("articleview", new ArrayList<>(), "likeCount", "desc");
		} else {
			result = DatabaseHelper.search("articleview", new ArrayList<>(), "id", "desc");
		}
		return convertArticle(result.getData());
	}

	public static List<Map<String, Object>> getUserArticle(String uid)throws SQLException{
		return convertArticle(DatabaseHelper.query("select * from articleview where publishUser=?", uid).getData());
	}
	
	
	public static Map<String, Object> getSingleArticle(String aid) throws SQLException {
		int id = Integer.valueOf(aid);
		List<Pair> list = new ArrayList<>();
		list.add(new Pair("id", aid));
		DatabaseResult result = DatabaseHelper.query("select * from articleview where id=? ",aid);
		convertArticle(result.getData());
		if (result.getData().isEmpty()) {
			return null;
		} else {
			return result.getData().get(0);
		}
	}

	public static List<Map<String, Object>> search(String type, String key)throws SQLException {
		List<Pair> list = new ArrayList<>();
		DatabaseResult result;
		if (type.equals("name")) {
			String sql = "select * from user join article on user.id = article.publishUser where name = ?";
			result = DatabaseHelper.query(sql,key);
		}
		else if (type.equals("tag")) {
			String sql = "select * from article join articletag  on article.id=articletag.articleid join tag on tag.id=articletag.tagid where tagName =?";
			result = DatabaseHelper.query(sql,key);
		}
		else {
			return null;
		}
		return convertArticle(result.getData());
	}
	
	public static void insertArticle(Map<String,Object> article)throws SQLException{
		article.put("time",String.valueOf(System.currentTimeMillis()));
		DatabaseHelper.insertRecord("article", article);
		
		//return true;
	}
	
	public static Map<String,Object> deleteArticle(int uid,String aid)throws SQLException{
		Map<String,Object> ret = new HashMap<>();
		/*
		List<Map<String, Object>> result = DatabaseHelper.query("select * from article where id=?",aid).getData();
		if(result.isEmpty()){
			map.put("status",false);
			map.put("info","article not exist");
			return map;
		}
		Map<String,Object> article = result.get(0);
		if(!article.get("publishUser").equals(uid)){
			map.put("status",false);
			map.put("info","no authorized");
			return map;
		}
		DatabaseHelper.executeUpdate("delete from article where id=?", aid);
		map.put("status",true);*/
		DatabaseResult result = DatabaseHelper.query("select deleteArticle(?,?) as res",uid,aid);
		if((int)(result.getData().get(0).get("res")) == 0){
			ret.put("status","true");
			return ret;
		}
		else{
			ret.put("status", "false");
			ret.put("info", "article not exist or no authorized");
			return ret;
		}
	}
	
	public static Map<String,Object> updateArticle(int uid,String aid,String title,String content)throws SQLException{
		
		Map<String,Object> ret = new HashMap<>();/*
		int r = DatabaseHelper.query("select * from article where id=? and publishUser=?",aid,uid).getData().size();
		if(r == 0){
			ret.put("status", "false");
			ret.put("info", "not signed in or aid incorrect");
			return ret;
		}
		else{
			DatabaseHelper.editRecord("article", Integer.valueOf(aid), article);
			ret.put("status","true");
			return ret;
		}*/ 
		DatabaseResult result = DatabaseHelper.query("select updateArticle(?,?,?,?) as res",aid,uid,title,content);
		if((int)(result.getData().get(0).get("res")) == 0){
			ret.put("status",true);
			return ret;
		}
		else{
			ret.put("status", false);
			ret.put("info", "not signed in or aid incorrect");
			return ret;
		}
	}
	
	private static List<Map<String, Object>> convertArticle(List<Map<String, Object>> list){
		for(Map<String, Object> map : list){
			long t = Long.valueOf((String)map.get("time"));
	        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	        Date date = new Date(t);
	        String res = simpleDateFormat.format(date);
			map.put("timeStr", res);
		}
		return list;
	}
	
	public static Map<String,Object> likeArticle(int uid,String aid){
		Map<String,Object> ret = new HashMap<>();
		try {
			Map<String, Object> map = new HashMap<>();
			map.put("userId",uid);
			map.put("articleId", aid);
			DatabaseHelper.insertRecord("articlelike",map);
			//updateArticleLike(aid);
			ret.put("status", true);
		} catch (Exception e) {
			e.printStackTrace();
			ret.put("status",false);
		}
		return ret;
	}
	
	public static Map<String,Object> unlikeArticle(int uid,String aid){
		Map<String,Object> ret = new HashMap<>();
		try {
			ret.put("status",true);
			DatabaseHelper.executeUpdate("delete from articlelike where articleId=? and userId=?",aid,uid);
			//updateArticleLike(aid);
		} catch (Exception e) {
			ret.put("status",false);
		}
		return ret;
	}
	
	public static boolean userLikeArticle(int uid,String aid)throws SQLException{
		DatabaseResult result = DatabaseHelper.query("select * from articlelike where userId=? and articleId=?",uid,aid);
		if(result.getData().isEmpty()){
			return false;
		}
		return true;
	}
	
	
	private static void updateArticleLike(String aid)throws SQLException{
		DatabaseHelper.executeUpdate("update article set likeCount= (select count(*) from articlelike  where articleId=?)"
				+ " where article.id=?",aid,aid);
	}
	
	public static Map<String,Object> addArticleTag(String aid,String tagName)throws SQLException{
		Map<String, Object> ret = new HashMap<>();
		try {
			boolean r = Tagm.addArticleTag(tagName, aid);
			ret.put("status",true);
		} catch (Exception e) {
			ret.put("status",false);
		}
		return ret;
	}
	public static Map<String,Object> deleteArticleTag(String aid,String tid)throws SQLException{
		Map<String, Object> ret = new HashMap<>();
		try {
			DatabaseHelper.executeSql("delete from articletag where articleId="+aid+" and tagId="+tid);
			ret.put("status",true);
		} catch (Exception e) {
			ret.put("status",false);
		}
		return ret;
	}	
	
	public static void main(String[] args)throws Exception {
		Object r = updateArticle(1,"1", "1","1");
		System.out.println(r);
	}
	
	
	
}
