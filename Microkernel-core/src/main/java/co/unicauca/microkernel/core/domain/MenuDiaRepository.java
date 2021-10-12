/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unicauca.microkernel.core.domain;

import co.unicauca.microkernel.common.entities.Component;
import co.unicauca.microkernel.common.entities.DayMenu;
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
public class MenuDiaRepository implements IMenuDiaRepository{
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
    public boolean ValidateCompMenu(int prmcompId, String prmDia){
        boolean resultado= false;
        try {
            this.connect();
            String sql = "SELECT * FROM DayMenu WHERE compId=? and dmenday=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, prmcompId);
            pstmt.setString(2, prmDia);
            ResultSet res = pstmt.executeQuery();
            if (res.next()) {
                resultado= true;
            }
            pstmt.close();
            this.disconnect();
        } catch (SQLException ex) {
            Logger.getLogger(MenuDiaRepository.class.getName()).log(Level.SEVERE, "Error al validar componente en el menu", ex);
        }
        return resultado;
    }
    @Override
    public String createMenuDia(DayMenu menu) {
        String resultado="FALLO";
        if (ValidateCompMenu(menu.getDmenCompID(),menu.getDmenDay())){
            return resultado;
        }
        try {
            this.connect();
            String sql = "INSERT INTO DayMenu (compID, dmenday, userLoginName) "
                    + "VALUES (?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, menu.getDmenCompID());
            pstmt.setString(2, menu.getDmenDay());
            pstmt.setString(3, menu.getUserLoginName());
           
            pstmt.execute();
            resultado= menu.getDmenDay();
            pstmt.close();
            this.disconnect();
        } catch (SQLException ex) {
            Logger.getLogger(MenuDiaRepository.class.getName()).log(Level.SEVERE, "Error al insertar el componente en el menu", ex);
            return resultado;
        }
        return resultado;
    }

    @Override
    public String findCompDia(String dia, String LoginAdmin) {
        List<Component> list=new ArrayList<>();
        String response="null";
                
        System.out.println("Listar Componentes del Menu para el "+dia);
        try{
            this.connect();            
            String sql = "SELECT menu.compID, compName, compType, compPrice, compImage , menu.userLoginName FROM DayMenu menu inner join Component comp on menu.compID = comp.compID WHERE menu.dmenday=? and menu.userLoginName=?";
            PreparedStatement pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,dia);
            pstmt.setString(2,LoginAdmin);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {      
                Component comp = new Component(Integer.parseInt(rs.getString(1)),rs.getString(2),Integer.parseInt(rs.getString(4)),rs.getString(3),rs.getBytes(5),rs.getString(6));
                list.add(comp);
            }
            response=listMenuToJson(list);
            pstmt.close();
            this.disconnect();
        }catch (SQLException ex) {
            Logger.getLogger(MenuDiaRepository.class.getName()).log(Level.SEVERE, "Error al listar los Componentes del menu del dia", ex);
        }
        //return response;
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
}
