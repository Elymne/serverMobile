package uit.nantes.serverMobile.api.entities;

import java.util.HashMap;
import java.util.List;

/*
 * @author Djurdjevic Sacha
 */
public class ExpenseManagement {
    
    double totalExpense;
    List<HashMap<User, Double>> owing;

    public ExpenseManagement(Event event) {
        this.totalExpense = this.getTotalExpense(event.getExpenseList());
    }
    
    private double getTotalExpense(List<Expense> expenseList){
        double result = 0;
        for(Expense expense : expenseList){
            result += expense.getAmount();
        }
        return result;
    }
    
    public double getOwing(String id){
        return 0;
    }
    
}
