package br.inatel.gespet.control;

import br.inatel.gespet.model.Vacina;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class VacinaDAO {
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

    //Metodos da Vacina
    public ArrayList<Vacina> salvarVacinaNaLista(int id) {

        //Declaração do ArrayList que ira armazenar os dados do Banco
        ArrayList<Vacina> listaDeVacinas = new ArrayList<>();

        //Conectando ao Banco
        connectToDb();

        //Comando SQL
        String sql = "SELECT * FROM VACINA WHERE CARTEIRA_VACINA_ID = ?";

        try {

            pst = con.prepareStatement(sql);
            pst.setInt(1, id); //Qual valor sera jogado no primeiro "?"
            rs = pst.executeQuery();

            while (rs.next()) {

                //Armazenando em uma vacina temporaria
                Vacina vacinaTemp = new Vacina(rs.getString("NOME"), rs.getString("DATA_VACINA"),
                        rs.getString("DATA_PROXIMA_VACINA"), rs.getInt("CARTEIRA_VACINA_ID"));

                //Inserindo na Lista
                listaDeVacinas.add(vacinaTemp);

                sucesso = true;
            }

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Erro!");
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
        return listaDeVacinas;
    }
    
    public boolean insert(Vacina novaVacina) {

        //Conectando ao Banco 
        connectToDb();

        //Comando SQL
        String sql = "INSERT INTO VACINA(NOME,DATA_VACINA,DATA_PROXIMA_VACINA,CARTEIRA_VACINA_ID) VALUES (?,?,?,?);";

        try {

            pst = con.prepareStatement(sql);
            pst.setString(1, novaVacina.getNome()); //Qual valor sera jogado no primeiro "?"
            pst.setString(2, novaVacina.getDataVacina()); //Qual valor sera jogado no terceiro "?"
            pst.setString(3, novaVacina.getDataProximaVacina()); //Qual valor sera jogado no quarto "?"
            pst.setInt(4, novaVacina.getCarteiraNumID()); //Qual valor sera jogado no quinto "?"
            pst.execute(); //Executar comando
            sucesso = true;

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Erro ao Inserir VACINA");
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
    
    public boolean deleteVacinaPeloID(int id) {

        //Conectando ao Banco
        connectToDb();

        //Comando SQL
        String sql = "DELETE FROM VACINA WHERE CARTEIRA_VACINA_ID = ?";

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
}
