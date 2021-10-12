/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unicauca.microkernel.client.access;

import co.unicauca.microkernel.common.entities.Component;
import co.unicauca.microkernel.common.entities.DayMenu;
import co.unicauca.microkernel.common.entities.Dish;
import co.unicauca.microkernel.common.entities.User;
import java.util.List;


/**
 *
 * @author HP
 */
public interface IClienteAccess {
    public String createComponente(Component component) throws Exception;
    public String updateComponente(Component prmObjComponente)throws Exception;
    public String deleteComponente(String prmCompName) throws Exception;
    public String findComponente(String prmCompName)throws Exception;
    public List<Component> findAllComponentes(String LoginAdmin)throws Exception;
    
    public String createDish(Dish plate) throws Exception;
    public String deleteDish(int prmPlateID) throws Exception;    
    public String findDish(String prmDishName)throws Exception;
    public List<Dish> findAllDish(String LoginAdmin)throws Exception;
    
    
    public String createUser(User prmObjUser)throws Exception;
    public String findUser(String prmUserLoginName)throws Exception;
    public String validateUser(String prmUserLoginName, String prmUserPassword)throws Exception;
    public String validateTypeUser(String prmUserLoginName, String prmUserType)throws Exception;
    
    public String createMenuDia(DayMenu menu)throws Exception;
    public List<Component> findCompDia(String dia, String LoginAdmin)throws Exception;
}
