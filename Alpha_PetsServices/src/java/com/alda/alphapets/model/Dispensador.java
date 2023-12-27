
package com.alda.alphapets.model;

/**
 *
 * @author Alda
 */
public class Dispensador {
    private int idDispensador;
    private String numeroSerie;
    private String depositoComida;
    private String depositoAgua;
    private String platoComida;
    private String platoAgua;
    private String rellenar;

    public Dispensador(int idDispensador, String numeroSerie, String depositoComida, String depositoAgua, String platoComida, String platoAgua, String rellenar) {
        this.idDispensador = idDispensador;
        this.numeroSerie = numeroSerie;
        this.depositoComida = depositoComida;
        this.depositoAgua = depositoAgua;
        this.platoComida = platoComida;
        this.platoAgua = platoAgua;
        this.rellenar = rellenar;
    }

    public Dispensador() {
    }

    public int getIdDispensador() {
        return idDispensador;
    }

    public void setIdDispensador(int idDispensador) {
        this.idDispensador = idDispensador;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public String getDepositoComida() {
        return depositoComida;
    }

    public void setDepositoComida(String depositoComida) {
        this.depositoComida = depositoComida;
    }

    public String getDepositoAgua() {
        return depositoAgua;
    }

    public void setDepositoAgua(String depositoAgua) {
        this.depositoAgua = depositoAgua;
    }

    public String getPlatoComida() {
        return platoComida;
    }

    public void setPlatoComida(String platoComida) {
        this.platoComida = platoComida;
    }

    public String getPlatoAgua() {
        return platoAgua;
    }

    public void setPlatoAgua(String platoAgua) {
        this.platoAgua = platoAgua;
    }
    
    public String getRellenar() {
        return rellenar;
    }
    
    public void setRellenar(String rellenar){
        this.rellenar = rellenar;
    }
}
