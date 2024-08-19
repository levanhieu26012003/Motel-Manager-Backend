<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- Không cần header và footer vì đã được kế thừa từ base.jsp -->

<h1 class="text-info text-center">Manage Motels</h1>
<div>
    <a class="btn btn-success" href="<c:url value="/user" />">Thêm Người Dùng</a>
</div>
<table class="table table-striped">
    <thead>
        <tr>
            <th></th>
            <th>Id</th>
            <th>Username</th>
            <th>User Role</th>
            <th>Email</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="user" items="${users}">
        <td width="200px" height="200px">
            <img src="${user.avatar}" height="100%" class="img-fluid" />
        </td>
        <td>${user.id}</td>
        <td>${user.username}</td>
        <td>${user.userRole}</td>
        <td>${user.email}</td>

        <td>
            <a class="btn btn-info" href="<c:url value="/user/${user.username}" />">Xem</a>
            <a class="btn btn-danger m-1" href="<c:url value="/user/delete/${user.username}" />">Xóa</a>
        </td>        
    </tr>
</c:forEach>
</tbody>
</table>
<script src="<c:url value="/js/script.js" />"></script>


