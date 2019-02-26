package uit.nantes.serverMobile.domain.util;

import java.util.ArrayList;
import java.util.List;
import uit.nantes.serverMobile.api.entities.Expense;
import uit.nantes.serverMobile.api.entities.Owing;

/**
 * @author Daniel Clemente Aguirre
 * @author Djurdjevic Sacha
 */
public class ExpenseManagement {
    
    public static double getAverageExpense(List<Expense> expenseList){
        double result = 0;
        for(Expense expense : expenseList){
            result += expense.getAmount();
        }
        return result/expenseList.size();
    }
    
    public static List<Owing> transformExpenseList(List<Expense> expenseList){
        Owing owing;
        List<Owing> result = new ArrayList<>();
        for(Expense expense : expenseList){
            owing = new Owing(expense.getUser().getId(), expense.getAmount());
            result.add(owing);
        }
        return result;
    }

    public static List<Owing> transformOwingList(List<Owing> listOwing, double averageExpense) {
        List<Owing> result = new ArrayList<>();
        for(Owing owing : listOwing){
            owing.setOwing(owing.getOwing() - averageExpense);
            owing.setId(owing.getId());
            result.add(owing);
        }
        return result;
        
    }

    public static double getMaxE(List<Owing> listOwing) {
        double result = 0;
        for(Owing owing : listOwing){
            if(owing.getOwing() > 0){
                result += owing.getOwing();
            }
        }
        return result;
    }

    public static List<Owing> makeOwingList(Owing owingUser, List<Owing> owingList, double maxE) {
        List<Owing> result = new ArrayList<>();
        Owing owingToAdd;
        owingList.remove(owingUser);
        boolean state = getState(owingUser);
        
        if(state){
            for(Owing owing : owingList){
                if(owing.getOwing() < 0){
                    owingToAdd = new Owing(owing.getId(), -sumOwing(owing.getOwing(), maxE, owingUser.getOwing()));
                    System.out.println(owingToAdd.toString());
                    result.add(owingToAdd);
                }
            }
        }else{
            for(Owing owing : owingList){
                if(owing.getOwing() > 0){
                    owingToAdd = new Owing(owing.getId(), sumOwing(owing.getOwing(), maxE, owingUser.getOwing()));
                    result.add(owingToAdd);
                }
            }
        }
        return result;
    }

    public static Owing getOwingUser(Expense expenseUser, List<Owing> owingList){
        Owing result = null;
        for(Owing owing : owingList){
            if(owing.getId().equals(expenseUser.getUser().getId())){
                result = owing;
                break;
            }
        }
        return result;
    }
    
    private static boolean getState(Owing owingUser){
        boolean result = true;
        if(owingUser.getOwing() < 0){
            result = false;
        }
        return result;
    }
    
    public static double sumOwing(double owing, double maxE, double owingUser){
        return (1/maxE * owing) * owingUser;
    }
    

}
