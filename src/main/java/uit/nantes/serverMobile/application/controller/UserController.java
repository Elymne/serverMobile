package uit.nantes.serverMobile.application.controller;

import java.util.List;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import uit.nantes.serverMobile.api.entities.User;
import uit.nantes.serverMobile.api.pojo.UserPojo;
import uit.nantes.serverMobile.application.controller.util.JsonResponse;
import uit.nantes.serverMobile.domain.UserService;

/**
 * @author Daniel Clemente Aguirre
 * @author Djurdjevic Sacha
 */
@Controller
@RequestMapping("/api/membre")
public class UserController {

    @Autowired
    UserService userService;
    
    @GetMapping(path = "/getAll")
    public @ResponseBody
    List<User> getAll() throws JSONException {
        return userService.findAll();
    }

    @GetMapping(path = "/get/{id}")
    public @ResponseBody
    User getUserById(@PathVariable String id) throws JSONException {
        return userService.findById(id);
    }

    @GetMapping(path = "/get/email/{email}")
    public @ResponseBody
    User getUserByEmail(@PathVariable String email) throws JSONException {
        return userService.findByEmail(email);
    }

    @GetMapping(path = "/get/pseudo/{pseudo}")
    public @ResponseBody
    User getUserByPseudo(@PathVariable String pseudo) throws JSONException {
        return userService.findByPseudo(pseudo);
    }

    @PutMapping(path = "/update/{id}")
    public @ResponseBody
    String update(@PathVariable String id, @RequestBody UserPojo userPojo) throws JSONException {
        Boolean result = userService.update(id, userPojo);  
        return JsonResponse.updateJsonResponse(result).toString();
    }

    @PostMapping(path = "/add")
    public @ResponseBody
    String addUser(@RequestBody UserPojo userPojo) throws JSONException {
        Boolean result = userService.insert(userPojo);
        return JsonResponse.insertJsonResponse(result).toString();
    }
    
    @DeleteMapping(path = "/delete/{id}")
    public @ResponseBody
    String deleteUser(@PathVariable String id) throws JSONException{
        boolean result = userService.delete(id); 
        return JsonResponse.deleteJsonResponse(result).toString();
    }

}
