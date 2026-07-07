/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.sistemamercearia.model.dao;

import br.com.sistemamercearia.model.entity.ItemVenda;
import br.com.sistemamercearia.model.entity.Venda;
import br.com.sistemamercearia.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

/**
 * @author elivan
 */
public class VendaDAO {

    /**
     * @param venda
     * @param itens
     * @throws SQLException
     * @throws IllegalArgumentException
     */
    public void salvarVendaCompleta(Venda venda, List<ItemVenda> itens)
            throws SQLException, IllegalArgumentException {

        if (venda == null) {
            throw new IllegalArgumentException("Venda não pode ser nula!");
        }
        if (itens == null || itens.isEmpty()) {
            throw new IllegalArgumentException("Venda deve ter pelo menos um item!");
        }

        String sqlVenda = """
                INSERT INTO Venda
                (data_hora, id_usuario, id_cliente, id_caixa,
                 valor_total, forma_pagamento, status)
                VALUES (?, ?, ?, ?, ?, ?, ?)
                """;

        String sqlItem = """
                INSERT INTO ItemVenda
                (id_venda, id_produto, quantidade,
                 preco_unitario, subtotal)
                VALUES (?, ?, ?, ?, ?)
                """;

        Connection connection = null;
        PreparedStatement stmtVenda = null;
        PreparedStatement stmtItem = null;
        ResultSet rs = null;

        try {
            connection = DatabaseConnection.getConnection();

            connection.setAutoCommit(false);

            long idVenda;

            stmtVenda = connection.prepareStatement(
                    sqlVenda,
                    PreparedStatement.RETURN_GENERATED_KEYS);

            stmtVenda.setTimestamp(1, Timestamp.valueOf(venda.getDataHora()));
            stmtVenda.setLong(2, venda.getIdUsuario());
            stmtVenda.setLong(3, venda.getIdCliente());
            stmtVenda.setLong(4, venda.getIdCaixa());
            stmtVenda.setDouble(5, venda.getValorTotal());
            stmtVenda.setString(6, venda.getFormaDePagamento().name());
            stmtVenda.setString(7, venda.getStatus().name());

            stmtVenda.executeUpdate();

            rs = stmtVenda.getGeneratedKeys();
            if (rs.next()) {
                idVenda = rs.getLong(1);
            } else {
                throw new SQLException("Não foi possível obter o ID da venda gerado pelo banco.");
            }

            if (rs != null) {
                rs.close();
                rs = null;
            }

            stmtItem = connection.prepareStatement(sqlItem);

            for (ItemVenda item : itens) {
                stmtItem.setLong(1, idVenda);
                stmtItem.setLong(2, item.getIdProduto());
                stmtItem.setLong(3, item.getQuantidade());
                stmtItem.setDouble(4, item.getPrecoUnitario());
                stmtItem.setDouble(5, item.getSubtotal());

                stmtItem.addBatch();
            }

            // Executa TODOS os inserts de uma vez (BATCH)
            int[] resultados = stmtItem.executeBatch();

            // Valida se todos os inserts funcionaram
            for (int resultado : resultados) {
                if (resultado < 0 && resultado != PreparedStatement.SUCCESS_NO_INFO) {
                    throw new SQLException("Erro ao inserir item de venda!");
                }
            }

            connection.commit();

        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                    System.err.println("Transação revertida por erro: " + e.getMessage());
                } catch (SQLException rollbackEx) {
                    System.err.println("ERRO CRÍTICO ao fazer rollback: " + rollbackEx.getMessage());
                    rollbackEx.printStackTrace();
                }
            }
            throw e;

        } finally {

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    System.err.println("Erro ao fechar ResultSet: " + e.getMessage());
                }
            }

            if (stmtVenda != null) {
                try {
                    stmtVenda.close();
                } catch (SQLException e) {
                    System.err.println("Erro ao fechar stmtVenda: " + e.getMessage());
                }
            }

            if (stmtItem != null) {
                try {
                    stmtItem.close();
                } catch (SQLException e) {
                    System.err.println("Erro ao fechar stmtItem: " + e.getMessage());
                }
            }

            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                } catch (SQLException e) {
                    System.err.println("Erro ao reabilitar autocommit: " + e.getMessage());
                }

                fecharConexao();
            }
        }
    }

    public void fecharConexao() {
        DatabaseConnection.closeConnection(connection);
    }
}