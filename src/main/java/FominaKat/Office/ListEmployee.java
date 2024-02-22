package FominaKat.Office;

import java.util.ArrayList;
import java.util.List;

public class ListEmployee {
    private List<Employee> employeeList;


    public ListEmployee() {
        this.employeeList = new ArrayList<>();
    }

    //    метод добавление нового сотрудника в справочник
    public void addEmployee(Employee employee) {
        if (employeeList.contains(employee)) {
            throw new IllegalArgumentException(String.format("сотрудник: %s, уже есть в справочнике\n", employee.toString()));
        } else {
            employeeList.add(employee);
        }
    }

//    метод, который ищет сотрудника по стажу (может быть список)

    /**
     * метод возвращает список сотрудников, со стажем в указанном диапазоне, включая граничные значения
     *
     * @param min - минимальный стаж
     * @param max - максимальны стаж
     * @return - список сотрудников
     */
    public List<Employee> getEmployeeByExperience(int min, int max) {
        return employeeList.stream()
                .filter(emp -> (emp.getExperience() >= min && emp.getExperience() <= max))
                .toList();
    }

    /**
     * метод возвращает список сотрудников, со стажем, более или равно указанному
     *
     * @param min - минимальный стаж
     * @return - список сотрудников
     */
    public List<Employee> getEmployeeByExperience(int min) {
        return employeeList.stream()
                .filter(emp -> (emp.getExperience() >= min))
                .toList();
    }

    //    метод, который возвращает номер телефона сотрудника по имени (может быть список)
    public List<String> getPhoneByNameEmployee(String name) {
        return employeeList.stream()
                .filter(emp -> emp.getName().equals(name))
                .map(emp -> emp.getPhone())
                .toList();


    }

    //    метод, который ищет сотрудника по табельному номеру
    public Employee getEmployeeBySN(int sNumber) {
        return employeeList.stream()
                .filter(emp -> emp.getServiceNumber() == sNumber)
                .findFirst().get();
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }
}
