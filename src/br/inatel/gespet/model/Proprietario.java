package br.inatel.gespet.model;

import br.inatel.gespet.control.ProprietarioDAO;

public class Proprietario {

    //Atributos
    private String cpf;
    private String nome;
    private String rua;
    private String bairro;
    private int celular;
    public ProprietarioDAO Pdao;

    //Construtores
    public Proprietario() {
        Pdao = new ProprietarioDAO();
    }

    public Proprietario(String nome, String cpf, String rua, String bairro, int celular) {
        Pdao = new ProprietarioDAO();
        this.nome = nome;
        this.cpf = cpf;
        this.rua = rua;
        this.bairro = bairro;
        this.celular = celular;
    }

    //Getters e Setters para cpf
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    //Getters e Setters para nome
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    //Getters e Setters para rua
    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    //Getters e Setters para bairro
    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    //Getters e Setters para celular
    public int getCelular() {
        return celular;
    }

    public void setCelular(int celular) {
        this.celular = celular;
    }
}
