import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

public class Accounting {
    private BudgetRepo repo;

    public Accounting(BudgetRepo repo) {

        this.repo = repo;
    }

    public double queryBudget(LocalDate start, LocalDate end) {
        if (repo.getAll().isEmpty()) {
            return 0;
        }
        return intervalDays(start, end);
    }

    private double intervalDays(LocalDate start, LocalDate end) {
        return DAYS.between(start, end) + 1;
    }

}
