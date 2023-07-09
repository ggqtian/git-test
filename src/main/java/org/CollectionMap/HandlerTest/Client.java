package org.CollectionMap.HandlerTest;

public class Client {
    public static void main(String[] args) {
        Handler director=new Director();
        Handler department_manager=new Department_manager();
        Handler deputy_general_manager=new Deputy_general_manager();
        Handler  general_manager=new General_manager();
        director.setNext(department_manager);
        department_manager.setNext(deputy_general_manager);
        deputy_general_manager.setNext(general_manager);
        director.handle(1000);
        director.handle(20000);
        director.handle(80000);
        director.handle(120000);
        director.handle(210000);

    }

}


