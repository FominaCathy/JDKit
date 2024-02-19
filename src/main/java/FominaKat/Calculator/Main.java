package FominaKat.Calculator;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        checkCalculator();
        compareArraysExample();
        checkPair();
    }

    private static void checkCalculator() {
        System.out.println(Calculator.sum(1.235, 2.0f));
        System.out.println(Calculator.subtract(1.235, 2f));
        System.out.println(Calculator.multiply(1.235, 2f));
        System.out.println(Calculator.divide(1, 0.01f));
        System.out.println(Calculator.divide(1, 0.0f));
    }

    private static void compareArraysExample() {
        ArrayList<Integer> intArr = new ArrayList<>();
        ArrayList<Integer> intArrDouble = new ArrayList<>();
        ArrayList<String> strArr = new ArrayList<>();

        Object[] arr = {"string", 5, intArr};
        Object[] arr2 = {"tu-tu", 52, intArrDouble};
        Object[] arr3 = {"tu-tu", 52, strArr};
        Object[] arr4 = {"tu-tu", 52};
        Object[] arr5 = {52, "tu-tu", intArrDouble}; // похож на arr2, но другой порядок

        System.out.println(Arrays.compareArrays(arr, arr2));
        System.out.println(Arrays.compareArrays(arr, arr3));
        System.out.println(Arrays.compareArrays(arr, arr4)); //разные размеры
        System.out.println(Arrays.compareArrays(arr2, arr5)); //разный порядок элементов


    }

    private static void checkPair() {
        Pair<Integer, String> stringPair = new Pair<>(5, "tu-tu");
        System.out.println(stringPair);
        Pair<Cat, Integer> catPair = new Pair<>(new Cat("Kuzma"), 5);
        System.out.println(catPair);
        System.out.println(catPair.getFirst());
    }
}
