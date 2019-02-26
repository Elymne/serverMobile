package uit.nantes.serverMobile.infra.jpa;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uit.nantes.serverMobile.api.entities.Expense;

/**
 * @author Daniel Clemente Aguirre
 * @author Djurdjevic Sacha
 */
public interface IExpenseRepository extends JpaRepository<Expense, String> {
    
    @Query(value="SELECT DISTINCT user_id_user,sum(amount) FROM expense ex GROUP BY user_id_user", nativeQuery=true)
    List<SpecialExpense> getAllMergeByUser();
    
    public static interface SpecialExpense {
        
        
    }
}
