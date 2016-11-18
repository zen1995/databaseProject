package webDatabase.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.Session;

import org.springframework.stereotype.Controller;  
import org.springframework.ui.Model;  
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
 