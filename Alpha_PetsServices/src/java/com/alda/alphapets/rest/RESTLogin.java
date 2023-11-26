
package com.alda.alphapets.rest;

import com.alda.alphapets.core.LoginController;
import com.alda.alphapets.model.Mascota;
import com.alda.alphapets.model.Usuario;
import com.google.gson.Gson;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alda
 */
@Path("log")
public class RESTLogin {
    @Path("in")
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    public Response login(@FormParam("datosUsuario") @DefaultValue("") String datosUsuario) throws Exception{
        String out = null;
        Gson gson = new Gson();
        Usuario u = new Usuario();
        List<Mascota> lm = new ArrayList<>();
        LoginController lc = new LoginController();
        
        try{
            u = gson.fromJson(datosUsuario, Usuario.class);
            
            if(u.getNombreUsuario() == null || u.getNombreUsuario() == ""){
                out = """
                      {"error": "Debe ingresar el nombre de usuario para continuar."}
                      """;
                return Response.status(Response.Status.OK).entity(out).build();
            }
            if(u.getContrasenia() == null || u.getContrasenia() == ""){
                out = """
                      {"error": "Debe ingresar la contraseña para continuar."}
                      """;
                return Response.status(Response.Status.OK).entity(out).build();
            }
            if(u == null){
                out = """
                      {"error": "Los datos del usuario se estan enviando incorrectamente. Intente de nuevo."}
                      """;
                return Response.status(Response.Status.OK).entity(out).build();
            }
            
            lm = lc.login(u.getNombreUsuario(), u.getContrasenia());
            
            if(lm != null){
                out = gson.toJson(lm);
            } else{
                out = """
                      {"error": "Usuario y contraseña incorrecta. Intente de nuevo."}
                      """;
            }
        } catch(Exception ex){
            ex.printStackTrace();
            out = """
                  {"exception": "%S"}
                  """;
            out = String.format(out, ex.getMessage());
        }
        return Response.status(Response.Status.OK).entity(out).build();
    }
}
