package Protocol.Requests;

import Protocol.MessageIdentifier;
import Protocol.Request;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * The type Broadcast message.
 */
public class BroadcastMessage extends Request {


  /**
   * The Msg size.
   */
  private Integer msgSize;
  /**
   * The Message.
   */
  private byte[] message;



  /**
   * Instantiates a new Broadcast message.
   *
   * @param usernameSize the username size
   * @param username     the username
   * @param msgSize      the msg size
   * @param message      the message
   */
  public BroadcastMessage(Integer usernameSize, byte[] username,
      Integer msgSize, byte[] message) {
    super(MessageIdentifier.BROADCAST_MESSAGE, usernameSize, username);
    this.msgSize = msgSize;
    this.message = message;
  }

  /**
   * Gets msg size.
   *
   * @return the msg size
   */
  public Integer getMsgSize() {
    return msgSize;
  }

  /**
   * Get message byte [ ].
   *
   * @return the byte [ ]
   */
  public byte[] getMessage() {
    return message;
  }


  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("BroadcastMessage{");
    sb.append("msgSize=").append(msgSize);
    sb.append(", message=").append(Arrays.toString(message));
    sb.append('}');
    return sb.toString();
  }

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
    if (!(object instanceof BroadcastMessage)) {
      return false;
    }
    if (!super.equals(object)) {
      return false;
    }
    BroadcastMessage that = (BroadcastMessage) object;
    return Objects.equals(getMsgSize(), that.getMsgSize()) &&
        Arrays.equals(getMessage(), that.getMessage());
  }

  /**
   * Hash method.
   *
   * @return int hash value
   */
  @Override
  public int hashCode() {
    int result = Objects.hash(super.hashCode(), getMsgSize());
    result = 31 * result + Arrays.hashCode(getMessage());
    return result;
  }
}
