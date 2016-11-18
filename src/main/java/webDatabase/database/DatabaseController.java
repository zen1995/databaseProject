package webDatabase.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.management.Query;
import javax.sql.RowSet;

public class DatabaseController {
	//search
	public static Map<String,Object> passwordRight(String user,String pasword)throws SQLException{
		Map<String, Object> map = new HashMap<>();
		Connection connection = DBConnection.getConnection();
		PreparedStatement statement = connection.prepareStatement("select * from user where account=? and password=?");
		statement.setString(1, user);
		statement.setString(2, pasword);
		ResultSet set = statement.executeQuery();
		DatabaseResult result = new DatabaseResult(set);
		set.close();
		statement.close();
		connection.close();
		if(result.getData().size() != 0){
			map.put("result",true);
			map.put("user", "testuser1");
			map.put("password","123456");
		}
		else {
			map.put("result",false);
		}
		return map;
	}

	public static Map<String,Object> loginPassword(String user,String pasword)throws SQLException{
		Map<String, Object> map = new HashMap<>();
		Connection connection = DBConnection.getConnection();
		PreparedStatement statement = connection.prepareStatement("select * from user where account=? and password=?");
		statement.setString(1, user);
		statement.setString(2, pasword);
		ResultSet set = statement.executeQuery();
		DatabaseResult result = new DatabaseResult(set);
		set.close();
		statement.close();
		connection.close();
		if(result.getData().size() == 0){
			map.put("status", false);
		}
		else {
			map = result.getData().get(0);
			map.put("status", true);
		}
		return map;
	}
	
	public static DatabaseResult getAllArticle()throws SQLException{
		return DatabaseHelper.search("article");
	}
	
	public static DatabaseResult getArticleByTag(int id)throws SQLException{
		Connection connection = DBConnection.getConnection();
		PreparedStatement statement = connection.prepareStatement(
				"select * from article "
				
				+ "where (article.id in (select articleId from articletag where tagId=?))"
				);
		statement.setInt(1, id);
		ResultSet set = statement.executeQuery();
		DatabaseResult result = new DatabaseResult(set);
		set.close();
		statement.close();
		connection.close();
		return result;
	}
	
	public static DatabaseResult getFollowUserArticle(int userId)throws SQLException{
		Connection connection = DBConnection.getConnection();
		PreparedStatement statement = connection.prepareStatement(
				"select * from article as a1 "
				+ "where (a1.id in( select a2.id from article as a2 where "
									+ "(a2.publishUser in "
									+ "(select user2 from userfollow where user1=?)"
						 			+ "))"
				+ ")");
		statement.setInt(1, userId);
		ResultSet set = statement.executeQuery();
		DatabaseResult result = new DatabaseResult(set);
		set.close();
		statement.close();
		connection.close();
		return result;
	}
	
	public static DatabaseResult getUserArticle(int uid)throws SQLException{
		Connection connection = DBConnection.getConnection();
		String sql = "select * from article where publishUser = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setInt(1, uid);
		ResultSet set = statement.executeQuery();
		DatabaseResult result = new DatabaseResult(set);
		set.close();
		statement.close();
		connection.close();
		return result;
	}
	
	public static DatabaseResult query(String sql,Object ...args )throws SQLException{
		Connection connection = DBConnection.getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		for(int i=0;i < args.length;i++){
			statement.setObject(i+1,args[i]);
		}
		ResultSet set = statement.executeQuery();
		DatabaseResult result = new DatabaseResult(set);
		set.close();
		statement.close();
		connection.close();
		return result;
	}
	
	public static DatabaseResult getArticleTag(int articleId)throws SQLException{
		String sql = "select * from tag join articletag on articletag.tagId = tag.id"
				+ " where articletag.articleId = ?";
		return query(sql,articleId);
	}
	
	public static DatabaseResult getUserInfo(int uid)throws SQLException{
		String sql = "select * from user where id=?";
		return query(sql,uid);
	}
	
	public static DatabaseResult getLikeUser(int articleId)throws SQLException{
		String sql = "select * from user join articlelike on articlelike.articleId=?";
		return query(sql,articleId);
	}
	
	//关注的人
	public static DatabaseResult getfollower(int uid)throws SQLException{
		String sql = "select * from user where user.id in"
				+ " (select user2 from userfollow where user1=?)";
		return query(sql,uid);
	}
	
	public static DatabaseResult getFans(int uid)throws SQLException{
		String sql = "select * from user where user.id in"
				+ " (select user1 from userfollow where user2=?)";
		return query(sql,uid);
	}
	public static DatabaseResult getArticle(int aid)throws SQLException{
		return query("select * from article where id=?",aid);
	}
	
	public static DatabaseResult sexChart()throws SQLException{
		return query("select count(*),sex from user group by sex"); 
	}
	
	public static DatabaseResult popularUser()throws SQLException{
		return query("select * from user as user1 join (select count(*) as likeCount ,userId as uid from articleLike group by userId) as table2 on table2.uid=user1.id");
		
	}
	public static DatabaseResult popularArticle()throws SQLException {
		return query("select * from article as article1 join (select count(*) as likeCount ,articleId as aid from articleLike group by articleId) as table2 on table2.aid=article1.id ");
	}
	
	//insert
	//use databaseHelper instead
	
	
	public static void main(String[] args) throws Exception{
		getArticle(1);
	}
}
