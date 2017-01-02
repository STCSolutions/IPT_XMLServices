/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.DB;

/**
 *
 * @author AMR
 */
public class BroadcastMessageItem {

    private String SenderExt;

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public String getSenderExt() {
        return SenderExt;
    }

    public void setSenderExt(String SenderExt) {
        this.SenderExt = SenderExt;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }
    private String Message;
    private String UserName;


}
