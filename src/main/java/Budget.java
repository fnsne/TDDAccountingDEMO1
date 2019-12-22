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

    public double getOverlapAmount(Period period) {
        double overlapDays = period.overlapDays(createPeriod());
        return getDailyAmount() * overlapDays;
    }

    private double days() {
        return getYearMonth().lengthOfMonth();
    }

    private double getDailyAmount() {
        return amount / days();
    }

    private Period createPeriod() {
        return new Period(firstDay(), lastDay());
    }

    private LocalDate firstDay() {
        YearMonth ym = getYearMonth();
        return ym.atDay(1);
    }

    private LocalDate lastDay() {
        return getYearMonth().atEndOfMonth();
    }

    private YearMonth getYearMonth() {
        return YearMonth.parse(yearMonth, DateTimeFormatter.ofPattern("yyyyMM"));
    }
}
