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
import java.sql.Statement;
import java.util.List;

/**
 * @author elivan
 */
public class VendaDAO {

    private Connection connection;

    public VendaDAO() {
        try {
            connection = DatabaseConnection.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar ao banco de dados.", e);
        }
    }

    public void salvarVendaCompleta(Venda venda, List<ItemVenda> itens) {

        if (venda == null) {
            throw new IllegalArgumentException("Venda não pode ser nula.");
        }

        if (itens == null || itens.isEmpty()) {
            throw new IllegalArgumentException("Venda deve possuir ao menos um item.");
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

        try {

            connection.setAutoCommit(false);

            long idVenda;

            try (PreparedStatement stmtVenda = connection.prepareStatement(
                    sqlVenda, Statement.RETURN_GENERATED_KEYS)) {

                stmtVenda.setTimestamp(1, Timestamp.valueOf(venda.getDataHora()));
                stmtVenda.setLong(2, venda.getIdUsuario());
                stmtVenda.setLong(3, venda.getIdCliente());
                stmtVenda.setLong(4, venda.getIdCaixa());
                stmtVenda.setDouble(5, venda.getValorTotal());
                stmtVenda.setString(6, venda.getFormaDePagamento().name());
                stmtVenda.setString(7, venda.getStatus().name());

                stmtVenda.executeUpdate();

                try (ResultSet rs = stmtVenda.getGeneratedKeys()) {

                    if (!rs.next()) {
                        throw new SQLException("Não foi possível obter o ID da venda.");
                    }

                    idVenda = rs.getLong(1);
                }
            }

            try (PreparedStatement stmtItem = connection.prepareStatement(sqlItem)) {

                for (ItemVenda item : itens) {
                    stmtItem.setLong(1, idVenda);
                    stmtItem.setLong(2, item.getIdProduto());
                    stmtItem.setLong(3, item.getQuantidade());
                    stmtItem.setDouble(4, item.getPrecoUnitario());
                    stmtItem.setDouble(5, item.getSubtotal());

                    stmtItem.addBatch();
                }

                stmtItem.executeBatch();
            }

            connection.commit();

        } catch (SQLException e) {

            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    throw new RuntimeException("Erro ao realizar rollback.", ex);
                }
            }

            throw new RuntimeException("Erro ao salvar venda completa.", e);

        } finally {

            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                } catch (SQLException e) {
                    throw new RuntimeException("Erro ao restaurar AutoCommit.", e);
                }
            }
        }
    }

    public void fecharConexao() {
        DatabaseConnection.closeConnection(connection);
    }
}