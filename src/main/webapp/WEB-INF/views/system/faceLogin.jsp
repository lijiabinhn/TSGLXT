<%--
  Created by IntelliJ IDEA.
  User: ASUSF456
  Date: 2020/11/22
  Time: 12:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta charset="utf-8"/>
    <title>用户管理</title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0"/>

    <!-- bootstrap & fontawesome -->
    <link rel="stylesheet" href="../assets/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="../assets/font-awesome/4.5.0/css/font-awesome.min.css"/>

    <!-- page specific plugin styles -->

    <!-- text fonts -->
    <link rel="stylesheet" href="../assets/css/fonts.googleapis.com.css"/>

    <!-- ace styles -->
    <link rel="stylesheet" href="../assets/css/ace.min.css" class="ace-main-stylesheet" id="main-ace-style"/>

    <!--[if lte IE 9]>
    <link rel="stylesheet" href="../assets/css/ace-part2.min.css" class="ace-main-stylesheet"/>
    <![endif]-->
    <link rel="stylesheet" href="../assets/css/ace-skins.min.css"/>
    <link rel="stylesheet" href="../assets/css/ace-rtl.min.css"/>

    <!--[if lte IE 9]>
    <link rel="stylesheet" href="../assets/css/ace-ie.min.css"/>
    <![endif]-->

    <!-- inline styles related to this page -->

    <!-- ace settings handler -->
    <script src="../assets/js/ace-extra.min.js"></script>

    <!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->

    <!--[if lte IE 8]>
    <script src="../assets/js/html5shiv.min.js"></script>
    <script src="../assets/js/respond.min.js"></script>
    <![endif]-->

    <!--[if !IE]> -->
    <script src="../assets/js/jquery-2.1.4.min.js"></script>

    <!-- <![endif]-->

    <!--[if IE]>
    <script src="../assets/js/jquery-1.11.3.min.js"></script>
    <![endif]-->
    <script src="../assets/js/bootstrap.min.js"></script>

    <!-- page specific plugin scripts -->
    <script src="../assets/js/jquery.dataTables.min.js"></script>
    <script src="../assets/js/jquery.dataTables.bootstrap.min.js"></script>
    <script src="../assets/js/dataTables.buttons.min.js"></script>
    <script src="../assets/js/buttons.flash.min.js"></script>
    <script src="../assets/js/buttons.html5.min.js"></script>
    <script src="../assets/js/buttons.print.min.js"></script>
    <script src="../assets/js/buttons.colVis.min.js"></script>
    <script src="../assets/js/dataTables.select.min.js"></script>

    <!-- ace scripts -->
    <script src="../assets/js/ace-elements.min.js"></script>
    <script src="../assets/js/ace.min.js"></script>

</head>
<body class="no-skin">
<div class="main-container ace-save-state" id="main-container">

    <div class="main-content">
         <div class="main-content-inner">

            <div class="page-content">

                <div style="padding: 10px;">
                    <table>
                        <tr>
                            <td colspan="2">
                                <button class="btn btn-sm btn-default" onclick="openMedia()">开启摄像头</button>
                                <button class="btn btn-sm btn-default" onclick="closeMedia()">关闭摄像头</button>
                                <button class="btn btn-sm btn-default" onclick="takePhoto('login')">登录</button>
                                <button class="btn btn-sm btn-default" onclick="takePhoto('register')">注册</button>
                                <div class="radio-box">
                                    <input type="radio" id="radio-2" checked="checked" name="type" value="2" />
                                    <label for="radio-1">学生</label>
                                </div>
                                <div class="radio-box">
                                    <input type="radio" id="radio-1" name="type" value="1" />
                                    <label for="radio-2">管理员</label>
                                </div>
                            </td>
                        </tr>
                    </table>
                    <table>
                        <tr>
                            <td>
                                <video id="video" width="500px" height="500px" autoplay="autoplay"></video>
                                <canvas id="canvas" width="500px" height="500px" style="display: none"></canvas>
                            </td>
                            <td>
                                <img id="imgTag" src="" alt="..." width="500px" height="500px"><br>
                            </td>
                        </tr>
                    </table>

                    <script>
                        let mediaStreamTrack=null; // 视频对象(全局)
                        let video ;
                        function openMedia() {
                            let constraints = {
                                video: { width: 500, height: 500 },
                                audio: false
                            };
                            //获得video摄像头
                            video = document.getElementById('video');
                            let promise = navigator.mediaDevices.getUserMedia(constraints);
                            promise.then((mediaStream) => {
                                // mediaStreamTrack = typeof mediaStream.stop === 'function' ? mediaStream : mediaStream.getTracks()[1];
                                mediaStreamTrack=mediaStream.getVideoTracks()
                                video.srcObject = mediaStream;
                                video.play();
                            });
                        }

                        // 拍照
                        function takePhoto(str) {
                            //获得Canvas对象
                            let video = document.getElementById('video');
                            let canvas = document.getElementById('canvas');
                            let ctx = canvas.getContext('2d');
                            ctx.drawImage(video, 0, 0, 500, 500);


                            // toDataURL  ---  可传入'image/png'---默认, 'image/jpeg'
                            let img = document.getElementById('canvas').toDataURL();
                            // 这里的img就是得到的图片
                            console.log('img-----', img);
                            document.getElementById('imgTag').src=img;
                            // alert(img);
                            // alert(str);
                            //上传
                            var flagStr="登录";
                            if(str=="register"){
                                flagStr="注册";
                            }
                            $.ajax({
                                type: "POST",
                                url: "faceController",
                                data: {"imgData":img,"type":str},
                                dataType: "json",
                                success:function(data){
                                    var flag = data.success;
                                    if (flag) {
                                        alert(flagStr+"成功");
                                        if(document.getElementById("radio-2").checked==true){

                                            window.parent.location.href = "userIndex";
                                        }
                                        else {
                                            window.parent.location.href = "index";
                                        }
                                    } else {
                                        alert(flagStr+"失败!");
                                    }
                                }
                                ,error:function(){
                                    alert("服务器内部错误！"+flagStr+"失败")
                                }
                            });
                        }

                        // 关闭摄像头
                        function closeMedia() {
                            let stream = document.getElementById('video').srcObject;
                            let tracks = stream.getTracks();

                            tracks.forEach(function(track) {
                                track.stop();
                            });

                            document.getElementById('video').srcObject = null;
                        }
                    </script>
                </div>

               <%-- <div class="mt-20 skin-minimal" style="text-align: center;">
                    <div class="radio-box">
                        <input type="radio" id="radio-2" checked="checked" name="type" value="2" />
                        <label for="radio-1">学生</label>
                    </div>
                    <div class="radio-box">
                        <input type="radio" id="radio-1" name="type" value="1" />
                        <label for="radio-2">管理员</label>
                    </div>
                </div>--%>

            </div><!-- /.page-content -->
        </div>
    </div><!-- /.main-content -->

</div><!-- /.main-container -->
</body>
</html>
