package br.inatel.gespet.model;

import javax.swing.JOptionPane;
import br.inatel.gespet.control.VeterinarioDAO;

public class Veterinario {

    //Atributos
    private int crmv;
    private String senha;
    public VeterinarioDAO Vdao;

    //Contrutores
    public Veterinario() {
        Vdao = new VeterinarioDAO();
    }

    public Veterinario(int crmv, String senha) {
        Vdao = new VeterinarioDAO();
        this.crmv = crmv;
        this.senha = senha;
    }

    //Metodos
    public void inserirVeterinarios(Veterinario vet) {

        //Declaração de Variaveis
        boolean resultado;

        //Variavel boolean para receber o Retorno da função insert
        resultado = Vdao.insert(vet);
        if (resultado) {
            JOptionPane.showMessageDialog(null, "VETERINÁRIO CADASTRADO COM SUCESSO!");
        }
    }

    //Declarando os get and set
    public int getCrmv() {
        return crmv;
    }

    public void setCrmv(int crmv) {
        this.crmv = crmv;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    



}
