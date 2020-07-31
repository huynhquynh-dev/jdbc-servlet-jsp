package com.laptrinhjavaweb.utils;

import javax.servlet.http.HttpServletRequest;

public class MessageUtil {
    public static void showMessage(HttpServletRequest req) {
        if (req.getParameter("message") != null) {
            String message = "";
            String alert = "";
            switch (req.getParameter("message")){
                case "insert_success":
                    message = "Insert Success";
                    alert = "success";
                    break;
                case "update_success":
                    message = "Update Success";
                    alert = "success";
                    break;
                case "delete_success":
                    message = "Delete Success";
                    alert = "success";
                    break;
                case "system_error":
                    message = "System Error";
                    alert = "success";
                    break;
            }
            req.setAttribute("message", message);
            req.setAttribute("alert", alert);
        }
    }
}
