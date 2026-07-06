/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.sistemamercearia.model.entity;

/**
 *
 * @author gabriel
 */
public class Cliente {
    private int id;
    private int diaVencimentoFiado;
    private String nome;
    private String cpf;
    private String endereco;
    private String telefone;
    private String senha;
    private Double limiteDeCredito;
    private boolean statusBloqueio;
    
    public Cliente(int diaVencimentoFiado, String nome, String cpf, String endereco, String telefone, String senha, Double limiteDeCredito, boolean statusBloqueio) {
        this.diaVencimentoFiado = diaVencimentoFiado;
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
        this.telefone = telefone;
        this.senha = senha;
        this.limiteDeCredito = limiteDeCredito;
        this.statusBloqueio = statusBloqueio;
    }

    public Cliente(int id, int diaVencimentoFiado, String nome, String cpf, String endereco, String telefone, String senha, Double limiteDeCredito, boolean statusBloqueio) {
        this.id = id;
        this.diaVencimentoFiado = diaVencimentoFiado;
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
        this.telefone = telefone;
        this.senha = senha;
        this.limiteDeCredito = limiteDeCredito;
        this.statusBloqueio = statusBloqueio;
    }

    public boolean isStatusBloqueio() {
        return statusBloqueio;
    }

    public void setStatusBloqueio(boolean statusBloqueio) {
        this.statusBloqueio = statusBloqueio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public int getDiaVencimentoFiado() {
        return diaVencimentoFiado;
    }

    public void setDiaVencimentoFiado(int diaVencimentoFiado) {
        this.diaVencimentoFiado = diaVencimentoFiado;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Double getLimiteDeCredito() {
        return limiteDeCredito;
    }

    public void setLimiteDeCredito(Double limiteDeCredito) {
        this.limiteDeCredito = limiteDeCredito;
    }
    
    public void bloquearConta(){
        this.statusBloqueio = true;
    }
    
    public void desbloquearConta(){
        this.statusBloqueio = false;
    }
}
