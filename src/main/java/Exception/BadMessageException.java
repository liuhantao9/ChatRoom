package Exception;

public class BadMessageException extends Exception {

  /**
   *
   * @param errorMessage
   */
  public BadMessageException(String errorMessage) {
    super(errorMessage);
  }
}
