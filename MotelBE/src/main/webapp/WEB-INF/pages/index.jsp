<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- Không cần header và footer vì đã được kế thừa từ base.jsp -->

<h1 class="text-info text-center">QUẢN LÝ NHÀ TRỌ</h1>
<div>
    <a class="btn btn-success m-2" href="<c:url value="/motels" />">Thêm nhà trọ</a>
</div>
<table class="table table-striped">
    <thead>
        <tr>
            <th></th>
            <th>Id</th>
            <th>Nhan đề</th>
            <th>Địa chỉ</th>
            <th>Giá</th>
            <th>Trạng thái</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="motel" items="${motels}">
        <td  width="250px">
            <img src="${motel.imageCollection[0].url}" class="img-fluid" />
        </td>
        <td>${motel.id}</td>
        <td>${motel.title}</td>
        <td>${motel.address}</td>
        <td>${String.format("%,d", motel.price)} VNĐ</td>
        <td>
            <c:choose>
                <c:when test="${motel.status == 'PENDING'}">
                    <p class='text-success'>Chờ xét duyệt</p>
                </c:when>
                <c:otherwise>
                    <p class='text-danger'>Đã duyệt</p>
                </c:otherwise>
        </c:choose>
    </td>
    <td>
        <a class="btn btn-info m-1" href="<c:url value="/motels/${motel.id}" />">Xem</a>
        <a class="btn btn-danger m-1" href="<c:url value="/motels/delete/${motel.id}" />">Xóa</a>
    </td>        
</tr>
</c:forEach>
</tbody>
</table>
<script src="<c:url value="/js/script.js" />"></script>


