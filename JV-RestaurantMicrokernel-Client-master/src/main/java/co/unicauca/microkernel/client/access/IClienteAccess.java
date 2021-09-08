/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unicauca.microkernel.client.access;

import co.unicauca.microkernel.common.entities.Component;
import co.unicauca.microkernel.common.entities.Dish;


/**
 *
 * @author HP
 */
public interface IClienteAccess {
    public String createComponente(Component component) throws Exception;
    public String createDish(Dish plate) throws Exception;
    public String deleteDish(int prmPlateID) throws Exception;
    public String updateComponente(int prmcompID)throws Exception;
    public String deleteComponente(int prmcompID) throws Exception;
    public Component findComponente(int prmcompID)throws Exception;
    public Dish findDish(int prmPlateID)throws Exception;
}
