<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>捐献图书列表</title>
    <link rel="stylesheet" type="text/css" href="../easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="../easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="../easyui/css/demo.css">
    <script type="text/javascript" src="../easyui/jquery.min.js"></script>
    <script type="text/javascript" src="../easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="../easyui/js/validateExtends.js"></script>
    <script type="text/javascript">
        $(function() {
            var table;

            //datagrid初始化
            $('#dataList').datagrid({
                title:'捐献列表',
                iconCls:'icon-more',//图标
                border: true,
                collapsible:false,//是否可折叠的
                fit: true,//自动大小
                method: "post",
                url:"get_ucList?t="+new Date().getTime(),
                idField:'cNo',
                singleSelect:false,//是否单选
                pagination:true,//分页控件
                rownumbers:true,//行号
                sortName:'cNo',
                sortOrder:'DESC',
                remoteSort: false,
                columns: [[
                    {field:'chk',checkbox: true,width:50},
                    {field:'cNo',title:'ID',width:80, sortable: true},
                    {field:'cName',title:'姓名',width:200, sortable: true},
                    {field:'cSex',title:'性别',width:150, sortable: true},
                    {field:'cTle',title:'电话号码',width:150, sortable: true},
                    {field:'cBook',title:'捐献书名',width:150, sortable: true},
                    {field:'cTime',title:'捐献时间',width:150, sortable: true},
                ]],
                toolbar: "#toolbar"
            });
            //设置分页控件
            var p = $('#dataList').datagrid('getPager');
            $(p).pagination({
                pageSize: 10,//每页显示的记录条数，默认为10
                pageList: [10,20,30,50,100],//可以设置每页记录条数的列表
                beforePageText: '第',//页数文本框前显示的汉字
                afterPageText: '页    共 {pages} 页',
                displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',
            });
            //设置工具类按钮
            $("#add").click(function(){
                table = $("#addTable");
                $("#addDialog").dialog("open");
            });

            //设置添加窗口
            $("#addDialog").dialog({
                title: "添加捐献书籍",
                width: 450,
                height: 300,
                iconCls: "icon-add",
                modal: true,
                collapsible: false,
                minimizable: false,
                maximizable: false,
                draggable: true,
                closed: true,
                buttons: [
                    {
                        text:'捐献',
                        plain: true,
                        iconCls:'icon-user_add',
                        handler:function(){
                            var validate = $("#addForm").form("validate");
                            if(!validate){
                                $.messager.alert("消息提醒","请检查你输入的数据!","warning");
                                return;
                            } else{
                                var data = $("#addForm").serialize();
                                $.ajax({
                                    type: "post",
                                    url: "addUserContribute",
                                    data: data,
                                    dataType:'json',
                                    success: function(data){
                                        if(data.type == "success"){
                                            $.messager.alert("消息提醒","添加成功!","info");
                                            //关闭窗口
                                            $("#addDialog").dialog("close");
                                            //清空原表格数据
                                            $("#add_cBook").textbox('setValue', "");
                                            //重新刷新页面数据
                                            $('#dataList').datagrid("reload");

                                        } else{
                                            $.messager.alert("消息提醒",data.msg,"warning");
                                            return;
                                        }
                                    }
                                });
                            }
                        }
                    },
                ],
                onClose: function(){
                    $("#add_cBook").textbox('setValue', "");
                }
            });


            //搜索按钮
            $("#search-btn").click(function(){
                $('#dataList').datagrid('reload',{
                    username:$("#search-name").textbox('getValue')
                });
            });
        });
    </script>
</head>
<body>
<!-- 数据列表 -->
<table id="dataList" cellspacing="0" cellpadding="0">

</table>
<!-- 工具栏 -->
<div id="toolbar">
    <div style="float: left;"><a id="add" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">去捐献</a></div>
    <div style="float: left;" class="datagrid-btn-separator"></div>
        书名：<input id="search-name" class="easyui-textbox" />
        <a id="search-btn" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true">搜索</a>
    </div>
</div>

<!-- 添加窗口 -->
<div id="addDialog" style="padding: 10px;">
    <form id="addForm" method="post">
        <table id="addTable" cellpadding="8">
            <tr >
                <td>姓名:</td>
                <td>
                    <input id="add_cName"  class="easyui-textbox" style="width: 200px; height: 30px;" type="text" name="cName" data-options="required:true, missingMessage:'请填写姓名'"  />
                </td>
            </tr>
            <tr >
                <td>性别:</td>
                <td>
                    <input id="add_cSex"  class="easyui-textbox" style="width: 200px; height: 30px;" type="text" name="cSex" data-options="required:true, missingMessage:'请填写性别'"  />
                </td>
            </tr>
            <tr >
                <td>电话号码:</td>
                <td>
                    <input id="add_cTle"  class="easyui-textbox" style="width: 200px; height: 30px;" type="text" name="cTle" data-options="required:true, missingMessage:'请填写电话号码'"  />
                </td>
            </tr>
            <tr >
                <td>书名:</td>
                <td>
                    <input id="add_cBook"  class="easyui-textbox" style="width: 200px; height: 30px;" type="text" name="cBook" data-options="required:true, missingMessage:'请填写书名'"  />
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>