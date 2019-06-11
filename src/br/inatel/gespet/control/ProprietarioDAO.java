package br.inatel.gespet.control;

import br.inatel.gespet.model.Proprietario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ProprietarioDAO {

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
    private final String url = "jdbc:mysql://localhost:3306/" + database + "?autoReconnect=true&useSSL=false";
    private final String user = "root";
    private final String passoword = "12345";

    //Variavel de Controle
    private boolean sucesso;

    //Conectar ao BD
    public boolean connectToDb() {

        try {

            con = DriverManager.getConnection(url, user, passoword); //DriverManager: Utilizada para fazer a conecção
            sucesso = true;

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Erro ao Conectar ao BD");
            System.out.println(ex);

            sucesso = false;
        }
        return sucesso;
    }

    //Metodos do ProprietarioDAO
    public boolean insert(Proprietario novoUsuario) {

        //Conectando ao Banco 
        connectToDb();

        //Comando SQL
        String sql = "INSERT INTO PROPRIETARIO(CPF,NOME,RUA,BAIRRO,CELULAR) VALUES (?,?,?,?,?);";

        try {

            pst = con.prepareStatement(sql);
            pst.setString(1, novoUsuario.getCpf()); //Qual valor sera jogado no primeiro "?"
            pst.setString(2, novoUsuario.getNome()); //Qual valor sera jogado no segundo "?"
            pst.setString(3, novoUsuario.getRua()); //Qual valor sera jogado no terceiro "?"
            pst.setString(4, novoUsuario.getBairro()); //Qual valor sera jogado no terceiro "?"
            pst.setInt(5, novoUsuario.getCelular()); //Qual valor sera jogado no quarto "?"
            pst.execute(); //Executar comando
            sucesso = true;

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Erro ao Inserir Proprietario");
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

    
    public ArrayList<Proprietario> buscarUsuariosSemFiltro() {

        //Declarção do ArrayList que ira armazenar os dados do Banco
        ArrayList<Proprietario> listaDeProprietarios = new ArrayList<>();

        //Conectando ao Banco
        connectToDb();

        //Comando SQL
        String sql = "SELECT * FROM PROPRIETARIO";

        try {

            st = con.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {

                //Armazenando dados no proprietario temporario
                Proprietario proprietarioTemp = new Proprietario(rs.getString("NOME"), rs.getString("CPF"),
                        rs.getString("RUA"), rs.getString("BAIRRO"), rs.getInt("CELULAR"));

                //Inserindo na Lista
                listaDeProprietarios.add(proprietarioTemp);

                sucesso = true;
            }

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Erro ao adicinar a lista");
            System.out.println(ex);

            sucesso = false;

        } finally {

            //Fechando Banco
            try {

                if (con != null && pst != null) {
                    con.close();
                    pst.close();
                }

            } catch (SQLException ex) {

                JOptionPane.showMessageDialog(null, "Erro ao fechar o Banco");
                System.out.println(ex);

            }

        }

        return listaDeProprietarios;
    }

    public boolean atualizarProprietarioCpfPeloCpf(String novoCpf, String numId) {

        //Conectando ao Banco
        connectToDb();

        //Comando SQL
        String sql = "UPDATE PROPRIETARIO SET CPF = ? WHERE CPF = ?";

        try {

            pst = con.prepareStatement(sql);
            pst.setString(1, novoCpf); //Qual valor sera jogado no primeiro "?"
            pst.setString(2, numId); //Qual valor sera jogado no segundo "?"
            pst.execute(); //Executar comando
            sucesso = true;

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Erro ao Atuaizar o Proprietario");
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

    public void atualizarProprietarioNomePeloCpf(String nome, String numId) {

        //Conectando ao Banco
        connectToDb();

        //Comando SQL
        String sql = "UPDATE PROPRIETARIO SET NOME = ? WHERE CPF = ?";

        try {

            pst = con.prepareStatement(sql);
            pst.setString(1, nome); //Qual valor sera jogado no primeiro "?"
            pst.setString(2, numId); //Qual valor sera jogado no segundo "?"
            pst.execute(); //Executar comando

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Erro ao Atuaizar o nome");
            System.out.println(ex);

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
    }

    public void atualizarProprietarioRuaPeloCpf(String rua, String numId) {

        //Conectando ao Banco
        connectToDb();

        //Comando SQL
        String sql = "UPDATE PROPRIETARIO SET RUA = ? WHERE CPF = ?";

        try {

            pst = con.prepareStatement(sql);
            pst.setString(1, rua); //Qual valor sera jogado no primeiro "?"
            pst.setString(2, numId); //Qual valor sera jogado no segundo "?"
            pst.execute(); //Executar comando

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Erro ao Atuaizar o Rua");
            System.out.println(ex);

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
    }
    
    public void atualizarProprietarioBairroPeloCpf(String bairro, String numId) {

        //Conectando ao Banco
        connectToDb();

        //Comando SQL
        String sql = "UPDATE PROPRIETARIO SET BAIRRO = ? WHERE CPF = ?";

        try {

            pst = con.prepareStatement(sql);
            pst.setString(1, bairro); //Qual valor sera jogado no primeiro "?"
            pst.setString(2, numId); //Qual valor sera jogado no segundo "?"
            pst.execute(); //Executar comando

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Erro ao Atuaizar o Bairro");
            System.out.println(ex);

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
    }

    public void atualizarProprietarioCelularPeloCpf(int celular, String numId) {

        //Conectando ao Banco
        connectToDb();

        //Comando SQL
        String sql = "UPDATE PROPRIETARIO SET CELULAR = ? WHERE CPF = ?";

        try {

            pst = con.prepareStatement(sql);
            pst.setInt(1, celular); //Qual valor sera jogado no primeiro "?"
            pst.setString(2, numId); //Qual valor sera jogado no segundo "?"
            pst.execute(); //Executar comando

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Erro ao Atuaizar o celular");
            System.out.println(ex);

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
    }

}
