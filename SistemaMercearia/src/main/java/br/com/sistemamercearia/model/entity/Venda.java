/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.sistemamercearia.model.entity;

import br.com.sistemamercearia.model.enums.FormaPagamento;
import br.com.sistemamercearia.model.enums.StatusVenda;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gabriel
 */
public class Venda {
    private int id;
    private LocalDateTime dataHora;
    private int idUsuario;
    private int idCliente;
    private int idCaixa;
    private Double valorTotal;
    private FormaPagamento formaDePagamento;
    private StatusVenda status;
    private List<ItemVenda> itens;

    public Venda(int idUsuario, int idCliente, int idCaixa) {
        this.idUsuario = idUsuario;
        this.idCliente = idCliente;
        this.idCaixa = idCaixa;
        this.dataHora = LocalDateTime.now();
        this.valorTotal = 0.0;
        this.itens = new ArrayList<>();
    }

    public Venda(int id, LocalDateTime dataHora, int idUsuario, int idCliente, int idCaixa, Double valorTotal, FormaPagamento formaDePagamento, StatusVenda status, List<ItemVenda> itens) {
        this.id = id;
        this.dataHora = dataHora;
        this.idUsuario = idUsuario;
        this.idCliente = idCliente;
        this.idCaixa = idCaixa;
        this.valorTotal = valorTotal;
        this.formaDePagamento = formaDePagamento;
        this.status = status;
        this.itens = itens;
    }
    
    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdCaixa() {
        return idCaixa;
    }

    public void setIdCaixa(int idCaixa) {
        this.idCaixa = idCaixa;
    }

    public StatusVenda getStatus() {
        return status;
    }

    public void setStatus(StatusVenda status) {
        this.status = status;
    }

    public List<ItemVenda> getItens() {
        return itens;
    }

    public void setItens(List<ItemVenda> itens) {
        this.itens = itens;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public FormaPagamento getFormaDePagamento() {
        return formaDePagamento;
    }

    public void setFormaDePagamento(FormaPagamento formaDePagamento) {
        this.formaDePagamento = formaDePagamento;
    } 
}
