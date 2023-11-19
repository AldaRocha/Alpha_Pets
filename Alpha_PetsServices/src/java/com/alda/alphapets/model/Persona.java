
package com.alda.alphapets.model;

/**
 *
 * @author Alda
 */
public class Persona {
    private int idPersona;
    private String nombrePersona;
    private Usuario usuario;
    private Dispensador dispensador;

    public Persona(int idPersona, String nombrePersona, Usuario usuario, Dispensador dispensador) {
        this.idPersona = idPersona;
        this.nombrePersona = nombrePersona;
        this.usuario = usuario;
        this.dispensador = dispensador;
    }

    public Persona() {
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombrePersona() {
        return nombrePersona;
    }

    public void setNombrePersona(String nombrePersona) {
        this.nombrePersona = nombrePersona;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Dispensador getDispensador() {
        return dispensador;
    }

    public void setDispensador(Dispensador dispensador) {
        this.dispensador = dispensador;
    }
}
