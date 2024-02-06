package com.xzy.essay.java.thread;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

/**
 * @author Xuziyu
 * @date 2022/11/27 16:58
 * @apiNote Synchronized 测试
 * 1、普通测试使用@Test 多线程测试使用 @RepeatedTest
 * @since
 */
@Slf4j
public class SynchronizedDemoTest {

    /**
     * 测试相同对象时，Synchronized添加在普通实例方法上的效果
     */
    private static SynchronizedDemo methodSynchronized;

    @BeforeEach
    void init(){
        methodSynchronized = new SynchronizedDemo();
    }


    /**
     * 结果显示：此时Synchronized所获取的锁为实例锁
     *         1、当初始实例为多个普通实例时，不同线程调用Synchronized普通方法不需要获取锁
     *         2、当实例对象为静态实例时或者仅有一个实例时，不同线程调用Synchronized普通方法需要获取锁
     * @param repetitionInfo 多次执行对象
     */
    @RepeatedTest(value = 2)
    void methodSynchronized(RepetitionInfo repetitionInfo) {
        methodSynchronized.testOutput(repetitionInfo.getCurrentRepetition());
        methodSynchronized.methodSynchronized(repetitionInfo.getCurrentRepetition());
    }

    /**
     * 结果显示：当Synchronized添加到静态方法上后，Synchronized所获取的锁为类锁。其与实例锁之间不会发生互斥现象
     * @param repetitionInfo 多次执行对象
     */
    @RepeatedTest(value = 2)
    void staticMethodSynchronized(RepetitionInfo repetitionInfo) {
        SynchronizedDemo.staticMethodSynchronized(repetitionInfo.getCurrentRepetition());
        methodSynchronized.methodSynchronized(repetitionInfo.getCurrentRepetition());
    }

    /**
     * 结果显示：当Synchronized添加到方法块上时，锁设置为实例锁，效果同methodSynchronized一致
     * @param repetitionInfo 多次执行对象
     */
    @RepeatedTest(value = 2)
    void blockSynchronizedThis(RepetitionInfo repetitionInfo) {
        methodSynchronized.blockSynchronizedThis(repetitionInfo.getCurrentRepetition());
        methodSynchronized.methodSynchronized(repetitionInfo.getCurrentRepetition());
    }

    /**
     * 结果显示：当Synchronized添加到方法块上时，锁设置为类锁，效果同staticMethodSynchronized一致
     * @param repetitionInfo 多次执行对象
     */
    @RepeatedTest(value = 2)
    void blockSynchronizedClass(RepetitionInfo repetitionInfo) {
        methodSynchronized.blockSynchronizedClass(repetitionInfo.getCurrentRepetition());
        SynchronizedDemo.staticMethodSynchronized(repetitionInfo.getCurrentRepetition());
    }

    /**
     * 结果显示： wait时可以通过另外的线程操作包含同样的锁实例的notify方法来重新预备启动之前wait的方法
     * @param repetitionInfo 多次执行对象
     */
    @RepeatedTest(value = 2)
    void waitAndNotifySynchronized(RepetitionInfo repetitionInfo) {
        if (repetitionInfo.getCurrentRepetition() == 1) {
            methodSynchronized.waitAndNotifySynchronized(repetitionInfo.getCurrentRepetition(), false);
        }else{
            methodSynchronized.waitAndNotifySynchronized(repetitionInfo.getCurrentRepetition(), true);
        }

    }

    /**
     * 结果显示：这只是一个测试，我想知道锁为Object时 是否所有对象会共用这个锁。答案是不会
     * @param repetitionInfo 多次执行对象
     */
    @RepeatedTest(value = 2)
    void objectSynchronizedAndThis(RepetitionInfo repetitionInfo) {
        methodSynchronized.objectSynchronizedAndThis(repetitionInfo.getCurrentRepetition());
        methodSynchronized.blockSynchronizedThis(repetitionInfo.getCurrentRepetition());
    }
}