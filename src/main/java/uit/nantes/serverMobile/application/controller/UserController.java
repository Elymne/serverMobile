package uit.nantes.serverMobile.application.controller;

import java.util.Map;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping(path = "/get/{id}")
    public @ResponseBody
    String getUserById(@PathVariable String id) throws JSONException {
        return ToJson.userToJSON(userService.findById(id)).toString();
    }

    @GetMapping(path = "/get/email/{id}")
    public @ResponseBody
    String getUserByEmail(@PathVariable String id) throws JSONException {
        return ToJson.userToJSON(userService.findByEmail(id)).toString();
    }

    @GetMapping(path = "/get/pseudo/{id}")
    public @ResponseBody
    String getUserByPseudo(@PathVariable String id) throws JSONException {
        return ToJson.userToJSON(userService.findByPseudo(id)).toString();
    }

    @PutMapping(path = "/update/{id}")
    public @ResponseBody
    void updatePasswordById(@PathVariable String id, @RequestBody User user) {
        
    }

    @PostMapping(path = "/add")
    public @ResponseBody
    String addUser(@RequestBody User user) {
        User newuser = new User(user.getPseudo(), user.getEmail(), user.getPassword());
        userService.insert(newuser);
        return "mdr";
    }

}
