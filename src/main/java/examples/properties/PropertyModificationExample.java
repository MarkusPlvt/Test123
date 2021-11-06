package examples.properties;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyModificationExample {

  public static void main(String[] args) {

    FileOutputStream propFOSXML = null;
    Properties props = new Properties();

    try (FileInputStream propFIS = new FileInputStream("beispiel.properties"); FileOutputStream propFOS = new FileOutputStream("beispiel_modifiziert.properties");) {

      props.load(propFIS);
      props.list(System.out);

      props.setProperty("prop2", "neuer Wert");
      props.list(System.out);

      System.out.println("\nProperty prop2 hat nun den Wert " + props.getProperty("prop2"));

      props.store(propFOS, "Modifizierte Properties");

      propFOSXML = new FileOutputStream("beispiel_modifiziert.properties.xml");
      props.storeToXML(propFOSXML, "Modifizierte Properties als XML-Datei");

      // Laden der Properties aus XML-Datei: props.loadFromXML(propFIS);

    } catch (FileNotFoundException e) {
      System.exit(1);
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
      System.exit(2);
    } finally {
      try {
        if (propFOSXML != null) {
          propFOSXML.close();
        }
      } catch (IOException e) {
        e.printStackTrace();
        System.exit(3);
      }
    }
  }

}
