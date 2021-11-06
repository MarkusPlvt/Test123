package prog.ex07.solution.javafx4palindrome;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.FlowPane;
import prog.ex07.exercise.javafx4palindrome.Constants;
import prog.ex07.exercise.javafx4palindrome.PalindromeChecker;

/**
 * SimplePizzaDeliveryService Class.
 *
 * @author Markus Kübler 207273
 * @datum 11.11.2020
 * @version 1.0
 */
public class PalindromeCheckerGui extends FlowPane {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(PalindromeCheckerGui.class);

  /**
   * Constructor for PalindromeCheckerGUI
   *
   * @param palindromeChecker palindromeChecker
   */
  public PalindromeCheckerGui(PalindromeChecker palindromeChecker) {
    Button button = new Button("Check Palindrome");
    Label label = new Label("--");
    TextField textfield = new TextField();

    super.getChildren().add(textfield);
    super.getChildren().add(button);
    super.getChildren().add(label);

    button.setOnAction(
        new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent event) {
            if (event.getSource() == button) {
              if (textfield.getText().isEmpty()) {
                label.setText(Constants.FAILURE);
              } else {
                if (palindromeChecker.isPalindrome(textfield.getText())) {
                  label.setText(Constants.SUCCESS);
                } else {
                  label.setText(Constants.FAILURE);
                }
              }
            }
          }
        });

    textfield.setOnKeyPressed(
        new EventHandler<KeyEvent>() {
          @Override
          public void handle(KeyEvent event) {
            if (event.getCode() == KeyCode.ENTER) {
              if (textfield.getText().isEmpty()) {
                label.setText(Constants.FAILURE);
              } else {
                if (palindromeChecker.isPalindrome(textfield.getText())) {
                  label.setText(Constants.SUCCESS);
                } else {
                  label.setText(Constants.FAILURE);
                }
              }
            }
          }
        });
  }
}
