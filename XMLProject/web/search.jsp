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
        <title>University Entrance Info</title>
        <link rel="stylesheet" href="css/style.css" />
    </head>
    <body>
        <div id="demo">
            <h1>Danh sách các trường đại học</h1>
            <h2>Bấm vào từng trường để xem thông tin chi tiết và điểm chuẩn gần nhất</h2>
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
        <form hidden method="GET" action="dispatch" id="uniInfo">
            <input type="text" name="code" value="" />
            <input type="submit" name="action" value="uniInfo" />
        </form>
        <br>
        <div style="display: block; margin: auto; max-width: 400px;">
            <h3>Score: <span id="score">0</span></h3>
            <canvas id="gc" width="400" height="400"></canvas>
        </div>

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
                            flag |= asciiString(name.toLowerCase()).indexOf(keyword) !== -1;
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

        <script>
            var ctx;
            var canv;
            window.onload = function () {
                canv = document.getElementById("gc");
                ctx = canv.getContext("2d");
                document.addEventListener("keydown", keyPush);
                setInterval(game, 1000 / 13);
                score = document.getElementById("score");
            }

            var px = py = 10;
            var gs = tc = 20;
            var ax = ay = 15;
            var xv = yv = 0;
            var trail = [];
            var tail = 5;
            var isHorizontal;
            var score;

            function game() {
                px += xv;
                py += yv;
                if (px < 0) {
                    px = tc - 1;
                }
                if (px > tc - 1) {
                    px = 0;
                }
                if (py < 0) {
                    py = tc - 1;
                }
                if (py > tc - 1) {
                    py = 0;
                }
                ctx.fillStyle = "black";
                ctx.fillRect(0, 0, canv.width, canv.height);

                ctx.fillStyle = "lime";
                for (var i = 0; i < trail.length; i++) {
                    ctx.fillRect(trail[i].x * gs, trail[i].y * gs, gs - 2, gs - 2);
                    if (trail[i].x == px && trail[i].y == py) {
                        tail = 5;
                        score.innerHTML = 0;
                    }
                }
                trail.push({x: px, y: py});
                while (trail.length > tail) {
                    trail.shift();
                }

                if (ax == px && ay == py) {
                    tail++;
                    ax = Math.floor(Math.random() * tc);
                    ay = Math.floor(Math.random() * tc);
                    score.innerHTML = (parseInt(score.innerHTML) + 1);
                }

                ctx.fillStyle = "red";
                ctx.fillRect(ax * gs, ay * gs, gs - 2, gs - 2);
            }

            function keyPush(evt) {
                switch (evt.keyCode) {
                    case 37:
                        evt.preventDefault();
                        if (!isHorizontal || (xv == 0 && yv == 0)) {
                            xv = -1;
                            yv = 0;
                        }
                        isHorizontal = true;
                        break;
                    case 38:
                        evt.preventDefault();
                        if (isHorizontal || (xv == 0 && yv == 0)) {
                            xv = 0;
                            yv = -1;
                        }
                        isHorizontal = false;
                        break;
                    case 39:
                        evt.preventDefault();
                        if (!isHorizontal || (xv == 0 && yv == 0)) {
                            xv = 1;
                            yv = 0;
                        }
                        isHorizontal = true;
                        break;
                    case 40:
                        evt.preventDefault();
                        if (isHorizontal || (xv == 0 && yv == 0)) {
                            xv = 0;
                            yv = 1;
                        }
                        isHorizontal = false;
                        break;
                }
            }
        </script>
    </body>
</html>