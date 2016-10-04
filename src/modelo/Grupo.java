/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;

/**
 *
 * @author manolopina
 */
public class Grupo extends Turma {
    
    private String nome;
    private ArrayList<String> componentes = new ArrayList();
    
    public Grupo(String nomeTurma,String nome, ArrayList<String> componentes) {
        super(nomeTurma);
        this.nome = nome;
        this.componentes = componentes;
    }
}
