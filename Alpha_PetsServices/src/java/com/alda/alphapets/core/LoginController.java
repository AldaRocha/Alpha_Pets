
package com.alda.alphapets.core;

import com.alda.alphapets.connection.ConexionMySQL;
import com.alda.alphapets.model.Dispensador;
import com.alda.alphapets.model.Mascota;
import com.alda.alphapets.model.Persona;
import com.alda.alphapets.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alda
 */
public class LoginController {
    public List<Mascota> login(String nombreUsuario, String contrasenia) throws SQLException{
        String query = "SELECT * FROM v_buscarPorUsuario vbpu WHERE vbpu.nombreUsuario = ? AND vbpu.contrasenia = ? AND vbpu.estatus = 1";
        
        ConexionMySQL connMySQL = new ConexionMySQL();
        
        Connection conn = connMySQL.open();
        
        PreparedStatement prepst = conn.prepareStatement(query);
        
        prepst.setString(1, nombreUsuario);
        prepst.setString(2, contrasenia);
        
        ResultSet rs = null;
        rs = prepst.executeQuery();
        
        List<Mascota> lm = new ArrayList<>();
        while(rs.next())
            lm.add(fill(rs));
        
        rs.close();
        prepst.close();
        connMySQL.close();
        
        return lm;
    }
    
    private Mascota fill(ResultSet rs) throws SQLException{
        Mascota m = new Mascota();
        Persona p = new Persona();
        Usuario u = new Usuario();
        Dispensador d = new Dispensador();
        
        d.setIdDispensador(rs.getInt("idDispensador"));
        d.setNumeroSerie(rs.getString("numeroSerie"));
        d.setDepositoComida(rs.getString("depositoComida"));
        d.setDepositoAgua(rs.getString("depositoAgua"));
        d.setPlatoComida(rs.getString("platoComida"));
        d.setPlatoAgua(rs.getString("platoAgua"));
        d.setRellenar(rs.getString("rellenar"));
        
        u.setIdUsuario(rs.getInt("idUsuario"));
        u.setNombreUsuario(rs.getString("nombreUsuario"));
        u.setContrasenia(rs.getString("contrasenia"));
        u.setEstatus(rs.getInt("estatus"));
        
        p.setIdPersona(rs.getInt("idPersona"));
        p.setNombrePersona(rs.getString("nombrePersona"));
        p.setUsuario(u);
        p.setDispensador(d);
        
        m.setIdMascota(rs.getInt("idMascota"));
        m.setNombreMascota(rs.getString("nombreMascota"));
        m.setEdadMascota(rs.getInt("edadMascota"));
        m.setRazaMascota(rs.getString("razaMascota"));
        m.setTamanioMascota(rs.getString("tamanioMascota"));
        m.setPersona(p);
        
        return m;
    }
}
