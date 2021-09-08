package co.unicauca.microkernel.core.domain;

import co.unicauca.microkernel.common.entities.Restaurant;
import java.util.List;

/**
 *
 * @author Luis Arango
 */
public interface IRestaurantRepository {
    /**
     * Crea un nuevo restaurante
     * @param prmObjRestaurant Objeto restaurante a ser creado
     * @return Identificador del objeto creado
     */
    public String createRestaurant(Restaurant prmObjRestaurant);
    /**
     * Lista los restaurantes de la base de datos.
     * @return 
     */
    public List<Restaurant> listAllRestaurant();
}
