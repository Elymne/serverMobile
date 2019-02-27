package uit.nantes.serverMobile.domain.util;

import uit.nantes.serverMobile.api.pojo.ExpensePojo;

/**
 * @author Daniel Clemente Aguirre
 * @author Djurdjevic Sacha
 */
public class ExpenseCheck {

    /*
    *  @param expensePojo Pojo de la classe Expense
    *  @return true si les informations de la dépense sont valides, false dans le cas contraire.
    */
    public static boolean checkUpdate(ExpensePojo expensePojo) {
        boolean result = true;
        if (expensePojo.getAmount() < 0
                || expensePojo.getWording().isEmpty()) {
            result = false;
        }
        return result;
    }

    /*
    *  @param expensePojo Pojo de la classe Expense
    *  @return true si les informations de la dépense sont valides, false dans le cas contraire.
    */
    public static boolean checkInsert(ExpensePojo expensePojo) {
        boolean result = true;
        if (expensePojo.getAmount() < 0
                || expensePojo.getWording().isEmpty()) {
            result = false;
        }
        return result;
    }
}
