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
        Period period = new Period(start, end);
        LocalDate firstDayOfBudget = LocalDate.of(2019, 04, 01);
        if (firstDayOfBudget.isAfter(end)) {
            return 0;
        }
        return period.intervalDays();
    }

}
