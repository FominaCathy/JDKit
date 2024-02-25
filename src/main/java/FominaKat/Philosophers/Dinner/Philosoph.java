package FominaKat.Philosophers.Dinner;

import java.util.Random;
import java.util.concurrent.CountDownLatch;


public class Philosoph implements Runnable {
    private static final int MAX_EATING = 3;
    private String name;
    private int number;
    private int countEating = 0;
    private Forks forks;
    private boolean isForks = false;
    private int secondFork;

    private CountDownLatch cdl;


    public Philosoph(int number, Forks forks, CountDownLatch cdl) {
        this.forks = forks;
        this.name = "Thinker " + number;
        this.number = number;
        this.secondFork = (number == forks.getFreeForks().length - 1) ? 0 : number + 1;
        this.cdl = cdl;
    }

    @Override
    public void run() {
        try {
            while (countEating < MAX_EATING) {
                getFork();
                if (isForks) {
                    eating(); // поел
                    putFork(); //положил вилку
                    countEating++;
                }
                if (countEating != MAX_EATING) {
                    think();
                }
            }
            finishDinner();
            this.cdl.await();

        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    private void eating() throws InterruptedException {
        Thread.sleep(new Random().nextInt(100, 500));
        System.out.println(String.format("%s - Закончил есть", name, countEating));
    }

    private void getFork() {
        if (forks.takeForks(this.number, this.secondFork)) {
            isForks = true;
            System.out.println(String.format("%s - взял вилку (%d, %d)", name, this.number, this.secondFork));
        }
    }

    private void putFork() {
        forks.putForks(this.number, this.secondFork);
        isForks = false;
        System.out.println(String.format("%s - положил вилку (%d, %d)", name, this.number, this.secondFork));
    }

    private void think() throws InterruptedException {
        Thread.sleep(new Random().nextInt(100, 800));
        System.out.println(String.format("%s - мыслит", name));
    }

    private void finishDinner() {
        System.out.println(name + " - закончил трапезу");
        this.cdl.countDown();
    }
}
