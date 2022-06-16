package com.nowcoder.community;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 一个文件里，只有一个类可以是public
 *
 * @author QiaoWeiBo
 * @date 2022/6/15 10:11 PM
 */

public class BlockingQueueTests {
    public static void main(String[] args) {
        //实例化阻塞队列
        BlockingQueue queue = new ArrayBlockingQueue(10);
        //实例化生产者线程并启动
        new Thread(new Producer(queue)).start();
        //实例化消费者线程并启动
        new Thread(new Consumer(queue)).start();
        new Thread(new Consumer(queue)).start();
        new Thread(new Consumer(queue)).start();
    }
}

class Producer implements Runnable {

    //增加一个变量来接收传进来的阻塞队列
    private BlockingQueue<Integer> queue;
    //有参的构造器
    public Producer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 100; ++i) {
                //生产数据中间有间隔
                Thread.sleep(20);
                queue.put(i);
                System.out.println(Thread.currentThread().getName() + "生产:" + queue.size());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

/**
 * 实例化Consumer类的时候也是需要传入queue
 *
 * @author QiaoWeiBo
 * @date 2022/6/16 12:13 AM
 */
class Consumer implements Runnable {

    private BlockingQueue<Integer> queue;

    public Consumer(BlockingQueue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            //只要有数据就一直消费
            while (true) {
                //消费数据的时间间隔是不确定的，所以下面不写死(下面是0～1000随机一个数),消费速度没有生产速度快
                Thread.sleep(new Random().nextInt(1000));
                queue.take();
                System.out.println(Thread.currentThread().getName() + "消费：" + queue.size());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}