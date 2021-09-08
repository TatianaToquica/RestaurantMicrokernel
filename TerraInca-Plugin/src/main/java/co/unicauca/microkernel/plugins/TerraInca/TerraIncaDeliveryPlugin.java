
package co.unicauca.microkernel.plugins.TerraInca;

/**
 *
 * @author HP
 */
public class TerraIncaDeliveryPlugin implements IDeliveryPlugin{
    /**
     * El calculo del domicilio es una mezcla del precio del almuerzo y distancia.
     *
     * @param delivery domicilio
     * @return costo del domicilio
     */
    public double calculateCost(Delivery delivery) {

        Dish plato = delivery.getDish;
        double price = plato.getPrice();
        double distance = delivery.getDistance();
        double valorFijo = 5000;
        double cost;

        if (distance<= 1) {

            cost = price+(valorFijo-2000);

        } else {

            cost = (valorFijo*distance)-valorFijo+price;

        }
        return cost;
    }
}
