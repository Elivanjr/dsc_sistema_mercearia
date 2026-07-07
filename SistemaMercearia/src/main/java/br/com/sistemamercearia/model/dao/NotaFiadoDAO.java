/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.sistemamercearia.model.dao;

import br.com.sistemamercearia.model.entity.NotaFiado;
import br.com.sistemamercearia.model.enums.StatusNotaFiado;
import br.com.sistemamercearia.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.Timestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author elivan
 */
public class NotaFiadoDAO {
    private Connection connection;

    public NotaFiadoDAO() {
        try {
            connection = DatabaseConnection.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar ao banco de dados", e);
        }
    }

    public void salvarNota(NotaFiado nota) {
        String sql = """
                INSERT INTO NotaFiado
                (id_cliente, id_venda, data_hora_emissao, data_vencimento,
                 multa, valor_original, saldo_devedor, nome_terceiro, status_nota)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, nota.getIdCliente());
            stmt.setLong(2, nota.getIdVenda());
            stmt.setTimestamp(3, Timestamp.valueOf(nota.getDataHoraEmissao()));
            stmt.setDate(4, Date.valueOf(nota.getDataVencimento()));
            stmt.setDouble(5, nota.getMulta());
            stmt.setDouble(6, nota.getValorOriginal());
            stmt.setDouble(7, nota.getSaldoDevedor());
            stmt.setString(8, nota.getNomeTerceiro());
            stmt.setString(9, nota.getStatusNota().name());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar nota fiado", e);
        }
    }

    public List<NotaFiado> buscasNotaAtrasadas() {
        List<NotaFiado> notas = new ArrayList<>();
        String sql = """
                SELECT * FROM NotaFiado WHERE status_nota = ? AND data_vencimento < ?
                """;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, StatusNotaFiado.PENDENTE.name());
            stmt.setDate(2, Date.valueOf(LocalDate.now()));

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    notas.add(mapearNotaFiado(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar notas fiado", e);
        }
        return notas;
    }

    public BigDecimal somarSaldoDevedorCliente(long idCliente) {
        String sql = "SELECT SUM(saldo_devedor) FROM NotaFiado WHERE id_cliente = ? AND status_nota = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, idCliente);
            stmt.setString(2, StatusNotaFiado.PENDENTE.name());

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getBigDecimal(1);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao somar saldo devedor", e);
        }
        return BigDecimal.ZERO;
    }

    private NotaFiado mapearNotaFiado(ResultSet rs) throws SQLException {

        NotaFiado nota = new NotaFiado();

        nota.setId(rs.getLong("id"));
        nota.setIdCliente(rs.getLong("id_cliente"));
        nota.setIdVenda(rs.getLong("id_venda"));
        nota.setDataHoraEmissao(rs.getTimestamp("data_hora_emissao").toLocalDateTime());
        nota.setDataVencimento(rs.getDate("data_vencimento").toLocalDate());
        nota.setMulta(rs.getDouble("multa"));
        nota.setValorOriginal(rs.getDouble("valor_original"));
        nota.setSaldoDevedor(rs.getDouble("saldo_devedor"));
        nota.setNomeTerceiro(rs.getString("nome_terceiro"));
        nota.setStatusNota(StatusNotaFiado.valueOf(rs.getString("status_nota")));

        return nota;
    }

    public void fecharConexao() {
        DatabaseConnection.closeConnection(connection);
    }
}
