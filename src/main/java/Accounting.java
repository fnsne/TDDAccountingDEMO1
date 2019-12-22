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
        return getOverlapAmount(budget, new Period(start, end));
    }

    private double getOverlapAmount(Budget budget, Period period) {
        double overlapDays = period.overlapDays(budget.createPeriod());
        return budget.getDailyAmount() * overlapDays;
    }

}
