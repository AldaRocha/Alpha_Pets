
package com.alda.alphapets.rest;

import com.alda.alphapets.model.Dispensador;
import com.alda.alphapets.core.DispensadorController;
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
@Path("dispensador")
public class RESTDispensador {
    @Path("actualizar")
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    public Response actualizarDispensador(@FormParam("datosDispensador") @DefaultValue("") String datosDispensador) throws Exception{
        String out = null;
        Gson gson = new Gson();
        Dispensador d = new Dispensador();
        DispensadorController dc = new DispensadorController();
        
        try{
            d = gson.fromJson(datosDispensador, Dispensador.class);
            
            if(d.getPlatoAgua() == null || d.getPlatoAgua() == ""){
                out = """
                      {"error": "Debe ingresar el contenido del plato de agua"}
                      """;
                return Response.status(Response.Status.OK).entity(out).build();
            }
            if(d.getPlatoComida() == null || d.getPlatoComida() == ""){
                out = """
                      {"error": "Debe ingresar el contenido del plato de comida"}
                      """;
                return Response.status(Response.Status.OK).entity(out).build();
            }
            if(d.getDepositoAgua() == null || d.getDepositoAgua() == ""){
                out = """
                      {"error": "Debe ingresar el contenido del deposito del agua"}
                      """;
                return Response.status(Response.Status.OK).entity(out).build();
            }
            if(d.getDepositoComida() == null || d.getDepositoComida() == ""){
                out = """
                      {"error": "Debe ingresar el contenido del deposito de comida"}
                      """;
                return Response.status(Response.Status.OK).entity(out).build();
            }
            if(d.getNumeroSerie() == null || d.getNumeroSerie() == ""){
                out = """
                      {"error": "Debe ingresar el numero de serie del dispensador."}
                      """;
                return Response.status(Response.Status.OK).entity(out).build();
            }
            if(d.getIdDispensador() == 0){
                out = """
                      {"error": "Debe ingresar el identificador del dispensador."}
                      """;
                return Response.status(Response.Status.OK).entity(out).build();
            }
            if(d == null){
                out = """
                      {"error": "Los datos del dispensador se estan enviando incorrectamente. Intente de nuevo."}
                      """;
                return Response.status(Response.Status.OK).entity(out).build();
            }
            
            if(dc.actualizarDispensador(d)){
                out = gson.toJson(d);
            } else{
                out = """
                      {"error": "Hubo un error al registrar los datos del dispensador. Intente de nuevo."}
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
    
    @Path("tiempo")
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    public Response actualizarRellenado(@FormParam("datosDispensador") @DefaultValue("") String datosDispensador) throws Exception{
        String out = null;
        Gson gson = new Gson();
        Dispensador d = new Dispensador();
        DispensadorController dc = new DispensadorController();
        
        try{
            d = gson.fromJson(datosDispensador, Dispensador.class);
            
            if(d == null){
                out = """
                      {"error": "Los datos del dispensador se estan enviando incorrectamente. Intente de nuevo."}
                      """;
                return Response.status(Response.Status.OK).entity(out).build();
            }
            if(d.getNumeroSerie() == null || d.getNumeroSerie() == ""){
                out = """
                      {"error": "Asegurese de enviar el numero se serie. Intente de nuevo."}
                      """;
                return Response.status(Response.Status.OK).entity(out).build();
            }
            if(d.getRellenar() == null || d.getRellenar() == ""){
                out = """
                      {"error": "Asegurese de enviar el la hora en la que se rellenara el dispensador. Intente de nuevo."}
                      """;
                return Response.status(Response.Status.OK).entity(out).build();
            }
            
            if(dc.actualizarTiempoRellenar(d)){
                out = gson.toJson(d);
            } else{
                out = """
                      {"error": "Hubo un error al registrar los datos del dispensador. Intente de nuevo."}
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
    
    @Path("obtener")
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    public Response obtener(@FormParam("datosDispensador") @DefaultValue("") String datosDispensador) throws Exception{
        String out = null;
        Gson gson = new Gson();
        Dispensador d = new Dispensador();
        DispensadorController dc = new DispensadorController();
        
        try{
            d = gson.fromJson(datosDispensador, Dispensador.class);
            
            if(d == null){
                out = """
                      {"error": "Los datos del dispensador se estan enviando incorrectamente. Intente de nuevo."}
                      """;
                return Response.status(Response.Status.OK).entity(out).build();
            }
            if(d.getIdDispensador() == 0){
                out = """
                      {"error": "Debe ingresar el identificador del dispensador."}
                      """;
                return Response.status(Response.Status.OK).entity(out).build();
            }
            if(d == null){
                out = """
                      {"error": "Los datos del dispensador se estan enviando incorrectamente. Intente de nuevo."}
                      """;
                return Response.status(Response.Status.OK).entity(out).build();
            }
            
            d = dc.getDatosDispensador(d);
            
            if(d != null){
                out = gson.toJson(d);
            } else{
                out = """
                      {"error": "Error al obtener los datos del dispensador."}
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
