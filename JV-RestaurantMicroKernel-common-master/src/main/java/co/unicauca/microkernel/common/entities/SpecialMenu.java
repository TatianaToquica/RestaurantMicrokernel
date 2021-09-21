package co.unicauca.microkernel.common.entities;

/**
 * Clase que define al menú especial
 * @author Luis Arango
 */
public class SpecialMenu {
    //<editor-fold defaultstate="collapsed" desc="Atributos">
    /**
     * Identificador del plato especial
     */
    private int smenID;
    /**
     * Dia del menu especial
     */
    private String smenDay;
    /**
     * Identificador del restaurante adscrito al menú
     */
    private String resID;
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Constructores">
    /**
     * Constructor parametrizado de la clase SpecialMenu
     * @param smenID Identificador del menu
     * @param smenDay Día del menú especial
     * @param resID restaurante adscrito al menú
     */
    public SpecialMenu(int smenID, String smenDay, String resID) {
        this.smenID = smenID;
        this.smenDay = smenDay;
        this.resID = resID;
    }
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Getters">
    /**
     * Obtiene el identificador del menu
     * @return int smenID
     */
    public int getSmenID() {
        return smenID;
    }
    /**
     * Obtiene el día del menú 
     * @return String smenDay
     */
    public String getSmenDay() {
        return smenDay;
    }
    /**
     * Obtiene el identificador del restaurante adscrito al menu
     * @return String resID
     */
    public String getResID() {
        return resID;
    }
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Setters">
    /**
     * Modifica el identificador del menu esoecial
     * @param smenID nuevo ID
     */
    public void setSmenID(int smenID) {
        this.smenID = smenID;
    }
    /**
     * Modifica el día del menu
     * @param smenDay día del menú especial
     */
    public void setSmenDay(String smenDay) {
        this.smenDay = smenDay;
    }
    /**
     * Modifica el identificador del restaurante adscrito al menú especial
     * @param resID 
     */
    public void setResID(String resID) {
        this.resID = resID;
    }
//</editor-fold> 
}