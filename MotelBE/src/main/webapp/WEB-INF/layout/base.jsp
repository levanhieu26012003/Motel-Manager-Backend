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
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

        <style>
            .status-icon {
                font-size: 1.5em;
            }
            .active-icon {
                color: green;
            }
            .inactive-icon {
                color: red;
            }
            .login-container {
                max-width: 400px;
                margin: 50px auto;
                padding: 15px;
                border: 1px solid #ddd;
                border-radius: 5px;
                background-color: #f9f9f9;
            }
            .avatar {
                border-radius: 50%;
                width: 100px;
                height: 100px;
                object-fit: cover;
            }
            table th, table td {
                text-align: center; /* Căn giữa chữ theo chiều ngang */
                vertical-align: middle; /* Căn giữa chữ theo chiều dọc */
                /* trang đánh giá*/
                .user-info {
                    display: flex;
                    align-items: center;
                }
                .user-info img {
                    width: 100px;
                    height: 100px;
                    border-radius: 50%;
                    margin-right: 20px;
                }
                .reviews {
                    margin-top: 20px;
                }
                .review {
                    border: 1px solid #ddd;
                    padding: 10px;
                    margin-bottom: 10px;
                }
                .low-rating {
                    background-color: #f8d7da;
                }
            }
        </style>
        <title>Login Page</title>

    </head>
    <body>
        <tiles:insertAttribute name="header" />
        <section class="container">
            <tiles:insertAttribute name="content" />
        </section>
        <tiles:insertAttribute name="footer" />
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>

        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>
