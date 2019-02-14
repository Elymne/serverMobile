package uit.nantes.serverMobile.application.controller;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import uit.nantes.serverMobile.application.controller.util.JsonResponse;
import uit.nantes.serverMobile.domain.ExpenseService;

/**
 * @author Djurdjevic Sacha
 */
@Controller
@RequestMapping("/api/depense")
public class ExpenseController {
    
    @Autowired
    ExpenseService expenseService;
    
    @GetMapping(path = "/get/{id}")
    public @ResponseBody
    String getUserById(@PathVariable String id) throws JSONException {
        return JsonResponse.getJsonExpenseResponse(expenseService.findById(id)).toString();
    }
    
    
    
}
