/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import conexao.Conexao;
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
    public ArrayList listar() {
       
        this.sql = "SELECT * FROM TURMA";
       
       ArrayList<Aluno> lista = new ArrayList();
       
        try {
            this.connection = Conexao.getConnection();    
            this.prepareStatment = this.connection.prepareStatement(sql);
            this.result = this.prepareStatment.executeQuery();    
            while(this.result.next()) {                
//                lista.add(new Aluno(this.result.getString("NOME")));
            }
        }catch(Exception e) {
           System.out.println(e.getMessage());
        }
        
        return lista;
    }

    public ArrayList pesquisar(Aluno aluno) {
        
        this.sql = "SELECT * FROM TURMA WHERE NOME = ?";
        
        ArrayList<Aluno> lista = new ArrayList();
        
//        try {
//           this.connection = Conexao.getConnection(); 
//           this.prepareStatment = this.connection.prepareStatement(sql);
//           this.prepareStatment.setString(1, turma.getNome());
//           this.result = this.prepareStatment.executeQuery();
//           while(this.result.next()) {
//               lista.add(new Turma(turma.getNome()));
//           }  
//        }catch(Exception e) {
//           System.out.println("Erro"+ e.getMessage());
//        }
     return lista;
    }
    
    public void deletar(Aluno turma) {
        
//        this.sql = "DELETE FROM TURMA WHERE NOME = ?";
//        
//        try {
//            this.connection = Conexao.getConnection();
//            this.prepareStatment = this.connection.prepareStatement(sql);
//            this.prepareStatment.setString(1, turma.getNome());
//            this.prepareStatment.executeUpdate();
//        }catch(Exception e) {
//            System.out.println("Erro: "+e.getMessage());
//        }
        
        
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
}
