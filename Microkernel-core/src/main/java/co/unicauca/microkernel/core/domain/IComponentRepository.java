package co.unicauca.microkernel.core.domain;
import co.unicauca.microkernel.common.entities.Component;
import java.util.List;

/**
 * Interfaz para las utilidades de la entidad Component
 * @author Luis Arango
 */
public interface IComponentRepository {
    /**
     * Crea un nuevo componente
     * @param prmObjComponente objeto a ser creado
     * @return ID del componente creado
     */
    public String createComponente(Component prmObjComponente);
    /**
     * Borra un componente de la base de datos
     * @param prmcompId identificador del plato
     * @return Id del componente borrado
     */
    public String deleteComponente(int prmcompId);
    /**
     * Actualiza la información de un componente
     * @param prmObjComponente Plato a modificar
     * @return nombre del componente actualizado
     */
    public String updateComponente(Component prmObjComponente);
    
    /**
     * Encuentra todos los componentes      
     * @return Lista de Componentes
     */
    public List<Component> findAllComponentes();
    
   

}
