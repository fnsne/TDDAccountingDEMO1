import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class Budget {
    private final String yearMonth;
    private final int amount;

    public Budget(String yearMonth, int amount) {

        this.yearMonth = yearMonth;
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
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

    private YearMonth getYearMonth() {
        return YearMonth.parse(yearMonth, DateTimeFormatter.ofPattern("yyyyMM"));
    }
}
