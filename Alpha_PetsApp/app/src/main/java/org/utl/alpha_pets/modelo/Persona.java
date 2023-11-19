package org.utl.alpha_pets.modelo;

public class Persona{
    int idPersona;
    String nombrePersona;
    String usuario;
    String contrasenia;

    public Persona(int idPersona, String nombrePersona, String usuario, String contrasenia){
        this.idPersona=idPersona;
        this.nombrePersona=nombrePersona;
        this.usuario=usuario;
        this.contrasenia=contrasenia;
    }

    public Persona(){
    }

    public int getIdPersona(){
        return idPersona;
    }

    public void setIdPersona(int idPersona){
        this.idPersona=idPersona;
    }

    public String getNombrePersona(){
        return nombrePersona;
    }

    public void setNombrePersona(String nombrePersona){
        this.nombrePersona=nombrePersona;
    }

    public String getUsuario(){
        return usuario;
    }

    public void setUsuario(String usuario){
        this.usuario=usuario;
    }

    public String getContrasenia(){
        return contrasenia;
    }

    public void setContrasenia(String contrasenia){
        this.contrasenia=contrasenia;
    }
}
