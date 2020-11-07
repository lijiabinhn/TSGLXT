package com.ljb.interceptor;

import com.ljb.pojo.Student;
import com.ljb.pojo.User;
import net.sf.json.JSONObject;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        String url = request.getRequestURI();
        User user = (User) request.getSession().getAttribute("user");
        Student student = (Student) request.getSession().getAttribute("student");
        if(user == null && student==null){
            //拦截器
            if("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))){
                //ajax����
                Map<String, String> ret = new HashMap<String, String>();
                ret.put("type", "error");
                ret.put("msg", "未登录或登录失效！");
                response.getWriter().write(JSONObject.fromObject(ret).toString());
                return false;
            }
            System.out.println("进入拦截器url = " + url);
            response.sendRedirect(request.getContextPath() + "/system/login");
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
