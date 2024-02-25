package FominaKat.Philosophers;

import FominaKat.Philosophers.Dinner.Dinner;

public class Main {

    public static void main(String[] args) {
        Dinner dinner = new Dinner(5);
        dinner.start();
   }
}
