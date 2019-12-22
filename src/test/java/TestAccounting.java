import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class TestAccounting {

    private BudgetRepo repo = mock(BudgetRepo.class);
    private Accounting accounting = new Accounting(repo);

    @Test
    public void no_budget() {
        budgetShouldBe(0.0,
                LocalDate.of(2019, 04, 01),
                LocalDate.of(2019, 04, 01)
        );
    }

    @Test
    public void period_inside_budget_month() {
        when(repo.getAll()).thenReturn(Arrays.asList(
                new Budget("201904", 1)));
        budgetShouldBe(1.0,
                LocalDate.of(2019, 04, 01),
                LocalDate.of(2019, 04, 01)
        );
    }

    @Test
    public void period_no_overlap_before_first_day() {
        when(repo.getAll()).thenReturn(Arrays.asList(
                new Budget("201904", 1)));
        budgetShouldBe(0.0,
                LocalDate.of(2019, 03, 01),
                LocalDate.of(2019, 03, 01)
        );
    }

    @Test
    public void period_no_overlap_after_first_day() {
        when(repo.getAll()).thenReturn(Arrays.asList(
                new Budget("201904", 1)));
        budgetShouldBe(0.0,
                LocalDate.of(2019, 05, 01),
                LocalDate.of(2019, 05, 01)
        );
    }

    @Test
    public void period_overlap_budget_first_day() {
        when(repo.getAll()).thenReturn(Arrays.asList(
                new Budget("201904", 30)));
        budgetShouldBe(1.0,
                LocalDate.of(2019, 03, 31),
                LocalDate.of(2019, 04, 01)
        );
    }

    @Test
    public void period_overlap_budget_last_day() {
        when(repo.getAll()).thenReturn(Arrays.asList(
                new Budget("201904", 30)));
        budgetShouldBe(1.0,
                LocalDate.of(2019, 04, 30),
                LocalDate.of(2019, 05, 01)
        );
    }

    private void budgetShouldBe(double expected, LocalDate start, LocalDate end) {
        assertEquals(expected,
                accounting.queryBudget(start, end)
        );
    }
}
