package org.utl.alpha_pets.modelo;

public class Mascota{
    int idMascota;
    String nombreMascota;
    int edad;
    String raza;
    String tamanio;
    Persona persona;

    public Mascota(int idMascota, String nombreMascota, int edad, String raza, String tamanio, Persona persona){
        this.idMascota=idMascota;
        this.nombreMascota=nombreMascota;
        this.edad=edad;
        this.raza=raza;
        this.tamanio=tamanio;
        this.persona=persona;
    }

    public Mascota(){
        this.persona=new Persona();
    }

    public int getIdMascota(){
        return idMascota;
    }

    public void setIdMascota(int idMascota){
        this.idMascota=idMascota;
    }

    public String getNombreMascota(){
        return nombreMascota;
    }

    public void setNombreMascota(String nombreMascota){
        this.nombreMascota=nombreMascota;
    }

    public int getEdad(){
        return edad;
    }

    public void setEdad(int edad){
        this.edad=edad;
    }

    public String getRaza(){
        return raza;
    }

    public void setRaza(String raza){
        this.raza=raza;
    }

    public String getTamanio(){
        return tamanio;
    }

    public void setTamanio(String tamanio){
        this.tamanio=tamanio;
    }

    public Persona getPersona(){
        return persona;
    }

    public void setPersona(Persona persona){
        this.persona=persona;
    }
}
