package webDatabase.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.Session;

import org.springframework.stereotype.Controller;  
import org.springframework.ui.Model;  
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import webDatabase.model.Articlem;
import webDatabase.model.Userm;
import webDatabase.util.JsonHelper;

@Controller
@RequestMapping(value = "/user")
public class UserController {
	@RequestMapping(value= "/login",method = RequestMethod.GET)
	public String loginPage(){
		return "user/login";
	}
	
	@RequestMapping(value= "/login",method = RequestMethod.POST)
	@ResponseBody
	public String loginCheck(HttpServletRequest request,Model model)throws Exception{
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		Map<String,Object> map = Userm.loginCheck(userName, password);
		String json = JsonHelper.jsonEncode(map);
		model.addAttribute("user",map);
		request.getSession().setAttribute("user",map);
		return json;
	}
	
	@RequestMapping(value = "/")
	public String userPage(HttpServletRequest request,Model model)throws SQLException{
		HashMap<String,Object> user = (HashMap<String,Object>)request.getSession().getAttribute("user");
		
		if(!Userm.isLogin(request)){
			model.addAttribute("info", "you are not loged in!");
			return "error Page";
		}
		model.addAttribute("articles",Articlem.getUserArticle(String.valueOf(user.get("id"))));
		model.addAttribute("user",user);
		return "user/userSpace";
	}
}
 