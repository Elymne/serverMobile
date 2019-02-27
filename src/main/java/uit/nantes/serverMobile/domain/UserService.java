package uit.nantes.serverMobile.domain;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uit.nantes.serverMobile.api.entities.User;
import uit.nantes.serverMobile.api.pojo.UserPojo;
import uit.nantes.serverMobile.domain.util.UserCheck;
import uit.nantes.serverMobile.infra.jpa.IUserRepository;

/**
 * @author Daniel Clemente Aguirre
 * @author Djurdjevic Sacha
 */
@Service
public class UserService {

    @Autowired
    IUserRepository userRepository;

    /*
    * @return La liste de tous les utilisateurs
    */
    public List<User> findAll() {
        return userRepository.findAll();
    }

    /*
    * @param String id Un identifiant d'utilisateur
    * @return Un utilisateur correspondant à l'id rentré en paramètre
    */
    public User findById(String id) {
        User result = new User();
        if (userRepository.existsById(id)) {
            result = userRepository.findById(id).get();
        }
        return result;
    }

    /*
    * @param String pseudo Un pseudo d'utilisateur
    * @return Un utilisateur correspondant au pseudo rentré en paramètre
    */
    public User findByPseudo(String pseudo) {
        User result = new User();
        for (User user : userRepository.findAll()) {
            if (user.getUsername().equals(pseudo)) {
                result = user;
                break;
            }
        }
        return result;
    }

    /*
    * @param String email Un email d'utilisateur
    * @return Un utilisateur correspondant à l'email rentré en paramètre
    */
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

    /*
    * @param String id Un identifiant d'utilisateur
    * @param UserPojo userPojo L'utilisateur modifié
    * @return Un boolean à vrai si l'update s'est effectué, faux si elle a échoué
    */
    public boolean update(String id, UserPojo userPojo) {
        boolean result = true;
        if (userRepository.existsById(id)) {
            User user = userRepository.findById(id).get();
            if (UserCheck.checkUpdate(userPojo)) {
                user.setUsername(userPojo.getPseudo());
                user.setEmail(userPojo.getEmail());
                user.setPassword(userPojo.getPassword());
                userRepository.save(user);
            } else {
                result = false;
            }
        }
        return result;
    }

    /*
    * @param UserPojo userPojo Un pojo de l'utilisateur à insérer
    * @return Un boolean à vrai si l'insertion s'est effectué, faux si elle a échoué
    */
    public boolean insert(UserPojo userPojo) {
        boolean result = false;
        if (UserCheck.checkInsert(userPojo)
                && findByPseudo(userPojo.getPseudo()).getId() == null) {
            User user = new User();
            user.createId();
            user.setUsername(userPojo.getPseudo());
            user.setEmail(userPojo.getEmail());
            user.setPassword(userPojo.getPassword());
            userRepository.save(user);
            result = true;
        }
        return result;
    }

    /*
    * @param String id Un identifiant d'utilisateur
    * @return Un boolean à vrai si le delete s'est effectué, faux si elle a échoué
    */
    public boolean delete(String id) {
        boolean result = false;
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            result = true;
        }
        return result;
    }

}
