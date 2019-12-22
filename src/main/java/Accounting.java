import java.time.LocalDate;
import java.util.List;

public class Accounting {
    private BudgetRepo repo;

    public Accounting(BudgetRepo repo) {

        this.repo = repo;
    }

    public double queryBudget(LocalDate start, LocalDate end) {
        List<Budget> budgets = repo.getAll();
        if (budgets.isEmpty()) {
            return 0;
        }
        Budget budget = budgets.get(0);
        double dailyAmount = budget.getAmount()/budget.days();
        return dailyAmount * new Period(start, end).overlapDays(budget);
    }

}
