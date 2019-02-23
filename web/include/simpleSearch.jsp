<%--
  Created by IntelliJ IDEA.
  User: zxfdo
  Date: 2019/2/18
  Time: 15:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>

<div>


    <form action="foresearch" method="post">
        <div class="simpleSearchDiv-out">
            <a href="${contextPath}">
                <img id="simpleLogo" class="simpleLogo pull-left" src="img/site/simpleLogo.png">
            </a>
            <div class="simpleSearchDiv pull-right">
                <input type="text" placeholder="平衡车 原汁机" name="keyword">
                <button class="searchButton" type="submit">搜天猫</button>
                <div class="searchBelow">
                    <c:forEach items="${cs}" var="c" varStatus="st">
                        <c:if test="${st.count>=8 and st.count<=11}">
                    <span>
                        <a href="forecategory?cid=${c.id}">
                                ${c.name}
                        </a>
                        <c:if test="${st.count!=11}">
                            <span>|</span>
                        </c:if>
                    </span>
                        </c:if>
                    </c:forEach>
                </div>
            </div>
        </div>
    </form>
    <div style="clear:both"></div>
</div>

