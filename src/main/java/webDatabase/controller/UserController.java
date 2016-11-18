package webDatabase.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;  
import org.springframework.ui.Model;  
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import webDatabase.model.Userm;

@Controller
@RequestMapping(value = "/user")
public class UserController {
	@RequestMapping(value= "/login",method = RequestMethod.GET)
	public String loginPage(){
		return "user/login";
	}
	
	@RequestMapping(value= "/login",method = RequestMethod.POST)
	@ResponseBody
	public String loginCheck(HttpServletRequest request)throws Exception{
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		Map<String,Object> map = Userm.loginCheck(userName, password);
		System.out.println(map);
		return "user/login";
	}
}
 