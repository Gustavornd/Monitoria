package com.example.clienteactivity;

public class Cliente {

    private int Id;
    private String Nome;
    private String Cpf;

    private String Email;
    private String Logradouro;
    private String Numero;
    private String Bairro;
    private String Cidade;
    private String Estado;
    private String Cep;
    private String Telefone;
    private String DiaVencimento;
    private String Complemento;

    public Cliente(String nome, String cpf, String email, String logradouro, String numero, String bairro, String cidade, String estado, String cep, String telefone, String diaVencimento, String complemento) {
        Nome = nome;
        Cpf = cpf;
        Email = email;
        Logradouro = logradouro;
        Numero = numero;
        Bairro = bairro;
        Cidade = cidade;
        Estado = estado;
        Cep = cep;
        Telefone = telefone;
        DiaVencimento = diaVencimento;
        Complemento = complemento;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getCpf() {
        return Cpf;
    }

    public void setCpf(String cpf) {
        Cpf = cpf;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getLogradouro() {
        return Logradouro;
    }

    public void setLogradouro(String logradouro) {
        Logradouro = logradouro;
    }

    public String getNumero() {
        return Numero;
    }

    public void setNumero(String numero) {
        Numero = numero;
    }

    public String getBairro() {
        return Bairro;
    }

    public void setBairro(String bairro) {
        Bairro = bairro;
    }

    public String getCidade() {
        return Cidade;
    }

    public void setCidade(String cidade) {
        Cidade = cidade;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }

    public String getCep() {
        return Cep;
    }

    public void setCep(String cep) {
        Cep = cep;
    }

    public String getTelefone() {
        return Telefone;
    }

    public void setTelefone(String telefone) {
        Telefone = telefone;
    }

    public String getDiaVencimento() {
        return DiaVencimento;
    }

    public void setDiaVencimento(String diaVencimento) {
        DiaVencimento = diaVencimento;
    }

    public String getComplemento() {
        return Complemento;
    }

    public void setComplemento(String complemento) {
        Complemento = complemento;
    }
}
