/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unicauca.microkernel.business;

import co.unicauca.microkernel.common.entities.Delivery;
import co.unicauca.microkernel.common.interfaces.IDeliveryPlugin;
import co.unicauca.microkernel.core.plugin.manager.DeliveryPluginManager;

/**
 *
 * @author HP
 */
public class DeliveryService {
    public double calculateDeliveryCost(Delivery deliveryData) throws Exception {

        String delCode = deliveryData.getDelCode();
        DeliveryPluginManager manager = DeliveryPluginManager.getInstance();
        IDeliveryPlugin plugin = manager.getDeliveryPlugin(delCode);

        if (plugin == null) {
            throw new Exception("No hay un plugin disponible para el restaurante indicado: " + delCode);
        }

        return plugin.calculateCost(deliveryData);

    }
}
