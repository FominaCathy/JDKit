package FominaKat.Multy;

/**
 * Создайте два потока A и B.
 * Поток A меняет значение Boolean переменной switcher с задержкой 1000 миллисекунд
 * (true в состояние false и наоборот).
 * Поток B ожидает состояния true переменной switcher и выводит на консоль обратный отсчет от 100
 * с задержкой 100 миллисекунд и приостанавливает свое действие, как только поток A переключит switcher
 * в состояние false.
 * Условием завершения работы потоков является достижение отсчета нулевой отметки.
 */
public class Task2 {
    private static volatile boolean switcher;
    public static boolean work = true;

    public static void main(String[] args) {
        Thread changerTrue = new Thread(() -> {
            while (work) {
                switcher = !switcher;

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
                System.out.println(switcher);
            }
        });

        Thread counter = new Thread(() -> {
            int count = 100;
            while (count > 0) {
                if (switcher) {
                    System.out.println(count--);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        System.out.println(e.getMessage());
                    }
                }

            }
            work = false;
        });

        changerTrue.start();
        counter.start();

    }
}
