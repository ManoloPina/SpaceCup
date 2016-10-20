/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import conexao.Conexao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import modelo.Lancamento;
/**
 *
 * @author manolopina
 */
public class LancamentoDAO extends DAO implements DaoInterface {
        //Insere uma turma a tabela turma
    
    SimpleDateFormat formatador = new SimpleDateFormat("HH:mm");
    
    
    
    public void inserir(Lancamento lancamento, String grupo) throws ParseException {
        
        int grupoId = this.getGrupoId(grupo);
        
        Date tempoApogeu = new SimpleDateFormat("HH:mm").parse(lancamento.getTempoApogeuDescida());
        
        
        this.sql = "INSERT INTO LANCAMENTO_FOGUETE ("
                + "ALTITUDE_MEDIA, VELOCIDADE_MAXIMA, TAXA_DESCIDA, TEMPO_APOGEU_DESCIDA, "
                + "PICO_ACELERACAO, ACELERACAO_MEDIA, TEMPO_PROPULSAO, TEMPO_EJECAO, "
                + "ALTITUDE_EJECAO, DURACAO, GRUPO_ID "
                + ") VALUES(?, ?, ?, ?, ?, ?, ? , ?, ?, ?, ?)";
        try {
            this.connection = Conexao.getConnection();    
            this.prepareStatment = this.connection.prepareStatement(sql);
            this.prepareStatment.setDouble(1, lancamento.getAltitudeMedia());
            this.prepareStatment.setDouble(2, lancamento.getVelocidadeMaxima());
            this.prepareStatment.setDouble(3, lancamento.getTaxaDescida());
            this.prepareStatment.setTime(4, java.sql.Time.valueOf(tempoApogeu.getTime()));
            this.prepareStatment.setDouble(5, lancamento.getPicoAceleracao());
            this.prepareStatment.setDouble(6, lancamento.getAceleracaoMedia());
            this.prepareStatment.setString(7, lancamento.getTempoPropulsao());
            this.prepareStatment.setString(8, lancamento.getTempoEjecao());
            this.prepareStatment.setDouble(9, lancamento.getAltitudeEjecao());
            this.prepareStatment.setString(10, lancamento.getDuracaoVoo());
            this.prepareStatment.setInt(11, grupoId);
            this.prepareStatment.executeUpdate();
        }catch(SQLException e) {
           System.out.println(e.getMessage());
           JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    @Override
    //Lista todos elementos da tabela
    public ArrayList<Lancamento> listar() {
       
        this.sql = "SELECT ALUNO.NOME, ALUNO.RM, TURMA.NOME, GRUPO.NOME FROM ALUNO "
                + "INNER JOIN TURMA "
                + "ON ALUNO.TURMA_ID=TURMA.ID "
                + "INNER JOIN GRUPO "
                + "ON ALUNO.GRUPO_ID=GRUPO.ID";
       
       ArrayList<Lancamento> lista = new ArrayList();
       
        try {
            this.connection = Conexao.getConnection();    
            this.prepareStatment = this.connection.prepareStatement(sql);
            this.result = this.prepareStatment.executeQuery();    
            while(this.result.next()) {                
//                lista.add(new Lancamento(
//                        this.result.getString("TURMA.NOME"),
//                        this.result.getString("ALUNO.NOME"),
//                        this.result.getInt("ALUNO.RM"),
//                        this.result.getString("GRUPO.NOME")
//                ));
            }
        }catch(Exception e) {
           System.out.println(e.getMessage());
        }
        
        return lista;
    }

    public ArrayList<Lancamento> pesquisar(Lancamento lancamento) {
        
      this.sql = "SELECT (NOME) FROM ALUNO WHERE NOME LIKE ?";
        
        this.sql = this.sql
        .replace("!", "!!")
        .replace("%", "!%")
        .replace("_", "!_")
        .replace("[", "![");
       
       ArrayList<Lancamento> lista = new ArrayList();
       
        try {
            this.connection = Conexao.getConnection();    
            this.prepareStatment = this.connection.prepareStatement(sql);
//            this.prepareStatment.setString(1, "%" + lancamento.getAlunoNome()+ "%");
            this.result = this.prepareStatment.executeQuery();
            while(this.result.next()) {                
//                lista.add(new Lancamento(
//                        this.result.getString("TURMA.NOME"),
//                        this.result.getString("ALUNO.NOME"),
//                        this.result.getInt("ALUNO.RM"),
//                        this.result.getString("GRUPO.NOME")
//                ));
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
    
    
    public void alterar(Lancamento lancamento, String grupo) {
        
        int grupoId = this.getGrupoId(grupo);
        
        this.sql = "UPDATE ALUNO "
                + "SET NOME=?, RM=?, TURMA_ID=?, GRUPO_ID=? "
                + "WHERE RM=?";
        
//        try {
//            this.connection = Conexao.getConnection();
//            this.prepareStatment = this.connection.prepareStatement(sql);
//            this.prepareStatment.setString(1, a.getAlunoNome());
//            this.prepareStatment.setInt(2, aluno.getRM());
//            this.prepareStatment.setInt(3, turmaId);
//            this.prepareStatment.setInt(4, grupoId);
//            this.prepareStatment.setInt(5, alunoRM);
//            this.prepareStatment.executeUpdate();
//        JOptionPane.showMessageDialog(null, "Grupo alterado com sucesso!");
//        }catch(SQLException | HeadlessException e) {
//            JOptionPane.showMessageDialog(null, e.getMessage());
//        }
    }
    

    public int getGrupoId(String grupo) {
        int grupoId = 0;
        this.sql = "SELECT ID FROM GRUPO WHERE NOME = ?";
        try {
           this.connection = Conexao.getConnection(); 
           this.prepareStatment = this.connection.prepareStatement(sql);
           this.prepareStatment.setString(1, grupo);
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
