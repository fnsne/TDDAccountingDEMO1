import java.time.LocalDate;

public class Accounting {
    private BudgetRepo repo;

    public Accounting(BudgetRepo repo) {

        this.repo = repo;
    }

    public double queryBudget(LocalDate start, LocalDate end) {
        if (repo.getAll().isEmpty()) {
            return 0;
        }
        return new Period(start, end).intervalDays();
    }

}
