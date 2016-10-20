/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexao.Conexao;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.Turma;
import modelo.Grupo;
import modelo.Lancamento;


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
    public ArrayList<Turma> listar() {
       
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
    
    public ResultSet listarTabela() {
        this.sql = "SELECT NOME FROM TURMA";
        ResultSet tableListResult = null;
        try {
            this.connection = conexao.getConnection();
            this.prepareStatment = this.connection.prepareStatement(sql);
            tableListResult = this.prepareStatment.executeQuery();
        }catch(Exception e) {
            System.out.println("Erro:"+e.getMessage());
        }
        return tableListResult;
    }
    
    public ResultSet pesquisarTable(String nome) {
        this.sql = "SELECT (NOME) FROM TURMA WHERE NOME LIKE ?";
        
        this.sql = this.sql
        .replace("!", "!!")
        .replace("%", "!%")
        .replace("_", "!_")
        .replace("[", "![");
        
        ResultSet tableListResult = null;
        
        try {
            this.connection = conexao.getConnection();
            this.prepareStatment = this.connection.prepareStatement(sql);
            this.prepareStatment.setString(1, "%" + nome + "%");
            tableListResult = this.prepareStatment.executeQuery();
            System.out.println(tableListResult);
        }catch(Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
        return tableListResult;
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
    
    public void alterarTurma(Turma turmaSelecionada, Turma turmaNova) {
        this.sql = "UPDATE TURMA SET NOME=? WHERE NOME=?";
        try {
            this.prepareStatment = this.connection.prepareStatement(sql);
            this.prepareStatment.setString(1, turmaNova.getNome());
            this.prepareStatment.setString(2, turmaSelecionada.getNome());
            this.prepareStatment.executeUpdate();
        }catch(Exception e) {
            JOptionPane.showMessageDialog(null, "Erro:"+e.getMessage());
        }
        
    }
    
}
