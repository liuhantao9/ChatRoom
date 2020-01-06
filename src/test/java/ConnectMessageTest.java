import Protocol.Message;
import Protocol.Requests.ConnectMessage;
import Protocol.Requests.QueryUsersMessage;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After; 

/** 
* ConnectMessage Tester. 
* 
* @author <Authors name> 
* @since <pre>Nov 25, 2019</pre> 
* @version 1.0 
*/ 
public class ConnectMessageTest {
  byte[] username = "name".getBytes();
  ConnectMessage message;

@Before
public void before() throws Exception {
  message = new ConnectMessage(username.length,username);
} 

@After
public void after() throws Exception { 
}

  /**
   *
   * Method: toString()
   *
   */
  @Test
  public void testToString() throws Exception {
//TODO: Test goes here...
    final StringBuilder sb = new StringBuilder("ConnectMessage{");
    sb.append("usernameSize=").append(message.getUsernameSize());
    sb.append(", username=").append(Arrays.toString(message.getUsername()));
    sb.append('}');
    Assert.assertTrue(message.toString().equals(sb.toString()));
  }

} 
