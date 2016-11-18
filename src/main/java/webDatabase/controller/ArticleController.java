package webDatabase.controller;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;  
import org.springframework.ui.Model;  
import org.springframework.web.bind.annotation.RequestMapping;

import webDatabase.model.Articlem;  
@Controller  
public class ArticleController {  
   
    @RequestMapping(value="/")  
    public String index_jsp(Model model)throws Exception{  
        List<Map<String,Object>> list = Articlem.getAllArticle();
        System.out.println(list);
        model.addAttribute("articles",(Object)list);
         return "article/showAllArticle";
    }   
//    @RequestMapping(value="/*")  
//    public void index_jsrp(Model model){  
//        model.addAttribute("str0121", "Hellow world");  
//        System.out.println("index.jsp");  
//    }  
}  