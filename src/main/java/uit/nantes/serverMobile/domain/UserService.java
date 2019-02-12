package uit.nantes.serverMobile.domain;

import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import uit.nantes.serverMobile.api.entities.User;
import uit.nantes.serverMobile.domain.util.UserCheck;
import uit.nantes.serverMobile.infra.jpa.IUserRepository;

/**
 * @author Djurdjevic Sacha
 */
public class UserService {

    @Autowired
    IUserRepository userRepository;

    public User findById(String id) {
        User result = new User("", "", "");
        try {
            result = userRepository.findById(id).get();
        } catch (NoSuchElementException e) {
            System.out.println(e);
        }
        return result;
    }

    public User findByPseudo(String pseudo) {
        User result = null;
        for (User user : userRepository.findAll()) {
            if (user.getPseudo().equals(pseudo)) {
                result = user;
                break;
            }
        }
        return result;
    }

    public User findByEmail(String email) {
        User result = null;
        for (User user : userRepository.findAll()) {
            if (user.getEmail().equals(email)) {
                result = user;
                break;
            }
        }
        return result;
    }

    public boolean update(String id, User user) {
        boolean result = true;
        User userUpdate = userRepository.findById(id).get();

        if (UserCheck.checkUpdate(user, userUpdate)) {
            userUpdate.setEmail(user.getEmail());
            userUpdate.setPassword(user.getPassword());
            userUpdate.setPseudo(user.getPseudo());
            userRepository.save(userUpdate);
        } else {
            result = false;
        }

        return result;
    }

    public boolean insert(User user) {
        String pseudo = null;
        String email = null;
        if(this.findByPseudo(user.getPseudo()).equals(null)){
            pseudo = user.getPseudo();
        }
        if(this.findByEmail(user.getEmail()).equals(null)){
            email = user.getEmail();
        }

        boolean result = false;

        if (UserCheck.checkInsert(user, pseudo, email)) {
            userRepository.save(user);
            result = true;
        }

        return result;
    }

    public boolean deleteById(String id) {
        User userDelete = null;
        boolean result = false;

        try {
            userDelete = userRepository.findById(id).get();
            userRepository.delete(userDelete);
            result = true;
        } catch (NoSuchElementException e) {
            System.out.println(e);
        }

        return result;
    }

}
