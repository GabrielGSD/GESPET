package br.inatel.gespet.control;

//package petshopgsd;
//
//import java.util.Scanner;
//import javax.swing.JOptionPane;

//public class PetShopGSD {
//
//    public static void main(String[] args) {
//
//        //Declaração dos objetos
//        Scanner teclado = new Scanner(System.in);
//        Veterinario veterinario = new Veterinario();
//        Proprietario proprietario = new Proprietario();
//        Animal animal = new Animal();
//        CarteiraDeVacina carteria = new CarteiraDeVacina();
//        PrincipaisInfoAnimal info = new PrincipaisInfoAnimal();
//        Vacina vacina = new Vacina();
//
//        //Declaração de Variaveis
//        int controle;
//        int controle1;
//        int controle2;
//        int crmvAux = 0;
//        boolean saida;
//        boolean saida1;
//        boolean saida2;
//
//        do {
//            //Atribuição de Valores das Variaveis de Saida
//            saida = false;
//            saida1 = false;
//            saida2 = false;
//
//            //Menu Inicial
//            System.out.println("________________MENU__________________");
//            System.out.println("Digite 1 para CADASTRAR o Veterinario");
//            System.out.println("Digite 2 para ABRIR o painel do Veterinario");
//            System.out.println("Digite 0 para FECHAR o programa");
//            System.out.println("______________________________________\n");
//
//            //Entrada do Operador de Controle
//            System.out.print("Entre com a operação desejada: ");
//            controle = teclado.nextInt();
//
//            switch (controle) {
//                case 1:
//
//                    //Cadastrando Veterinario
//                    veterinario.inserirVeterinarios();
//
//                    break;
//                case 2:
//                    //Painel de controle do Veterinario
//                   // crmvAux = 123;
//
//                    if (true) {
//                        do {
//                            System.out.println("--LOGIN EFETUADO COM SUCESSO!--");
//                            
//                            //Menu do Veterinario
//                            System.out.println("__________PAINEL DO VETERINARIO__________");
//                            System.out.println("Digite 1 para fazer um CADASTRO do paciente");
//                            System.out.println("Digite 2 para ENTRAR no Painel do paciente");
//                            System.out.println("Digite 0 para Sair");
//                            System.out.println("_________________________________________\n");
//
//                            //Entrada do Operador de Controle
//                            System.out.print("Entre com o que deseja: ");
//                            controle1 = teclado.nextInt();
//                            teclado.nextLine();
//
//                            switch (controle1) {
//                                case 1:
//
//                                    //Cadastrando Paciente
//                                    proprietario.inserir(123);
//
//                                    break;
//                                case 2: //Painel de controle do Paciente
//                                    do {
//                                        //Menu 3
//                                        System.out.println("__________PAINEL DO PACIENTE__________");
//                                        System.out.println("Digite 1 para LISTAR os paciente");
//                                        System.out.println("Digite 2 para BUSCAR um paciente");
//                                        System.out.println("Digite 3 para ATUALIZAR um paciente");
//                                        System.out.println("Digite 4 para DELETAR um paciente");
//                                        System.out.println("Digite 5 para MOSTRAR dados da Carteira de vacina de um paciente");
//                                        System.out.println("Digite 7 para ATUALIZAR dados da Proprietario");
//                                        System.out.println("Digite 8 para MOSTRAR os dados mais importantes do paciente");
//                                        System.out.println("Digite 0 para Sair");
//                                        System.out.println("______________________________________\n");
//
//                                        System.out.print("Entre com o que deseja: ");
//                                        controle2 = teclado.nextInt();
//                                        teclado.nextLine();
//
//                                        switch (controle2) {
//                                            case 1:
//
//                                                //Listar animais
//                                                animal.listarAnimal();
//
//                                                break;
//                                            case 2:
//
//                                                //Buscar animais
//                                                animal.buscarAnimal();
//
//                                                break;
//                                            case 3:
//
//                                                //Editar
//                                                animal.atualizarAnimal();
//
//                                                break;
//                                            case 4:
//
//                                                //Deletar
//                                                animal.deletarAnimalPeloId();
//
//                                                break;
//                                            case 5:
//
//                                                //Mostrar dados da Carteirinha
//                                                carteria.mostrarDadosDaCarteirinha();
//
//                                                break;
//                                            case 6:
//                                                
//                                                //Inserir VACINAS
//                                                vacina.inserir();
//                                                
//                                                break;
//                                            case 7:
//
//                                                //Atualizar dados do Proprietario
//                                                proprietario.atualizarProprietario();
//
//                                                break;
//                                            case 8:
//
//                                                //Mostrar dados mais importantes do Animal
//                                                info.mostrarDadosDaCarteirinha();
//
//                                                break;
//                                            case 0:
//
//                                                //Sair do PAINEL DO PACIENTE
//                                                saida2 = true;
//
//                                                break;
//                                            default:
//                                                break;
//
//                                        }
//                                    } while (!saida2);
//                                case 0:
//
//                                    //Sair do PAINEL DO VETERINARIO
//                                    saida1 = true;
//
//                                    break;
//                                default:
//                                    break;
//                            }
//                        } while (!saida1);
//                    } else {
//                        
//                        JOptionPane.showMessageDialog(null, "Veterinario NÃO Cadastrado");
//                    
//                    }
//                    break;
//                case 0:
//
//                    //Sair do PROGRAMA
//                    saida = true;
//
//                    break;
//                default:
//                    break;
//            }
//
//        } while (!saida);
//
//    }
//
//}
