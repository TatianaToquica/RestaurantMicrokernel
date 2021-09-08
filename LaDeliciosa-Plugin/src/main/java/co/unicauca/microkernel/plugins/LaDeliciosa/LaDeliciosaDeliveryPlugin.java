
package co.unicauca.microkernel.plugins.LaDeliciosa;

import co.unicauca.microkernel.common.entities.Delivery;
import co.unicauca.microkernel.common.interfaces.IDeliveryPlugin;

/**
 *
 * @author HP
 */
public class LaDeliciosaDeliveryPlugin implements IDeliveryPlugin{
    /**
     * El calculo del domicilio es una mezcla del precio del almuerzo y distancia.
     *
     * @param delivery domicilio
     * @return costo del domicilio
     */
    public double calculateCost(Delivery delivery) {

        Dish plato = delivery.getPlato();
        double price = plato.getPrice();
        double distance = delivery.getDistance();
        double valorFijo = 4000;
        double cost;

        if (distance<= 2) {

            cost = price;

        } else {

            cost = (valorFijo*distance)-(valorFijo*2)+price;

        }
        return cost;
    }
}
