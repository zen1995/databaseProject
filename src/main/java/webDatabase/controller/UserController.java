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

import webDatabase.database.DatabaseHelper;
import webDatabase.database.DatabaseResult;
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
	@RequestMapping(value= "/register",method = RequestMethod.GET)
	public String registerPage(){
		return "user/register";
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
	
	@RequestMapping(value= "/logout")
	@ResponseBody
	public String logout(HttpServletRequest request,Model model)throws Exception{
        Map<String,Object> map = new HashMap<>();
        map.put("status", false);
        map.put("id", -1);
        request.getSession().setAttribute("user",map);
		Map<String,Object>ret = new HashMap<>();
		ret.put("status", true);
		ret.put("info", "success!");
        return JsonHelper.jsonEncode(ret);
	}
	
	
	@RequestMapping(value = "/")
	public String userPage(HttpServletRequest request,Model model)throws SQLException{
		HashMap<String,Object> user = (HashMap<String,Object>)request.getSession().getAttribute("user");
		
		if(!Userm.isLogin(request)){
			model.addAttribute("info", "you are not loged in!");
			return "other/errPage";
		}
		model.addAttribute("articles",Articlem.getUserArticle(String.valueOf(user.get("id"))));
		model.addAttribute("user",user);
		return "user/userSpace";
	}
	
	@RequestMapping(value= "/register/checkAccount")
	@ResponseBody
	public String checkAccount(HttpServletRequest request)throws SQLException{
		String account = request.getParameter("account");
		Map<String, Object> map = Userm.checkAccount(account);
		return JsonHelper.jsonEncode(map);
	}
	
	@RequestMapping(value= "/register/register")
	@ResponseBody	
	public String register(HttpServletRequest request)throws SQLException{
		String account = request.getParameter("account");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String sex = request.getParameter("sex");
		Map<String,Object> ret = Userm.register(account, name, password, sex);
		return JsonHelper.jsonEncode(ret);
	}
	
	@RequestMapping(value= "/editInfo/edit")
	@ResponseBody
	public String edit(HttpServletRequest request)throws SQLException{
		Map<String,Object> user = (Map<String, Object>)request.getSession().getAttribute("user");
		if((boolean)user.get("status") == false){
			Map<String, Object> ret = new HashMap<>();
			ret.put("status", false);
			ret.put("info", "you are not loged in");
			return JsonHelper.jsonEncode(ret);
		}
		String name = request.getParameter("name");
		String sex = request.getParameter("sex");
		String age = request.getParameter("age");
		String password = request.getParameter("password");
		String description = request.getParameter("description");
		Map<String,Object> ret = Userm.editUserInfo((int)user.get("id"), name, sex, age, password, description);
		Userm.refreshUser(request);
		return JsonHelper.jsonEncode(ret);
	}
	
	@RequestMapping(value = "/editInfo")
	public String editPage(HttpServletRequest request)throws SQLException{
		return "user/editUser";
	}	
	
	@RequestMapping(value= "/follow/follow")
	@ResponseBody
	public String follow(HttpServletRequest request)throws SQLException{
		Map<String,Object> user = (Map<String, Object>)request.getSession().getAttribute("user");
		if((boolean)user.get("status") == false){
			Map<String, Object> ret = new HashMap<>();
			ret.put("status", false);
			ret.put("info", "you are not loged in");
			return JsonHelper.jsonEncode(ret);
		}
		String uid2 = request.getParameter("uid2");
		Map<String, Object> ret = Userm.follow(String.valueOf(user.get("id")), uid2);
		Userm.refreshUser(request);
		return JsonHelper.jsonEncode(ret);
	}
	
	@RequestMapping(value= "/follow/unfollow")
	@ResponseBody
	public String unfollow(HttpServletRequest request)throws SQLException{
		Map<String,Object> user = (Map<String, Object>)request.getSession().getAttribute("user");
		if((boolean)user.get("status") == false){
			Map<String, Object> ret = new HashMap<>();
			ret.put("status", false);
			ret.put("info", "you are not loged in");
			return JsonHelper.jsonEncode(ret);
		}
		String uid2 = request.getParameter("uid2");
		Map<String, Object> ret = Userm.unFollow(String.valueOf(user.get("id")), uid2);
		Userm.refreshUser(request);
		return JsonHelper.jsonEncode(ret);
	}
}
 