package webDatabase.model;

import java.awt.Paint;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import webDatabase.database.DBConnection;
import webDatabase.database.DatabaseHelper;
import webDatabase.database.DatabaseResult;
import webDatabase.database.Pair;

public class Articlem {
	public static List<Map<String, Object>> getAllArticle() throws SQLException {
		DatabaseResult result = DatabaseHelper.search("article");
		return result.getData();
	}

	public static List<Map<String, Object>> getAllArticle(String byType) throws SQLException {
		byType = byType.toLowerCase();
		DatabaseResult result;
		if (byType.equals("bylike")) {
			result = DatabaseHelper.search("article", new ArrayList<>(), "likeCount", "desc");
		} else {
			result = DatabaseHelper.search("article", new ArrayList<>(), "id", "desc");
		}
		return result.getData();
	}


	public static Map<String, Object> getSingleArticle(String aid) throws SQLException {
		int id = Integer.valueOf(aid);
		List<Pair> list = new ArrayList<>();
		list.add(new Pair("id", aid));
		DatabaseResult result = DatabaseHelper.search("article", list);
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
		return result.getData();
	}
	
	public static void main(String[] args)throws Exception {
		Object object = search("name","userName-1");
		object = search("tag", "tag-0");
		System.out.println(object);
	}
}
