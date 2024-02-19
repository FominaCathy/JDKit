package FominaKat.Generics;

import java.util.Arrays;
import java.util.Iterator;

/**
 * Описать собственную коллекцию – список на основе массива.
 * Коллекция должна иметь возможность хранить любые типы данных, иметь методы добавления и удаления элементов.
 * Написать итератор по массиву. Итератор – это объект, осуществляющий движение по коллекциям любого типа,
 * содержащим любые типы данных. Итераторы обычно имеют только два метода – проверка на наличие
 * следующего элемента и переход к следующему элементу. Но также, особенно в других языках программирования,
 * возможно встретить итераторы, реализующие дополнительную логику.
 */
public class ArtCollect<T> implements Iterable<T> {


    private Object[] arr;
    private int count;

    public ArtCollect(int sizeCollect) {
        arr = new Object[sizeCollect];
    }

    public ArtCollect() {
        this(10);
    }

    public void add(T item) {
        if (count == arr.length) {
            arr = Arrays.copyOf(arr, 2 * arr.length);
        }
        arr[count++] = item;
    }

    public T get(int i) {
        if ((i > count) || (i < 0)) {
            return null;
        }
        return (T) arr[i];
    }

    public void delete(int pos) {
        if ((pos < count) && (pos >= 0)) {
            for (int i = pos; i < count - 1; i++) {
                arr[i] = arr[i + 1];
            }
            count--;
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Collect: ");
        for (int i = 0; i < count; i++) {
            stringBuilder.append(arr[i]).append(" ");
        }
        return stringBuilder.toString();
    }


    @Override
    public Iterator<T> iterator() {

        return new Iterator<T>() {
            private int pos = 0;

            @Override
            public boolean hasNext() {
                return pos < count;
            }

            @Override
            public T next() {
                return (T) arr[pos++];
            }
        };
    }
}
