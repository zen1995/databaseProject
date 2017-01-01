package webDatabase.database;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TableCreator {
	private static Logger logger = LoggerFactory.getLogger(TableCreator.class);



	public static void create() {
		try {
			createUserTable();
			createUserFollowTable();
			createArticleTable();
			createArticleLikeTable();
			createTagTable();
			createArticleTagTable();
			createArticleView();
		} catch (Exception e) {
			logger.error("err", e);
		}

	}

	private static void createUserTable() throws SQLException {
		String sql = "create table user(" + "id INT NOT NULL AUTO_INCREMENT,\n" + "name varchar(50),\n"
				+ "sex varchar(10),\n" + "age int,\n" + "account varchar(50),\n" + "password varchar(50),\n"
				+ "registerTime varchar(50),\n" + "" + "dbPassowrd varchar(50)," + "dbUser varchar(50)," + ""
				+ "unique(id)," + "PRIMARY KEY (`id`)" + ") ENGINE = InnoDB;";
		DatabaseHelper.executeSql(sql);
	}

	private static void createUserFollowTable() throws SQLException {
		String sql = "create table userFollow(" + "id INT NOT NULL AUTO_INCREMENT,\n" + "user1 int,\n" + "user2 int,\n"
				+ "PRIMARY KEY (`id`),\n" + "unique(id)," + "unique(user1,user2),\n"
				+ "foreign key(user1) references user(id),\n" + "foreign key(user2) references user(id)\n"
				+ ") ENGINE = InnoDB;";
		DatabaseHelper.executeSql(sql);
	}

	private static void createArticleTable() throws SQLException {
		String sql = "create table article(" 
	+ "id INT NOT NULL AUTO_INCREMENT,\n" 
				+ "publishUser int,\n"
				+ "title varchar(50),\n" 
				+ "content text,\n" 
				+ "likeCount int NOT NULL DEFAULT '0' ,\n"
				+ "time varchar(50) not null default '0',\n"
				+ "PRIMARY KEY (`id`),\n" + "unique(id)," + "foreign key(publishUser) references user(id)\n"
				+ ") ENGINE = InnoDB;";
		DatabaseHelper.executeSql(sql);
	}

	private static void createArticleLikeTable() throws SQLException {
		String sql = "create table articleLike(" + "id INT NOT NULL AUTO_INCREMENT,\n" + "userId int,\n"
				+ "articleId int,\n" + "PRIMARY KEY (`id`),\n" + "unique(id),"+"unique(articleId,userId),"
				+ "foreign key(userId) references user(id),\n" 
				+ "CONSTRAINT foreign key(articleId) references article(id) ON  DELETE CASCADE"
				+ ") ENGINE = InnoDB;";
		DatabaseHelper.executeSql(sql);
	}

	private static void createTagTable() throws SQLException {
		String sql = "create table tag(" + "id INT NOT NULL AUTO_INCREMENT,\n" + "tagName varchar(50),\n"
				+ "PRIMARY KEY (`id`),\n" + "unique(id)," + "unique(tagName)" + ") ENGINE = InnoDB;";
		DatabaseHelper.executeSql(sql);
	}

	public static void createArticleTagTable() throws SQLException {
		String sql = "create table articleTag(" + "id INT NOT NULL AUTO_INCREMENT,\n" + "articleId int,\n"
				+ "tagId int,\n" + "PRIMARY KEY (`id`),\n" 
				+ "unique(id)," 
				+ "unique(tagId,articleId),"
				//+ "foreign key(tagId) references tag(id),\n" 
				//+ "foreign key(articleId) references article(id),"
				+"CONSTRAINT FOREIGN KEY (`tagId`) REFERENCES `tag` (`id`)  ON  DELETE CASCADE,"
				+"CONSTRAINT FOREIGN KEY (`articleId`) REFERENCES `article` (`id`)  ON  DELETE CASCADE"
				+ ") ENGINE = InnoDB;";
		DatabaseHelper.executeSql(sql);
	}

	public static void createArticleView()throws SQLException{
		String sql = "create view articleview as "
				+ " select article.id,name,publishUser,title,time,content,likeCount "
				+ "from article join user on article.publishUser=user.id";
		DatabaseHelper.executeSql(sql);
	}
	




	public static void init() {
		create();
		
	}

	public static void main(String[] args) throws Exception {
		DBConnection.dropDatabase();
		init();
	}

}
