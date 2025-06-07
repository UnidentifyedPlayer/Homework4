package ru.aston.hometask.task4.LiveLock;

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
        while (true) {
            try {
                lock1.tryLock(50, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println("lock1 acquired, trying to acquire lock2.");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if (lock2.tryLock()) {
                System.out.println("lock2 acquired.");
            } else {
                System.out.println("cannot acquire lock2, releasing lock1.");
                lock1.unlock();
                continue;
            }

            System.out.println("executing first operation.");
            break;
        }
        lock2.unlock();
        lock1.unlock();
    }
}
