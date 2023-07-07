package org.example.noteSafe;

class SaleTickets1 implements Runnable {
    int ticket = 100;
    boolean isFlage=true;

    @Override
    public void run() {

       while (isFlage){

           show();
       }
    }

    private synchronized void show() {//此时的同步监视器是this，即是此题目的this

        if (ticket > 0) {

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "窗口售票，票号为：" + ticket);
            ticket--;
        } else{
            isFlage=false;
        }
    }
}

public class WindowTest1 {

    public static void main(String[] args) {
        SaleTickets saleTickets1 = new SaleTickets();
        Thread t1 = new Thread(saleTickets1);
        Thread t2 = new Thread(saleTickets1);
        Thread t3 = new Thread(saleTickets1);
        t1.setName("窗口一");
        t2.setName("窗口二");
        t3.setName("窗口三");
        t1.start();
        t2.start();
        t3.start();

    }


}
