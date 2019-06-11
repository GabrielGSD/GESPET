package br.inatel.gespet.model;

import java.util.Date;
import java.util.Scanner;
import javax.swing.JOptionPane;
import br.inatel.gespet.control.VacinaDAO;

public class Vacina {

    //Atributos
    private String nome;
    private String dataVacina;
    private String dataProximaVacina;
    private int carteiraNumID;
    public VacinaDAO Vdao;

    //Construtores
    public Vacina() {
        Vdao = new VacinaDAO();
    }

    public Vacina(String nome, String dataVacina, String dataProximaVacina, int carteiraNumID) {
        this.nome = nome;
        this.dataVacina = dataVacina;
        this.dataProximaVacina = dataProximaVacina;
        this.carteiraNumID = carteiraNumID;
        Vdao = new VacinaDAO();
    }

    public boolean deletarVacina(int id) {

        //Declaração de Variaveis
        boolean resultado;

        //Deletando no Banco
        resultado = Vdao.deleteVacinaPeloID(id);

        return resultado;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataVacina() {
        return dataVacina;
    }

    public void setDataVacina(String dataVacina) {
        this.dataVacina = dataVacina;
    }

    public String getDataProximaVacina() {
        return dataProximaVacina;
    }

    public void setDataProximaVacina(String dataProximaVacina) {
        this.dataProximaVacina = dataProximaVacina;
    }


    public int getCarteiraNumID() {
        return carteiraNumID;
    }

    public void setCarteiraNumID(int carteiraNumID) {
        this.carteiraNumID = carteiraNumID;
    }

}
