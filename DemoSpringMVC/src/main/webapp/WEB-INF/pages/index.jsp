<%-- 
    Document   : index
    Created on : Mar 27, 2024, 1:11:45 PM
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h1 class="text-center text-info mt-1">DANH MỤC SẢN PHẨM</h1>
<a href="<c:url value="/products" />" class="btn btn-success mb-1">Thêm sản phẩm</a>
<table class="table table-striped">
    <tr>
        <th></th>
        <th>Id</th>
        <th>Tên sản phẩm</th>
        <th>Gía sản phẩm</th>
        <th></th>
    </tr>
    <c:forEach items="${products}" var="p">
        <tr>
            <td><img class="rounded img-fluid" src="${p.image}" width="200" alt="${p.name}"></td>
            <td>${p.name}</td>
            <td>${p.name}</td>
            <td>${p.price} VNĐ</td>

            <td>
                <button class="btn btn-danger">Xóa</button>
                <c:url value="/products/${p.id}" var="url" />
                <a href="${url}" class="btn btn-info">Cập nhật</a>
            </td>
        </tr>
    </c:forEach>
</table>
