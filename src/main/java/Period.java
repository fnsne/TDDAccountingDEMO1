import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

public class Period {
    private final LocalDate start;
    private final LocalDate end;

    public Period(LocalDate start, LocalDate end) {

        this.start = start;
        this.end = end;
    }

    public double days() {
        return DAYS.between(start, end) + 1;
    }

    public LocalDate getEnd() {
        return end;
    }

    public LocalDate getStart() {
        return start;
    }

    public double overlapDays(Budget budget) {
        LocalDate overlapFirstDay = getStart();
        if (getStart().isBefore(budget.firstDay())) {
            overlapFirstDay = budget.firstDay();
        }

        LocalDate overlapLastDay = getEnd();
        if (getEnd().isAfter(budget.lastDay())) {
            overlapLastDay = budget.lastDay();
        }
        return new Period(overlapFirstDay, overlapLastDay).days();
    }
}
