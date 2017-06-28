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
        <link rel="stylesheet" href="style.css" />
    </head>
    <body>
        <div id="demo">
            <h1>Material Design Responsive Table</h1>
            <h2>Table of my other Material Design works (list was updated 08.2015)</h2>

            <div class="shadow-z-1">
                <!-- Table starts here -->
                <table id="table" class="table table-hover table-mc-light-blue">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Link</th>
                            <th>Status</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td data-title="ID">1</td>
                            <td data-title="Name">Material Design Color Palette</td>
                            <td data-title="Link">
                                <a href="https://github.com/zavoloklom/material-design-color-palette" target="_blank">GitHub</a>
                            </td>
                            <td data-title="Status">Completed</td>
                        </tr>
                        <tr>
                            <td data-title="ID">2</td>
                            <td data-title="Name">Material Design Iconic Font</td>
                            <td data-title="Link">
                                <a href="https://codepen.io/zavoloklom/pen/uqCsB" target="_blank">Codepen</a>
                                <a href="https://github.com/zavoloklom/material-design-iconic-font" target="_blank">GitHub</a>
                            </td>
                            <td data-title="Status">Completed</td>
                        </tr>
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

            function traversalDOMTree(tableId) {
                //clear table
                var table = document.getElementById(tableId);
                var i = 1;
                while (i < table.rows.length) {
                    deleteRow(tableId, i);
                }

                count = 0;

                //parse DOMTree
                getUniversityList();
                console.log(xmlDOM);
                
                if(xmlDOM.parseError.errorCode != 0) {
                    console.log("Error: " + xml.parseError.reason);
                } else {
                    searchNode(xmlDOM, tableId)
                }
            }
            
            function searchNode (node, tableId) {
                if(node == null) {
                    return;
                }
                
                if(node.tagName == )
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

                xmlHttp.onreadystatechange = function () {
                    if (xmlHttp.readyState == 4) { // Code 200 (or not :( )
                        if(xmlHttp.status == 200) {
                            xmlDOM = xmlHttp.responseXML;
                        } else {
                            console.log("Request status: " + xmlHttp.status);
                        }
                    }
                };

                xmlHttp.open("GET", url, true);
                xmlHttp.send(null);
            }

            (function () {
                if (!localStorage.uniList) {


                    xhr.onreadystatechange = handleResponse;
                    function handleResponse() {
                        if (xhr.readyState == 4) { // Code 200 (or not :( )

                        } else { // Skip if not ready

                        }
                    }

                    xhr.open("GET", "getUniversityList", false);
                    xhr.send(null);
                }
            })();
        </script>
    </body>
</html>