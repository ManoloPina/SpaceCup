/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexao.Conexao;
import java.awt.HeadlessException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.Aluno;


/**
 *
 * @author manolopina
 */
public class AlunoDAO extends DAO implements DaoInterface{
        
    //Insere uma turma a tabela turma
    public void inserir(Aluno aluno) {
        int turmaId = this.getTurmaId(aluno);
        int grupoId = this.getGrupoId(aluno);
        
        this.sql = "INSERT INTO ALUNO (RM, NOME, TURMA_ID, GRUPO_ID) VALUES (?, ?, ?, ?)";
        try {
            this.connection = Conexao.getConnection();    
            this.prepareStatment = this.connection.prepareStatement(sql);
            this.prepareStatment.setInt(1, aluno.getRM());
            this.prepareStatment.setString(2, aluno.getAlunoNome());
            this.prepareStatment.setInt(3, turmaId);
            this.prepareStatment.setInt(4, grupoId);
            this.prepareStatment.executeUpdate();
        }catch(Exception e) {
           JOptionPane.showConfirmDialog(null, e.getMessage());
        }
    }

    @Override
    //Lista todos elementos da tabela
    public ArrayList<Aluno> listar() {
       
        this.sql = "SELECT ALUNO.NOME, ALUNO.RM, TURMA.NOME, GRUPO.NOME FROM ALUNO "
                + "INNER JOIN TURMA "
                + "ON ALUNO.TURMA_ID=TURMA.ID "
                + "INNER JOIN GRUPO "
                + "ON ALUNO.GRUPO_ID=GRUPO.ID";
       
       ArrayList<Aluno> lista = new ArrayList();
       
        try {
            this.connection = Conexao.getConnection();    
            this.prepareStatment = this.connection.prepareStatement(sql);
            this.result = this.prepareStatment.executeQuery();    
            while(this.result.next()) {                
                lista.add(new Aluno(
                        this.result.getString("TURMA.NOME"),
                        this.result.getString("ALUNO.NOME"),
                        this.result.getInt("ALUNO.RM"),
                        this.result.getString("GRUPO.NOME")
                ));
            }
        }catch(Exception e) {
           System.out.println(e.getMessage());
        }
        
        return lista;
    }

    public ArrayList<Aluno> pesquisar(Aluno aluno) {
        
      this.sql = "SELECT (NOME) FROM ALUNO WHERE NOME LIKE ?";
        
        this.sql = this.sql
        .replace("!", "!!")
        .replace("%", "!%")
        .replace("_", "!_")
        .replace("[", "![");
       
       ArrayList<Aluno> lista = new ArrayList();
       
        try {
            this.connection = Conexao.getConnection();    
            this.prepareStatment = this.connection.prepareStatement(sql);
            this.prepareStatment.setString(1, "%" + aluno.getAlunoNome()+ "%");
            this.result = this.prepareStatment.executeQuery();
            while(this.result.next()) {                
                lista.add(new Aluno(
                        this.result.getString("TURMA.NOME"),
                        this.result.getString("ALUNO.NOME"),
                        this.result.getInt("ALUNO.RM"),
                        this.result.getString("GRUPO.NOME")
                ));
            }
        }catch(Exception e) {
           System.out.println(e.getMessage());
        }
        
        return lista;
    }
    
    public void deletar(int rm) {
        
        this.sql = "DELETE FROM ALUNO WHERE RM = ?";
        
        try {
            this.connection = Conexao.getConnection();
            this.prepareStatment = this.connection.prepareStatement(sql);
            this.prepareStatment.setInt(1, rm);
            this.prepareStatment.executeUpdate();
        }catch(Exception e) {
            System.out.println("Erro: "+e.getMessage());
        }
    }
    
    
    public void alterar(Aluno aluno, int alunoRM) {
        
        int grupoId = this.getGrupoId(aluno);
        int turmaId = this.getTurmaId(aluno);
        
        this.sql = "UPDATE ALUNO "
                + "SET NOME=?, RM=?, TURMA_ID=?, GRUPO_ID=? "
                + "WHERE RM=?";
        
        try {
            this.connection = Conexao.getConnection();
            this.prepareStatment = this.connection.prepareStatement(sql);
            this.prepareStatment.setString(1, aluno.getAlunoNome());
            this.prepareStatment.setInt(2, aluno.getRM());
            this.prepareStatment.setInt(3, turmaId);
            this.prepareStatment.setInt(4, grupoId);
            this.prepareStatment.setInt(5, alunoRM);
            this.prepareStatment.executeUpdate();
        JOptionPane.showMessageDialog(null, "Grupo alterado com sucesso!");
        }catch(SQLException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public int getTurmaId(Aluno aluno) {
        int turmaId = 0;
        this.sql = "SELECT ID FROM TURMA WHERE NOME = ?";
        try {
           this.connection = Conexao.getConnection(); 
           this.prepareStatment = this.connection.prepareStatement(sql);
           this.prepareStatment.setString(1, aluno.getTurmaNome());
           this.result = this.prepareStatment.executeQuery();
           if(this.result.next()) {
               turmaId = this.result.getInt("id");  
           }
        }catch(Exception e) {
           System.out.println("Erro"+ e.getMessage());
        }
        return turmaId;
    }
    
    public int getGrupoId(Aluno aluno) {
        int grupoId = 0;
        this.sql = "SELECT ID FROM GRUPO WHERE NOME = ?";
        try {
           this.connection = Conexao.getConnection(); 
           this.prepareStatment = this.connection.prepareStatement(sql);
           this.prepareStatment.setString(1, aluno.getGrupoNome());
           this.result = this.prepareStatment.executeQuery();
           if(this.result.next()) {
               grupoId = this.result.getInt("id");  
           }
        }catch(Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return grupoId;
    }
    
    public ResultSet pesquisarTable(String alunoNome) {
        this.sql = "SELECT ALUNO.NOME, ALUNO.RM, TURMA.NOME, GRUPO.NOME FROM ALUNO "
                + "INNER JOIN TURMA "
                + "ON ALUNO.TURMA_ID=TURMA.ID "
                + "INNER JOIN GRUPO "
                + "ON ALUNO.GRUPO_ID=GRUPO.ID "
                + "WHERE ALUNO.NOME LIKE ?";
                
        
        this.sql = this.sql
        .replace("!", "!!")
        .replace("%", "!%")
        .replace("[", "![");
        
        ResultSet tableListResult = null;
        
        try {
            this.connection = Conexao.getConnection();
            this.prepareStatment = this.connection.prepareStatement(sql);
            this.prepareStatment.setString(1, "%" + alunoNome + "%");
            tableListResult = this.prepareStatment.executeQuery();
            System.out.println(tableListResult);
        }catch(Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
        return tableListResult;
    }
}
