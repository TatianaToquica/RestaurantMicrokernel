/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unicauca.microkernel.app;

import co.unicauca.microkernel.core.infra.RestaurantServidorSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author HP
 */
public class Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
            RestaurantServidorSocket socket= new RestaurantServidorSocket();
            socket.start();
        }catch(Exception ex){
            Logger.getLogger("Application").log(Level.SEVERE, "Error al ejecutar la aplicación", ex);
        }
    }
    
}
