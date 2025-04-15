package org.example;

/**
 * Класс, представляющий информацию о человеке.
 * Содержит данные: идентификатор, имя, пол, отдел, зарплату и дату рождения.
 */
public class Person {

    private final int id;            // Уникальный идентификатор
    private final String name;       // Полное имя
    private final String gender;     // Пол
    private final Department department;  // Отдел (департамент)
    private final double salary;     // Зарплата
    private final String birthDate;  // Дата рождения

    /**
     * Создает новый объект Person с заданными параметрами.
     *
     * @param id         уникальный идентификатор сотрудника
     * @param name       полное имя сотрудника
     * @param gender     пол (например, "Male", "Female")
     * @param department объект Department, к которому прикреплен сотрудник
     * @param salary     размер заработной платы
     * @param birthDate  дата рождения в строковом формате
     */
    public Person(int id, String name, String gender, Department department,
                  double salary, String birthDate) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.department = department;
        this.salary = salary;
        this.birthDate = birthDate;
    }

    /**
     * Возвращает строковое представление объекта Person.
     * Формат: Person{id=..., name='...', gender='...', department='...', salary=..., birthDate='...'}
     *
     * @return строковое представление
     */
    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", department='" + department.getName() + '\'' +
                ", salary=" + salary +
                ", birthDate='" + birthDate + '\'' +
                '}';
    }


    /**
     * @return уникальный идентификатор сотрудника
     */
    public int getId() {
        return id;
    }

    /**
     * @return полное имя сотрудника
     */
    public String getName() {
        return name;
    }

    /**
     * @return пол сотрудника
     */
    public String getGender() {
        return gender;
    }

    /**
     * @return объект Department, к которому прикреплен сотрудник
     */
    public Department getDepartment() {
        return department;
    }

    /**
     * @return размер заработной платы
     */
    public double getSalary() {
        return salary;
    }

    /**
     * @return дата рождения в строковом формате
     */
    public String getBirthDate() {
        return birthDate;
    }
}