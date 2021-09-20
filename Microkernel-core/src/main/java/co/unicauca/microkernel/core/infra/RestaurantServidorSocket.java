/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.unicauca.microkernel.core.infra;

import co.unicauca.microkernel.common.entities.Component;
import co.unicauca.microkernel.common.entities.Dish;
import co.unicauca.microkernel.common.entities.User;
import co.unicauca.microkernel.common.infra.JsonError;
import co.unicauca.microkernel.common.infra.Protocol;
import co.unicauca.microkernel.common.infra.Utilities;
import co.unicauca.microkernel.core.domain.Factory;
import co.unicauca.microkernel.core.domain.IComponentRepository;
import co.unicauca.microkernel.core.domain.IDishRepository;
import co.unicauca.microkernel.core.domain.IUserRepository;
import co.unicauca.microkernel.core.services.ComponentService;
import co.unicauca.microkernel.core.services.DishService;
import co.unicauca.microkernel.core.services.UserService;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.google.gson.Gson;

/**
 *
 * @author HP
 */
public class RestaurantServidorSocket implements Runnable{
    /**
     * Objeto de tipo ServerSocket.
     */
    private static ServerSocket ssock;

    /**
     * Objeto de tipo Socket por donde se hace la petición/respuesta.
     */
    private static Socket socket;

    /**
     * Objeto de tipo Scanner que permite leer un flujo de datos del socket.
     */
    private Scanner input;

    /**
     * Objeto de tipo PrintStream que permite escribir un flujo de datos del
     * socket.
     */
    private PrintStream output;

    /**
     * Puerto por donde escucha el server socket, la configuracion que tomara
     * sera de 'server.port'.
     */
    private static final int PORT = Integer.parseInt(Utilities.loadProperty("server.port"));
    
    private ComponentService serviceComponent;
    private DishService serviceDish;
    private UserService serviceUser;
    
    public RestaurantServidorSocket() {
        //inyeccion de dependencias par hacer la inyeccion
        IComponentRepository componentRepo = Factory.getInstance().getComponentRepository();
        serviceComponent = new ComponentService(componentRepo);
        IDishRepository dishRepo = Factory.getInstance().getDishRepository();
        serviceDish = new DishService(dishRepo);
        IUserRepository userRepo = Factory.getInstance().getUserRepository();
        serviceUser = new UserService(userRepo);
        //codificar plato, restaurante...
    }
    /**
     * Metodo que permitira iniciar el servidor.
     */
    public void start() {
        openPort();
        while (true) {

            waitToClient();
            throwThread();
        }
    }

    /**
     * Metodo encargado de arrojar un hilo.
     */
    private static void throwThread() {
        new Thread(new RestaurantServidorSocket()).start();
    }

    /**
     * Metodo que se encarga de Instanciar el server socket y abre el puerto
     * respectivo.
     */
    private static void openPort() {
        try {
            ssock = new ServerSocket(PORT);
            Logger.getLogger("Server").log(Level.INFO, "Servidor iniciado, escuchando por el puerto {0}", PORT);
        } catch (IOException ex) {
            Logger.getLogger(RestaurantServidorSocket.class.getName()).log(Level.SEVERE, "Error del server socket al abrir el puerto", ex);
        }
    }

    /**
     * Metodo que se encarga de esperar a que el cliente se conecte y le
     * devuelve un socket.
     */
    private static void waitToClient() {
        try {
            socket = ssock.accept();
            Logger.getLogger("Socket").log(Level.INFO, "Socket conectado");
        } catch (IOException ex) {
            Logger.getLogger(RestaurantServidorSocket.class.getName()).log(Level.SEVERE, "Error al abrir un socket", ex);
        }
    }

    /**
     * Metodo encargado de darle cuerpo a un hilo.
     */
    @Override
    public void run() {
        try {
            createStreams();
            readStream();
            closeStream();

        } catch (IOException ex) {
            Logger.getLogger(RestaurantServidorSocket.class.getName()).log(Level.SEVERE, "Error al leer el flujo", ex);
        }
    }

    /**
     * Crea los flujos con el socket.
     *
     * @throws IOException
     */
    private void createStreams() throws IOException {
        output = new PrintStream(socket.getOutputStream());
        input = new Scanner(socket.getInputStream());
    }

    /**
     * Lee el flujo del socket, para ello extrae el flujo que envio el cliente.
     */
    private void readStream() {
        if (input.hasNextLine()) {
            // Extrae el flujo que envió la aplicación cliente
            String request = input.nextLine();
            processRequest(request);

        } else {
            output.flush();
            String errorJson = generateErrorJson();
            output.println(errorJson);
        }
    }
    /**
     * Metodo encargado de procesar una peticion proveniente del cliente.
     *
     * @param requestJson Peticion que proviene del socket del cliente en
     * formato json.
     */
    private void processRequest(String requestJson) {
        Gson gson = new Gson();
        Protocol protocolRequest = gson.fromJson(requestJson, Protocol.class);
        //saca de request la persona que ha hecho la solicitud, en nuestro caso administrador o comprador
        switch (protocolRequest.getResource()) {
            case "administrador":
                //funciona exactamente igual platoD postPlatoDia
                if (protocolRequest.getAction().equals("postComponente")) {
                    administradorRegistrarComponente(protocolRequest);
                }
                if (protocolRequest.getAction().equals("postPlatoEspecial")) {
                    administradorRegistrarPlatoEspecial(protocolRequest);
                }
                if (protocolRequest.getAction().equals("updateComponente")) {
                    administradorUpdateComponent(protocolRequest);
                }
                if (protocolRequest.getAction().equals("deleteComponent")) {
                    administradorDeleteComponent(protocolRequest);
                }
                if (protocolRequest.getAction().equals("deleteDish")) {
                    administradorDeleteDish(protocolRequest);
                }
                break;
            case "sistema":
                if (protocolRequest.getAction().equals("postCrearUser")) {
                    sistemaRegistrarUser(protocolRequest);
                }
                if (protocolRequest.getAction().equals("postFindUser")) {
                    sistemaFindUser(protocolRequest);
                }
                if (protocolRequest.getAction().equals("postValidateUser")) {
                    sistemaValidateUser(protocolRequest);
                }
                if (protocolRequest.getAction().equals("postValidateTypeUser")) {
                    sistemaValidateTypeUser(protocolRequest);
                }
                
        }
    }
    private void administradorRegistrarComponente(Protocol protocolRequest) {
        //crea la instancia
        Component comp = new Component();
        //se asignan los atributos de la instancia, segun los valores de los parametros
        //el orden debe ser exacto
        comp.setCompId(Integer.parseInt(protocolRequest.getParameters().get(0).getValue()));
        comp.setCompName(protocolRequest.getParameters().get(1).getValue());
        comp.setCompType(protocolRequest.getParameters().get(2).getValue());
        comp.setCompPrice(Integer.parseInt(protocolRequest.getParameters().get(3).getValue()));
        comp.setCompImage(protocolRequest.getBytes());
        //hacer validacion para esta, es decir sobre el parseo del dato
        String response;
        //el servicio comunicara con la base de datos,
        //se pasa el plato creado, y servicio llamara al repositorio
        response = serviceComponent.createComponente(comp);
        output.println(response);
    }
    private void administradorUpdateComponent(Protocol protocol){
        Component comp = new Component();
        comp.setCompId(Integer.parseInt(protocol.getParameters().get(0).getValue()));        
        comp.setCompName(protocol.getParameters().get(1).getValue());
        comp.setCompType(protocol.getParameters().get(2).getValue());
        comp.setCompPrice(Integer.parseInt(protocol.getParameters().get(3).getValue()));
        comp.setCompImage(protocol.getBytes());
        
        String response = null;
        response = serviceComponent.updateComponente(comp.getCompId());
        output.println(response);
        Logger.getLogger(RestaurantServidorSocket.class.getName()).log(Level.SEVERE, "response: "+response);
    }
    private void administradorDeleteComponent(Protocol protocolRequest) {
        //creo el id de la racion
        int compId;
        //se asignan los atributos de la instancia, segun los valores de los parametros
        //el orden debe ser exacto
        compId = (Integer.parseInt(protocolRequest.getParameters().get(0).getValue()));
        //hacer validacion para esta, es decir sobre el parseo del dato
        String response = null;
        //el servicio comunicara con la base de datos,
        //se pasa el plato creado, y servicio llamara al repositorio
        response = serviceComponent.deleteComponente(compId);
        output.println(response);
    }
    private void administradorRegistrarPlatoEspecial(Protocol protocolRequest) {
        //crea la instancia
        Dish plate=new Dish();
        //se asignan los atributos de la instancia, segun los valores de los parametros
        //el orden debe ser exacto
        plate.setDishID(Integer.parseInt(protocolRequest.getParameters().get(0).getValue()));
        plate.setDishName(protocolRequest.getParameters().get(1).getValue());
        plate.setDishDescription(protocolRequest.getParameters().get(2).getValue());
        plate.setDishPrice(Integer.parseInt(protocolRequest.getParameters().get(3).getValue()));
        plate.setDishImage(protocolRequest.getBytes());
        //hacer validacion para esta, es decir sobre el parseo del dato
        String response;
        //el servicio comunicara con la base de datos,
        //se pasa el plato creado, y servicio llamara al repositorio
        
        response = serviceDish.createDish(plate);
        output.println(response);
        
    }
    private void administradorDeleteDish(Protocol protocolRequest) {
        //creo el id de la racion
        int dishId;
        //se asignan los atributos de la instancia, segun los valores de los parametros
        //el orden debe ser exacto
        dishId = (Integer.parseInt(protocolRequest.getParameters().get(0).getValue()));
        String response = null;
        //el servicio comunicara con la base de datos,
        //se pasa el plato creado, y servicio llamara al repositorio
        response = serviceDish.deleteDish(dishId);
        output.println(response);
    }
    private void sistemaRegistrarUser(Protocol protocolRequest) {
        //crea la instancia
        User usuario=new User();
        //se asignan los atributos de la instancia, segun los valores de los parametros
        //el orden debe ser exacto
        usuario.setUserLoginName(protocolRequest.getParameters().get(0).getValue());
        usuario.setUserPassword(protocolRequest.getParameters().get(1).getValue());
        usuario.setUserName(protocolRequest.getParameters().get(2).getValue());
        usuario.setUserLastName(protocolRequest.getParameters().get(3).getValue());
        usuario.setUserAddres(protocolRequest.getParameters().get(4).getValue());
        usuario.setUserMobile(protocolRequest.getParameters().get(5).getValue());
        usuario.setUserEmail(protocolRequest.getParameters().get(6).getValue());
        usuario.setUserType(protocolRequest.getParameters().get(7).getValue());
        //hacer validacion para esta, es decir sobre el parseo del dato
        String response = null;
        //el servicio comunicara con la base de datos,
        //se pasa el plato creado, y servicio llamara al repositorio
        
        response = serviceUser.createUser(usuario);
        output.println(response);
    }
    private void sistemaFindUser(Protocol protocolRequest) {
        String prmUserLoginName;
        
        prmUserLoginName = (protocolRequest.getParameters().get(0).getValue());
        String response = null;
        //el servicio comunicara con la base de datos,
        //se pasa el plato creado, y servicio llamara al repositorio
        response = serviceUser.findUser(prmUserLoginName);
        output.println(response);
    }
    private void sistemaValidateUser(Protocol protocolRequest) {
        String prmUserLoginName;
        String prmUserPassword;
        prmUserLoginName = (protocolRequest.getParameters().get(0).getValue());
        prmUserPassword = (protocolRequest.getParameters().get(1).getValue());
        String response = null;
        //el servicio comunicara con la base de datos,
        //se pasa el plato creado, y servicio llamara al repositorio
        response = serviceUser.validateUser(prmUserLoginName,prmUserPassword);
        output.println(response);
    }
     private void sistemaValidateTypeUser(Protocol protocolRequest) {
        String prmUserLoginName;
        String prmUserType;
        prmUserLoginName = (protocolRequest.getParameters().get(0).getValue());
        prmUserType = (protocolRequest.getParameters().get(1).getValue());
        String response = null;
        //el servicio comunicara con la base de datos,
        //se pasa el plato creado, y servicio llamara al repositorio
        response = serviceUser.validateTypeUser(prmUserLoginName,prmUserType);
        output.println(response);
    }
    /**
     * Genera un ErrorJson genérico en caso de fallar alguna solicitud no
     * controlada.
     *
     * @return error en formato json
     */
    private String generateErrorJson() {
        List<JsonError> errors = new ArrayList<>();
        JsonError error = new JsonError();
        error.setCode("400");
        error.setError("BAD_REQUEST");
        error.setMessage("Error en la solicitud");
        errors.add(error);

        Gson gson = new Gson();
        String errorJson = gson.toJson(errors);

        return errorJson;
    }

    /**
     * Cierra los flujos de entrada y salida
     *
     * @throws IOException
     */
    private void closeStream() throws IOException {
        output.close();
        input.close();
        socket.close();
    }      
    
}
