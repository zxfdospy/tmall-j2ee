<%--
  Created by IntelliJ IDEA.
  User: zxfdo
  Date: 2019/2/19
  Time: 22:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false"%>
<title>模仿天猫官网-${c.name}</title>
<div id="category">
    <div class="categoryPageDiv">
        <img src="img/category/${c.id}.jpg">
        <%@include file="sortBar.jsp"%>
        <%@include file="productsByCategory.jsp"%>
    </div>

</div>
