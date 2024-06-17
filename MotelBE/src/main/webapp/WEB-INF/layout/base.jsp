<%-- 
    Document   : base
    Created on : Apr 3, 2024, 1:08:22 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>
            <tiles:insertAttribute name="title" />
        </title>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">      
        <style>
            .image-container {
                display: flex;
                flex-wrap: wrap;
            }
            .image-wrapper {
                position: relative;
                margin: 10px;
            }
            .image-wrapper img {
                width: 150px;
                height: 150px;
            }
            .image-wrapper .close {
                position: absolute;
                top: 5px;
                right: 5px;
                background: red;
                color: white;
                border: none;
                border-radius: 50%;
                cursor: pointer;
                width: 25px;
                height: 25px;
                display: flex;
                justify-content: center;
                align-items: center;
            }
        </style>
    </head>
    <body>
        <tiles:insertAttribute name="header" />
        <section class="container">
            <tiles:insertAttribute name="content" />
        </section>
        <tiles:insertAttribute name="footer" />
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
        <script src="<c:url value="${pageContext.request.contextPath}/resources/js/script.js" />"></script>

        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>
