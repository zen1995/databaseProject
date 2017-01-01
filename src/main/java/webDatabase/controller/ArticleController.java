package webDatabase.controller;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.junit.runners.Parameterized.Parameter;
import org.springframework.stereotype.Controller;  
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.sym.Name;

import webDatabase.database.DatabaseHelper;
import webDatabase.model.Articlem;
import webDatabase.model.Tagm;
import webDatabase.util.JsonHelper;  
@Controller  
public class ArticleController {  
    
    @RequestMapping(value="/")  
    public String index_jsp(HttpServletRequest request,Model model)throws Exception{  
        String type = request.getParameter("showType");
        if(type == null) type = "";
    	List<Map<String,Object>> list = Articlem.getAllArticle(type);
        model.addAttribute("articles",(Object)list);
        System.out.println(request.getParameter("a"));
         return "article/showAllArticle";
    } 
    @RequestMapping("/article/show/{aid}")
    public String showSingleArticle(HttpServletRequest request,Model model,@PathVariable("aid") String aid)throws Exception{
    	Map<String,Object> map = Articlem.getSingleArticle(aid);
    	if(map == null){
    		model.addAttribute("info","no article id-"+aid);
    		return "other/errPage";
    	}
    	model.addAttribute("article", map);
    	model.addAttribute("tag",Tagm.getArticleTag(aid));
    	Map<String,Object> user = (Map<String, Object>)request.getSession().getAttribute("user");
    	model.addAttribute("canLike",!Articlem.userLikeArticle((int)user.get("id"), aid));
    	return "article/show";
    }

	@RequestMapping(value = "/article/byuser")
	public String articlebyuser() {
		return "/article/byuser";
	}
	
    @RequestMapping(value = "/article/search")
    public String search(HttpServletRequest request,Model model)throws SQLException{
    	String queryType = request.getParameter("type");
    	String string = request.getParameter("value");
    	List<Map<String,Object>> list = Articlem.search(queryType,string);
    	model.addAttribute("articles",list);
    	if(list ==null){
    		model.addAttribute("info","invalid paramer-"+queryType);
    		return "other/errPage";    		
    	}
    	return "article/result";
    }
    
    @RequestMapping(value = "/article/addPage",method = RequestMethod.GET)
    public String addArticlePage(HttpServletRequest request,Model model)throws SQLException{
    	return "/article/add";
    }
    @RequestMapping(value = "/article/add")
    @ResponseBody
    public String addArticle(HttpServletRequest request,Model model)throws SQLException{
    	String title = request.getParameter("title");
    	String content = request.getParameter("content");
    	Map<String,Object> user = (Map<String, Object>)request.getSession().getAttribute("user");
    	if((boolean)user.get("status") == true){
    		Map<String,Object> map = new HashMap<>();
    		map.put("title", title);
    		map.put("content",content);
    		map.put("publishUser", user.get("id"));
    		Articlem.insertArticle(map);
    		
    		Map<String,Object> map2 = new HashMap<>();
    		map2.put("status", true);
    		return JsonHelper.jsonEncode(map2);
    	}
    	else {
    		Map<String,Object> map2 = new HashMap<>();
    		map2.put("status", true);
    		map2.put("info","login status error");
    		//model.addAttribute("info","login status error");
    		return JsonHelper.jsonEncode(map2);    			
		}
    }
    @RequestMapping(value = "/article/like")
    @ResponseBody
    public String likeArticle(HttpServletRequest request)throws SQLException{
    	Map<String,Object> user = (Map<String, Object>)request.getSession().getAttribute("user");
    	if((boolean)user.get("status") == true){
    		String aid = request.getParameter("aid");
    		int uid = (int)user.get("id");
    		String command = request.getParameter("command");
    		Map<String,Object> map2 = new HashMap<>();
    		if(command.equals("like")){
    			map2 =Articlem.likeArticle(uid, aid);
    		}
    		else if(command.equals("unlike")){
    			map2 = Articlem.unlikeArticle(uid, aid);
    		}
    		else{
    			map2.put("status",false);
    		}
    		return JsonHelper.jsonEncode(map2);
    	}
    	else{
    		Map<String,Object> map2 = new HashMap<>();
    		map2.put("status", true);
    		map2.put("info","login status error");
    		//model.addAttribute("info","login status error");
    		return JsonHelper.jsonEncode(map2);    
    	}
    }

    @RequestMapping(value = "/article/delete")
    @ResponseBody
    public String deleteArticle(HttpServletRequest request)throws SQLException{
    	Map<String,Object> user = (Map<String, Object>)request.getSession().getAttribute("user");
    	Map<String, Object> map = Articlem.deleteArticle((int)user.get("id"),request.getParameter("aid"));
    	return JsonHelper.jsonEncode(map);
    }
    
    @RequestMapping(value = "/article/modify")
    @ResponseBody
    public String modifyArticle(HttpServletRequest request)throws SQLException{
    	Map<String,Object> user = (Map<String, Object>)request.getSession().getAttribute("user");
    	String aid = request.getParameter("aid");
    	String title = request.getParameter("title");
    	String content = request.getParameter("content");
    	Map<String,Object> article = new HashMap<>();
    	article.put("id", aid);
    	article.put("title", title);
    	article.put("content", content);
    	if((boolean)user.get("status") == false){
    		Map<String,Object> map = new HashMap<>();
    		map.put("status", false);
    		map.put("info", "you are not log in");
    		return JsonHelper.jsonEncode(map);
    	}
    	Map<String, Object> map = Articlem.updateArticle((int)user.get("id"),aid, title,content);
    	return JsonHelper.jsonEncode(map);
    } 
    
    @RequestMapping(value = "/article/modifyPage/{aid}")
    public String modifyArticlePage(HttpServletRequest request,Model model,@PathVariable("aid") String aid)throws SQLException{
    	Map<String,Object> user = (Map<String, Object>)request.getSession().getAttribute("user");
    	if((boolean)user.get("status") == false){
    		model.addAttribute("info","you are not log in");
    		return "other/errPage";  
    	}
    	Map<String, Object> article = Articlem.getSingleArticle(aid);
    	if((int)article.get("publishUser") != (int)user.get("id")){
    		model.addAttribute("info","you are not authorized");
    		return "other/errPage";  
    	}
    	model.addAttribute("article", article);
    	return "article/modify";
    } 

    @RequestMapping(value = "/article/addTag")
    @ResponseBody
    public String addTag(HttpServletRequest request)throws SQLException{
    	Map<String,Object> user = (Map<String, Object>)request.getSession().getAttribute("user");
    	if((boolean)user.get("status") == true){
    		String aid = request.getParameter("aid");
    		int uid = (int)user.get("id");
    		String tname = request.getParameter("tname");
    		Map<String,Object> map2 = new HashMap<>();
    		map2 = Articlem.addArticleTag(aid, tname);
    		return JsonHelper.jsonEncode(map2);
    	}
    	else{
    		Map<String,Object> map2 = new HashMap<>();
    		map2.put("status", true);
    		map2.put("info","login status error");
    		//model.addAttribute("info","login status error");
    		return JsonHelper.jsonEncode(map2);    
    	}
    } 
    
    @RequestMapping(value = "/article/deleteTag")
    @ResponseBody
    public String delete(HttpServletRequest request)throws SQLException{
    	Map<String,Object> user = (Map<String, Object>)request.getSession().getAttribute("user");
    	if((boolean)user.get("status") == true){
    		String aid = request.getParameter("aid");
    		int uid = (int)user.get("id");
    		String tid = request.getParameter("tid");
    		Map<String,Object> map2 = new HashMap<>();
    		map2 = Articlem.deleteArticleTag(aid, tid);
    		return JsonHelper.jsonEncode(map2);
    	}
    	else{
    		Map<String,Object> map2 = new HashMap<>();
    		map2.put("status", true);
    		map2.put("info","login status error");
    		//model.addAttribute("info","login status error");
    		return JsonHelper.jsonEncode(map2);    
    	}
    }    
    
}  
