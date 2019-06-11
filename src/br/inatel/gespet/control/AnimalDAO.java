package br.inatel.gespet.control;

import br.inatel.gespet.model.Animal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class AnimalDAO {

    //Declarando as classes que são utilizadas ara fazer a conexão com MYSQL
    public Connection con;
    //Comandos SQL Dinamicos
    public PreparedStatement pst; //Atualizar, Inserir, Deletar 
    // comandos SQL Estáticos
    public Statement st;
    // Representa a tabela resultate da pesquisa 
    public ResultSet rs;

    //Aributos bases
    private final String database = "pet_shop"; //nome do banco a ser utilizadao
    private final String url = "jdbc:mysql://127.0.0.1:3306/" + database + "?autoReconnect=true&useSSL=false";
    private final String user = "root"; //Usuario
    private final String passoword = "@snesDK64"; //Senha

    //Variavel de Controle
    private boolean sucesso;

    //Conectar ao BD
    public boolean connectToDb() {

        try {

            con = DriverManager.getConnection(url, user, passoword); //DriverManager: Utilizada para fazer a conecção
            sucesso = true;

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Erro ao fechar o BD");
            System.out.println(ex);
            sucesso = false;

        }
        return sucesso;
    }

    //Metodos do AnimalDAO
    //Retorna o ID pelo Nome e Cpf 
    public int IdAnimal(String nome, String cpf) {

        //Declaração de Variaveis
        int idAnimal = 0;

        //Conectando ao Banco
        connectToDb();

        //Comando SQL
        String sql = "SELECT NUMERO_ID FROM ANIMAL WHERE NOME = ? AND PROPRIETARIO_CPF = ? ";

        try {

            pst = con.prepareStatement(sql);
            pst.setString(1, nome); //Qual valor sera jogado no primeiro "?"
            pst.setString(2, cpf); //Qual valor sera jogado no segundo "?"
            rs = pst.executeQuery();

            rs.next();
            idAnimal = rs.getInt("NUMERO_ID");

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Erro ao buscar ID");
            idAnimal = 0;
            System.out.println(ex);
            sucesso = false;

        } finally {

            //Fechando o Banco
            try {

                if (con != null && pst != null) {
                    con.close();
                    pst.close();
                }

            } catch (SQLException ex) {

                JOptionPane.showMessageDialog(null, "Erro ao fechar o BD");
                System.out.println(ex);

            }

        }
        return idAnimal;
    }
    
    //Retorna o ID pelo Nome do Animal e o nome do Dono
    public int IdAnimalNome(String nome, String nomeDono) {

        //Declaração de Variaveis
        int idAnimal = 0;

        //Conectando ao Banco
        connectToDb();

        //Comando SQL
        String sql = "SELECT ANIMAL.NUMERO_ID AS 'ID' FROM ANIMAL, PROPRIETARIO WHERE ANIMAL.NOME = ? AND PROPRIETARIO.NOME = ?\n" +
"	AND PROPRIETARIO_CPF = ANIMAL.PROPRIETARIO_CPF ";

        try {

            pst = con.prepareStatement(sql);
            pst.setString(1, nome); //Qual valor sera jogado no primeiro "?"
            pst.setString(2, nomeDono); //Qual valor sera jogado no segundo "?"
            rs = pst.executeQuery();

            rs.next();
            idAnimal = rs.getInt("ID");

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Erro ao buscar ID");
            idAnimal = 0;
            System.out.println(ex);
            sucesso = false;

        } finally {

            //Fechando o Banco
            try {

                if (con != null && pst != null) {
                    con.close();
                    pst.close();
                }

            } catch (SQLException ex) {

                JOptionPane.showMessageDialog(null, "Erro ao fechar o BD");
                System.out.println(ex);

            }

        }
        return idAnimal;
    }

    //Retornado o ultimo ID inserido na tabela ANIMAL
    public int IdAnimal() {

        //Declaração de Variaveis
        int idAnimal = 0;

        //Conectando ao Banco
        connectToDb();

        //Comando SQL
        String sql = "SELECT MAX(NUMERO_ID) AS 'ID' FROM ANIMAL";

        try {

            st = con.createStatement();
            rs = st.executeQuery(sql);

            rs.next();
            idAnimal = rs.getInt("ID");

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Erro ao buscar ultimo ID inserido em Animal");
            System.out.println(ex);
            sucesso = false;

        } finally {

            //Fechando o Banco
            try {

                if (con != null && pst != null) {
                    con.close();
                    pst.close();
                }

            } catch (SQLException ex) {

                JOptionPane.showMessageDialog(null, "Erro ao fechar o BD");
                System.out.println(ex);

            }

        }
        return idAnimal;
    }

    //Inserir Animal
    public boolean insert(Animal novoAnimal) {

        //Conectando ao banco
        connectToDb();

        //Comando SQL
        String sql = "INSERT INTO ANIMAL(NOME,ESPECIE,RACA,GENERO,IDADE,PROPRIETARIO_CPF) VALUES (?,?,?,?,?,?);";

        try {

            pst = con.prepareStatement(sql);
            pst.setString(1, novoAnimal.getNome()); //Qual valor sera jogado no primeiro "?"
            pst.setString(2, novoAnimal.getEspecie()); //Qual valor sera jogado no segundo "?"
            pst.setString(3, novoAnimal.getRaca()); //Qual valor sera jogado no terceiro "?"
            pst.setString(4, novoAnimal.getGenero()); //Qual valor sera jogado no terceiro "?"
            pst.setInt(5, novoAnimal.getIdade()); //Qual valor sera jogado no quarto "?"
            pst.setString(6, novoAnimal.getProprietarioCPF()); //Qual valor sera jogado no quinto "?"
            pst.execute(); //Executar comando
            sucesso = true;

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Erro ao Inserir Animal");
            System.out.println(ex);
            sucesso = false;

        } finally {

            //Fechando BD
            try {

                if (con != null && pst != null) {
                    con.close();
                    pst.close();
                }

            } catch (SQLException ex) {

                JOptionPane.showMessageDialog(null, "Erro ao fechar o BD");
                System.out.println(ex);

            }

        }

        return sucesso;
    }

    //Criar Lista de Animal
    public ArrayList<Animal> salvarAnimaisNaLista() {

        //Declaração da lista que ira armazenar os dados do Banco
        ArrayList<Animal> listaDeAnimal = new ArrayList<>();

        //Conectando ao Banco
        connectToDb();

        //Comando SQL
        String sql = "SELECT * FROM ANIMAL";

        try {

            st = con.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {

                //Atribuindo dados a um animal temporario
                Animal animalTemp = new Animal(rs.getInt("NUMERO_ID"), rs.getString("NOME"),
                        rs.getString("ESPECIE"), rs.getString("RACA"), rs.getString("GENERO"),
                        rs.getInt("IDADE"), rs.getString("PROPRIETARIO_CPF"));

                //Inserindo na Lista
                listaDeAnimal.add(animalTemp);

                sucesso = true;
            }

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Erro ao buscar Animal");
            System.out.println(ex);
            sucesso = false;

        } finally {

            //Fechando o Banco
            try {

                if (con != null && pst != null) {
                    con.close();
                    pst.close();
                }

            } catch (SQLException ex) {

                JOptionPane.showMessageDialog(null, "Erro ao fechar o BD");
                System.out.println(ex);

            }

        }
        return listaDeAnimal;
    }

    //Deletando Animal pelo ID
    public boolean deleteAnimalPeloID(int id) {

        //Conectando ao Banco
        connectToDb();

        //Comando SQL
        String sql = "DELETE FROM ANIMAL WHERE NUMERO_ID = ?";

        try {

            pst = con.prepareStatement(sql);
            pst.setInt(1, id); //Qual valor sera jogado no "?"
            pst.execute(); //Executar comando
            sucesso = true;

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Erro ao deletar Animal");
            System.out.println(ex);
            sucesso = false;

        } finally {

            //Fechando o Banco
            try {

                if (con != null && pst != null) {
                    con.close();
                    pst.close();
                }

            } catch (SQLException ex) {

                JOptionPane.showMessageDialog(null, "Erro ao fechar o BD");
                System.out.println(ex);

            }
        }
        return sucesso;
    }

    //Deletando Tabela Resultante da Relação Veterinario e Animal
    public boolean deleteVhasAPeloID(int id) {

        //Conectando ao Banco
        connectToDb();

        //Comando SQL
        String sql = " DELETE FROM VETERINARIO_has_ANIMAL WHERE ANIMAL_NUMERO_ID = ? ";

        try {

            pst = con.prepareStatement(sql);
            pst.setInt(1, id); //Qual valor sera jogado no "?"
            pst.execute(); //Executar comando
            sucesso = true;

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Erro ao deletar VhasA");
            System.out.println(ex);
            sucesso = false;

        } finally {

            //Fechando o Banco
            try {

                if (con != null && pst != null) {
                    con.close();
                    pst.close();
                }

            } catch (SQLException ex) {

                JOptionPane.showMessageDialog(null, "Erro ao fechar o BD");
                System.out.println(ex);

            }
        }
        return sucesso;
    }

    //Atualizar dados do Animal pelo Cpf e nome
    public boolean atualizarAnimalRacaPeloNomeCpf(String raca, int numId) {

        //Conectando ao Banco
        connectToDb();

        //Comando SQL
        String sql = "UPDATE ANIMAL SET RACA = ? WHERE NUMERO_ID = ?";

        try {

            pst = con.prepareStatement(sql);
            pst.setString(1, raca); //Qual valor sera jogado no primeiro "?"
            pst.setInt(2, numId); //Qual valor sera jogado no segundo "?"
            pst.execute(); //Executar comando
            sucesso = true;

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Erro ao Atuaizar a Raca");
            System.out.println(ex);
            sucesso = false;

        } finally {

            //Fechando o Banco
            try {

                if (con != null && pst != null) {
                    con.close();
                    pst.close();
                }

            } catch (SQLException ex) {

                JOptionPane.showMessageDialog(null, "Erro ao fechar o BD");
                System.out.println(ex);

            }

        }

        return sucesso;
    }

    public boolean atualizarAnimalSexoPeloNomeCpf(String sexo, int numId) {

        //Conectando ao Banco
        connectToDb();

        //Comando SQL
        String sql = "UPDATE ANIMAL SET GENERO = ? WHERE NUMERO_ID = ?";

        try {

            pst = con.prepareStatement(sql);
            pst.setString(1, sexo); //Qual valor sera jogado no primeiro "?"
            pst.setInt(2, numId); //Qual valor sera jogado no segundo "?"
            pst.execute(); //Executar comando
            sucesso = true;

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Erro ao Atuaizar o sexo");
            System.out.println(ex);
            sucesso = false;

        } finally {

            //Fechando o Banco
            try {

                if (con != null && pst != null) {
                    con.close();
                    pst.close();
                }

            } catch (SQLException ex) {

                JOptionPane.showMessageDialog(null, "Erro ao fechar o BD");
                System.out.println(ex);

            }

        }

        return sucesso;
    }
    
    public boolean atualizarAnimalIdadePeloNomeCpf(String idade, int numId) {

        //Conectando ao Banco
        connectToDb();

        //Comando SQL
        String sql = "UPDATE ANIMAL SET IDADE = ? WHERE NUMERO_ID = ?";

        try {
            pst = con.prepareStatement(sql);

            pst.setString(1, idade); //Qual valor sera jogado no primeiro "?"
            pst.setInt(2, numId); //Qual valor sera jogado no segundo "?"
            pst.execute(); //Executar comando

            sucesso = true;

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Erro ao Atuaizar a Idade");
            System.out.println(ex);
            sucesso = false;

        } finally {

            //Fechando o Banco
            try {

                if (con != null && pst != null) {
                    con.close();
                    pst.close();
                }

            } catch (SQLException ex) {

                JOptionPane.showMessageDialog(null, "Erro ao fechar o BD");
                System.out.println(ex);

            }

        }

        return sucesso;
    }

    public boolean atualizarAnimalNomePeloNomeCpf(String nomeA, int numId) {

        //Conectando ao Banco
        connectToDb();

        //Comando SQL
        String sql = "UPDATE ANIMAL SET NOME = ? WHERE NUMERO_ID = ?";

        try {

            pst = con.prepareStatement(sql);
            pst.setString(1, nomeA);//Qual valor sera jogado no primeiro "?"
            pst.setInt(2, numId); //Qual valor sera jogado no segundo "?"
            pst.execute(); //Executar comando
            sucesso = true;

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Erro ao Atuaizar o Nome");
            System.out.println(ex);
            sucesso = false;

        } finally {

            //Fechando o Banco
            try {

                if (con != null && pst != null) {
                    con.close();
                    pst.close();
                }

            } catch (SQLException ex) {

                JOptionPane.showMessageDialog(null, "Erro ao fechar o BD");
                System.out.println(ex);

            }

        }

        return sucesso;
    }

    public boolean atualizarAnimalEspeciePeloNomeCpf(String especie, int numId) {

        //Conectando ao Banco
        connectToDb();

        //Comando SQL
        String sql = "UPDATE ANIMAL SET ESPECIE = ? WHERE NUMERO_ID = ?";

        try {

            pst = con.prepareStatement(sql);
            pst.setString(1, especie); //Qual valor sera jogado no primeiro "?"
            pst.setInt(2, numId); //Qual valor sera jogado no segundo "?"
            pst.execute();
            sucesso = true;

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Erro ao Atuaizar o Especie");
            System.out.println(ex);
            sucesso = false;

        } finally {
            try {

                if (con != null && pst != null) {
                    con.close();
                    pst.close();
                }

            } catch (SQLException ex) {
                System.out.println("Erro ao fechar o bd");
                System.out.println(ex);
            }

        }

        return sucesso;
    }

    //Inserindo dados na tabela originada do relacionamento N-N do Veterinario com as Vacinas
    public boolean insertVhasA(int crmv, int id) {

        //Conectando ao Banco
        connectToDb();

        //Comando SQL
        String sql = "INSERT INTO VETERINARIO_has_ANIMAL(VETERINARIO_NUMERO_CRMV,ANIMAL_NUMERO_ID) VALUES(?,?)";

        try {

            pst = con.prepareStatement(sql);
            pst.setInt(1, crmv); //Qual valor sera jogado no primeiro "?"
            pst.setInt(2, id); //Qual valor sera jogado no segundo "?"
            pst.execute(); //Executar comando

            sucesso = true;

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Erro ao Inserir na Tabela VETERINARIO_has_ANIMAL");
            System.out.println(ex);
            sucesso = false;

        } finally {

            //Fechando o Banco
            try {

                if (con != null && pst != null) {
                    con.close();
                    pst.close();
                }

            } catch (SQLException ex) {

                JOptionPane.showMessageDialog(null, "Erro ao fechar o BD");
                System.out.println(ex);

            }
        }
        return sucesso;
    }
}
