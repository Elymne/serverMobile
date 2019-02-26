package uit.nantes.serverMobile.infra.jpa;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uit.nantes.serverMobile.api.entities.Expense;
import uit.nantes.serverMobile.infra.jpa.pojo.ISpecialExpense;

/**
 * @author Daniel Clemente Aguirre
 * @author Djurdjevic Sacha
 */
public interface IExpenseRepository extends JpaRepository<Expense, String> {
    
    @Query(value="SELECT ex.user_id_user,sum(ex.amount) as total FROM expense ex GROUP BY ex.user_id_user ORDER BY ex.user_id_user", nativeQuery=true)
    List<ISpecialExpense> getAllMergeByUser();
    
    @Query(value="SELECT ex.user_id_user,sum(ex.amount) as total FROM expense ex WHERE ex.event_id_event = :idEvent GROUP BY ex.user_id_user ORDER BY ex.user_id_user", nativeQuery=true)
    List<ISpecialExpense> getAllMergeByUserFromEvent(@Param("idEvent") String idEvent);
}
