package FominaKat.Philosophers.Dinner;

import java.util.Arrays;

public class Forks {
    private boolean[] freeForks;

    public Forks(int forks) {
        this.freeForks = new boolean[forks];
        Arrays.fill(this.freeForks, true);
    }


    /**
     * взять вилки
     *
     * @param left  - левая вилка
     * @param right - правая вилка
     */
    public synchronized boolean takeForks(int left, int right) {
        if (freeForks[left] && freeForks[right]) {
            freeForks[left] = false;
            freeForks[right] = false;
            return true;
        }
        return false; //не смог взять
    }

    /**
     * положить вилки
     *
     * @param left  - левая вилка
     * @param right - правая вилка
     */
    public synchronized void putForks(int left, int right) {
        freeForks[left] = true;
        freeForks[right] = true;
    }

    public boolean[] getFreeForks() {
        return freeForks;
    }
}
