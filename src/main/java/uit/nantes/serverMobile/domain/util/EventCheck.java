package uit.nantes.serverMobile.domain.util;

import uit.nantes.serverMobile.api.entities.Event;

public class EventCheck {

	public static boolean checkEventUpdate(Event newEvent, Event repoEvent) {
		boolean result = false;
		if (newEvent.getTitle().equals(repoEvent.getTitle())) {
			if (newEvent.getPlace().equals(repoEvent.getPlace())) {
				if (newEvent.getDate().equals(repoEvent.getDate())) {
					// if(newEvent.getActive().equals(repoEvent.getActive())) {
					return true;
					// }
				}
			}
		}
		return result;
	}

	public static boolean checkEventInsert(Event event, Event repoEvent) {
		boolean result = false;
		if(event.getTitle().equals(repoEvent.getTitle())) {
			result = true;
		}
		return result;
	}

}
