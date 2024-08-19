<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<h1 class="text-center text-info mt-1">Chi tiết đánh giá</h1>

<table class="table table-striped mt-3">
    <thead class="thead-dark">
        <tr>
            <th>Id</th>
            <th>comment</th>
            <th>rating</th>
            <th>Người đánh giá</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="review" items="${reviews}">
            <tr>                   
                <td>${review.id}</td>
                <td>${review.comment}</td>
                <td>${review.rating}</td>
                <td>${review.tenantId.username}</td>
            </tr>
        </c:forEach>
    </tbody>
    <tr>                   
        <td></td>
        <td>Đánh giá trung bình: </td>
        <td>${avg}</td>
        <td></td>
    </tr>
</table>

<p>Người dùng: ${user.username}</p>



