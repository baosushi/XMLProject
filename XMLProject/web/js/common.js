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

function asciiString(obj) {
    var str;

    try {
        if (eval(obj))
            str = eval(obj).value;
        else
            str = obj;
    } catch (e) {
        str = obj;
    }

    str = str.replace(/à|á|ạ|ả|ã|â|ầ|ấ|ậ|ẩ|ẫ|ă|ằ|ắ|ặ|ẳ|ẵ/g, "a");
    str = str.replace(/è|é|ẹ|ẻ|ẽ|ê|ề|ế|ệ|ể|ễ/g, "e");
    str = str.replace(/ì|í|ị|ỉ|ĩ/g, "i");
    str = str.replace(/ò|ó|ọ|ỏ|õ|ô|ồ|ố|ộ|ổ|ỗ|ơ|ờ|ớ|ợ|ở|ỡ/g, "o");
    str = str.replace(/ù|ú|ụ|ủ|ũ|ư|ừ|ứ|ự|ử|ữ/g, "u");
    str = str.replace(/ỳ|ý|ỵ|ỷ|ỹ/g, "y");
    str = str.replace(/đ/g, "d");
    str = str.replace(/^\-+|\-+$/g, "");
    return str;
}

Array.prototype.forEach.call(document.getElementsByClassName("modal"), function (element) {
    element.addEventListener('click', function (event) {
        this.classList.add("hidden");
    });

    Array.prototype.forEach.call(element.childNodes, function (child) {
        if (child.classList != null) {
            child.addEventListener("click", function (e) {
                e.stopPropagation();
            });
        }
    });
});

Array.prototype.forEach.call(document.getElementsByClassName("modal-close"), function (element) {
    element.addEventListener('click', function (event) {
        this.parentNode.parentNode.classList.add("hidden");
    });
});