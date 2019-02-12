package uit.nantes.serverMobile.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import uit.nantes.serverMobile.api.entities.User;
import uit.nantes.serverMobile.domain.UserService;

/**
 * @author Djurdjevic Sacha
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(path = "/get/{id}")
    public @ResponseBody
    User getUserById(@PathVariable String id) {
        return userService.findById(id);
    }

    @PostMapping(path = "/add")
    public @ResponseBody
    void addUser() {
        userService.insert(new User("a1", "Elymne", "", ""));
    }

}
