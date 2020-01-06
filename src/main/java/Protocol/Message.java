package Protocol;

import java.io.Serializable;
import java.util.Objects;

/**
 * The type Message.
 */
public abstract class Message implements Serializable {

  /**
   * The Identifier.
   */
  private final MessageIdentifier identifier;

  /**
   * Gets identifier.
   *
   * @return the identifier
   */
  public MessageIdentifier getIdentifier() {
    return identifier;
  }

  /**
   * Instantiates a new Message.
   *
   * @param identifier the identifier
   */
  public Message(MessageIdentifier identifier) {
    this.identifier = identifier;
  }

  public boolean isFailedMessage() {
    return identifier == MessageIdentifier.FAILED_MESSAGE;
  }

  public boolean isConnectResponse() {
    return identifier == MessageIdentifier.CONNECT_RESPONSE;
  }

  public boolean isDisconnectResponse() {
    return identifier == MessageIdentifier.DISCONNECT_MESSAGE;
  }

  public boolean isQueryResponse() {
    return identifier == MessageIdentifier.QUERY_USER_RESPONSE;
  }

  public boolean isBroadcastMessage() {
    return identifier == MessageIdentifier.BROADCAST_MESSAGE;
  }

  public boolean isDirectMessage() {
    return identifier == MessageIdentifier.DIRECT_MESSAGE;
  }

  public boolean isSendInsultMessage() {
    return identifier == MessageIdentifier.SEND_INSULT;
  }

  public boolean isConnectMessage() {
    return identifier == MessageIdentifier.CONNECT_MESSAGE;
  }

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
    if (!(object instanceof Message)) {
      return false;
    }
    Message message = (Message) object;
    return getIdentifier() == message.getIdentifier();
  }

  @Override
  public int hashCode() {
    return Objects.hash(getIdentifier());
  }
}
