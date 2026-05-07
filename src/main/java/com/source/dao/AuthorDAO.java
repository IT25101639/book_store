package com.source.dao;

import com.source.model.Author;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthorDAO {

    public void addAuthor(Author author) throws SQLException {
        String sql = "INSERT INTO author (author_name, email) VALUES (?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, author.getAuthorName());
            ps.setString(2, author.getEmail());
            ps.executeUpdate();
        }
    }

    public List<Author> getAllAuthors() throws SQLException {
        List<Author> list = new ArrayList<>();
        String sql = "SELECT * FROM author";
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Author a = new Author(
                    rs.getInt("id"),
                    rs.getString("author_name"),
                    rs.getString("email")
                );
                list.add(a);
            }
        }
        return list;
    }

    public void deleteAuthor(int id) throws SQLException {
        String sql = "DELETE FROM author WHERE id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public Author getAuthorById(int id) throws SQLException {
        String sql = "SELECT * FROM author WHERE id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Author(
                        rs.getInt("id"),
                        rs.getString("author_name"),
                        rs.getString("email")
                    );
                }
            }
        }
        return null;
    }

    public void updateAuthor(Author author) throws SQLException {
        String sql = "UPDATE author SET author_name = ?, email = ? WHERE id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, author.getAuthorName());
            ps.setString(2, author.getEmail());
            ps.setInt(3, author.getId());
            ps.executeUpdate();
        }
    }
}
