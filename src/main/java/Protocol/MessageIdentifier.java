package Protocol;

/**
 * The enum Message identifier.
 */
public enum MessageIdentifier {
  /**
   * Connect message message identifier.
   */
  CONNECT_MESSAGE(19),
  /**
   * Connect response message identifier.
   */
  CONNECT_RESPONSE(20),
  /**
   * Disconnect message message identifier.
   */
  DISCONNECT_MESSAGE(21),
  /**
   * Query connected users message identifier.
   */
  QUERY_CONNECTED_USERS(22),
  /**
   * Query user response message identifier.
   */
  QUERY_USER_RESPONSE(23),
  /**
   * Broadcast message message identifier.
   */
  BROADCAST_MESSAGE(24),
  /**
   * Direct message message identifier.
   */
  DIRECT_MESSAGE(25),
  /**
   * Failed message message identifier.
   */
  FAILED_MESSAGE(26),
  /**
   * Send insult message identifier.
   */
  SEND_INSULT(27),

  /**
   * Disconnect response message identifier.
   */
  DISCONNECT_RESPONSE(28);

  /**
   * The Id.
   */
  private final Integer id;

  MessageIdentifier(Integer id) {
    this.id = id;
  }

}

