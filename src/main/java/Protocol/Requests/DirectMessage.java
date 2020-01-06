package Protocol.Requests;

import Protocol.Message;
import Protocol.MessageIdentifier;
import Protocol.Request;
import java.util.Arrays;
import java.util.Objects;

/**
 * The type Direct message.
 */
public class DirectMessage extends Request {


  /**
   * The Recipient size.
   */
  private Integer recipientSize;
  /**
   * The Recipient username.
   */
  private byte[] recipientUsername;
  /**
   * The Message size.
   */
  private Integer messageSize;
  /**
   * The Message.
   */
  private byte[] message;


  /**
   * Gets recipient size.
   *
   * @return the recipient size
   */
  public Integer getRecipientSize() {
    return recipientSize;
  }

  /**
   * Get recipient username byte [ ].
   *
   * @return the byte [ ]
   */
  public byte[] getRecipientUsername() {
    return recipientUsername;
  }


  /**
   * Instantiates a new Direct message.
   *
   * @param usernameSize      the username size
   * @param username          the username
   * @param recipientSize     the recipient size
   * @param recipientUsername the recipient username
   * @param messageSize       the message size
   * @param message           the message
   */
  public DirectMessage(Integer usernameSize, byte[] username,
      Integer recipientSize, byte[] recipientUsername, Integer messageSize, byte[] message) {
    super(MessageIdentifier.DIRECT_MESSAGE, usernameSize, username);
    this.recipientSize = recipientSize;
    this.recipientUsername = recipientUsername;
    this.messageSize = messageSize;
    this.message = message;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("DirectMessage{");
    sb.append("recipientSize=").append(recipientSize);
    sb.append(", recipientUsername=").append(Arrays.toString(recipientUsername));
    sb.append(", messageSize=").append(messageSize);
    sb.append(", message=").append(Arrays.toString(message));
    sb.append('}');
    return sb.toString();
  }

  /**
   * Gets message size.
   *
   * @return the message size
   */
  public Integer getMessageSize() {
    return messageSize;
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
    if (!(object instanceof DirectMessage)) {
      return false;
    }
    if (!super.equals(object)) {
      return false;
    }
    DirectMessage that = (DirectMessage) object;
    return Objects.equals(getRecipientSize(), that.getRecipientSize()) &&
        Arrays.equals(getRecipientUsername(), that.getRecipientUsername()) &&
        Objects.equals(getMessageSize(), that.getMessageSize()) &&
        Arrays.equals(getMessage(), that.getMessage());
  }

  /**
   * Hash method.
   *
   * @return int hash value
   */
  @Override
  public int hashCode() {
    int result = Objects.hash(super.hashCode(), getRecipientSize(), getMessageSize());
    result = 31 * result + Arrays.hashCode(getRecipientUsername());
    result = 31 * result + Arrays.hashCode(getMessage());
    return result;
  }

  /**
   * Get message byte [ ].
   *
   * @return the byte [ ]
   */
  public byte[] getMessage() {
    return message;
  }

}
