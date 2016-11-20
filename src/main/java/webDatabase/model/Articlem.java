package webDatabase.model;

import java.awt.Paint;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import webDatabase.database.DatabaseHelper;
import webDatabase.database.DatabaseResult;
import webDatabase.database.Pair;

public class Articlem {
	public static List<Map<String, Object>> getAllArticle() throws SQLException{
		DatabaseResult result = DatabaseHelper.search("article");
		return result.getData();
	}
	
	public static List<Map<String,Object>> getAllArticle(String byType)throws SQLException{
		byType = byType.toLowerCase();
		DatabaseResult result;
		if (byType.equals("bylike")) {
			result = DatabaseHelper.search("article",new ArrayList<>(),"likeCount","desc");
		}
		else {
			result = DatabaseHelper.search("article",new ArrayList<>(),"id","desc");
		}
		return result.getData();
	}
	
	public static Map<String,Object> getSingleArticle(String aid)throws SQLException{
		int id = Integer.valueOf(aid);
		List<Pair> list = new ArrayList<>();
		list.add(new Pair("id", aid));
		DatabaseResult result = DatabaseHelper.search("article",list);
		if(result.getData().isEmpty()){
			return null;
		}
		else {
			return result.getData().get(0);
		}
	}
	
	public static List<Map<String,String>> search(String type,String key){
		List<Pair> list = new ArrayList<>();
		if(type.equals("like")){
			//list.add("")
			//DatabaseHelper.search("article",);
		}
		
		
		return null;
	}
}
