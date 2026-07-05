/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.sistemamercearia.model.entity;

import br.com.sistemamercearia.model.enums.StatusNotaFiado;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author gabriel
 */
public class NotaFiado {
    private int id;
    private int idCliente;
    private int idVenda;
    private LocalDateTime dataHoraEmissao;
    private LocalDate dataVencimento;
    private Double saldoDevedor;
    private String nomeTerceiro;
    private StatusNotaFiado statusNota;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(int idVenda) {
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
    
}
