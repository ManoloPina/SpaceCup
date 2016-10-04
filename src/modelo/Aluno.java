/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author manolopina
 */
public class Aluno  extends Turma {
    
    private String nome;
    private Integer RM;
    
    public Aluno(String nomeTurma,String nome, Integer RM) {
        super(nome);
        this.nome = nome;
        this.RM = RM;
    }
    
    public String getNome() {
        return this.nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getRM() {
        return RM;
    }

    public void setRM(Integer RM) {
        this.RM = RM;
    }
}
