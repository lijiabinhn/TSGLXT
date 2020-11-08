<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>学生信息管理系统|用户界面</title>
    <link rel="shortcut icon" href="favicon.ico"/>
    <link rel="bookmark" href="favicon.ico"/>
    <link rel="stylesheet" type="text/css" href="../easyui/css/default.css" />
    <link rel="stylesheet" type="text/css" href="../easyui/themes/default/easyui.css" />
    <link rel="stylesheet" type="text/css" href="../easyui/themes/icon.css" />
    <script type="text/javascript" src="../easyui/jquery.min.js"></script>
    <script type="text/javascript" src="../easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src='../easyui/js/outlook2.js'> </script>
    <script type="text/javascript">
        var _menus = {"menus":[
                {"menuid":"1","icon":"","menuname":"查询图书",
                    "menus":[
                        {"menuid":"11","menuname":"图书列表","icon":"icon-user-teacher","url":"../us/ubList"}
                    ]
                },
                {"menuid":"2","icon":"","menuname":"图书管理",
                    "menus":[
                        //{"menuid":"21","menuname":"学生列表","icon":"icon-user-student","url":"../student/list"},
                    ]
                },
                {"menuid":"3","icon":"","menuname":"查看罚款",
                    "menus":[
                        //{"menuid":"31","menuname":"图书列表","icon":"icon-world","url":"../book/list"},
                    ]
                },
                {"menuid":"4","icon":"","menuname":"捐献图书",
                    "menus":[
                        // {"menuid":"41","menuname":"借阅列表","icon":"icon-house","url":"../operation/borrowList"},
                        // {"menuid":"42","menuname":"归还列表","icon":"icon-house","url":"../operation/returnList"},
                        // {"menuid":"43","menuname":"挂失列表","icon":"icon-house","url":"../operation/loseList"},
                        // {"menuid":"44","menuname":"续借列表","icon":"icon-house","url":"../operation/continueList"},
                        // {"menuid":"45","menuname":"预约列表","icon":"icon-house","url":"../operation/orderList"},
                    ]
                },
                {"menuid":"5","icon":"","menuname":"个人中心",
                    "menus":[
                        //{"menuid":"51","menuname":"交纳罚款列表","icon":"icon-house","url":"../ticket/list"},
                    ]
                },
            ]};


    </script>

</head>
<body class="easyui-layout" style="overflow-y: hidden"  scroll="no">
<noscript>
    <div style=" position:absolute; z-index:100000; height:2046px;top:0px;left:0px; width:100%; background:white; text-align:center;">
        <img src="images/noscript.gif" alt='抱歉，请开启脚本支持！' />
    </div>
</noscript>
<div region="north" split="true" border="false" style="overflow: hidden; height: 30px;
        background: #7f99be;
        line-height: 20px;color: #fff; font-family: Verdana, 微软雅黑,黑体">
    <span style="float:right; padding-right:20px;" class="head"><span style="color:red; font-weight:bold;">${student.getStuSn()}&nbsp;</span>您好&nbsp;&nbsp;&nbsp;<a href="login_out" id="loginOut">安全退出</a></span>
    <span style="padding-left:10px; font-size: 16px; ">学生信息管理系统</span>
</div>
<div region="south" split="true" style="height: 30px; background: #D2E0F2; ">
    <div class="footer">Copyright &copy; version 3.0</div>
</div>
<div region="west" hide="true" split="true" title="导航菜单" style="width:180px;" id="west">
    <div id="nav" class="easyui-accordion" fit="true" border="false">
        <!--  导航内容 -->
    </div>

</div>
<div id="mainPanle" region="center" style="background: #eee; overflow-y:hidden">
    <div id="tabs" class="easyui-tabs"  fit="true" border="false" >
        <jsp:include page="welcome.jsp" />
    </div>
</div>

</body>
</html>