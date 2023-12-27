
package com.alda.alphapets.rest;

import com.alda.alphapets.core.DispensadorController;
import com.alda.alphapets.core.PersonaController;
import com.alda.alphapets.model.Mascota;
import com.google.gson.Gson;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 *
 * @author Alda
 */
@Path("persona")
public class RESTPersona {
    @Path("agregar")
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    public Response crearCuenta(@FormParam("datosPersona") @DefaultValue("") String datosPersona) throws Exception{
        String out = null;
        Gson gson = new Gson();
        Mascota m = new Mascota();
        PersonaController pc = new PersonaController();
        DispensadorController dc = new DispensadorController();

        try{
            m = gson.fromJson(datosPersona, Mascota.class);
            
            if(m.getPersona().getDispensador().getNumeroSerie() == null || m.getPersona().getDispensador().getNumeroSerie() == ""){
                out = """
                      {"error": "Debe ingresar el numero de serie del dispensador para continuar."}
                      """;
                return Response.status(Response.Status.OK).entity(out).build();
            } else{
                m.getPersona().getDispensador().setIdDispensador(dc.insertarDispensador(m.getPersona().getDispensador()));
                
                if(m.getTamanioMascota() == null || m.getTamanioMascota() == ""){
                    out = """
                      {"error": "Debe ingresar el tamaño de la mascota para continuar."}
                      """;
                    return Response.status(Response.Status.OK).entity(out).build();
                }
                if(m.getRazaMascota() == null || m.getRazaMascota() == ""){
                    out = """
                      {"error": "Debe ingresar la raza de la mascota para continuar."}
                      """;
                    return Response.status(Response.Status.OK).entity(out).build();
                }
                if(m.getEdadMascota() == 0){
                    out = """
                      {"error": "Debe ingresar la edad de la mascota para continuar."}
                      """;
                return Response.status(Response.Status.OK).entity(out).build();
                }
                if(m.getNombreMascota() == null || m.getNombreMascota() == ""){
                    out = """
                      {"error": "Debe ingresar el nombre de la mascota para continuar."}
                      """;
                    return Response.status(Response.Status.OK).entity(out).build();
                }
                if(m.getPersona().getNombrePersona() == null || m.getPersona().getNombrePersona() == ""){
                    out = """
                      {"error": "Debe ingresar su nombre para continuar."}
                      """;
                    return Response.status(Response.Status.OK).entity(out).build();
                }
                if(m.getPersona().getDispensador().getIdDispensador() == -1 || m.getPersona().getDispensador().getIdDispensador() == 0){
                    out = """
                      {"error": "Hubo un error al registrar el dispensador. Intente de nuevo."}
                      """;
                    return Response.status(Response.Status.OK).entity(out).build();
                }
                if(m.getPersona().getUsuario().getContrasenia() == null || m.getPersona().getUsuario().getContrasenia() == ""){
                    out = """
                          {"error": "Debe ingresar una contraseña para continuar."}
                          """;
                    return Response.status(Response.Status.OK).entity(out).build();
                }
                if(m.getPersona().getUsuario().getNombreUsuario() == null || m.getPersona().getUsuario().getNombreUsuario() == ""){
                    out = """
                          {"error": "Debe ingresar un nombre de usuario continuar."}
                          """;
                    return Response.status(Response.Status.OK).entity(out).build();
                }
                if(m == null){
                    out = """
                          {"error": "Debe ingresar todos los datos de registro para continuar."}
                          """;
                    return Response.status(Response.Status.OK).entity(out).build();
                }
                
                m.setIdMascota(pc.crearCuenta(m));
                
                if(m.getIdMascota() == -1 || m.getIdMascota() == 0){
                    out = """
                      {"error": "Error al registrar la cuenta. Intente mas tarde."}
                      """;
                } else{
                    out = gson.toJson(m);
                }
            }
        } catch(Exception ex){
            ex.printStackTrace();
            out = """
                  {"exception": "%s"}
                  """;
            out = String.format(out, ex.getMessage());
        }
        return Response.status(Response.Status.OK).entity(out).build();
    }
    
    @Path("actualizar")
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    public Response actualizarCuenta(@FormParam("datosPersona") @DefaultValue("") String datosPersona) throws Exception{
        String out = null;
        Gson gson = new Gson();
        Mascota m = new Mascota();
        PersonaController pc = new PersonaController();
        
        try{
            m = gson.fromJson(datosPersona, Mascota.class);
            
            if(m.getIdMascota() == -1 || m.getIdMascota() == 0){
                out = """
                      {"error": "Debe ingresar el identificador de la mascota para continuar."}
                      """;
                return Response.status(Response.Status.OK).entity(out).build();
            }
            if(m.getPersona().getIdPersona() == -1 || m.getPersona().getIdPersona() == 0){
                out = """
                      {"error": "Debe ingresar el identificador de la persona para continuar."}
                      """;
                return Response.status(Response.Status.OK).entity(out).build();
            }
            if(m.getPersona().getUsuario().getIdUsuario() == -1 || m.getPersona().getUsuario().getIdUsuario() == 0){
                out = """
                      {"error": "Debe ingresar el identificador del usuario para continuar."}
                      """;
                return Response.status(Response.Status.OK).entity(out).build();
            }
            if(m.getTamanioMascota() == null || m.getTamanioMascota() == ""){
                out = """
                  {"error": "Debe ingresar el tamaño de la mascota para continuar."}
                  """;
                return Response.status(Response.Status.OK).entity(out).build();
            }
            if(m.getRazaMascota() == null || m.getRazaMascota() == ""){
                out = """
                  {"error": "Debe ingresar la raza de la mascota para continuar."}
                  """;
                return Response.status(Response.Status.OK).entity(out).build();
            }
            if(m.getEdadMascota() == 0){
                out = """
                  {"error": "Debe ingresar la edad de la mascota para continuar."}
                  """;
                return Response.status(Response.Status.OK).entity(out).build();
            }
            if(m.getNombreMascota() == null || m.getNombreMascota() == ""){
                out = """
                  {"error": "Debe ingresar el nombre de la mascota para continuar."}
                  """;
                return Response.status(Response.Status.OK).entity(out).build();
            }
            if(m.getPersona().getNombrePersona() == null || m.getPersona().getNombrePersona() == ""){
                out = """
                  {"error": "Debe ingresar su nombre para continuar."}
                  """;
                return Response.status(Response.Status.OK).entity(out).build();
            }
            if(m.getPersona().getUsuario().getContrasenia() == null || m.getPersona().getUsuario().getContrasenia() == ""){
                out = """
                      {"error": "Debe ingresar una contraseña para continuar."}
                      """;
                return Response.status(Response.Status.OK).entity(out).build();
            }
            if(m.getPersona().getUsuario().getNombreUsuario() == null || m.getPersona().getUsuario().getNombreUsuario() == ""){
                out = """
                      {"error": "Debe ingresar un nombre de usuario continuar."}
                      """;
                return Response.status(Response.Status.OK).entity(out).build();
            }
            if(m == null){
                out = """
                      {"error": "Debe ingresar todos los datos de registro para continuar."}
                      """;
                return Response.status(Response.Status.OK).entity(out).build();
            }
            
            if(pc.actualizarCuenta(m)){
                out = gson.toJson(m);
            } else{
                out = """
                      {"error": "Error al actualizar los datos del usuario. Intente de nuevo"}
                      """;
            }
        } catch(Exception ex){
            ex.printStackTrace();
            out = """
                  {"exception": "%s"}
                  """;
            out = String.format(out, ex.getMessage());
        }
        return Response.status(Response.Status.OK).entity(out).build();
    }
    
    @Path("activar")
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    public Response activarCuenta(@FormParam("datosPersona") @DefaultValue("") String datosPersona) throws Exception{
        String out = null;
        Gson gson = new Gson();
        Mascota m = new Mascota();
        PersonaController pc = new PersonaController();
        
        try{
            m = gson.fromJson(datosPersona, Mascota.class);
            
            if(m.getPersona().getUsuario().getIdUsuario() == 0){
                out = """
                      {"error": "Debe seleccionar el usuario para continuar."}
                      """;
                return Response.status(Response.Status.OK).entity(out).build();
            }
            if(m == null){
                out = """
                      {"error": "Debe ingresar todos los datos del usuario para continuar."}
                      """;
                return Response.status(Response.Status.OK).entity(out).build();
            }
            
            if(pc.activarCuenta(m)){
                out = gson.toJson(m);
            } else{
                out = """
                      {"error": "Error al activar el usuario. Intente de nuevo."}
                      """;
            }
        } catch(Exception ex){
            ex.printStackTrace();
            out = """
                  {"exception": "%s"}
                  """;
            out = String.format(out, ex.getMessage());
        }
        return Response.status(Response.Status.OK).entity(out).build();
    }
    
    @Path("eliminar")
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    public Response eliminarCuenta(@FormParam("datosPersona") @DefaultValue("") String datosPersona) throws Exception{
        String out = null;
        Gson gson = new Gson();
        Mascota m = new Mascota();
        PersonaController pc = new PersonaController();
        
        try{
            m = gson.fromJson(datosPersona, Mascota.class);
            
            if(m.getPersona().getUsuario().getIdUsuario() == 0){
                out = """
                      {"error": "Debe seleccionar el usuario para continuar."}
                      """;
                return Response.status(Response.Status.OK).entity(out).build();
            }
            if(m == null){
                out = """
                      {"error": "Debe ingresar todos los datos del usuario para continuar."}
                      """;
                return Response.status(Response.Status.OK).entity(out).build();
            }
            
            if(pc.eliminarCuenta(m)){
                out = gson.toJson(m);
            } else{
                out = """
                      {"error": "Error al eliminar el usuario. Intente de nuevo."}
                      """;
            }
        } catch(Exception ex){
            ex.printStackTrace();
            out = """
                  {"exception": "%s"}
                  """;
            out = String.format(out, ex.getMessage());
        }
        return Response.status(Response.Status.OK).entity(out).build();
    }
}
