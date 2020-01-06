package Protocol;

import java.util.Arrays;
import java.util.Objects;

/**
 * The type Request.
 */
public abstract class Request extends Message {

  /**
   * The Sender size.
   */
  private Integer usernameSize;
  /**
   * The Sender username.
   */
  private byte[] username;

  @Override
  public abstract String toString();

  /**
   * equals method.
   *
   * @return boolean if equal.
   */
  @Override
  public boolean equals(Object object) {
    if (this == object) {
      return true;
    }
    if (!(object instanceof Request)) {

      return false;
    }
    if (!super.equals(object)) {

      return false;
    }
    Request request = (Request) object;
    return Objects.equals(getUsernameSize(), request.getUsernameSize()) &&
        Arrays.equals(getUsername(), request.getUsername());
  }

  @Override
  public int hashCode() {
    int result = Objects.hash(super.hashCode(), getUsernameSize());
    result = 31 * result + Arrays.hashCode(getUsername());
    return result;
  }

  /**
   * Instantiates a new Request.
   *
   * @param identifier   the identifier
   * @param usernameSize the username size
   * @param username     the username
   */
  public Request(MessageIdentifier identifier, Integer usernameSize, byte[] username) {
    super(identifier);
    this.usernameSize = usernameSize;
    this.username = username;
  }

  /**
   * Gets username size.
   *
   * @return the username size
   */
  public Integer getUsernameSize() {
    return usernameSize;
  }

  /**
   * Get username byte [ ].
   *
   * @return the byte [ ]
   */
  public byte[] getUsername() {
    return username;
  }



}
