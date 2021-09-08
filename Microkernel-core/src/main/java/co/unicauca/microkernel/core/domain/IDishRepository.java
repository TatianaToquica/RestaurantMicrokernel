/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unicauca.microkernel.core.domain;

import co.unicauca.microkernel.common.entities.Dish;
import java.util.List;

/**
 *
 * @author HP
 */
public interface IDishRepository {
    /**
     * Crea un nuevo plato
     * @param prmObjPlato objeto a ser creado
     * @return ID del plato creado
     */
    public String createDish(Dish prmObjPlato);
    /**
     * Encuentra un plato por parámetro 
     * @param prmplatoID identificador del plato a buscar
     * @return Objeto de tipo Dish
     */
    public Dish findDish(int prmplatoID);
    /**
     * Encuentra todos los platos    
     * @return Lista de platos especiales
     */
    public List<Dish> findAllDish();
    /**
     * 
     * @param prmplatoID
     * @return 
     */
    public String deleteDish(int prmplatoID);
    public String updateDish(int prmplatoID);
}
