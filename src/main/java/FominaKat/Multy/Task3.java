package FominaKat.Multy;

import java.util.concurrent.CountDownLatch;

/**
 * 3 бегуна должны прийти к старту гонки
 * Программа должна гарантировать, что гонка начнется только когда все три участника будут на старте
 * Программа должна отсчитать “На старт”, “Внимание”, “Марш”
 * Программа должна завершиться когда все участники закончат гонку.
 * Подумайте об использовании примитива синхронизации
 */
public class Task3 {


    public static void main(String[] args) throws InterruptedException {

        CountDownLatch cdl = new CountDownLatch(4);

        Thread tom = new Thread(new Runner("Tom", cdl));
        Thread jerry = new Thread(new Runner("Jerry", cdl));
        Thread lulu = new Thread(new Runner("Lulu", cdl));

        tom.start();
        jerry.start();
        lulu.start();


        while (cdl.getCount() > 1){
            Thread.sleep(100);
        }
        System.out.println("на старт!");
        Thread.sleep(500);
        System.out.println("внимание!");
        Thread.sleep(500);
        System.out.println("марш!");
        cdl.countDown();
    }
}
