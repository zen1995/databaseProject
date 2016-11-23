package webDatabase.controller;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.junit.runners.Parameterized.Parameter;
import org.springframework.stereotype.Controller;  
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import webDatabase.model.Articlem;  
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
    		return "TODO err Page";
    	}
    	return "TODO";
    }

	@RequestMapping(value = "/article/byuser")
	public String articlebyuser() {
		return "/article/byuser";
	}
	
    @RequestMapping("/article/search")
    public String search(HttpServletRequest request,Model model)throws SQLException{
    	String queryType = request.getParameter("type");
    	String string = request.getParameter("value");
    	List<Map<String,Object>> list = Articlem.search(queryType,string);
    	model.addAttribute("articles",list);
    	if(list === null){
    		model.addAttribute("info","invalid paramer-"+queryType);
    		return "TODO err Page";    		
    	}
    	return "TODO";
    }
}  