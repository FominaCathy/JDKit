package FominaKat.Multy;

/**
 * В рамках выполнения задачи необходимо:
 * Создать два класс ObjectA, ObjectB
 * Реализовать класс в котором два потока при запуске провоцируют DeadLock для объектов ObjectA, ObjectB
 */
public class Main {

    public static void main(String[] args) {


        ObjectA objectA = new ObjectA();
        ObjectB objectB = new ObjectB();

        Thread firstThread = new Thread(() -> {

            synchronized (objectA) {
                System.out.println("объект А заблокирован. ожидаем объект Б");
                synchronized (objectB) {
                    System.out.println("объект Б заблокирован");
                }

            }
        });

        Thread secondThread = new Thread(() -> {

            synchronized (objectB) {
                System.out.println("объект B заблокирован. ожидаем объект А");
                synchronized (objectA) {
                    System.out.println("объект А заблокирован");
                }


            }
        });

        firstThread.start();
        secondThread.start();
    }
}
