package prog.ex15.exercise.welcomecountry;

import java.util.Locale;

/**
 * Fills knowledge into a CountryKnowledgeContainer.
 */
public class StupidKnowledgeGenerator {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(StupidKnowledgeGenerator.class);

  /**
   * Fills knowledge into the container.
   *
   * @param container Container to be filled.
   * @param locale    locale to which the knowledge should be related to in language and content.
   *                  This is ignored in this implementation.
   */
  public static void fillContainer(CountryKnowledgeContainer container, Locale locale) {
    container.addKnowledge(Category.TRAFFIC, "Maximum speed on highways is 70 mph.");
    container.addKnowledge(Category.FOOD, "Our most prominent food is Fish&Chips.");
    container.addKnowledge(Category.HOLIDAY,
            "Our most important holyday is  Queen’s Official Birthday.");
    container.addKnowledge(Category.STATISTICS, "Our population is 55.977.178");
  }
}
