package kz.aitu.Library.entities.inventory;

import kz.aitu.Library.entities.utils.LibraryItem;

public class Magazine extends LibraryItem {
    private int issue;

    public Magazine() {
        super("", "", 0);
    }

    public Magazine(String id, String title, int year, int issue) {
        super(id, title, year);
        this.issue = issue;
    }

    public int getIssue() { return issue; }
    public void setIssue(int issue) { this.issue = issue; }

    @Override
    public int getMaxBorrowDays() { return 7; }

    @Override
    public String toString() {
        return "Magazine{" + super.toString() + ", issue=" + issue + "}";
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setIssueNumber(int issueNumber) {
    }

    public void setCategory(String category) {

    }
}