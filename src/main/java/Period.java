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

    public double overlapDays(Period period) {
        if (period.getStart().isAfter(end) || period.getEnd().isBefore(start)) {
            return 0;
        }
        LocalDate overlapFirstDay = getStart();
        if (getStart().isBefore(period.getStart())) {
            overlapFirstDay = period.getStart();
        }

        LocalDate overlapLastDay = getEnd();
        if (getEnd().isAfter(period.getEnd())) {
            overlapLastDay = period.getEnd();
        }
        return new Period(overlapFirstDay, overlapLastDay).days();
    }
}
