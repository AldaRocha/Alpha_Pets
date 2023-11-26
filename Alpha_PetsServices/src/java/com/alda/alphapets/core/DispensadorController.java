
package com.alda.alphapets.core;

import com.alda.alphapets.connection.ConexionMySQL;
import com.alda.alphapets.model.Dispensador;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 *
 * @author Alda
 */
public class DispensadorController {
    public int insertarDispensador(Dispensador d) throws SQLException{
        String query = "{CALL insertarDispensador(?, ?)}";
        
        int idDispensadorGenerado = -1;
        
        ConexionMySQL connMySQL = new ConexionMySQL();
        
        Connection conn = connMySQL.open();
        
        CallableStatement clblsmt = conn.prepareCall(query);
        
        clblsmt.setString(1, d.getNumeroSerie());
        
        clblsmt.registerOutParameter(2, Types.INTEGER);
        
        clblsmt.executeUpdate();
        
        idDispensadorGenerado = clblsmt.getInt(2);
        
        d.setIdDispensador(idDispensadorGenerado);
        
        clblsmt.close();
        conn.close();
        connMySQL.close();
        
        return idDispensadorGenerado;
    }
    
    public boolean actualizarDispensador(Dispensador d) throws SQLException{
        String query = "{CALL actualizarDispensador(?, ?, ?, ?, ?)}";
        boolean exito = true;
        
        try{
            ConexionMySQL connMySQL = new ConexionMySQL();
            
            Connection conn = connMySQL.open();
            
            CallableStatement clblsmt = conn.prepareCall(query);
            
            clblsmt.setString(1, d.getNumeroSerie());
            clblsmt.setString(2, d.getDepositoComida());
            clblsmt.setString(3, d.getDepositoAgua());
            clblsmt.setString(4, d.getPlatoComida());
            clblsmt.setString(5, d.getPlatoAgua());
            
            clblsmt.executeUpdate();
            
            clblsmt.close();
            conn.close();
            connMySQL.close();
        } catch(SQLException ex){
            exito = false;
        }
        return exito;
    }
    
    public Dispensador getDatosDispensador(Dispensador d) throws SQLException{
        String query = "SELECT * FROM v_datosDispensador vdd WHERE vdd.idDispensador = ?";
        
        ConexionMySQL connMySQL = new ConexionMySQL();
        
        Connection conn = connMySQL.open();
        
        PreparedStatement prepst = conn.prepareStatement(query);
        
        prepst.setInt(1, d.getIdDispensador());
        
        ResultSet rs = null;
        rs = prepst.executeQuery();
        
        Dispensador dis = new Dispensador();
        if(rs.next())
            dis = (fill(rs));
        
        rs.close();
        prepst.close();
        connMySQL.close();
        
        return dis;
    }
    
    private Dispensador fill(ResultSet rs) throws SQLException{
        Dispensador d = new Dispensador();
        
        d.setIdDispensador(rs.getInt("idDispensador"));
        d.setNumeroSerie(rs.getString("numeroSerie"));
        d.setDepositoComida(rs.getString("depositoComida"));
        d.setDepositoAgua(rs.getString("depositoAgua"));
        d.setPlatoComida(rs.getString("platoComida"));
        d.setPlatoAgua(rs.getString("platoAgua"));
        
        return d;
    }
}
