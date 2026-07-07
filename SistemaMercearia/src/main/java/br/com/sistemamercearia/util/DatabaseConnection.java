/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.sistemamercearia.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author elivan
 */
public class DatabaseConnection {

  private static final String URL = "jdbc:mysql://localhost:3306/smart_mercearia";
  private static final String USER = "root";
  private static final String PASSWORD = "";

  public static Connection getConnection() throws SQLException {
    try {
      Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
      return connection;
    } catch (Exception e) {
      throw new SQLException("Erro ao conectar ao banco de dados: " + e.getMessage(), e);
    }
  }

  public static void closeConnection(Connection conn) {
    if (conn != null) {
      try {
        conn.close();
      } catch (SQLException e) {
        throw new RuntimeException("Erro ao fechar a conexão com o banco de dados: " + e.getMessage(), e);
      }
    }
  }

  public static void closeConnection(Connection conn, Statement stmt) {
    try {
      if (stmt != null) {
        stmt.close();
      }
    } catch (SQLException e) {
      throw new RuntimeException("Erro ao fechar o statement: " + e.getMessage(), e);
    } finally {
      closeConnection(conn);
    }
  }

  public static void closeConnection(Connection conn, Statement stmt, ResultSet rs) {
    try {
      if (rs != null) {
        rs.close();
      }
    } catch (SQLException e) {
      throw new RuntimeException("Erro ao fechar o result set: " + e.getMessage(), e);
    } finally {
      closeConnection(conn, stmt);
    }
  }
}
