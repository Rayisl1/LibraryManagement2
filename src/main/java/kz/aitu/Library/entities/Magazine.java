package kz.aitu.Library.entities;

public class Magazine extends LibraryItem {

    private int issue;

    public Magazine(String id, String title, int year, int issue) {
        super(id, title, year);
        this.issue = issue;
    }

    public int getIssue() { return issue; }
    public void setIssue(int issue) { this.issue = issue; }

    @Override
    public int getMaxBorrowDays() {
        return 7;
    }

    @Override
    public String toString() {
        return "Magazine{" + super.toString() + ", issue=" + issue + "}";
    }
}
