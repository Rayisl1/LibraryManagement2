package kz.aitu.Library.entities;

import kz.aitu.Library.StudentMember;
import org.jspecify.annotations.Nullable;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBaseControl {
    public static void addBook(String id, String title, String author, Integer year) {
        String connectionURL = "jdbc:postgresql://localhost:5432/postgres";
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            Class.forName("org.postgresql.Driver");

            con = DriverManager.getConnection(connectionURL, "postgres", "lagohe14");

            String sqlCourse = "INSERT INTO book (id, title, author, year) VALUES (?, ?, ?, ?)";
            stmt = con.prepareStatement(sqlCourse);

            stmt.setString(1, id);
            stmt.setString(2, title);
            stmt.setString(3, author);
            stmt.setInt(4, year);

            stmt.executeUpdate();

            System.out.println("Book added: " + title);

        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void deleteBook(String title) {
        String connectionURL = "jdbc:postgresql://localhost:5432/postgres";
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            Class.forName("org.postgresql.Driver");

            con = DriverManager.getConnection(connectionURL, "postgres", "lagohe14");

            // SQL to delete a book by title
            String sqlDelete = "DELETE FROM book WHERE title = ?";
            stmt = con.prepareStatement(sqlDelete);

            stmt.setString(1, title);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Book with title " + title + " deleted.");
            } else {
                System.out.println("No book found with title " + title);
            }

        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void updateBookId(String title, Integer newBookId) {
        String connectionURL = "jdbc:postgresql://localhost:5432/postgres";
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            Class.forName("org.postgresql.Driver");

            con = DriverManager.getConnection(connectionURL, "postgres", "lagohe14");

            // Обновляем книгу по её title, меняем её ID
            String sqlUpdate = "UPDATE book SET id = ? WHERE title = ?";
            stmt = con.prepareStatement(sqlUpdate);

            stmt.setInt(1, newBookId);  // Новый ID
            stmt.setString(2, title);  // Title книги, по которому будем искать

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Book ID updated for book with title '" + title + "' to " + newBookId);
            } else {
                System.out.println("No book found with title '" + title + "'");
            }

        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public static void printTablesInfoBook() {
        String connectionURL = "jdbc:postgresql://localhost:5432/postgres";
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(connectionURL, "postgres", "lagohe14");
            stmt = con.createStatement();

            String sqlBook = "SELECT * FROM book";
            rs = stmt.executeQuery(sqlBook);

            System.out.println("Table: book");
            while (rs.next()) {
                String id = rs.getString("id");
                String title = rs.getString("title");
                String author = rs.getString("author");
                Integer year = rs.getInt("year");
                System.out.println("ID: " + id + ", Title: " + title + ", Author: " + author + ", Year: " + year);
            }

            rs.close();

            String sqlLibraryMember = "SELECT * FROM librarymember";
            rs = stmt.executeQuery(sqlLibraryMember);

            System.out.println("\nTable: librarymember");
            while (rs.next()) {
                Integer id = rs.getInt("id");
                String name = rs.getString("name");
                System.out.println("ID: " + id + ", Name: " + name);
            }
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void addLibraryMember(Integer id, String name) {
        String connectionURL = "jdbc:postgresql://localhost:5432/postgres";
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            Class.forName("org.postgresql.Driver");

            con = DriverManager.getConnection(connectionURL, "postgres", "lagohe14");

            String sqlCourse = "INSERT INTO librarymember(id, name) VALUES (?, ?)";
            stmt = con.prepareStatement(sqlCourse);

            stmt.setInt(1, id);
            stmt.setString(2, name);

            stmt.executeUpdate();

            System.out.println("LibraryMember added: " + name);

        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void deleteLibraryMember(Integer id) {
        String connectionURL = "jdbc:postgresql://localhost:5432/postgres";
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            Class.forName("org.postgresql.Driver");

            con = DriverManager.getConnection(connectionURL, "postgres", "lagohe14");

            String sqlDelete = "DELETE FROM librarymember WHERE id = ?";
            stmt = con.prepareStatement(sqlDelete);

            stmt.setInt(1, id);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("LibraryMember with ID " + id + " deleted.");
            } else {
                System.out.println("No library member found with ID " + id);
            }

        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void updateLibraryMemberId(String name, Integer newId) {
        String connectionURL = "jdbc:postgresql://localhost:5432/postgres";
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            Class.forName("org.postgresql.Driver");

            con = DriverManager.getConnection(connectionURL, "postgres", "lagohe14");

            // Обновляем ID члена библиотеки по имени
            String sqlUpdate = "UPDATE librarymember SET id = ? WHERE name = ?";
            stmt = con.prepareStatement(sqlUpdate);

            stmt.setInt(1, newId);  // Новый ID
            stmt.setString(2, name);  // Имя члена библиотеки, по которому будем искать

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("LibraryMember ID updated for member with name '" + name + "' to " + newId);
            } else {
                System.out.println("No LibraryMember found with name '" + name + "'");
            }

        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public static void printTablesInfoMember() {
        String connectionURL = "jdbc:postgresql://localhost:5432/postgres";
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("org.postgresql.Driver");

            con = DriverManager.getConnection(connectionURL, "postgres", "lagohe14");

            String sqlSelect = "SELECT * FROM librarymember";
            stmt = con.createStatement();
            rs = stmt.executeQuery(sqlSelect);

            System.out.println("Library Member Information:");
            while (rs.next()) {
                Integer id = rs.getInt("id");
                String name = rs.getString("name");

                System.out.println("ID: " + id + ", Name: " + name);
            }

        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
