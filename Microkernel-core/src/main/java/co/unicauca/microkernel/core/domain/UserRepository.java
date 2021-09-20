package co.unicauca.microkernel.core.domain;

import co.unicauca.microkernel.common.entities.User;
import co.unicauca.microkernel.common.infra.Utilities;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Interfaz para las utilidades del la clase user
 * @author Luis Arango
 */
public class UserRepository implements IUserRepository{
    //<editor-fold defaultstate="collapsed" desc="Atributos">
    /**
     * Objeto de tipo conexión
     */
    private Connection conn;
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Métodos Sobre-Escritos">
    /**
     * Metodo que sobreescribe la interfaz de repositorio de usuario para encontrar un usuario
     * @param prmUserLoginName Nombre de usuario a buscar.
     * @param prmUserPassword contraseña de usuario a buscar.
     * @return Objeto de tipo User
     */
    @Override
    public String findUser(String prmUserLoginName) {
        String resultado="Fallo";
        this.connect();
        try {
            String sql = "SELECT userName FROM User where userLoginName=? ";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, prmUserLoginName);//Preguntar a Luis si debe ir la contraseña
            ResultSet res = pstmt.executeQuery();
            if (res.next()) {
                resultado=prmUserLoginName;
            }
            pstmt.close();
            this.disconnect();
        } catch (SQLException ex) {
            Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, "Error al consultar el Usuario de la base de datos", ex);
            
        }
        return resultado;
    }
    /**
     * Metodo que sobreescribe la interfaz de repositorio de usuario para encontrar un usuario
     * @param prmUserLoginName Nombre de usuario a buscar.
     * @return Objeto de tipo User
     */
    @Override
    public String validateUser(String prmUserLoginName ,String prmUserPassword) {
        String resultado="Fallo";
        this.connect();
        try {
            String sql = "SELECT userName FROM User where userLoginName=? and userPassword=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, prmUserLoginName);
            pstmt.setString(2, prmUserPassword);
            ResultSet res = pstmt.executeQuery();
            if (res.next()) {
                resultado=prmUserPassword;
            }
            pstmt.close();
            this.disconnect();
        } catch (SQLException ex) {
            Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, "Error al consultar el Usuario de la base de datos", ex);
            
        }
        return resultado;
    }
    @Override
    public String validateTypeUser(String prmUserLoginName, String prmUserType) {
        String resultado="Fallo";
        this.connect();
        try {
            String sql = "SELECT userName FROM User where userLoginName=? and userType=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, prmUserLoginName);
            pstmt.setString(2, prmUserType);
            ResultSet res = pstmt.executeQuery();
            if (res.next()) {
                resultado=prmUserType;
            }
            pstmt.close();
            this.disconnect();
        } catch (SQLException ex) {
            Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, "Error al consultar el Usuario de la base de datos", ex);
            
        }
        return resultado;
    }
    /**
     * Metodo que sobreescribe la interfaz de repositorio de usuario y cre un usuario
     * @param prmObjUser Objeto de tipo User
     * @return String con el valor retornado por gerUserLoginName
     */
    @Override
    public String createUser(User prmObjUser) {
        String resultado="Fallo crear usuario";
        try {
            this.connect();
            String sql = "INSERT INTO User(userLoginName, userPassword, userName, userLastName, userAddress, userMobile, userEmail ,userType) "
                    + "VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, prmObjUser.getUserLoginName());
            pstmt.setString(2, prmObjUser.getUserPassword());
            pstmt.setString(3, prmObjUser.getUserName());
            pstmt.setString(4, prmObjUser.getUserLastName());
            pstmt.setString(5, prmObjUser.getUserAddres());
            pstmt.setString(6, prmObjUser.getUserMobile());
            pstmt.setString(7, prmObjUser.getUserEmail());
            pstmt.setString(8, prmObjUser.getUserType());
            pstmt.executeUpdate();
            resultado=prmObjUser.getUserLoginName();
            pstmt.close();
            this.disconnect();
        } catch (SQLException ex) {
            Logger.getLogger(IUserRepository.class.getName()).log(Level.SEVERE, resultado , ex);
        }
        return resultado;
    }    
    
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Metodos de conexión">
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
            Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, "Error al consultar usuario en la base de datos", ex);
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
            Logger.getLogger(UserRepository.class.getName()).log(Level.FINER, "Error al cerrar Connection", ex);
        }
    }
//</editor-fold>      
    
}
