package kz.aitu.Library.entities;

import java.util.Objects;

// Убрали слово abstract, теперь это обычный класс
public class LibraryMember {
    private int id; // Убрали final, чтобы работал сеттер
    private String name;

    // ОБЯЗАТЕЛЬНО: Пустой конструктор для JDBC и Spring
    public LibraryMember() {
    }

    public LibraryMember(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() { return id; }
    public String getName() { return name; }

    // Сеттеры необходимы для метода getAllMembers в DataBaseControl
    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }

    // Теперь это обычный метод вместо абстрактного
    public int getMaxBorrowCount() {
        return 5; // Значение по умолчанию
    }

    @Override
    public String toString() {
        return "LibraryMember{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", maxBorrow=" + getMaxBorrowCount() +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LibraryMember)) return false;
        LibraryMember that = (LibraryMember) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}