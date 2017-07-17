<%-- 
    Document   : index
    Created on : May 23, 2017, 10:05:48 AM
    Author     : Temporary
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>University Entrance Info</title>
        <link rel="stylesheet" href="css/style.css" />
    </head>
    <body>
        <%--<jsp:forward page="/CrawlDataServlet" />--%>
        <div id="demo">
            <h1>Danh sách các trường đại học</h1>
            <h2>Bấm vào từng trường để xem thông tin chi tiết và điểm chuẩn gần nhất</h2>
            <div class="pull-right"><input type="text" id="keyword" class="search box" placeholder="University name or code" /></div>
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
            var count = 0;
            var xmlHttp;
            var xmlDOM;
            var data = [];

            function deleteRow(tableId, position) {
                var table = document.getElementById(tableId);
                if (position > 0 && position < table.rows.length) {
                    table.deleteRow(position);
                } else {
                    console.log("Delete failed");
                }
            }

            function addRow(tableId, cells) {
                var table = document.getElementById(tableId);
                var row = table.tBodies[0].insertRow(table.tBodies[0].rows.length);
                var tmp;
                cells.forEach(function (value, index) {
                    tmp = row.insertCell(row.cells.length);
                    tmp.innerHTML = value;
                });

                return row;
            }

            function traversalDOMTree(tableId) {
                //clear table
                var table = document.getElementById(tableId);
                var i = 1;
                while (i < table.rows.length) {
                    deleteRow(tableId, i);
                }

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

            function getXmlHttpObject() {
                var xmlHttp = null;
                try { // Common up to date browser
                    xmlHttp = new XMLHttpRequest();
                } catch (e) { // IE
                    try {
                        xmlHttp = new ActiveXObject("Msxml12.XMLHTTP");
                    } catch (ex) {
                        xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
                    }
                }

                return xmlHttp;
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
//                if (!localStorage.uniList) {
//                    getUniversityList();
//                } else {
//                    xmlDOM = localStorage.uniList;
//                }
//                
//                if (xmlDOM.parseError.errorCode != 0) {
//                    console.log("Error: " + xml.parseError.reason);
//                } else {
//                    
//                }

                traversalDOMTree("table");
            })();
        </script>
    </body>
</html>