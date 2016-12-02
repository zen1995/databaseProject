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
    		map.put("publisherUser", user.get("id"));
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
    
    

    
    
}  
