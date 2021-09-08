/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unicauca.microkernel.client.access;

import co.unicauca.microkernel.client.infra.ServidorSocket;
import co.unicauca.microkernel.common.entities.Component;
import co.unicauca.microkernel.common.infra.JsonError;
import co.unicauca.microkernel.common.infra.Protocol;
import java.io.IOException;
import static java.lang.System.out;
import com.google.gson.Gson;
import static java.lang.String.valueOf;

/**
 *
 * @author HP
 */
public class ClienteAccessSocket implements IClienteAccess {
    
    private ServidorSocket mySocket;
    
    public ClienteAccessSocket() {
        mySocket = new ServidorSocket();
    }
    
    private String procesarConexion(String requestJson) throws Exception {
        String jsonResponse = null;
        try {
            //se establece la conexion
            mySocket.connect();
            //se envia la solicitud y se recibe una respuesta,
            //(CREO)AQUI VALIDAR SI SE DIO CON EXITO LA OPERACION, SEGUN LA REPUESTA DEL SERVIDOR
            jsonResponse = mySocket.sendStream(requestJson);
            mySocket.closeStream();
            mySocket.disconnect();
            if (jsonResponse.equals("FALLO")) {
                return "FALLO";
            } else {
                out.println("todo normal");
            }
        } catch (IOException ex) {
            ex.getMessage();
        }
        if (jsonResponse == null) {
            throw new Exception("no se pudo conectar al servidor");
        } else {
            if (jsonResponse.contains("error")) {
                //Devolvió algún erroR, usar mejor login
                out.println("hubo algun tipo de error");
                throw new Exception(this.extractMessages(jsonResponse));
            } else {
                //Devuelve la respuesta del servidor
                return jsonResponse;
            }
        }
    }
    
    /**
     * Extra los mensajes de la lista de errores
     *
     * @param jsonResponse lista de mensajes json
     * @return Mensajes de error
     */
    private String extractMessages(String jsonResponse) {
        var errors = jsonToErrors(jsonResponse);
        var msjs = "";
        for (var error : errors) {
            msjs += error.getMessage();
        }
        return msjs;
    }

    /**
     * Convierte el jsonError a un array de objetos jsonError
     *
     * @param jsonError
     * @return objeto MyError
     */
    private JsonError[] jsonToErrors(String jsonError) {
        var gson = new Gson();
        var error = gson.fromJson(jsonError, JsonError[].class);
        return error;
    }
    
    public String createComponente(Component component) throws Exception {
        String jsonResponse = null;
        //devuelve un string en formato Json que lo que se enviara
        String requestJson = addComponente(component);
        if((this.procesarConexion(requestJson).equals("FALLO"))){
            return null;
        }
        return valueOf(component.getCompId());
    }
    
    private String addComponente(Component instancia){
        Protocol protocol = new Protocol();
        protocol.setResource("comprador");
        protocol.setAction("agregarPlatoEspecialPedido");
        protocol.addParameter("plaep_id", String.valueOf(instancia.getCompId()));
//        protocol.addParameter("ped_id", String.valueOf(instancia.getPedId()));
//        protocol.addParameter("plae_id", String.valueOf(instancia.getPlaeId()));
//        protocol.addParameter("cantidad", String.valueOf(instancia.getCantidad()));
        
        Gson gson = new Gson();
        String requestJson = gson.toJson(protocol);
        System.out.println("json: "+requestJson);
        return requestJson;
    }

}
