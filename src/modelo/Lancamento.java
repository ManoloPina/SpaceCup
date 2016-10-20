/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Time;

/**
 *
 * @author manolopina
 */
public class Lancamento {
    
    private double altitudeMedia;
    private double velocidadeMaxima;
    private double taxaDescida;
    private String tempoApogeuDescida;
    private double picoAceleracao;
    private double aceleracaoMedia;
    private String tempoPropulsao;
    private String tempoEjecao;
    private double altitudeEjecao;
    private String duracaoVoo;

    public Lancamento(
            double altitudeMedia, 
            double velocidadeMaxima, 
            double taxaDescida, 
            String tempoApogeuDescida, 
            double picoAceleracao, 
            double aceleracaoMedia, 
            String tempoPropulsao, 
            String tempoEjecao, 
            double altitudeEjecao, 
            String duracaoVoo
    ) {
        this.altitudeMedia = altitudeMedia;
        this.velocidadeMaxima = velocidadeMaxima;
        this.taxaDescida = taxaDescida;
        this.tempoApogeuDescida = tempoApogeuDescida;
        this.picoAceleracao = picoAceleracao;
        this.aceleracaoMedia = aceleracaoMedia;
        this.tempoPropulsao = tempoPropulsao;
        this.tempoEjecao = tempoEjecao;
        this.altitudeEjecao = altitudeEjecao;
        this.duracaoVoo = duracaoVoo;
    }

    public double getAltitudeMedia() {
        return altitudeMedia;
    }

    public double getVelocidadeMaxima() {
        return velocidadeMaxima;
    }

    public double getTaxaDescida() {
        return taxaDescida;
    }

    public String getTempoApogeuDescida() {
        return tempoApogeuDescida;
    }

    public double getPicoAceleracao() {
        return picoAceleracao;
    }

    public double getAceleracaoMedia() {
        return aceleracaoMedia;
    }

    public String getTempoPropulsao() {
        return tempoPropulsao;
    }

    public String getTempoEjecao() {
        return tempoEjecao;
    }

    public double getAltitudeEjecao() {
        return altitudeEjecao;
    }

    public String getDuracaoVoo() {
        return duracaoVoo;
    }

    public void setAltitudeMedia(double altitudeMedia) {
        this.altitudeMedia = altitudeMedia;
    }

    public void setVelocidadeMaxima(double velocidadeMaxima) {
        this.velocidadeMaxima = velocidadeMaxima;
    }

    public void setTaxaDescida(double taxaDescida) {
        this.taxaDescida = taxaDescida;
    }

    public void setTempoApogeuDescida(String tempoApogeuDescida) {
        this.tempoApogeuDescida = tempoApogeuDescida;
    }

    public void setPicoAceleracao(double picoAceleracao) {
        this.picoAceleracao = picoAceleracao;
    }

    public void setAceleracaoMedia(double aceleracaoMedia) {
        this.aceleracaoMedia = aceleracaoMedia;
    }

    public void setTempoPropulsao(String tempoPropulsao) {
        this.tempoPropulsao = tempoPropulsao;
    }

    public void setTempoEjecao(String tempoEjecao) {
        this.tempoEjecao = tempoEjecao;
    }

    public void setAltitudeEjecao(double altitudeEjecao) {
        this.altitudeEjecao = altitudeEjecao;
    }

    public void setDuracaoVoo(String duracaoVoo) {
        this.duracaoVoo = duracaoVoo;
    }
    
    
    
    
    
    
    
}
