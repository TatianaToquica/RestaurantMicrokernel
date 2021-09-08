package co.unicauca.microkernel.common.entities;

/**
 *
 * @author Usuario
 */
public class Delivery {
    //<editor-fold defaultstate="collapsed" desc="Atributos">
    /**
     * codigo del domicilio
     */
    private String delCode;
    /**
     * Distancia del envio
     */
    private int delDistance;
    /**
     * Instancia del plato
     */
    private Dish plato;
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Constructor">
    /**
     * Constructor parametrizado de la clase Delivey
     * @param delCode codigo (ID)
     * @param delDistance Distancia
     * @param plato plato pedido
     */
    public Delivery(String delCode, int delDistance, Dish plato) {
        this.delCode = delCode;
        this.delDistance = delDistance;
        this.plato= plato;
    }
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Getters">
    /**
     * Obtiene el codigo del envío
     * @return String delCode
     */
    public String getDelCode() {
        return delCode;
    }
    /**
     * Obtiene la distancia del envio
     * @return int delDistance
     */
    public int getDelDistance() {
        return delDistance;
    }
    /**
     * Obtiene el plato
     * @return objeto Dish
     */
    public Dish getPlato() {    
        return plato;
    }

//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Setters">
    /**
     * Modifica el código del envío
     * @param delCode nuevo código de envío
     */
    public void setDelCode(String delCode) {
        this.delCode = delCode;
    }
    /**
     * Modifica la distancia del envío 
     * @param delDistance nueva distancia de envío
     */
    public void setDelDistance(int delDistance) {
        this.delDistance = delDistance;
    }
      /**
     * Modifica el plato 
     * @param plato Nuevo plato pedido
     */
    public void setPlato(Dish plato) {
        this.plato = plato;
    } 
    
//</editor-fold>
}
