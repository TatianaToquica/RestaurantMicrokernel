/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unicauca.microkernel.core.domain;

import co.unicauca.microkernel.common.entities.Dish;
import co.unicauca.microkernel.common.infra.Utilities;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HP
 */
public class DishRepository implements IDishRepository{
      //<editor-fold defaultstate="collapsed" desc="Atributos">
    /**
     * Atributo para hacer la conexión con la base de datos
     */
    public Connection conn;
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Metodos de conexion">
    /**
     * Método encargado de realizar la conexión a la base de datos
     * @return 1 si la conexión fue exitosa, -1 de lo contrario
     */
    public int connect() {
        try {
            Class.forName(Utilities.loadProperty("server.db.driver"));
            //crea una instancia de la controlador de la base de datos
            String url = Utilities.loadProperty("server.db.url");
            String username = Utilities.loadProperty("server.db.username");
            String pwd = Utilities.loadProperty("server.db.password");
            conn = DriverManager.getConnection(url, username, pwd);
            return 1;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ComponentRepository.class.getName()).log(Level.SEVERE, "Error al conectar la base de datos", ex);
        }
        return -1;
    }
    
    /**
     * Metodo encargado de desconectar la aplicacion de la base de datos.
     */
    public void disconnect() {
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(ComponentRepository.class.getName()).log(Level.FINER, "Error al cerrar Connection", ex);
        }
    }
//</editor-fold>
   
    //<editor-fold defaultstate="collapsed" desc="Métodos sobre-escritos">
    @Override
    public String createDish(Dish prmObjPlato) {
        try {
            this.connect();
            String sql = "INSERT INTO Dish (dishID, dishName, dishDescription, dishPrice, dishImage) "
                    + "VALUES (?,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, prmObjPlato.getDishID());
            pstmt.setString(2, prmObjPlato.getDishName());
            pstmt.setString(3, prmObjPlato.getDishDescription());
            pstmt.setInt(4, (int) prmObjPlato.getDishPrice()); //BD float clase double
            pstmt.setBytes(5, prmObjPlato.getDishImage());
            pstmt.executeUpdate();
            pstmt.close();
            this.disconnect();
        } catch (SQLException ex) {
            Logger.getLogger(ComponentRepository.class.getName()).log(Level.SEVERE, "Error al insertar el ObjDish", ex);
        }
        return Integer.toString(prmObjPlato.getDishID());
    }

    @Override
    public Dish findComponente(int prmplatoID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Dish> findAllComponentes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String deleteComponente(int prmplatoID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String updateComponente(int prmplatoID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
