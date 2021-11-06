package examples.dateandtime;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

public class ShowTemporalAdjusters {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(ShowTemporalAdjusters.class);

  public static void main(String[] args) {
    LocalDate today = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    LocalDate payday = today.with(TemporalAdjusters.lastDayOfMonth()).minusDays(2);
    System.out.println("Payday:" + formatter.format(payday));

    LocalDate nextWed = today.with(TemporalAdjusters.next(DayOfWeek.WEDNESDAY));
    System.out.println("nächster Mittwoch:" + formatter.format(nextWed));
  }
}
