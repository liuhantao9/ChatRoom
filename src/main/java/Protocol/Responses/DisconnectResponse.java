package Protocol.Responses;

import Protocol.IResponse;
import Protocol.Message;
import Protocol.MessageIdentifier;
import java.util.Arrays;
import java.util.Objects;

/**
 * The type Disconnect response.
 */
public class DisconnectResponse extends Message implements IResponse {

  /**
   * The Success.
   */
  private boolean success;
  /**
   * The Msg size.
   */
  private Integer msgSize;
  /**
   * The Message.
   */
  private byte[] message;

  /**
   * Instantiates a new Disconnect response.
   *
   * @param success the success
   * @param msgSize the msg size
   * @param message the message
   */
  public DisconnectResponse(boolean success, Integer msgSize,
      byte[] message) {
    super(MessageIdentifier.DISCONNECT_RESPONSE);
    this.success = success;
    this.msgSize = msgSize;
    this.message = message;
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
    if (!(object instanceof DisconnectResponse)) {
      return false;
    }
    if (!super.equals(object)) {
      return false;
    }
    DisconnectResponse that = (DisconnectResponse) object;
    return isSuccess() == that.isSuccess() &&
        Objects.equals(msgSize, that.msgSize) &&
        Arrays.equals(getMessage(), that.getMessage());
  }

  /**
   * Hash method.
   *
   * @return int hash value
   */
  @Override
  public int hashCode() {
    int result = Objects.hash(super.hashCode(), isSuccess(), msgSize);
    result = 31 * result + Arrays.hashCode(getMessage());
    return result;
  }

  /**
   * Is success boolean.
   *
   * @return the boolean
   */
  public boolean isSuccess() {
    return success;
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
    final StringBuilder sb = new StringBuilder("DisconnectResponse{");
    sb.append("success=").append(success);
    sb.append(", msgSize=").append(msgSize);
    sb.append(", message=").append(Arrays.toString(message));
    sb.append('}');
    return sb.toString();
  }

  @Override
  public Integer getResponseSize() {
    return this.msgSize;
  }
}
