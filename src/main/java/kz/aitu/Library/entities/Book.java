package kz.aitu.Library.entities;

import java.util.Objects;

public class Book {
    private final String id;
    private String title;
    private String author;
    private Integer year;

    // If null, available for all
    private Integer borrowedByMemberId = null;

    public Book(String id, String title, String author, Integer year) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public Integer getYear() { return year; }
    public Integer getBorrowedByMemberId() { return borrowedByMemberId; }

    public void setTitle(String title) { this.title = title; }
    public void setAuthor(String author) { this.author = author; }
    public void setYear(Integer year) { this.year = year; }

    public boolean isAvailable() {
        return borrowedByMemberId == null;
    }

    public void borrow(Integer memberId) {
        borrowedByMemberId = memberId;
    }

    public void giveBack() {
        borrowedByMemberId = null;
    }

    @Override
    public String toString() {
        return "Book{id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year +
                ", available=" + isAvailable() +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
