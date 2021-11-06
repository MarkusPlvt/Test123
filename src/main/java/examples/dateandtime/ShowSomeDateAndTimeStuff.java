package examples.dateandtime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

public class ShowSomeDateAndTimeStuff {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(ShowSomeDateAndTimeStuff.class);

  public static void main(String[] args) {
    LocalDate xmasDate = LocalDate.of(2020, Month.DECEMBER, 24);
    LocalTime evening = LocalTime.of(20, 0);
    LocalDateTime xmas = LocalDateTime.of(xmasDate, evening);

    DateTimeFormatter ownFormatter = DateTimeFormatter.ofPattern("EEEE dd.MM.yyyy 'um' HH:mm 'Uhr' ");
    System.out.println("Weihnachtsabend - eigenes Pattern: " + ownFormatter.format(xmas));


    Locale.setDefault(Locale.GERMANY);
    DateTimeFormatter fullFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL, FormatStyle.SHORT);
    System.out.println("Ausführliche Darstellung im Germany-Locale: " + fullFormatter.format(xmas));

    Locale.setDefault(Locale.UK);
    fullFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL, FormatStyle.SHORT);
    System.out.println("Ausführliche Darstellung im UK-Locale: " + fullFormatter.format(xmas));

    Locale.setDefault(Locale.US);
    fullFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL, FormatStyle.SHORT);
    System.out.println("Ausführliche Darstellung im US-Locale: " + fullFormatter.format(xmas));
  }
}
