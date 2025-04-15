

import org.example.Department;
import org.example.Person;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    @Test
    void testPersonCreation() {
        Department department = new Department("IT");
        Person person = new Person(1, "Иван Иванов", "М", department, 100000, "1990-01-01");

        assertEquals(1, person.getId());
        assertEquals("Иван Иванов", person.getName());
        assertEquals("IT", person.getDepartment().getName());
    }

    @Test
    void testToString() {
        Department department = new Department("Бухгалтерия");
        Person person = new Person(2, "Мария Петрова", "Ж", department, 90000, "1985-05-15");

        String expected = "Person{id=2, name='Мария Петрова', gender='Ж', " +
                "department='Бухгалтерия', salary=90000.0, birthDate='1985-05-15'}";
        assertEquals(expected, person.toString());
    }
}