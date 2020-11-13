<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>挂失图书列表</title>
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
                title:'我的挂失列表',
                iconCls:'icon-more',//图标
                border: true,
                collapsible:false,//是否可折叠的
                fit: true,//自动大小
                method: "post",
                url:"get_loseList?t="+new Date().getTime(),
                idField:'lbNo',
                singleSelect:false,//是否单选
                pagination:true,//分页控件
                rownumbers:true,//行号
                sortName:'lbNo',
                sortOrder:'DESC',
                remoteSort: false,
                columns: [[
                    {field:'chk',checkbox: true,width:50},
                    {field:'lbNo',title:'ID',width:80, sortable: true},
                    {field:'stuNo',title:'学号',width:150, sortable: true},
                    {field:'stuName',title:'姓名',width:150, sortable: true},
                    {field:'bookNo',title:'书号',width:150, sortable: true},
                    {field:'bookName',title:'书名',width:150, sortable: true},
                    {field:'borrowTime',title:'借阅时间',width:150, sortable: true},
                    {field:'loseTime',title:'挂失时间',width:150},
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
            //修改
            /*$("#edit").click(function(){
                table = $("#editTable");
                var selectRows = $("#dataList").datagrid("getSelections");
                if(selectRows.length != 1){
                    $.messager.alert("消息提醒", "请选择一条数据进行操作!", "warning");
                } else{
                    $("#editDialog").dialog("open");
                }
            });*/


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
    <%--    <c:if test="${userType == 1}">--%>
    <div style="float: left;"><a id="add" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">缴纳罚款</a></div>
    <div style="float: left;" class="datagrid-btn-separator"></div>
    <%--<div style="float: left;"><a id="edit" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">修改</a></div>
    <div style="float: left;" class="datagrid-btn-separator"></div>--%>
    <%--    </c:if>--%>
    <div>
        <%--        <c:if test="${userType == 1}">--%>
<%--        <a id="delete" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-some-delete',plain:true">删除</a>--%>
        <%--        </c:if>--%>
        姓名：<input id="search-name" class="easyui-textbox" />
        <a id="search-btn" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true">搜索</a>
    </div>
</div>


</body>
</html>