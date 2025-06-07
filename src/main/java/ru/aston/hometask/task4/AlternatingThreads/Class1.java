package ru.aston.hometask.task4.AlternatingThreads;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

public class Class1 implements Runnable {

    private Lock lock;

    private StatusClass loopCompleted;

    public Class1(Lock lock, StatusClass loopCompleted) {
        this.lock = lock;
        this.loopCompleted = loopCompleted;
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (!loopCompleted.getStatus()) {
                    if (lock.tryLock(50, TimeUnit.MILLISECONDS)) {
                        System.out.println(Thread.currentThread().getName() + " 1");
                        loopCompleted.setStatus(true);
                        lock.unlock();
                        Thread.sleep(500);
                    }
                }

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
