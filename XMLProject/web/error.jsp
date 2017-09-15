<%-- 
    Document   : error
    Created on : Jul 18, 2017, 2:41:07 AM
    Author     : Temporary
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Trang lỗi!</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/style.css" />
    </head>
    <body style="background: url(images/Ghost-Default-Error-Screen.png) no-repeat center; overflow: hidden;">
        <div class="nav-fixed">
            <div class="logo">
                <img src="images/graduation-white-hat-icon-77155.png" alt="graduation-white-hat-icon-77155"/>
            </div>
            <div class="menu-container">
                <h2>
                    Tuyển sinh XML
                </h2>
                <div class="menu-item">
                    <a href="dispatch?action=search">Danh sách các trường Đại học</a>
                </div>
                <div class="menu-item">
                    <a href="dispatch?action=home">Trang chủ</a>
                </div>
            </div>
        </div>

        <script>
            document.getElementsByTagName("body")[0].addEventListener("click", function (event) {
                window.location.href = "dispatch?action=home";
            });
        </script>
    </body>
</html>
