/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexao.Conexao;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.Grupo;

/**
 *
 * @author manolopina
 */
public class GrupoDAO extends DAO implements DaoInterface {
    
    public void cadastrar(Grupo grupo) {
        this.sql = "INSERT INTO GRUPO (NOME) VALUES (?)";
        
        try {
            this.connection = Conexao.getConnection();
            this.prepareStatment = this.connection.prepareStatement(sql);
            this.prepareStatment.setString(1, grupo.getNome());
            this.prepareStatment.executeUpdate();
            JOptionPane.showMessageDialog(null, "Grupo cadastrado com sucesso!");
        }catch(Exception e) {
            JOptionPane.showMessageDialog(null, "Erro: "+e.getMessage());
        }
    }

    @Override
    public ArrayList<Grupo> listar() {
        this.sql = "SELECT * FROM GRUPO";
       
       ArrayList<Grupo> lista = new ArrayList();
       
        try {
            this.connection = Conexao.getConnection();    
            this.prepareStatment = this.connection.prepareStatement(sql);
            this.result = this.prepareStatment.executeQuery();    
            while(this.result.next()) {                
                lista.add(new Grupo( this.result.getInt("ID"), this.result.getString("NOME") ));
            }
        }catch(Exception e) {
           System.out.println(e.getMessage());
        }
        
        return lista;
    }
    
}