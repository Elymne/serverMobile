package uit.nantes.serverMobile.application.controller;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import uit.nantes.serverMobile.api.entities.User;
import uit.nantes.serverMobile.application.controller.util.ToJson;
import uit.nantes.serverMobile.domain.UserService;

/**
 * @author Djurdjevic Sacha
 */
@Controller
@RequestMapping("/api/membre")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(path = "/{id}")
    public @ResponseBody
    String getUserById(@PathVariable String id) throws JSONException {
        return ToJson.userToJSON(userService.findById(id)).toString();
    }
    
    @GetMapping(path = "/get/email/{id}")
    public @ResponseBody
    User getUserByEmail(@PathVariable String id) {
        return userService.findByEmail(id);
    }
    
    @GetMapping(path = "/get/pseudo/{id}")
    public @ResponseBody
    User getUserByPseudo(@PathVariable String id) {
        return userService.findByPseudo(id);
    }
    
    @PutMapping(path = "/update/password/{id}/{password}")
    public @ResponseBody
    void updatePasswordById(@PathVariable("id") String id, @PathVariable("password") String password){
        userService.updatePassword(id, password);
    }
    
    @PutMapping(path = "/update/email/{id}")
    public @ResponseBody
    void updateEmailById(@PathVariable String id, @PathVariable String password){
        userService.updateEmail(id, password);
    }
    
    @PostMapping(path = "/{pseudo}/{password}/{email}")
    public @ResponseBody
    void addUser(@PathVariable("pseudo") String pseudo, @PathVariable("password") String password, @PathVariable("email") String email) {
        userService.insert(new User(pseudo, password, email));
    }

}
