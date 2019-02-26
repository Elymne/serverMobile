package uit.nantes.serverMobile.domain.util;

import java.util.ArrayList;
import java.util.List;
import uit.nantes.serverMobile.api.entities.Owing;
import uit.nantes.serverMobile.infra.jpa.pojo.ISpecialExpense;

/**
 * @author Daniel Clemente Aguirre
 * @author Djurdjevic Sacha
 */
public class ExpenseManagement {
    
    public static double getAverageExpense(List<ISpecialExpense> specialExpenseList){
        double result = 0;
        for(ISpecialExpense specialExpense : specialExpenseList){
            result += specialExpense.getTotal();
        }
        return result/specialExpenseList.size();
    }
    
    public static List<Owing> transformExpenseList(List<ISpecialExpense> specialExpenseList){
        Owing owing;
        List<Owing> result = new ArrayList<>();
        for(ISpecialExpense specialExpense : specialExpenseList){
            owing = new Owing(specialExpense.getUser_id_user(), specialExpense.getTotal());
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

    public static Owing getOwingUser(String idUser, List<Owing> owingList){
        Owing result = null;
        for(Owing owing : owingList){
            if(owing.getId().equals(idUser)){
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
