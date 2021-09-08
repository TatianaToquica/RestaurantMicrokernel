package co.unicauca.microkernel.core.domain;
import co.unicauca.microkernel.common.entities.User;

/**
 * Interfaz para las utilidades del usuario
 * @author Luis Arango
 */
public interface IUserRepository {
    /**
     * Metodo encargado de buscar un usuario
     * @param prmUserName userName a buscar
     * @param prmUserPassword userPassword a buscar
     * @return objeto de tipo User
     */
    public User findUser(String prmUserName, String prmUserPassword);
    /**
     * Metodo para crear un usuario 
     * @param prmObjUser Objeto usuario a crear
     * @return cadena de texto que contiene el userName de prmObjUser
     */
    public String createUser(User prmObjUser);   
    /**
     * Metodo encargado de buscar un usuario
     * @param prmUserName userName a buscar     
     * @return objeto de tipo User
     */
    public User invalidateUser(String prmUserName);
    
}
