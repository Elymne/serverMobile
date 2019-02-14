package uit.nantes.serverMobile.infra.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import uit.nantes.serverMobile.api.entities.Expense;

/**
 * @author DJurdjevic Sacha
 */
public interface IExpenseRepository extends JpaRepository<Expense, String> {
}
