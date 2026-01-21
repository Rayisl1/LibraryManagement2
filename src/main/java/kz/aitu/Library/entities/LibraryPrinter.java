package kz.aitu.Library.entities;

public class LibraryPrinter {

    public static void line() {
        System.out.println("----------------------------------");
    }

    public static void printBooks(String title, java.util.List<Book> books) {
        line();
        System.out.println(title);
        line();
        if (books.isEmpty()) {
            System.out.println("No books found.");
        } else {
            for (Book b : books) {
                System.out.println("• " + b);
            }
        }
    }
}
