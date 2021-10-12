package co.unicauca.microkernel.core.domain;

import co.unicauca.microkernel.common.entities.Component;
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
 * Clase que implementa a la interfaz del componente
 * @author Luis Arango
 */
public class ComponentRepository implements IComponentRepository{
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
    /**
     * Crea un componente con un ocjto de tipo componente porporcionado
     * @param prmObjComponente nuevo Objeto componente a ser creafo e insertado en la base de datos
     * @return retorna ID del componente o una excepción en caso de fallar
     */
    @Override
    public String createComponente(Component prmObjComponente) {
        String resultado="Fallo crear Componente";
        try {
            this.connect();
            String sql = "INSERT INTO Component (compID, compName, compType, compPrice, compImage, userLoginName) "
                    + "VALUES (?,?,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, prmObjComponente.getCompId());
            pstmt.setString(2, prmObjComponente.getCompName());
            pstmt.setString(3, prmObjComponente.getCompType());
            pstmt.setInt(4, prmObjComponente.getCompPrice());
            pstmt.setBytes(5, prmObjComponente.getCompImage());
            pstmt.setString(6, prmObjComponente.getUserLoginName());
            pstmt.execute();
            resultado=prmObjComponente.getCompName();
            pstmt.close();
            this.disconnect();
        } catch (SQLException ex) {
            Logger.getLogger(ComponentRepository.class.getName()).log(Level.SEVERE, "Error al insertar el ObjComponente", ex);
            return resultado;
        }
        return resultado;
    }  
    
    @Override    
    public String findComponente(String prmCompName) {   
        String resultado="Fallo";
        try {
            this.connect();
            String sql = "SELECT * FROM Component WHERE compName=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, prmCompName);
            ResultSet res = pstmt.executeQuery();
            if (res.next()) {
                resultado=prmCompName;
            }
            pstmt.close();
            this.disconnect();
        } catch (SQLException ex) {
            Logger.getLogger(ComponentRepository.class.getName()).log(Level.SEVERE, "Error al buscar un componente en la base de datos", ex);
        }
        return resultado;
    }
    @Override
    public String deleteComponente(String prmCompName) {
        String resultado;
        resultado=findComponente(prmCompName);        
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
            String sql = "DELETE FROM Component WHERE compName= (?)";
            //pstmt mantendra la solicitud sobre la base de datos, se asignam sus columnas
            PreparedStatement pstmt = conn.prepareStatement(sql);
            //se compara el id, OJO Ddebe cumplir estrictamente el orden y el tipo de dato(de las tablas)
            pstmt.setString(1, prmCompName);

            pstmt.executeUpdate();
            //se cierra
            pstmt.close();
            //se termina la coneccion
            this.disconnect();
        } catch (SQLException ex) {
            Logger.getLogger(ComponentRepository.class.getName()).log(Level.SEVERE, "Error al eliminar el componente de la BD", ex);
        }
        return ""+prmCompName;
    }
    
    @Override
    public String updateComponente(Component comp) {
        String resultado;
        resultado=findComponente(comp.getCompName());        
        if (resultado!="Fallo") {
            System.out.println("EXISTE EL ELEMENTO");
        } else {
            System.out.println("NO EXISTE EL ELEMENTO");
            return "FALLO";
        }
        try{
            this.connect();
            //String sql = "UPDATE platoespecial set "+atributo+" = "+valor+" WHERE PESP_NOMBRE = "+clave;
            String sql = "UPDATE Component SET compID = ?, compName = ?, compType = ?, compPrice = ?, compImage = ? WHERE compID = ?";
            System.out.println("SENTENCIA SQL UPDATE Component: "+sql);
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, comp.getCompId());
            pstmt.setString(2, comp.getCompName());
            pstmt.setString(3, comp.getCompType());             
            pstmt.setInt(4, comp.getCompPrice());
            pstmt.setBytes(5, comp.getCompImage());  
            
            pstmt.executeUpdate();
            
            pstmt.close();
            this.disconnect();
        }catch (SQLException ex) {
            Logger.getLogger(ComponentRepository.class.getName()).log(Level.SEVERE, "Error al actualizar el componente en la BD", ex);
            return "FALLO";
        }
        return comp.getCompName();
    }
     @Override
    public String findAllComponentes(String LoginAdmin) {
        List<Component> list=new ArrayList<>();
        String response="null";
        System.out.println("Listar Componentes");
        try{
            this.connect();            
            String sql = "SELECT compID, compName, compType, compPrice, compImage , userLoginName FROM Component WHERE userLoginName=?";
            PreparedStatement pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,LoginAdmin);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {      
                Component comp = new Component(Integer.parseInt(rs.getString(1)),rs.getString(2),Integer.parseInt(rs.getString(4)),rs.getString(3),rs.getBytes(5),rs.getString(6));
                list.add(comp);
            }
            response=listMenuToJson(list);
            pstmt.close();
            this.disconnect();
        }catch (SQLException ex) {
            Logger.getLogger(ComponentRepository.class.getName()).log(Level.SEVERE, "Error al listar los Componentes", ex);
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
//</editor-fold>   
    
}
