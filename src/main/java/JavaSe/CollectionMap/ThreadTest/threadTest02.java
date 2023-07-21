package JavaSe.CollectionMap.ThreadTest;

class EvenNumPrint implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + ":" + i);
        }
    }
}

public class threadTest02 {
    public static void main(String[] args) {
        EvenNumPrint p = new EvenNumPrint();
        Thread t1=new Thread(p);
        t1.start();
        //使用实现Runnable接口（提供runnable接口匿名方式）
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <100 ; i++) {
                    if(i%2==0){
                        System.out.println(Thread.currentThread().getName() + ":" + i);
                    }
                }
            }
        }).start();


        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + ":" + i);
        }
    }

}
