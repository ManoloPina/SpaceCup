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
public class Grupo  {
    
    private int id;
    private String nome;
//    private ArrayList<String> componentes = new ArrayList();
    
    public Grupo(String nome) {
        this.nome = nome;
    }
    
    public Grupo(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
}
