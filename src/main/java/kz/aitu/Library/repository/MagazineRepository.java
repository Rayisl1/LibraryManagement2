package kz.aitu.Library.repository;

import kz.aitu.Library.config.DbConfig;
import kz.aitu.Library.entities.inventory.Magazine;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MagazineRepository {

    public static void addMagazine(String id, String title, int year, int issue) {
        String sql = "INSERT INTO magazine (id, title, year, issue) VALUES (?, ?, ?, ?)";
        try (Connection con = DbConfig.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, id);
            stmt.setString(2, title);
            stmt.setInt(3, year);
            stmt.setInt(4, issue);
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public static List<Magazine> getAllMagazines() {
        List<Magazine> magazines = new ArrayList<>();
        try (Connection con = DbConfig.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM magazine")) {
            while (rs.next()) {
                Magazine mag = new Magazine();
                mag.setId(rs.getString("id"));
                mag.setTitle(rs.getString("title"));
                mag.setYear(rs.getInt("year"));
                mag.setIssueNumber(rs.getInt("issue"));
                magazines.add(mag);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return magazines;
    }

    public static boolean deleteMagazine(String id) {
        String sql = "DELETE FROM magazine WHERE id = ?";
        try (Connection con = DbConfig.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    public static boolean updateMagazineTitle(String id, String newTitle) {
        String sql = "UPDATE magazine SET title = ? WHERE id = ?";
        try (Connection con = DbConfig.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, newTitle);
            stmt.setString(2, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    public static Magazine findMagazineById(String id) {
        String sql = "SELECT * FROM magazine WHERE id = ?";
        try (Connection con = DbConfig.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Magazine magazine = new Magazine();
                    magazine.setId(rs.getString("id"));
                    magazine.setTitle(rs.getString("title"));
                    magazine.setIssueNumber(rs.getInt("issue"));
                    magazine.setYear(rs.getInt("year"));
                    return magazine;
                }
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }
}