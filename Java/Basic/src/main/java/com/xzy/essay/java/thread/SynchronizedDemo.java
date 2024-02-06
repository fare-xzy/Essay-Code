package com.xzy.essay.java.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Xuziyu
 * @date 2022/11/27 16:48
 * @apiNote Synchronized 可重入锁， 初期是乐观锁锁升级后为悲观锁，非公平锁，独享锁，互斥锁
 * @since
 */
@Slf4j
public class SynchronizedDemo {

    public void testOutput(int received){
        log.info("SynchronizedDemo.testOutput 【{}】",received);
    }



    /**
     * synchronized 添加在普通方法上的效果
     * @param received 接收到的测试数据
     */
    synchronized void methodSynchronized(int received){
       log.info("SynchronizedDemo.methodSynchronized 【{}】", received);
        try {
            Thread.sleep(10000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * synchronized 添加在静态方法上的效果
     * @param received 接收到的测试数据
     */
    synchronized static void staticMethodSynchronized(int received){
        log.info("SynchronizedDemo.staticMethodSynchronized 【{}】", received);
        try {
            Thread.sleep(10000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * synchronized 添加在块上的效果，实例锁
     * @param received 接收到的测试数据
     */
    void blockSynchronizedThis(int received){
        synchronized (this){
            log.info("SynchronizedDemo.blockSynchronizedThis 【{}】", received);
            try {
                Thread.sleep(10000L);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * synchronized 添加在块上的效果，类锁
     * @param received 接收到的测试数据
     */
    void blockSynchronizedClass(int received){
        synchronized (SynchronizedDemo.class){
            log.info("SynchronizedDemo.blockSynchronizedThis 【{}】", received);
            try {
                Thread.sleep(10000L);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    Object lock = new Object();
    /**
     * wait和Notify方法的测试
     * @param received 接收到的测试数据
     */
    void waitAndNotifySynchronized(int received, boolean isWait){
        synchronized (lock){
            log.info("SynchronizedDemo.waitAndNotifySynchronized 【{}】", received);
            try {
                if(isWait){
                    log.info("SynchronizedDemo.waitAndNotifySynchronized 【WAIT】");
                    lock.wait();
                    log.info("SynchronizedDemo.waitAndNotifySynchronized 【RUN】");

                }else{
                    log.info("SynchronizedDemo.waitAndNotifySynchronized 【NOTIFY】");
                    lock.notify();
                    log.info("SynchronizedDemo.waitAndNotifySynchronized 【WAIT PREPARE START】");
                }
                Thread.sleep(10000L);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * 测试Object锁是否会被其他实例共用
     * @param received 接收到的测试数据
     */
    void objectSynchronizedAndThis(int received){
        synchronized (lock){
            log.info("SynchronizedDemo.objectSynchronizedAndThis 【{}】", received);
            try {
                Thread.sleep(10000L);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

}
