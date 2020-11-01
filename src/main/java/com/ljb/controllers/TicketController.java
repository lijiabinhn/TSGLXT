package com.ljb.controllers;

import com.ljb.page.Page;
import com.ljb.pojo.Student;
import com.ljb.pojo.Ticket;
import com.ljb.service.TicketService;
import com.ljb.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(path = "/ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @RequestMapping(path = "/list",method = RequestMethod.GET)
    public ModelAndView list(ModelAndView model){
        model.setViewName("ticket/ticket_view");
        return model;
    }

    @RequestMapping(path = "/get_list",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getList(
            @RequestParam(value = "username",required = false,defaultValue = "") String username,
            Page page
    ){
        Map<String,Object> ret = new HashMap<String,Object>();
        Map<String,Object> queryMap = new HashMap<String,Object>();
        queryMap.put("username", "%"+username+"%");
        queryMap.put("offset", page.getOffset());
        queryMap.put("pageSize", page.getRows());
        ret.put("rows", ticketService.findList(queryMap));
        ret.put("total", ticketService.getTotal(queryMap));
        return ret;
    }

    @RequestMapping(path="/add",method=RequestMethod.POST)
    @ResponseBody
    public Map<String, String> add(Ticket ticket) {
        Map<String, String> ret = new HashMap<String, String>();
        if(StringUtils.isEmpty(ticket.getStuNo())){
            ret.put("type", "error");
            ret.put("msg", "学号不能为空！");
            return ret;
        }
        if(StringUtils.isEmpty(ticket.getStuName())){
            ret.put("type", "error");
            ret.put("msg", "姓名不能为空！");
            return ret;
        }
        /*if(student.getStuCid() == 0){
            ret.put("type", "error");
            ret.put("msg", "班级ID不能为空！");
            return ret;
        }*/
//        if(isExist(student.getStuSn(), student.getStuId())){
//            ret.put("type", "error");
//            ret.put("msg", "该用户已存在！");
//            return ret;
//        }
        // student.setSn(StringUtil.generateSn("S", ""));
        int i = ticketService.add(ticket);
        if(i <= 0){
            ret.put("type", "error");
            ret.put("msg", "添加罚款信息失败！");
            return ret;
        }
        ret.put("type", "success");
        ret.put("msg", "添加成功！");
        return ret;
    }

    @RequestMapping(path="/edit",method=RequestMethod.POST)
    @ResponseBody
    public Map<String, String> edit(Ticket ticket){
        Map<String, String> ret = new HashMap<String, String>();
        if(StringUtils.isEmpty(ticket.getStuNo())){
            ret.put("type", "error");
            ret.put("msg", "请输入学号！");
            return ret;
        }
        if(StringUtils.isEmpty(ticket.getStuName())){
            ret.put("type", "error");
            ret.put("msg", "请输入姓名！");
            return ret;
        }
        /*if(student.getStuCid() == 0){
            ret.put("type", "error");
            ret.put("msg", "请输入班级Id");
            return ret;
        }*/
        /*if(isExist(student.getStuSn(), student.getStuId())){
            ret.put("type", "error");
            ret.put("msg", "该用户已存在!");
            return ret;
        }*/
        // student.setStuSn(StringUtil.generateSn("S", ""));
        int i = ticketService.edit(ticket);
        if(i<0){
            ret.put("type", "error");
            ret.put("msg", "执行出错！");
            return ret;
        }
        ret.put("type", "success");
        ret.put("msg", "修改成功！");
        return ret;
    }


    @RequestMapping(path="/delete",method=RequestMethod.POST)
    @ResponseBody
    public Map<String, String> delete(
            @RequestParam(value="ids[]",required=true) Long[] ids
    ){
        Map<String, String> ret = new HashMap<String, String>();
        if(ids == null || ids.length == 0){
            ret.put("type", "error");
            ret.put("msg", "请选择要删除的行！");
            return ret;
        }
        try {
            if(ticketService.delete(StringUtil.joinString(Arrays.asList(ids), ",")) <= 0){
                ret.put("type", "error");
                ret.put("msg", "删除失败！");
                return ret;
            }
        } catch (Exception e) {
            // TODO: handle exception
            ret.put("type", "error");
            ret.put("msg", "请联系管理员");
            return ret;
        }
        ret.put("type", "success");
        ret.put("msg", "删除成功！");
        return ret;
    }
}
