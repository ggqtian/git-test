package org.example;

public class Deputy_general_manager extends  Handler{
    @Override
    public void handle(int num) {
        if(num<=100000){
            System.out.println("副总经理处理了"+num+"的购物单");
        }
        else {
            this.next.handle(num);
        }
    }
}
