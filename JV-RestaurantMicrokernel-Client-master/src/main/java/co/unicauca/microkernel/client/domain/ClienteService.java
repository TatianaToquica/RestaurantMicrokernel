/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unicauca.microkernel.client.domain;

import co.unicauca.microkernel.client.access.IClienteAccess;
import co.unicauca.microkernel.common.entities.Component;
import co.unicauca.microkernel.common.entities.DayMenu;
import co.unicauca.microkernel.common.entities.Dish;
import co.unicauca.microkernel.common.entities.User;
import java.util.List;

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
    public String deleteComponente(String prmCompName) throws Exception{
         return this.service.deleteComponente(prmCompName);
    }
    public String findComponente(String prmCompName)throws Exception{
        return this.service.findComponente(prmCompName);
    }
    public List<Component> findAllComponentes(String LoginAdmin)throws Exception{
        return this.service.findAllComponentes(LoginAdmin);
    }
    
     
    public String createDish(Dish plate) throws Exception{
        return this.service.createDish(plate);
    }    
    public String deleteDish(int prmPlateID) throws Exception{
        return this.service.deleteDish(prmPlateID);
    }    
    public String findDish(String prmDishName)throws Exception{
        return this.service.findDish(prmDishName);
    }
    public List<Dish> findAllDish(String LoginAdmin)throws Exception{
        return this.service.findAllDish(LoginAdmin);
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
    public String createMenuDia(DayMenu menu)throws Exception{
        return this.service.createMenuDia(menu);
    }
    public List<Component> findCompDia(String dia, String LoginAdmin)throws Exception{
        return this.service.findCompDia(dia, LoginAdmin);
    }
}
