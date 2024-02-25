package FominaKat.Multy;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class Runner implements Runnable {

    private String name;
    private CountDownLatch cdl;

    public Runner(String name, CountDownLatch cdl) {
        this.name = name;
        this.cdl = cdl;
    }

    @Override
    public void run() {
        try {
            goToStart();
            cdl.await();
            running();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    private void goToStart() throws InterruptedException {
        System.out.println(name + " -  go to start");
        Thread.sleep(new Random().nextInt(100, 1000));
        System.out.println(name + " - ready to start");
        cdl.countDown();
    }

    private void running() throws InterruptedException {
        System.out.println(name + " START");
        Thread.sleep(new Random().nextInt(100, 1000));
        System.out.println(name + " - FINISH");
    }
}
