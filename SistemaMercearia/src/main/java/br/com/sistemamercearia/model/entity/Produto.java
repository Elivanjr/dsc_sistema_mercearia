/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.sistemamercearia.model.entity;

import br.com.sistemamercearia.model.enums.UnidadeDeMedida;
import java.time.LocalDate;

/**
 *
 * @author gabriel
 */
public class Produto {
    private int id;
    private int quantidadeDisponivel;
    private String descricao;
    private UnidadeDeMedida unidadeDeMedida;
    private LocalDate dataFabricacao;
    private LocalDate dataVencimento;
    private Double precoUnitario;
    private Double quantidadeMedida;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }

    public void setQuantidadeDisponivel(int quantidadeDisponivel) {
        this.quantidadeDisponivel = quantidadeDisponivel;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public UnidadeDeMedida getUnidadeDeMedida() {
        return unidadeDeMedida;
    }

    public void setUnidadeDeMedida(UnidadeDeMedida unidadeDeMedida) {
        this.unidadeDeMedida = unidadeDeMedida;
    }

    public LocalDate getDataFabricacao() {
        return dataFabricacao;
    }

    public void setDataFabricacao(LocalDate dataFabricacao) {
        this.dataFabricacao = dataFabricacao;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public Double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(Double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public Double getQuantidadeMedida() {
        return quantidadeMedida;
    }

    public void setQuantidadeMedida(Double quantidadeMedida) {
        this.quantidadeMedida = quantidadeMedida;
    }
    
    public boolean verificarValidade(LocalDate dataAtual){
        return !dataAtual.isAfter(this.dataVencimento);
    }
    
    public boolean temEstoque(int quantidadeDesejada){
        if (quantidadeDesejada <= 0) throw new IllegalArgumentException("A quantidade desejada deve ser maior que zero.");
        
        return quantidadeDesejada <= this.quantidadeDisponivel;
    }
}
