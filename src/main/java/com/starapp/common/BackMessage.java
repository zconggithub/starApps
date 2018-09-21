package com.starapp.common;

/**
 * @date:2018/9/517:14
 * @author:Allen
 * @description:
 */
public class BackMessage {
    private  String backExceptionMsg;//异常信息
    private  int backCode;//返回结果码
    private  String backCode_Msg;//返回码对应信息
    private  Object obj;

    public BackMessage() {
    }

    public BackMessage(String backExceptionMsg, int backCode, String backCode_Msg, Object obj) {
        this.backExceptionMsg = backExceptionMsg;
        this.backCode = backCode;
        this.backCode_Msg = backCode_Msg;
        this.obj = obj;
    }

    public String getBackExceptionMsg() {
        return backExceptionMsg;
    }

    public void setBackExceptionMsg(String backExceptionMsg) {
        this.backExceptionMsg = backExceptionMsg;
    }

    public int getBackCode() {
        return backCode;
    }

    public void setBackCode(int backCode) {
        this.backCode = backCode;
    }

    public String getBackCode_Msg() {
        return backCode_Msg;
    }

    public void setBackCode_Msg(String backCode_Msg) {
        this.backCode_Msg = backCode_Msg;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}
