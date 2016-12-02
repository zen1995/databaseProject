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
	
	public static boolean deleteArticle(String aid)throws SQLException{
		return DatabaseHelper.executeUpdate("delete form article where id=?", aid);
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
	
	public static void main(String[] args)throws Exception {
		Object object = search("name","userName-1");
		object = search("tag", "tag-0");
		System.out.println(object);
	}
}
