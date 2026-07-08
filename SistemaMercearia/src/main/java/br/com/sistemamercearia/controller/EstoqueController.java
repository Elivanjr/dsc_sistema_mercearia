/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.sistemamercearia.controller;

import br.com.sistemamercearia.model.dao.ProdutoDAO;
import br.com.sistemamercearia.model.entity.Produto;
import java.time.LocalDate;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author gabriel
 */
public class EstoqueController {
    public final ProdutoDAO produtoDAO = new ProdutoDAO();
    
    public void cadastrarNovoLoteProduto(Produto produto){
        if(produto.verificarValidade(LocalDate.now())){
            JOptionPane.showMessageDialog(null,
                    "Lotes com data de validade vencida não podem ser cadastrados!"
            );
            return;
        }
            
        if(produto.getQuantidadeDisponivel() <= 0){
            JOptionPane.showMessageDialog(
                    null,
                    "Lotes com estoque menor ou igual a 0 não podem ser cadastrados!"
            );
            return;
        }
            
        produtoDAO.salvar(produto);
        produtoDAO.fecharConexao();
    }
    
    public void atualizarInformacoesLote(long idProduto, Produto produto){
        if(produto.verificarValidade(LocalDate.now())){
            JOptionPane.showMessageDialog(null,
                    "Lotes com data de validade vencida não podem ser cadastrados!"
            );
            return;
        }
            
        if(produto.getQuantidadeDisponivel() <= 0){
            JOptionPane.showMessageDialog(
                    null,
                    "Lotes com estoque menor ou igual a 0 não podem ser cadastrados!"
            );
            return;
        }
        
        produtoDAO.atualizarInformacoesProduto(idProduto, produto);
        produtoDAO.fecharConexao();
    }
    
    public void deletarLoteProduto(long idProduto){
        if(idProduto <= 0){
            JOptionPane.showMessageDialog(
                    null,
                    "ID inválido!"
            );
            
            return;
        }
        
        produtoDAO.deletarLoteProduto(idProduto);
        produtoDAO.fecharConexao();
    }
    
    public List<Produto> verificarAlertasDeVencimento(){
        return produtoDAO.listarProdutosProximosVencimento();
    }
    
    public List<Produto> verificarAlertasDeBaixoEstoque(){
        return produtoDAO.listarProdutosBaixoEstoque();
    }
 
}
