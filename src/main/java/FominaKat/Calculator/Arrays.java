package FominaKat.Calculator;

/**
 * Напишите обобщенный метод compareArrays(), который принимает два массива
 * и возвращает true, если они одинаковые, и false в противном случае.
 * Массивы могут быть любого типа данных, но должны иметь одинаковую длину
 * и содержать элементы одного типа по парно по индексам.
 * То есть тип элемента в первом массиве под нулевым индексом такой же как тип элемента во втором массиве под
 * нулевым индексом (и под всеми остальными индексами аналогично)
 */

public class Arrays{

    static <T, V> boolean compareArrays(T[] first, V[] second) {

        if (first.length != second.length) {
            return false;
        } else {
            int sizeArray = first.length;
            for (int i = 0; i < sizeArray; i++) {
                if (first[i].getClass() != second[i].getClass()) {
                    return false;
                }
            }
        }

        return true;
    }
}
