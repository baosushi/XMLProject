<%-- 
    Document   : uniInfo
    Created on : Jul 1, 2017, 2:37:36 PM
    Author     : Temporary
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${a}</title>
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
            <h1>Thông tin tuyển sinh</h1>
            <h1><b>${SelectedUniversity.getUniversityName()}</b></h1>

            <c:import url="WEB-INF/uniInfo.xsl" var="xslDoc" charEncoding="UTF-8"/>
            <x:transform doc="${UniInfo}" xslt="${xslDoc}"/>
        </div>

        <div class="modal hidden">
            <div class="modal-container">
                <img class="modal-close" src="images/icon-close-128.png" />
                <div class="modal-content">
                    <h1>Thông tin tuyển sinh mới nhất</h1>
                    <h2 id="major-name">Ngành</h2>
                    <table id="modal-table" class="shadow-z-1 table table-hover table-mc-light-blue">
                        <thead>
                            <tr>
                                <th>Mã khối</th>
                                <th>Môn thi</th>
                                <th>Chỉ tiêu mới nhất</th>
                                <th>Điểm sàn mới nhất</th>
                            </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <script src="js/common.js" type="text/javascript"></script>
        <script>
            var xml = '${UniInfo}';
            var xmlHttp;
            var xmlDOM;
            var xmlDoc;
            var data = [];

            if (window.DOMParser)
            {
                var parser = new DOMParser();
                xmlDOM = parser.parseFromString(xml, "text/xml");
            }
            else // Internet Explorer
            {
                xmlDoc = new ActiveXObject("Microsoft.XMLDOM");
                xmlDoc.async = false;
                xmlDoc.loadXML(xml);
            }

//            (function () 
            document.querySelector('#table').addEventListener('click', function (event) {
                if (event.target.tagName.toLowerCase() === 'td') {
                    var row = event.target.parentElement;
//                    var toggleState = row.getAttribute("data-toggle");
                    var table = document.getElementById("modal-table");
                    var modal = document.getElementsByClassName("modal")[0];
                    modal.querySelector("#major-name").innerText = "Ngành " + row.childNodes[2].innerText.replace(/Ngành/g, "").replace(/ngành/g, "").replace(/[.]$/g, "").replace(/\s+/g, " ");

//                    if (toggleState == 'false') {
//                        toggleState = 'true';

                    table.tBodies[0].innerHTML = "";
                    var majorDetailXml = row.querySelector("td").querySelector("input[type='hidden']").value;

                    var majorDetailDOM;
                    if (window.DOMParser)
                    {
                        var parser = new DOMParser();
                        majorDetailDOM = parser.parseFromString(majorDetailXml, "text/xml");
                    }
                    else // Internet Explorer
                    {
                        xmlDoc = new ActiveXObject("Microsoft.XMLDOM");
                        xmlDoc.async = false;
                        xmlDoc.loadXML(majorDetailDOM);
                    }

                    searchNode(majorDetailDOM, "modal-table");
                    modal.classList.remove("hidden");
//                    } else {
//                        toggleState = 'false';
//                        modal.classList.add("hidden");
//                        table.tBodies[0].innerHTML = "";
//                    }

//                    row.setAttribute("data-toggle", toggleState);
                }
            });
//            })();

            function searchNode(node, tableId) {
                if (node == null) {
                    return;
                }

                if (node.tagName == "blockOfMajor") {
                    var flag = false;
                    node.childNodes.forEach(function (value, index) {
                        if (value.tagName == "block") {
                            value.childNodes.forEach(function (value1, index1) {
                                if (value1.tagName == "blockName") {
                                    data[0] = value1.firstChild.nodeValue;
                                }

                                if (value1.tagName == "description") {
                                    data[1] = value1.firstChild.nodeValue;
                                }
                            });
                        }

                        if (value.tagName == "acceptedEntryLastYear") {
                            flag = true;
                            data[2] = value.firstChild.nodeValue;
                        }

                        if (value.tagName == "baseScoreLastYear") {
                            flag = true;
                            data[3] = !value.firstChild ? "" : value.firstChild.nodeValue;
                        }
                    });

                    if (flag) {
                        addRow(tableId, data);
                    }
                }

                node.childNodes.forEach(function (value, index) {
                    searchNode(value, tableId);
                });
            }

            function generatePDF() {
                xmlHttp = getXmlHttpObject();
                if (xmlHttp == null) {
                    alert("Ajax not supported!");
                }

                var url = "PDFProcess?uniCode=" + xmlDOM.firstElementChild.getElementsByTagName("code")[0].textContent;

                xmlHttp.onreadystatechange = function () {
                    if (xmlHttp.readyState == 4) { // Code 200 (or not :( )
                        if (xmlHttp.status == 200) {
                            var blob = new Blob([xmlHttp.responseText], {type: "application/pdf"});

                            var a = document.createElement('a');
                            a.href = window.URL.createObjectURL(blob);
                            a.download = xmlDOM.firstElementChild.getElementsByTagName("code")[0].textContent + "-ThongTinTuyenSinh.pdf";
                            a.target = "_blank";
                            a.click();
                        } else {
                            console.log("Request status: " + xmlHttp.status);
                        }
                    }
                };

                xmlHttp.open("GET", url, true);
                xmlHttp.send(null);
            }
        </script>
    </body>
</html>
