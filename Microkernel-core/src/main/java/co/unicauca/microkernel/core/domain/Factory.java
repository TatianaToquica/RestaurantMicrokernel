package co.unicauca.microkernel.core.domain;

/**
 *
 * @author Luis Arango
 */
public class Factory {
    //<editor-fold defaultstate="collapsed" desc="Instancia">
    /**
     * Instancia de la clase Factory
     */
    private static Factory instance;
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Constructor">
    /**
     * Constructor privado no parametrizado de la clase Factory
     */
    private Factory(){}
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Get static">
    /**
     * Clase singleton
     * @return instancia de Factory
     */
    public static Factory getInstance(){
        if(instance == null){
            instance = new Factory();
        }
        return instance;
    }
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Metodos">
    /**
     * Método que crea una instancia concreta de la jerarquia IRestaurantRepository.
     * @return una clase hija de la abstracción IRestaurantRepository.
     */
    public IRestaurantRepository getRestaurantRepository(){
        IRestaurantRepository result = new RestaurantRepository();
        return result;
    }
    /**
     * Método que crea una instancia concreta de la jerarquia IUserRepository
     * @return Una clase hija de la abstracción IUserRepository
     */
    public IUserRepository getUserRepository(){
        IUserRepository result = new UserRepository();
        return result;
    }
    /**
     * Método que crea una instancia concreta de la jerarquia IComponentRepository
     * @return Una clase hija de la abstracción IComponentRepository
     */
    public IComponentRepository getComponentRepository(){
        IComponentRepository result = new ComponentRepository();
        return result;
    }
    public IDishRepository getDishRepository(){
        IDishRepository result = new DishRepository();
        return result;
    }
    public IMenuDiaRepository getMenuDiaRepository(){
        IMenuDiaRepository result = new MenuDiaRepository();
        return result;
    } 
//</editor-fold>
}
