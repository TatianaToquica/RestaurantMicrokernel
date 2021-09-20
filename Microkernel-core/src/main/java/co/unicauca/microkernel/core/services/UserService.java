
package co.unicauca.microkernel.core.services;

import co.unicauca.microkernel.common.entities.User;
import co.unicauca.microkernel.core.domain.IUserRepository;

/**
 *
 * @author HP
 */
public class UserService {
     private final IUserRepository repository;
     
     public UserService(IUserRepository repository){
        this.repository = repository;
    }
    
    public String createUser(User prmObjUser){
        //hacer validaciones aqui
        return repository.createUser(prmObjUser);
    }
    public String findUser(String prmUserLoginName){
        //hacer validaciones aqui
        return repository.findUser(prmUserLoginName);
    }
    public String validateUser(String prmUserLoginName, String prmUserPassword) {
        //hacer validaciones aqui
        return repository.validateUser(prmUserLoginName, prmUserPassword);
    }
    public String validateTypeUser(String prmUserLoginName, String prmUserType){
        return repository.validateTypeUser(prmUserLoginName, prmUserType);
    }
}
