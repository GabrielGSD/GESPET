package br.inatel.gespet.model;

import br.inatel.gespet.control.PrincipaisInfoAnimalDAO;

public class PrincipaisInfoAnimal {

    //Atributos
    private int id;
    private String nomeDoAnimal;
    private String especieDoAnimal;
    private String racaDoAnimal;
    private int idadeDoAnimal;
    private String generoDoAnimal;
    private String nomeDoProprietario;
    private String cpfDoProprietario;
    private PrincipaisInfoAnimalDAO Pdao;

    //Construtores
    public PrincipaisInfoAnimal() {
        Pdao = new PrincipaisInfoAnimalDAO();
    }

    public PrincipaisInfoAnimal(int id, String nomeDoAnimal, String especieDoAnimal, 
            String racaDoAnimal, int idadeDoAnimal, String generoDoAnimal, 
            String nomeDoProprietario, String cpfDoProprietario) {
        
        Pdao = new PrincipaisInfoAnimalDAO();
        this.id = id;
        this.nomeDoAnimal = nomeDoAnimal;
        this.especieDoAnimal = especieDoAnimal;
        this.racaDoAnimal = racaDoAnimal;
        this.idadeDoAnimal = idadeDoAnimal;
        this.generoDoAnimal = generoDoAnimal;
        this.nomeDoProprietario = nomeDoProprietario;
        this.cpfDoProprietario = cpfDoProprietario;
    }

/*
    //Metodos
    public void mostrarDadosDoAnimal() {

        //Declaração de Objetos
        AnimalDAO animal = new AnimalDAO();
        
        //Declaração de variaveis
        int aux = 0; //Variavel auxiliar para verificar se o Animal esta cadastrado 
        int idAnimal; //Variavel auxiliar para verificar se a Veterinario cadastrado 
        String nome;
        String cpf;
        
        //Entrada de dados
        idAnimal = animal.IdAnimal(nome, cpf);

        //Foreach para varrer e mostrar toda lista resultante do salvarCarteiraNaLista()
        for (PrincipaisInfoAnimal info : Pdao.salvarInfoNaLista()) {

            //if para mostrar os dados correspondentes ao CRMV passado
            if (info.getId() == idAnimal ) {
                
                //jTextPane1.setText("Numero de ID: " + animal.getNumeroID() + "\nNome: " + animal.getNome()+ "\nIdade: " + animal.getIdade() +
                 //                  "\nEspecie: " + animal.getEspecie() + "\nRaça: " + animal.getRaca() + "\nCpf do DONO: " + animal.getProprietarioCPF());
                aux++;
            }
        }
        if (aux == 0) {
            System.out.println("\nENTRADA INVÁLIDA! TENTE NOVAMENTE!!\n");
        }

    }*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getNomeDoAnimal() {
        return nomeDoAnimal;
    }

    public void setNomeDoAnimal(String nomeDoAnimal) {
        this.nomeDoAnimal = nomeDoAnimal;
    }

    public String getEspecieDoAnimal() {
        return especieDoAnimal;
    }

    public void setEspecieDoAnimal(String especieDoAnimal) {
        this.especieDoAnimal = especieDoAnimal;
    }

    public String getRacaDoAnimal() {
        return racaDoAnimal;
    }

    public void setRacaDoAnimal(String racaDoAnimal) {
        this.racaDoAnimal = racaDoAnimal;
    }

    public int getIdadeDoAnimal() {
        return idadeDoAnimal;
    }

    public void setIdadeDoAnimal(int idadeDoAnimal) {
        this.idadeDoAnimal = idadeDoAnimal;
    }

    public String getGeneroDoAnimal() {
        return generoDoAnimal;
    }

    public void setGeneroDoAnimal(String generoDoAnimal) {
        this.generoDoAnimal = generoDoAnimal;
    }

    public String getNomeDoProprietario() {
        return nomeDoProprietario;
    }

    public void setNomeDoProprietario(String nomeDoProprietario) {
        this.nomeDoProprietario = nomeDoProprietario;
    }

    public String getCpfDoProprietario() {
        return cpfDoProprietario;
    }

    public void setCpfDoProprietario(String cpfDoProprietario) {
        this.cpfDoProprietario = cpfDoProprietario;
    }

    
}
