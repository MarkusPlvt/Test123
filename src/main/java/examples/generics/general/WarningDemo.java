package examples.generics.general;

@SuppressWarnings("unchecked")
public class WarningDemo {

  /**
   * Pretend that this method is part of an old library,
   * written before generics. <br/>
   * It returns Box instead of Box<T>.
   */
  static Box createBox() {
    return new Box();
  }

  public static void main(String[] args) {
    Box<Integer> bi;
    bi = createBox();
  }
}

