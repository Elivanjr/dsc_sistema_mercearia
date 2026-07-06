/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.sistemamercearia.model.entity;

import br.com.sistemamercearia.model.enums.UnidadeDeMedida;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author gabriel
 */
public class Produto {
    private long id;
    private long quantidadeDisponivel;
    private String descricao;
    private String codigoDeBarras;
    private String lote;
    private UnidadeDeMedida unidadeDeMedida;
    private LocalDate dataFabricacao;
    private LocalDate dataVencimento;
    private Double precoUnitario;
    private Double quantidadeMedida;

    public String getCodigoDeBarras() {
        return codigoDeBarras;
    }

    public void setCodigoDeBarras(String codigoDeBarras) {
        this.codigoDeBarras = codigoDeBarras;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }

    public void setQuantidadeDisponivel(long quantidadeDisponivel) {
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
    
    public boolean temEstoque(long quantidadeDesejada){
        return quantidadeDesejada >0 && quantidadeDesejada <= this.quantidadeDisponivel;
    }
    
    public boolean estaProximoDoVencimento(long diasLimite, LocalDate dataAtual){
        return ChronoUnit.DAYS.between(dataAtual, this.dataVencimento) > diasLimite;
    }
    
    public void debitarEstoque(long quantidadeDesejada){
        if (quantidadeDesejada <= 0)
            throw new IllegalArgumentException("A quantidade desejada deve ser maior que zero.");
        
        if(!this.temEstoque(quantidadeDesejada))
            throw new IllegalArgumentException("Estoque indisponível.");
        
        this.quantidadeDisponivel -= quantidadeDesejada;
        
    }
}
