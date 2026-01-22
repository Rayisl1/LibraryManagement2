package kz.aitu.Library.entities;

public class Book {
    private String id;
    private String title;
    private String author;
    private Integer year;
    private Integer borrowedByMemberId = null;

    public Book() {} // Пустой конструктор для JSON

    // Методы для логики Library.java
    public boolean isAvailable() { return borrowedByMemberId == null; }
    public void borrow(Integer memberId) { this.borrowedByMemberId = memberId; }
    public void giveBack() { this.borrowedByMemberId = null; }

    // Геттеры и сеттеры
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    public Integer getYear() { return year; }
    public void setYear(Integer year) { this.year = year; }
    public Integer getBorrowedByMemberId() { return borrowedByMemberId; }
}