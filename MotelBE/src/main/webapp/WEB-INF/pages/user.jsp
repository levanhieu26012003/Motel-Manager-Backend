<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<h1 class="text-center text-info mt-1">QUẢN LÝ SẢN PHẨM</h1>

<c:url value="/user" var="action" />
<form:form method="post" action="${action}" modelAttribute="user" enctype="multipart/form-data">
    <form:errors path="*" element="div" cssClass="alert alert-danger" />

    <div class="form-floating mb-3 mt-3">
        <form:input class="form-control" id="title" path="username" />
                <label for="title">Username</label>

    </div>


    <div class="form-floating mb-3 mt-3">
        <form:input class="form-control" id="title" path="password" />
                <label for="title">password</label>

    </div>


    <div class="form-floating mb-3 mt-3">
        <form:input class="form-control"  path="email" />
                <label for="title">Email</label>

    </div>

    <div class="form-floating">

        <form:select class="form-select"  path="userRole">
            <option value="ROLE_USER_TENANT" selected>ROLE_USER_TENANT</option>
            <option value="ROLE_USER_HOST" selected>ROLE_USER_HOST</option>
            <option value="ROLE_ADMIN" selected>ROLE_ADMIN</option>
        </form:select>
                    <label class="form-label">Danh mục:</label>

    </div>


    <div class="form-floating mb-3 mt-3">
        <form:input type="file" class="form-control"  id="image" path="file"  />

        <c:if test="${user.id > 0}">
                 <img  src="${user.avatar}" width="200" class="img-fluid m-2" />
        </c:if>
    </div>

    <div class="form-floating">
        <button class="btn btn-info mt-1" type="submit">
            <c:choose>
                <c:when test="${user.id > 0}"> Cập nhât</c:when>
                <c:otherwise> Thêm  </c:otherwise>
            </c:choose>
        </button>
        <form:hidden path="id" />

    </form:form>


