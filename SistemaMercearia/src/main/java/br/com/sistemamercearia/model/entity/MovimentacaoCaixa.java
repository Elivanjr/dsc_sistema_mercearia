/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.sistemamercearia.model.entity;

import br.com.sistemamercearia.model.enums.OrigemMovimentacao;
import br.com.sistemamercearia.model.enums.TipoMovimentacao;
import java.time.LocalDateTime;

/**
 *
 * @author gabriel
 */
public class MovimentacaoCaixa {
    private int id;
    private int idCaixa;
    private TipoMovimentacao tipoMovimentacao;
    private Double valor;   
    private String descricao;
    private LocalDateTime dataHoraMovimentacao;
    private OrigemMovimentacao origem;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCaixa() {
        return idCaixa;
    }

    public void setIdCaixa(int idCaixa) {
        this.idCaixa = idCaixa;
    }

    public TipoMovimentacao getTipoMovimentacao() {
        return tipoMovimentacao;
    }

    public void setTipoMovimentacao(TipoMovimentacao tipoMovimentacao) {
        this.tipoMovimentacao = tipoMovimentacao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getDataHoraMovimentacao() {
        return dataHoraMovimentacao;
    }

    public void setDataHoraMovimentacao(LocalDateTime dataHoraMovimentacao) {
        this.dataHoraMovimentacao = dataHoraMovimentacao;
    }

    public OrigemMovimentacao getOrigem() {
        return origem;
    }

    public void setOrigem(OrigemMovimentacao origem) {
        this.origem = origem;
    }
}
