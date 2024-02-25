package FominaKat.Philosophers.Dinner;

import java.util.concurrent.CountDownLatch;


public class Dinner {
    private int countPerson;

    public Dinner(int countPerson) {
        this.countPerson = countPerson;
    }

    public void start() {

        try {
            CountDownLatch cdl = new CountDownLatch(countPerson + 1);
            Forks forks = new Forks(countPerson); //готовим стол
            System.out.println("Ужин начался. Приятного аппетита!");
            for (int i = 0; i < countPerson; i++) { //запускаю философов
                new Thread(new Philosoph(i, forks, cdl)).start();
            }

            while (cdl.getCount() > 1) {
                Thread.sleep(100); //жду пока все поедят
            }
            cdl.countDown();

            System.out.println("Ужин закончен. Все философы поели");

        } catch (InterruptedException e) {
            System.out.println(e.getStackTrace());
        }
    }
}
