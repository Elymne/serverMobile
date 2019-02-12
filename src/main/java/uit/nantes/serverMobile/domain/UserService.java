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
        return userRepository.findByPseudo(pseudo);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
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
        String pseudo = this.findByPseudo(user.getId()).getId();
        String email = this.findByEmail(user.getId()).getId();

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
