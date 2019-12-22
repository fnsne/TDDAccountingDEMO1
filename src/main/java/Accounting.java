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
        Period period = new Period(start, end);
        if (budgets.get(0).firstDay().isAfter(end)) {
            return 0;
        }
        return period.intervalDays();
    }

}
