package org.example;

public class General_manager extends Handler{
    @Override
    public void handle(int num) {
        if(num<=200000){
            System.out.println("总经理可以处理了"+num+"的购物单");
        }
        else {
            System.out.println("采购单过大，需要开会解决");
        }
    }
}
