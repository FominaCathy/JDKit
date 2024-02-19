package FominaKat.Calculator;

public class Calculator {

    public static <T extends Number> double sum(T a, T b) {
        return a.doubleValue() + b.doubleValue();
    }

    public static <T extends Number> double subtract(T a, T b) {
        return a.doubleValue() - b.doubleValue();
    }

    public static <T extends Number> double multiply(T a, T b) {
        return a.doubleValue() * b.doubleValue();
    }

    public static <T extends Number> double divide(T a, T b) {
        if (b.doubleValue() == 0) {
            throw new ArithmeticException("division by zero");
        }
        return a.doubleValue() / b.doubleValue();
    }

}
