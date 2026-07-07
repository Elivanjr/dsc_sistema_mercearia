/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.sistemamercearia.model.dao;

import br.com.sistemamercearia.model.entity.Caixa;
import br.com.sistemamercearia.model.entity.MovimentacaoCaixa;
import br.com.sistemamercearia.model.enums.OrigemMovimentacao;
import br.com.sistemamercearia.model.enums.StatusCaixa;
import br.com.sistemamercearia.model.enums.TipoMovimentacao;
import br.com.sistemamercearia.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

/**
 *
 * @author elivan
 */
public class CaixaDAO {
  private Connection connection;

  public CaixaDAO() {
    try {
      connection = DatabaseConnection.getConnection();
    } catch (SQLException e) {
      throw new RuntimeException("Erro ao conectar ao banco de dados.", e);
    }
  }

  public void abrirTurno(Caixa caixa) {
    String sql = """
        INSERT INTO Caixa(id_usuario_abertura, data_hora_abertura, valor_troco_inicial,
        status_caixa, saldo_minimo_proximo_dia) VALUES (?, ?, ?, ?, ?);
        """;
    try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

      stmt.setLong(1, caixa.getIdUsuarioAbertura());
      stmt.setTimestamp(2, Timestamp.valueOf(caixa.getDataHoraAbertura()));
      stmt.setDouble(3, caixa.getValorTrocoInicial());
      stmt.setString(4, caixa.getStatusCaixa().name());
      stmt.setDouble(5, caixa.getSaldoMinimoProximoDia());
      stmt.executeUpdate();

    } catch (SQLException e) {
      throw new RuntimeException("Erro ao abrir turno.", e);
    }
  }

  public void fecharTurno(Caixa caixa) {
    String sql = """
        UPDATE Caixa SET
        data_hora_fechamento = ?,
        status_caixa = ?
        WHERE id = ?
        """;
    try (PreparedStatement stmt = connection.prepareStatement(sql)) {

      stmt.setTimestamp(1, Timestamp.valueOf(caixa.getDataHoraFechamento()));
      stmt.setString(2, caixa.getStatusCaixa().name());
      stmt.setLong(3, caixa.getId());
      stmt.executeUpdate();

    } catch (SQLException e) {
      throw new RuntimeException("Erro ao fechar turno.", e);
    }
  }

  public void registrarMovimentacao(MovimentacaoCaixa mov) {
    String sql = """
        INSERT INTO MovimentacaoCaixa(
        id_caixa, tipo, valor, origem,
        descricao, data_hora_movimentacao
        )
        VALUES (?, ?, ?, ?, ?, ?)
        """;
    try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

      stmt.setLong(1, mov.getIdCaixa());
      stmt.setString(2, mov.getTipoMovimentacao().name());
      stmt.setDouble(3, mov.getValor());
      stmt.setString(4, mov.getOrigem().name());
      stmt.setString(5, mov.getDescricao());
      stmt.setTimestamp(6, Timestamp.valueOf(mov.getDataHoraMovimentacao()));

      stmt.executeUpdate();

    } catch (SQLException e) {
      throw new RuntimeException("Erro ao registrar movimentação.", e);
    }
  }

  public Caixa buscarCaixaAbertoUsuario(long idUsuario) {
    String sql = """
        SELECT * FROM Caixa WHERE id_usuario_abertura = ?
        AND status_caixa = ?
        AND data_hora_fechamento IS NULL
        """;
    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
      stmt.setLong(1, idUsuario);
      stmt.setString(2, StatusCaixa.ABERTO.name());

      try (ResultSet rs = stmt.executeQuery()) {
        if (rs.next()) {
          Caixa caixa = new Caixa();
          caixa.setId(rs.getLong("id"));
          caixa.setDataHoraAbertura(rs.getTimestamp("data_hora_abertura").toLocalDateTime());
          caixa.setValorTrocoInicial(rs.getDouble("valor_troco_inicial"));
          caixa.setStatusCaixa(StatusCaixa.valueOf(rs.getString("status_caixa")));
          caixa.setSaldoMinimoProximoDia(rs.getDouble("saldo_minimo_proximo_dia"));
          caixa.setIdUsuarioAbertura(rs.getLong("id_usuario_abertura"));
          return caixa;
        }
      } catch (SQLException e) {
        throw new RuntimeException("Erro ao buscar caixa aberto.", e);
      }
    } catch (SQLException e) {
      throw new RuntimeException("Erro ao buscar caixa aberto.", e);
    }
    return null;
  }

  public List<MovimentacaoCaixa> listarMovimentacoes(long idCaixa, LocalDate data) {
    List<MovimentacaoCaixa> movimentacoes = new ArrayList<>();

    String sql = """
        SELECT * FROM MovimentacaoCaixa
        WHERE id_caixa = ?
        AND DATE(data_hora_movimentacao) = ?
        ORDER BY data_hora_movimentacao ASC
        """;

    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
      stmt.setLong(1, idCaixa);
      stmt.setDate(2, Date.valueOf(data));

      try (ResultSet rs = stmt.executeQuery()) {
        while (rs.next()) {
          MovimentacaoCaixa mov = new MovimentacaoCaixa();
          mov.setId(rs.getLong("id"));
          mov.setIdCaixa(rs.getLong("id_caixa"));
          mov.setTipoMovimentacao(TipoMovimentacao.valueOf(rs.getString("tipo")));
          mov.setValor(rs.getDouble("valor"));
          mov.setOrigem(OrigemMovimentacao.valueOf(rs.getString("origem")));
          mov.setDescricao(rs.getString("descricao"));
          mov.setDataHoraMovimentacao(rs.getTimestamp("data_hora_movimentacao").toLocalDateTime());
          movimentacoes.add(mov);
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Erro ao buscar movimentações.", e);
    }

    return movimentacoes;
  }

  public void fecharConexao() {
    DatabaseConnection.closeConnection(connection);
  }
}
