package prog.ex13.pizzadeliveryproperties;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import prog.ex13.exercise.pizzadeliveryproperties.InvalidPizzaDeliveryConfigurationException;
import prog.ex13.exercise.pizzadeliveryproperties.PizzaDeliveryConfigurator;
import prog.ex13.exercise.pizzadeliveryproperties.PizzaSize;
import prog.ex13.solution.pizzadeliveryproperties.PropertyPizzaDeliveryConfigurator;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class TestPropertyPizzaDeliveryConfiguratorGoodCases {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(TestPropertyPizzaDeliveryConfiguratorGoodCases.class);

  private PizzaDeliveryConfigurator configurator;

  @Before
  public void setup(){
    configurator = new PropertyPizzaDeliveryConfigurator();
  }

  @Test
  public void testIfDefaultIsUnconfigured() {
    logger.info("Check if an unloaded configurator has no valid configuration.");
    assertFalse(configurator.hasValidConfiguration());
  }

  @Test
  public void testReadProperPropertyFile() throws IOException, InvalidPizzaDeliveryConfigurationException {
    logger.info("try to read a proper configuration file.");
    configurator.load(new File("testfiles/pizzadelivery-good-configuration.properties"));
    logger.info("configurator should have proper configuration.");
    assertTrue(configurator.hasValidConfiguration());
    Map<PizzaSize, Integer> pizzaSizePriceList = configurator.getPizzaSizePriceList();
    logger.info("PizzaSizePriceList should not be null");
    assertNotNull(pizzaSizePriceList);
    logger.info("Number of entries in PizzaSizePriceList should be the same as there are " +
            "different PizzaSize values.");
    assertEquals(PizzaSize.values().length, pizzaSizePriceList.size());

  }
}
