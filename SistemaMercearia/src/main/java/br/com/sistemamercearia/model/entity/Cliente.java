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
    private long id;
    private long diaVencimentoFiado;
    private String nome;
    private String cpf;
    private String endereco;
    private String telefone;
    private String senha;
    private Double limiteDeCredito;
    private boolean statusBloqueio;

    public boolean isStatusBloqueio() {
        return statusBloqueio;
    }

    public void setStatusBloqueio(boolean statusBloqueio) {
        this.statusBloqueio = statusBloqueio;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    public long getDiaVencimentoFiado() {
        return diaVencimentoFiado;
    }

    public void setDiaVencimentoFiado(long diaVencimentoFiado) {
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
    
    public boolean possuiSenhaCadastrada(){
        return this.senha != null && !this.senha.isBlank();
    }
    
    //falta implementar...
    //public boolean verificarSenha(String senhaInformada)
}
