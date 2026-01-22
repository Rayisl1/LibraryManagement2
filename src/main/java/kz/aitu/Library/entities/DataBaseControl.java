package kz.aitu.Library.entities;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBaseControl {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASS = "lagohe14";

    // --- МЕТОДЫ ДЛЯ КНИГ (Books) ---

    public static void addBook(String id, String title, String author, Integer year) {
        String sql = "INSERT INTO book (id, title, author, year) VALUES (?, ?, ?, ?)";
        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
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
        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, title);
            int rows = stmt.executeUpdate();
            System.out.println(rows > 0 ? "Book deleted: " + title : "No book found");
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public static void updateBookId(String title, Integer newBookId) {
        String sql = "UPDATE book SET id = ? WHERE title = ?";
        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, newBookId);
            stmt.setString(2, title);
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public static List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
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

    // --- МЕТОДЫ ДЛЯ УЧАСТНИКОВ (LibraryMember) ---

    public static List<LibraryMember> getAllMembers() {
        List<LibraryMember> members = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM librarymember ORDER BY id ASC")) {
            while (rs.next()) {
                LibraryMember member = new LibraryMember() {
                    @Override public int getMaxBorrowCount() { return 0; }
                };
                member.setId(rs.getInt("id"));
                member.setName(rs.getString("name"));
                members.add(member);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return members;
    }

    public static boolean addLibraryMember(Integer id, String name) {
        String sql = "INSERT INTO librarymember(id, name) VALUES (?, ?)";
        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.setString(2, name);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    public static boolean deleteLibraryMember(Integer id) {
        String sql = "DELETE FROM librarymember WHERE id = ?";
        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    public static boolean updateLibraryMemberId(String name, Integer id) {
        String sql = "UPDATE librarymember SET name = ? WHERE id = ?";
        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setInt(2, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    // --- МЕТОДЫ ДЛЯ ЖУРНАЛОВ (Magazine) ---

    public static List<Magazine> getAllMagazines() {
        List<Magazine> magazines = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM magazine")) {
            while (rs.next()) {
                Magazine mag = new Magazine();
                mag.setId(rs.getString("id"));
                mag.setTitle(rs.getString("title"));
                mag.setYear(rs.getInt("year"));
                mag.setIssue(rs.getInt("issue"));
                magazines.add(mag);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return magazines;
    }
    public static void printTablesInfoBook() {
        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
             Statement stmt = con.createStatement()) {

            // Вывод информации о книгах
            ResultSet rsBook = stmt.executeQuery("SELECT * FROM book");
            System.out.println("--- Table: book ---");
            while (rsBook.next()) {
                String id = rsBook.getString("id");
                String title = rsBook.getString("title");
                String author = rsBook.getString("author");
                int year = rsBook.getInt("year");
                System.out.println("ID: " + id + ", Title: " + title + ", Author: " + author + ", Year: " + year);
            }

            // Вывод информации об участниках
            ResultSet rsMember = stmt.executeQuery("SELECT * FROM librarymember");
            System.out.println("\n--- Table: librarymember ---");
            while (rsMember.next()) {
                int id = rsMember.getInt("id");
                String name = rsMember.getString("name");
                System.out.println("ID: " + id + ", Name: " + name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // --- МЕТОДЫ ДЛЯ ЖУРНАЛОВ (Magazine) ---

    public static void addMagazine(String id, String title, int year, int issue) {
        String sql = "INSERT INTO magazine (id, title, year, issue) VALUES (?, ?, ?, ?)";
        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, id);
            stmt.setString(2, title);
            stmt.setInt(3, year);
            stmt.setInt(4, issue);
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public static boolean deleteMagazine(String id) {
        String sql = "DELETE FROM magazine WHERE id = ?";
        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    public static boolean updateMagazineTitle(String id, String newTitle) {
        String sql = "UPDATE magazine SET title = ? WHERE id = ?";
        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, newTitle);
            stmt.setString(2, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }
}