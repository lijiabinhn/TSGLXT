package com.ljb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path = "/book")
public class BookController {

    @RequestMapping(path="/list",method= RequestMethod.GET)
    public ModelAndView list(ModelAndView model){
        model.setViewName("book/book_view");
        return model;
    }
}
