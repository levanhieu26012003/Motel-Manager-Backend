<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<h1 class="text-center text-info mt-1">QUẢN LÝ SẢN PHẨM</h1>

<c:url value="/user" var="action" />
<form:form method="post" action="${action}" modelAttribute="user" enctype="multipart/form-data">
    <form:errors path="*" element="div" cssClass="alert alert-danger" />

    <div class="form-floating mb-3 mt-3">
        <label for="title">Username</label>
        <form:input class="form-control" id="title" path="username" />
    </div>

    
            <div class="form-floating mb-3 mt-3">
                <label for="title">password</label>
                <form:input class="form-control" id="title" path="password" />
            </div>
        

    <div class="form-floating mb-3 mt-3">
        <label for="title">Email</label>
        <form:input class="form-control"  path="email" />
    </div>

    <div class="form-floating mb-3 mt-3">
        <label for="title">Role</label>
        <form:input class="form-control"  path="userRole" />
    </div>


    <div class="form-floating mb-3 mt-3 image-container">
        <form:input type="file" class="form-control"  id="image" path="file"  />
        <label for="image">Ảnh sản phẩm</label>

        <c:if test="${user.id > 0}">
            <div class="image-wrapper"
                 <img  src="${user.avatar}" width="200" class="img-fluid" />
            </div>
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


