<%--
  Created by IntelliJ IDEA.
  User: zxfdo
  Date: 2019/1/31
  Time: 22:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@include file="../include/admin/adminHeader.jsp" %>
<%@include file="../include/admin/adminNavigator.jsp" %>

<script>
    $(function () {
        $(".addFormSingle").submit(function () {
            if (checkEmpty("filepathSingle", "单个图片")) {
                return true;
            }
            return false;
        });
        $(".addFormDetail").submit(function () {
            if (checkEmpty("filepathDetail", "详情图片"))
                return true;
            return false;
        });
    });

    function ajaxProductImage(obj) {
        var newlocation=$(obj).attr("newlocation");
        var productImage=$(obj).attr("id");
        var imagetype=$(obj).attr("imagetype");
        var edittype=$(obj).attr("edittype");
        var page="admin_productImage_update";
        $.post(
            page,
            {"piid":productImage,"newlocation":newlocation,"imagetype":imagetype,"edittype":edittype},
            function (result) {
                if(result=="success"){
                    location.reload();
                }
            }
        )
    }

</script>

<title>产品图片管理</title>

<div class="workingArea">
    <ol class="breadcrumb">
        <li><a href="admin_category_list">所有分类</a></li>
        <li><a href="admin_product_list?cid=${p.category.id}">${p.category.name}</a></li>
        <li class="active">${p.name}</li>
        <li class="active">产品图片管理</li>
    </ol>

    <table class="addPictureTable" align="center">
        <tr>
            <td class="addPictureTableTD">
                <div>
                    <div class="panel panel-warning addPictureDiv">
                        <div class="panel-heading">新增产品<b class="text-primary"> 单个 </b>图片</div>
                        <div class="panel-body">
                            <form method="post" class="addFormSingle" action="admin_productImage_add"
                                  enctype="multipart/form-data">
                                <table class="addTable">
                                    <tr>
                                        <td>请选择本地图片 尺寸400X400 为佳</td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <input id="filepathSingle" type="file" name="filepath"/>
                                        </td>
                                    </tr>
                                    <tr class="submitTR">
                                        <td align="center">
                                            <input type="hidden" name="type" value="type_single"/>
                                            <input type="hidden" name="pid" value="${p.id}"/>
                                            <input type="hidden" name="location" value="${fn:length(pisSingle)}">
                                            <button type="submit" class="btn btn-success" <c:if test="${fn:length(pisSingle)==5}"> disabled="disabled" </c:if>>提 交</button>
                                        </td>
                                    </tr>
                                </table>
                            </form>
                        </div>
                    </div>
                    <table class="table table-striped table-bordered table-hover  table-condensed">
                        <thead>
                        <tr class="success">
                            <th>排序</th>
                            <th>ID</th>
                            <th>产品单个图片缩略图</th>
                            <th>下移</th>
                            <th>上移</th>
                            <th>删除</th>
                        </tr>
                        </thead>
                        <tbody id="productSingleImage">
                        <c:forEach items="${pisSingle}" var="pi" varStatus="count">
                            <tr>
                                <td>${count.count}</td>
                                <td>${pi.id}</td>
                                <td><a title="点击查看原图" href="img/productSingle/${pi.id}.jpg"><img height="50px" src="img/productSingle/${pi.id}.jpg"></a></td>
                                <td><a id="${pi.id}" newlocation="${count.count}" imagetype="type_single" edittype="down" href="javascript:void(0)" onclick="ajaxProductImage(this)"><span class="glyphicon glyphicon-arrow-down"></span></a></td>
                                <td><a id="${pi.id}" newlocation="${count.index-1}" imagetype="type_single" edittype="up" href="javascript:void(0)" onclick="ajaxProductImage(this)"><span class="glyphicon glyphicon-arrow-up"></span></a></td>
                                <td><a deleteLink="true" href="admin_productImage_delete?id=${pi.id}"><span class="glyphicon glyphicon-trash"></span></a></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>

                </div>
            </td>
            <td class="addPictureTableTD">
                <div>

                    <div class="panel panel-warning addPictureDiv">
                        <div class="panel-heading">新增产品<b class="text-primary"> 详情 </b>图片</div>
                        <div class="panel-body">
                            <form method="post" class="addFormDetail" action="admin_productImage_add"
                                  enctype="multipart/form-data">
                                <table class="addTable">
                                    <tr>
                                        <td>请选择本地图片 宽度790 为佳</td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <input id="filepathDetail" type="file" name="filepath"/>
                                        </td>
                                    </tr>
                                    <tr class="submitTR">
                                        <td align="center">
                                            <input type="hidden" name="type" value="type_detail"/>
                                            <input type="hidden" name="pid" value="${p.id}"/>
                                            <input type="hidden" name="location" value="${fn:length(pisDetail)}">
                                            <button type="submit" class="btn btn-success">提 交</button>
                                        </td>
                                    </tr>
                                </table>
                            </form>
                        </div>
                    </div>
                    <table class="table table-striped table-bordered table-hover  table-condensed">
                        <thead>
                        <tr class="success">
                            <th>排序</th>
                            <th>ID</th>
                            <th>产品详情图片缩略图</th>
                            <th>下移</th>
                            <th>上移</th>
                            <th>删除</th>
                        </tr>
                        </thead>
                        <tbody id="productDetailImage">
                        <c:forEach items="${pisDetail}" var="pi" varStatus="count">
                            <tr>
                                <td>${count.count}</td>
                                <td>${pi.id}</td>
                                <td><a title="点击查看原图" href="img/productDetail/${pi.id}.jpg"><img height="50px" src="img/productDetail/${pi.id}.jpg"></a></td>
                                <td><a id="${pi.id}" newlocation="${count.count}" imagetype="type_detail" edittype="down" href="javascript:void(0)" onclick="ajaxProductImage(this)"><span class="glyphicon glyphicon-arrow-down"></span></a></td>
                                <td><a id="${pi.id}" newlocation="${count.index-1}" imagetype="type_detail" edittype="up" href="javascript:void(0)" onclick="ajaxProductImage(this)"><span class="glyphicon glyphicon-arrow-up"></span></a></td>
                                <td><a deleteLink="true" href="admin_productImage_delete?id=${pi.id}"><span class="glyphicon glyphicon-trash"></span></a></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </td>
        </tr>
    </table>

</div>

<%@include file="../include/admin/adminFooter.jsp" %>