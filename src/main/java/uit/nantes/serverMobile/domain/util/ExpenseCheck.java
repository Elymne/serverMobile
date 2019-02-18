package uit.nantes.serverMobile.domain.util;

import uit.nantes.serverMobile.api.entities.Event;
import uit.nantes.serverMobile.api.entities.Expense;
import uit.nantes.serverMobile.api.entities.User;

/**
 * @author Djurdjevic Sacha
 */
public class ExpenseCheck {

    public static boolean checkUpdate(Expense expense) {
        boolean result = true;
        if (expense.getAmount() < 0
                || expense.getWording().isBlank()) {
            result = false;
        }
        return result;
    }

    public static boolean checkInsert(Expense expense) {
        boolean result = true;
        if (expense.getAmount() < 0
                || expense.getWording().isBlank()
                || check(expense.getUser(),expense.getEvent())) {
            result = false;
        }
        return result;
    }

    private static boolean check(User user, Event event) {
        boolean result = false;
        for (User unUser : event.getUserList()) {
            if (unUser.getId().equals(user.getId())) {
                result = true;
            }
        }
        return result;
    }

}
