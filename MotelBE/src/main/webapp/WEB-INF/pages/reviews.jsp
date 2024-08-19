<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="container mt-5">
    <h1 class="text-info text-center">Manage Users</h1>
    <table class="table table-striped mt-3">
        <thead class="thead-dark">
            <tr>
                <th>Avatar</th>
                <th>Id</th>
                <th>Username</th>
                <th>User Role</th>
                <th>Điểm Đánh Giá</th>
                <th>Trạng Thái</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="user" items="${users}">
                <tr>
                    <td width="100px" height="100px">
                        <img src="${user.avatar}" height="100%" class="avatar" />
                    </td>
                    <td>${user.id}</td>
                    <td>${user.username}</td>
                    <td>${user.userRole}</td>
                    <td>${user.avg}</td>
                    <td class="">

                        <c:choose>
                            <c:when test="${user.active}">
                                <i class="fas fa-times status-icon active-icon"></i>
                            </c:when>
                            <c:otherwise>
                                <i class="fas fa-times status-icon inactive-icon"></i>
                            </c:otherwise>
                        </c:choose>


                    </td>
                    <td>
                        <a class="btn btn-info" href="<c:url value='/review/${user.id}' />">Chi tiết</a>
                        <c:choose>
                            <c:when test="${user.active}">
                                <a id="blockBtn"class="btn btn-danger ml-2" href="<c:url value='/user/active/${user.username}' />">Chặn</a>
                            </c:when>
                            <c:otherwise>
                                <a id="blockBtn" class="btn btn-danger ml-2" href="<c:url value='/user/active/${user.username}' />">Bỏ chặn</a>
                            </c:otherwise>
                        </c:choose>


                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
<!--</<script >
   document.getElementById('blockBtn').addEventListener('click', function() {
    var targetElement = document.getElementById('colorStatus');
    if (targetElement.style.backgroundColor === 'green') {
        targetElement.style.backgroundColor = 'red'; // Change to the original color
    } else {
        targetElement.style.backgroundColor = 'green'; // Change to the new color
    }
});


</script>-->


<script src="<c:url value="/js/script.js" />"></script>
