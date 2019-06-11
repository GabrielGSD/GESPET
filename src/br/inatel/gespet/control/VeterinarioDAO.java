package br.inatel.gespet.control;

import br.inatel.gespet.model.Veterinario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class VeterinarioDAO {

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

            JOptionPane.showMessageDialog(null, "Erro ao Conectar ao BD");
            System.out.println(ex);

            sucesso = false;
        }
        return sucesso;
    }

    //Metodos VeterinarioDAO
    public boolean insert(Veterinario novoVeterinario) {

        //Sempre conectar ao banco e sempre fechar(BOA PRATICA) 
        connectToDb();

        //Comando SQL
        String sql = "INSERT INTO VETERINARIO(NUMERO_CRMV,SENHA) VALUES (?,?);";

        try {

            pst = con.prepareStatement(sql);
            pst.setInt(1, novoVeterinario.getCrmv()); //Qual valor sera jogado no primeiro "?"
            pst.setString(2, novoVeterinario.getSenha()); //Qual valor sera jogado no segundo "?"
            pst.execute(); //Executar comando
            sucesso = true;

        } catch (SQLException ex) {

            System.out.println("Erro ao Inserir VETERINARIO");
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

                JOptionPane.showMessageDialog(null, "Erro ao fechar o Banco");
                System.out.println(ex);

            }
        }
        return sucesso;
    }

    public ArrayList<Veterinario> salvarVeterinarioEmLista() {

        //Declarando o ArrayList onde sera armazenado os dados do Banco 
        ArrayList<Veterinario> listaDeVeterinario = new ArrayList<>();

        //Conectanto ao Banco
        connectToDb();

        //Comando SQL
        String sql = "SELECT * FROM VETERINARIO";

        try {

            st = con.createStatement();
            rs = st.executeQuery(sql);

            //Lendo as tuplas
            while (rs.next()) {

                //Armazenando em um Veterinario Temporário
                Veterinario veterinarioTemp = new Veterinario(rs.getInt("NUMERO_CRMV"), rs.getString("SENHA"));

                //Inserindo na Lista
                listaDeVeterinario.add(veterinarioTemp);

                sucesso = true;
            }

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Erro ao Salvar Veterinario na lista");
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

                System.out.println("Erro ao fechar o BD");
                System.out.println(ex);

            }

        }
        return listaDeVeterinario;
    }

    public boolean atualizarAnimalSenhaPeloCrmv(String senha, int numId) {

        //Conectando ao Banco
        connectToDb();

        //Comando SQL
        String sql = "UPDATE VETERINARIO SET SENHA = ? WHERE NUMERO_CRMV = ?";

        try {

            pst = con.prepareStatement(sql);
            pst.setString(1, senha); //Qual valor sera jogado no primeiro "?"
            pst.setInt(2, numId); //Qual valor sera jogado no segundo "?"
            pst.execute(); //Executar comando
            sucesso = true;

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Erro ao Atuaizar a SENHA");
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
