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
        if (budget.firstDay().isAfter(end) || budget.lastDay().isBefore(start)) {
            return 0;
        }
        LocalDate overlapFirstDay;
        if (start.isBefore(budget.firstDay())) overlapFirstDay = budget.firstDay();
        else overlapFirstDay = start;
        Period period = new Period(overlapFirstDay, end);
        return period.days();
    }

}
