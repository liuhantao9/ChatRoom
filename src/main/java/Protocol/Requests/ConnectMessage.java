package Protocol.Requests;

import Protocol.Message;
import Protocol.MessageIdentifier;
import Protocol.Request;
import java.util.Arrays;
import java.util.Objects;

/**
 * The type Connect message.
 */
public class ConnectMessage extends Request {

  /**
   * Instantiates a new Connect message.
   *
   * @param usernameSize the username size
   * @param username     the username
   */
  public ConnectMessage(Integer usernameSize, byte[] username) {
    super(MessageIdentifier.CONNECT_MESSAGE, usernameSize, username);
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("ConnectMessage{");
    sb.append("usernameSize=").append(this.getUsernameSize());
    sb.append(", username=").append(Arrays.toString(this.getUsername()));
    sb.append('}');
    return sb.toString();
  }

}
