/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.sistemamercearia.model.entity;

import br.com.sistemamercearia.model.enums.StatusCaixa;
import java.time.LocalDateTime;

/**
 *
 * @author gabriel
 */
public class Caixa {
    private long id;
    private long idUsuarioAbertura;
    private LocalDateTime dataHoraAbertura;
    private LocalDateTime dataHoraFechamento;
    private Double valorTrocoInicial;
    private StatusCaixa statusCaixa;
    private Double saldoMinimoProximoDia;
    
    public void setStatusCaixa(StatusCaixa statusCaixa) {
        this.statusCaixa = statusCaixa;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdUsuarioAbertura() {
        return idUsuarioAbertura;
    }

    public void setIdUsuarioAbertura(long idUsuarioAbertura) {
        this.idUsuarioAbertura = idUsuarioAbertura;
    }

    public LocalDateTime getDataHoraAbertura() {
        return dataHoraAbertura;
    }

    public void setDataHoraAbertura(LocalDateTime dataHoraAbertura) {
        this.dataHoraAbertura = dataHoraAbertura;
    }

    public LocalDateTime getDataHoraFechamento() {
        return dataHoraFechamento;
    }

    public void setDataHoraFechamento(LocalDateTime dataHoraFechamento) {
        this.dataHoraFechamento = dataHoraFechamento;
    }

    public Double getValorTrocoInicial() {
        return valorTrocoInicial;
    }

    public void setValorTrocoInicial(Double valorTrocoInicial) {
        this.valorTrocoInicial = valorTrocoInicial;
    }

    public StatusCaixa getStatusCaixa() {
        return statusCaixa;
    }

    public Double getSaldoMinimoProximoDia() {
        return saldoMinimoProximoDia;
    }

    public void setSaldoMinimoProximoDia(Double saldoMinimoProximoDia) {
        this.saldoMinimoProximoDia = saldoMinimoProximoDia;
    }
    
    public boolean estaAberto(){
        return this.statusCaixa == StatusCaixa.ABERTO;
    }
    
    public void fechar(){
        this.statusCaixa = StatusCaixa.FECHADO;
    }
}
