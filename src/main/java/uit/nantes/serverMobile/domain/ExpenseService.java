package uit.nantes.serverMobile.domain;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import uit.nantes.serverMobile.api.entities.Event;
import uit.nantes.serverMobile.api.entities.Expense;
import uit.nantes.serverMobile.api.entities.User;
import uit.nantes.serverMobile.domain.util.ExpenseCheck;
import uit.nantes.serverMobile.infra.jpa.IExpenseRepository;

/**
 * @author Djurdjevic Sacha
 */
public class ExpenseService {

    @Autowired
    IExpenseRepository expenseRepository;

    public List<Expense> findAll() {
        List<Expense> expenseList = new ArrayList<Expense>();
        expenseRepository.findAll().forEach(expenseList::add);
        return expenseList;
    }

    public Expense findById(String id) {
        Expense result = new Expense();
        result.notExist();
        if (expenseRepository.existsById(id)) {
            result = expenseRepository.findById(id).get();
            result.exist();
        }
        return result;
    }

    public List<Expense> findAllByEventId(String id) {
        List<Expense> result = new ArrayList<>();
        for (Expense expense : expenseRepository.findAll()) {
            if (expense.getEvent().getId().equals(id)) {
                result.add(expense);
            }
        }
        if (result.isEmpty()) {
            result = null;
        }
        return result;
    }

    public List<Expense> findAllByUser(String id) {
        List<Expense> result = new ArrayList<>();
        for (Expense expense : expenseRepository.findAll()) {
            if (expense.getUser().getId().equals(id)) {
                result.add(expense);
            }
        }
        if (result.isEmpty()) {
            result = null;
        }
        return result;
    }
    
    public List<Expense> findAllByUserAndEvent(String idUser, String idEvent) {
        List<Expense> result = new ArrayList<>();
        for (Expense expense : expenseRepository.findAll()) {
            if (expense.getUser().getId().equals(idUser)
                    && expense.getIdEvent().equals(idEvent)) {
                result.add(expense);
            }
        }
        if (result.isEmpty()) {
            result = null;
        }
        return result;
    }

    public boolean update(String id, Expense expense) {
        boolean result = true;
        if (expenseRepository.existsById(id)) {
            Expense expenseUpdate = expenseRepository.findById(id).get();
            if (ExpenseCheck.checkUpdate(expense)) {
                expenseUpdate.setAmount(expense.getAmount());
                expenseUpdate.setWording(expense.getWording());
                expenseRepository.save(expenseUpdate);
            } else {
                result = false;
            }
        }
        return result;
    }

    public boolean insert(Expense expense) {
        boolean result = false;
        if(ExpenseCheck.checkInsert(expense)){
            expenseRepository.save(expense);
            result = true;
        }
        return result;
    }

    public boolean deleteById(String id) {
        boolean result = false;
        if (expenseRepository.existsById(id)) {
            expenseRepository.deleteById(id);
            result = true;
        }
        return result;
    }

}
