/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.sistemamercearia.model.entity;

import br.com.sistemamercearia.model.enums.FormaPagamentoFiado;
import java.time.LocalDateTime;

/**
 *
 * @author gabriel
 */
public class PagamentoFiado {
    private long id;
    private long idNotaFiado;
    private LocalDateTime dataHoraPagamento;
    private Double valorPago;
    private FormaPagamentoFiado metodoPagamento;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdNotaFiado() {
        return idNotaFiado;
    }

    public void setIdNotaFiado(long idNotaFiado) {
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

    public FormaPagamentoFiado getMetodoPagamento() {
        return metodoPagamento;
    }

    public void setMetodoPagamentoFiado(FormaPagamentoFiado metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }
}
