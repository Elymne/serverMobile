package uit.nantes.serverMobile.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uit.nantes.serverMobile.api.entities.User;
import uit.nantes.serverMobile.infra.IUserRepository;

/**
 * @author DJurdjevic Sacha
 */
@Service
public class UserService {
    
    @Autowired
    IUserRepository userRepository;
    
    public User findById(String id){
        return userRepository.findById(id).get();
    }
    
    public User findByPseudo(String pseudo){
        return userRepository.findByPseudo(pseudo);
    }
    
    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }
    
    public void updatePseudo(String id, String pseudo){
        User userUpdate = userRepository.findById(id).get();
        userUpdate.setPseudo(pseudo);
        userRepository.save(userUpdate);
    }
    
    public void updatePassword(String id, String password){
        User userUpdate = userRepository.findById(id).get();
        userUpdate.setPassword(password);
        userRepository.save(userUpdate);
    }
    
    public void updateEmail(String id, String email){
        User userUpdate = userRepository.findById(id).get();
        userUpdate.setEmail(email);
        userRepository.save(userUpdate);
    }
    
    public void insert(User user){
        userRepository.save(user);
    }
    
    public void deleteById(String id){
        User userDelete = userRepository.findById(id).get();
        userRepository.delete(userDelete);
    }
    
}
