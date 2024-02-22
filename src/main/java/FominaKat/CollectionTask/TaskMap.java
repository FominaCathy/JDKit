package FominaKat.CollectionTask;

import java.util.HashMap;
import java.util.Map;

/**
 * 1. Создайте телефонный справочник с помощью Map - телефон это ключ, а имя значение
 * 2. Найдите человека с самым маленьким номером телефона
 * 3. Найдите номер телефона человека чье имя самое большое в алфавитном порядке
 */
public class TaskMap {

    public static void main(String[] args) {
        HashMap<String, String> phoneDict = createDict();

        System.out.println((getMinPhone(phoneDict)));
        System.out.println(getPhone(phoneDict));
    }

    private static HashMap<String, String> createDict() {
        HashMap<String, String> phone = new HashMap<>();
        phone.put("123", "Ivan");
        phone.put("125", "Yan");
        phone.put("5387", "Tosya");
        phone.put("4245", "Sonya");
        phone.put("126", "Tony");
        phone.put("753", "Don");
        return phone;
    }

    private static String getMinPhone(HashMap<String, String> dict) {
//        String key = dict.keySet().stream()
//                .min((e1, e2)-> (Integer.parseInt(e1) - Integer.parseInt(e2)))
//                .get();
//        return dict.get(key);
        return dict.entrySet().stream()
                .min((e1, e2) -> (Integer.parseInt(e1.getKey()) - Integer.parseInt(e2.getKey())))
                .get().getValue();

    }

    private static String getPhone(HashMap<String, String> dict){
        return dict.entrySet().stream()
                .max((e1, e2) -> e1.getValue().compareToIgnoreCase(e2.getValue()))
                .get().getKey();
    }
}
