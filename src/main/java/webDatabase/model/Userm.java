package webDatabase.model;

import java.sql.SQLException;
import java.util.Map;

import webDatabase.database.DatabaseController;

public class Userm {
	public static Map<String,Object> loginCheck(String user,String pasword)throws SQLException{
		return DatabaseController.loginPassword(user, pasword);
	}
}
