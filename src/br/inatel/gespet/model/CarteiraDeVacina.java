package br.inatel.gespet.model;

import br.inatel.gespet.model.Vacina;
import java.sql.Timestamp;
import java.util.Scanner;
import br.inatel.gespet.control.CarteiraDeVacinaDAO;

public class CarteiraDeVacina {

    //Atributos
    private int numeroID;
    private String nome;
    private String especie;
    private String raca;
    private String genero;
    private int idade;
    private Timestamp dataDeCriacao;
    private int numID;
    private CarteiraDeVacinaDAO Cdao;

    //Construtores
    public CarteiraDeVacina() {
        Cdao = new CarteiraDeVacinaDAO();
    }

    public CarteiraDeVacina(int numeroID, String nome, String especie, 
            String raca, String genero, int idade, 
            Timestamp dataDeCriacao, int numID) {

        Cdao = new CarteiraDeVacinaDAO();
        this.numeroID = numeroID;
        this.nome = nome;
        this.especie = especie;
        this.raca = raca;
        this.genero = genero;
        this.idade = idade;
        this.dataDeCriacao = dataDeCriacao;
        this.numID = numID;
    }

    //Metodos da Carteira de Vacina
    public boolean inserir(String nome,String especie, String raca, String genero, int idade, int id) {

        //Delcaração de Objetos
        CarteiraDeVacina novaCarteirinha = new CarteiraDeVacina();

        //Declaração de Variáveis
        boolean resultadoC;

        //Atribuição de Valores
        novaCarteirinha.setNome(nome);
        novaCarteirinha.setEspecie(especie);
        novaCarteirinha.setRaca(raca);
        novaCarteirinha.setGenero(genero);
        novaCarteirinha.setIdade(idade);
        novaCarteirinha.setNumID(id);
        
        //Inserindo no banco
        resultadoC = Cdao.insert(novaCarteirinha);

        return resultadoC;
    }

    public void mostrarDadosDaCarteirinha() {

        //Declaração de Objetos
        Scanner teclado = new Scanner(System.in);

        //Declaração de variaveis
        int aux = 0; //Variavel auxiliar para verificar se o Animal esta cadastrado 
        int idAux; //Variavel auxiliar para verificar se a Veterinario cadastrado 

        //Entrada de dados
        System.out.print("Entre com o ID do animal que gostaria de ver os dados da Vacina: ");
        idAux = teclado.nextInt();
        teclado.nextLine();

        //Foreach para varrer e mostrar toda lista resultante do salvarCarteiraNaLista()
        for (CarteiraDeVacina carteira : Cdao.salvarCarteiraNaLista()) {

            //IF para conferir se idAux esta na lista
            if (carteira.getNumeroID() == idAux) {
                System.out.println("");
                System.out.println("Numero de ID: " + carteira.getNumeroID());
                System.out.println("Nome do Animal: " + carteira.getNome());
                System.out.println("Especie do Animal: " + carteira.getEspecie());
                System.out.println("Raca do Animal: " + carteira.getRaca());
                System.out.println("Genero do Animal: " + carteira.getGenero());
                System.out.println("Idade do Animal: " + carteira.getIdade());
                System.out.println("Data de Criacao: " + carteira.getDeCriacao());
                System.out.println("______________________________________\n");
                aux++;
            }
        }
        if (aux == 0) {

            System.out.println("\nNÃO tem nenhum animal cadastrado com esse ID!");

        }
    }

    public boolean deletarCarteira(int id) {

        //Declaração de objetos
        Vacina vacinas = new Vacina();
        
        //Declaração de Variaveis
        boolean resultadoC;
        boolean resultadoV;
        boolean resultadoF;

        //Deletando no Banco
        resultadoV = vacinas.deletarVacina(id); //Deletando tabela Vacina
        resultadoC = Cdao.deleteCarteiraPeloID(id);

        if (resultadoC && resultadoV) {
            resultadoF = true;
        } else {
            resultadoF = false;
        }

        return resultadoF;
    }

    //Getters e Setters para numero de Identificação
    public int getNumeroID() {
        return numeroID;
    }

    //Getters e Setters Data de Inicio da Carteira
    public Timestamp getDeCriacao() {
        return dataDeCriacao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

   
   
    //Getters e Setters para ID de animal
    public int getNumID() {
        return numID;
    }

    public void setNumID(int numID) {
        this.numID = numID;
    }

}
