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
	private static int userCount = 20;
	private static int articleCount = userCount * 2;
	private static int followCount = userCount * 6;
	private static int tagCount = (int) (articleCount * 0.5);

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
	
	private static void insertArticle() throws SQLException {
		DatabaseResult result = DatabaseHelper.search("user");
		List<Map<String, Object>> data = result.getData();

		for (int i = 0; i < articleCount; i++) {
			int u = (int) (Math.random() * data.size());
			Map<String, Object> map = data.get(u);
			int uid = (int) map.get("id");
			Map<String, Object> article = new HashMap<>();
			article.put("publishUser", uid);
			article.put("title", "article title-" + i);
			String content = "";
			for (int j = 0; j < 20; j++) {
				content += "content-" + i + " ";
			}
			article.put("content", content);
			article.put("time", System.currentTimeMillis());
			DatabaseHelper.insertRecord("article", article);
		}
	}

	private static void insertUser() throws SQLException {
		for (int i = 0; i < userCount; i++) {
			Map<String, Object> map = new HashMap<>();
			map.put("name", "userName-" + i);
			double r = Math.random();
			String sex = "male";
			if (r >= 0.5)
				sex = "female";
			map.put("sex", sex);
			map.put("age", (int) (Math.random() * 50 + 10));
			map.put("password", "123456");
			map.put("account", "account" + i);
			map.put("registerTime", String.valueOf(System.currentTimeMillis()));
			DatabaseHelper.insertRecord("user", map);
		}
	}

	private static void insertUserFollow() throws SQLException {
		DatabaseResult result = DatabaseHelper.search("user");
		List<Map<String, Object>> data = result.getData();
		Set<String> set = new HashSet<>();
		for (int i = 0; i < followCount; i++) {
			while (true) {

				int u = (int) (Math.random() * data.size());
				Map<String, Object> map = data.get(u);
				int uid1 = (int) map.get("id");
				int uid2 = uid1;
				
				uid2 = (int) data.get((int) (Math.random() * data.size())).get("id");
				
				if(uid1 == uid2 || set.contains(uid1+"-"+uid2)){
					continue;
				}
				set.add(uid1+"-"+uid2);
				Map<String, Object> follow = new HashMap<>();
				follow.put("user1", uid1);
				follow.put("user2", uid2);
				DatabaseHelper.insertRecord("userfollow", follow);
				break;
			}
		}
	}

	private static void insertTag() throws SQLException {
		for (int i = 0; i < tagCount; i++) {
			Map<String, Object> map = new HashMap<>();
			map.put("tagName", "tag-" + i);
			DatabaseHelper.insertRecord("tag", map);
		}
	}

	private static void insertArticleLike() throws SQLException {
		List<Map<String, Object>> users = DatabaseHelper.search("user").getData();
		List<Map<String, Object>> articles = DatabaseHelper.search("article").getData();
		Set<String> set = new HashSet<>();
		for (int i = 0; i < articleCount; i++) {
			int uid = 0;
			int aid = 0;
			while (true) {
				uid = (int) (users.get((int) (Math.random() * users.size())).get("id"));
				aid = (int) (articles.get((int) (Math.random() * articles.size())).get("id"));
				if (set.contains(uid + "-" + aid) || aid == uid) {
					continue;
				} else {
					set.add(uid + "-" + aid);
					Map<String, Object> map = new HashMap<>();
					map.put("userId", uid);
					map.put("articleId", aid);
					DatabaseHelper.insertRecord("articlelike", map);
					break;
				}
			}

		}

	}

	private static void insertArticleTag() throws SQLException {
		List<Map<String, Object>> tags = DatabaseHelper.search("tag").getData();
		List<Map<String, Object>> articles = DatabaseHelper.search("article").getData();
		Set<String> set = new HashSet<>();
		for (int i = 0; i < articleCount; i++) {
			int tid = 0;
			int aid = 0;
			while (true) {
				tid = (int) (tags.get((int) (Math.random() * tags.size())).get("id"));
				aid = (int) (articles.get((int) (Math.random() * articles.size())).get("id"));
				if (set.contains(tid + "-" + aid) || aid == tid) {
					continue;
				} else {
					set.add(tid + "-" + aid);
					Map<String, Object> map = new HashMap<>();
					map.put("tagId", tid);
					map.put("articleId", aid);
					DatabaseHelper.insertRecord("articleTag", map);
					break;
				}
			}

		}
	}

	private static void insert() {
		try {
			insertUser();
			insertArticle();
			insertUserFollow();
			insertTag();
			insertArticleLike();
			insertArticleTag();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void init() {
		create();
		insert();
	}

	public static void main(String[] args) throws Exception {
		DBConnection.dropDatabase();
		init();
	}

}
