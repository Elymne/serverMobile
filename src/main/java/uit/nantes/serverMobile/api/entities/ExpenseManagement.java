package uit.nantes.serverMobile.api.entities;

import java.util.List;

/**
 * @author Daniel Clemente Aguirre
 * @author Djurdjevic Sacha
 */
public class ExpenseManagement {

    private final Event event;
    private final double totalExpense;
    private final double averageExpense;

    public ExpenseManagement(Event event) {
        this.event = event;
        this.totalExpense = this.getTotalExpense(event.getExpenseList());
        this.averageExpense = this.getAverageExpense(this.getTotalExpense(event.getExpenseList()), event.getExpenseList());
    }

    private double getTotalExpense(List<Expense> expenseList) {
        double result = 0;
        result = expenseList.stream().map((expense) 
                -> expense.getAmount()).reduce(result, (accumulator, _item) -> accumulator + _item);
        return result;
    }

    private double getAverageExpense(Double totalExpense, List<Expense> expenseList) {
        double result = totalExpense / expenseList.size();
        return result;
    }

    private double getMaxExpenseByUser(String id) {
        double result = 0;
        result = this.event.getExpenseList().stream().filter((expense) 
                -> (expense.getUser().getId() == null ? id == null : expense.getUser().getId().equals(id))).map((expense) 
                        -> expense.getAmount()).reduce(result, (accumulator, _item) -> accumulator + _item);
        return result;
    }

    public double getOwing(String id) {
        double result = getMaxExpenseByUser(id) - this.averageExpense;
        return result;
    }

    public double getTotalExpense() {
        return totalExpense;
    }

    public double getAverageExpense() {
        return averageExpense;
    }
    
    

}
