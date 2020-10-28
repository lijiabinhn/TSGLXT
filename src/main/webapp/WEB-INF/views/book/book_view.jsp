<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>图书列表</title>
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
                title:'图书列表',
                iconCls:'icon-more',//图标
                border: true,
                collapsible:false,//是否可折叠的
                fit: true,//自动大小
                method: "post",
                url:"get_list?t="+new Date().getTime(),
                idField:'bookNo',
                singleSelect:false,//是否单选
                pagination:true,//分页控件
                rownumbers:true,//行号
                sortName:'bookNo',
                sortOrder:'DESC',
                remoteSort: false,
                columns: [[
                    {field:'chk',checkbox: true,width:50},
                    {field:'bookNo',title:'ID',width:50, sortable: true},
                    {field:'bookName',title:'书名',width:150, sortable: true},
                    {field:'Author',title:'作者',width:150, sortable: true},
                    {field:'Publish',title:'出版社',width:150, sortable: true},
                    {field:'buyTime',title:'购买时间',width:150, sortable: true},
                    {field:'isBorrow',title:'是否借阅',width:150, sortable: true},
                    {field:'isOrder',title:'是否预约',width:300},
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
            $("#edit").click(function(){
                table = $("#editTable");
                var selectRows = $("#dataList").datagrid("getSelections");
                if(selectRows.length != 1){
                    $.messager.alert("消息提醒", "请选择一条数据进行操作!", "warning");
                } else{
                    $("#editDialog").dialog("open");
                }
            });
            //删除
            $("#delete").click(function(){
                var selectRows = $("#dataList").datagrid("getSelections");
                var selectLength = selectRows.length;
                if(selectLength == 0){
                    $.messager.alert("消息提醒", "请选择数据进行删除!", "warning");
                } else{
                    var ids = [];
                    $(selectRows).each(function(i, row){
                        ids[i] = row.id;
                    });
                    $.messager.confirm("消息提醒", "如果年级下存在班级信息则无法删除，须先删除年级下属的班级信息？", function(r){
                        if(r){
                            $.ajax({
                                type: "post",
                                url: "delete",
                                data: {ids: ids},
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
                }
            });

            //设置添加窗口
            $("#addDialog").dialog({
                title: "添加年级",
                width: 450,
                height: 350,
                iconCls: "icon-add",
                modal: true,
                collapsible: false,
                minimizable: false,
                maximizable: false,
                draggable: true,
                closed: true,
                buttons: [
                    {
                        text:'添加',
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
                                    url: "add",
                                    data: data,
                                    dataType:'json',
                                    success: function(data){
                                        if(data.type == "success"){
                                            $.messager.alert("消息提醒","添加成功!","info");
                                            //关闭窗口
                                            $("#addDialog").dialog("close");
                                            //清空原表格数据
                                            $("#add_name").textbox('setValue', "");
                                            $("#add_remark").textbox('setValue', "");
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
                    $("#add_name").textbox('setValue', "");
                    $("#add_remark").textbox('setValue', "");
                }
            });

            //编辑年级信息
            $("#editDialog").dialog({
                title: "修改年级信息",
                width: 450,
                height: 350,
                iconCls: "icon-edit",
                modal: true,
                collapsible: false,
                minimizable: false,
                maximizable: false,
                draggable: true,
                closed: true,
                buttons: [
                    {
                        text:'提交',
                        plain: true,
                        iconCls:'icon-edit',
                        handler:function(){
                            var validate = $("#editForm").form("validate");
                            if(!validate){
                                $.messager.alert("消息提醒","请检查你输入的数据!","warning");
                                return;
                            } else{

                                var data = $("#editForm").serialize();

                                $.ajax({
                                    type: "post",
                                    url: "edit",
                                    data: data,
                                    dataType:'json',
                                    success: function(data){
                                        if(data.type == "success"){
                                            $.messager.alert("消息提醒","修改成功!","info");
                                            //关闭窗口
                                            $("#editDialog").dialog("close");

                                            //重新刷新页面数据
                                            $('#dataList').datagrid("reload");
                                            $('#dataList').datagrid("uncheckAll");

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
                onBeforeOpen: function(){
                    var selectRow = $("#dataList").datagrid("getSelected");
                    //设置值
                    $("#edit-id").val(selectRow.bookNo);
                    $("#edit_name").textbox('setValue', selectRow.bookName);
                    $("#edit_name").textbox('setValue', selectRow.Author);
                    $("#edit_name").textbox('setValue', selectRow.Publish);
                    $("#edit_name").textbox('setValue', selectRow.buyTime);
                    $("#edit_name").textbox('setValue', selectRow.isBorrow);
                    $("#edit_remark").textbox('setValue', selectRow.isBorrow);
                }
            });

            //搜索按钮
            $("#search-btn").click(function(){
                $('#dataList').datagrid('reload',{
                    name:$("#search-name").textbox('getValue')
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
    <c:if test="${userType == 1}">
        <div style="float: left;"><a id="add" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">添加</a></div>
        <div style="float: left;" class="datagrid-btn-separator"></div>
        <div style="float: left;"><a id="edit" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">修改</a></div>
        <div style="float: left;" class="datagrid-btn-separator"></div>
    </c:if>
    <div>
        <c:if test="${userType == 1}">
            <a id="delete" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-some-delete',plain:true">删除</a>
        </c:if>
        书名：<input id="search-name" class="easyui-textbox" />
        <a id="search-btn" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true">搜索</a>
    </div>
</div>

<!-- 添加窗口 -->
<div id="addDialog" style="padding: 10px;">
    <form id="addForm" method="post">
        <table id="addTable" cellpadding="8">
            <tr >
                <td>书名:</td>
                <td>
                    <input id="add_name"  class="easyui-textbox" style="width: 200px; height: 30px;" type="text" name="bookName" data-options="required:true, missingMessage:'请填写书名'"  />
                </td>
            </tr>
            <tr >
                <td>作者:</td>
                <td>
                    <input id="add_author"  class="easyui-textbox" style="width: 200px; height: 30px;" type="text" name="Author" data-options="required:true, missingMessage:'请填写作者'"  />
                </td>
            </tr>
            <tr >
                <td>出版社:</td>
                <td>
                    <input id="add_publish"  class="easyui-textbox" style="width: 200px; height: 30px;" type="text" name="Publish" data-options="required:true, missingMessage:'请填写出版社'"  />
                </td>
            </tr>
            <tr >
                <td>购买时间:</td>
                <td>
                    <input id="add_buyTime"  class="easyui-textbox" style="width: 200px; height: 30px;" type="text" name="buyTime" data-options="required:true, missingMessage:'请填写购买时间'"  />
                </td>
            </tr>
            <tr >
                <td>是否借阅:</td>
                <td>
                    <input id="add_isBorrow"  class="easyui-textbox" style="width: 200px; height: 30px;" type="text" name="isBorrow" data-options="required:true, missingMessage:'请填写是否借阅'"  />
                </td>
            </tr>
            <tr >
                <td>是否预约:</td>
                <td>
                    <input id="add_Order"  class="easyui-textbox" style="width: 200px; height: 30px;" type="text" name="isOrder" data-options="required:true, missingMessage:'请填写是否预约'"  />
                </td>
            </tr>
        </table>
    </form>
</div>


<!-- 修改窗口 -->
<div id="editDialog" style="padding: 10px">
    <form id="editForm" method="post">
        <input type="hidden" name="id" id="edit-id">
        <table id="editTable" border=0 cellpadding="8" >
            <tr >
                <td>书名:</td>
                <td>
                    <input id="edit_name"  class="easyui-textbox" style="width: 200px; height: 30px;" type="text" name="bookName" data-options="required:true, missingMessage:'请填写书名'"  />
                </td>
            </tr>
            <tr >
                <td>作者:</td>
                <td>
                    <input id="edit_author"  class="easyui-textbox" style="width: 200px; height: 30px;" type="text" name="Author" data-options="required:true, missingMessage:'请填写作者'"  />
                </td>
            </tr>
            <tr >
                <td>出版社:</td>
                <td>
                    <input id="edit_publish"  class="easyui-textbox" style="width: 200px; height: 30px;" type="text" name="Publish" data-options="required:true, missingMessage:'请填写出版社'"  />
                </td>
            </tr>
            <tr >
                <td>购买时间:</td>
                <td>
                    <input id="edit_buyTime"  class="easyui-textbox" style="width: 200px; height: 30px;" type="text" name="buyTime" data-options="required:true, missingMessage:'请填写购买时间'"  />
                </td>
            </tr>
            <tr >
                <td>是否借阅:</td>
                <td>
                    <input id="edit_isBorrow"  class="easyui-textbox" style="width: 200px; height: 30px;" type="text" name="isBorrow" data-options="required:true, missingMessage:'请填写是否借阅'"  />
                </td>
            </tr>
            <tr >
                <td>是否预约:</td>
                <td>
                    <input id="edit_Order"  class="easyui-textbox" style="width: 200px; height: 30px;" type="text" name="isOrder" data-options="required:true, missingMessage:'请填写是否预约'"  />
                </td>
            </tr>
        </table>
    </form>
</div>


</body>
</html>