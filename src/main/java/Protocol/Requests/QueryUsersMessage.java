package Protocol.Requests;

import Protocol.MessageIdentifier;
import Protocol.Request;
import java.util.Arrays;
import java.util.Objects;

/**
 * The type Query users.
 */
public class QueryUsersMessage extends Request {

  /**
   * Instantiates a new Query users message.
   *
   * @param usernameSize the username size
   * @param username     the username
   */
  public QueryUsersMessage(Integer usernameSize, byte[] username) {
    super(MessageIdentifier.QUERY_CONNECTED_USERS, usernameSize, username);
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("QueryUsersMessage{");
    sb.append("usernameSize=").append(this.getUsernameSize());
    sb.append(", username=").append(Arrays.toString(this.getUsername()));
    sb.append('}');
    return sb.toString();
  }
}
