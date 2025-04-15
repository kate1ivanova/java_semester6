import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.example.CSVFileReader;
import org.example.Person;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Тесты для класса {@link CSVFileReader}
 */
public class CSVFileReaderTest {

    /**
     * Проверяет чтение людей из корректного CSV файла.
     */
    @Test
    public void testReadPeopleFromCSV() throws Exception {
        CSVFileReader csvFileReader = new CSVFileReader();
        String csvFilePath = "resTest/resTests.csv";

        List<Person> people = csvFileReader.readPeopleFromCSV(csvFilePath, ';');

        assertNotNull(people, "Список людей не должен быть null");
        assertEquals(1, people.size(), "Ожидается 1 человек в списке");

        Person firstPerson = people.get(0);
        assertEquals(28281, firstPerson.getId());
        assertEquals("Aahan", firstPerson.getName());
        assertEquals("Male", firstPerson.getGender());
        assertEquals("15.05.1970", firstPerson.getBirthDate());
        assertEquals("I", firstPerson.getDepartment().getName());
        assertEquals(4800.0, firstPerson.getSalary(), 0.01);
    }

    /**
     * Проверяет поведение при неверном разделителе — должен вернуться пустой список.
     */
    @Test
    public void testReadPeopleFromCSV_InvalidSeparator() throws Exception {
        CSVFileReader csvFileReader = new CSVFileReader();
        String csvFilePath = "resTest/resTests.csv";

        List<Person> people = csvFileReader.readPeopleFromCSV(csvFilePath, ':');

        // Ожидаем пустой список
        assertTrue(people.isEmpty(), "Список должен быть пустым при неверном разделителе");
    }
}