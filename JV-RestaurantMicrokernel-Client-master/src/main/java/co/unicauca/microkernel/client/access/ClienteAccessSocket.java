
package co.unicauca.microkernel.client.access;

import co.unicauca.microkernel.client.infra.ServidorSocket;
import co.unicauca.microkernel.common.entities.Component;
import co.unicauca.microkernel.common.entities.DayMenu;
import co.unicauca.microkernel.common.entities.Dish;
import co.unicauca.microkernel.common.entities.User;
import co.unicauca.microkernel.common.infra.JsonError;
import co.unicauca.microkernel.common.infra.Protocol;
import java.io.IOException;
import static java.lang.System.out;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import static java.lang.String.valueOf;
import java.util.List;

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
        protocol.setResource("administrador");
        protocol.setAction("postComponente");
        protocol.addParameter("compID", String.valueOf(instancia.getCompId()));
        protocol.addParameter("compName", String.valueOf(instancia.getCompName()));
        protocol.addParameter("compType", String.valueOf(instancia.getCompType()));
        protocol.addParameter("compPrice", String.valueOf(instancia.getCompPrice()));
        protocol.setBytes(instancia.getCompImage());
        protocol.addParameter("userLoginName", String.valueOf(instancia.getUserLoginName()));
        
        Gson gson = new Gson();
        String requestJson = gson.toJson(protocol);
        System.out.println("json: "+requestJson);
        return requestJson;
    }
    public String createDish(Dish plate) throws Exception {
        String jsonResponse = null;
        //devuelve un string en formato Json que lo que se enviara
        String requestJson = addDish(plate);
        if((this.procesarConexion(requestJson).equals("FALLO"))){
            return null;
        }
        return valueOf(plate.getDishID());
    }
    private String addDish(Dish instancia){
        Protocol protocol = new Protocol();
        protocol.setResource("administrador");
        protocol.setAction("postPlatoEspecial");
        protocol.addParameter("dishID", String.valueOf(instancia.getDishID()));
        protocol.addParameter("dishName", String.valueOf(instancia.getDishName()));
        protocol.addParameter("dishDescription", String.valueOf(instancia.getDishDescription()));
        protocol.addParameter("dishPrice", String.valueOf(instancia.getDishPrice()));
        protocol.setBytes(instancia.getDishImage());  
        protocol.addParameter("userLoginName", String.valueOf(instancia.getUserLoginName()));
        
        Gson gson = new Gson();
        String requestJson = gson.toJson(protocol);
        System.out.println("json: "+requestJson);
        return requestJson;
    }

    @Override
    public String updateComponente(Component prmObjComponente) throws Exception {         
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
    @Override
    public String deleteComponente(String prmCompName) throws Exception {
         var respJson = deleteComponentJson(prmCompName);
        if(this.procesarConexion(respJson).equals("FALLO")){
            return "FALLO";
        }
        return "" + prmCompName;
    }
    private String deleteComponentJson(String prmCompName){
        var protocol = new Protocol();
        protocol.setResource("administrador");
        protocol.setAction("deleteComponent");
        protocol.addParameter("compName", ""+prmCompName);
        
        var gson = new Gson();
        var requestJson = gson.toJson(protocol);
        out.println("json: "+requestJson);
        
        return requestJson;
    }

    
    @Override
    public String deleteDish(int prmPlateID) throws Exception {
        var respJson = deletePlatoEspecialJson(prmPlateID);
        if(this.procesarConexion(respJson).equals("FALLO")){
            return "FALLO";
        }
        return "" + prmPlateID;
    }
     private String deletePlatoEspecialJson(int prmPlateID){
        var protocol = new Protocol();
        protocol.setResource("administrador");
        protocol.setAction("deleteDish");
        protocol.addParameter("dishId", ""+prmPlateID);
        
        var gson = new Gson();
        var requestJson = gson.toJson(protocol);
        out.println("json: "+requestJson);

        return requestJson;

    }

    @Override
    public String createUser(User prmObjUser) throws Exception {
        //String jsonResponse = null;
        //devuelve un string en formato Json que lo que se enviara
        String requestJson = addUser(prmObjUser);
        if((this.procesarConexion(requestJson).equals("FALLO"))){
            return null;
        }
        return valueOf(prmObjUser.getUserName());
    }
    private String addUser(User instancia){
        Protocol protocol = new Protocol();
        protocol.setResource("sistema");
        protocol.setAction("postCrearUser");
        protocol.addParameter("userLoginName", String.valueOf(instancia.getUserLoginName()));
        protocol.addParameter("userPassword", String.valueOf(instancia.getUserPassword()));
        protocol.addParameter("userName", String.valueOf(instancia.getUserName()));
        protocol.addParameter("userLastName", String.valueOf(instancia.getUserLastName()));
        protocol.addParameter("userAddress", String.valueOf(instancia.getUserAddres()));
        protocol.addParameter("userMobile", String.valueOf(instancia.getUserMobile()));
        protocol.addParameter("userEmail", String.valueOf(instancia.getUserEmail()));
        protocol.addParameter("userType", String.valueOf(instancia.getUserType()));
                     
        
        Gson gson = new Gson();
        String requestJson = gson.toJson(protocol);
        System.out.println("json: "+requestJson);
        return requestJson;
    }

    @Override
    public String findUser(String prmUserLoginName) throws Exception {
        var respJson = findUserJson(prmUserLoginName);
        if(this.procesarConexion(respJson).equals("Fallo")){
            return "Fallo";
        }
        return prmUserLoginName;
    }
     private String findUserJson(String prmUserLoginName){
        var protocol = new Protocol();
        protocol.setResource("sistema");
        protocol.setAction("postFindUser");
        protocol.addParameter("userLoginName", prmUserLoginName);
        
        var gson = new Gson();
        var requestJson = gson.toJson(protocol);
        out.println("json: "+requestJson);

        return requestJson;

    }

    @Override
    public String validateUser(String prmUserLoginName, String prmUserPassword) throws Exception {
         var respJson = validateUserJson(prmUserLoginName, prmUserPassword);
        if(this.procesarConexion(respJson).equals("Fallo")){
            return "Fallo";
        }
        return prmUserPassword;
    }
    private String validateUserJson(String prmUserLoginName, String prmUserPassword){
        var protocol = new Protocol();
        protocol.setResource("sistema");
        protocol.setAction("postValidateUser");
        protocol.addParameter("userLoginName", prmUserLoginName);
        protocol.addParameter("userPassword", prmUserPassword);
        var gson = new Gson();
        var requestJson = gson.toJson(protocol);
        out.println("json: "+requestJson);

        return requestJson;
    }

    @Override
    public String validateTypeUser(String prmUserLoginName, String prmUserType) throws Exception {
         var respJson = validateTypeUserJson(prmUserLoginName, prmUserType);
        if(this.procesarConexion(respJson).equals("Fallo")){
            return "Fallo";
        }
        return prmUserType;
    }
    private String validateTypeUserJson(String prmUserLoginName, String prmUserType){
        var protocol = new Protocol();
        protocol.setResource("sistema");
        protocol.setAction("postValidateTypeUser");
        protocol.addParameter("userLoginName", prmUserLoginName);
        protocol.addParameter("userType", prmUserType);
        var gson = new Gson();
        var requestJson = gson.toJson(protocol);
        out.println("json: "+requestJson);

        return requestJson;
    }

    @Override
    public String findComponente(String prmCompName) throws Exception {
        var respJson = findComponentJson(prmCompName);
        if(this.procesarConexion(respJson).equals("Fallo")){
            return "Fallo";
        }
        return prmCompName;
    }
    private String findComponentJson(String prmCompName){
        var protocol = new Protocol();
        protocol.setResource("sistema");
        protocol.setAction("postFindComponent");
        protocol.addParameter("compName", prmCompName);
        
        var gson = new Gson();
        var requestJson = gson.toJson(protocol);
        out.println("json: "+requestJson);

        return requestJson;

    }

    @Override
    public List<Component> findAllComponentes(String LoginAdmin) throws Exception {
        var respJson = findAllComponentsJson(LoginAdmin);
        //System.out.println("AccsesSocket: "+respJson);
        var response = procesarConexion(respJson);         
        return jsonListComponents(response);
    }
    private String findAllComponentsJson(String LoginAdmin){
        var protocol = new Protocol();
        protocol.setResource("administrador");
        protocol.setAction("postListarComponentes");
        protocol.addParameter("userLoginName", LoginAdmin);
        
        var gson = new Gson();
        var requestJson = gson.toJson(protocol);
        out.println("json: "+requestJson);

        return requestJson;

    }
    /**
     * Convierte un json en una lista de tipo Componente
     * 
     * @param jsonListarComponentes
     * @return 
     */
    private List<Component> jsonListComponents(String jsonListarComponents){
        var gson=new Gson();
        var list = new TypeToken<List<Component>>(){}.getType();
        return gson.fromJson(jsonListarComponents, list);
    }

    @Override
    public List<Dish> findAllDish(String LoginAdmin) throws Exception {
        var respJson = findAllDishJson(LoginAdmin);
        System.out.println("AccsesSocket: "+respJson);
        var response = procesarConexion(respJson);          
        return jsonListDish(response);
    }
    private String findAllDishJson(String LoginAdmin){
        var protocol = new Protocol();
        protocol.setResource("administrador");
        protocol.setAction("postListarPlatos");
        protocol.addParameter("userLoginName", LoginAdmin);
        
        var gson = new Gson();
        var requestJson = gson.toJson(protocol);
        out.println("json: "+requestJson);

        return requestJson;

    }
    /**
     * Convierte un json en una lista de tipo Plato especial
     * 
     * @param jsonListarDish
     * @return 
     */
    private List<Dish> jsonListDish(String jsonListarDish){
        var gson=new Gson();
        var list = new TypeToken<List<Dish>>(){}.getType();
        return gson.fromJson(jsonListarDish, list);
    }

    @Override
    public String findDish(String prmDishName) throws Exception {
        var respJson = findDishJson(prmDishName);
        if(this.procesarConexion(respJson).equals("Fallo")){
            return "Fallo";
        }
        return prmDishName;
    }
    private String findDishJson(String prmDishName){
        var protocol = new Protocol();
        protocol.setResource("sistema");
        protocol.setAction("postFindDish");
        protocol.addParameter("dishName", prmDishName);
        
        var gson = new Gson();
        var requestJson = gson.toJson(protocol);
        out.println("json: "+requestJson);

        return requestJson;

    }

    @Override
    public String createMenuDia(DayMenu menu) throws Exception {
        String jsonResponse = null;
        //devuelve un string en formato Json que lo que se enviara
        String requestJson = addCompMenuDia(menu);
        if((this.procesarConexion(requestJson).equals("FALLO"))){
            return "FALLO";
        }
        return valueOf(menu.getDmenDay());
    }
    
    private String addCompMenuDia(DayMenu instancia){
        Protocol protocol = new Protocol();
        protocol.setResource("administrador");
        protocol.setAction("postCrearMenuDia");
        protocol.addParameter("compID", String.valueOf(instancia.getDmenCompID()));
        protocol.addParameter("dmenday", String.valueOf(instancia.getDmenDay()));
        protocol.addParameter("userLoginName", String.valueOf(instancia.getUserLoginName()));
        
        Gson gson = new Gson();
        String requestJson = gson.toJson(protocol);
        System.out.println("json: "+requestJson);
        return requestJson;
    }
    

    @Override
    public List<Component> findCompDia(String dia, String LoginAdmin) throws Exception {
        var respJson = findCompDiaJson(dia,LoginAdmin);
        System.out.println("AccsesSocket: "+respJson);
        var response = procesarConexion(respJson);          
        return jsonListCompMenDia(response);
    }
    private String findCompDiaJson(String dia, String LoginAdmin){
        var protocol = new Protocol();
        protocol.setResource("administrador");
        protocol.setAction("postListarMenuDia");
        protocol.addParameter("dmenday", dia);
        protocol.addParameter("userLoginName", LoginAdmin);
        
        var gson = new Gson();
        var requestJson = gson.toJson(protocol);
        out.println("json: "+requestJson);

        return requestJson;

    }
    /**
     * Convierte un json en una lista de tipo Plato especial
     * 
     * @param jsonListCompMenDia
     * @return 
     */
    private List<Component> jsonListCompMenDia(String jsonListCompMenDia){
        var gson=new Gson();
        var list = new TypeToken<List<Component>>(){}.getType();
        return gson.fromJson(jsonListCompMenDia, list);
    }
    
}
