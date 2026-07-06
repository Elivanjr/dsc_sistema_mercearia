/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.sistemamercearia.model.entity;

import br.com.sistemamercearia.model.enums.StatusNotaFiado;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author gabriel
 */
public class NotaFiado {
    private long id;
    private long idCliente;
    private long idVenda;
    private LocalDateTime dataHoraEmissao;
    private LocalDate dataVencimento;
    private Double multa;
    private Double valorOriginal;
    private Double saldoDevedor;
    private String nomeTerceiro;
    private StatusNotaFiado statusNota;

    public Double getMulta() {
        return multa;
    }

    public void setMulta(Double multa) {
        this.multa = multa;
    }

    public Double getValorOriginal() {
        return valorOriginal;
    }

    public void setValorOriginal(Double valorOriginal) {
        this.valorOriginal = valorOriginal;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(long idCliente) {
        this.idCliente = idCliente;
    }

    public long getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(long idVenda) {
        this.idVenda = idVenda;
    }

    public LocalDateTime getDataHoraEmissao() {
        return dataHoraEmissao;
    }

    public void setDataHoraEmissao(LocalDateTime dataHoraEmissao) {
        this.dataHoraEmissao = dataHoraEmissao;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public Double getSaldoDevedor() {
        return saldoDevedor;
    }

    public void setSaldoDevedor(Double saldoDevedor) {
        this.saldoDevedor = saldoDevedor;
    }

    public String getNomeTerceiro() {
        return nomeTerceiro;
    }

    public void setNomeTerceiro(String nomeTerceiro) {
        this.nomeTerceiro = nomeTerceiro;
    }

    public StatusNotaFiado getStatusNota() {
        return statusNota;
    }

    public void setStatusNota(StatusNotaFiado statusNota) {
        this.statusNota = statusNota;
    }
    
    public void abaterSaldo(Double valorPago){
        if(valorPago <= 0)
            throw new IllegalArgumentException("O valor pago deve ser maior que R$ 0,00.");
        
        this.saldoDevedor -= valorPago;
        
        if(this.saldoDevedor == 0.0)
            this.setStatusNota(StatusNotaFiado.PAGA);
    }
    
    public boolean estaAtrasada(LocalDate dataAtual){
        return this.statusNota == StatusNotaFiado.PENDENTE && dataAtual.isAfter(this.dataVencimento);
    }
    
    public long diasEmAtraso(LocalDate dataAtual){
        return ChronoUnit.DAYS.between(this.dataVencimento, dataAtual);
    }
}
