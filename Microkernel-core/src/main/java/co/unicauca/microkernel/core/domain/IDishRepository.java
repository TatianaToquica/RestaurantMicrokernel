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
     * Borra un plato especial de la base de datos
     * @param prmplatoID identificador del plato
     * @return respuesta de exito o fracaso sobre la operacion
     */
    public String deleteDish(int prmplatoID);
    /**
     * Actualiza la información de un plato
     * @param prmObjPlato Plato a modificar
     * @return nombre del plato que fue actualizado
     */
    public String updateDish(Dish prmObjPlato);
    
    /**
     * Encuentra todos los platos    
     * @return Lista de platos especiales
     */
    public List<Dish> findAllDish();    
    
}
