package FominaKat.CollectionTask;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * В рамках выполнения задачи необходимо:
 * 1. Создайте коллекцию мужских и женских имен с помощью интерфейса List
 * 2. Отсортируйте коллекцию в алфавитном порядке
 * 3. Отсортируйте коллекцию по количеству букв в слове
 * 4. Разверните коллекцию
 */
public class TaskLists {
    public static void main(String[] args) {
        List<String> list = createList();

        System.out.println(sortByLength(list));
        System.out.println(list);
        System.out.println(reverseList(list));
    }

    private static List<String> reverseList(List<String> list) {
        // не работает
        return list.stream().sorted(Comparator.reverseOrder()).toList();
    }

    private static List<String> createList() {
        List<String> list = new ArrayList<>();
        list.add("tony");
        list.add("adamat");
        list.add("Maryanna");
        list.add("Alena");
        list.add("Don");
        list.add("Yn");
        return list;
    }

    private static List<String> sortByAlpha(List<String> list) {
        list.sort(null);
        return list;
    }

    private static List<String> sortByLength(List<String> list) {
        return list.stream().sorted((e1, e2) -> Integer.compare(e1.length(), e2.length())).toList();
    }

}
