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
			result = DatabaseHelper.search("article", new ArrayList<>(), "likeCount", "desc");
		} else {
			result = DatabaseHelper.search("article", new ArrayList<>(), "id", "desc");
		}
		return convertArticle(result.getData());
	}

	public static List<Map<String, Object>> getUserArticle(String uid)throws SQLException{
		return DatabaseHelper.query("select * from articleview where publishUser=?", uid).getData();
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
		Map<String,Object> map = new HashMap<>();
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
		map.put("status",true);
		return map;
	}
	
	public static void updateArticle(String aid,Map<String,Object> article)throws SQLException{
		DatabaseHelper.editRecord("article", Integer.valueOf(aid), article);
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
			updateArticleLike(aid);
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
			updateArticleLike(aid);
		} catch (Exception e) {
			ret.put("status",false);
		}
		return ret;
	}
	
	private static void updateArticleLike(String aid)throws SQLException{
		DatabaseHelper.executeUpdate("update article set likeCount= (select count(*) from articlelike  where articleId=?)"
				+ " where article.id=?",aid,aid);
	}
	
	public static Map<String,Object> addArticleTag(String aid,String tid)throws SQLException{
		Map<String, Object> ret = new HashMap<>();
		try {
			Map<String,Object> map = new HashMap<>();
			map.put("articleId",aid);
			map.put("tagId", tid);
			DatabaseHelper.insertRecord("articletag",map);
			ret.put("status",true);
		} catch (Exception e) {
			ret.put("status",false);
		}
		return ret;
	}
	
	
	public static void main(String[] args)throws Exception {
		System.out.println(getSingleArticle("3"));
	}
	
	
	
}
