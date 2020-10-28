package com.ljb.controllers;

import com.ljb.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
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
}
