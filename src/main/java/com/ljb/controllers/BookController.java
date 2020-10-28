package com.ljb.controllers;

import com.ljb.page.Page;
import com.ljb.service.BookService;
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
@RequestMapping(path = "/book")
public class BookController {
    @Autowired
    private BookService bookService;


    @RequestMapping(path="/list",method= RequestMethod.GET)
    public ModelAndView list(ModelAndView model){
        model.setViewName("book/book_view");
        return model;
    }
    @RequestMapping(path="/get_list",method=RequestMethod.POST)
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
        ret.put("rows", bookService.findList(queryMap));
        ret.put("total", bookService.getTotal(queryMap));
        return ret;
    }
}
