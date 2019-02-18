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
import uit.nantes.serverMobile.api.entities.Expense;
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
    Expense getExpenseById(@PathVariable String id) throws JSONException {
        return expenseService.findById(id);
    }
    
    @GetMapping(path = "/get/user/{id}")
    public @ResponseBody
    List<Expense> getAllExpenseByUser(@PathVariable String id) throws JSONException {
        return expenseService.findAllByUser(id);
    }
    
    @GetMapping(path = "/get/event/{id}")
    public @ResponseBody
    List<Expense> getAllExpenseByEvent(@PathVariable String id) throws JSONException {
        return expenseService.findAllByEventId(id);
    }
    
    @GetMapping(path = "/get/eventuser/{idUser}/{idEvent}")
    public @ResponseBody
    List<Expense> getAllExpenseByUserAndEvent(@PathVariable String idUser, @PathVariable String idEvent) throws JSONException {
        return expenseService.findAllByUserAndEvent(idUser, idEvent);
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
        Boolean result = expenseService.insert(expense);

        return JsonResponse.insertJsonResponse(result).toString();
    }

    @DeleteMapping(path = "/delete/{id}")
    public @ResponseBody
    String deleteUser(@PathVariable String id) throws JSONException {
        boolean result = expenseService.deleteById(id);

        return JsonResponse.deleteJsonResponse(result).toString();
    }

}
