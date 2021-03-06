/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexao.Conexao;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.Turma;
import modelo.Grupo;
import modelo.Lancamentto;


/**
 *
 * @author manolopina
 */
public class TurmaDAO extends DAO implements DaoInterface {
    
    
    
    //Insere uma turma a tabela turma
    public void inserir(Turma turma) {
        this.sql = "INSERT INTO TURMA (NOME) VALUES (?)";
        try {
            this.connection = Conexao.getConnection();    
            this.prepareStatment = this.connection.prepareStatement(sql);
            this.prepareStatment.setString(1, turma.getNome());
            this.prepareStatment.executeUpdate();
            JOptionPane.showMessageDialog(null, "Turma cadastrada com sucesso!");
        }catch(Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    @Override
    //Lista todos elementos da tabela
    public ArrayList listar() {
       
        this.sql = "SELECT * FROM TURMA";
       
       ArrayList<Turma> lista = new ArrayList();
       
        try {
            this.connection = Conexao.getConnection();    
            this.prepareStatment = this.connection.prepareStatement(sql);
            this.result = this.prepareStatment.executeQuery();    
            while(this.result.next()) {                
                lista.add(new Turma(this.result.getString("NOME")));
            }
        }catch(Exception e) {
           System.out.println(e.getMessage());
        }
        
        return lista;
    }

    public ArrayList pesquisar(Turma turma) {
        
        this.sql = "SELECT * FROM TURMA WHERE NOME = ?";
        
        ArrayList<Turma> lista = new ArrayList();
        
        try {
           this.connection = Conexao.getConnection(); 
           this.prepareStatment = this.connection.prepareStatement(sql);
           this.prepareStatment.setString(1, turma.getNome());
           this.result = this.prepareStatment.executeQuery();
           while(this.result.next()) {
               lista.add(new Turma(turma.getNome()));
           }  
        }catch(Exception e) {
           System.out.println("Erro"+ e.getMessage());
        }
     return lista;
    }
    
    public void deletar(Turma turma) {
        
        this.sql = "DELETE FROM TURMA WHERE NOME = ?";
        
        try {
            this.connection = Conexao.getConnection();
            this.prepareStatment = this.connection.prepareStatement(sql);
            this.prepareStatment.setString(1, turma.getNome());
            this.prepareStatment.executeUpdate();
        }catch(Exception e) {
            System.out.println("Erro: "+e.getMessage());
        }
        
        
    }
    
}
