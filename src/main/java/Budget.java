import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class Budget {
    private final String yearMonth;
    private final double amount;

    public Budget(String yearMonth, int amount) {
        this.yearMonth = yearMonth;
        this.amount = amount;
    }

    public LocalDate firstDay() {
        YearMonth ym = getYearMonth();
        return ym.atDay(1);
    }

    public LocalDate lastDay() {
        return getYearMonth().atEndOfMonth();
    }

    public double days() {
        return getYearMonth().lengthOfMonth();
    }

    public double getDailyAmount() {
        return amount / days();
    }

    public Period createPeriod() {
        return new Period(firstDay(), lastDay());
    }

    public double getOverlapAmount(Period period) {
        double overlapDays = period.overlapDays(createPeriod());
        return getDailyAmount() * overlapDays;
    }

    private YearMonth getYearMonth() {
        return YearMonth.parse(yearMonth, DateTimeFormatter.ofPattern("yyyyMM"));
    }
}
