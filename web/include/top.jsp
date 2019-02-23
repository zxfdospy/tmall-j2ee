<%--
  Created by IntelliJ IDEA.
  User: zxfdo
  Date: 2019/2/17
  Time: 14:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>

<script>
    $(function () {
        //	content css3
        var htmlHref = window.location.href;
        htmlHref = htmlHref.replace(/^http:\/\/[^/]+/, "");
        var addr = htmlHref.substr(htmlHref.lastIndexOf('/', htmlHref.lastIndexOf('/') - 1) + 1);
        var index = addr.lastIndexOf("\/");
        //js 获取字符串中最后一个斜杠后面的内容
        var addrLast = decodeURI(addr.substring(index + 1, addr.length));
        //js 获取字符串中最后一个斜杠前面的内容
        var str = decodeURI(addr.substring(0, index));
        $("a#forelogout").attr("href","forelogout?now="+addrLast);

    });
</script>
<nav class="top-out">
    <nav class="top ">
        <a href="${contextPath}">
            <span style="color:#C40000;margin:0px" class=" glyphicon glyphicon-home redColor"></span>
            天猫首页
        </a>

        <span>喵，欢迎来天猫</span>
        <c:if test="${!empty user}">
            <a href="login.jsp">${user.name}</a>
            <a id="forelogout" href="forelogout">退出</a>
        </c:if>

        <c:if test="${empty user}">
            <a href="login.jsp">请登录</a>
            <a href="register.jsp">免费注册</a>
        </c:if>


        <span class="pull-right">
            <a href="forebought">我的订单</a>
            <a id="cartTotalItemNumber" href="forecart">
            <span style="color:#C40000;margin:0px" class=" glyphicon glyphicon-shopping-cart redColor"></span>
                购物车<strong>${cartTotalItemNumber}</strong>件</a>
        </span>

    </nav>
</nav>