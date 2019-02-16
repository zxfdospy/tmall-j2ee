<%--
  Created by IntelliJ IDEA.
  User: zxfdo
  Date: 2019/1/28
  Time: 17:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<script>
    $(function () {
        function showTime() {
            var date = new Date();
            var time = date.toLocaleString();
            $("#timeDiv").text(time);
        }

        setInterval(showTime, 1000);
    })
</script>

<div class="footer text-center">
    <div class="label label-warning">zxfdospy&how2j版权所有</div>
    <div class="label label-primary" id="timeDiv">
        <jsp:useBean id="time" class="java.util.Date"/>
        ${time}
    </div>

</div>

</body>
</html>
