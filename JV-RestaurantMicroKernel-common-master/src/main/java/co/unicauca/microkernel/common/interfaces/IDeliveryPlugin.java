package co.unicauca.microkernel.common.interfaces;
import co.unicauca.microkernel.common.entities.Delivery;

/**
 * 
 * @author Luis Arango
 */
public interface IDeliveryPlugin {
   /**
     * Establece el costo de enví­o.
     * @param delivery enví­o
     * @return costo del enví­o
     */
    double calculateCost(Delivery delivery); 
}
