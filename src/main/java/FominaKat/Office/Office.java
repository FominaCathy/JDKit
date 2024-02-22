package FominaKat.Office;

import java.util.List;

public class Office {
    private static ListEmployee office;

    public static void main(String[] args) {
        office = new ListEmployee();
        Employee tonyM = new Employee("Tony", "123", 5);
        Employee tonyJ = new Employee("Tony", "15666", 1);
        Employee alex = new Employee("Alex", "12354", 12);
        Employee lulu = new Employee("Lulu", "6999", 3);

        // добавление сотрудника
        addEmployee(tonyJ);
        addEmployee(tonyM);
        addEmployee(tonyM);
        addEmployee(alex);
        addEmployee(lulu);
        addEmployee(lulu);

        //выборка сотрудников по стажу
        System.out.println("сотрудники со стажем от 5ти лет");
        printListEmployee(office.getEmployeeByExperience(5));
        System.out.println("сотрудники со стажем до 5ти лет");
        printListEmployee(office.getEmployeeByExperience(0, 4));

        // выборка "номер телефона сотрудника по имени "
        System.out.println("список телефонов сотрудников с именем Tony: ");
        office.getPhoneByNameEmployee("Tony").forEach(System.out::println);

        //сотрудника по табельному номеру
        int tabNumber = 3;
        System.out.printf("сотрудник с таб.номером = %d\n", tabNumber);
        System.out.println(office.getEmployeeBySN(tabNumber).toString());
    }

    private static void addEmployee(Employee employee) {
        try {
            office.addEmployee(employee);

        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void printListEmployee(List<Employee> list) {
        list.forEach(employee -> System.out.println(employee.toString() + " - стаж: " + employee.getExperience()));
    }
}
