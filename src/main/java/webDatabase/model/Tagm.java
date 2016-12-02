package webDatabase.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.websocket.Session;

//import com.mysql.jdbc.Statement;

import webDatabase.database.DBConnection;
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
	
	public static boolean deleteTag(String tagName){
		try {
			Connection connection = DBConnection.getConnection();
			Statement statement = connection.createStatement();
			
			statement.close();
			connection.close();
		} catch (Exception e) {
			// TODO: handle exception
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
