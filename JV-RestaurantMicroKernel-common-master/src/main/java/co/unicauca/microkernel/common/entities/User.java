package co.unicauca.microkernel.common.entities;

/**
 * Clase que define al usuario 
 * @author Luis Arango
 */
public class User {
    //<editor-fold defaultstate="collapsed" desc="Atributos">
    /**
     * Nombre de usuario en plataforma
     */
    private String userLoginName;
    /**
     * Contraseña de acceso;
     */
    private String userPassword;
    /**
     * Nombre del usuario
     */
    private String userName;
    /**
     * Apellido del usuario
     */
    private String userLastName;
    /**
     * Dirección del usuario
     */
    private String userAddres;
    /**
     * Número telefónico del usuario
     */
    private String userMobile;
    /**
     * Email del usuario
     */
    private String userEmail;
     /**
     * Tipo de usuario
     */
    private String userType;
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Constructores">
    /**
     * Constructor parametrizado de la clase User
     * @param userLoginName Nombre de usuario
     * @param userPassword Contraseña
     * @param userName Nombre
     * @param userLastName Apellido
     * @param userAddres Dirección de residencia
     * @param userMobile Número de teléfono
     * @param userEmail Correo Electrónico
     * @param userType tipo de usuario
     */
    public User (String userLoginName, String userPassword, String userName, String userLastName, String userAddres, String userMobile, String userEmail, String userType) {
        this.userLoginName = userLoginName;
        this.userPassword = userPassword;
        this.userName = userName;
        this.userLastName = userLastName;
        this.userAddres = userAddres;
        this.userMobile = userMobile;
        this.userEmail = userEmail;
        this.userType = userType;
    }
    public User (String userLoginName, String userPassword){
        this.userLoginName = userLoginName;
        this.userPassword = userPassword;
    }
    /**
     * Constructor no parametrizado por defecto
     */
    public User() {}
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Getters">
    /**
     * Obtiene el nombre de usuario
     * @return String Nombre de usuario
     */
    public String getUserLoginName() {
        return userLoginName;
    }
    /**
     * Obtiene la contraseña del usuario
     * @return Strin Constraseña
     */
    public String getUserPassword() {
        return userPassword;
    }
    /**
     * Obtiene el nombre natural del usuario
     * @return String Nombre de pila
     */
    public String getUserName() {
        return userName;
    }
    /**
     * Obtiene el apellido del usuario
     * @return String Apellido
     */
    public String getUserLastName() {
        return userLastName;
    }
    /**
     * Obtiene la dirección de residencia 
     * @return String Dirección
     */
    public String getUserAddres() {
        return userAddres;
    }
    /**
     * Obtiene el número de teléfono del usuario
     * @return 
     */
    public String getUserMobile() {
        return userMobile;
    }
    /**
     * Obtiene el E-mail del usuario
     * @return String E-mail
     */
    public String getUserEmail() {
        return userEmail;
    }
    /**
     * Obtiene el tipo de usuario
     * @return String userType
     */
    public String getUserType() {
        return userType;
    }
    
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Setters">
    /**
     * Modifica el nombre de usuario de login 
     * @param userLoginName String nuevo nombre de usuario
     */
    public void setUserLoginName(String userLoginName) {
        this.userLoginName = userLoginName;
    }
    /**
     * Modifica la contraseña del usuario
     * @param userPassword String nueva contraseña
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
    /**
     * Modifica el nombre natural del usuario
     * @param userName String nuevo nombre
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
    /**
     * Modifica el apellido del usuario
     * @param userLastName String nuevo apellido
     */
    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }
    /**
     * Modifica la dirección de residencia del usuario
     * @param userAddres String nueva dirección
     */
    public void setUserAddres(String userAddres) {
        this.userAddres = userAddres;
    }
    /**
     * Modifica el número telefónico del usuario
     * @param userMobile String nuevo número de teléfono
     */
    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }
    /**
     * Modifica el E-mail del usuario
     * @param userEmail String nuevo E-mail
     */
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
    /**
     * Modifica el tipo de usuario
     * @param userType String nuevo userType
     */
    public void setUserType(String userType) {
        this.userType = userType;
    }
//</editor-fold>    
}
