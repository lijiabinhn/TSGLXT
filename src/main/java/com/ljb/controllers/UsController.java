package com.ljb.controllers;

import com.ljb.page.Page;
import com.ljb.pojo.*;
import com.ljb.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.xml.namespace.QName;
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

    @Autowired
    private ContinueService continueService;

    @Autowired
    private ReturnService returnService;

    @Autowired
    private LoseService loseService;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private ContributeService contributeService;

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


    /**
     * 跳转到用户借阅图书界面
     * @param model
     * @return
     */

    @RequestMapping(path = "/uoList",method = RequestMethod.GET)
    public ModelAndView uoList(ModelAndView model){
        model.setViewName("operation/borrow/uborrow_view");
        return model;
    }

    /**
     * 显示借阅图书列表
     * @param username
     * @param page
     * @return
     */

    @RequestMapping(path = "/get_uoList",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getBorrowList(
            @RequestParam(value = "username",required = false,defaultValue = "") String username,
            Page page
    ){
        Map<String, Object> ret =new HashMap<String, Object>();
        Map<String, Object> queryMap = new HashMap<String, Object>();
        username = Student.getUserName();
        queryMap.put("username", username);
        queryMap.put("offset", page.getOffset());
        queryMap.put("pageSize", page.getRows());
        ret.put("rows", borrowService.userList(queryMap));
        ret.put("total", borrowService.getTotal(queryMap));
        return ret;
    }

    /**
     * 添加续借信息
     * @param continues
     * @return
     */

    @RequestMapping(path="/addUserContinue",method=RequestMethod.POST)
    @ResponseBody
    public Map<String, String> addUserContinue(Continue continues){
        Map<String, String> ret = new HashMap<String, String>();
        if(continues == null){
            ret.put("type", "error");
            ret.put("msg", "未填写借阅信息!");
            return ret;
        }
        if(StringUtils.isEmpty(continues.getStuName())){
            ret.put("type", "error");
            ret.put("msg", "姓名不能为空!");
            return ret;
        }
        if(StringUtils.isEmpty(continues.getStuNo())){
            ret.put("type", "error");
            ret.put("msg", "学号不能为空!");
            return ret;
        }
        String result = continueService.userAdd(continues);
        if(result.equals(""))
        {
            ret.put("type", "error");
            ret.put("msg", "查询失败，请联系管理员!");
            return ret;
        }
        else if(result.equals("n"))
        {
            ret.put("type", "error");
            ret.put("msg", "续借失败!");
            return ret;
        }
        ret.put("type", "success");
        ret.put("msg", "续借成功!");
        return ret;
    }


    /**
     * 添加归还信息
     * @param returns
     * @return
     */

    @RequestMapping(path="/addUserReturn",method=RequestMethod.POST)
    @ResponseBody
    public Map<String, String> addUserReturn(Return returns){
        Map<String, String> ret = new HashMap<String, String>();
        if(returns == null){
            ret.put("type", "error");
            ret.put("msg", "未填写借阅信息!");
            return ret;
        }
        if(StringUtils.isEmpty(returns.getStuName())){
            ret.put("type", "error");
            ret.put("msg", "姓名不能为空!");
            return ret;
        }
        if(StringUtils.isEmpty(returns.getStuNo())){
            ret.put("type", "error");
            ret.put("msg", "学号不能为空!");
            return ret;
        }
        String result = returnService.userAdd(returns);
        if(result.equals(""))
        {
            ret.put("type", "error");
            ret.put("msg", "查询失败，请联系管理员!");
            return ret;
        }
        else if(result.equals("n"))
        {
            ret.put("type", "error");
            ret.put("msg", "归还失败!");
            return ret;
        }
        ret.put("type", "success");
        ret.put("msg", "归还成功!");
        return ret;
    }


    /**
     * 添加挂失信息
     * @param lose
     * @return
     */

    @RequestMapping(path="/addUserLose",method=RequestMethod.POST)
    @ResponseBody
    public Map<String, String> addUserLose(Lose lose){
        Map<String, String> ret = new HashMap<String, String>();
        if(lose == null){
            ret.put("type", "error");
            ret.put("msg", "未填写借阅信息!");
            return ret;
        }
        if(StringUtils.isEmpty(lose.getStuName())){
            ret.put("type", "error");
            ret.put("msg", "姓名不能为空!");
            return ret;
        }
        if(StringUtils.isEmpty(lose.getStuNo())){
            ret.put("type", "error");
            ret.put("msg", "学号不能为空!");
            return ret;
        }
        String result = loseService.userAdd(lose);
        if(result.equals(""))
        {
            ret.put("type", "error");
            ret.put("msg", "查询失败，请联系管理员!");
            return ret;
        }
        else if(result.equals("n"))
        {
            ret.put("type", "error");
            ret.put("msg", "挂失失败!");
            return ret;
        }
        ret.put("type", "success");
        ret.put("msg", "挂失成功!");
        return ret;
    }

    /**
     * 跳转到罚款列表
     * @param model
     * @return
     */

    @RequestMapping(path = "/utList",method = RequestMethod.GET)
    public ModelAndView utList(ModelAndView model){
        model.setViewName("ticket/uticket_view");
        return model;
    }


    /**
     * 显示罚款列表
     * @param username
     * @param page
     * @return
     */

    @RequestMapping(path = "/get_utList",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getTicketList(
            @RequestParam(value = "username",required = false,defaultValue = "") String username,
            Page page
    ){
        Map<String, Object> ret =new HashMap<String, Object>();
        Map<String, Object> queryMap = new HashMap<String, Object>();
        username = Student.getUserName();
        queryMap.put("username", username);
        queryMap.put("offset", page.getOffset());
        queryMap.put("pageSize", page.getRows());
        ret.put("rows", ticketService.userList(queryMap));
        ret.put("total", ticketService.getTotal(queryMap));
        return ret;
    }


    /**
     * 跳转到捐献图书列表
     * @param model
     * @return
     */

    @RequestMapping(path = "/ucList",method = RequestMethod.GET)
    public ModelAndView ucList(ModelAndView model){
        model.setViewName("contribute/contribute_view");
        return model;
    }

    /**
     * 显示捐献图书列表
     * @param username
     * @param page
     * @return
     */

    @RequestMapping(path = "/get_ucList",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getContributeList(
            @RequestParam(value = "username",required = false,defaultValue = "") String username,
            Page page
    ){
        Map<String, Object> ret =new HashMap<String, Object>();
        Map<String, Object> queryMap = new HashMap<String, Object>();
        username = Student.getUserName();
        queryMap.put("username", username);
        queryMap.put("offset", page.getOffset());
        queryMap.put("pageSize", page.getRows());
        ret.put("rows", contributeService.userList(queryMap));
        ret.put("total", contributeService.getTotal(queryMap));
        return ret;
    }

    /**
     * 添加捐献图书信息
     * @param contribute
     * @return
     */

    @RequestMapping(path="/addUserContribute",method=RequestMethod.POST)
    @ResponseBody
    public Map<String, String> addUserContribute(Contribute contribute){
        Map<String, String> ret = new HashMap<String, String>();
        if(contribute == null){
            ret.put("type", "error");
            ret.put("msg", "未填写借阅信息!");
            return ret;
        }
        if(StringUtils.isEmpty(contribute.getcName())){
            ret.put("type", "error");
            ret.put("msg", "姓名不能为空!");
            return ret;
        }
        if(StringUtils.isEmpty(contribute.getcBook())){
            ret.put("type", "error");
            ret.put("msg", "书名不能为空!");
            return ret;
        }
        int result = contributeService.add(contribute);
        if(result<=0)
        {
            ret.put("type", "error");
            ret.put("msg", "挂失失败!");
            return ret;
        }
        ret.put("type", "success");
        ret.put("msg", "挂失成功!");
        return ret;
    }

}


