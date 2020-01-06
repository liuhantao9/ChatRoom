package Protocol.Requests;

import Protocol.MessageIdentifier;
import Protocol.Request;
import java.util.Arrays;

/**
 * The type Disconnect message.
 */
public class DisconnectMessage extends Request {

  /**
   * Instantiates a new Disconnect message.
   *
   * @param usernameSize the username size
   * @param username     the username
   */
  public DisconnectMessage(Integer usernameSize, byte[] username) {
    super(MessageIdentifier.DISCONNECT_MESSAGE, usernameSize, username);
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("DisconnectMessage{");
    sb.append("usernameSize=").append(this.getUsernameSize());
    sb.append(", username=").append(Arrays.toString(this.getUsername()));
    sb.append('}');
    return sb.toString();
  }
}
