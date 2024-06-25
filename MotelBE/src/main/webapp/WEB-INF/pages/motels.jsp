<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>QUẢN LÝ SẢN PHẨM</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <h1 class="text-center text-info mt-1">QUẢN LÝ SẢN PHẨM</h1>

        <c:url value="/motels" var="action" />
        <form:form method="post" action="${action}" modelAttribute="motel" enctype="multipart/form-data">
            <form:errors path="*" element="div" cssClass="alert alert-danger" />

            <div class="row">
                <div class="col-md-6">
                    <div class="form-group">
                        <label for="title">Nhan đề</label>
                        <form:input class="form-control" id="title" placeholder='Nhan đề' path="title" />
                    </div>

                    <div class="form-group">
                        <label for="area">Diện tích</label>
                        <form:input class="form-control" id="area" path="area" />
                    </div>

                    <div class="form-group">
                        <label for="price">Giá</label>
                        <form:input class="form-control" id="price" path="price" />
                    </div>

                    <div class="form-group">
                        <label for="numberTenant">Số người ở</label>
                        <form:input class="form-control" id="numberTenant" path="numberTenant" />
                    </div>

                    <div class="form-group">
                        <label for="address">Số nhà/Đường</label>
                        <form:input class="form-control" id="address" path="address" />
                    </div>
                </div>

                <div class="col-md-6">
                    <div class="form-group">
                        <label for="wards">Phường/Xã/Thị trấn</label>
                        <form:input class="form-control" id="wards" path="wards" />
                    </div>

                    <div class="form-group">
                        <label for="district">Quận/Huyện</label>
                        <form:input class="form-control" id="district" path="district" />
                    </div>

                    <div class="form-group">
                        <label for="province">Tỉnh/Thành phố</label>
                        <form:input class="form-control" id="province" path="province" />
                    </div>

                    <div class="form-group">
                        <label for="image">Hình ảnh</label>
                        <form:input type="file" class="form-control" id="image" path="files" multiple="multiple" />
                        <c:if test="${motel.id > 0}">
                            <c:forEach items="${motel.imageCollection}" var="img">
                                <img class="m-2" src="${img.url}" width="200" class="img-fluid" />
                            </c:forEach>
                        </c:if>
                    </div>

                    <div class="form-group">
                        <label for="userId">Người dùng:</label>
                        <form:select class="form-control" id="userId" path="userId">
                            <c:forEach items="${users}" var="c">
                                <c:choose>
                                    <c:when test="${c.id==motel.userId.id}">
                                        <option value="${c.id}" selected>${c.username}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${c.id}">${c.username}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </form:select>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <c:choose>
                    <c:when test="${motel.id == null}"> 
                        <button class="btn btn-info mt-1" type="submit">Thêm</button>
                    </c:when>
                    <c:when test="${motel.status == 'PENDING'}"> 
                        <button class="btn btn-info ml-5 mt-1" type="submit">Duyệt</button>
                    </c:when>
                    <c:when test="${motel.status == 'APPROVED'}"> 
                        <button class="btn btn-info mt-1" type="submit">Cập nhật</button>
                    </c:when>
                </c:choose>
                <form:hidden path="id" />
            </div>
        </form:form>
    </div>

    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
