package examples.properties;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyExample {

  public static void main(String[] args) {

    Properties props = new Properties();

    try (FileInputStream propFIS = new FileInputStream("beispiel.properties")) {

      props.load(propFIS);
      props.list(System.out);

      System.out.println("\nProperty prop2 hat den Wert " + props.getProperty("prop2"));
      System.out.println("\nProperty prop4 hat den Wert " + props.getProperty("prop4"));
      System.out.println("\nProperty prop4 hat den Standardwert " + props.getProperty("prop4",
							"default for prop4"));

    } catch (FileNotFoundException e) {
      e.printStackTrace();
      System.exit(1);
    } catch (IOException e) {
      e.printStackTrace();
      System.exit(2);
    }

  }
}
