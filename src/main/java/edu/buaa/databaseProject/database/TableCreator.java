package edu.buaa.databaseProject.database;

import java.sql.SQLException;

public class TableCreator {
	public static void create(){
		
	}
	
	private static void createUserTable()throws SQLException{
		String sql = "create table user("
				+ "id INT NOT NULL AUTO_INCREMENT,\n"
				+ "name varchar(50),\n"
				+ "sex varchar(10),\n"
				+ "age int,\n"
				+ "account varchar(50),\n"
				+ "password varchar(50),\n"
				+ "registerTime int,\n"
				+ "unique(id),"
				+ "PRIMARY KEY (`id`)"
				+ ") ENGINE = InnoDB;";
		DatabaseHelper.executeSql(sql);
	}
	
	private static void createUserFollowTable()throws SQLException{
		String sql = "create table userFollow("
				+ "id INT NOT NULL AUTO_INCREMENT,\n"
				+ "user1 int,\n"
				+ "user2 int,\n"
				+ "PRIMARY KEY (`id`),\n"
				+ "unique(id),"
				+ "foreign key(user1) references user(id),\n"
				+ "foreign key(user2) references user(id)\n"
				+ ") ENGINE = InnoDB;";
		DatabaseHelper.executeSql(sql);
	}
	
	private static void createArticleTable()throws SQLException{
		String sql = "create table article("
				+ "id INT NOT NULL AUTO_INCREMENT,\n"
				+ "publishUser int,\n"
				+ "title varchar(50),\n"
				+ "content text,\n"
				+ "likeCount int,\n"
				+ "PRIMARY KEY (`id`),\n"
				+ "unique(id),"
				+ "foreign key(publishUser) references user(id)\n"
				+ ") ENGINE = InnoDB;";
		DatabaseHelper.executeSql(sql);
	}
	
	private static void createArticleLikeTable()throws SQLException{
		String sql = "create table articleLike("
				+ "id INT NOT NULL AUTO_INCREMENT,\n"
				+ "userId int,\n"
				+ "articleId int,\n"
				+ "PRIMARY KEY (`id`),\n"
				+ "unique(id),"
				+ "foreign key(userId) references user(id),\n"
				+ "foreign key(articleId) references article(id)"
				+ ") ENGINE = InnoDB;";
		DatabaseHelper.executeSql(sql);
	}
	
	private static void createTagTable()throws SQLException{
		String sql = "create table tag("
				+ "id INT NOT NULL AUTO_INCREMENT,\n"
				+ "tagName varchar(50),\n"
				+ "PRIMARY KEY (`id`),\n"
				+ "unique(id),"
				+ "unique(tagName)"
				+ ") ENGINE = InnoDB;";
		DatabaseHelper.executeSql(sql);
	}
	
	public static void createArticleTagTable()throws SQLException{
		String sql = "create table articleTag("
				+ "id INT NOT NULL AUTO_INCREMENT,\n"
				+ "articleId int,\n"
				+ "tagId int,\n"
				+ "PRIMARY KEY (`id`),\n"
				+ "unique(id),"
				+ "unique(tagId,articleId),"
				+ "foreign key(tagId) references tag(id),\n"
				+ "foreign key(articleId) references article(id)"
				+ ") ENGINE = InnoDB;";
		DatabaseHelper.executeSql(sql);
	}
	
	public static void main(String[] args) throws Exception{
		//createUserTable();
		//createUserFollowTable();
		//createArticleTable();
		//createArticleLikeTable();
		//createTagTable();
		createArticleTagTable();
	}
	
	
}
