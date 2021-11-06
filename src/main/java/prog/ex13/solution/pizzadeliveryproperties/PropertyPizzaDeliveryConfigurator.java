package prog.ex13.solution.pizzadeliveryproperties;

import prog.ex13.exercise.pizzadeliveryproperties.InvalidPizzaDeliveryConfigurationException;
import prog.ex13.exercise.pizzadeliveryproperties.PizzaDeliveryConfigurator;
import prog.ex13.exercise.pizzadeliveryproperties.PizzaSize;
import prog.ex13.exercise.pizzadeliveryproperties.Topping;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class PropertyPizzaDeliveryConfigurator implements PizzaDeliveryConfigurator {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(PropertyPizzaDeliveryConfigurator.class);

  @Override
  public void load(final File file) throws IOException, InvalidPizzaDeliveryConfigurationException {

  }

  @Override
  public Map<PizzaSize, Integer> getPizzaSizePriceList() throws InvalidPizzaDeliveryConfigurationException {
    return null;
  }

  @Override
  public Map<Topping, Integer> getToppingsPriceList() throws InvalidPizzaDeliveryConfigurationException {
    return null;
  }

  @Override
  public boolean hasValidConfiguration() {
    return false;
  }
}
