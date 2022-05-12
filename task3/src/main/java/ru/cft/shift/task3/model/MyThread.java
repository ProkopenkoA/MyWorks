package ru.cft.shift.task3.model;

public class MyThread extends Thread {

    SecondListener secondListener;
    int secondTime = 0;

    public int getSecondTime() {
        return secondTime;
    }

    public MyThread(SecondListener secondListener) {
        this.secondListener = secondListener;
    }

    public MyThread() {
    }

    @Override
    public void run() {
        while (secondTime < 999) {
            secondListener.setSecond(secondTime);
            secondTime++;
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
