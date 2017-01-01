package webDatabase.model;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import webDatabase.database.DatabaseController;
import webDatabase.database.DatabaseHelper;
import webDatabase.database.DatabaseResult;

public class Userm {
	public static Map<String,Object> loginCheck(String user,String pasword)throws SQLException{
		return DatabaseController.loginPassword(user, pasword);
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
	public static void main(String[] args)throws Exception {
		register("aaaassaa","tsst", "asa", "asa");
	}
}
