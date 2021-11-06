package prog.ex15.solution.welcomecountry;

import java.io.IOException;
import prog.ex15.exercise.welcomecountry.CountryKnowledgeContainer;
import prog.ex15.exercise.welcomecountry.Category;
import prog.ex15.exercise.welcomecountry.Language;

import java.util.Properties;

/**
 * I18nKnowledgeGenerator class.
 *
 * @author Markus Kübler
 * @datum 20.01.2021
 * @version 1.0
 */
public class I18nKnowledgeGenerator {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(I18nKnowledgeGenerator.class);

  /**
   * @param container CountryKnowledgeContainer
   * @param language language -> couldn't find Locale for DK & NL >> changed to Language to use in
   *     SwitchCase
   */
  public static void fillContainer(CountryKnowledgeContainer container, Language language)
      throws IOException {
    Properties properties = new Properties();
    String countryCode;
    switch (language) {
      case GERMANY:
        countryCode = "de_DE";
        break;
      case ENGLAND:
        countryCode = "en_EN";
        break;
      case NETHERLANDS:
        countryCode = "nl_NL";
        break;
      case DENMARK:
        countryCode = "dk_DK";
        break;
      default:
        countryCode = null;
    }

    if (countryCode.isEmpty()) {
      properties.load(
          I18nKnowledgeGenerator.class
              .getClassLoader()
              .getResourceAsStream("bundles/WelcomeCountry.properties"));
    } else {
      properties.load(
          I18nKnowledgeGenerator.class
              .getClassLoader()
              .getResourceAsStream("bundles/WelcomeCountry_" + countryCode + ".properties"));
    }

    container.clear();
    container.addKnowledge(
        Category.TRAFFIC, properties.getProperty("traffic.maximum.speed.highways"));
    container.addKnowledge(Category.FOOD, properties.getProperty("food.most.prominent.food"));
    container.addKnowledge(
        Category.HOLIDAY, properties.getProperty("holyday.most.important.holyday"));
    container.addKnowledge(Category.STATISTICS, properties.getProperty("statistics.population"));
  }
}
