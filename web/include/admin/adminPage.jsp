<%--
  Created by IntelliJ IDEA.
  User: zxfdo
  Date: 2019/1/28
  Time: 22:44
  To change this template use File | Settings | File Templates.
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<script>
    $(function () {
        $("ul.pagination li.disabled a").click(function () {
            return false;
        })
    })
    
</script>

<nav>
    <ul class="pagination">
        <li <c:if test="${page.start==0}">class="disabled" </c:if>>
            <a href="?page.start=0${page.param}">&laquo;
            </a>
        </li>
        <li <c:if test="${page.start==0}">class="disabled" </c:if>>
            <a href="?page.start=${page.start-page.count}${page.param}">
                &lsaquo;
            </a>
        </li>
        <%--varStatus="status" 表示遍历的状态,count基1,index基0--%>
        <c:forEach begin="0" end="${page.totalPage-1}" varStatus="status">
            <c:if test="${status.count*5-page.start<=20 && status.count*5-page.start>=-10}">
            <li>
                <a href="?page.start=${status.index*page.count}"
                   <c:if test="${page.start==status.index*page.count}">class="current" </c:if>>${status.count}</a>
            </li>
            </c:if>
        </c:forEach>


        <li <c:if test="${page.start==page.last}">class="disabled" </c:if>>
            <a href="?page.start=${page.start+page.count}${page.param}">&rsaquo;
            </a>
        </li>
        <li <c:if test="${page.start==page.last}">class="disabled" </c:if>>
            <a href="?page.start=${page.last}${page.param}">&raquo;</a>
        </li>

    </ul>

</nav>
