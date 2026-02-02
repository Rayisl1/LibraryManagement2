package kz.aitu.Library.entities.librarymembers;

import java.util.Objects;

public class LibraryMember {
    private int id;
    private String name;

    public LibraryMember() {
    }

    public LibraryMember(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() { return id; }
    public String getName() { return name; }


    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }

    public int getMaxBorrowCount() {
        return 5;
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