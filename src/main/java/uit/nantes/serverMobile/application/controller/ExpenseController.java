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
import uit.nantes.serverMobile.api.entities.Event;
import uit.nantes.serverMobile.api.entities.Expense;
import uit.nantes.serverMobile.api.entities.User;
import uit.nantes.serverMobile.application.controller.util.JsonResponse;
import uit.nantes.serverMobile.domain.EventService;
import uit.nantes.serverMobile.domain.ExpenseService;
import uit.nantes.serverMobile.domain.UserService;

/**
 * @author Djurdjevic Sacha
 */
@Controller
@RequestMapping("/api/depense")
public class ExpenseController {

    @Autowired
    ExpenseService expenseService;
    
    @Autowired
    UserService userService;
    
    @Autowired
    EventService eventService;

    @GetMapping(path = "/get/{id}")
    public @ResponseBody
    String getUserById(@PathVariable String id) throws JSONException {
        return JsonResponse.getJsonExpenseResponse(expenseService.findById(id)).toString();
    }

    @PutMapping(path = "/update/{id}")
    public @ResponseBody
    String update(@PathVariable String id, @RequestBody Expense expense) throws JSONException {
        Boolean result = expenseService.update(id, expense);

        return JsonResponse.updateJsonResponse(result).toString();
    }

    @PostMapping(path = "/add")
    public @ResponseBody
    String addUser(@RequestBody Expense expense) throws JSONException {
        expense.createId();
        User user = userService.findById(expense.getIdUser());
        Event event = eventService.findById(expense.getIdEvent());
        
        Boolean result = expenseService.insert(expense, user, event);

        return JsonResponse.insertJsonResponse(result).toString();
    }

    @DeleteMapping(path = "/delete/{id}")
    public @ResponseBody
    String deleteUser(@PathVariable String id) throws JSONException {
        boolean result = expenseService.deleteById(id);

        return JsonResponse.deleteJsonResponse(result).toString();
    }

}
