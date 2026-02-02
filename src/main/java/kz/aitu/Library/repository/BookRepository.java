package kz.aitu.Library.repository;

import kz.aitu.Library.config.DbConfig;
import kz.aitu.Library.entities.inventory.Book;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class BookRepository {

    public static void addBook(String id, String title, String author, Integer year) {
        String sql = "INSERT INTO book (id, title, author, year) VALUES (?, ?, ?, ?)";
        try (Connection con = DbConfig.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, id);
            stmt.setString(2, title);
            stmt.setString(3, author);
            stmt.setInt(4, year);
            stmt.executeUpdate();
            System.out.println("Book added: " + title);
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public static void deleteBook(String title) {
        String sql = "DELETE FROM book WHERE title = ?";
        try (Connection con = DbConfig.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, title);
            int rows = stmt.executeUpdate();
            System.out.println(rows > 0 ? "Book deleted: " + title : "No book found");
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public static void updateBookId(String title, Integer newBookId) {
        String sql = "UPDATE book SET id = ? WHERE title = ?";
        try (Connection con = DbConfig.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, newBookId);
            stmt.setString(2, title);
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public static List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        try (Connection con = DbConfig.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM book")) {
            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getString("id"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setYear(rs.getInt("year"));
                books.add(book);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return books;
    }

    public static Book findBookById(String id) {
        String sql = "SELECT * FROM book WHERE id = ?";
        try (Connection con = DbConfig.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Book book = new Book();
                    book.setId(rs.getString("id"));
                    book.setTitle(rs.getString("title"));
                    book.setAuthor(rs.getString("author"));
                    book.setYear(rs.getInt("year"));
                    return book;
                }
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }
}