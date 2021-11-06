package prog.ex13.exercise.pizzadeliveryproperties;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public interface PizzaDeliveryConfigurator {
  /**
   * Loads configuration data from the given file. Each load() call removes all old configuration
   * data
   * @param file file with the configuration information
   * @throws IOException if the file does not exist or is not readable
   * @throws InvalidPizzaDeliveryConfigurationException if the content of the file is not a valid
   * configuration
   */
  void load(File file) throws IOException, InvalidPizzaDeliveryConfigurationException;

  /**
   * Returns the price information for different Pizza sizes.
   *
   * @return Map with price per PizzaSize
   * @throws InvalidPizzaDeliveryConfigurationException if no valid configuration has been loaded
   */
  Map<PizzaSize, Integer> getPizzaSizePriceList() throws InvalidPizzaDeliveryConfigurationException;

  /**
   * Returns the price information for Toppings.
   *
   * @return Map with price per Topping
   * @throws InvalidPizzaDeliveryConfigurationException if no valid configuration has been loaded
   */
  Map<Topping, Integer> getToppingsPriceList() throws InvalidPizzaDeliveryConfigurationException;

  /**
   * Check if the PizzaDeliveryConfigurator has a valid configuration.
   * @return true if the last load resulted in a valid configuration
   */
  boolean hasValidConfiguration();
}
