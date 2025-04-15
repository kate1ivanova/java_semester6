


package org.example;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс для чтения данных о людях из CSV-файла
 */
public class CSVFileReader {

    /**
     * Читает список людей из CSV-файла и преобразует их в объекты Person
     *
     * @param csvFilePath путь к CSV-файлу в ресурсах
     * @param separator   разделитель, используемый в CSV-файле
     * @return список объектов Person, прочитанных из файла
     */
    public List<Person> readPeopleFromCSV(String csvFilePath, char separator) {
        List<Person> people = new ArrayList<>();

        try (InputStream in = getClass().getClassLoader().getResourceAsStream(csvFilePath);
             InputStreamReader inputStreamReader = new InputStreamReader(in);
             CSVReader reader = in == null ? null : new CSVReaderBuilder(inputStreamReader)
                     .withCSVParser(new com.opencsv.CSVParserBuilder()
                             .withSeparator(separator)
                             .build())
                     .build()) {

            // Проверка наличия файла
            if (reader == null) {
                throw new Exception("Файл не найден: " + csvFilePath);
            }

            // Пропускаем заголовок файла
            reader.readNext();

            // Чтение данных построчно
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                try {
                    if (nextLine.length >= 6) {
                        // Парсинг данных из строки
                        int id = Integer.parseInt(nextLine[0].trim());
                        String name = nextLine[1].trim();
                        String gender = nextLine[2].trim();
                        String birthDate = nextLine[3].trim();
                        String departmentName = nextLine[4].trim();
                        double salary = Double.parseDouble(nextLine[5].trim());

                        // Создание объекта Person и добавление в список
                        Person person = new Person(id, name, gender,
                                new Department(departmentName), salary, birthDate);
                        people.add(person);
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Ошибка парсинга строки: " + String.join(",", nextLine));
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            System.err.println("Ошибка при чтении файла:");
            e.printStackTrace();
        }

        return people;
    }
}


