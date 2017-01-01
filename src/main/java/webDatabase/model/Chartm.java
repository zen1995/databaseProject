package webDatabase.model;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import webDatabase.database.DatabaseHelper;
import webDatabase.database.DatabaseResult;
import webDatabase.database.Pair;

public class Chartm {
	public static List<Map<String, Object>> getSexRate()throws SQLException{
		DatabaseResult result = DatabaseHelper.query("select count(*) as sexSum,sex from user where sex is not null group by sex ");
		return result.getData();
	}
	
	public static List<Map<String, Object>> getPopularUser()throws SQLException{
		DatabaseResult result = DatabaseHelper.query("select sum(likeCount) as likeSum,publishUser,name from articleview group by publishUser,name order by likeSum desc limit 10");
		return result.getData();
	}

	
	public static List<Map<String, Object>> getPopularArticle()throws SQLException{
		DatabaseResult result = DatabaseHelper.query("select likeCount,name,title from articleview order by likeCount desc limit 10");
		return result.getData();
	}
	
	public static List<Map<String, Object>> getMostLikeUser()throws SQLException{
		DatabaseResult result = DatabaseHelper.query("select count(*) as fans, name,user.id from user "
				+ "join userfollow on userfollow.user2=user.id group by user.id,name  order by fans desc limit 10");
		return result.getData();
	}
}
