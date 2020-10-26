package com.ljb.controllers;

import com.ljb.dao.UserMapper;
import com.ljb.page.Page;
import com.ljb.pojo.Clazz;
import com.ljb.pojo.Student;
import com.ljb.service.ClazzService;
import com.ljb.service.StudentService;
import com.ljb.util.StringUtil;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping(path = "/student")
public class StudentController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private ClazzService clazzService;

    @RequestMapping(path="/list",method= RequestMethod.GET)
    public ModelAndView list(ModelAndView model){
        model.setViewName("student/student_view");
       /* List<Clazz> clazzList=clazzService.findAll();
        System.out.println(clazzList);
        model.addObject("clazzList",clazzList );
        model.addObject("clazzListJson", JSONArray.fromObject(clazzList));*/
       /* List<Student> clazzList=studentService.findAll();
        model.addObject("clazzList",clazzList );
        model.addObject("clazzListJson", JSONArray.fromObject(clazzList));*/
        return model;
    }
    @RequestMapping(path="/get_list",method=RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getList(
            @RequestParam(value="username",required=false,defaultValue="") String username,
            @RequestParam(value="clazzId",required=false) Integer clazzId,
            HttpServletRequest request,
            Page page
    ){
        Map<String, Object> ret = new HashMap<String, Object>();
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("username", "%"+username+"%");
        /*Object attribute = request.getSession().getAttribute("userType");
        if("2".equals(attribute.toString())){

            Student loginedStudent = (Student)request.getSession().getAttribute("user");
            queryMap.put("username", "%"+loginedStudent.getStuSn()+"%");
        }
        if(clazzId != 0){
            queryMap.put("clazzId", clazzId);
        }*/
        queryMap.put("offset", page.getOffset());
        queryMap.put("pageSize", page.getRows());
        ret.put("rows", studentService.findList(queryMap));
        ret.put("total", studentService.getTotal(queryMap));
        return ret;
    }

    @RequestMapping(path="/upload_photo",method=RequestMethod.POST)
    @ResponseBody
    public Map<String, String> uploadPhoto(MultipartFile photo,
                                           HttpServletRequest request,
                                           HttpServletResponse response
    ) throws IOException {
        Map<String, String> ret = new HashMap<String, String>();
        if(photo == null){
            //上传图片
            ret.put("type", "error");
            ret.put("msg", "请先选择图片");
            return ret;
        }
        if(photo.getSize() > 10485760){
            //图片大小
            ret.put("type", "error");
            ret.put("msg", "所选图片过大");
            return ret;
        }
        String suffix = photo.getOriginalFilename().substring(photo.getOriginalFilename().lastIndexOf(".") + 1,photo.getOriginalFilename().length());
        if(!"jpg,png,gif,jpeg".contains(suffix.toLowerCase())){
            ret.put("type", "error");
            ret.put("msg", "仅支持jpg,png,gif,jpeg格式");
            return ret;
        }
        String savePath = request.getServletContext().getRealPath("/") + "\\upload\\";
        System.out.println(savePath);
        File savePathFile = new File(savePath);
        if(!savePathFile.exists()){
            savePathFile.mkdir();//创建文件夹
        }
        //上传文件名称
        String filename = new Date().getTime() + "." + suffix;
        photo.transferTo(new File(savePath + filename ));
        ret.put("type", "success");
        ret.put("msg", "上传成功！");
        ret.put("src", request.getServletContext().getContextPath() + "/upload/" + filename);
        return ret;
    }

    @RequestMapping(value="/edit",method=RequestMethod.POST)
    @ResponseBody
    public Map<String, String> edit(Student student){
        Map<String, String> ret = new HashMap<String, String>();
        if(StringUtils.isEmpty(student.getStuSn())){
            ret.put("type", "error");
            ret.put("msg", "请输入用户名！");
            return ret;
        }
        if(StringUtils.isEmpty(student.getStuPw())){
            ret.put("type", "error");
            ret.put("msg", "请输入密码！");
            return ret;
        }
        if(student.getStuCid() == 0){
            ret.put("type", "error");
            ret.put("msg", "请输入班级Id");
            return ret;
        }
        /*if(isExist(student.getStuSn(), student.getStuId())){
            ret.put("type", "error");
            ret.put("msg", "该用户已存在!");
            return ret;
        }*/
       // student.setStuSn(StringUtil.generateSn("S", ""));
        int edit = studentService.edit(student);

        if(edit<0){
            ret.put("type", "error");
            ret.put("msg", "执行出错！");
            return ret;
        }
        ret.put("type", "success");
        ret.put("msg", "修改成功！");
        return ret;
    }


    /**
     * 添加
     * @param student
     * @return
     */
    @RequestMapping(value="/add",method=RequestMethod.POST)
    @ResponseBody
    public Map<String, String> add(Student student){
        Map<String, String> ret = new HashMap<String, String>();
        if(StringUtils.isEmpty(student.getStuSn())){
            ret.put("type", "error");
            ret.put("msg", "用户名不能为空！");
            return ret;
        }
        if(StringUtils.isEmpty(student.getStuPw())){
            ret.put("type", "error");
            ret.put("msg", "密码不能为空！");
            return ret;
        }
        if(student.getStuCid() == 0){
            ret.put("type", "error");
            ret.put("msg", "班级ID不能为空！");
            return ret;
        }
        if(isExist(student.getStuSn(), student.getStuId())){
            ret.put("type", "error");
            ret.put("msg", "该用户已存在！");
            return ret;
        }
       // student.setSn(StringUtil.generateSn("S", ""));
        int i = studentService.add(student);
        if(i <= 0){
            ret.put("type", "error");
            ret.put("msg", "添加学生信息失败！");
            return ret;
        }
        ret.put("type", "success");
        ret.put("msg", "添加成功！");
        return ret;
    }

    /**
     * 删除
     * @param ids
     * @return
     */
    @RequestMapping(value="/delete",method=RequestMethod.POST)
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
            if(studentService.delete(StringUtil.joinString(Arrays.asList(ids), ",")) <= 0){
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

    private boolean isExist(String username,Integer id){
        Student student = studentService.findByUserName(username);
        if(student != null){
            if(id<=0){
                return true;
            }
            if(student.getStuId() != id){
                return true;
            }
        }
        return false;
    }
}
