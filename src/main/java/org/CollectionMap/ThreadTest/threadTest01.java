package org.CollectionMap.ThreadTest;

public class threadTest01 {
    static class PrintNum extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                if (i % 2 == 0) {
                    System.out.println(Thread.currentThread().getName()+i);
                }
            }
        }
    }

    public static void main(String[] args) {
        PrintNum t1 = new PrintNum();
        t1.start();
        //重新开一个线程，然后继续主线程
        /*问题一:能否用run替换start
        run是一个普通的方法，不会创建新的线程

        一个线程只能启动一次，不能让已经start的线程再次执行start

        thead线程名从0开始++

        */
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName()+i + "+++++++++++++++");
            }
        }
    }
}