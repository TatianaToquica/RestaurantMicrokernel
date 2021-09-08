/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unicauca.microkernel.client.presentation;

import co.unicauca.microkernel.client.access.Factory;
import co.unicauca.microkernel.client.access.IClienteAccess;
import co.unicauca.microkernel.client.domain.ClienteService;
import co.unicauca.microkernel.common.entities.Component;
import co.unicauca.microkernel.common.infra.Utilities;

/**
 *
 * @author HP
 */
public class test {
    
    private static IClienteAccess service = Factory.getInstance().getClienteService();
    private static ClienteService servicioRestaurante = new ClienteService(service);
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String re = "";
        try {
            Component comp = new Component(1, "sancocho", 0, "so", Utilities.convertirFoto("C:\\Users\\HP\\Pictures\\espuma\\2.png"));
            re = servicioRestaurante.createComponente(comp);
            System.out.println(re);
        } catch (Exception e) {
        }
       
    }
    
}
