package uit.nantes.serverMobile.domain;

import java.util.List;
import org.springframework.stereotype.Service;
import uit.nantes.serverMobile.api.entities.Expense;
import uit.nantes.serverMobile.api.entities.ExpenseManagement;
import uit.nantes.serverMobile.api.entities.Owing;

/**
 * @author Daniel Clemente Aguirre
 * @author Djurdjevic Sacha
 */
@Service
public class ExpenseManagerService {

    public List<Owing> getOwing(Expense expenseUser, List<Expense> expenseList) {
        List<Owing> result;
        List<Owing> listOwing;
        double averageExpense = ExpenseManagement.getAverageExpense(expenseList);

        listOwing = ExpenseManagement.transformExpenseList(expenseList);
        listOwing = ExpenseManagement.transformOwingList(listOwing, averageExpense);

        double maxE = ExpenseManagement.getMaxE(listOwing);
        Owing userOwing = ExpenseManagement.getOwingUser(expenseUser, listOwing);

        result = ExpenseManagement.makeOwingList(userOwing, listOwing, maxE);
        return result;
    }

}
