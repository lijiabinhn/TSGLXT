<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>借阅图书列表</title>
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
                title:'借阅图书列表',
                iconCls:'icon-more',//图标
                border: true,
                collapsible:false,//是否可折叠的
                fit: true,//自动大小
                method: "post",
                url:"get_uoList?t="+new Date().getTime(),
                idField:'orNo',
                singleSelect:false,//是否单选
                pagination:true,//分页控件
                rownumbers:true,//行号
                sortName:'orNo',
                sortOrder:'DESC',
                remoteSort: false,
                columns: [[
                    {field:'chk',checkbox: true,width:50},
                    {field:'orNo',title:'ID',width:80, sortable: true},
                    {field:'stuName',title:'姓名',width:150, sortable: true},
                    {field:'stuNo',title:'学号',width:150, sortable: true},
                    {field:'stuClazz',title:'所在班级',width:150, sortable: true},
                    {field:'bookNo',title:'书号',width:150, sortable: true},
                    {field:'bookName',title:'书名',width:150, sortable: true},
                    {field:'borrowTime',title:'借阅时间',width:150, sortable: true},
                    {field:'returnTime',title:'应还时间',width:150},
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
            //续借
            $("#add").click(function(){
                table = $("#addTable");
                var selectRows = $("#dataList").datagrid("getSelections");
                if(selectRows.length != 1){
                    $.messager.alert("消息提醒", "请选择一条数据进行操作!", "warning");
                } else{
                    $("#addDialog").dialog("open");
                }
            });
            //归还
            $("#edit").click(function(){
                table = $("#editTable");
                var selectRows = $("#dataList").datagrid("getSelections");
                if(selectRows.length != 1){
                    $.messager.alert("消息提醒", "请选择一条数据进行操作!", "warning");
                } else{
                    $("#editDialog").dialog("open");
                }
            });
            //挂失
            $("#lose").click(function(){
                table = $("#loseTable");
                var selectRows = $("#dataList").datagrid("getSelections");
                if(selectRows.length != 1){
                    $.messager.alert("消息提醒", "请选择一条数据进行操作!", "warning");
                } else{
                    $("#loseDialog").dialog("open");
                }
            });

            //设置添加窗口
            $("#addDialog").dialog({
                title: "添加续借信息",
                width: 400,
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
                        text:'续借',
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
                                    url: "addUserContinue",
                                    data: data,
                                    dataType:'json',
                                    success: function(data){
                                        if(data.type == "success"){
                                            $.messager.alert("消息提醒","续借成功!","info");
                                            //关闭窗口
                                            $("#addDialog").dialog("close");
                                            //清空原表格数据
                                            $("#add_stuName").textbox('setValue', "");
                                            $("#add_stuNo").textbox('setValue', "");
                                            $("#add_delayTime").textbox('setValue', "");
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
                onBeforeOpen: function(){
                    var selectRow = $("#dataList").datagrid("getSelected");
                    //设置值
                    $("#add_bookNo").textbox('setValue', selectRow.bookNo);
                    $("#add_bookName").textbox('setValue', selectRow.bookName);
                    $("#add_borrowTime").textbox('setValue', selectRow.borrowTime);
                    $("#add_returnTime").textbox('setValue', selectRow.returnTime);
                }
            });

            //编辑年级信息
            $("#editDialog").dialog({
                title: "添加归还信息",
                width: 400,
                height: 300,
                iconCls: "icon-edit",
                modal: true,
                collapsible: false,
                minimizable: false,
                maximizable: false,
                draggable: true,
                closed: true,
                buttons: [
                    {
                        text:'归还',
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
                                    url: "addUserReturn",
                                    data: data,
                                    dataType:'json',
                                    success: function(data){
                                        if(data.type == "success"){
                                            $.messager.alert("消息提醒","归还成功!","info");
                                            //关闭窗口
                                            $("#editDialog").dialog("close");
                                            $("#edit_stuName").textbox('setValue', "");
                                            $("#edit_stuNo").textbox('setValue', "");
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
                    $("#edit_bookNo").textbox('setValue', selectRow.bookNo);
                    $("#edit_bookName").textbox('setValue', selectRow.bookName);
                    $("#edit_borrowTime").textbox('setValue', selectRow.borrowTime);
                    $("#edit_returnTime").textbox('setValue', selectRow.returnTime);
                }
            });
            //挂失列表
            $("#loseDialog").dialog({
                title: "添加挂失信息",
                width: 400,
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
                        text:'挂失',
                        plain: true,
                        iconCls:'icon-user_add',
                        handler:function(){
                            var validate = $("#loseForm").form("validate");
                            if(!validate){
                                $.messager.alert("消息提醒","请检查你输入的数据!","warning");
                                return;
                            } else{
                                var data = $("#loseForm").serialize();
                                $.ajax({
                                    type: "post",
                                    url: "addUserLose",
                                    data: data,
                                    dataType:'json',
                                    success: function(data){
                                        if(data.type == "success"){
                                            $.messager.alert("消息提醒","挂失成功!","info");
                                            //关闭窗口
                                            $("#loseDialog").dialog("close");
                                            //清空原表格数据
                                            $("#lose_stuName").textbox('setValue', "");
                                            $("#lose_stuNo").textbox('setValue', "");
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
                onBeforeOpen: function(){
                    var selectRow = $("#dataList").datagrid("getSelected");
                    //设置值
                    $("#lose_bookNo").textbox('setValue', selectRow.bookNo);
                    $("#lose_bookName").textbox('setValue', selectRow.bookName);
                    $("#lose_borrowTime").textbox('setValue', selectRow.borrowTime);
                    $("#lose_returnTime").textbox('setValue', selectRow.returnTime);
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
    <%--    <c:if test="${userType == 1}">--%>
    <div style="float: left;"><a id="add" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">续借</a></div>
    <div style="float: left;" class="datagrid-btn-separator"></div>
    <div style="float: left;"><a id="edit" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">归还</a></div>
    <div style="float: left;" class="datagrid-btn-separator"></div>
    <%--    </c:if>--%>
    <div>
        <%--        <c:if test="${userType == 1}">--%>
        <a id="lose" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-some-delete',plain:true">挂失</a>
        <%--        </c:if>--%>
        书名：<input id="search-name" class="easyui-textbox" />
        <a id="search-btn" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true">搜索</a>
    </div>
</div>

<!-- 续借窗口 -->
<div id="addDialog" style="padding: 10px;">
    <form id="addForm" method="post">
        <table id="addTable" cellpadding="8">
            <tr >
                <td>姓名:</td>
                <td>
                    <input id="add_stuName"  class="easyui-textbox" style="width: 200px; height: 30px;" type="text" name="stuName" data-options="required:true, missingMessage:'请填写姓名'"  />
                </td>
            </tr>
            <tr >
                <td>学号:</td>
                <td>
                    <input id="add_stuNo"  class="easyui-textbox" style="width: 200px; height: 30px;" type="int" name="stuNo" data-options="required:true, missingMessage:'请填写学号'"  />
                </td>
            </tr>
            <tr >
                <td>续借时长:</td>
                <td>
                    <input id="add_delayTime"  class="easyui-textbox" style="width: 200px; height: 30px;" type="int" name="delayTime" data-options="required:true, missingMessage:'请填写续借时长'"  />
                </td>
            </tr>
            <tr >
                <td>书号:</td>
                <td>
                    <input id="add_bookNo"  class="easyui-textbox" style="width: 200px; height: 30px;" type="int" name="bookNo" readonly data-options="required:true"  />
                </td>
            </tr>
            <tr >
                <td>书名:</td>
                <td>
                    <input id="add_bookName"  class="easyui-textbox" style="width: 200px; height: 30px;" type="text" name="bookName" readonly data-options="required:true"  />
                </td>
            </tr>
            <tr >
                <td>借阅时间:</td>
                <td>
                    <input id="add_borrowTime"  class="easyui-textbox" style="width: 200px; height: 30px;" type="text" name="borrowTime" readonly data-options="required:true"  />
                </td>
            </tr>
            <tr >
                <td>应还时间:</td>
                <td>
                    <input id="add_returnTime"  class="easyui-textbox" style="width: 200px; height: 30px;" type="text" name="returnTime" readonly data-options="required:true"  />
                </td>
            </tr>
        </table>
    </form>
</div>


<!-- 归还窗口 -->
<div id="editDialog" style="padding: 10px">
    <form id="editForm" method="post">
        <table id="editTable" border=0 cellpadding="8" >
            <tr >
                <td>姓名:</td>
                <td>
                    <input id="edit_stuName"  class="easyui-textbox" style="width: 200px; height: 30px;" type="text" name="stuName" data-options="required:true, missingMessage:'请填写姓名'"  />
                </td>
            </tr>
            <tr >
                <td>学号:</td>
                <td>
                    <input id="edit_stuNo"  class="easyui-textbox" style="width: 200px; height: 30px;" type="int" name="stuNo" data-options="required:true, missingMessage:'请填写学号'"  />
                </td>
            </tr>
            <tr >
                <td>书号:</td>
                <td>
                    <input id="edit_bookNo"  class="easyui-textbox" style="width: 200px; height: 30px;" type="int" name="bookNo" readonly data-options="required:true"  />
                </td>
            </tr>
            <tr >
                <td>借阅时间:</td>
                <td>
                    <input id="edit_borrowTime"  class="easyui-textbox" style="width: 200px; height: 30px;" type="text" name="borrowTime" readonly data-options="required:true"  />
                </td>
            </tr>
            <tr >
                <td>应还时间:</td>
                <td>
                    <input id="edit_returnTime"  class="easyui-textbox" style="width: 200px; height: 30px;" type="text" name="returnTime" readonly data-options="required:true"  />
                </td>
            </tr>
        </table>
    </form>
</div>
<div id="loseDialog" style="padding: 10px;">
    <form id="loseForm" method="post">
        <table id="loseTable" cellpadding="8">
            <tr >
                <td>姓名:</td>
                <td>
                    <input id="lose_stuName"  class="easyui-textbox" style="width: 200px; height: 30px;" type="text" name="stuName" data-options="required:true, missingMessage:'请填写姓名'"  />
                </td>
            </tr>
            <tr >
                <td>学号:</td>
                <td>
                    <input id="lose_stuNo"  class="easyui-textbox" style="width: 200px; height: 30px;" type="int" name="stuNo" data-options="required:true, missingMessage:'请填写学号'"  />
                </td>
            </tr>
            <tr >
                <td>书号:</td>
                <td>
                    <input id="lose_bookNo"  class="easyui-textbox" style="width: 200px; height: 30px;" type="int" name="bookNo" readonly data-options="required:true"  />
                </td>
            </tr>
            <tr >
                <td>书名:</td>
                <td>
                    <input id="lose_bookName"  class="easyui-textbox" style="width: 200px; height: 30px;" type="text" name="bookName" readonly data-options="required:true"  />
                </td>
            </tr>
            <tr >
                <td>借阅时间:</td>
                <td>
                    <input id="lose_borrowTime"  class="easyui-textbox" style="width: 200px; height: 30px;" type="text" name="borrowTime" readonly data-options="required:true"  />
                </td>
            </tr>
            <tr >
                <td>应还时间:</td>
                <td>
                    <input id="lose_returnTime"  class="easyui-textbox" style="width: 200px; height: 30px;" type="text" name="returnTime" readonly data-options="required:true"  />
                </td>
            </tr>
        </table>
    </form>
</div>

</body>
</html>