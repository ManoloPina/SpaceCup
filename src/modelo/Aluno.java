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
public class Aluno {
    
    private String alunoNome;
    private String turmaNome;
    private int RM;
    private String grupoNome;
    
    public Aluno(String turmaNome, String alunoNome, int RM, String grupoNome) {
        this.alunoNome = alunoNome;
        this.turmaNome = turmaNome;
        this.grupoNome = grupoNome;
        this.RM = RM;
    }

    public String getGrupoNome() {
        return grupoNome;
    }

    public void setGrupoNome(String grupoNome) {
        this.grupoNome = grupoNome;
    }
    
    public String getAlunoNome() {
        return this.alunoNome;
    }
    
    public void setAlunoNome(String alunoNome) {
        this.alunoNome = alunoNome;
    }

    public Integer getRM() {
        return RM;
    }

    public void setRM(Integer RM) {
        this.RM = RM;
    }

    public String getTurmaNome() {
        return turmaNome;
    }

    public void setTurmaNome(String turmaNome) {
        this.turmaNome = turmaNome;
    }
}
