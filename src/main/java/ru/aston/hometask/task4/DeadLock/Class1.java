package ru.aston.hometask.task4.DeadLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

public class Class1 implements Runnable {

    private Lock lock1;

    private Lock lock2;

    public Class1(Lock lock1, Lock lock2) {
        this.lock1 = lock1;
        this.lock2 = lock2;
    }

    @Override
    public void run() {
        lock1.lock();
        System.out.println(Thread.currentThread().getName() + ": Lock 1 aquired");
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        while (true) {
            try {
                System.out.println(Thread.currentThread().getName() + ": Awaiting Lock 2");
                if (lock2.tryLock(2, TimeUnit.SECONDS)) {
                    System.out.println(Thread.currentThread().getName() + ": Lock 2 acquired");
                    break;
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        lock2.unlock();
        lock1.unlock();
    }
}
