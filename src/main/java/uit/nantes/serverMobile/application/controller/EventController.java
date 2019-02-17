package uit.nantes.serverMobile.application.controller;

import java.text.ParseException;
import java.util.List;
import org.json.JSONException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import uit.nantes.serverMobile.api.entities.Event;
import uit.nantes.serverMobile.api.entities.User;
import uit.nantes.serverMobile.application.controller.util.JsonResponse;
import uit.nantes.serverMobile.domain.EventService;

@RestController
@RequestMapping(value = "/api/evenement")
public class EventController {

    @Autowired
    EventService eventService;

    @GetMapping(path = "/getAll")
    @ResponseBody
    public List<Event> getAll() {
        return eventService.findAll();
    }
    
    @GetMapping(path = "/get/{id}")
    @ResponseBody
    public Event getEventById(@PathVariable String id) {
        return eventService.findById(id);
    }
    
    @GetMapping(path = "/get/title/{title}")
    @ResponseBody
    public Event getEventByTitle(@PathVariable("title") String title) {
        return eventService.findByTitle(title);
    }

    @PostMapping(path = "/add")
    @ResponseBody
    public String addEvent(@RequestBody Event event) throws JSONException, ParseException {
        event.createId();
        event.setActive(true);
        boolean result = eventService.insert(event);
        return JsonResponse.insertJsonResponse(result).toString();
    }

    @PutMapping(path = "/update/{id}")
    @ResponseBody
    public String updateEvent(@PathVariable String id, @RequestBody Event event) throws JSONException, ParseException {
        boolean result = eventService.update(id, event);
        return JsonResponse.updateJsonResponse(result).toString();
    }

    @PutMapping(path = "/addUser/{id}")
    @ResponseBody
    public String addUserToEvent(@PathVariable String id, @RequestBody User user) throws JSONException, ParseException {
        boolean result = eventService.addUser(id, user);
        return JsonResponse.addUserJsonResponse(result).toString();
    }
    
    @PutMapping(path = "/removeUser/{id}")
    @ResponseBody
    public String removeUserFromEvent(@PathVariable String id, @RequestBody User user) throws JSONException, ParseException { 
        boolean result = eventService.removeUser(id, user);
        return JsonResponse.removeUserJsonResponse(result).toString();
    }

    @DeleteMapping(path = "/delete/{id}")
    @ResponseBody
    public String deleteEvent(@PathVariable String id) throws JSONException {
        boolean result = eventService.delete(id);
        return JsonResponse.deleteJsonResponse(result).toString();
    }
}
