package examples.lambdas;

public class AnonymousInnerClassVsLambda {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(AnonymousInnerClassVsLambda.class);

  /**
   * Demo similarity of anonymous inner class with lambda
   */
  public static void main(String[] args) {

    Runnable runnable1 = new Runnable() {
      @Override
      public void run() {
        System.out.println("Hello World 1");
      }
    };

    Runnable runnable2 = () -> System.out.println("Hello World 2");

    runnable1.run();
    runnable2.run();
  }
}
