<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>罚款列表</title>
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
                title:'罚款列表',
                iconCls:'icon-more',//图标
                border: true,
                collapsible:false,//是否可折叠的
                fit: true,//自动大小
                method: "post",
                url:"get_list?t="+new Date().getTime(),
                idField:'ticNo',
                singleSelect:false,//是否单选
                pagination:true,//分页控件
                rownumbers:true,//行号
                sortName:'ticNo',
                sortOrder:'DESC',
                remoteSort: false,
                columns: [[
                    {field:'chk',checkbox: true,width:50},
                    {field:'ticNo',title:'ID',width:100, sortable: true},
                    {field:'stuNo',title:'学号',width:150, sortable: true},
                    {field:'stuName',title:'姓名',width:100, sortable: true},
                    {field:'stuClazz',title:'所属班级',width:150, sortable: true},
                    {field:'bookName',title:'书名',width:150, sortable: true},
                    {field:'ticTime',title:'违期时长',width:100, sortable: true},
                    {field:'ticMoney',title:'应付金额',width:100, sortable: true},
                    {field:'isMoney',title:'是否付款',width:100, sortable: true},
                    {field:'payWay',title:'付款方式',width:100},
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
                        ids[i] = row.ticNo;
                    });
                    $.messager.confirm("消息提醒", "将删除与用户相关的所有数据，确认继续？", function(r){
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
                title: "添加罚款信息",
                width: 450,
                height: 450,
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
                                            $("#add_stuName").textbox('setValue', "");
                                            $("#add_stuNo").textbox('setValue', "");
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
                    $("#add_stuName").textbox('setValue', "");
                    $("#add_stuNo").textbox('setValue', "");
                    // $("#add_password").textbox('setValue', "");
                }
            });

            //编辑用户信息
            $("#editDialog").dialog({
                title: "修改罚款信息",
                width: 450,
                height: 450,
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
                    $("#edit-id").val(selectRow.ticNo);
                    $("#edit_stuNo").textbox('setValue', selectRow.stuNo);
                    $("#edit_stuName").textbox('setValue', selectRow.stuName);
                    $("#edit_stuClazz").textbox('setValue', selectRow.stuClazz);
                    $("#edit_bookName").textbox('setValue', selectRow.bookName);
                    $("#edit_ticTime").textbox('setValue', selectRow.ticTime);
                    $("#edit_ticMoney").textbox('setValue', selectRow.ticMoney);
                    $("#edit_isMoney").textbox('setValue', selectRow.isMoney);
                    $("#edit_payWay").textbox('setValue', selectRow.payWay);
                }
            });

            //搜索按钮
            $("#search-btn").click(function(){
                $('#dataList').datagrid('reload',{
                    username:$("#search-username").textbox('getValue')
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
    <div style="float: left;"><a id="add" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">添加</a></div>
    <div style="float: left;" class="datagrid-btn-separator"></div>
    <div style="float: left;"><a id="edit" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">修改</a></div>
    <div style="float: left;" class="datagrid-btn-separator"></div>
    <div>
        <a id="delete" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-some-delete',plain:true">删除</a>
        用户名：<input id="search-username" class="easyui-textbox" />
        <a id="search-btn" href="javascript:;" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true">搜索</a>
    </div>
</div>

<!-- 添加窗口 -->
<div id="addDialog" style="padding: 10px;">
    <form id="addForm" method="post">
        <table id="addTable" cellpadding="8">
            <tr >
                <td>学号:</td>
                <td>
                    <input id="add_stuNo"  class="easyui-textbox" style="width: 200px; height: 30px;" type="int" name="stuNo" data-options="required:true, missingMessage:'请填写学号'"  />
                </td>
            </tr>
            <tr >
                <td>姓名:</td>
                <td>
                    <input id="add_stuName"  class="easyui-textbox" style="width: 200px; height: 30px;" type="text" name="stuName" data-options="required:true, missingMessage:'请填写用户名'"  />
                </td>
            </tr>
            <tr >
                <td>所属班级:</td>
                <td>
                    <input id="add_stuClazz"  class="easyui-textbox" style="width: 200px; height: 30px;" type="text" name="stuClazz" data-options="required:true, missingMessage:'请填写所属班级'"  />
                </td>
            </tr>
            <tr >
                <td>书名:</td>
                <td>
                    <input id="add_bookName"  class="easyui-textbox" style="width: 200px; height: 30px;" type="text" name="bookName" data-options="required:true, missingMessage:'请填写书名'"  />
                </td>
            </tr>
            <tr >
                <td>违期时长:</td>
                <td>
                    <input id="add_ticTime"  class="easyui-textbox" style="width: 200px; height: 30px;" type="int" name="ticTime" data-options="required:true, missingMessage:'请填写违期时间'"  />
                </td>
            </tr>
            <tr >
                <td>应付金额:</td>
                <td>
                    <input id="add_ticMoney"  class="easyui-textbox" style="width: 200px; height: 30px;" type="int" name="ticMoney" data-options="required:true, missingMessage:'请填写应付金额'"  />
                </td>
            </tr>
            <tr >
                <td>是否付款:</td>
                <td>
                    <input id="add_isMoney"  class="easyui-textbox" style="width: 200px; height: 30px;" type="text" name="isMoney" data-options="required:true, missingMessage:'请填写是否付款'"  />
                </td>
            </tr>
            <tr >
                <td>付款方式:</td>
                <td>
                    <input id="add_payWay"  class="easyui-textbox" style="width: 200px; height: 30px;" type="text" name="payWay" data-options="required:true, missingMessage:'请填写付款方式'"  />
                </td>
            </tr>
        </table>
    </form>
</div>


<!-- 修改窗口 -->
<div id="editDialog" style="padding: 10px">
    <form id="editForm" method="post">
        <input type="hidden" name="ticNo" id="edit-id">
        <table id="editTable" border=0 cellpadding="8" >
            <tr >
                <td>学号:</td>
                <td>
                    <input id="edit_stuNo"  class="easyui-textbox" style="width: 200px; height: 30px;" type="int" name="stuNo" data-options="required:true, missingMessage:'请填写学号'"  />
                </td>
            </tr>
            <tr >
                <td>用户名:</td>
                <td>
                    <input id="edit_stuName"  class="easyui-textbox" style="width: 200px; height: 30px;" type="text" name="stuName" data-options="required:true, missingMessage:'请填写用户名'"  />
                </td>
            </tr>
            <tr >
                <td>所属班级:</td>
                <td>
                    <input id="edit_stuClazz"  class="easyui-textbox" style="width: 200px; height: 30px;" type="text" name="stuClazz" data-options="required:true, missingMessage:'请填写所属班级'"  />
                </td>
            </tr>
            <tr >
                <td>书名:</td>
                <td>
                    <input id="edit_bookName"  class="easyui-textbox" style="width: 200px; height: 30px;" type="text" name="bookName" data-options="required:true, missingMessage:'请填写书名'"  />
                </td>
            </tr>
            <tr >
                <td>违期时长:</td>
                <td>
                    <input id="edit_ticTime"  class="easyui-textbox" style="width: 200px; height: 30px;" type="int" name="ticTime" data-options="required:true, missingMessage:'请填写违期时间'"  />
                </td>
            </tr>
            <tr >
                <td>应付金额:</td>
                <td>
                    <input id="edit_ticMoney"  class="easyui-textbox" style="width: 200px; height: 30px;" type="float" name="ticMoney" data-options="required:true, missingMessage:'请填写应付金额'"  />
                </td>
            </tr>
            <tr >
                <td>是否付款:</td>
                <td>
                    <input id="edit_isMoney"  class="easyui-textbox" style="width: 200px; height: 30px;" type="text" name="isMoney" data-options="required:true, missingMessage:'请填写是否付款'"  />
                </td>
            </tr>
            <tr >
                <td>付款方式:</td>
                <td>
                    <input id="edit_payWay"  class="easyui-textbox" style="width: 200px; height: 30px;" type="text" name="payWay" data-options="required:true, missingMessage:'请填写付款方式'"  />
                </td>
            </tr>
        </table>
    </form>
</div>


</body>
</html>