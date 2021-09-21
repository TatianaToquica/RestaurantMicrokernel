package co.unicauca.microkernel.common.entities;

/**
 * Clase que define el menú del día (DayMenu)
 * @author Luis Arango
 */
public class DayMenu {
    //<editor-fold defaultstate="collapsed" desc="Atributos">
    /**
     * Identificador del componente
     */
    private int dmenCompID;
    /**
     * describe el día del menú 
     */
    private String dmenDay;
    /**
     * identificador del restaurante al cual está adscrito el menú
     */
    private String resID;
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Constructores">
    /**
     * Constructor por defecto no parametrizado
     */
    public DayMenu() {
    }
    /**
     * Constructor parametrizado de la calse DayMenu
     * @param dmenID Identificador
     * @param dmenDay día del menú
     * @param resID Identificador del restaurante
     */
    public DayMenu(int dmenCompID, String dmenDay, String resID) {
        this.dmenCompID = dmenCompID;
        this.dmenDay = dmenDay;
        this.resID = resID;
    }
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Getters">
    /**
     * Obtiene el identificador del menu
     * @return int dmenID
     */
    public int getDmenCompID() {    
        return dmenCompID;
    }
    /**
     * Obtiene el día del menú
     * @return String dmenDay
     */
    public String getDmenDay() {
        return dmenDay;
    }
    /**
     * Obtiene el ID del restaurante adscrito al menú
     * @return 
     */
    public String getResID() {
        return resID;
    }
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Setters">
    /**
     * Modifica el ID del menú
     * @param dmenID Identificador
     */
    public void setDmenCompID(int dmenCompID) {    
        this.dmenCompID = dmenCompID;
    }

    /**
     * Modifica el día del menú
     * @param dmenDay nuevo día del menú
     */
    public void setDmenDay(String dmenDay) {
        this.dmenDay = dmenDay;
    }
    /**
     * Modifica el ID del restaurante adscrito al menú 
     * @param resID nuevo identificador del restaurante
     */
    public void setResID(String resID) {
        this.resID = resID;
    }
//</editor-fold>
}