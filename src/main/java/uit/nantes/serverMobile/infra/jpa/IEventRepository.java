package uit.nantes.serverMobile.infra.jpa;

import org.springframework.data.repository.CrudRepository;

import uit.nantes.serverMobile.api.entities.Event;
import uit.nantes.serverMobile.api.entities.User;

public interface IEventRepository  extends CrudRepository<Event, String>{

	Iterable<Event> findAllByUser(User createur);

	Event findByTitle(String title);
}
