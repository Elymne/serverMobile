package uit.nantes.serverMobile.domain;

import java.util.List;
import org.springframework.stereotype.Service;
import uit.nantes.serverMobile.domain.util.ExpenseManagement;
import uit.nantes.serverMobile.api.entities.Owing;
import uit.nantes.serverMobile.infra.jpa.pojo.ISpecialExpense;

/**
 * @author Daniel Clemente Aguirre
 * @author Djurdjevic Sacha
 */
@Service
public class ExpenseManagerService {

    public List<Owing> getDetailOwing(String idUser, List<ISpecialExpense> specialExpenseList) {
        List<Owing> result;
        List<Owing> listOwing;
        double averageExpense = ExpenseManagement.getAverageExpense(specialExpenseList);

        listOwing = ExpenseManagement.transformExpenseList(specialExpenseList);
        listOwing = ExpenseManagement.transformOwingList(listOwing, averageExpense);

        double maxE = ExpenseManagement.getMaxE(listOwing);
        Owing userOwing = ExpenseManagement.getOwingUser(idUser, listOwing);

        result = ExpenseManagement.makeOwingList(userOwing, listOwing, maxE);
        return result;
    }
    
    public Owing getOwing(String idUser, List<ISpecialExpense> specialExpenseList) {
        List<Owing> listOwing;
        double averageExpense = ExpenseManagement.getAverageExpense(specialExpenseList);

        listOwing = ExpenseManagement.transformExpenseList(specialExpenseList);
        listOwing = ExpenseManagement.transformOwingList(listOwing, averageExpense);

        Owing userOwing = ExpenseManagement.getOwingUser(idUser, listOwing);

        return userOwing;
    }
}
