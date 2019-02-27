package uit.nantes.serverMobile.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uit.nantes.serverMobile.api.entities.Expense;
import uit.nantes.serverMobile.api.pojo.ExpensePojo;
import uit.nantes.serverMobile.domain.util.ExpenseCheck;
import uit.nantes.serverMobile.infra.jpa.IEventRepository;
import uit.nantes.serverMobile.infra.jpa.IExpenseRepository;
import uit.nantes.serverMobile.infra.jpa.IUserRepository;
import uit.nantes.serverMobile.infra.jpa.pojo.ISpecialExpense;

/**
 * @author Daniel Clemente Aguirre
 * @author Djurdjevic Sacha
 */
@Service
public class ExpenseService {

	@Autowired
	IExpenseRepository expenseRepository;

	@Autowired
	IUserRepository userRepository;

	@Autowired
	IEventRepository eventRepository;

	public List<Expense> findAll() {
		List<Expense> expenseList = new ArrayList<>();
		expenseRepository.findAll().stream().filter((expense) -> (!expense.getWording().equals("none")))
				.forEachOrdered((expense) -> {
					expenseList.add(expense);
				});
		return expenseList;
	}

	public List<ISpecialExpense> findAllGroupByUser() {
		return expenseRepository.getAllMergeByUser();
	}

	public List<ISpecialExpense> findAllGroupByUserByEvent(String idEvent) {
		return expenseRepository.getAllMergeByUserFromEvent(idEvent);
	}

	public Expense findById(String id) {
		Expense result = new Expense();
		if (expenseRepository.existsById(id)) {
			result = expenseRepository.findById(id).get();
		}
		return result;
	}

	public List<Expense> findAllByEvent(String id) {
		List<Expense> result = new ArrayList<>();
		for (Expense expense : expenseRepository.findAll()) {
			if (expense.getEvent().getId().equals(id) && !expense.getWording().equals("none")) {
				result.add(expense);
			}
		}
		if (result.isEmpty()) {
			result = null;
		}
		return result;
	}

	public List<Expense> findAllByUser(String id) {
		List<Expense> result = new ArrayList<>();
		for (Expense expense : expenseRepository.findAll()) {
			if (expense.getUser().getId().equals(id) && !expense.getWording().equals("none")) {
				result.add(expense);
			}
		}
		if (result.isEmpty()) {
			result = null;
		}
		return result;
	}

	public List<Expense> findAllByUserAndEvent(String idUser, String idEvent) {
		List<Expense> result = new ArrayList<>();
		for (Expense expense : expenseRepository.findAll()) {
			if (expense.getUser().getId().equals(idUser) && expense.getEvent().getId().equals(idEvent)
					&& !expense.getWording().equals("none")) {
				result.add(expense);
			}
		}
		return result;
	}

	public boolean insert(ExpensePojo expensePojo) {
		boolean result = false;
		if (ExpenseCheck.checkInsert(expensePojo) && userRepository.existsById(expensePojo.getUserId())
				&& eventRepository.existsById(expensePojo.getEventId())) {
			Expense expense = new Expense();
			expense.createId();
			expense.setAmount(expensePojo.getAmount());
			expense.setWording(expensePojo.getWording());
			expense.setUser(userRepository.findById(expensePojo.getUserId()).get());
			expense.setEvent(eventRepository.findById(expensePojo.getEventId()).get());
			expenseRepository.save(expense);
			result = true;
		}
		return result;
	}

	public boolean update(String id, ExpensePojo expensePojo) {
		boolean result = true;
		if (expenseRepository.existsById(id) && userRepository.existsById(expensePojo.getUserId())
				&& eventRepository.existsById(expensePojo.getEventId())) {
			if (ExpenseCheck.checkUpdate(expensePojo)) {
				Expense expense = expenseRepository.findById(id).get();
				expense.setAmount(expense.getAmount());
				expense.setWording(expense.getWording());
				expense.setUser(userRepository.findById(expensePojo.getUserId()).get());
				expense.setEvent(eventRepository.findById(expensePojo.getEventId()).get());
				expenseRepository.save(expense);
			} else {
				result = false;
			}
		}
		return result;
	}

	public boolean deleteById(String id) {
		boolean result = false;
		if (expenseRepository.existsById(id)) {
			expenseRepository.deleteById(id);
			result = true;
		}
		return result;
	}

	public double getTotal(String id) {
		double total = 0;
		List<Expense> expenses = this.findAllByEvent(id);
		List<Double> amounts = expenses != null ? expenses.stream().map(Expense::getAmount).collect(Collectors.toList())
				: new ArrayList<Double>();
		total = amounts.size() != 0 ? amounts.stream().mapToDouble(Double::doubleValue).sum() : 0;
		return total;
	}
}