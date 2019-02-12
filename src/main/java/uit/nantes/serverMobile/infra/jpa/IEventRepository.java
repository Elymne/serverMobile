package uit.nantes.serverMobile.infra.jpa;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import uit.nantes.serverMobile.api.entities.Event;

public interface IEventRepository  extends CrudRepository<Event, String>{
	
	@Query("select e from Event e where e.idUser = :idUser")
	Iterable<Event> findAllByIdUser(@Param("idUser") String idUser);

	Event findByTitle(String title);
}
