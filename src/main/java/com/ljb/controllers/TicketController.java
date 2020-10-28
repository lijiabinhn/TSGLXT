package com.ljb.controllers;

import com.ljb.page.Page;
import com.ljb.service.TicketService;
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
@RequestMapping(path = "/ticket")
public class TicketController {

        @Autowired
        private TicketService ticketService;

        @RequestMapping(path = "/list")
        public ModelAndView list(ModelAndView model){
            model.setViewName("ticket/ticket_view");
       /* List<grade> findAll = gradeService.findAll();
        model.addObject("gradeList",findAll );
        model.addObject("gradeListJson", JSONArray.fromObject(findAll));*/
            return model;
        }
    @RequestMapping(path="/get_list",method= RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getList(
            @RequestParam(value="username",required=false,defaultValue="") String username,
            Page page
    ){
        Map<String, Object> ret = new HashMap<String, Object>();
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("username", "%"+username+"%");
        queryMap.put("offset", page.getOffset());
        queryMap.put("pageSize", page.getRows());
        ret.put("rows", ticketService.findList(queryMap));
        ret.put("total", ticketService.getTotal(queryMap));
        return ret;
    }
}
