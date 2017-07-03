<%-- 
    Document   : uniInfo
    Created on : Jul 1, 2017, 2:37:36 PM
    Author     : Temporary
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${a}</title>
        <link rel="stylesheet" href="css/style.css" />
    </head>
    <body>
        <div id="demo">
            <h1>Thông tin tuyển sinh</h1>
            <h2>${a}</h2>
            <div class="pull-right">
                <input type="text" id="keyword" class="search box" placeholder="University name or code" />
            </div>
            <div class="table-container">
                <!-- Table starts here -->
                <table id="table" class="shadow-z-1 table table-hover table-mc-light-blue">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>University Name</th>
                            <th>University Code</th>
                            <th>Contact Number</th>
                        </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
            </div>
        </div>

        <script>
            var xml = '${UniInfo}';
        </script>
    </body>
</html>
