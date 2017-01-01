package webDatabase.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import webDatabase.database.DBConnection;
import webDatabase.database.DatabaseController;
import webDatabase.database.DatabaseHelper;
import webDatabase.database.DatabaseResult;

public class Userm {
	public static Map<String,Object> loginCheck(String user,String pasword)throws SQLException{
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
			result = DatabaseHelper.query("select * from userfollow join user on user.id=userfollow.user2 where user1=?",map.get("id"));
			map.put("fout", result.getData());
			result = DatabaseHelper.query("select * from userfollow join user on user.id=userfollow.user1 where user2=?",map.get("id"));

			map.put("fin", result.getData());
			map.put("status", true);
		}
		return map;
	}
	
	public static void logOut(HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        map.put("status", false);
        request.getSession().setAttribute("user",map);
	}
	
	public static boolean isLogin(HttpServletRequest request){
		Map<String,Object> user = (Map<String, Object>) request.getSession().getAttribute("user");
		if((boolean)user.get("status") == true){
			return true;
		}
		return false;
	}
	
	public static Map<String,Object> checkAccount(String name)throws SQLException{
		Map<String,Object> ret = new HashMap<>();
		DatabaseResult result = DatabaseHelper.query("select * from user where account=?",name );
		if(result.getData().size() == 0){
			ret.put("status", false);
		}
		else{
			ret.put("status",true);
		}
		return ret;
	}
	
	public static Map<String,Object> register(String account,String name,String password,String sex)throws SQLException{
		Map<String,Object> ret = new HashMap<>();
		DatabaseResult result = DatabaseHelper.query("select addUser(?,?,?,?) as res", account,name,password,sex);
		int r = (int)result.getData().get(0).get("res");
		if(r == 0){
			ret.put("status", true);
		}
		else{
			ret.put("status", false);
			ret.put("info"," account duplicate!");
		}
		return ret;
	}
	
	public static Map<String,Object> editUserInfo(int uid,String name,String sex,String age,String password,String description)throws SQLException{
		DatabaseHelper.executeUpdate("update user set name=?,sex=?,age=?,password=?,description=? where id=?",name,sex,age,password,description,uid);
		Map<String,Object> ret = new HashMap<>();
		ret.put("status", true);
		return ret;
	}
	
	public static Map<String,Object> follow(String uid1,String uid2)throws SQLException{
		int r = DatabaseHelper.query("select follow(?,?)", uid1,uid2).getUnique();
		Map<String,Object> ret = new HashMap<>();
		if(r == 0){
			ret.put("status", true);
		}
		else{
			ret.put("status", false);
			ret.put("info", "you already follow this person");
		}
		return ret;
	}
	
	public static Map<String, Object> unFollow(String uid1,String uid2)throws SQLException{
		DatabaseHelper.executeUpdate("delete from userfollow where user1=? and user2=?",uid1,uid2);
		Map<String,Object> ret = new HashMap<>();
		ret.put("status", true);
		
		return ret;
	}
	public static void refreshUser(HttpServletRequest request)throws SQLException{
		Map<String,Object> user = (Map<String, Object>)request.getSession().getAttribute("user");
		int uid = (int)user.get("id");
		Map<String,Object> map = DatabaseHelper.query("select * from user  where id=?", uid).getData().get(0);
		DatabaseResult result = DatabaseHelper.query("select * from userfollow join user on user.id=userfollow.user2 where user1=?",map.get("id"));
		map.put("fout", result.getData());
		result = DatabaseHelper.query("select * from userfollow join user on user.id=userfollow.user1 where user2=?",map.get("id"));

		map.put("fin", result.getData());
		map.put("status", true);
		
		request.getSession().setAttribute("user",map);
	}
	
	public static List<Map<String,Object>> searchUser(String userName)throws SQLException{
		DatabaseResult result = DatabaseHelper.query("select * from user where name like ?","%"+userName+"%");
		return result.getData();
	}
	
	public static Map<String,Object> getUserById(String uid)throws SQLException{
		DatabaseResult result = DatabaseHelper.query("select * from user where id=?", uid);
		if(result.getData().size() == 0){
			Map<String, Object> ret = new HashMap<>();
			ret.put("status", false);
			return ret;
		}
		Map<String, Object> ret = result.getData().get(0);
		result = DatabaseHelper.query("select * from userfollow join user on user.id=userfollow.user2 where user1=?",uid);
		ret.put("fout", result.getData());
		result = DatabaseHelper.query("select * from userfollow join user on user.id=userfollow.user1 where user2=?",uid);
		ret.put("status", true);
		ret.put("fin", result.getData());
		return ret;
	}
	
	public static void main(String[] args)throws Exception {
		System.out.println(searchUser("name"));
	}
}
