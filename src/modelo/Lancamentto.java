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
public class Lancamentto {
    private double altitudeMaxima;
    private double velocidadeMaxima;
    private Time tempoPropulsao;
    private double picoAceleracao;
    private double aceleracaoMedia;
    private Time tempoApogeuDescida;
    private Time tempoEjecao;
    private double altitudeEjecao;
    private double taxaDescida;
    private Time duracaoVoo;

    public Lancamentto(double altitudeMaxima, double velocidadeMaxima, Time tempoPropulsao, double picoAceleracao, double aceleracaoMedia, Time tempoApogeuDescida, Time tempoEjecao, double altitudeEjecao, double taxaDescida, Time duracaoVoo) {
        this.altitudeMaxima = altitudeMaxima;
        this.velocidadeMaxima = velocidadeMaxima;
        this.tempoPropulsao = tempoPropulsao;
        this.picoAceleracao = picoAceleracao;
        this.aceleracaoMedia = aceleracaoMedia;
        this.tempoApogeuDescida = tempoApogeuDescida;
        this.tempoEjecao = tempoEjecao;
        this.altitudeEjecao = altitudeEjecao;
        this.taxaDescida = taxaDescida;
        this.duracaoVoo = duracaoVoo;
    }

    public double getAltitudeMaxima() {
        return altitudeMaxima;
    }

    public double getVelocidadeMaxima() {
        return velocidadeMaxima;
    }

    public Time getTempoPropulsao() {
        return tempoPropulsao;
    }

    public double getPicoAceleracao() {
        return picoAceleracao;
    }

    public double getAceleracaoMedia() {
        return aceleracaoMedia;
    }

    public Time getTempoApogeuDescida() {
        return tempoApogeuDescida;
    }

    public Time getTempoEjecao() {
        return tempoEjecao;
    }

    public double getAltitudeEjecao() {
        return altitudeEjecao;
    }

    public double getTaxaDescida() {
        return taxaDescida;
    }

    public Time getDuracaoVoo() {
        return duracaoVoo;
    }

    public void setAltitudeMaxima(double altitudeMaxima) {
        this.altitudeMaxima = altitudeMaxima;
    }

    public void setVelocidadeMaxima(double velocidadeMaxima) {
        this.velocidadeMaxima = velocidadeMaxima;
    }

    public void setTempoPropulsao(Time tempoPropulsao) {
        this.tempoPropulsao = tempoPropulsao;
    }

    public void setPicoAceleracao(double picoAceleracao) {
        this.picoAceleracao = picoAceleracao;
    }

    public void setAceleracaoMedia(double aceleracaoMedia) {
        this.aceleracaoMedia = aceleracaoMedia;
    }

    public void setTempoApogeuDescida(Time tempoApogeuDescida) {
        this.tempoApogeuDescida = tempoApogeuDescida;
    }

    public void setTempoEjecao(Time tempoEjecao) {
        this.tempoEjecao = tempoEjecao;
    }

    public void setAltitudeEjecao(double altitudeEjecao) {
        this.altitudeEjecao = altitudeEjecao;
    }

    public void setTaxaDescida(double taxaDescida) {
        this.taxaDescida = taxaDescida;
    }

    public void setDuracaoVoo(Time duracaoVoo) {
        this.duracaoVoo = duracaoVoo;
    }
    
    
    
    
}
