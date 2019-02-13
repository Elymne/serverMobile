package uit.nantes.serverMobile.infra.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import uit.nantes.serverMobile.api.entities.Event;

public interface IEventRepository  extends JpaRepository<Event, String>{
}
