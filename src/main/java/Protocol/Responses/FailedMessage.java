package Protocol.Responses;

import Protocol.IResponse;
import Protocol.Message;
import Protocol.MessageIdentifier;
import java.util.Arrays;
import java.util.Objects;

/**
 * The type Failed message.
 */
public class FailedMessage extends Message implements IResponse {

  /**
   * The Size.
   */
  private Integer size;
  /**
   * The Description.
   */
  private byte[] description;

  /**
   * Instantiates a new Failed message.
   *
   * @param size        the size
   * @param description the description
   */
  public FailedMessage(Integer size, byte[] description) {
    super(MessageIdentifier.FAILED_MESSAGE);
    this.size = size;
    this.description = description;
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
    if (!(object instanceof FailedMessage)) {
      return false;
    }
    if (!super.equals(object)) {
      return false;
    }
    FailedMessage message = (FailedMessage) object;
    return Objects.equals(size, message.size) &&
        Arrays.equals(getDescription(), message.getDescription());
  }

  /**
   * Hash method.
   *
   * @return int hash value
   */
  @Override
  public int hashCode() {
    int result = Objects.hash(super.hashCode(), size);
    result = 31 * result + Arrays.hashCode(getDescription());
    return result;
  }

  /**
   * Get description byte [ ].
   *
   * @return the byte [ ]
   */
  public byte[] getDescription() {
    return description;
  }


  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("FailedMessage{");
    sb.append("size=").append(size);
    sb.append(", description=").append(Arrays.toString(description));
    sb.append('}');
    return sb.toString();
  }

  @Override
  public Integer getResponseSize() {
    return this.size;
  }
}
