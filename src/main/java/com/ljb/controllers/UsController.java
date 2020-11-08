package com.ljb.controllers;

import com.ljb.page.Page;
import com.ljb.pojo.Borrow;
import com.ljb.pojo.Order;
import com.ljb.service.BookService;
import com.ljb.service.BorrowService;
import com.ljb.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(path = "/us")
public class UsController {


    @Autowired
    private BookService bookService;

    @Autowired
    private BorrowService borrowService;

    @Autowired
    private OrderService orderService;

    /**
     * 跳转到用户图书查阅界面
     * @param model
     * @return
     */
    @RequestMapping(path = "/ubList",method = RequestMethod.GET)
    public ModelAndView list(ModelAndView model){
        model.setViewName("book/ubook_view");
        return model;
    }

    /**
     * 显示图书列表
     * @param username
     * @param page
     * @return
     */
    @RequestMapping(path = "/get_ubList",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getList(
            @RequestParam(value = "username",required = false,defaultValue = "") String username,
            Page page
    ){
        Map<String, Object> ret =new HashMap<String, Object>();
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("username", "%"+username+"%");
        queryMap.put("offset", page.getOffset());
        queryMap.put("pageSize", page.getRows());
        ret.put("rows", bookService.findList(queryMap));
        ret.put("total", bookService.getTotal(queryMap));
        return ret;
    }

    /**
     * 1.判断是否可借
     * 2. 添加借阅信息
     * @param borrow
     * @return
     */
    @RequestMapping(path="/addUserBorrow",method=RequestMethod.POST)
    @ResponseBody
    public Map<String, String> addUserBorrow(Borrow borrow){
        Map<String, String> ret = new HashMap<String, String>();
        if(borrow == null){
            ret.put("type", "error");
            ret.put("msg", "未填写借阅信息!");
            return ret;
        }
        if(StringUtils.isEmpty(borrow.getStuName())){
            ret.put("type", "error");
            ret.put("msg", "姓名不能为空!");
            return ret;
        }
        if(StringUtils.isEmpty(borrow.getStuNo())){
            ret.put("type", "error");
            ret.put("msg", "学号不能为空!");
            return ret;
        }
        /*User existUser = userService.findByUserName(user.getUserName());
        if(existUser != null){
            ret.put("type", "error");
            ret.put("msg", "该用户已存在!");
            return ret;
        }*/
       String result = borrowService.showInfo(borrow);
       if(result.equals(""))
       {
           ret.put("type", "error");
           ret.put("msg", "查询失败，请联系管理员!");
           return ret;
       }
        if(result.equals("n")){
            ret.put("type", "error");
            ret.put("msg", "很遗憾，您无借书权限!");
            return ret;
        }
        else if(result.equals("l")||result.equals("y")||result.equals("s")||result.equals("否"))
        {
            ret.put("type", "error");
            ret.put("msg", "您来晚了，该书无法借阅!");
            return ret;
        }
        result=borrowService.userAdd(borrow);
        if(result.equals("n"))
        {
            ret.put("type", "error");
            ret.put("msg", "借阅失败!");
            return ret;
        }
        ret.put("type", "success");
        ret.put("msg", "添加成功!");
        return ret;
    }

    /**
     * 1.判断是否可预约
     * 2.添加预约信息
     * @param order
     * @return
     */
    @RequestMapping(path="/addUserOrder",method=RequestMethod.POST)
    @ResponseBody
    public Map<String, String> addUserOrder(Order order){
        Map<String, String> ret = new HashMap<String, String>();
        if(order == null){
            ret.put("type", "error");
            ret.put("msg", "未填写借阅信息!");
            return ret;
        }
        if(StringUtils.isEmpty(order.getStuName())){
            ret.put("type", "error");
            ret.put("msg", "姓名不能为空!");
            return ret;
        }
        if(StringUtils.isEmpty(order.getStuNo())){
            ret.put("type", "error");
            ret.put("msg", "学号不能为空!");
            return ret;
        }
        /*User existUser = userService.findByUserName(user.getUserName());
        if(existUser != null){
            ret.put("type", "error");
            ret.put("msg", "该用户已存在!");
            return ret;
        }*/
        String result = orderService.showInfo(order);
        if(result.equals(""))
        {
            ret.put("type", "error");
            ret.put("msg", "查询失败，请联系管理员!");
            return ret;
        }
        else if(result.equals("y"))
        {
            ret.put("type", "error");
            ret.put("msg", "您来晚了，书已经被预约了,无法预约!");
            return ret;
        }
        /*else if(result.equals("s")){
            ret.put("type", "error");
            ret.put("msg", "您来晚了，书已经被借走了,是否继续预约!");
            return ret;
        }*/
        else if(result.equals("l"))
        {
            ret.put("type", "error");
            ret.put("msg", "该书已经丢失了,无法预约!");
            return ret;
        }
        else if(result.equals("n"))
        {
            ret.put("type", "error");
            ret.put("msg", "您无法预约，请查看是否有罚款未交!");
            return ret;
        }
        else if(result.equals("否"))
        {
            ret.put("type", "error");
            ret.put("msg", "预约失败!");
            return ret;
        }
        result=orderService.userAdd(order);
        if(result.equals("s"))
        {
            ret.put("type", "error");
            ret.put("msg", "预约失败，该书可直接借阅!");
            return ret;
        }
        else if(result.equals("n"))
        {
            ret.put("type", "success");
            ret.put("msg", "预约成功!");
            return ret;
        }
        ret.put("type", "success");
        ret.put("msg", "预约成功!");
        return ret;
    }

}
