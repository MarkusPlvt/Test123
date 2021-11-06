package prog.ex13.pizzadeliveryproperties;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import prog.ex13.exercise.pizzadeliveryproperties.InvalidPizzaDeliveryConfigurationException;
import prog.ex13.exercise.pizzadeliveryproperties.PizzaDeliveryConfigurator;
import prog.ex13.solution.pizzadeliveryproperties.PropertyPizzaDeliveryConfigurator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.Assert.fail;

public class TestPropertyPizzaDeliveryConfiguratorBadCases {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(TestPropertyPizzaDeliveryConfiguratorBadCases.class);

  private PizzaDeliveryConfigurator configurator;

  @Before
  public void setup(){
    configurator = new PropertyPizzaDeliveryConfigurator();
  }

  @Test(expected = FileNotFoundException.class)
  public void testWithNonExistingFileName() throws IOException, InvalidPizzaDeliveryConfigurationException {
    logger.info("Try to open a nonexisting file. FileNotFoundException expected.");
    configurator.load(new File("testfiles/this-is-a-not-existing-property-file.properties"));

    fail("An attempt to open a non-existing property file should result in an IOException.");
  }

  @Test(expected = InvalidPizzaDeliveryConfigurationException.class)
  public void testWithMissingPizzaSize() throws IOException,
          InvalidPizzaDeliveryConfigurationException {
    logger.info("Try to load a configuration where one pizza size is missing.");
    configurator.load(new File("testfiles/pizzadelivery-bad-configuration1.properties"));

    fail("Reading a bad configuration should result in an " +
            "InvalidPizzaDeliveryConfigurationException");
  }

  @Test(expected = InvalidPizzaDeliveryConfigurationException.class)
  public void testWithToppingWithNoNumericValue() throws IOException,
          InvalidPizzaDeliveryConfigurationException {
    logger.info("Try to load a configuration where one topping as not a numeric value.");
    configurator.load(new File("testfiles/pizzadelivery-bad-configuration2.properties"));

    fail("Reading a bad configuration should result in an " +
            "InvalidPizzaDeliveryConfigurationException");
  }

}
