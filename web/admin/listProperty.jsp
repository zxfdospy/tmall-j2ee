<%--
  Created by IntelliJ IDEA.
  User: zxfdo
  Date: 2019/1/29
  Time: 14:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@include file="../include/admin/adminHeader.jsp" %>
<%@include file="../include/admin/adminNavigator.jsp" %>
<script>
    function showPropertyEditModal(obj) {
        var index=$(obj).attr("id");
        var id=document.getElementById("propertyDetail").rows[index].cells[0].innerText;
        var name=document.getElementById('propertyDetail').rows[index].cells[1].innerText;
        $("#propertyEditId").val(id);
        $("#propertyEditName").val(name);
        $("#propertyEditModal").modal('show');
    }
</script>

<title>属性管理</title>

<div class="workingArea">
    <ol class="breadcrumb">
        <li><a href="admin_category_list">所有分类</a></li>
        <li><a href="admin_property_list?cid=${c.id}">${c.name}</a></li>
        <li class="active">属性管理</li>
    </ol>


    <div class="listDataTableDiv">
        <table id="propertyDetail" class="table table-striped table-bordered table-hover table-condensed text-center">
            <thead>
            <tr class="success">
                <th>ID</th>
                <th>属性名称</th>
                <th>编辑</th>
                <th>删除</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${ps}" var="p" varStatus="st">
                <tr>
                    <td>${p.id}</td>
                    <td>${p.name}</td>
                    <td>
                        <a id="${st.count}" onclick="showPropertyEditModal(this)" href="javascript:void(0)"><span class="glyphicon glyphicon-edit"></span></a>
                        <%--<a href="admin_property_edit?id=${p.id}"><span class="glyphicon glyphicon-edit"></span></a>--%>
                    </td>
                    <td>
                        <a href="admin_property_delete?id=${p.id}"><span class="glyphicon glyphicon-trash"></span></a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>

        </table>
    </div>
    <%--modal窗口提交--%>
    <div class="modal fade" id="propertyEditModal" tabindex="-1" role="dialog" >
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header bg-warning">
                    <button data-dismiss="modal" class="close" type="button">×<span class="sr-only">Close</span></button>
                    <h4 class="modal-title">属性编辑</h4>
                </div>
                <div class="modal-body">
                    <form method="post" id="propertyEditForm" action="admin_property_update">
                        <table class="editTable">
                            <tr>
                                <td>属性名称</td>
                                <td><input id="propertyEditName" name="propertyEditName" type="text" class="form-control"></td>
                            </tr>
                            <tr class="submitTR">
                                <td colspan="2" align="center">
                                    <input type="hidden" id="propertyEditId" name="propertyEditId">
                                    <button type="submit" class="btn btn-success" onclick="return checkEmpty('propertyEditName','属性名称')">提 交</button>
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
        <%@include file="../include/admin/adminPage.jsp"%>
    </div>

    <div class="panel panel-warning addDiv">
        <div class="panel-heading">新增属性</div>
        <div class="panel-body">
            <form method="post" id="propertyAddForm" action="admin_property_add">
                <table class="addTable">
                    <tr>
                        <td>属性名称</td>
                        <td><input type="text" id="name" name="name" class="form-control"></td>
                    </tr>
                    <tr class="submitTR text-center">
                        <td colspan="2">
                            <input type="hidden" name="cid" value="${c.id}">
                            <button type="submit" class="btn btn-success" onclick="return checkEmpty('name','属性名称')">
                                提交
                            </button>
                        </td>
                    </tr>
                </table>

            </form>


        </div>

    </div>
</div>

<%@include file="../include/admin/adminFooter.jsp"%>






