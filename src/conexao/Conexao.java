/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author manolopina
 */
public class Conexao {
    
    private static Connection connection;    
    private static String url = "jdbc:mysql://127.0.0.1:3306/SPACECUP";
    private static String usuario = "root";
    private static String senha = "Noir100%";
    
    public static Connection getConnection() {
        if(connection == null) {
            try {
               Class.forName("com.mysql.jdbc.Driver");
               connection = DriverManager.getConnection(url, usuario, senha);
            }
            catch(ClassNotFoundException e) {
                JOptionPane.showMessageDialog(null, "Erro ao carregar o driver de conexão\n"+e.getMessage());
            }
            catch(SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao estabelecer conexão com o banco de dados\n"+e);
                System.out.println("Erro:"+e.getMessage());
            }
        }
        return connection;
    }
    
}
