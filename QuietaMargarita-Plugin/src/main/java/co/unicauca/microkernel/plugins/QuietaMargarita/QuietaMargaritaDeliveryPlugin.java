
package co.unicauca.microkernel.plugins.QuietaMargarita;
import co.unicauca.microkernel.common.entities.Component;
import co.unicauca.microkernel.common.entities.Delivery;
import co.unicauca.microkernel.common.interfaces.IDeliveryPlugin;
/**
 *
 * @author HP
 */
public class QuietaMargaritaDeliveryPlugin implements IDeliveryPlugin{
    /**
     * El calculo del domicilio es una mezcla del precio del almuerzo y distancia.
     *
     * @param delivery domicilio
     * @return costo del domicilio
     */
    public double calculateCost(Delivery delivery) {

        Component comp = delivery.getComponente();
        double price = comp.getCompPrice();
        double distance = delivery.getDelDistance();
        double valorFijo = 2000;
        double cost;

        if (distance<= 1) {

            cost = valorFijo+price;

        } else {

            cost = (valorFijo*distance)+price;

        }
        return cost;
    }
}
