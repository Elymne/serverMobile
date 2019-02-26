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
    
    @Query(value="", nativeQuery=true)
    List<Expense> getAllMergeByUser();
}
