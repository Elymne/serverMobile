package uit.nantes.serverMobile.infra;

import org.springframework.data.repository.CrudRepository;

import uit.nantes.serverMobile.api.entities.Event;

public interface IEventRepository  extends CrudRepository<Event, String>{

}
