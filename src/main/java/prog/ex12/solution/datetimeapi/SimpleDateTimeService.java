package prog.ex12.solution.datetimeapi;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import prog.ex12.exercise.datetimeapi.DateTimeService;
import prog.ex12.exercise.datetimeapi.EventInTime;
import prog.ex12.exercise.datetimeapi.NoDateTimeServiceStateException;

/**
 * SimpleDateTimeService Class.
 *
 * @author Markus Kübler 207273
 * @datum 22.12.2020
 * @version 1.0
 */
public class SimpleDateTimeService implements DateTimeService {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(SimpleDateTimeService.class);
  private List<EventInTime> events = new ArrayList<>();
  private static int eventCounter = 1;
  private EventInTime eventInTime;

  /**
   * Returns the leap year nearest to the given year. If the distance to the given year is the same
   * for more than one solution, the earlier one shall be chosen.
   *
   * @param year year to reference to
   * @return leap year with minimum distance to the given year
   * @throws IllegalArgumentException if the year is a null reference
   */
  @Override
  public Year nearestLeapYear(final Year year) throws IllegalArgumentException {
    Year foundLeapPast = null;
    Year foundLeapFuture = null;
    Year leapToReturn;
    if (year == null) {
      throw new IllegalArgumentException("Year is null.");
    }
    if (year.isLeap()) {
      return year;
    }
    for (int i = 1; i <= 3; i++) {
      Year testYearPast = year.minusYears(i);
      if (testYearPast.isLeap()) {
        foundLeapPast = testYearPast;
        break;
      }
    }
    for (int i = 1; i <= 3; i++) {
      Year testYearFuture = year.plusYears(i);
      if (testYearFuture.isLeap()) {
        foundLeapFuture = testYearFuture;
        break;
      }
    }
    int diffPast = Math.abs(foundLeapPast.compareTo(year));
    int diffFuture = foundLeapFuture.compareTo(year);
    if (diffPast == diffFuture) {
      return foundLeapPast;
    } else {
      if (diffPast < diffFuture) {
        leapToReturn = foundLeapPast;
      } else {
        leapToReturn = foundLeapFuture;
      }
      return leapToReturn;
    }
  }

  /**
   * Returns the day of week of the localDate object.
   *
   * @param localDate LocalDate the day of week is requested to.
   * @return Day of week belonging to the given LocalDate
   * @throws IllegalArgumentException if the localDate object is a null reference
   */
  @Override
  public DayOfWeek getDayOfWeek(final LocalDate localDate) throws IllegalArgumentException {
    if (localDate == null) {
      throw new IllegalArgumentException("localDate is a null reference.");
    } else {
      return LocalDate.parse(localDate.toString()).getDayOfWeek();
    }
  }

  /**
   * Calculates the difference between now and the given LocalDate.
   *
   * @param event localDate of the event
   * @return Period representing the time difference between now and then
   * @throws IllegalArgumentException if the localDate object is a null reference
   */
  @Override
  public Period timeBetweenNowAndThen(final LocalDate event) throws IllegalArgumentException {
    if (event == null) {
      throw new IllegalArgumentException("event is empty.");
    }
    LocalDate now = LocalDate.now();
    return Period.between(now, event);
  }

  /**
   * Calculates the difference between now and the referenced EventInTime object.
   *
   * @param eventId id of the EventInTime object
   * @return Period representing the time difference between now and then
   * @throws IllegalArgumentException if the eventId is not valid
   */
  @Override
  public Period timeBetweenNowAndThen(final int eventId) throws IllegalArgumentException {
    LocalDate now = LocalDate.now();
    for (int i = 0; i < events.size(); i++) {
      EventInTime eventInTime = events.get(i);
      if (eventInTime.getEventId() == eventId) {
        return Period.between(now, eventInTime.getLocalDate());
      }
    }
    throw new IllegalArgumentException("eventId is not valid.");
  }

  /**
   * Adds an event to this service for future reference. The service assigns a unique id to each
   * EventInTime object.
   *
   * @param event name of the event. The name consists only of letters, numbers and blanks. The
   *     event must contain at minimum two readable characters.
   * @param localDate Date of the event
   * @return id of the EventInTime object
   * @throws IllegalArgumentException if one of the parameters is a null reference or the string
   *     does not meet the above specification.
   */
  @Override
  public int addEvent(final String event, final LocalDate localDate)
      throws IllegalArgumentException {
    if (event == null) {
      throw new IllegalArgumentException("Event is null.");
    }
    if (localDate == null) {
      throw new IllegalArgumentException("LocalDate is null");
    }
    String normalizer = event.replaceAll("[a-zA-Z0-9 ]", "");
    if (normalizer.length() > 0) {
      throw new IllegalArgumentException("Eventname cointains bad character.");
    }
    String normalizer2 = event.replaceAll(" ", "");
    if (normalizer2.length() >= 2) {
      eventInTime = new EventInTime(eventCounter, event, localDate);
      events.add(eventInTime);
      eventCounter++;
      return eventInTime.getEventId();
    } else {
      throw new IllegalArgumentException(
          "The event must contain at minimum two readable characters.");
    }
  }

  /**
   * Returns a list of registered events.
   *
   * @return List of EventInTime objects. If no objects are registered, the list is empty.
   */
  @Override
  public List<EventInTime> getEvents() {
    return events;
  }

  /**
   * Removes the EventInTime object referenced by the eventId.
   *
   * @param eventId id of the object to be removed
   * @throws IllegalArgumentException if the eventId is not a valid id
   */
  @Override
  public void removeEvent(final int eventId) throws IllegalArgumentException {
    for (int i = 0; i < events.size(); i++) {
      EventInTime eventInTime = events.get(i);
      if (eventInTime.getEventId() == eventId) {
        events.remove(i);
        break;
      }
    }
    throw new IllegalArgumentException("eventId is not a valid id.");
  }

  /**
   * Loads the list of events from the given file. A call of the load-method removes all data which
   * was stored before, even if it fails.
   *
   * @param file file to load data from
   * @throws IOException if the file does not exist or cannot be read
   * @throws NoDateTimeServiceStateException if the content of the file does not meet the
   *     specification
   */
  @Override
  public void load(final File file) throws IOException, NoDateTimeServiceStateException {
    if (!file.canRead() || !file.exists()) {
      throw new IOException("file does not exist or cannot be read.");
    }
    events.clear();
    String line;
    int i = 0;
    int eventId;
    String eventName;
    LocalDate localDate;
    BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

    while ((line = bufferedReader.readLine()) != null) {
      StringTokenizer stringTokenizer = new StringTokenizer(line, ";");
      if (stringTokenizer.countTokens() != 3) {
        throw new NoDateTimeServiceStateException(
            "content of the file in line " + i + " does not meet the specification.");
      }
      eventId = Integer.parseInt(stringTokenizer.nextToken());
      eventName = stringTokenizer.nextToken();
      localDate = LocalDate.parse(stringTokenizer.nextToken());
      EventInTime eventInTime = new EventInTime(eventId, eventName, localDate);
      events.add(eventInTime);
      i++;
    }
  }

  /**
   * Saves the list of events to the given file.
   *
   * @param file file to be written to
   * @throws IOException if the file cannot be created and / or written.
   */
  @Override
  public void save(final File file) throws IOException {
    if (!file.createNewFile() || !file.canWrite()) {
      throw new IOException("file cannot be created and / or written.");
    }
    FileWriter fileWriter = new FileWriter(file);
    int i = 0;
    while (events.size() > i) {
      fileWriter.write(
          events.get(i).getEventId()
              + ";"
              + events.get(i).getName()
              + ";"
              + events.get(i).getLocalDate()
              + "\n");
      i++;
    }
    fileWriter.close();
  }
}
