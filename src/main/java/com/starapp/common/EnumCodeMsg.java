package com.starapp.common;

import com.sun.org.apache.bcel.internal.classfile.Code;

/**
 * @date:2018/9/517:18
 * @author:Allen
 * @description:返回信息枚举类
 */
public enum EnumCodeMsg{

    Code200("成功",200),
    Code404("未找到页面",400),
    Code500("未找到页面",500),
    Code300("方法出错",300),
    Code301("抛出异常",301),
    Code000("空",0),
    Code001("其他错误信息",1);
    private  String EnumName;
    private    int  EnumCode;

    public String getEnumName() {
        return EnumName;
    }

    public void setEnumName(String enumName) {
        EnumName = enumName;
    }

    public int getEnumCode() {
        return EnumCode;
    }

    public void setEnumCode(int enumCode) {
        EnumCode = enumCode;
    }

    EnumCodeMsg(String enumName, int enumCode) {
        EnumName = enumName;
        EnumCode = enumCode;
    }

    /**
     * 测试
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(EnumCodeMsg.Code200.EnumCode);
    }
}
