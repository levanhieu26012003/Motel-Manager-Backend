<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- Không cần header và footer vì đã được kế thừa từ base.jsp -->

<h1 class="text-info text-center">Manage Motels</h1>
<div>
    <a class="btn btn-success" href="<c:url value="/motels" />">Thêm sản phẩm</a>
</div>
<table class="table table-striped">
    <thead>
        <tr>
            <th></th>
            <th>Id</th>
            <th>Title</th>
            <th>Address</th>
            <th>Price</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="motel" items="${motels}">
        <td>
            <c:forEach items="${motel.imageCollection}" var="c">
                <img src="${c.url}" width="200" class="img-fluid" />
            </c:forEach>
        </td>
        <td>${motel.id}</td>
        <td>${motel.title}</td>
        <td>${motel.address}</td>
        <td>${motel.price}</td>

        <td>
            <a class="btn btn-info m-1" href="<c:url value="/motels/${motel.id}" />">Xem</a>
            <a class="btn btn-danger m-1" href="<c:url value="/motels/delete/${motel.id}" />">Xóa</a>
        </td>        
    </tr>
</c:forEach>
</tbody>
</table>
<script src="<c:url value="/js/script.js" />"></script>


