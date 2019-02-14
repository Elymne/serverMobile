package uit.nantes.serverMobile.domain.util;

import uit.nantes.serverMobile.api.entities.Expense;

/**
 * @author Djurdjevic Sacha
 */
public class ExpenseCheck {
    
    public static boolean checkUpdate(Expense expense){
        boolean result = true;
        if(expense.getAmount() < 0
                || expense.getWording().isBlank()){
            result = false;
        }
        return result;
    }
    
    public static boolean checkInsert(Expense expense) {
        boolean result = true;
        if(expense.getAmount() < 0
                || expense.getWording().isBlank()){
            result = false;
        }
        return result;
    }
    
}
