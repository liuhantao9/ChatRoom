package Server;

import Protocol.Message;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.concurrent.BlockingQueue;

/**
 * The thread handles user input message.
 */
public class UserInputRequestThread implements Runnable {

  private BlockingQueue<Message> userInput;
  private ObjectInputStream in;
  private boolean stopFlag;

  /**
   * Construct a user input quest thread.
   * @param userInput the blocking queue which stores current user request
   * @param in the input stream from user
   */
  public UserInputRequestThread(BlockingQueue<Message> userInput, ObjectInputStream in) {
    this.userInput = userInput;
    this.in = in;
    this.stopFlag = false;
  }

  /**
   * Runs the thread and put client request into blocking queue
   */
  @Override
  public void run() {
    while (!stopFlag) {
      try {
        Object temp = in.readObject();
        Message m = (Message) temp;
        userInput.add(m);
      } catch (IOException | ClassNotFoundException e) {
        e.printStackTrace();
      }
    }
  }
}
