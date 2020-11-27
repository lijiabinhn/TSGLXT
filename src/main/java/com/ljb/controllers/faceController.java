package com.ljb.controllers;

import com.ljb.pojo.Student;
import com.wln.util.FaceUtil;
import com.wln.util.ImageUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/system/faceController")
public class faceController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("开始人脸检测...");
        File file = ImageUtils.uploadImg(request,"imgData","upimg");
        boolean res = false;
        boolean delFlag = true;
        try {
            String faceToken = FaceUtil.detect(file);
            System.out.println("人脸信息为："+faceToken);
            if(faceToken!=null){
                res = FaceUtil.search(faceToken);
                String type = request.getParameter("type");
                if("register".equals(type)){
                    if(res){
                        res = false;
                    }
                    else{
                        res = FaceUtil.addFace(faceToken);
                        delFlag = false;
                    }
                }
            }
            else{

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if(delFlag){
                file.delete();
            }
            PrintWriter pw = response.getWriter();
            String msg = "{\"success\":"+res+"}";

            Student.setUserName("李佳滨");

            pw.write(msg);
            pw.close();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
