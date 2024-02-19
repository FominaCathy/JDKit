package FominaKat.Generics;

import java.io.DataInput;
import java.io.InputStream;

/**
 * Создать обобщенный класс с тремя параметрами (T, V, K).
 * Класс содержит три переменные типа (T, V, K),
 * конструктор, принимающий на вход параметры типа (T, V, K),
 * методы возвращающие значения трех переменных.
 *
 * Создать метод, выводящий на консоль имена классов для трех переменных класса.
 * Наложить ограничения на параметры типа:
 * T должен реализовать интерфейс Comparable,
 * V должен реализовать интерфейс DataInput и расширять класс InputStream,
 * K должен расширять класс Number
 * @param <T>
 * @param <V>
 * @param <K>
 */

public class Solution<T extends Comparable<T>, V extends InputStream & DataInput, K extends Number> {
    private T t;
    private V v;
    private K k;

    public Solution(T t, V v, K k) {
        this.k = k;
        this.t = t;
        this.v = v;

    }

    public T getT() {
        return t;
    }

    public V getV() {
        return v;
    }

    public K getK() {
        return k;
    }

    public void getClassName() {
        System.out.println(String.format("t= $s, v= $s, k = %s",
                t.getClass().getSimpleName(), v.getClass().getSimpleName(), k.getClass().getSimpleName()));
    }
}
