package ru.aston.hometask.task4.LiveLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

public class Class2 implements Runnable {

    private Lock lock1;

    private Lock lock2;

    public Class2(Lock lock1, Lock lock2) {
        this.lock1 = lock1;
        this.lock2 = lock2;
    }

    @Override
    public void run() {
        while (true) {
            try {
                lock2.tryLock(50, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("lock2 acquired, trying to acquire lock1.");

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if (lock1.tryLock()) {
                System.out.println("lock1 acquired.");
            } else {
                System.out.println("cannot acquire lock1, releasing lock2.");
                lock2.unlock();
                continue;
            }

            System.out.println("executing second operation.");
            break;
        }
        lock1.unlock();
        lock2.unlock();
    }
}
