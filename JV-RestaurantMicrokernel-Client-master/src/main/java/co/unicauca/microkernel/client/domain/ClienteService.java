/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unicauca.microkernel.client.domain;

import co.unicauca.microkernel.client.access.IClienteAccess;
import co.unicauca.microkernel.common.entities.Component;

/**
 *
 * @author HP
 */
public class ClienteService {
    private final IClienteAccess service;
    
    public ClienteService(IClienteAccess service) {
        this.service = service;
    }
    
    public String createComponente(Component component) throws Exception{
        return this.service.createComponente(component);
    }
}
