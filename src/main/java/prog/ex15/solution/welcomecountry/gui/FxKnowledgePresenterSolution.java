package prog.ex15.solution.welcomecountry.gui;

import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import prog.ex15.exercise.welcomecountry.Category;
import prog.ex15.exercise.welcomecountry.CountryKnowledgeContainer;
import prog.ex15.exercise.welcomecountry.Language;
import prog.ex15.solution.welcomecountry.I18nKnowledgeGenerator;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

/**
 * FxKnowledgePresenterSolution class. JavaFX component presenting the content of a
 * CountryKnowledgeContainer.
 *
 * @author Markus Kübler
 * @datum 20.01.2021
 * @version 1.0
 */
public class FxKnowledgePresenterSolution extends Accordion {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(FxKnowledgePresenterSolution.class);

  CountryKnowledgeContainer countryKnowledgeContainer;
  String countryCode;

  public FxKnowledgePresenterSolution(
      final CountryKnowledgeContainer countryKnowledgeContainer, Language language)
      throws IOException {
    this.countryKnowledgeContainer = countryKnowledgeContainer;
    fillAccordion(language);
  }

  private void fillAccordion(Language language) throws IOException {
    this.getPanes().clear();
    for (Category category : Category.values()) {
      TitledPane titledPane = new TitledPane();
      Properties properties = new Properties();
      titledPane.setText(category.toString());
      countryCode = null;
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
        List<String> knowledgeList = countryKnowledgeContainer.getKnowledge(category);
        VBox box = new VBox();
        for (String string : knowledgeList) {
          box.getChildren().add(new Label(string));
          logger.info("Adding label " + string);
        }
        titledPane.setContent(box);
      } else {
        properties.load(
            I18nKnowledgeGenerator.class
                .getClassLoader()
                .getResourceAsStream("bundles/WelcomeCountry_" + countryCode + ".properties"));
        List<String> knowledgeList = countryKnowledgeContainer.getKnowledge(category);
        VBox box = new VBox();
        for (String string : knowledgeList) {
          box.getChildren().add(new Label(string));
          logger.info("Adding label " + string);
        }
        titledPane.setContent(box);
      }
      this.getPanes().add(titledPane);
    }
  }

  public void updateCountryKnowledgeContainer(
      final CountryKnowledgeContainer countryKnowledgeContainer, Language language)
      throws IOException {
    this.countryKnowledgeContainer = countryKnowledgeContainer;
    fillAccordion(language);
  }
}
