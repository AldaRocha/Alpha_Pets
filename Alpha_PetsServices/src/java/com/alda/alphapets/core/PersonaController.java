
package com.alda.alphapets.core;

import com.alda.alphapets.connection.ConexionMySQL;
import com.alda.alphapets.model.Mascota;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

/**
 *
 * @author Alda
 */
public class PersonaController {
    public int crearCuenta(Mascota m) throws SQLException{
        String query = "{CALL crearCuenta(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        
        int idUsuarioGenerado = -1;
        int idPersonaGenerado = -1;
        int idMascotaGenerado = -1;
        
        ConexionMySQL connMySQL = new ConexionMySQL();
        
        Connection conn = connMySQL.open();
        
        CallableStatement clblsmt = conn.prepareCall(query);
        
        clblsmt.setString(1, m.getPersona().getUsuario().getNombreUsuario());
        clblsmt.setString(2, m.getPersona().getUsuario().getContrasenia());
        clblsmt.setInt(3, m.getPersona().getDispensador().getIdDispensador());
        clblsmt.setString(4, m.getPersona().getNombrePersona());
        clblsmt.setString(5, m.getNombreMascota());
        clblsmt.setInt(6, m.getEdadMascota());
        clblsmt.setString(7, m.getRazaMascota());
        clblsmt.setString(8, m.getTamanioMascota());
        
        clblsmt.registerOutParameter(9, Types.INTEGER);
        clblsmt.registerOutParameter(10, Types.INTEGER);
        clblsmt.registerOutParameter(11, Types.INTEGER);
        
        clblsmt.executeUpdate();
        
        idUsuarioGenerado = clblsmt.getInt(9);
        idPersonaGenerado = clblsmt.getInt(10);
        idMascotaGenerado = clblsmt.getInt(11);
        
        m.getPersona().getUsuario().setIdUsuario(idUsuarioGenerado);
        m.getPersona().setIdPersona(idPersonaGenerado);
        m.setIdMascota(idMascotaGenerado);
        
        clblsmt.close();
        conn.close();
        connMySQL.close();
        
        return idMascotaGenerado;
    }
    
    public boolean actualizarCuenta(Mascota m) throws SQLException{
        String query = "{CALL actualizarCuenta(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        boolean exito = true;
        
        try{
            ConexionMySQL connMySQL = new ConexionMySQL();
            
            Connection conn = connMySQL.open();
            
            CallableStatement clblsmt = conn.prepareCall(query);
            
            clblsmt.setString(1, m.getPersona().getUsuario().getNombreUsuario());
            clblsmt.setString(2, m.getPersona().getUsuario().getContrasenia());
            clblsmt.setString(3, m.getPersona().getNombrePersona());
            clblsmt.setString(4, m.getNombreMascota());
            clblsmt.setInt(5, m.getEdadMascota());
            clblsmt.setString(6, m.getRazaMascota());
            clblsmt.setString(7, m.getTamanioMascota());
            clblsmt.setInt(8, m.getPersona().getUsuario().getIdUsuario());
            clblsmt.setInt(9, m.getPersona().getIdPersona());
            clblsmt.setInt(10, m.getIdMascota());
            
            clblsmt.executeUpdate();
            
            clblsmt.close();
            conn.close();
            connMySQL.close();
        } catch(SQLException ex){
            exito = false;
        }
        
        return exito;
    }
    
    public boolean activarCuenta(Mascota m) throws SQLException{
        String query = "{CALL activarCuenta(?)}";
        boolean exito = true;
        
        try{
            ConexionMySQL connMySQL = new ConexionMySQL();
            
            Connection conn = connMySQL.open();
            
            CallableStatement clblsmt = conn.prepareCall(query);
            
            clblsmt.setInt(1, m.getPersona().getUsuario().getIdUsuario());
            
            clblsmt.executeUpdate();
            
            clblsmt.close();
            conn.close();
            connMySQL.close();
        } catch(SQLException ex){
            exito = false;
        }
        
        return exito;
    }
    
    public boolean eliminarCuenta(Mascota m) throws SQLException{
        String query = "{CALL eliminarCuenta(?)}";
        boolean exito = true;
        
        try{
            ConexionMySQL connMySQL = new ConexionMySQL();
            
            Connection conn = connMySQL.open();
            
            CallableStatement clblsmt = conn.prepareCall(query);
            
            clblsmt.setInt(1, m.getPersona().getUsuario().getIdUsuario());
            
            clblsmt.executeUpdate();
            
            clblsmt.close();
            conn.close();
            connMySQL.close();
        } catch(SQLException ex){
            exito = false;
        }
        
        return exito;
    }
}
