package com.ljb.controllers;

import com.ljb.pojo.grade;
import com.ljb.service.GradeService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(path = "/clazz")
public class ClazzController {

    @Autowired
    private GradeService gradeService;

    @RequestMapping(path = "/list")
    public ModelAndView list(ModelAndView model){
        model.setViewName("clazz/clazz_view");
       /* List<grade> findAll = gradeService.findAll();
        model.addObject("gradeList",findAll );
        model.addObject("gradeListJson", JSONArray.fromObject(findAll));*/
        return model;
    }
}
