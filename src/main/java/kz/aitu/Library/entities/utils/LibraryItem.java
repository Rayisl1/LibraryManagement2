package kz.aitu.Library.entities.utils;

import java.util.Objects;

public abstract class LibraryItem {

    protected String id;
    protected String title;
    protected int year;
    private Integer borrowedByMemberId = null;

    public LibraryItem(String id, String title, int year) {
        this.id = id;
        this.title = title;
        this.year = year;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public int getYear() { return year; }

    public boolean isAvailable() {
        return borrowedByMemberId == null;
    }

    public Integer getBorrowedByMemberId() {
        return borrowedByMemberId;
    }

    public void borrow(int memberId) {
        borrowedByMemberId = memberId;
    }

    public void giveBack() {
        borrowedByMemberId = null;
    }
    //Полиформизм
    public abstract int getMaxBorrowDays();

    @Override
    public String toString() {
        return "id='" + id + "', title='" + title + "', year=" + year +
                ", available=" + isAvailable();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LibraryItem)) return false;
        LibraryItem that = (LibraryItem) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
