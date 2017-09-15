<%-- 
    Document   : index
    Created on : Jul 18, 2017, 2:41:40 AM
    Author     : Temporary
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Cổng tra cứu tuyển sinh đại học</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
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
            <h1 style="margin-top: 50px;">Thông tin tuyển sinh các trường Đại học</h1>
            <h2>Tìm kiếm bằng tên hoặc mã trường</h2>
            <input type="text" id="keyword" class="box search" placeholder="Nhập tên hoặc mã trường" />
        </div>

        <div style="display: block; margin: auto; max-width: 400px;">
            <h3>Score: <span id="score">0</span></h3>
            <canvas id="gc" width="400" height="400"></canvas>
        </div>

        <script>
            document.getElementById("keyword").addEventListener("keypress", function (event) {
                if (event.keyCode == 13 && this.value.trim() != "") {
                    window.location.href = "dispatch?action=search&keyword=" + this.value;
                }
            });
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
