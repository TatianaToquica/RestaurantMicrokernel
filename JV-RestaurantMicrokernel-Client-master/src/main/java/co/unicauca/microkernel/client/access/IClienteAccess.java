/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unicauca.microkernel.client.access;

import co.unicauca.microkernel.common.entities.Component;
import co.unicauca.microkernel.common.entities.Dish;
import co.unicauca.microkernel.common.entities.User;


/**
 *
 * @author HP
 */
public interface IClienteAccess {
    public String createComponente(Component component) throws Exception;
    public String updateComponente(Component prmObjComponente)throws Exception;
    public String deleteComponente(int prmcompId) throws Exception;
    
    
    public String createDish(Dish plate) throws Exception;
    public String deleteDish(int prmPlateID) throws Exception;    
    public Dish findDish(int prmPlateID)throws Exception;
    
    public String createUser(User prmObjUser)throws Exception;
    public String findUser(String prmUserLoginName)throws Exception;
    public String validateUser(String prmUserLoginName, String prmUserPassword)throws Exception;
    public String validateTypeUser(String prmUserLoginName, String prmUserType)throws Exception;
}
