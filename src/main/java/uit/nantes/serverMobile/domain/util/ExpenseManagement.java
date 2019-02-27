package uit.nantes.serverMobile.domain.util;

import java.util.ArrayList;
import java.util.List;
import uit.nantes.serverMobile.api.entities.Event;
import uit.nantes.serverMobile.api.entities.Expense;
import uit.nantes.serverMobile.api.entities.Owing;
import uit.nantes.serverMobile.api.entities.User;
import uit.nantes.serverMobile.infra.jpa.pojo.ISpecialExpense;

/**
 * @author Daniel Clemente Aguirre
 * @author Djurdjevic Sacha
 */
public class ExpenseManagement {

    /*
    * @param List<ISpecialExpense> specialExpenseList Une liste spécialisé contenant qu'un montant global d'un utilisateur, son id et 
    * @return Retourne la moyenne des montants contenu dans la liste donnée en paramètre
     */
    public static double getAverageExpense(List<ISpecialExpense> specialExpenseList) {
        double result = 0;
        for (ISpecialExpense specialExpense : specialExpenseList) {
            result += specialExpense.getTotal();
        }
        return result / specialExpenseList.size();
    }

    /*
    * @param List<ISpecialExpense> specialExpenseList Une liste spécialisé contenant un montant global d'un utilisateur, son id, et l'évènement
    * @param Double averageExpense La moyenne des dépenses d'un évènement
    * @return Une liste composé des dûs de chaque utilisateur dans la liste rentré en paramètre
     */
    public static List<Owing> transformOwingList(List<ISpecialExpense> specialExpenseList, double averageExpense) {
        Owing owing = null;
        List<Owing> result = new ArrayList<>();
        for (ISpecialExpense specialExpense : specialExpenseList) {
            owing = new Owing(specialExpense.getUser_id_user(),
                    specialExpense.getTotal() - averageExpense);
            result.add(owing);
        }
        return result;

    }

    /*
    * @param List<Owing> owingList La liste des dûs de chaque utilisateur
    * @return La quantité d'argent max qu'il reste à répartir enter chaque utilisateur
    */
    public static double getMaxE(List<Owing> owingList) {
        double result = 0;
        for (Owing owing : owingList) {
            if (owing.getOwing() > 0) {
                result += owing.getOwing();
            }
        }
        return result;
    }

    /*
    * @param Owing owingUser Le dû d'un utilisateur
    * @param List<Owing> owingList La liste des dûs de tous les utilisateurs d'un évènement
    * @param double maxE La quantité d'argent maximum à équilibrer entre tous les utilisateurs
    * @return Une liste de dûs transformé pour afficher ce qu'un utilisateur doit en somme d'argent à d'autre utilisateur
    * Si les données sont négative
    */
    public static List<Owing> makeOwingList(Owing owingUser, List<Owing> owingList, double maxE) {
        List<Owing> result = new ArrayList<>();
        Owing owingToAdd;
        owingList.remove(owingUser);
        boolean state = getState(owingUser);

        if (state) {
            for (Owing owing : owingList) {
                if (owing.getOwing() < 0) {
                    owingToAdd = new Owing(owing.getId(), -sumOwing(owing.getOwing(), maxE, owingUser.getOwing()));
                    System.out.println(owingToAdd.toString());
                    result.add(owingToAdd);
                }
            }
        } else {
            for (Owing owing : owingList) {
                if (owing.getOwing() > 0) {
                    owingToAdd = new Owing(owing.getId(), sumOwing(owing.getOwing(), maxE, owingUser.getOwing()));
                    result.add(owingToAdd);
                }
            }
        }
        return result;
    }

    /*
    * @param String idUser identifiant d'un utilisateur
    * @param List<Owing> owingList Une liste de dûs
    * @return Le dû d'un utilisateur dont l'identifiant est celui correspondant à la chaîne de caractère rentré en paramètre
    */
    public static Owing getOwingUser(String idUser, List<Owing> owingList) {
        Owing result = null;
        for (Owing owing : owingList) {
            if (owing.getId().equals(idUser)) {
                result = owing;
                break;
            }
        }
        return result;
    }

    /*
    * @param Owing owingUser Un dû d'un utilisateur
    * return Un boolean égale à vrai si l'utilisateur ne doit pas d'argent et faux si il en doit
    */
    private static boolean getState(Owing owingUser) {
        boolean result = true;
        if (owingUser.getOwing() < 0) {
            result = false;
        }
        return result;
    }

    /*
    * @param double owing Une quantité d'argent dépensé par un utilisateur
    * @param double maxE Le total d'argent à équilibrer entre chaque utilisateur
    * @param double owingUser Une quantité d'argent dépense par un second utilisateur
    * @return Une quantité d'argent que doit l'utilisateur rentré en troisème paramètre à un autre utilisateur rentré en premier paramètre
    */
    public static double sumOwing(double owing, double maxE, double owingUser) {
        return (1 / maxE * owing) * owingUser;
    }

    /*
    * @param User user Un utilisateur
    * @param Event event Un evenement
    * @return Une dépense de base à 0 lié à l'evenement et l'utilisateur rentré en paramètre
    */
    public static Expense createExpenseByCreating(User user, Event event) {
        Expense expense = new Expense();
        expense.createId();
        expense.setAmount(0);
        expense.setWording("none");
        expense.setUser(user);
        expense.setEvent(event);
        return expense;
    }

}
