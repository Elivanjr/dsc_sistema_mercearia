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
    private long id;
    private LocalDateTime dataHora;
    private long idUsuario;
    private long idCliente;
    private long idCaixa;
    private Double valorTotal;
    private FormaPagamento formaDePagamento;
    private StatusVenda status;
    private List<ItemVenda> itens;

    public Venda(long idUsuario, long idCaixa) {
        this.idUsuario = idUsuario;
        this.idCaixa = idCaixa;
        this.status = StatusVenda.EM_ANDAMENTO;
        this.itens = new ArrayList<>();
    }

    public void adicionarItem(ItemVenda item){
        if(this.itens == null)
           this.itens = new ArrayList<>();
        this.itens.add(item);
    }
    
    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(long idCliente) {
        this.idCliente = idCliente;
    }

    public long getIdCaixa() {
        return idCaixa;
    }

    public void setIdCaixa(long idCaixa) {
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
    
    public Double calcularSubtotalFinal(){
        Double valor = 0.0;
        for(ItemVenda item : this.itens)
            valor += item.calculaSubtotal();
        return valor;
    }
    
    public void cancelarVenda(){
        this.status = StatusVenda.CANCELADA;
    }
    
    public boolean estaConcluida(){
        return this.status == StatusVenda.CONCLUIDA;
    }
}
