package prog.javafx.main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import prog.ex07.exercise.javafx4palindrome.PalindromeChecker;
import prog.ex07.solution.javafx4palindrome.PalindromeCheckerGui;
import prog.ex07.solution.javafx4palindrome.SimplePalindromeChecker;
import prog.ex09.solution.editpizzascreen.gui.EditPizzaScreen;
import prog.ex10.exercise.javafx4pizzadelivery.gui.ScreenController;
import prog.ex10.exercise.javafx4pizzadelivery.pizzadelivery.PizzaDeliveryService;
import prog.ex10.solution.javafx4pizzadelivery.gui.CreateOrderScreen;
import prog.ex10.solution.javafx4pizzadelivery.gui.PizzaDeliveryScreenController;
import prog.ex10.solution.javafx4pizzadelivery.gui.SingletonAttributeStore;
import prog.ex10.solution.javafx4pizzadelivery.pizzadelivery.SimplePizzaDeliveryService;


public class KprogGuiWithJavaFx extends Application {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(KprogGuiWithJavaFx.class);

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(final Stage primaryStage) throws Exception {
    TabPane root = new TabPane();
    Tab welcomeTab = new Tab("Welcome", new Label("Your advertisement could be here."));
    root.getTabs().add(welcomeTab);

    PalindromeChecker palindromeChecker = new SimplePalindromeChecker();
    PalindromeCheckerGui palindromeCheckerGui = new PalindromeCheckerGui(palindromeChecker);
    Tab palindromeTab = new Tab("PalindromeChecker", palindromeCheckerGui);
    root.getTabs().add(palindromeTab);

    prog.ex09.exercise.editpizzascreen.pizzadelivery.PizzaDeliveryService ex09Service =
            new prog.ex09.solution.editpizzascreen.pizzadelivery.SimplePizzaDeliveryService();
    int orderId = ex09Service.createOrder();
    int pizzaId = ex09Service.addPizza(orderId,
            prog.ex09.exercise.editpizzascreen.pizzadelivery.PizzaSize.EXTRA_LARGE);
    EditPizzaScreen editPizzaScreen = new EditPizzaScreen(ex09Service, orderId, pizzaId);
    Tab editPizzaTab = new Tab("ex09 EditPizzaScreen", editPizzaScreen);
    root.getTabs().add(editPizzaTab);


    SingletonAttributeStore attributeStore = SingletonAttributeStore.getReference();
    PizzaDeliveryService service = new SimplePizzaDeliveryService();
    attributeStore.setAttribute("PizzaDeliveryService", service);
    Tab pizzaDeliveryTab = new Tab("PizzaDeliveryService", new Label("Gorgios PizzaDelivery!"));
    ScreenController controller = new PizzaDeliveryScreenController(pizzaDeliveryTab);
    controller.switchTo(null, CreateOrderScreen.SCREEN_NAME);
    root.getTabs().add(pizzaDeliveryTab);

    primaryStage.setTitle("Hello JavaFX World");
    primaryStage.setScene(new Scene(root));
    primaryStage.show();

  }
}
