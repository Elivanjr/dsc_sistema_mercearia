package br.com.sistemamercearia.model.dao;

import br.com.sistemamercearia.model.entity.Produto;
import br.com.sistemamercearia.model.enums.UnidadeDeMedida;
import br.com.sistemamercearia.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author elivan
 */
public class ProdutoDAO {

  private final Connection connection;

  private static final int LIMITE_ESTOQUE_BAIXO = 10;
  private static final int LIMITE_VENCIMENTO_DIAS = 30;

  public ProdutoDAO() throws SQLException {
    this.connection = DatabaseConnection.getConnection();
  }

  public void salvar(Produto produto) {
    String sql = """
        INSERT INTO Produto
        (quantidade_disponivel, descricao, codigo_de_barras, lote,
         unidade_de_medida, data_fabricacao, data_vencimento,
         preco_unitario, quantidade_medida)
        VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
      stmt.setLong(1, produto.getQuantidadeDisponivel());
      stmt.setString(2, produto.getDescricao());
      stmt.setString(3, produto.getCodigoDeBarras());
      stmt.setString(4, produto.getLote());
      stmt.setString(5, produto.getUnidadeDeMedida().name());
      stmt.setDate(6, Date.valueOf(produto.getDataFabricacao()));
      stmt.setDate(7, Date.valueOf(produto.getDataVencimento()));
      stmt.setDouble(8, produto.getPrecoUnitario());
      stmt.setDouble(9, produto.getQuantidadeMedida());

      stmt.executeUpdate();
    } catch (SQLException e) {
      throw new RuntimeException("Erro ao salvar produto", e);
    }
  }

  public Produto buscarPorCodigoELote(String codigo, String lote) {
    String sql = "SELECT * FROM Produto WHERE codigo_de_barras = ? AND lote = ?";

    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
      stmt.setString(1, codigo);
      stmt.setString(2, lote);

      try (ResultSet rs = stmt.executeQuery()) {
        if (rs.next()) {
          return mapearProduto(rs);
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Erro ao buscar produto", e);
    }
    return null;
  }

  public void atualizarEstoque(long idProduto, long qtdVendida) {
    String sql = "UPDATE Produto SET quantidade_disponivel = quantidade_disponivel - ? WHERE id = ?";

    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
      stmt.setLong(1, qtdVendida);
      stmt.setLong(2, idProduto);
      stmt.executeUpdate();
    } catch (SQLException e) {
      throw new RuntimeException("Erro ao atualizar estoque", e);
    }
  }

  public List<Produto> listarProdutosBaixoEstoque() {
    List<Produto> produtos = new ArrayList<>();
    String sql = """
        SELECT * FROM Produto
        WHERE quantidade_disponivel <= ?
        ORDER BY quantidade_disponivel ASC
        """;

    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
      stmt.setInt(1, LIMITE_ESTOQUE_BAIXO);

      try (ResultSet rs = stmt.executeQuery()) {
        while (rs.next()) {
          produtos.add(mapearProduto(rs));
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Erro ao listar produtos com baixo estoque", e);
    }

    return produtos;
  }

  public List<Produto> listarProdutosProximosVencimento() {
    List<Produto> produtos = new ArrayList<>();
    String sql = """
        SELECT * FROM Produto
        WHERE data_vencimento BETWEEN ? AND ?
        """;

    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
      stmt.setDate(1, Date.valueOf(LocalDate.now()));
      stmt.setDate(2, Date.valueOf(LocalDate.now().plusDays(LIMITE_VENCIMENTO_DIAS)));

      try (ResultSet rs = stmt.executeQuery()) {
        while (rs.next()) {
          produtos.add(mapearProduto(rs));
        }
      }
    } catch (SQLException e) {
      throw new RuntimeException("Erro ao listar produtos próximos do vencimento", e);
    }

    return produtos;
  }

  private Produto mapearProduto(ResultSet rs) throws SQLException {
    Produto produto = new Produto();

    produto.setId(rs.getLong("id"));
    produto.setQuantidadeDisponivel(rs.getLong("quantidade_disponivel"));
    produto.setDescricao(rs.getString("descricao"));
    produto.setCodigoDeBarras(rs.getString("codigo_de_barras"));
    produto.setLote(rs.getString("lote"));
    produto.setUnidadeDeMedida(UnidadeDeMedida.valueOf(rs.getString("unidade_de_medida")));
    produto.setDataFabricacao(rs.getDate("data_fabricacao").toLocalDate());
    produto.setDataVencimento(rs.getDate("data_vencimento").toLocalDate());
    produto.setPrecoUnitario(rs.getDouble("preco_unitario"));
    produto.setQuantidadeMedida(rs.getDouble("quantidade_medida"));

    return produto;
  }

  public void fecharConexao() {
    DatabaseConnection.closeConnection(connection);
  }
}