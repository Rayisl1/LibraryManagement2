package kz.aitu.Library.entities;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Library {
    private String name;

    // Data pool
    private final List<Book> books = new ArrayList<>();
    private final List<LibraryMember> members = new ArrayList<>();

    public Library(String name) {
        this.name = name;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public void addBook(Book book) { books.add(book); }
    public void addMember(LibraryMember member) { members.add(member); }

    // Search
    public List<Book> searchByTitle(String keyword) {
        List<Book> res = new ArrayList<>();
        String k = keyword.toLowerCase();
        for (Book b : books) {
            if (b.getTitle().toLowerCase().contains(k)) res.add(b);
        }
        return res;
    }

    // Filter
    public List<Book> availableBooks() {
        List<Book> res = new ArrayList<>();
        for (Book b : books) {
            if (b.isAvailable()) res.add(b);
        }
        return res;
    }

    // Sort
    public List<Book> sortByYear() {
        List<Book> copy = new ArrayList<>(books);
        copy.sort(Comparator.comparingInt(Book::getYear));
        return copy;
    }

    public List<Book> sortByTitle() {
        List<Book> copy = new ArrayList<>(books);
        copy.sort(Comparator.comparing(Book::getTitle, String.CASE_INSENSITIVE_ORDER));
        return copy;
    }

    // Borrow / Return logic
    public String borrowBook(int memberId, String bookId) {
        LibraryMember member = null;
        Book book = null;

        for (LibraryMember m : members)
            if (m.getId() == memberId) member = m;

        for (Book b : books)
            if (b.getId().equalsIgnoreCase(bookId)) book = b;

        if (member == null) return "❌ Member not found.";
        if (book == null) return "❌ Book not found.";
        if (!book.isAvailable()) return "⚠️ Book is already borrowed.";

        int count = 0;
        for (Book b : books) {
            if (b.getBorrowedByMemberId() != null &&
                    b.getBorrowedByMemberId().equals(memberId)) {
                count++;
            }
        }

        if (count >= member.getMaxBorrowCount())
            return "⚠️ Borrow limit reached (max " + member.getMaxBorrowCount() + ").";

        book.borrow(memberId);
        return "✅ Book borrowed successfully.";
    }

    public String returnBook(String bookId) {
        for (Book b : books) {
            if (b.getId().equalsIgnoreCase(bookId) && !b.isAvailable()) {
                b.giveBack();
                return "↩️ Book returned successfully.";
            }
        }
        return "❌ Book not found or already available.";
    }
}
