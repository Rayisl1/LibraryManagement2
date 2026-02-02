package kz.aitu.Library.entities.inventory;

import kz.aitu.Library.entities.librarymembers.LibraryMember;

public class Book {
    private String id;
    private String title;
    private String author;
    private Integer year;
    private Integer borrowedByMemberId = null;

    public Book() {}

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

    public static class StudentMember extends LibraryMember {
        public StudentMember(int id, String name) {
            super(id, name);
        }

        @Override
        public int getMaxBorrowCount() {
            return 3;
        }
    }
}