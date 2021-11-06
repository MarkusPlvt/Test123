package examples.properties;

import java.util.Properties;

public class NestedPropertiesExample {
  
  public static void main(String[] args) {
    Properties defaultProperties = new Properties();
    Properties userProperties = new Properties(defaultProperties);

    defaultProperties.setProperty("User", "Standard");
    defaultProperties.setProperty("Version", "" + 0.02f);

    userProperties.setProperty("User", "Speziell ");
    System.out.println("Default Properties:");
    defaultProperties.list(System.out);

    System.out.println("\nUser Properties:");
    userProperties.list(System.out);
    System.out.println("Property 'User' is '" + userProperties.getProperty("User") + "'");
  }
}
