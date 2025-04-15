package org.example;

import java.util.UUID;

/**
 * Класс, представляющий департамент/отдел в организации.
 * Содержит уникальный идентификатор и название отдела.
 */
public class Department {

    private final UUID id;      // Уникальный идентификатор отдела
    private final String name;  // Название отдела

    /**
     * Создает новый департамент с указанным названием.
     * Уникальный ID генерируется автоматически.
     *
     * @param name название создаваемого департамента
     */
    public Department(String name) {
        this.id = UUID.randomUUID();
        this.name = name;
    }

    /**
     * Возвращает название департамента.
     *
     * @return строку с названием департамента
     */
    public String getName() {
        return name;
    }

    /**
     * Возвращает уникальный идентификатор департамента.
     *
     * @return объект UUID, представляющий идентификатор
     */
    public UUID getID() {
        return id;
    }
}