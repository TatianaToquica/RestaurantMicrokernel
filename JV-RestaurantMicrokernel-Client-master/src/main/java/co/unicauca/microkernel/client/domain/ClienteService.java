/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unicauca.microkernel.client.domain;

import co.unicauca.microkernel.client.access.IClienteAccess;
import co.unicauca.microkernel.common.entities.Component;
import co.unicauca.microkernel.common.entities.Dish;
import co.unicauca.microkernel.common.entities.User;

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
    public String createDish(Dish plate) throws Exception{
        return this.service.createDish(plate);
    }
    public String deleteComponente(int prmcompID) throws Exception{
         return this.service.deleteComponente(prmcompID);
    }
    public String deleteDish(int prmPlateID) throws Exception{
        return this.service.deleteDish(prmPlateID);
    }
    public Dish findDish(int prmPlateID)throws Exception{
        return this.service.findDish(prmPlateID);
    }
    public String createUser(User prmObjUser)throws Exception{
        return this.service.createUser(prmObjUser);
    }
    public String findUser(String prmUserLoginName)throws Exception{
        return this.service.findUser(prmUserLoginName);
    }
    public String validateUser(String prmUserLoginName, String prmUserPassword)throws Exception{
        return this.service.validateUser(prmUserLoginName, prmUserPassword);
    }
    public String validateTypeUser(String prmUserLoginName, String prmUserType)throws Exception{
        return this.service.validateTypeUser(prmUserLoginName, prmUserType);
    }
}
