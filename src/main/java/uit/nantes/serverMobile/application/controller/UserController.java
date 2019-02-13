package uit.nantes.serverMobile.application.controller;

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
import uit.nantes.serverMobile.application.controller.util.JsonResponse;
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
        return JsonResponse.getJsonUserResponse(userService.findById(id)).toString();
    }

    @GetMapping(path = "/get/email/{id}")
    public @ResponseBody
    String getUserByEmail(@PathVariable String id) throws JSONException {
        return JsonResponse.getJsonUserResponse(userService.findByEmail(id)).toString();
    }

    @GetMapping(path = "/get/pseudo/{id}")
    public @ResponseBody
    String getUserByPseudo(@PathVariable String id) throws JSONException {
        return JsonResponse.getJsonUserResponse(userService.findByPseudo(id)).toString();
    }

    @PutMapping(path = "/update/{id}")
    public @ResponseBody
    String update(@PathVariable String id, @RequestBody User user) throws JSONException {
        Boolean result = userService.update(id, user);
        
        return JsonResponse.updateJsonResponse(result).toString();
    }

    @PostMapping(path = "/add")
    public @ResponseBody
    String addUser(@RequestBody User user) throws JSONException {
        user.createId();
        Boolean result = userService.insert(user);
        
        return JsonResponse.insertJsonResponse(result).toString();
    }
    
    @DeleteMapping(path = "/delete/{id}")
    public @ResponseBody
    String deleteUser(@PathVariable String id) throws JSONException{
        boolean result = userService.deleteById(id);
        
        return JsonResponse.deleteJsonResponse(result).toString();
    }

}
