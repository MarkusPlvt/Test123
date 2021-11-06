package prog.ex15.exercise.welcomecountry.gui;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.BorderPane;
import prog.ex15.exercise.welcomecountry.CountryKnowledgeContainer;
import prog.ex15.exercise.welcomecountry.Language;
import prog.ex15.exercise.welcomecountry.StupidKnowledgeGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;


/**
 * JavaFX component presenting the WelcomeToMyCountry content in either a standalone application
 * or in a bundle of JavaFX components.
 */
public class FxWelcomeMain extends BorderPane {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(FxWelcomeMain.class);

  private ChoiceBox<Language> languageChoiceBox;

  /**
   * Constructor to create the gui elements and prepare all data structures.
   */
  public FxWelcomeMain() {
    languageChoiceBox = new ChoiceBox<>();
    List<Language> languageList = new ArrayList<>();
    languageList.addAll(Arrays.asList(Language.values()));
    ObservableList<Language> languageObservableList = FXCollections.observableList(languageList);
    languageChoiceBox.setItems(languageObservableList);
    languageChoiceBox.getSelectionModel().select(Language.ENGLAND);
    languageChoiceBox.getSelectionModel().selectedItemProperty()
            .addListener((language) -> switchLanguage(language));
    this.setTop(languageChoiceBox);

    CountryKnowledgeContainer countryKnowledgeContainer = new CountryKnowledgeContainer();
    StupidKnowledgeGenerator.fillContainer(countryKnowledgeContainer, Locale.getDefault());
    FxKnowledgePresenter knowledgePresenter = new FxKnowledgePresenter(countryKnowledgeContainer);
    this.setCenter(knowledgePresenter);
  }

  /**
   * Action when the language selector gets triggered.
   *
   * @param language selected language
   */
  private void switchLanguage(final Observable language) {
    logger.info("User selected language " + language.toString());
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Nonsense");
    alert.setContentText("Don't try this. I am stupid english");
    alert.show();
  }
}
