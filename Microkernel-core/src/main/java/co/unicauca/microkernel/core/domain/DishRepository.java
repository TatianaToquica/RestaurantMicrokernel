/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unicauca.microkernel.core.domain;


import co.unicauca.microkernel.common.entities.Dish;
import co.unicauca.microkernel.common.infra.Utilities;
import com.google.gson.Gson;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
        String resultado="Fallo crear Plato";
        try {
            this.connect();
            String sql = "INSERT INTO Dish (dishID, dishName, dishDescription, dishPrice, dishImage , userLoginName) "
                    + "VALUES (?,?,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, prmObjPlato.getDishID());
            pstmt.setString(2, prmObjPlato.getDishName());
            pstmt.setString(3, prmObjPlato.getDishDescription());
            pstmt.setInt(4, (int) prmObjPlato.getDishPrice()); //BD float clase double
            pstmt.setBytes(5, prmObjPlato.getDishImage());
            pstmt.setString(6, prmObjPlato.getUserLoginName());
            pstmt.execute();
            resultado=prmObjPlato.getDishName();
            pstmt.close();
            this.disconnect();
        } catch (SQLException ex) {
            Logger.getLogger(DishRepository.class.getName()).log(Level.SEVERE, "Error al insertar el ObjDish", ex);
        }
        return resultado;
    }  
    @Override
    public String findDish(String prmDishName) {
        String resultado="Fallo";
        try {
            this.connect();
            String sql = "SELECT * FROM Dish WHERE dishName=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, prmDishName);
            ResultSet res = pstmt.executeQuery();
            if (res.next()) {
                resultado=prmDishName;
            }
            pstmt.close();
            this.disconnect();
        } catch (SQLException ex) {
            Logger.getLogger(DishRepository.class.getName()).log(Level.SEVERE, "Error al buscar un plato especial en la base de datos", ex);
        }
        return resultado;
    }
    @Override
    public String deleteDish(String prmDishName) {
        String resultado;
        resultado=findDish(prmDishName);        
        if (resultado!="Fallo") {
            System.out.println("EXISTE EL ELEMENTO");
        } else {
            System.out.println("NO EXISTE EL ELEMENTO");
            return "FALLO";
        }
        try {
            //primero se establece la conexion
            this.connect(); //validar cuando la conexion no sea exitosa
            //se estructura la sentencia sql en un string
            String sql = "DELETE FROM Dish WHERE dishName = (?)";
            //pstmt mantendra la solicitud sobre la base de datos, se asignam sus columnas
            PreparedStatement pstmt = conn.prepareStatement(sql);
            //se compara el id, OJO Ddebe cumplir estrictamente el orden y el tipo de dato(de las tablas)
            pstmt.setString(1, prmDishName);
            //se ejecuta la sentencia sql
            pstmt.executeUpdate();
            //se cierra
            pstmt.close();
            //se termina la coneccion
            this.disconnect();

        } catch (SQLException ex) {
            Logger.getLogger(DishRepository.class.getName()).log(Level.SEVERE, "Error al eliminar el plato", ex);
        }
        return prmDishName;
    }

    @Override
    public String updateDish(Dish prmObjPlato) {
        String resultado;
        resultado=findDish(prmObjPlato.getDishName());        
        if (resultado!="Fallo") {
            System.out.println("EXISTE EL ELEMENTO");
        } else {
            System.out.println("NO EXISTE EL ELEMENTO");
            return "FALLO";
        }
        try{
            this.connect();
            //String sql = "UPDATE platoespecial set "+atributo+" = "+valor+" WHERE PESP_NOMBRE = "+clave;
            String sql = "UPDATE Dish SET dishID = ?, dishName = ?, dishDescription = ?, dishPrice = ?, dishImage = ? WHERE dishID = ?";
            System.out.println("SENTENCIA SQL UPDATE Component: "+sql);
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, prmObjPlato.getDishID());
            pstmt.setString(2, prmObjPlato.getDishName());
            pstmt.setString(3, prmObjPlato.getDishDescription());
            pstmt.setInt(4, (int) prmObjPlato.getDishPrice()); //BD float clase double
            pstmt.setBytes(5, prmObjPlato.getDishImage());
            
            pstmt.executeUpdate();
            
            pstmt.close();
            this.disconnect();
        }catch (SQLException ex) {
            Logger.getLogger(DishRepository.class.getName()).log(Level.SEVERE, "Error al actualizar el componente en la BD", ex);
            return "FALLO";
        }
        return prmObjPlato.getDishName();
    }

    @Override
    public String findAllDish(String LoginAdmin) {
        List<Dish> list=new ArrayList<>();
        String response="null";
        System.out.println("Listar Platos especiales");
        try{
            this.connect();            
            String sql = "SELECT dishID, dishName, dishDescription, dishPrice, dishImage , userLoginName FROM Dish WHERE userLoginName=?";
            PreparedStatement pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,LoginAdmin);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {      
                Dish plate = new Dish(Integer.parseInt(rs.getString(1)),rs.getString(2),rs.getString(3),Integer.parseInt(rs.getString(4)),rs.getBytes(5),rs.getString(6));
                list.add(plate);
            }
            response=listMenuToJson(list);
            pstmt.close();
            this.disconnect();
        }catch (SQLException ex) {
            Logger.getLogger(DishRepository.class.getName()).log(Level.SEVERE, "Error al listar los platos", ex);
        }
        return response;
    }
    /**
     * Convierte una lista de tipo plato en un json
     *
     * @param list
     * @return
     */
    public String listMenuToJson(List list) {
        Gson gson = new Gson();
        String response = gson.toJson(list);
        return response;
    }
    //</editor-fold>     
}
