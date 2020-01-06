import Protocol.Message;
import Protocol.Requests.QueryUsersMessage;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After; 

/** 
* QueryUsersMessage Tester. 
* 
* @author <Authors name> 
* @since <pre>Nov 25, 2019</pre> 
* @version 1.0 
*/ 
public class QueryUsersMessageTest {
  byte[] username = "name".getBytes();
  QueryUsersMessage message;

@Before
public void before() throws Exception {

} 

@After
public void after() throws Exception {
  message = new QueryUsersMessage(username.length,username);
}
  /**
   *
   * Method: toString()
   *
   */
  @Test
  public void testToString() throws Exception {
//TODO: Test goes here...
    QueryUsersMessage message = new QueryUsersMessage(username.length,username);
    final StringBuilder sb = new StringBuilder("QueryUsersMessage{");
    sb.append("usernameSize=").append(message.getUsernameSize());
    sb.append(", username=").append(Arrays.toString(message.getUsername()));
    sb.append('}');
    Assert.assertTrue(message.toString().equals(sb.toString()));
  }


} 
