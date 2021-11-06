package examples.designpattern.observer;

public class DemoWithObservers {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(DemoWithObservers.class);

  public static void main(String[] args) {
    DepreactedObservablePerson depreactedObservablePerson = new DepreactedObservablePerson("Victor", "Hugo");
    DeprecatedPersonObserver deprecatedPersonObserver = new DeprecatedPersonObserver();
    depreactedObservablePerson.addObserver(deprecatedPersonObserver);
    depreactedObservablePerson.setFirstName("Alexander");

    PropertyChangeObservablePerson propertyChangeObservablePerson = new PropertyChangeObservablePerson("Alexandre", "Dumas");
    ProperyChangePersonObserver properyChangePersonObserver = new ProperyChangePersonObserver();
    propertyChangeObservablePerson.addPropertyChangeListener(properyChangePersonObserver);
    propertyChangeObservablePerson.setFirstName("Viktor");
  }
}
