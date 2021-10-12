/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unicauca.microkernel.core.services;

import co.unicauca.microkernel.common.entities.DayMenu;
import co.unicauca.microkernel.core.domain.IMenuDiaRepository;

/**
 *
 * @author HP
 */
public class MenuDiaService {
    private final IMenuDiaRepository repository;
    
    public MenuDiaService(IMenuDiaRepository repository){
        this.repository = repository;
    }
    public String createMenuDia(DayMenu menu){
        return repository.createMenuDia(menu);
    }
    public String findCompDia(String dia, String LoginAdmin){
        return repository.findCompDia(dia, LoginAdmin);
    }
}
