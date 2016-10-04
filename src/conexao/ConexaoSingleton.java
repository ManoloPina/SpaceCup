/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author manolopina
 */
public class ConexaoSingleton {
    private static Connection connection;
    private static String usuario = "root";
    private static String senha = "root";
    private static String url = "jdbc:oracle:thin:@192.168.60.15:1521:ORCL";
    
    public static synchronized Connection getConnection() {
        if(connection == null) {
            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                connection = DriverManager.getConnection(url, usuario, senha);
            }catch(ClassNotFoundException | SQLException e) {
                System.out.println("Erro:"+e.getMessage());
            }            
        }
        return connection;
    }
}
