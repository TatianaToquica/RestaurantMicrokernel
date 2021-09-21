/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unicauca.microkernel.core.services;

import co.unicauca.microkernel.common.entities.Component;
import co.unicauca.microkernel.core.domain.IComponentRepository;

/**
 *
 * @author HP
 */
public class ComponentService {
    private final IComponentRepository repository;
    
    public ComponentService(IComponentRepository repository){
        this.repository = repository;
    }
    
    public String createComponente(Component prmObjComponente){
        //hacer validaciones aqui
        return repository.createComponente(prmObjComponente);
    }
   
    public String deleteComponente(int prmcompId) {
        //hacer validaciones aqui
        return repository.deleteComponente(prmcompId);
    }
    public String updateComponente(Component prmObjComponente){
        //hacer validaciones aqui
        return repository.updateComponente(prmObjComponente);
    }
}
