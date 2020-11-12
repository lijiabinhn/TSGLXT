<%--
  Created by IntelliJ IDEA.
  User: ASUSF456
  Date: 2020/11/9
  Time: 21:39
  To change this template use <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>个人中心</title>
    <link rel="stylesheet" type="text/css" href="../easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="../easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="../easyui/css/demo.css">
    <script type="text/javascript" src="../easyui/jquery.min.js"></script>
    <script type="text/javascript" src="../easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="../easyui/js/validateExtends.js"></script>
    <script type="text/javascript">

            //设置工具类按钮
            $("#add").click(function(){
                $.ajax({
                    type: "post",
                    url: "delete",
                    dataType:'json',
                    success: function(data){
                        if(data.type == "success"){
                            $.messager.alert("消息提醒","删除成功!","info");
                            //刷新表格
                            $("#dataList").datagrid("reload");
                            $("#dataList").datagrid("uncheckAll");
                        } else{
                            $.messager.alert("消息提醒",data.msg,"warning");
                            return;
                        }
                    }
                });
            });

            //修改
            $("#edit").click(function(){
                $.ajax({
                    type: "post",
                    url: "delete",
                    dataType:'json',
                    success: function(data){
                        if(data.type == "success"){
                            $.messager.alert("消息提醒","删除成功!","info");
                            //刷新表格
                            $("#dataList").datagrid("reload");
                            $("#dataList").datagrid("uncheckAll");
                        } else{
                            $.messager.alert("消息提醒",data.msg,"warning");
                            return;
                        }
                    }
                });
            });
            //删除
            $("#delete").click(function(){
                    $.messager.confirm("消息提醒", "确定删除学生信息？", function(r){
                        if(r){
                            $.ajax({
                                type: "post",
                                url: "delete",
                                dataType:'json',
                                success: function(data){
                                    if(data.type == "success"){
                                        $.messager.alert("消息提醒","删除成功!","info");
                                        //刷新表格
                                        $("#dataList").datagrid("reload");
                                        $("#dataList").datagrid("uncheckAll");
                                    } else{
                                        $.messager.alert("消息提醒",data.msg,"warning");
                                        return;
                                    }
                                }
                            });
                        }
                    });
            });


            //搜索按钮
            $("#search-btn").click(function(){
                $('#dataList').datagrid('reload',{
                    username:$("#search-name").textbox('getValue'),
                    // clazzId:$("#search-clazz-id").combobox('getValue')
                });
            });
            //上传图片按钮
            $("#upload-btn").click(function(){
                if($("#add-upload-photo").filebox("getValue") == ''){
                    $.messager.alert("消息提醒","请选择图片文件!","warning");
                    return;
                }
                $("#photoForm").submit();
            });
            $("#edit-upload-btn").click(function(){
                if($("#edit-upload-photo").filebox("getValue") == ''){
                    $.messager.alert("消息提醒","请选择图片文件!","warning");
                    return;
                }
                $("#editPhotoForm").submit();
            });
        function uploaded(e){
            var data = $(window.frames["photo_target"].document).find("body pre").text();
            if(data == '')return;
            data = JSON.parse(data);
            if(data.type == "success"){
                $.messager.alert("消息提醒","图片上传成功!","info");
                $("#photo-preview").attr("src",data.src);
                $("#edit-photo-preview").attr("src",data.src);
                $("#add_photo").val(data.src);
                $("#edit_photo").val(data.src);
            }else{
                $.messager.alert("消息提醒",data.msg,"warning");
            }
        }
    </script>
</head>
<body>
<!-- 数据列表 -->
<table id="dataList" cellspacing="0" cellpadding="0">

</table>
<!-- 工具栏 -->
<div id="toolbar">
    <div style="float: left;"><a id="add" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">我的预约</a></div>
    <div style="float: left;" class="datagrid-btn-separator"></div>
    <div style="float: left;"><a id="record" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">我的归还</a></div>
    <div style="float: left;" class="datagrid-btn-separator"></div>
    <div style="float: left;"><a id="edit" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">我的续借</a></div>
    <div style="float: left;" class="datagrid-btn-separator"></div>
    <div>
        <a id="delete" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-some-delete',plain:true">我的挂失</a>
        学生名：<input id="search-name" class="easyui-textbox" />

        <a id="search-btn" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true">搜索</a>
    </div>
</div>

<!-- 提交表单处理iframe框架 -->
<iframe id="photo_target" name="photo_target" onload="uploaded(this)"></iframe>
</body>
</html>
