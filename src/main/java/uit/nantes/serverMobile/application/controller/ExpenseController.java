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
import uit.nantes.serverMobile.api.pojo.ExpensePojo;
import uit.nantes.serverMobile.application.controller.util.JsonResponse;
import uit.nantes.serverMobile.domain.EventService;
import uit.nantes.serverMobile.domain.ExpenseService;
import uit.nantes.serverMobile.domain.UserService;
import uit.nantes.serverMobile.infra.jpa.pojo.ISpecialExpense;

/**
 * @author Daniel Clemente Aguirre
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

    @GetMapping(path = "/getAll")
    public @ResponseBody
    List<Expense> getAll() {
        return expenseService.findAll();
    }

    @GetMapping(path = "/getAllGroupByUser")
    public @ResponseBody
    List<ISpecialExpense> getAllGroupByUser() {
        return expenseService.findAllGroupByUser();
    }

    @GetMapping(path = "/getAllGroupByUserFromEvent/{idEvent}")
    public @ResponseBody
    List<ISpecialExpense> getAllGroupByUserFromEvent(@PathVariable String idEvent) {
        return expenseService.findAllGroupByUserByEvent(idEvent);
    }
    
    @GetMapping(path = "/getAllGroupByEvent/{idEvent}")
    public @ResponseBody
    List<ISpecialExpense> getAllGroupByEvent(@PathVariable String idEvent) {
        return expenseService.findAllGroupByEvent(idEvent);
    }

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
        return expenseService.findAllByEvent(id);
    }

    @GetMapping(path = "/get/eventuser/{idUser}/{idEvent}")
    public @ResponseBody
    List<Expense> getAllExpenseByUserAndEvent(@PathVariable String idUser, @PathVariable String idEvent) throws JSONException {
        return expenseService.findAllByUserAndEvent(idUser, idEvent);
    }

    @PostMapping(path = "/add")
    public @ResponseBody
    String addUser(@RequestBody ExpensePojo expensePojo) throws JSONException {
        Boolean result = expenseService.insert(expensePojo);
        return JsonResponse.addExpenseJsonResponse(result).toString();
    }

    @PutMapping(path = "/update/{id}")
    public @ResponseBody
    String update(@PathVariable String id, @RequestBody ExpensePojo expensePojo) throws JSONException {
        Boolean result = expenseService.update(id, expensePojo);
        return JsonResponse.updateJsonResponse(result).toString();
    }

    @DeleteMapping(path = "/delete/{id}")
    public @ResponseBody
    String deleteUser(@PathVariable String id) throws JSONException {
        boolean result = expenseService.deleteById(id);
        return JsonResponse.removeExpenseJsonResponse(result).toString();
    }

}
