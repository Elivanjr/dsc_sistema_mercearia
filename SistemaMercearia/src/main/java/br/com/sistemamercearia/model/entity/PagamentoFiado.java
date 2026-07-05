/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.sistemamercearia.model.entity;

import br.com.sistemamercearia.model.enums.FormaPagamento;
import java.time.LocalDateTime;

/**
 *
 * @author gabriel
 */
public class PagamentoFiado {
    private int id;
    private int idNotaFiado;
    private LocalDateTime dataHoraPagamento;
    private Double valorPago;
    private FormaPagamento metodoPagamento;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdNotaFiado() {
        return idNotaFiado;
    }

    public void setIdNotaFiado(int idNotaFiado) {
        this.idNotaFiado = idNotaFiado;
    }

    public LocalDateTime getDataHoraPagamento() {
        return dataHoraPagamento;
    }

    public void setDataHoraPagamento(LocalDateTime dataHoraPagamento) {
        this.dataHoraPagamento = dataHoraPagamento;
    }

    public Double getValorPago() {
        return valorPago;
    }

    public void setValorPago(Double valorPago) {
        this.valorPago = valorPago;
    }

    public FormaPagamento getMetodoPagamento() {
        return metodoPagamento;
    }

    public void setMetodoPagamento(FormaPagamento metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }
}
