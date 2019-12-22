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
        double overlapDays = new Period(start, end).overlapDays(createPeriod(budget));
        return budget.getDailyAmount() * overlapDays;
    }

    private Period createPeriod(Budget budget) {
        return new Period(budget.firstDay(), budget.lastDay());
    }

}
