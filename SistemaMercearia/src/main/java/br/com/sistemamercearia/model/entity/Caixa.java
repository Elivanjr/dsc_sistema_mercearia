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
    private int id;
    private int idUsuarioAbertura;
    private LocalDateTime dataHoraAbertura;
    private LocalDateTime dataHoraFechamento;
    private Double valorTrocoInicial;
    private StatusCaixa statusCaixa;
    private Double saldoMinimoProximoDia;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUsuarioAbertura() {
        return idUsuarioAbertura;
    }

    public void setIdUsuarioAbertura(int idUsuarioAbertura) {
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

    public void setStatusCaixa(StatusCaixa statusCaixa) {
        this.statusCaixa = statusCaixa;
    }

    public Double getSaldoMinimoProximoDia() {
        return saldoMinimoProximoDia;
    }

    public void setSaldoMinimoProximoDia(Double saldoMinimoProximoDia) {
        this.saldoMinimoProximoDia = saldoMinimoProximoDia;
    }
}
