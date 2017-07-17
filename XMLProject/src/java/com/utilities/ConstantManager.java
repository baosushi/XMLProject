package com.utilities;

public class ConstantManager {
    // Servlet name
    public static final String dispatchServlet = "";
    public static final String crawlDataServlet = "CrawlDataServlet";
    
    // Page URL
    public static final String khoidaihoc_path = "http://luyenthithptquocgia.com/cac-khoi-thi-xet-tuyen-dai-hoc-cao-dang-2017-a487.html";
    public static final String mainPage_tuyensinh247_path = "http://diemthi.tuyensinh247.com";
    public static final String diemchuan_tuyensinh247_path = "http://diemthi.tuyensinh247.com/diem-chuan.html";
    public static final String thongtintruong_tuyensinh247_path = "http://diemthi.tuyensinh247.com/danh-sach-truong-dai-hoc-cao-dang.html";
    public static final String mainPage_diemthi24h_path = "http://diemthi.24h.com.vn";
    public static final String diemchuan_diemthi24h_path = "http://diemthi.24h.com.vn/diem-chuan/";
    
    // File path
    public static final String xmlDataDownloadPath = "WEB-INF/html_downloaded.xml";
    
    // Regular Expression
    public static final String REGEX_NUMBERS = "\\d+";
    public static final String REGEX_DOUBLE = "\\d{1,2}(\\.\\d{1,2})?";
    public static final String REGEX_TABS = "\\t{1,}";
    public static final String REGEX_WEBSITE = "((http(s)?):((\\/)){2})?(www(\\.))?([a-z0-9_-])+((\\.)([a-z]){2,4})+(\\/)?";
    public static final String REGEX_EMAIL = "([a-zA-Z0-9_.-])+(@)([a-z])+((\\.)([a-z])+)+";
}
