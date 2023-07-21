package JavaSe.CollectionMap.noteSafe;

class SaleTickets implements Runnable {
    int ticket = 100;


    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (this) {//obj 必须唯一？yes
                if (ticket > 0) {
                    System.out.println(Thread.currentThread().getName() + "窗口售票，票号为：" + ticket);
                    ticket--;
                } else {
                    break;
                }
            }
        }
    }
}

public class WindowTest {
    public static void main(String[] args) {
        SaleTickets saleTickets = new SaleTickets();
        Thread t1 = new Thread(saleTickets);
        Thread t2 = new Thread(saleTickets);
        Thread t3 = new Thread(saleTickets);
        t1.setName("窗口一");
        t2.setName("窗口二");
        t3.setName("窗口三");
        t1.start();
        t2.start();
        t3.start();

    }
}
