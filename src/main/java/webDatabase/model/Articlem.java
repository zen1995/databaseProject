package webDatabase.model;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import webDatabase.database.DatabaseHelper;
import webDatabase.database.DatabaseResult;

public class Articlem {
	public static List<Map<String, Object>> getAllArticle() throws SQLException{
		DatabaseResult result = DatabaseHelper.search("article");
		return result.getData();
	}
}
