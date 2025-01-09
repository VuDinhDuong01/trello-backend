package com.example.trello.Constant;

import lombok.Data;

@Data
public class ErrorMesage {
    public static final int ERROR_OK = 0;
    public static final int ERROR_SYSTEM = ERROR_OK + 1;
    public static final int ERROR_FORBIDDEN = ERROR_OK + 2;
    public static final int ERROR_METHOD_FORMAT_NOT_INVALID = ERROR_OK + 3;
    public static final int ERROR_REQUEST_NOT_FOUND = ERROR_OK + 4;
    public static final int ERROR_PERMISSION_INVALID = ERROR_OK + 5;
    public static final int ERROR_UNAUTHORIZED = ERROR_OK + 6;
    public static final int ERROR_METHOD_NOT_ALLOWED = ERROR_OK + 7;

    public static String errorMesage(int errorCode) {
        switch (errorCode) {
            case ERROR_OK:
                return "Thành công";
            case ERROR_SYSTEM:
                return "Lỗi hệ thống không xác định";
            case ERROR_FORBIDDEN:
                return "Truy cập không hợp lệ";
            case ERROR_METHOD_FORMAT_NOT_INVALID:
                return "Định dạng dữ liệu không hợp lệ";
            case ERROR_REQUEST_NOT_FOUND:
                return "End point url không hợp lệ !";
            case ERROR_PERMISSION_INVALID:
                return "Bạ không có quyền.";
            case ERROR_UNAUTHORIZED:
            default:
                return "Phương thức không hợp lệ.";

        }
    }
}
