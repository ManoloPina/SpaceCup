/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import conexao.Conexao;

/**
 *
 * @author manolopina
 */
public class DAO {
    
    Connection connection;
    Conexao conexao;
    PreparedStatement prepareStatment;
    ResultSet result;
    String sql;    
}
