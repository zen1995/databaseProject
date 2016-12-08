package webDatabase.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import webDatabase.model.Tagm;
import webDatabase.util.JsonHelper;

@Controller
public class TagController {
    @RequestMapping(value = "/tag/add")
    @ResponseBody
    public String addTag(HttpServletRequest request,Model model)throws SQLException{
    	String tag = request.getParameter("tagName");
    	boolean r = Tagm.addTag(tag);
    	HashMap<String,Object> map = new HashMap<>();
    	map.put("status",r);
    	return JsonHelper.jsonEncode(map);
    }
    
    @RequestMapping(value = "/tag/show")
    public String showTagsPage(Model model)throws SQLException{
    	List<Map<String,Object>> tags = Tagm.getAllTag();
    	model.addAttribute("tags",tags);
    	return "tag/showTags";
    }
    
    
}
