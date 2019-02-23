<%--
  Created by IntelliJ IDEA.
  User: zxfdo
  Date: 2019/1/28
  Time: 0:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false"%>

<div class="navitagorDiv">
    <nav class="navbar navbar-default navbar-fixed-top navbar-inverse">
        <img src="img/site/tmallbuy.png" class="pull-left" style="margin-left:10px;margin-right:0px" height="45px">

        <a class="navbar-brand" href="admin_category_list">天猫后台</a>
        <a class="navbar-brand" href="admin_category_list">分类管理</a>
        <a class="navbar-brand" href="admin_user_list">用户管理</a>
        <a class="navbar-brand" href="admin_order_list">订单管理</a>

        <div style="display: inline-block" class="pull-right">
        <a class="navbar-brand" href="adminlogin.jsp">${admin.name}</a>
        <a class="navbar-brand" href="admin_check_loginout">退出</a>
        </div>
        <div style="clear: both"></div>
    </nav>
</div>

