package org.example;


import java.util.List;


public class Main {
    public static void main(String[] args) {
        CSVFileReader csvReader = new CSVFileReader();
        try {

            List<Person> people = csvReader.readPeopleFromCSV("foreign_names.csv", ';');
            // Выводим результат
            for (Person person : people) {
                System.out.println(person);
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}