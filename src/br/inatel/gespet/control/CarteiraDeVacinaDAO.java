package br.inatel.gespet.control;

import br.inatel.gespet.model.CarteiraDeVacina;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class CarteiraDeVacinaDAO {

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
    private final String user = "root"; //Usuario
    private final String passoword = "12345"; //Senha

    //Variavel de controle
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

    //Metodos da Carteira
    public boolean insert(CarteiraDeVacina novaCarteirinha) {

        //Conectando ao Banco 
        connectToDb();

        //Comando SQL
        String sql = "INSERT INTO CARTEIRA_VACINA(NOME,ESPECIE,RACA,GENERO,IDADE,ANIMAL_NUMERO_ID) VALUES (?,?,?,?,?,?);";

        try {

            pst = con.prepareStatement(sql);
            pst.setString(1, novaCarteirinha.getNome()); //Qual valor sera jogado no primeiro "?"
            pst.setString(2, novaCarteirinha.getEspecie()); //Qual valor sera jogado no terceiro "?"
            pst.setString(3, novaCarteirinha.getRaca()); //Qual valor sera jogado no quarto "?"
            pst.setString(4, novaCarteirinha.getGenero()); //Qual valor sera jogado no quarto "?"
            pst.setInt(5, novaCarteirinha.getIdade()); //Qual valor sera jogado no quinto "?"
            pst.setInt(6, novaCarteirinha.getNumID()); //Qual valor sera jogado no quinto "?"
            pst.execute(); //Executar comando
            sucesso = true;

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Erro ao Inserir CARTEIRA de VACINA");
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

    public boolean deleteCarteiraPeloID(int id) {

        //Conectando ao Banco
        connectToDb();

        //Comando SQL
        String sql = "DELETE FROM CARTEIRA_VACINA WHERE NUMERO_ID = ?";

        try {

            pst = con.prepareStatement(sql);
            pst.setInt(1, id); //Qual valor sera jogado no "?"
            pst.execute(); //Executar comando
            sucesso = true;

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Erro ao deletar Carteira");
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

    public ArrayList<CarteiraDeVacina> salvarCarteiraNaLista() {

        //Declaração do ArrayList que ira armazenar os dados do Banco
        ArrayList<CarteiraDeVacina> listaDeCarteira = new ArrayList<>();

        //Conectando ao Banco
        connectToDb();

        //Comando SQL
        String sql = "SELECT * FROM CARTEIRA_VACINA";

        try {

            st = con.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {

                //Armazenando em uma carteira temporaria
                CarteiraDeVacina carteiraTemp = new CarteiraDeVacina(rs.getInt("NUMERO_ID"), rs.getString("NOME"),
                        rs.getString("ESPECIE"),rs.getString("RACA"),rs.getString("GENERO"),rs.getInt("IDADE"),
                        rs.getTimestamp("DATA_DE_CRIACAO"),rs.getInt("ANIMAL_NUMERO_ID"));

                //Inserindo na Lista
                listaDeCarteira.add(carteiraTemp);

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
        return listaDeCarteira;
    }

    public boolean atualizarCarteiraVacinaPeloID(String vacina, int numId) {

        //Conectando ao Banco
        connectToDb();

        //Comando SQL
        String sql = "UPDATE CARTEIRA_VACINA SET VACINAS = ? WHERE NUMERO_ID = ?";

        try {

            pst = con.prepareStatement(sql);
            pst.setString(1, vacina); //Qual valor sera jogado no primeiro "?"
            pst.setInt(2, numId); //Qual valor sera jogado no segundo "?"
            pst.execute(); //Executar comando
            sucesso = true;

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Erro ao atualizar CARTEIRA");
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

                JOptionPane.showMessageDialog(null, "Erro ao Conectar ao BD");
                System.out.println(ex);

            }

        }

        return sucesso;
    }

    public boolean atualizarCarteiraGeneroPeloID(String genero, int numId) {

        //Conectando ao Banco
        connectToDb();

        //Comando SQL
        String sql = "UPDATE CARTEIRA_VACINA SET GENERO = ? WHERE NUMERO_ID = ?";

        try {

            pst = con.prepareStatement(sql);
            pst.setString(1, genero); //Qual valor sera jogado no primeiro "?"
            pst.setInt(2, numId); //Qual valor sera jogado no segundo "?"
            pst.execute(); //Executar comando
            sucesso = true;

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Erro ao atualizar CARTEIRA");
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

                JOptionPane.showMessageDialog(null, "Erro ao Conectar ao BD");
                System.out.println(ex);

            }

        }

        return sucesso;
    }

    public boolean atualizarCarteiraDataProximaVacinaPeloID(String dataProximaVacina, int numId) {

        //Conectando ao Banco
        connectToDb();

        //Comando SQL
        String sql = "UPDATE CARTEIRA_VACINA SET DATA_PROXIMA_VACINA = ? WHERE NUMERO_ID = ?";

        try {

            pst = con.prepareStatement(sql);
            pst.setString(1, dataProximaVacina); //Qual valor sera jogado no primeiro "?"
            pst.setInt(2, numId); //Qual valor sera jogado no segundo "?"
            pst.execute(); //Executar comando

            sucesso = true;

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Erro ao atualizar CARTEIRA");
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

                JOptionPane.showMessageDialog(null, "Erro ao Conectar ao BD");
                System.out.println(ex);

            }

        }

        return sucesso;
    }

    public boolean atualizarCarteiraObservacaoPeloID(String observacao, int numId) {

        //Conectando ao Banco
        connectToDb();

        //Comando SQL
        String sql = "UPDATE CARTEIRA_VACINA SET OBSERVACOES = ? WHERE NUMERO_ID = ?";

        try {

            pst = con.prepareStatement(sql);
            pst.setString(1, observacao); //Qual valor sera jogado no primeiro "?"
            pst.setInt(2, numId); //Qual valor sera jogado no segundo "?"
            pst.execute(); //Executar comando
            sucesso = true;

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Erro ao atualizar CARTEIRA");
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

                JOptionPane.showMessageDialog(null, "Erro ao Conectar ao BD");
                System.out.println(ex);

            }

        }

        return sucesso;
    }
}
