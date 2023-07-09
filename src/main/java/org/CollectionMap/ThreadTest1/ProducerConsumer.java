package org.CollectionMap.ThreadTest1;

/*
线程安全问题：因为生产者与消费者共享数据缓冲区，产生安全问题。不过这个问题可以使用同步解决。
• 线程的协调工作问题：
– 要解决该问题，就必须让生产者线程在缓冲区满时等待(wait)，暂停进入阻塞状态，等到下次消费者消耗了缓冲区中的数据的时候，通知(notify)正在
等待的线程恢复到就绪状态，重新开始往缓冲区添加数据。同样，也可以
让消费者线程在缓冲区空时进入等待(wait)，暂停进入阻塞状态，等到生产
者往缓冲区添加数据之后，再通知(notify)正在等待的线程恢复到就绪状
态。通过这样的通信机制来解决此类问题。
 */
class Clerk extends Thread {//店员
    int productNum = 0;//产品数量

    public synchronized void addProduct() {
        if (productNum >= 20) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            productNum++;
            System.out.println(Thread.currentThread().getName() + "生产了第" + productNum + "个产品");
            notifyAll();
        }


    }

    public synchronized void minusProduct() {
        if (productNum <= 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();

            }
        } else {
            System.out.println(Thread.currentThread().getName() + "消费了第" + productNum + "个产品");
            productNum--;
            notifyAll();
        }


    }

}

class Producer extends Thread {
    private Clerk clerk;

    public Producer(Clerk clerk) {
        this.clerk = clerk;

    }

    @Override
    public void run() {
        while (true) {
            System.out.println("生产者开始生产");
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clerk.addProduct();

        }
    }
}


class Consumer extends Thread {
    private Clerk clerk;

    public Consumer(Clerk clerk) {
        this.clerk = clerk;

    }

    @Override
    public void run() {
        while (true) {
            System.out.println("消费者开始消费");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clerk.minusProduct();

        }
    }

}


public class ProducerConsumer {
    public static void main(String[] args) {
        Clerk clerk = new Clerk();
        Producer producer1 = new Producer(clerk);
        Consumer consumer1 = new Consumer(clerk);
        Consumer consumer2 = new Consumer(clerk);
        producer1.setName("生产者1");
        consumer1.setName("消费者1");
        consumer2.setName("消费者2");
        producer1.start();
        consumer1.start();
        consumer2.start();
    }

    ;
}


