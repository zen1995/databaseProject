package webDatabase.model;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import webDatabase.database.DatabaseHelper;
import webDatabase.database.DatabaseResult;

public class Tagm {

	public static boolean addTag(String tagName){
		Map<String, Object> map = new HashMap<>();
		map.put("tagName",tagName);
		try {
			DatabaseHelper.insertRecord("tag", map);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static List<Map<String,Object>> getArticleTag(String aid)throws SQLException{
		//List<String> list;
		DatabaseResult result =DatabaseHelper.query("select * from articletag join tag on articletag.tagId=tag.id where articletag.articleId=?", aid);
		return result.getData();
	}
	
	public static void main(String[] args)throws Exception {
		// TODO Auto-generated method stub
		Object rObject = getArticleTag("1");
		System.out.println(rObject);
	}

}
