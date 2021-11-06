package examples.i18n.currency;


import java.text.NumberFormat;
import java.util.Locale;

public class Geldbetraege {
  public static void main(String[] args) {
    NumberFormat germanNumberFormat = NumberFormat.getCurrencyInstance(Locale.GERMANY);
    NumberFormat britishNumberFormat = NumberFormat.getCurrencyInstance(Locale.UK);
    System.out.println("Geldbetrag in Euro : " + germanNumberFormat.format(12345.67));
    System.out.println("Geldbetrag in Pfund : " + britishNumberFormat.format(12345.67));
  }
}


