package Protocol.Requests;

import Protocol.MessageIdentifier;
import Protocol.Request;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * The type Send insult.
 */
public class SendInsultMessage extends Request {

  /**
   * The Recipient size.
   */
  private int recipientSize;
  /**
   * The Recipient name.
   */
  private byte[] recipientName;
  /**
   * The Msg size.
   */
  private Integer msgSize;
  /**
   * The Msg.
   */
  private byte[] msg;


  public SendInsultMessage(Integer senderSize, byte[] senderUsername,
      int recipientSize, byte[] recipientName, Integer msgSize, byte[] msg) {
    super(MessageIdentifier.SEND_INSULT, senderSize, senderUsername);
    this.recipientSize = recipientSize;
    this.recipientName = recipientName;
    this.msgSize = msgSize;
    this.msg = msg;
  }

  /**
   * Gets recipient size.
   *
   * @return the recipient size
   */
  public int getRecipientSize() {
    return recipientSize;
  }

  /**
   * Get recipient name byte [ ].
   *
   * @return the byte [ ]
   */
  public byte[] getRecipientName() {
    return recipientName;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("SendInsultMessage{");
    sb.append("recipientSize=").append(recipientSize);
    sb.append(", recipientName=").append(Arrays.toString(recipientName));
    sb.append(", msgSize=").append(msgSize);
    sb.append(", msg=").append(Arrays.toString(msg));
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
    if (!(object instanceof SendInsultMessage)) {
      return false;
    }
    if (!super.equals(object)) {
      return false;
    }
    SendInsultMessage that = (SendInsultMessage) object;
    return getRecipientSize() == that.getRecipientSize() &&
        Arrays.equals(getRecipientName(), that.getRecipientName()) &&
        Objects.equals(getMsgSize(), that.getMsgSize()) &&
        Arrays.equals(getMsg(), that.getMsg());
  }

  /**
   * Hash method.
   *
   * @return int hash value
   */
  @Override
  public int hashCode() {
    int result = Objects.hash(super.hashCode(), getRecipientSize(), getMsgSize());
    result = 31 * result + Arrays.hashCode(getRecipientName());
    result = 31 * result + Arrays.hashCode(getMsg());
    return result;
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
   * Get msg byte [ ].
   *
   * @return the byte [ ]
   */
  public byte[] getMsg() {
    return msg;
  }

}
