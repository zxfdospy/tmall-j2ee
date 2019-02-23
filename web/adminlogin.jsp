<%--
  Created by IntelliJ IDEA.
  User: zxfdo
  Date: 2019/2/23
  Time: 12:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@include file="include/admin/adminHeader.jsp" %>

<title>天猫后台管理平台</title>

<script>
    $(function () {
        $("div#errorMessage").css('visibility', 'hidden');
        <c:if test="${!empty msg}">
        $("span.errorMessage").html("${msg}");
        $("div#errorMessage").css('visibility', 'visible');
        </c:if>

        $("form.loginForm").submit(function(){
            if(0==$("#name").val().length||0==$("#password").val().length){
                $("span.errorMessage").html("请输入账号密码");
                $("div#errorMessage").css('visibility', 'visible');
                return false;
            }
            return true;
        });

        $("form.loginForm input").keyup(function(){
            $("div#errorMessage").css('visibility', 'hidden');
        });


    })

</script>
<h2 class="text-center">
    模拟天猫后台管理登录
</h2>

<form method="post" action="admin_check_login" class="loginForm">
    <div id="errorMessage" class="alert alert-danger text-center" style="padding:2px;width: 150px;margin:5px auto;">
        <span class="errorMessage">&nbsp;</span>
    </div>
    <div class="text-center">
        账号:<input id="name" name="name" type="text">
        密码:<input id="password" name="password" type="password">
        <button type="submit">提交</button>
    </div>

</form>


<%@include file="include/admin/adminFooter.jsp" %>

