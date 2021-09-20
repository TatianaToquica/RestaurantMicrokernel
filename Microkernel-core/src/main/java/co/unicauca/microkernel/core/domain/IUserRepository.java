package co.unicauca.microkernel.core.domain;
import co.unicauca.microkernel.common.entities.User;

/**
 * Interfaz para las utilidades del usuario
 * @author Luis Arango
 */
public interface IUserRepository {
    /**
     * Metodo encargado de buscar un usuario
     * @param prmUserLoginName Login a buscar     * 
     * @return cadena de texto que contiene el UserLoginName de prmObjUser
     */
    public String findUser(String prmUserLoginName);
    /**
     * Metodo para crear un usuario 
     * @param prmObjUser Objeto usuario a crear
     * @return cadena de texto que contiene el UserLoginName de prmObjUser
     */
    public String createUser(User prmObjUser);   
    /**
     * Metodo encargado de validar un usuario y su contraseña
     * @param prmUserLoginName Login a buscar     
     * @param prmUserPassword password a buscar    
     * @return cadena de texto que contiene el UserLoginName de prmObjUser
     */
    public String validateUser(String prmUserLoginName, String prmUserPassword);
    /**
     * Metodo encargado de validar un usuario y su contraseña
     * @param prmUserLoginName Login a buscar     
     * @param prmUserType password a buscar    
     * @return cadena de texto que contiene el UserType de prmObjUser
     */
    public String validateTypeUser(String prmUserLoginName, String prmUserType);    
    
}
