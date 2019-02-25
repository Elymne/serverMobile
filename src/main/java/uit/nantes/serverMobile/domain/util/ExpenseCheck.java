package uit.nantes.serverMobile.domain.util;

import uit.nantes.serverMobile.api.pojo.ExpensePojo;

/**
 * @author Daniel Clemente Aguirre
 * @author Djurdjevic Sacha
 */
public class ExpenseCheck {

    public static boolean checkUpdate(ExpensePojo expensePojo) {
        boolean result = true;
        if (expensePojo.getAmount() < 0
                || expensePojo.getWording().isBlank()) {
            result = false;
        }
        return result;
    }

    public static boolean checkInsert(ExpensePojo expensePojo) {
        boolean result = true;
        if (expensePojo.getAmount() < 0
                || expensePojo.getWording().isBlank()) {
            result = false;
        }
        return result;
    }
}
