package webDatabase.model;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import webDatabase.database.DatabaseController;

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
}
