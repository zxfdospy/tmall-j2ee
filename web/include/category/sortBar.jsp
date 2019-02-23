<%--
  Created by IntelliJ IDEA.
  User: zxfdo
  Date: 2019/2/19
  Time: 22:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script>
    $(function () {
        $("input.sortBarPrice").keyup(function () {
            var min = $("input.beginPrice").val();
            var max = $("input.endPrice").val();
            if (min.length == 0 && max.length == 0) {
                $("div.productUnit").show();
                return;
            }
            if (min <= 0 || min.length == 0)
                min = 0;
            $("input.beginPrice").val(min);
            if (max <= 0 || max.length == 0)
                max = Number.MAX_SAFE_INTEGER;
            if (min.length != 0 && max.length != 0) {
                $("div.productUnit").hide();
                $("div.productUnit").each(function () {
                    var price = $(this).attr("price");
                    price = new Number(price);
                    if (price < max * 100 && price >= min * 100)
                        $(this).show();
                });
            }
        });

        if (${param.sort=='priceUp'}) {
            $("a#priceSort").attr("href", "?cid=${c.id}&sort=priceDown");
            $("a#priceSort span").removeClass("glyphicon-arrow-down");
            $("a#priceSort span").addClass("glyphicon-arrow-up");
        }
        if (${param.sort=='priceDown'}) {
            $("a#priceSort").attr("href", "?cid=${c.id}&sort=priceUp");
            $("a#priceSort span").removeClass("glyphicon-arrow-up");
            $("a#priceSort span").addClass("glyphicon-arrow-down");
        }
    });
</script>
<div class="categorySortBar">

    <table class="categorySortBarTable categorySortTable">
        <tr>
            <td
                    <c:if test="${'all'==param.sort||empty param.sort}">class="grayColumn"</c:if> ><a
                    href="?cid=${c.id}&sort=all">综合<span class="glyphicon glyphicon-arrow-down"></span></a></td>
            <td
                    <c:if test="${'review'==param.sort}">class="grayColumn"</c:if> ><a href="?cid=${c.id}&sort=review">人气<span
                    class="glyphicon glyphicon-arrow-down"></span></a></td>
            <td <c:if test="${'date'==param.sort}">class="grayColumn"</c:if>><a href="?cid=${c.id}&sort=date">新品<span
                    class="glyphicon glyphicon-arrow-down"></span></a></td>
            <td <c:if test="${'saleCount'==param.sort}">class="grayColumn"</c:if>><a href="?cid=${c.id}&sort=saleCount">销量<span
                    class="glyphicon glyphicon-arrow-down"></span></a></td>
            <td <c:if test="${'priceUp'==param.sort||'priceDown'==param.sort}">class="grayColumn"</c:if>><a
                    id="priceSort" href="?cid=${c.id}&sort=priceUp">价格<span
                    class="glyphicon glyphicon-arrow-down"></span></a></td>
        </tr>
    </table>

    <table class="categorySortBarTable">
        <tr>
            <td><input class="sortBarPrice beginPrice" type="number" placeholder="请输入"></td>
            <td class="grayColumn priceMiddleColumn">-</td>
            <td><input class="sortBarPrice endPrice" type="number" placeholder="请输入"></td>
        </tr>
    </table>

</div>