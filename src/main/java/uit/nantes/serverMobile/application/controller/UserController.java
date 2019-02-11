package uit.nantes.serverMobile.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import uit.nantes.serverMobile.api.entities.User;
import uit.nantes.serverMobile.domain.UserService;

/**
 * @author Djurdjevic Sacha
 */
@RestController()
@CrossOrigin
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(path = "/user/{id}")
    public @ResponseBody
    User findUserById(@PathVariable String id) {
        return userService.findById(id);
    }

}
