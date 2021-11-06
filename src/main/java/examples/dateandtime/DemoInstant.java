package examples.dateandtime;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Demo für die Klassen Clock, Instant und Period inkl. Formatierer.
 *
 * @author Cornelia Rabe
 * @version 2015/03/13
 */
public class DemoInstant {

  public static void main(String[] args) {
    // Instant ist ein Zeitstempel und repräsentiert somit einen exakten
    // Zeitpunkt (z.B. für Logger-Ausgabe). Die zeitliche
    // Granularität ist in Nanosekunden (mit 96 Bit) festgelegt.

    Clock myclock = Clock.systemDefaultZone();
    Instant start = myclock.instant();
    System.out.println(start);
    // P für "Period" ; T trennt Datum und Zeit
    // Ausgabe:2014-09-23T08:44:11.807Z

    Long anf = System.currentTimeMillis();
    System.out.println("Systemzeit Anfang:" + anf);

    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    // ca. 3 Sekunden spaeter nach obiger Ausgabe
    Instant end = Instant.now();
    Long ende = System.currentTimeMillis();

    Duration elapsed = Duration.between(start, end);
    System.out.println(elapsed); // Ausgabe: PT3.01S

    DateTimeFormatter df = DateTimeFormatter.ofPattern("HH:mm:ss:SSSS");
    LocalTime fTime = LocalTime.ofNanoOfDay(elapsed.toNanos());
    String output = fTime.format(df);
    System.out.println("Duration:" + output); // Ausgabe: Duration:00:00:03:0270

    System.out.println("Systemzeit Ende:" + ende);

    long diff = ende - anf;
    System.out.println("diff:" + diff);
    // Achtung: entweder System.currentTimeMillis
    // oder die neue API - NICHT mischen!

    ZonedDateTime zdt = ZonedDateTime.now();
    System.out.println("zdt:" + zdt);

    LocalTime ltime1 = LocalTime.of(11, 30, 00, 0); // 11:30 Uhr
    LocalTime ltime2 = LocalTime.parse("11:30"); // auch 11:30 Uhr

    // Vergleichen mit equals!!!
    System.out.println("Zeitvergleich:" + ltime1.equals(ltime2));

    // 11. 11 2014
    LocalDate date14_11_11 = LocalDate.of(2014, Month.NOVEMBER, 11);

    // der 32. Tag im Jahr ist der 1. Februar
    LocalDate date14_02_01 = LocalDate.ofYearDay(2014, 32);

    // LocalDateTime ldatetime1 = LocalDateTime.of(date14_11_11, ltime1);
    LocalDateTime ldatetime2a = LocalDateTime.of(date14_02_01, ltime2);

    LocalDateTime ldatetime2b = LocalDateTime.of(2014, Month.FEBRUARY, 1,
            11, 30, 0, 0);

    // beides ist der 1.2.2014, 11:30 Uhr
    assert (ldatetime2a.equals(ldatetime2b));

    Period between = Period.between(date14_11_11, date14_02_01);
    System.out.println(between); // Ausgabe: P-9M-10D

    // Geburtstag Angela Merkel 17. Juli 1954
    LocalDate birthdayAngela = LocalDate.of(1954, Month.JULY, 17);
    LocalDate now = LocalDate.now();

    Period p = Period.between(birthdayAngela, now);
    System.out.println("Angela Merkel Alter:" + p.getYears() + " Jahre "
            + p.getMonths() + " Monate " + p.getDays() + " Tage ");

    Duration d = Duration.between(ldatetime2a, LocalDateTime.now());
    System.out.println("Dauer:" + d.toHours() + " in Stunden ");
    System.out.println("Dauer:" + d.toMinutes() + " in Minuten ");
  }
}
