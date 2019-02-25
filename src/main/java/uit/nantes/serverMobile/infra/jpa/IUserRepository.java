package uit.nantes.serverMobile.infra.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import uit.nantes.serverMobile.api.entities.User;

/**
 * @author Daniel Clemente Aguirre
 * @author Djurdjevic Sacha
 */
public interface IUserRepository extends JpaRepository<User, String>{
}
