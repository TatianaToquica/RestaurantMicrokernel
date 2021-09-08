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
    private Component componente;
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Constructor">
    /**
     * Constructor parametrizado de la clase Delivey
     * @param delCode codigo (ID)
     * @param delDistance Distancia
     * @param plato plato pedido
     */
    public Delivery(String delCode, int delDistance, Component componente) {
        this.delCode = delCode;
        this.delDistance = delDistance;
        this.componente=componente ;
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
    public Component getComponente() {    
        return componente;
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

    public void setComponente(Component componente) {
        this.componente = componente;
    }
      /**
     * Modifica el plato 
     * @param plato Nuevo plato pedido
     */
    
    
//</editor-fold>
}
