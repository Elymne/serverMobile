package uit.nantes.serverMobile.infra.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import uit.nantes.serverMobile.api.entities.Event;

/**
 * @author Daniel Clemente Aguirre
 * @author Djurdjevic Sacha
 */
public interface IEventRepository  extends JpaRepository<Event, String>{
}
