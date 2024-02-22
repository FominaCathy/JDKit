package FominaKat.Office;

/**
 * класс сотрудник. содержит информацию о сотруднике
 */
public class Employee {
    private int serviceNumber = 0;
    private String name;
    private String phone;
    private int experience;

    private static int count = 0;

    public Employee(String name, String phone, int experience) {
        this.name = name;
        this.phone = phone;
        this.experience = experience;
        this.serviceNumber = ++count;
    }

    public int getServiceNumber() {
        return serviceNumber;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public int getExperience() {
        return experience;
    }

    @Override
    public String toString() {
        return "(SN: " + serviceNumber + ") " + name;
    }
}
