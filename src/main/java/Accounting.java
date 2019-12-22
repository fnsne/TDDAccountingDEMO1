import javax.swing.*;
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
        double totalAmount = 0;
        Period period = new Period(start, end);
        for (Budget budget : budgets) {
            totalAmount += budget.getOverlapAmount(period);
        }
        return totalAmount;
    }

}
