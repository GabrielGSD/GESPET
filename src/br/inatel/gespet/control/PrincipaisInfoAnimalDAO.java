package br.inatel.gespet.control;

import br.inatel.gespet.model.PrincipaisInfoAnimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class PrincipaisInfoAnimalDAO {

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

    public ArrayList<PrincipaisInfoAnimal> salvarInfoNaLista() {

        //Declaração do ArrayList que ira armazenar os dados do Banco
        ArrayList<PrincipaisInfoAnimal> listaDeInfo = new ArrayList<>();

        //Conectando ao Banco
        connectToDb();

        //Comando SQL
        String sql = "SELECT * FROM PRINCIPAIS_INFO_ANIMAL";

        try {

            st = con.createStatement();
            rs = st.executeQuery(sql);

            while (rs.next()) {

                //Armazenando em uma carteira temporaria
                PrincipaisInfoAnimal carteiraTemp = new PrincipaisInfoAnimal(rs.getInt("IDENTIFICADOR DO ANIMAL"), rs.getString("NOME DO ANIMAL"),
                        rs.getString("ESPECIE DO ANIMAL"), rs.getString("RACA DO ANIMAL"), rs.getInt("IDADE DO ANIMAL"), rs.getString("GENERO DO ANIMAL"),
                        rs.getString("NOME DO DONO"), rs.getString("CPF DO DONO"));

                //Inserindo na Lista
                listaDeInfo.add(carteiraTemp);

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
        return listaDeInfo;
    }

}
