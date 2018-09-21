package com.starapp.common;

/**
 * @date:2018/9/1823:57
 * @author:Allen
 * @description:
 */
public class TestThread extends Thread{

    private String name;
   int money;

    // 创建一个静态钥匙
    static Object ob = "aa";//值是任意的

    public TestThread(String name,int money){
       super(name);
       this.money=money;
    }




    @Override
    public void run() {
        while(money>0){
            synchronized (ob) {
                if (money > 0) {
                    System.out.println("这个是：" + money + "[" + Thread.currentThread().getName() + "]");
                    money--;
                }else {
                    System.out.println("钱没了");

                }
            }
            try{
                sleep(2000);//休息一秒
            }catch (InterruptedException e){
                    e.getMessage();
            }
    }}

    public static void main(String[] args) {
        TestThread A=new TestThread("A",10);
        A.start();
        TestThread B=new TestThread("B",10);
        B.start();
    }
    }
