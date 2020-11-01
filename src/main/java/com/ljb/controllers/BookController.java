package com.ljb.controllers;

import com.ljb.page.Page;
import com.ljb.pojo.Book;
import com.ljb.service.BookService;
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
@RequestMapping(path = "/book")
public class BookController {

    @Autowired
    private BookService bookService;



    @RequestMapping(path = "/list",method = RequestMethod.GET)
    public ModelAndView list(ModelAndView model){
        model.setViewName("book/book_view");
        return model;
    }

    @RequestMapping(path = "/get_list",method = RequestMethod.POST)
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

    @RequestMapping(path="/add",method=RequestMethod.POST)
    @ResponseBody
    public Map<String, String> add(Book book){
        Map<String, String> ret = new HashMap<String, String>();
        if(book == null){
            ret.put("type", "error");
            ret.put("msg", "未添加图书!");
            return ret;
        }
        if(StringUtils.isEmpty(book.getBookName())){
            ret.put("type", "error");
            ret.put("msg", "书名不能为空!");
            return ret;
        }
        if(StringUtils.isEmpty(book.getAuthors())){
            ret.put("type", "error");
            ret.put("msg", "作者不能为空!");
            return ret;
        }
        /*User existUser = bookService.findByUserName(user.getUserName());
        if(existUser != null){
            ret.put("type", "error");
            ret.put("msg", "该用户已存在!");
            return ret;
        }*/
        int i = bookService.add(book);
        if(i <= 0){
            ret.put("type", "error");
            ret.put("msg", "添加失败!");
            return ret;
        }
        ret.put("type", "success");
        ret.put("msg", "添加成功!");
        return ret;
    }

    @RequestMapping(path = "/edit",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> edit(Book book)
    {
        Map<String,String> ret=new HashMap<String, String>();
        if(book==null)
        {
            ret.put("type","error");
            ret.put("msg","请选择要修改的行！");
            return ret;
        }
        if(StringUtils.isEmpty(book.getBookName()))
        {
            ret.put("type","error");
            ret.put("msg","请填写书名！");
            return ret;
        }
        if(StringUtils.isEmpty(book.getAuthors()))
        {
            ret.put("type","error");
            ret.put("msg","请填写作者！");
            return ret;
        }
        int i = bookService.edit(book);
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

    @RequestMapping(path = "/delete",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> delete(
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
        int i = bookService.delete(idsString);
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
