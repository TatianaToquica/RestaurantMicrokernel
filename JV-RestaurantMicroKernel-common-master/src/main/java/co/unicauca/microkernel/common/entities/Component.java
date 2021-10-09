package co.unicauca.microkernel.common.entities;

/**
 * Clase que define al componente
 * @author Luis Arango
 */
public class Component {
    //<editor-fold defaultstate="collapsed" desc="Atributos">
    /**
     * Identificador del componente
     */
    private int compId;
    /**
     * nombre del componente
     */
    private String compName;
    /**
     * Precio del componente
     */
    private int compPrice;
    /**
     * Tipo del componente
     */
    private String compType;
    /**
     * imagen que describe el componente
     */
    private byte [] CompImage;
    /**
     * login del admin
     */
    private String  userLoginName;
//</editor-fold>  
    //<editor-fold defaultstate="collapsed" desc="Constructor">
    /**
     * Constructor por defecto
     */
    public Component(){}
    /**
     * COnstructor parametrizado de la clase Component
     * @param compId Identificador
     * @param compNombre Nombre
     * @param precio Precio
     * @param Tipo Tipo 
     * @param CompImage Imagen
     * @param userLoginName loginAdmin
     */
    public Component(int compId, String compNombre, int precio, String Tipo, byte[] CompImage,String  userLoginName) {
        this.compId = compId;
        this.compName = compNombre;
        this.compPrice = precio;
        this.compType = Tipo;
        this.CompImage = CompImage;
        this.userLoginName =  userLoginName;
    }
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Getters">
    /**
     * Obtiene el identificador del componente
     * @return int compID
     */
    public int getCompId() {
        return compId;
    }
    /**
     * Obtiene el nombre del componente
     * @return Strign compName
     */
    public String getCompName() {
        return compName;
    }
    /**
     * Obtiene el precio del componente
     * @return int compPrice
     */
    public int getCompPrice() {
        return compPrice;
    }
    /**
     * Obtiene el tipo del componente
     * @return String compType
     */
    public String getCompType() {
        return compType;
    }
    /**
     * Obtiene la imagen del componente
     * @return byte compImage
     */
    public byte[] getCompImage() {
        return CompImage;
    }

    public String getUserLoginName() {
        return userLoginName;
    }

   
    
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Setters">
    /**
     * Modifica el identificador del componente
     * @param compId nuevo compID
     */
    public void setCompId(int compId) {
        this.compId = compId;
    }
    /**
     * MOdifica el nombre del componente
     * @param compName  nuevo compName
     */
    public void setCompName(String compName) {
        this.compName = compName;
    }
    /**
     * Modifica el precio del componente
     * @param compPrice nuevo compPrice
     */
    public void setCompPrice(int compPrice) {
        this.compPrice = compPrice;
    }
    /**
     * Modifica el tipo del componente
     * @param compType nuevo compType
     */
    public void setCompType(String compType) {
        this.compType = compType;
    }
    /**
     * Modifica la imagen del componente
     * @param CompImage nueva compImage
     */
    public void setCompImage(byte[] CompImage) {
        this.CompImage = CompImage;
    }
    public void setUserLoginName(String userLoginName) {
        this.userLoginName = userLoginName;
    }
//</editor-fold>    
}
