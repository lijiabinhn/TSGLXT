package com.ljb.controllers;

import com.ljb.pojo.Student;
import com.ljb.pojo.User;
import com.ljb.service.StudentService;
import com.ljb.service.UserService;
import com.ljb.util.CpachaUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RequestMapping(path = "/system")
@Controller
public class SystemController {

    @Autowired
    private UserService userService;

    @Autowired
    private StudentService studentService;

    @RequestMapping(path = "/index",method = RequestMethod.GET)
    public ModelAndView index(ModelAndView model)
    {
        model.setViewName("system/index");
        return model;
    }


    @RequestMapping(path = "/userIndex",method = RequestMethod.GET)
    public ModelAndView userIndex(ModelAndView model){
        model.setViewName("system/userIndex");
        return model;
    }

    //跳转到login页面
    @RequestMapping(path = "/login",method = RequestMethod.GET)
    public ModelAndView login(ModelAndView model)
    {
        model.setViewName("system/login");
        return model;
    }
    @RequestMapping(path = "/faceLogin",method = RequestMethod.GET)
    public ModelAndView faceLogin(ModelAndView model)
    {
        model.setViewName("system/faceLogin");
        return model;
    }

    //生成验证码
    @RequestMapping(path = "/get_cpacha",method = RequestMethod.GET)
    public void getCpacha(HttpServletRequest request,
       //自定义验证码
       @RequestParam(value = "vl",defaultValue = "4",required = false) Integer vl,
       @RequestParam(value = "w",defaultValue = "4",required = false) Integer w,
       @RequestParam(value = "h",defaultValue = "4",required = false) Integer h,
                          HttpServletResponse response)
    {

        CpachaUtil cpachaUtil=new CpachaUtil(4,98,33);
        String generatorVCode = cpachaUtil.generatorVCode();
        request.getSession().setAttribute("loginCpacha", generatorVCode);
        BufferedImage bufferedImage = cpachaUtil.generatorRotateVCodeImage(generatorVCode, true);
        try {
            ImageIO.write(bufferedImage,"gif",response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 登录表单提交
     * */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> login(
            @RequestParam(value = "username", required = true) String username,
            @RequestParam(value = "password", required = true) String password,
            @RequestParam(value = "vcode", required = true) String vcode,
            @RequestParam(value = "type", required = true) int type,
            HttpServletRequest request,
            HttpServletResponse response
    ) {

        Map<String, String> ret = new HashMap<String, String>();
        if (StringUtils.isEmpty(username)) {
            ret.put("type", "error");
            ret.put("msg", "用户名不能为空!");
            return ret;
        }
        if (StringUtils.isEmpty(password)) {
            ret.put("type", "error");
            ret.put("msg", "密码不能为空!");
            return ret;
        }
        if (StringUtils.isEmpty(vcode)) {
            ret.put("type", "error");
            ret.put("msg", "请输入验证码!");
            return ret;
        }
        String loginCpacha = (String) request.getSession().getAttribute("loginCpacha");
        if (StringUtils.isEmpty(loginCpacha)) {
            ret.put("type", "error");
            ret.put("msg", "请刷新后重试!");
            return ret;
        }
        if (!vcode.toUpperCase().equals(loginCpacha.toUpperCase())) {
            ret.put("type", "error");
            ret.put("msg", "验证码错误!");
            return ret;
        }
        request.getSession().setAttribute("loginCpacha", null);
        //从数据库中查找用户
        //User user = userService.findByUserName(username);

        if (type == 1) {
            //管理员登录
            User user = userService.findByUserName(username);
            //System.out.println(user);
            if (user == null) {
                ret.put("type", "error");
                ret.put("msg", "该用户不存在");
                return ret;
            }
            else if (!password.equals(user.getUserPw())) {
                ret.put("type", "error");
                ret.put("msg", "密码不正确!");
                return ret;
            }
            request.getSession().setAttribute("user", user);
        }
        if (type == 2) {
            //学生用户登录
            Student student = studentService.findByUserName(username);
            System.out.println(student);
            if (student == null) {
                ret.put("type", "error");
                ret.put("msg","该用户不存在!");
                return ret;
            }
            if (!password.equals(student.getStuPw())) {
                ret.put("type", "error");
                ret.put("msg", "密码错误!");
                return ret;
            }
            Student.setUserName(username);
            request.getSession().setAttribute("student", student);
        }
        request.getSession().setAttribute("userType", type);
        ret.put("type", "success");
        ret.put("msg", "登录成功!");
        return ret;
    }
@RequestMapping(path = "/login_out",method=RequestMethod.GET)
    public String loginOut(HttpServletRequest request){
        request.getSession().setAttribute("user", null);
        return "redirect:login";
    }
}
