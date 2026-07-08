/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.sistemamercearia.model.dao;

import br.com.sistemamercearia.util.DatabaseConnection;
import br.com.sistemamercearia.model.entity.Usuario;
import br.com.sistemamercearia.model.enums.PerfilUsuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author elivan
 */

public class UsuarioDAO {
  private Connection connection;

  public UsuarioDAO() {
    try {
            this.connection = DatabaseConnection.getConnection();
        } catch (SQLException e) {
            // Registra o erro e interrompe a execução de forma limpa
            throw new RuntimeException("Falha crítica ao conectar com o banco de dados no DAO.", e);
        }
    }

  public void salvar(Usuario usuario) throws SQLException {
    if (usuario == null) {
      throw new IllegalArgumentException("Usuário não pode ser nulo!");
    }

    String sql = "INSERT INTO Usuario (nome, login, senha, perfil) VALUES (?, ?, ?, ?)";

    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
      stmt.setString(1, usuario.getNome());
      stmt.setString(2, usuario.getLogin());
      stmt.setString(3, usuario.getSenha());
      stmt.setString(4, usuario.getPerfilUsuario().name());

      stmt.executeUpdate();
    }
  }

  public Usuario buscarPorLogin(String login) throws SQLException {
    String sql = "SELECT * FROM Usuario WHERE login = ?";

    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
      stmt.setString(1, login);

      try (ResultSet rs = stmt.executeQuery()) {
        if (rs.next()) {
          Usuario usuario = new Usuario();
          usuario.setId(rs.getLong("id"));
          usuario.setNome(rs.getString("nome"));
          usuario.setLogin(rs.getString("login"));
          usuario.setSenha(rs.getString("senha"));
          usuario.setPerfilUsuario(PerfilUsuario.valueOf(rs.getString("perfil")));

          return usuario;
        }
      }
    }

    return null;
  }

  public void fecharConexao() {
    DatabaseConnection.closeConnection(connection);
  }
}
