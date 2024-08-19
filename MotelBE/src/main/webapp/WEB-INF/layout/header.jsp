<%-- 
    Document   : header
    Created on : Apr 3, 2024, 1:10:48 PM
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="#">Admin Dashboard</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
            <li class="nav-item m-3 bg- btn">
                <a href="<c:url value="/" />">Nhà trọ</a>
            </li>
            
            <li class="nav-item m-3 bg- btn">
                <a href="<c:url value="/reviews" />">Đánh giá</a>
            </li>

            <li class="nav-item m-3 bg- btn">
                <a href="<c:url value="/users" />">Người dùng</a>
            </li>

            <li class="nav-item m-3 bg- btn">
                <a href="<c:url value="/stats" />">Thống kê báo cáo</a>
            </li>


            <c:choose>
                <c:when test="${pageContext.request.userPrincipal.name == null}">
                    <li class="nav-item m-3 bg- btn">
                        <a  href="<c:url value="/login" />">Đăng nhập</a>
                    </li>
                </c:when>
                <c:when test="${pageContext.request.userPrincipal.name != null}">
                    <li class="nav-item  m-3 bg- btn">
                        
                        <a href="<c:url value="/" />">${pageContext.request.userPrincipal.name}</a>
                    </li>
                    <li class="nav-item  m-3 bg- btn">
                        <a href="<c:url value="/logout" />">Đăng xuất</a>
                    </li>
                </c:when>
            </c:choose>


        </ul>
    </div>
</nav>
