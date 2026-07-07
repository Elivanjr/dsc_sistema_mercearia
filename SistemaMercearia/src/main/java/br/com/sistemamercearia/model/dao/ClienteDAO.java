package br.com.sistemamercearia.model.dao;

import br.com.sistemamercearia.model.entity.Cliente;
import br.com.sistemamercearia.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author elivan
 */
public class ClienteDAO {
    private Connection connection;

    public ClienteDAO() throws SQLException {
        connection = DatabaseConnection.getConnection();
    }

    public void salvar(Cliente cliente) {
        String sql = "INSERT INTO Cliente (diaVencimentoFiado, nome, "
                + "cpf, telefone, endereco, senha, limiteDeCredito, statusBloqueio)"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setLong(1, cliente.getDiaVencimentoFiado());
            stmt.setString(2, cliente.getNome());
            stmt.setString(3, cliente.getCpf());
            stmt.setString(4, cliente.getTelefone());
            stmt.setString(5, cliente.getEndereco());
            stmt.setString(6, cliente.getSenha());
            stmt.setDouble(7, cliente.getLimiteDeCredito());
            stmt.setBoolean(8, cliente.isStatusBloqueio());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar cliente", e);
        }
    }

    public Cliente buscarPorCpf(String cpf) {
        String sql = "SELECT * FROM Cliente WHERE cpf = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cpf);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Cliente cliente = new Cliente();
                    cliente.setId(rs.getLong("id"));
                    cliente.setNome(rs.getString("nome"));
                    cliente.setCpf(rs.getString("cpf"));
                    cliente.setTelefone(rs.getString("telefone"));
                    cliente.setEndereco(rs.getString("endereco"));
                    cliente.setSenha(rs.getString("senha"));
                    cliente.setLimiteDeCredito(rs.getDouble("limiteDeCredito"));
                    cliente.setStatusBloqueio(rs.getBoolean("statusBloqueio"));
                    return cliente;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar cliente", e);
        }
        return null;
    }

    public void atualizarStatus(long idCliente, boolean bloqueado) {
        String sql = "UPDATE Cliente SET statusBloqueio = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setBoolean(1, bloqueado);
            stmt.setLong(2, idCliente);

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar status do cliente", e);
        }
    }

    public void fecharConexao() {
        DatabaseConnection.closeConnection(connection);
    }
}
