package uit.nantes.serverMobile.domain;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import uit.nantes.serverMobile.api.entities.User;
import uit.nantes.serverMobile.api.pojo.UserPojo;
import uit.nantes.serverMobile.domain.util.UserCheck;
import uit.nantes.serverMobile.infra.jpa.IUserRepository;

/**
 * @author Daniel Clemente Aguirre
 * @author Djurdjevic Sacha
 */
public class UserService {

    @Autowired
    IUserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(String id) {
        User result = new User();
        if (userRepository.existsById(id)) {
            result = userRepository.findById(id).get();
        }
        return result;
    }

    public User findByPseudo(String pseudo) {
        User result = new User();
        for (User user : userRepository.findAll()) {
            if (user.getPseudo().equals(pseudo)) {
                result = user;
                break;
            }
        }
        return result;
    }

    public User findByEmail(String email) {
        User result = new User();
        for (User user : userRepository.findAll()) {
            if (user.getEmail().equals(email)) {
                result = user;
                break;
            }
        }
        return result;
    }

    public boolean update(String id, UserPojo userPojo) {
        boolean result = true;
        if (userRepository.existsById(id)) {
            User user = userRepository.findById(id).get();
            if (UserCheck.checkUpdate(userPojo)) {
                user.setPseudo(userPojo.getPseudo());
                user.setEmail(userPojo.getEmail());
                user.setPassword(userPojo.getPassword());
                userRepository.save(user);
            } else {
                result = false;
            }
        }
        return result;
    }

    public boolean insert(UserPojo userPojo) {
        boolean result = false;
        if (UserCheck.checkInsert(userPojo)) {
            User user = new User();
            user.createId();
            user.setPseudo(userPojo.getPseudo());
            user.setEmail(userPojo.getEmail());
            user.setPassword(userPojo.getPassword());
            userRepository.save(user);
            result = true;
        }
        return result;
    }

    public boolean deleteById(String id) {
        boolean result = false;
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            result = true;
        }
        return result;
    }

}
