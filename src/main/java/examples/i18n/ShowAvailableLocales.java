package examples.i18n;

import java.text.DateFormat;
import java.util.Locale;

public class ShowAvailableLocales {

  static public void main(String[] args) {
    Locale list[] = DateFormat.getAvailableLocales();
    System.out.println("Von DateFormat unterstützte Locales im ISO-Format: " + list.length);
    for (Locale aLocale : list) {
      System.out.println(aLocale.toString());
    }
    System.out.println("\n\nVon DateFormat unterstützte Locales in lesbarer Form:");
    for (Locale aLocale : list) {
      System.out.println(aLocale.getDisplayName());
    }
  }
}
