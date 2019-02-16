<%--
  Created by IntelliJ IDEA.
  User: zxfdo
  Date: 2019/1/29
  Time: 21:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@include file="../include/admin/adminHeader.jsp" %>
<%@include file="../include/admin/adminNavigator.jsp" %>
<script>
    $(function () {
        $(".addTable input[type='number']").change(function () {
            var number=$(this).val();
            if(number<0)
                number=0;
            $(this).val(number);
        })

    });

    function showProductEditModal(obj) {
        var index=$(obj).attr("id");
        var id=document.getElementById("productDetail").rows[index].cells[0].innerText;
        var name=document.getElementById('productDetail').rows[index].cells[2].innerText;
        var subTitle=document.getElementById('productDetail').rows[index].cells[3].innerText;
        var originalPrice=document.getElementById('productDetail').rows[index].cells[4].innerText;
        var promotePrice=document.getElementById('productDetail').rows[index].cells[5].innerText;
        var stock=document.getElementById('productDetail').rows[index].cells[6].innerText;
        //获取page.start的参数
        var pagestatus=getUrlParam('page.start');
        $("#productEditId").val(id);
        $("#productEditName").val(name);
        $("#productEditSubTitle").val(subTitle);
        $("#productEditOriginalPrice").val(originalPrice);
        $("#productEditPromotePrice").val(promotePrice);
        $("#productEditStock").val(stock);
        $("#productEditPageStatus").val(pagestatus);
        $("#productEditModal").modal('show');
    }

    function getUrlParam(name)
    {
        var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
        var r = window.location.search.substr(1).match(reg);  //匹配目标参数
        if (r!=null) return unescape(r[2]); return null; //返回参数值
    }


    function checkAddProduct() {
        if (!checkEmpty("name", "产品名称"))
            return false;
//      if (!checkEmpty("subTitle", "小标题"))
//          return false;
        if (!checkZero("originalPrice", "原价格"))
            return false;
        if (!checkZero("promotePrice", "优惠价格"))
            return false;
        return true;
    }

    function checkEditProduct() {
        if (!checkEmpty("productEditName", "产品名称"))
            return false;
//      if (!checkEmpty("subTitle", "小标题"))
//          return false;
        if (!checkZero("productEditOriginalPrice", "原价格"))
            return false;
        if (!checkZero("productEditPromotePrice", "优惠价格"))
            return false;
        return true;
    }
</script>

<title>产品管理</title>

<div class="workingArea">
    <ol class="breadcrumb">
        <li><a href="admin_category_list">所有分类</a></li>
        <li><a href="admin_product_list?cid=${c.id}">${c.name}</a></li>
        <li class="active">产品管理</li>
    </ol>

    <div class="listDataTableDiv">
        <table id="productDetail" class="table table-striped table-bordered table-hover table-condensed text-center">
            <thead>
            <tr class="success">
                <th>ID</th>
                <th>图片</th>
                <th>产品名称</th>
                <th>产品小标题</th>
                <th width="53px">原价格</th>
                <th width="80px">优惠价格</th>
                <th width="80px">库存数量</th>
                <th width="80px">图片管理</th>
                <th width="80px">设置属性</th>
                <th width="42px">编辑</th>
                <th width="42px">删除</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${ps}" var="p" varStatus="status">
                <tr>
                    <td>${p.id}</td>
                    <td>
                        <c:if test="${!empty p.firstProductImage}">
                            <img width="40px" src="img/productSingle/${p.firstProductImage.id}.jpg">
                        </c:if>
                    </td>
                    <td>${p.name}</td>
                    <td>${p.subTitle}</td>
                    <td>${p.originalPrice/100}</td>
                    <td>${p.promotePrice/100}</td>
                    <td>${p.stock}</td>
                    <td><a href="admin_productImage_list?pid=${p.id}"><span class="glyphicon glyphicon-picture"></span></a>
                    </td>
                    <td><a href="admin_product_editPropertyValue?id=${p.id}"><span
                            class="glyphicon glyphicon-th-list"></span></a></td>
                    <td><a id="${status.count}" onclick="showProductEditModal(this)" href="javascript:void(0)"><span class="glyphicon glyphicon-edit"></span></a></td>
                    <%--<td><a href="admin_product_edit?id=${p.id}"><span class="glyphicon glyphicon-edit"></span></a></td>--%>
                    <td><a deleteLink="true" href="admin_product_delete?id=${p.id}&page.start=${page.start}"><span
                            class="glyphicon glyphicon-trash"></span></a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>

        </table>
    </div>

    <%--modal窗口提交--%>
    <div class="modal fade" id="productEditModal" tabindex="-1" role="dialog" >
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header bg-warning">
                    <button data-dismiss="modal" class="close" type="button">×<span class="sr-only">Close</span></button>
                    <h4 class="modal-title">产品编辑</h4>
                </div>
                <div class="modal-body">
                    <form method="post" id="productEditForm" action="admin_product_update">
                        <table id="productEditTable" class="editTable">
                            <tr>
                                <td>产品名称</td>
                                <td><input id="productEditName" name="productEditName" type="text" class="form-control"></td>
                            </tr>
                            <tr>
                                <td>产品小标题</td>
                                <td><input id="productEditSubTitle" name="productEditSubTitle" type="text" class="form-control"></td>
                            </tr>
                            <tr>
                                <td>原价格</td>
                                <td><input id="productEditOriginalPrice" value="0.00" name="productEditOriginalPrice" type="number" step="0.01" class="form-control"></td>
                            </tr>
                            <tr>
                                <td>优惠价格</td>
                                <td><input id="productEditPromotePrice" value="0.00" name="productEditPromotePrice" type="number" step="0.01" class="form-control"></td>
                            </tr>
                            <tr>
                                <td>库存</td>
                                <td><input id="productEditStock" value="1" name="productEditStock" type="number" class="form-control"></td>
                            </tr>
                            <tr class="submitTR">
                                <td colspan="2" align="center">
                                    <input type="hidden" name="productEditId" id="productEditId" value="">
                                    <input type="hidden" name="productEditPageStatus" id="productEditPageStatus" value="">
                                    <button type="submit" class="btn btn-success" onclick="return checkEditProduct()">提 交</button>
                                </td>
                            </tr>
                        </table>
                    </form>
                </div>
                <%--<div class="modal-footer">--%>
                <%--<button data-dismiss="modal" class="btn btn-default" type="button">关闭</button>--%>
                <%--<button class="btn btn-primary" id="submit" type="button">提交</button>--%>
                <%--</div>--%>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div>

    <div class="pageDiv">
        <%@include file="../include/admin/adminPage.jsp" %>
    </div>

    <div class="panel panel-warning addDiv">
        <div class="panel-heading">新增产品</div>
        <div class="panel-body">
            <form method="post" id="addForm" action="admin_product_add">
                <table class="addTable">
                    <tr>
                        <td>产品名称</td>
                        <td><input id="name" name="name" type="text" class="form-control"></td>
                    </tr>
                    <tr>
                        <td>产品小标题</td>
                        <td><input id="subTitle" name="subTitle" type="text" class="form-control"></td>
                    </tr>
                    <tr>
                        <td>原价格</td>
                        <td><input id="originalPrice" value="0.00" name="originalPrice" type="number" step="0.01" class="form-control"></td>
                    </tr>
                    <tr>
                        <td>优惠价格</td>
                        <td><input id="promotePrice" value="0.00" name="promotePrice" type="number" step="0.01" class="form-control"></td>
                    </tr>
                    <tr>
                        <td>库存</td>
                        <td><input id="stock" value="1" name="stock" type="number" class="form-control"></td>
                    </tr>
                    <tr class="submitTR">
                        <td colspan="2" align="center">
                            <input type="hidden" name="cid" value="${c.id}">
                            <button type="submit" class="btn btn-success" onclick="return checkAddProduct()">提 交</button>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</div>
</div>

<%@include file="../include/admin/adminFooter.jsp" %>






