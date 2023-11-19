
package com.alda.alphapets.model;

/**
 *
 * @author Alda
 */
public class Mascota {
    private int idMascota;
    private String nombreMascota;
    private int edadMascota;
    private String razaMascota;
    private String tamanioMascota;
    private Persona persona;

    public Mascota(int idMascota, String nombreMascota, int edadMascota, String razaMascota, String tamanioMascota, Persona persona) {
        this.idMascota = idMascota;
        this.nombreMascota = nombreMascota;
        this.edadMascota = edadMascota;
        this.razaMascota = razaMascota;
        this.tamanioMascota = tamanioMascota;
        this.persona = persona;
    }

    public Mascota() {
    }

    public int getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(int idMascota) {
        this.idMascota = idMascota;
    }

    public String getNombreMascota() {
        return nombreMascota;
    }

    public void setNombreMascota(String nombreMascota) {
        this.nombreMascota = nombreMascota;
    }

    public int getEdadMascota() {
        return edadMascota;
    }

    public void setEdadMascota(int edadMascota) {
        this.edadMascota = edadMascota;
    }

    public String getRazaMascota() {
        return razaMascota;
    }

    public void setRazaMascota(String razaMascota) {
        this.razaMascota = razaMascota;
    }

    public String getTamanioMascota() {
        return tamanioMascota;
    }

    public void setTamanioMascota(String tamanioMascota) {
        this.tamanioMascota = tamanioMascota;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }
}
