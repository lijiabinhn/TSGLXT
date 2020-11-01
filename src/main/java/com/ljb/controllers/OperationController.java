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

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(path = "/operation")
public class OperationController {

    @Autowired
    private BorrowService borrowService;

    @Autowired
    private ReturnService returnService;

    @Autowired
    private LoseService loseService;

    @Autowired
    private ContinueService continueService;

    @Autowired
    private OrderService orderService;

    /**
     * 借阅图书列表
     * @param model
     * @return
     */
    @RequestMapping(path = "/borrowList",method = RequestMethod.GET)
    public ModelAndView borrowList(ModelAndView model){
        model.setViewName("operation/borrow/borrow_view");
        return model;
    }

    @RequestMapping(path = "/get_borrowList",method =RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> get_borrowList(
            @RequestParam(value = "username",required = false,defaultValue = "") String username,
            Page page
    ){
        Map<String,Object> ret = new HashMap<String,Object>();
        Map<String,Object> queryMap = new HashMap<String,Object>();
        queryMap.put("username", "%"+username+"%");
        queryMap.put("offset", page.getOffset());
        queryMap.put("pageSize", page.getRows());
        ret.put("rows", borrowService.findList(queryMap));
        ret.put("total", borrowService.getTotal(queryMap));
        return ret;
    }

    @RequestMapping(path="/addBorrow",method=RequestMethod.POST)
    @ResponseBody
    public Map<String, String> addBorrow(Borrow borrow){
        Map<String, String> ret = new HashMap<String, String>();
        if(borrow == null){
            ret.put("type", "error");
            ret.put("msg", "未添加借阅信息!");
            return ret;
        }
        if(StringUtils.isEmpty(borrow.getStuName())){
            ret.put("type", "error");
            ret.put("msg", "用户名不能为空!");
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
        int i = borrowService.add(borrow);
        if(i <= 0){
            ret.put("type", "error");
            ret.put("msg", "添加失败!");
            return ret;
        }
        ret.put("type", "success");
        ret.put("msg", "添加成功!");
        return ret;
    }

    @RequestMapping(path = "/editBorrow",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> editBorrow(Borrow borrow)
    {
        Map<String,String> ret=new HashMap<String, String>();
        if(borrow==null)
        {
            ret.put("type","error");
            ret.put("msg","请选择要修改的行！");
            return ret;
        }
        if(StringUtils.isEmpty(borrow.getStuName()))
        {
            ret.put("type","error");
            ret.put("msg","请填写用户名！");
            return ret;
        }
        if(StringUtils.isEmpty(borrow.getStuNo()))
        {
            ret.put("type","error");
            ret.put("msg","请填写学号！");
            return ret;
        }
        int i = borrowService.edit(borrow);
        if(i<=0)
        {
            ret.put("type","error");
            ret.put("msg","修改用户数据失败！");
            return ret;
        }
        ret.put("type","error");
        ret.put("msg","修改成功！");
        return ret;
    }

    @RequestMapping(path = "/deleteBorrow",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> deleteBorrow(
            @RequestParam(value = "ids[]",required = true) Integer ids[]
    )
    {
        Map<String,String> ret=new HashMap<String,String>();
        if(ids == null){
            ret.put("type", "error");
            ret.put("msg", "请选中要删除的行!");
            return ret;
        }
        String idsString = "";
        for(int id:ids){
            idsString += id + ",";
        }
        idsString = idsString.substring(0,idsString.length()-1);
        int i = borrowService.delete(idsString);
        if(i <= 0){
            ret.put("type", "error");
            ret.put("msg", "删除失败!");
            return ret;
        }
        ret.put("type", "success");
        ret.put("msg", "删除成功!");
        return ret;

    }


    /**
     * 归还图书列表
     */
    @RequestMapping(path = "/returnList",method = RequestMethod.GET)
    public ModelAndView returnList(ModelAndView model){
        model.setViewName("operation/return/return_view");
        return model;
    }

    @RequestMapping(path = "/get_returnList",method =RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> get_returnList(
            @RequestParam(value = "username",required = false,defaultValue = "") String username,
            Page page
    ){
        Map<String,Object> ret = new HashMap<String,Object>();
        Map<String,Object> queryMap = new HashMap<String,Object>();
        queryMap.put("username", "%"+username+"%");
        queryMap.put("offset", page.getOffset());
        queryMap.put("pageSize", page.getRows());
        ret.put("rows", returnService.findList(queryMap));
        ret.put("total", returnService.getTotal(queryMap));
        return ret;
    }

    @RequestMapping(path="/addReturn",method=RequestMethod.POST)
    @ResponseBody
    public Map<String, String> addReturn(Return returns){
        Map<String, String> ret = new HashMap<String, String>();
        if(returns == null){
            ret.put("type", "error");
            ret.put("msg", "未添加借阅信息!");
            return ret;
        }
        if(StringUtils.isEmpty(returns.getStuName())){
            ret.put("type", "error");
            ret.put("msg", "用户名不能为空!");
            return ret;
        }
        if(StringUtils.isEmpty(returns.getStuNo())){
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
        int i = returnService.add(returns);
        if(i <= 0){
            ret.put("type", "error");
            ret.put("msg", "添加失败!");
            return ret;
        }
        ret.put("type", "success");
        ret.put("msg", "添加成功!");
        return ret;
    }

    @RequestMapping(path = "/editReturn",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> editReturn(Return returns)
    {
        Map<String,String> ret=new HashMap<String, String>();
        if(returns==null)
        {
            ret.put("type","error");
            ret.put("msg","请选择要修改的行！");
            return ret;
        }
        if(StringUtils.isEmpty(returns.getStuName()))
        {
            ret.put("type","error");
            ret.put("msg","请填写用户名！");
            return ret;
        }
        if(StringUtils.isEmpty(returns.getStuNo()))
        {
            ret.put("type","error");
            ret.put("msg","请填写学号！");
            return ret;
        }
        int i = returnService.edit(returns);
        if(i<=0)
        {
            ret.put("type","error");
            ret.put("msg","修改用户数据失败！");
            return ret;
        }
        ret.put("type","error");
        ret.put("msg","修改成功！");
        return ret;
    }

    @RequestMapping(path = "/deleteReturn",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> deleteReturn(
            @RequestParam(value = "ids[]",required = true) Integer ids[]
    )
    {
        Map<String,String> ret=new HashMap<String,String>();
        if(ids == null){
            ret.put("type", "error");
            ret.put("msg", "请选中要删除的行!");
            return ret;
        }
        String idsString = "";
        for(int id:ids){
            idsString += id + ",";
        }
        idsString = idsString.substring(0,idsString.length()-1);
        int i = returnService.delete(idsString);
        if(i <= 0){
            ret.put("type", "error");
            ret.put("msg", "删除失败!");
            return ret;
        }
        ret.put("type", "success");
        ret.put("msg", "删除成功!");
        return ret;

    }

    /**
     * 挂失列表
     */

    @RequestMapping(path = "/loseList",method = RequestMethod.GET)
    public ModelAndView loseList(ModelAndView model){
        model.setViewName("operation/lose/lose_view");
        return model;
    }

    @RequestMapping(path = "/get_loseList",method =RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> get_loseList(
            @RequestParam(value = "username",required = false,defaultValue = "") String username,
            Page page
    ){
        Map<String,Object> ret = new HashMap<String,Object>();
        Map<String,Object> queryMap = new HashMap<String,Object>();
        queryMap.put("username", "%"+username+"%");
        queryMap.put("offset", page.getOffset());
        queryMap.put("pageSize", page.getRows());
        ret.put("rows", loseService.findList(queryMap));
        ret.put("total", loseService.getTotal(queryMap));
        return ret;
    }

    @RequestMapping(path="/addLose",method=RequestMethod.POST)
    @ResponseBody
    public Map<String, String> addLose(Lose lose){
        Map<String, String> ret = new HashMap<String, String>();
        if(lose == null){
            ret.put("type", "error");
            ret.put("msg", "未添加借阅信息!");
            return ret;
        }
        if(StringUtils.isEmpty(lose.getStuName())){
            ret.put("type", "error");
            ret.put("msg", "用户名不能为空!");
            return ret;
        }
        if(StringUtils.isEmpty(lose.getStuNo())){
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
        int i = loseService.add(lose);
        if(i <= 0){
            ret.put("type", "error");
            ret.put("msg", "添加失败!");
            return ret;
        }
        ret.put("type", "success");
        ret.put("msg", "添加成功!");
        return ret;
    }

    @RequestMapping(path = "/editLose",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> editLose(Lose lose)
    {
        Map<String,String> ret=new HashMap<String, String>();
        if(lose==null)
        {
            ret.put("type","error");
            ret.put("msg","请选择要修改的行！");
            return ret;
        }
        if(StringUtils.isEmpty(lose.getStuName()))
        {
            ret.put("type","error");
            ret.put("msg","请填写用户名！");
            return ret;
        }
        if(StringUtils.isEmpty(lose.getStuNo()))
        {
            ret.put("type","error");
            ret.put("msg","请填写学号！");
            return ret;
        }
        int i = loseService.edit(lose);
        if(i<=0)
        {
            ret.put("type","error");
            ret.put("msg","修改用户数据失败！");
            return ret;
        }
        ret.put("type","error");
        ret.put("msg","修改成功！");
        return ret;
    }

    @RequestMapping(path = "/deleteLose",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> deleteLose(
            @RequestParam(value = "ids[]",required = true) Integer ids[]
    )
    {
        Map<String,String> ret=new HashMap<String,String>();
        if(ids == null){
            ret.put("type", "error");
            ret.put("msg", "请选中要删除的行!");
            return ret;
        }
        String idsString = "";
        for(int id:ids){
            idsString += id + ",";
        }
        idsString = idsString.substring(0,idsString.length()-1);
        int i = loseService.delete(idsString);
        if(i <= 0){
            ret.put("type", "error");
            ret.put("msg", "删除失败!");
            return ret;
        }
        ret.put("type", "success");
        ret.put("msg", "删除成功!");
        return ret;

    }


    /**
     * 续借列表
     */
    @RequestMapping(path = "/continueList",method = RequestMethod.GET)
    public ModelAndView continueList(ModelAndView model){
        model.setViewName("operation/continue/continue_view");
        return model;
    }

    @RequestMapping(path = "/get_continueList",method =RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> get_continueList(
            @RequestParam(value = "username",required = false,defaultValue = "") String username,
            Page page
    ){
        Map<String,Object> ret = new HashMap<String,Object>();
        Map<String,Object> queryMap = new HashMap<String,Object>();
        queryMap.put("username", "%"+username+"%");
        queryMap.put("offset", page.getOffset());
        queryMap.put("pageSize", page.getRows());
        ret.put("rows", continueService.findList(queryMap));
        ret.put("total", continueService.getTotal(queryMap));
        return ret;
    }


    @RequestMapping(path="/addContinue",method=RequestMethod.POST)
    @ResponseBody
    public Map<String, String> addContinue(Continue continues){
        Map<String, String> ret = new HashMap<String, String>();
        if(continues == null){
            ret.put("type", "error");
            ret.put("msg", "未添加借阅信息!");
            return ret;
        }
        if(StringUtils.isEmpty(continues.getStuName())){
            ret.put("type", "error");
            ret.put("msg", "用户名不能为空!");
            return ret;
        }
        if(StringUtils.isEmpty(continues.getStuNo())){
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
        int i = continueService.add(continues);
        if(i <= 0){
            ret.put("type", "error");
            ret.put("msg", "添加失败!");
            return ret;
        }
        ret.put("type", "success");
        ret.put("msg", "添加成功!");
        return ret;
    }

    @RequestMapping(path = "/editContinue",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> editContinue(Continue continues)
    {
        Map<String,String> ret=new HashMap<String, String>();
        if(continues==null)
        {
            ret.put("type","error");
            ret.put("msg","请选择要修改的行！");
            return ret;
        }
        if(StringUtils.isEmpty(continues.getStuName()))
        {
            ret.put("type","error");
            ret.put("msg","请填写用户名！");
            return ret;
        }
        if(StringUtils.isEmpty(continues.getStuNo()))
        {
            ret.put("type","error");
            ret.put("msg","请填写学号！");
            return ret;
        }
        int i = continueService.edit(continues);
        if(i<=0)
        {
            ret.put("type","error");
            ret.put("msg","修改用户数据失败！");
            return ret;
        }
        ret.put("type","error");
        ret.put("msg","修改成功！");
        return ret;
    }

    @RequestMapping(path = "/deleteContinue",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> deleteContinue(
            @RequestParam(value = "ids[]",required = true) Integer ids[]
    )
    {
        Map<String,String> ret=new HashMap<String,String>();
        if(ids == null){
            ret.put("type", "error");
            ret.put("msg", "请选中要删除的行!");
            return ret;
        }
        String idsString = "";
        for(int id:ids){
            idsString += id + ",";
        }
        idsString = idsString.substring(0,idsString.length()-1);
        int i = continueService.delete(idsString);
        if(i <= 0){
            ret.put("type", "error");
            ret.put("msg", "删除失败!");
            return ret;
        }
        ret.put("type", "success");
        ret.put("msg", "删除成功!");
        return ret;

    }


    /**
     * 预约列表
     */
    @RequestMapping(path = "/orderList",method = RequestMethod.GET)
    public ModelAndView orderList(ModelAndView model){
        model.setViewName("operation/order/order_view");
        return model;
    }

    @RequestMapping(path = "/get_orderList",method =RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> get_orderList(
            @RequestParam(value = "username",required = false,defaultValue = "") String username,
            Page page
    ){
        Map<String,Object> ret = new HashMap<String,Object>();
        Map<String,Object> queryMap = new HashMap<String,Object>();
        queryMap.put("username", "%"+username+"%");
        queryMap.put("offset", page.getOffset());
        queryMap.put("pageSize", page.getRows());
        ret.put("rows", orderService.findList(queryMap));
        ret.put("total", orderService.getTotal(queryMap));
        return ret;
    }

    @RequestMapping(path="/addOrder",method=RequestMethod.POST)
    @ResponseBody
    public Map<String, String> addOrder(Order order){
        Map<String, String> ret = new HashMap<String, String>();
        if(order == null){
            ret.put("type", "error");
            ret.put("msg", "未添加借阅信息!");
            return ret;
        }
        if(StringUtils.isEmpty(order.getStuName())){
            ret.put("type", "error");
            ret.put("msg", "用户名不能为空!");
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
        int i = orderService.add(order);
        if(i <= 0){
            ret.put("type", "error");
            ret.put("msg", "添加失败!");
            return ret;
        }
        ret.put("type", "success");
        ret.put("msg", "添加成功!");
        return ret;
    }

    @RequestMapping(path = "/editOrder",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> editOrder(Order order)
    {
        Map<String,String> ret=new HashMap<String, String>();
        if(order==null)
        {
            ret.put("type","error");
            ret.put("msg","请选择要修改的行！");
            return ret;
        }
        if(StringUtils.isEmpty(order.getStuName()))
        {
            ret.put("type","error");
            ret.put("msg","请填写用户名！");
            return ret;
        }
        if(StringUtils.isEmpty(order.getStuNo()))
        {
            ret.put("type","error");
            ret.put("msg","请填写学号！");
            return ret;
        }
        int i = orderService.edit(order);
        if(i<=0)
        {
            ret.put("type","error");
            ret.put("msg","修改用户数据失败！");
            return ret;
        }
        ret.put("type","error");
        ret.put("msg","修改成功！");
        return ret;
    }

    @RequestMapping(path = "/deleteOrder",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> deleteOrder(
            @RequestParam(value = "ids[]",required = true) Integer ids[]
    )
    {
        Map<String,String> ret=new HashMap<String,String>();
        if(ids == null){
            ret.put("type", "error");
            ret.put("msg", "请选中要删除的行!");
            return ret;
        }
        String idsString = "";
        for(int id:ids){
            idsString += id + ",";
        }
        idsString = idsString.substring(0,idsString.length()-1);
        int i = orderService.delete(idsString);
        if(i <= 0){
            ret.put("type", "error");
            ret.put("msg", "删除失败!");
            return ret;
        }
        ret.put("type", "success");
        ret.put("msg", "删除成功!");
        return ret;

    }
}
