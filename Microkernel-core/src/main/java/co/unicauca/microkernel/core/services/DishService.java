/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unicauca.microkernel.core.services;

import co.unicauca.microkernel.common.entities.Dish;
import co.unicauca.microkernel.core.domain.IDishRepository;

/**
 *
 * @author HP
 */
public class DishService {
    private final IDishRepository repository;
    
    public DishService(IDishRepository repository){
        this.repository = repository;
    }
    
    public String createDish(Dish prmObjPlate){
        //hacer validaciones aqui
        return repository.createDish(prmObjPlate);
    }
     public String deleteDish(int prmplatoID){
        //hacer validaciones aqui
        return repository.deleteDish(prmplatoID);
    }
     public Dish findDish(int prmplatoID){
         return repository.findDish(prmplatoID);
     }
}
