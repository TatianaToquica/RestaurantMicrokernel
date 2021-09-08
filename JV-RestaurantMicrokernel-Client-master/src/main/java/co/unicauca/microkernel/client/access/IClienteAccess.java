/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unicauca.microkernel.client.access;

import co.unicauca.microkernel.common.entities.Component;


/**
 *
 * @author HP
 */
public interface IClienteAccess {
    public String createComponente(Component component) throws Exception;
}
