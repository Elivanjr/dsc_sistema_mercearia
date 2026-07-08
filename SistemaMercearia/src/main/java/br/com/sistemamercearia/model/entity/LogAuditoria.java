/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.sistemamercearia.model.entity;

import java.time.LocalDateTime;

/**
 *
 * @author gabriel
 */
public class LogAuditoria {
    private long id;
    private final long idUsuario;
    private final String acao;
    private final String descricao;
    private final LocalDateTime dataHora;
    
    public LogAuditoria(long idUsuario, String acao, String descricao){
        this.idUsuario = idUsuario;
        this.acao = acao;
        this.descricao = descricao;
        this.dataHora = LocalDateTime.now();
        
    }
    
    public LogAuditoria(long idUsuario, String acao, String descricao, LocalDateTime dataHora){
        this.idUsuario = idUsuario;
        this.acao = acao;
        this.descricao = descricao;
        this.dataHora = dataHora;
    }

    public long getId() {
        return id;
    }

    public long getIdUsuario() {
        return idUsuario;
    }

    public String getAcao() {
        return acao;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }
    
}
