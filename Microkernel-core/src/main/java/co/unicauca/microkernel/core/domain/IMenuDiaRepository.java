/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unicauca.microkernel.core.domain;

/**
 *
 * @author HP
 */
public interface IMenuDiaRepository {
    /**
     * Crea Menu dia
     * @param prmcompId id del componente
     * @param prmDia dia en el que sera ofertado el componente
     * @param LoginAdmin login del administrador del restaurante
     * @return ID del componente creado
     */
    public String createMenuDia(int prmcompId,String prmDia,String LoginAdmin);
    
}
