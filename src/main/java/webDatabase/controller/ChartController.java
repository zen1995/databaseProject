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

import webDatabase.model.Chartm;
import webDatabase.model.Tagm;
import webDatabase.util.JsonHelper;

@Controller
public class ChartController {
    
    @RequestMapping(value = "/chart/show")
    public String showTagsPage(Model model)throws SQLException{
    	List<Map<String,Object>> sexData = Chartm.getSexRate();
    	String sData = JsonHelper.jsonEncode(sexData);
    	model.addAttribute("sexData",JsonHelper.jsonEncode(sexData));
    	List<Map<String,Object>> popularUser = Chartm.getPopularUser();
    	String popularUData = JsonHelper.jsonEncode(popularUser);
    	model.addAttribute("popularUser", popularUData);
    	model.addAttribute("popularArticle",JsonHelper.jsonEncode(Chartm.getPopularArticle()));
    	model.addAttribute("mostFan",JsonHelper.jsonEncode(Chartm.getMostLikeUser()));

    	return "chart/show";
    }
}
