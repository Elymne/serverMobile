package uit.nantes.serverMobile.domain;

import org.springframework.beans.factory.annotation.Autowired;
import uit.nantes.serverMobile.api.entities.User;
import uit.nantes.serverMobile.domain.security.UserCheck;
import uit.nantes.serverMobile.infra.jpa.IUserRepository;

/**
 * @author Djurdjevic Sacha
 */
public class UserService {

    @Autowired
    IUserRepository userRepository;

    public User findById(String id) {
        User result = new User();
        result.notExist();
        if (userRepository.existsById(id)) {
            result = userRepository.findById(id).get();
            result.exist();
        }
        return result;
    }

    public User findByPseudo(String pseudo) {
        User result = new User();
        result.notExist();
        for (User user : userRepository.findAll()) {
            if (user.getPseudo().equals(pseudo)) {
                result = user;
                result.exist();
                break;
            }
        }
        return result;
    }

    public User findByEmail(String email) {
        User result = new User();
        result.notExist();
        for (User user : userRepository.findAll()) {
            if (user.getEmail().equals(email)) {
                result = user;
                result.exist();
                break;
            }
        }
        return result;
    }

    public boolean update(String id, User user) {
        boolean result = true;
        if (userRepository.existsById(id)) {
            User userUpdate = userRepository.findById(id).get();
            if (UserCheck.checkUpdate(user, userUpdate)) {
                userUpdate.setEmail(user.getEmail());
                userUpdate.setPassword(user.getPassword());
                userUpdate.setPseudo(user.getPseudo());
                userRepository.save(userUpdate);
            } else {
                result = false;
            }
        }
        return result;
    }

    public boolean insert(User user) {
        boolean result = false;
        boolean pseudo = !this.findByPseudo(user.getPseudo()).doesExist();
        boolean email = !this.findByEmail(user.getEmail()).doesExist();
        if (pseudo && email) {
            if (UserCheck.checkInsert(user)) {
                userRepository.save(user);
                result = true;
            }
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
