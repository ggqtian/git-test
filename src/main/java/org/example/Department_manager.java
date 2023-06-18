package org.example;

public class Department_manager extends Handler {

    @Override
    public void handle(int num) {
        if(num<=50000){
            System.out.println("部门经理处理了"+num+"的购物单");
        }
        else {
            this.next.handle(num);
        }
    }
}
