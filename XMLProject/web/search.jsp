<%-- 
    Document   : index
    Created on : May 23, 2017, 10:05:48 AM
    Author     : Temporary
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>Thông tin tuyển sinh Đại học</title>
        <link rel="stylesheet" href="css/style.css" />
    </head>
    <body>
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
        <div id="demo">
            <h1>Danh sách các trường đại học</h1>
            <h2>Bấm vào từng trường để xem thông tin chi tiết và điểm chuẩn gần nhất</h2>
            <div class="pull-right">
                <input type="text" id="keyword" class="box search" placeholder="Nhập tên hoặc mã trường" />
            </div>
            <div class="table-container">
                <!-- Table starts here -->
                <table id="table" class="shadow-z-1 table table-hover table-mc-light-blue">
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>Tên trường</th>
                            <th>Mã trường</th>
                            <th>Liên hệ</th>
                        </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
            </div>
        </div>
        <form hidden method="GET" action="dispatch" id="uniInfo">
            <input type="text" name="code" value="" />
            <input type="submit" name="action" value="uniInfo" />
        </form>
        <br>

        <script src="js/common.js" type="text/javascript"></script>
        <script>
            var count = 0;
            var xmlHttp;
            var xmlDOM;
            var data = [];
            var timeoutObject = null;

            function traversalDOMTree(tableId) {
                //clear table
                var table = document.getElementById(tableId);
                table.tBodies[0].innerHTML = "";

                count = 0;

                //parse DOMTree
                if (!localStorage.uniList || (new Date() - new Date(localStorage.uniListDataTime)) >= 3600000) {
                    getUniversityList();

                    xmlHttp.onreadystatechange = function () {
                        if (xmlHttp.readyState == 4) { // Code 200 (or not :( )
                            if (xmlHttp.status == 200) {
                                xmlDOM = xmlHttp.responseXML;
                                localStorage.uniList = new XMLSerializer().serializeToString(xmlDOM);
                                localStorage.uniListDataTime = new Date();
                            } else {
                                console.log("Request status: " + xmlHttp.status);
                            }

//                        if (xmlDOM.parseError.errorCode != 0) {
//                            console.log("Error: " + xml.parseError.reason);
//                        } else {
                            searchNode(xmlDOM, tableId);
//                        }
                        }
                    };
                } else {
                    if (window.DOMParser)
                    {
                        var parser = new DOMParser();
                        xmlDOM = parser.parseFromString(localStorage.uniList, "text/xml");
                    }
                    else // Internet Explorer
                    {
                        xmlDoc = new ActiveXObject("Microsoft.XMLDOM");
                        xmlDoc.async = false;
                        xmlDoc.loadXML(localStorage.uniList);
                    }

                    searchNode(xmlDOM, tableId);
                }

                document.getElementById("keyword").addEventListener("input", function () {
                    clearTimeout(timeoutObject);
                    timeoutObject = setTimeout(function () {
                        var keyword = document.getElementById("keyword").value;
                        searchUniversity(keyword.toLowerCase(), tableId);
                    }, 500);
                });
                
                if('${param.keyword}'.trim() != ""){
                    document.getElementById("keyword").value = '${param.keyword}'.trim();
                    document.getElementById("keyword").dispatchEvent(new Event('input'));
                }
            }

            function searchUniversity(keyword, tableId) {
                var table = document.getElementById(tableId);
                table.tBodies[0].innerHTML = "";

                count = 0;
                searchUniDOM(xmlDOM, tableId, keyword);
            }

            function searchUniDOM(node, tableId, keyword) {
                if (node == null) {
                    return;
                }

                if (node.tagName == "university") {
                    var flag = false;
                    var name = "";
                    var code = "";
                    node.childNodes.forEach(function (value, index) {
                        if (value.tagName == "universityName") {
                            name = value.firstChild.nodeValue;
                            flag |= asciiString(name.toLowerCase()).indexOf(asciiString(keyword)) !== -1;
                            flag |= name.toLowerCase().indexOf(keyword) !== -1;
                        }

                        if (value.tagName == "code") {
                            code = !value.firstChild ? "" : value.firstChild.nodeValue;
                            flag |= (code != null && asciiString(code.toLowerCase()).indexOf(keyword) !== -1);
                            flag |= (code != null && code.toLowerCase().indexOf(keyword) !== -1);
                        }

                        if (value.tagName == "phoneNumber") {
                            data[3] = !value.firstChild ? "" : value.firstChild.nodeValue;
                        }
                    });

                    if (flag) {
                        data[0] = ++count;
                        data[1] = name;
                        data[2] = code;
                        addRow(tableId, data);
                    }
                }

                node.childNodes.forEach(function (value, index) {
                    searchUniDOM(value, tableId, keyword);
                });
            }

            function searchNode(node, tableId) {
                if (node == null) {
                    return;
                }

                if (node.tagName == "university") {
                    data[0] = ++count;
                    node.childNodes.forEach(function (value, index) {
                        if (value.tagName == "universityName") {
                            data[1] = value.firstChild.nodeValue;
                        }

                        if (value.tagName == "code") {
                            data[2] = value.firstChild.nodeValue;
                        }

                        if (value.tagName == "phoneNumber") {
                            data[3] = !value.firstChild ? "" : value.firstChild.nodeValue;
                        }
                    });

                    addRow(tableId, data);
                }

                node.childNodes.forEach(function (value, index) {
                    searchNode(value, tableId);
                });
            }

            function getUniversityList() {
                xmlHttp = getXmlHttpObject();
                if (xmlHttp == null) {
                    alert("Ajax not supported!");
                }

                var url = "GetUniversityList";

                xmlHttp.open("GET", url, true);
                xmlHttp.send(null);
            }

            (function () {
                traversalDOMTree("table");

                document.querySelector('#table').addEventListener('click', function (event) {
                    if (event.target.tagName.toLowerCase() === 'td') {
                        var selectedUniCode = event.target.parentElement.childNodes[2].textContent;
                        document.getElementsByName("code")[0].value = selectedUniCode;
                        document.getElementById("uniInfo").querySelector("input[type=submit]").click();
                    }
                });
            })();
        </script>
    </body>
</html>