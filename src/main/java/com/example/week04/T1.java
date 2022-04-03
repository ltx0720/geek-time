package com.example.week04;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ltx
 * @date 2022/3/26 7:04 下午
 */
public class T1 {

    private static volatile boolean flag = Boolean.FALSE;
    private static AtomicInteger integer = new AtomicInteger(0);
    private static CountDownLatch downLatch = new CountDownLatch(1);
    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
    private static Semaphore semaphore = new Semaphore(1);
    private static Lock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {


        // 1.volatile
        new Thread(()-> {
            String str = T1.testMethod();
            flag = Boolean.TRUE;
            System.out.println("method end 1");
        }).start();
        while (!flag) {}
        System.out.println("main end 1");

        // 2.cas
        for(int i=0; i<30; i++) {
            int finalI = i;
            int finalK = i+1;
            new Thread(()-> {
                String str = T1.testMethod();
                while (!integer.compareAndSet(finalI, finalK)) {
                }

            }).start();
        }
        while (!integer.compareAndSet(30, 1)) {
//            Thread.sleep(3);
        }
        System.out.println(integer.get());

        System.out.println("main end 2");


        // 3.CountDownLatch
        new Thread(()-> {
            String str = T1.testMethod();
            downLatch.countDown();
        }).start();
        downLatch.await();


        // 4.cyclicBarrier
        new Thread(()-> {
            String str = T1.testMethod();
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }).start();
        cyclicBarrier.await();

        // 5.信号量
        semaphore.acquire();
        new Thread(()-> {
            String str = T1.testMethod();
            System.out.println("str end");
            semaphore.release();
        }).start();
        semaphore.acquire();
        System.out.println("main end");


        System.out.println("main end");

        return;

    }



    public static String testMethod() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "str";
    }
}
