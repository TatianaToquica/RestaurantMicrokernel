/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unicauca.microkernel.core.domain;

import co.unicauca.microkernel.common.entities.Component;
import co.unicauca.microkernel.common.entities.DayMenu;

/**
 *
 * @author HP
 */
public interface IMenuDiaRepository {
    
    public String createMenuDia(DayMenu menu);
    public String findCompDia(String dia, String LoginAdmin);
}
