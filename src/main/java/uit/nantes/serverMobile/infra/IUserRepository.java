package uit.nantes.serverMobile.infra;

import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import uit.nantes.serverMobile.api.entities.User;

/**
 * @author Djurdjevic Sacha
 */
@Transactional
public interface IUserRepository extends CrudRepository<User, String>{
    
    User findByPseudo(String id);
    
    User findByEmail(String id);
}
