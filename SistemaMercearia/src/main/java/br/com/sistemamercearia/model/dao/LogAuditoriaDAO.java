/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.sistemamercearia.model.dao;

import br.com.sistemamercearia.util.DatabaseConnection;
import br.com.sistemamercearia.model.entity.LogAuditoria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author elivan
 */
public class LogAuditoriaDAO {
  private Connection connection;

  public LogAuditoriaDAO() throws SQLException {
    connection = DatabaseConnection.getConnection();
  }

  public void inserirLog(LogAuditoria log) throws SQLException {
    String sql = "INSERT INTO LogAuditoria (id_usuario, acao, descricao, data_hora) VALUES (?, ?, ?, ?)";

    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
      stmt.setLong(1, log.getIdUsuario());
      stmt.setString(2, log.getAcao());
      stmt.setString(3, log.getDescricao());
      stmt.setTimestamp(4, Timestamp.valueOf(log.getDataHora()));

      stmt.executeUpdate();

    }
  }

  public List<LogAuditoria> listarLogsPorPeriodo(LocalDateTime inicio, LocalDateTime fim) throws SQLException {
    List<LogAuditoria> logs = new ArrayList<>();

    String sql = "SELECT * FROM LogAuditoria WHERE data_hora BETWEEN ? AND ? ORDER BY data_hora";

    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
      stmt.setTimestamp(1, Timestamp.valueOf(inicio));
      stmt.setTimestamp(2, Timestamp.valueOf(fim));

      try (ResultSet rs = stmt.executeQuery()) {
        while (rs.next()) {

          LogAuditoria log = new LogAuditoria(
              rs.getLong("id_usuario"),
              rs.getString("acao"),
              rs.getString("descricao"),
              rs.getTimestamp("data_hora").toLocalDateTime());

          logs.add(log);
        }
      }
    }
    return logs;
  }
}
