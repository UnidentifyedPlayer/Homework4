package ru.aston.hometask.task4.LiveLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) {
        Lock lock1 = new ReentrantLock(true);
        Lock lock2 = new ReentrantLock(true);
        Class1 class1 = new Class1(lock1, lock2);
        Class2 class2 = new Class2(lock1, lock2);
        Thread thread1 = new Thread(class1);
        Thread thread2 = new Thread(class2);
        thread1.start();
        thread2.start();
    }
}
