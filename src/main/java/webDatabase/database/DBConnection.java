package webDatabase.database;

import com.sun.rowset.CachedRowSetImpl;



import javax.sql.RowSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.*;;
 
/**
 * database操作的相关函�?
 */
public class DBConnection {
	static public final String driver = "com.mysql.jdbc.Driver";
	static public final String preUrl = "jdbc:mysql://localhost:3306/";
	static public final String postURL = "?useUnicode=true&characterEncoding=UTF-8&useOldAliasMetadataBehavior=true";
	static private String user = "root";
	static private String password = "621159";
	public static  final String databaseName = "databaseproject";
	private static Logger logger = LoggerFactory.getLogger(DBConnection.class);
	/* 初始�? */
	static {
		try {
			//java.lang.Thread.sleep(30000000);
			//initDB();
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = null;
			do{
				connection = DriverManager.getConnection(preUrl + postURL, user, password);
				System.out.println("try connect");
			}while(connection == null);
			Statement stmt = connection.createStatement();
			String sql = "create database if not exists " + databaseName
					+ " CHARACTER SET 'utf8' COLLATE 'utf8_general_ci';";
			stmt.execute(sql);
			stmt.close();
			connection.close();
		} catch (Exception e) {
			logger.error("unable to connection database!",e);
		}
	}

	private static void init(){
		try {
			Connection connection = null;
			do{
				connection = DriverManager.getConnection(preUrl + postURL, user, password);
			}while(connection == null);
			Statement stmt = connection.createStatement();
			String sql = "create database if not exists " + databaseName
					+ " CHARACTER SET 'utf8' COLLATE 'utf8_general_ci';";
			stmt.execute(sql);
			stmt.close();
			connection.close();
		} catch (Exception e) {
			logger.error("unable to connection database!",e);
		}
	}
	
	private static String getUrl() {
		return preUrl + databaseName + postURL;
	}

	public static void dropDatabase()throws SQLException{
		Connection connection =  DriverManager.getConnection(preUrl + postURL, user, password);
		String sql = "drop database "+" if exists "+databaseName;
		Statement s = connection.createStatement();
		s.execute(sql);
		s.close();
		connection.close();
	}
	
	/**
	 * 获取数据库连�?
	 * 
	 * @return connection
	 */
	public static Connection getConnection() throws SQLException {
		init();
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(getUrl(), user, password);

			// connection = DriverManager.getConnection(url,user,password);
			// stmt = connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("unable to connecte DB",e);
			throw e;
		}
		return connection;
	}

	public static Connection getConnection(String user,String password)throws SQLException {
		init();
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(getUrl(), user, password);

			// connection = DriverManager.getConnection(url,user,password);
			// stmt = connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("unable to connecte DB",e);
			throw e;
		}
		return connection;
	}
	
	public static String getPassword() {
		return password;
	}
	public static String getUser() {
		return user;
	}
	public static void setPassword(String password) {
		DBConnection.password = password;
	}
	public static void setUser(String user) {
		DBConnection.user = user;
	}
	
	public static void execute(String sql) throws SQLException {
		Statement statement = null;
		Connection connection = null;
		connection = getConnection();
		statement = connection.createStatement();
		statement.executeUpdate(sql);
		statement.close();
		connection.close();
	}
	
	public static void rootUser(){
		user = "root";
		password = "123456";
	}
	public static void loginUser(){
		user = "login_user";
		password = "123456";
	}
	public static void setUser(String user,String pass){
		DBConnection.user = user;
		DBConnection.password = pass;
	}
	
	
}
