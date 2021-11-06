package prog.ex13.exercise.pizzadeliveryproperties;

public class InvalidPizzaDeliveryConfigurationException extends Exception {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(InvalidPizzaDeliveryConfigurationException.class);

  public InvalidPizzaDeliveryConfigurationException(final String message) {
    super(message);
  }

  public InvalidPizzaDeliveryConfigurationException(final String message, final Throwable cause) {
    super(message, cause);
  }
}
