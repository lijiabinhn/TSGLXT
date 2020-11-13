package com.ljb.controllers;

import com.ljb.page.Page;
import com.ljb.pojo.Student;
import com.ljb.service.ContinueService;
import com.ljb.service.LoseService;
import com.ljb.service.OrderService;
import com.ljb.service.ReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(path = "/userCenter")
public class UserCenterController {


    @Autowired
    private OrderService orderService;

    @Autowired
    private ReturnService returnService;

    @Autowired
    private ContinueService continueService;

    @Autowired
    private LoseService loseService;



    /**
     * 跳转到我的预约界面
     * @param model
     * @return
     */

    @RequestMapping(path = "/orderList",method = RequestMethod.GET)
    public ModelAndView orderList(ModelAndView model){
        model.setViewName("userCenter/userOrder_view");
        return model;
    }

    /**
     * 显示我的预约信息
     * @param username
     * @param page
     * @return
     */

    @RequestMapping(path = "/get_orderList",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getOrderList(
            @RequestParam(value = "username",required = false,defaultValue = "") String username,
            Page page
    ){
        Map<String, Object> ret =new HashMap<String, Object>();
        Map<String, Object> queryMap = new HashMap<String, Object>();
        username = Student.getUserName();
        queryMap.put("username", username);
        queryMap.put("offset", page.getOffset());
        queryMap.put("pageSize", page.getRows());
        ret.put("rows", orderService.userList(queryMap));
        ret.put("total", orderService.getTotal(queryMap));
        return ret;
    }


    /**
     * 跳转到我的续借界面
     * @param model
     * @return
     */

    @RequestMapping(path = "/continueList",method = RequestMethod.GET)
    public ModelAndView continueList(ModelAndView model){
        model.setViewName("userCenter/userContinue_view");
        return model;
    }

    /**
     * 显示我的续借信息
     * @param username
     * @param page
     * @return
     */

    @RequestMapping(path = "/get_continueList",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getContinueList(
            @RequestParam(value = "username",required = false,defaultValue = "") String username,
            Page page
    ){
        Map<String, Object> ret =new HashMap<String, Object>();
        Map<String, Object> queryMap = new HashMap<String, Object>();
        username = Student.getUserName();
        queryMap.put("username", username);
        queryMap.put("offset", page.getOffset());
        queryMap.put("pageSize", page.getRows());
        ret.put("rows", continueService.userList(queryMap));
        ret.put("total", continueService.getTotal(queryMap));
        return ret;
    }


    /**
     * 跳转到我的归还界面
     * @param model
     * @return
     */

    @RequestMapping(path = "/returnList",method = RequestMethod.GET)
    public ModelAndView returnList(ModelAndView model){
        model.setViewName("userCenter/userReturn_view");
        return model;
    }

    /**
     * 显示我的归还信息
     * @param username
     * @param page
     * @return
     */

    @RequestMapping(path = "/get_returnList",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getReturnList(
            @RequestParam(value = "username",required = false,defaultValue = "") String username,
            Page page
    ){
        Map<String, Object> ret =new HashMap<String, Object>();
        Map<String, Object> queryMap = new HashMap<String, Object>();
        username = Student.getUserName();
        queryMap.put("username", username);
        queryMap.put("offset", page.getOffset());
        queryMap.put("pageSize", page.getRows());
        ret.put("rows", returnService.userList(queryMap));
        ret.put("total", returnService.getTotal(queryMap));
        return ret;
    }



    /**
     * 跳转到我的挂失界面
     * @param model
     * @return
     */

    @RequestMapping(path = "/loseList",method = RequestMethod.GET)
    public ModelAndView loseList(ModelAndView model){
        model.setViewName("userCenter/userLose_view");
        return model;
    }

    /**
     * 显示我的挂失信息
     * @param username
     * @param page
     * @return
     */

    @RequestMapping(path = "/get_loseList",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getLoseList(
            @RequestParam(value = "username",required = false,defaultValue = "") String username,
            Page page
    ){
        Map<String, Object> ret =new HashMap<String, Object>();
        Map<String, Object> queryMap = new HashMap<String, Object>();
        username = Student.getUserName();
        queryMap.put("username", username);
        queryMap.put("offset", page.getOffset());
        queryMap.put("pageSize", page.getRows());
        ret.put("rows", loseService.userList(queryMap));
        ret.put("total", loseService.getTotal(queryMap));
        return ret;
    }
}
