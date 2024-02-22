package FominaKat.CollectionTask;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Создайте коллекцию мужских и женских имен
 * Получите уникальный список Set на основани
 * Определите наименьший элемент (алфавитный
 * Определите наибольший элемент (по количест
 * Удалите все элементы содержащие букву ‘A’
 */
public class TaskSet {
    public static void main(String[] args) {
        List<String> list = createList();

        HashSet<String> set = new HashSet<>(list);

        System.out.println(firstSet(set));
        System.out.println(cut(set));

    }

    private static String firstSet(HashSet<String> set) {
        TreeSet<String> tree = new TreeSet<>(set);
        return tree.first();

    }

    private static String maxElement(Set<String> set) {
        return set.stream()
                .min((e1, e2) -> e1.compareTo(e2))
                .orElse("empty").toString();
    }

    private static String getLong(Set<String> set) {
        return set.stream()
                .max((e1, e2) -> e1.length() - e2.length())
                .orElse("empty").toString();
    }

    private static Set<String> cut (Set<String> set){
        return set.stream()
                .filter(s -> !s.toLowerCase().contains("a"))
                .collect(Collectors.toSet());
    }

    private static List<String> createList() {
        List<String> list = new ArrayList<>();
        list.add("tony");
        list.add("adamat");
        list.add("adamat");
        list.add("Maryanna");
        list.add("Alena");
        list.add("Don");
        list.add("Don");
        list.add("Don");
        list.add("Yn");
        return list;
    }


}
