package kz.aitu.Library.repository;

import kz.aitu.Library.config.DbConfig;
import kz.aitu.Library.entities.librarymembers.LibraryMember;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberRepository {

    public static List<LibraryMember> getAllMembers() {
        List<LibraryMember> members = new ArrayList<>();
        try (Connection con = DbConfig.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM librarymember ORDER BY id ASC")) {
            while (rs.next()) {
                LibraryMember member = new LibraryMember();
                member.setId(rs.getInt("id"));
                member.setName(rs.getString("name"));
                members.add(member);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return members;
    }

    public static boolean addLibraryMember(Integer id, String name) {
        String sql = "INSERT INTO librarymember(id, name) VALUES (?, ?)";
        try (Connection con = DbConfig.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.setString(2, name);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    public static boolean deleteLibraryMember(Integer id) {
        String sql = "DELETE FROM librarymember WHERE id = ?";
        try (Connection con = DbConfig.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    public static boolean updateLibraryMemberId(String name, Integer id) {
        String sql = "UPDATE librarymember SET name = ? WHERE id = ?";
        try (Connection con = DbConfig.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setInt(2, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    public static LibraryMember findMemberById(int id) {
        String sql = "SELECT * FROM librarymember WHERE id = ?";
        try (Connection con = DbConfig.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    LibraryMember member = new LibraryMember();
                    member.setId(rs.getInt("id"));
                    member.setName(rs.getString("name"));
                    return member;
                }
            }
        } catch (SQLException e) {
            System.err.println("Ошибка БД в findMemberById: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}