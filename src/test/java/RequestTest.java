import Protocol.Request;
import Protocol.Requests.ConnectMessage;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After; 

/** 
* Request Tester. 
* 
* @author <Taowen Liu>
* @since <pre>Nov 25, 2019</pre> 
* @version 1.0 
*/ 
public class RequestTest {
  byte[] username = "name".getBytes();
  byte[] username1 = "name1".getBytes();
  Request message;
  Request message1;
  Request message2;
  Request message3;

@Before
public void before() throws Exception {
  message = new ConnectMessage(username.length,username);
  message1 = new ConnectMessage(username.length,username);
  message2 = new ConnectMessage(username1.length,username1);
  message3 = new ConnectMessage(username1.length,username1);
}

@After
public void after() throws Exception { 
} 


/** 
* 
* Method: hashCode() 
* 
*/ 
@Test
public void testHashCode() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: toString() 
* 
*/ 
@Test
public void testToString() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getUsernameSize() 
* 
*/ 
@Test
public void testGetUsernameSize() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getUsername() 
* 
*/ 
@Test
public void testGetUsername() throws Exception { 
//TODO: Test goes here... 
}


  /**
   *
   * Method: equals(Object object)
   *
   */
  @Test
  public void testEquals() throws Exception {
//TODO: Test goes here...
    Assert.assertEquals(message.hashCode(), message.hashCode());
    Assert.assertTrue(message.equals(message));
    Assert.assertTrue(message.equals(message1));
    Assert.assertFalse(message.equals(message2));
    Assert.assertFalse(message.equals(message3));
    Assert.assertFalse(message == null);
  }


} 
