package examples.properties;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class NestedSystemPropertiesExample {

  public static void main(String[] args) {

    FileInputStream propFIS = null;
    FileOutputStream propFOS = null;

    // Neues Property-Objekt erzeugen,
    // das die Systemeigenschaften (System Properties)
    // hierarchisch schachtelt.
    Properties props = new Properties(System.getProperties());
    try {
      propFIS = new FileInputStream("beispiel.properties");

      props.load(propFIS);

      props.list(System.out);

      System.out.println("\nModifikationen bei geschachtelten Properties:");
      System.out.println("user.country in props = " + props.getProperty("user.country"));
      props.setProperty("user.country", "FR");
      System.out.println("user.country in props = " + props.getProperty("user.country"));
      System.out.println("user.country in System Properties = " + System.getProperty("user"
              + ".country"));

      propFOS = new FileOutputStream("beispiel_hierarisch.properties");
      props.store(propFOS, "Hierarchische Properties");

    } catch (FileNotFoundException e) {
      System.exit(1);
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
      System.exit(2);
    } finally {
      try {
        propFIS.close();
      } catch (IOException e) {
        e.printStackTrace();
        System.exit(3);
      }
    }
  }
}
