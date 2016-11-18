package webDatabase.model;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by czn on 2/11/2016.
 */
public class SessionInterceptor extends HandlerInterceptorAdapter{

    @Override
    public synchronized boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
    	if(session.getAttribute("user") == null){
            Map<String,Object> map = new HashMap<>();
            map.put("status", false);
            session.setAttribute("user",map);
        }
        return true;
    }

	@Override
	public void postHandle(
			HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
			throws Exception {
		request.setAttribute("user",request.getSession().getAttribute("user"));
	}
    
    @Override
    public synchronized void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex){
    	
    }
}
