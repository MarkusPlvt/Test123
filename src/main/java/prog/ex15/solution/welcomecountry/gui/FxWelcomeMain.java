package prog.ex15.solution.welcomecountry.gui;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.BorderPane;
import prog.ex15.exercise.welcomecountry.CountryKnowledgeContainer;
import prog.ex15.exercise.welcomecountry.Language;
import prog.ex15.solution.welcomecountry.I18nKnowledgeGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * I18nKnowledgeGenerator class. JavaFX component presenting the WelcomeToMyCountry content in
 * either a standalone application or in a bundle of JavaFX components.
 *
 * @author Markus Kübler
 * @datum 20.01.2021
 * @version 1.0
 */
public class FxWelcomeMain extends BorderPane {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(FxWelcomeMain.class);

  private ChoiceBox<Language> languageChoiceBox;
  private Language language;
  private Language defaultLanguage = Language.ENGLAND;
  private CountryKnowledgeContainer countryKnowledgeContainer;
  private FxKnowledgePresenterSolution knowledgePresenter;

  /** Constructor to create the gui elements and prepare all data structures. */
  public FxWelcomeMain() throws IOException {
    language = defaultLanguage;
    languageChoiceBox = new ChoiceBox<>();
    List<Language> languageList = new ArrayList<>();
    languageList.addAll(Arrays.asList(Language.values()));
    ObservableList<Language> languageObservableList = FXCollections.observableList(languageList);
    languageChoiceBox.setItems(languageObservableList);
    languageChoiceBox.getSelectionModel().select(language);
    languageChoiceBox
        .getSelectionModel()
        .selectedItemProperty()
        .addListener(
            (language, preSelectedLanguage, selectedLanguage) -> {
              try {
                switchLanguage(language, preSelectedLanguage, selectedLanguage);
              } catch (IOException e) {
                e.printStackTrace();
              }
            });
    this.setTop(languageChoiceBox);
    countryKnowledgeContainer = new CountryKnowledgeContainer();
    this.switchLanguage(null, null, language);
  }

  /**
   * Action when the language selector gets triggered.
   *
   * @param language observable language
   * @param preSelectedLanguage language which was selected before
   * @param selectedLanguage language which is selected now
   */
  private void switchLanguage(
      final Observable language, Language preSelectedLanguage, Language selectedLanguage)
      throws IOException {
    logger.info("User selected language " + selectedLanguage.toString());
    I18nKnowledgeGenerator.fillContainer(countryKnowledgeContainer, selectedLanguage);
    knowledgePresenter =
        new FxKnowledgePresenterSolution(countryKnowledgeContainer, selectedLanguage);
    this.setCenter(knowledgePresenter);

    // Alert alert = new Alert(Alert.AlertType.INFORMATION); alert.setTitle("Language changed.");
    // alert.setContentText("Language has been changed to: " +
    // languageChoiceBox.getValue().toString().toLowerCase()); alert.show();
  }
}
