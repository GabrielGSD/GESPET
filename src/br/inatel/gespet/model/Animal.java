package br.inatel.gespet.model;

import java.util.Scanner;
import br.inatel.gespet.control.AnimalDAO;
import br.inatel.gespet.control.CarteiraDeVacinaDAO;

public class Animal {

    //Atributos
    private int numeroID;
    private String especie;
    private String nome;
    private String raca;
    private String genero;
    private int idade;
    private String proprietarioCPF;
    public AnimalDAO Adao;

    //Contrutores
    public Animal() {
        Adao = new AnimalDAO();
    }

    public Animal(int numeroID, String nome, String especie, String raca, String genero, int idade, String proprietarioCPF) {
        Adao = new AnimalDAO();
        this.numeroID = numeroID;
        this.nome = nome;
        this.especie = especie;
        this.raca = raca;
        this.genero = genero;
        this.idade = idade;
        this.proprietarioCPF = proprietarioCPF;
    }

    //Metodos
    public boolean inserir(Animal anm, int crmv) {

        //Declaração de Objetos
        
        CarteiraDeVacina carteirinha = new CarteiraDeVacina();
        CarteiraDeVacinaDAO carteirinhaDAO = new CarteiraDeVacinaDAO();
        Scanner teclado = new Scanner(System.in);

        //Declaração de Variaveis
        boolean resultadoF;
        boolean resultadoC;
        boolean resultadoA;
        boolean resultadoVhasA;

        //Inserindo no banco
        resultadoA = Adao.insert(anm);

        //Entrada de dados da Carteirinha
        resultadoC = carteirinha.inserir(anm.getNome(),anm.getEspecie(),anm.getRaca(),anm.getGenero(),anm.getIdade(), Adao.IdAnimal());

        //Entrada de dados da tabela resultante da relação N-N de Veterinario e Animal
        resultadoVhasA = Adao.insertVhasA(crmv, Adao.IdAnimal());

        if (resultadoA && resultadoC && resultadoVhasA) {
            resultadoF = true;
        } else {
            resultadoF = false;
        }

        return resultadoF;
    }

    public void listarAnimal() {

        //Declaração de variaveis
        int aux = 0; //Variavel auxiliar para verificar se a Veterinario cadastrado 

        //Foreach para varrer e mostrar toda lista resultante do buscarVeterinarioSemFiltro()
        for (Animal animal : Adao.salvarAnimaisNaLista()) {
            System.out.println("");
            System.out.println("Numero de ID: " + animal.getNumeroID());
            System.out.println("Nome: " + animal.getNome());
            System.out.println("Idade: " + animal.getIdade());
            System.out.println("Especie: " + animal.getEspecie());
            System.out.println("Raça: " + animal.getRaca());
            System.out.println("Cpf do DONO: " + animal.getProprietarioCPF());
            System.out.println("______________________________________\n");
            aux++;
        }
        if (aux == 0) {
            System.out.println("\nNÃO tem nenhum animal cadastrado!");
        }
    }
/*
    public void atualizarAnimal() {

        //Declaração de objetos
        Scanner teclado = new Scanner(System.in);

        //Declaração de Variaveis
        int IdAux;
        String nome;
        String nomeD;
        String aux;

        //Atribuição de Valores 
        IdAux = Adao.IdAnimalNome(nome, nomeD);
        
        //Menu de Opções
        System.out.println("Oq deseja editar?\n"
                + "1 - nome\n"
                + "2 - especie\n"
                + "3 - raca\n"
                + "4 - idade\n");

        //Entrada de Dados
        System.out.print("Entre com o q deseja: ");
        controle = teclado.nextInt();
        teclado.nextLine();

        switch (controle) {
            case 1://Atualizar nome

                //Entrada de dados
                System.out.print("Entre com o novo nome:");
                aux = teclado.nextLine();
                System.out.print("Entre com o numero de identificação do animal:");
                auxID = teclado.nextInt();
                teclado.nextLine();

                //Atribuindo dados ao Banco
                Adao.atualizarAnimalNomePeloNomeCpf(aux, auxID);

                break;
            case 2://Atualizar especie

                //Entrada de dados
                System.out.print("Entre com o nova especie:");
                aux = teclado.nextLine();
                System.out.print("Entre com o numero de identificação do animal:");
                auxID = teclado.nextInt();
                teclado.nextLine();

                //Atribuindo dados ao Banco  
                Adao.atualizarAnimalEspeciePeloNomeCpf(aux, auxID);

                break;
            case 3://Atualizar nome

                //Entrada de dados
                System.out.print("Entre com o nova raça:");
                aux = teclado.nextLine();
                System.out.print("Entre com o numero de identificação do animal:");
                auxID = teclado.nextInt();
                teclado.nextLine();

                //Atribuindo dados ao Banco
                Adao.atualizarAnimalRacaPeloNomeCpf(aux, auxID);

                break;
            case 4://Atualizar nome

                //Entrada de dados
                System.out.print("Entre com o nova idade:");
                aux = teclado.nextLine();
                System.out.print("Entre com o numero de identificação do animal:");
                auxID = teclado.nextInt();
                teclado.nextLine();

                //Atribuindo dados ao Banco
                Adao.atualizarAnimalIdadePeloNomeCpf(aux, auxID);

                break;
            default:
                break;
        }
    }
*/
    public void deletarAnimalPeloId() {

        //Declaração de Objetos
        Scanner teclado = new Scanner(System.in);
        CarteiraDeVacina carteira = new CarteiraDeVacina();

        //Declaração de Variaveis
        boolean resultadoVhasA;
        boolean resultadoCarteira;
        boolean resultado;
        int id;

        //Entrad de dados
        System.out.print("Entre com o ID do animal a ser deletado: ");
        id = teclado.nextInt();
        teclado.nextLine();

        //Deletando no Banco
        resultadoVhasA = Adao.deleteVhasAPeloID(id); //Deletando tabela originada da relação
        resultado = Adao.deleteAnimalPeloID(id);
        resultadoCarteira = carteira.deletarCarteira(id); //Deletando carteira

        if (resultadoVhasA && resultado && resultadoCarteira) {
            System.out.println("Sucesso ao Deletar!");
        } else {
            System.out.println("Deu ruim ao Deletar");
        }
    }

    //Getters para ID
    public int getNumeroID() {
        return numeroID;
    }

    //Getters e Setters para especie
    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    //Getters e Setters para nome
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    //Getters e Setters para raca
    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    //Getters e Setters para CPF do Proprietario
    public String getProprietarioCPF() {
        return proprietarioCPF;
    }

    public void setProprietarioCPF(String proprietarioCPF) {
        this.proprietarioCPF = proprietarioCPF;
    }

    //Getters e Setters para Idade
    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    //Getters e Setters para genero
    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

}
