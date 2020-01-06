package Protocol.Responses;

import Protocol.IResponse;
import Protocol.Message;
import Protocol.MessageIdentifier;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;
import javafx.util.Pair;

/**
 * The type Query response.
 */
public class QueryResponse extends Message implements IResponse {

  /**
   * The Num of users.
   */
  private Integer numOfUsers;
  /**
   * The Users.
   */
  private List<Pair<Integer, byte[]>> users;

  /**
   * Instantiates a new Query response.
   *
   * @param numOfUsers the num of users
   * @param users      the users
   */
  public QueryResponse(Integer numOfUsers,
      List<Pair<Integer, byte[]>> users) {
    super(MessageIdentifier.QUERY_USER_RESPONSE);
    this.numOfUsers = numOfUsers;
    this.users = users;
  }


  /**
   * Gets users.
   *
   * @return the users
   */
  public List<Pair<Integer, byte[]>> getUsers() {
    return users;
  }


  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("QueryResponse{");
    sb.append("numOfUsers=").append(numOfUsers);
    sb.append(", users=").append(users);
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
    if (!(object instanceof QueryResponse)) {
      return false;
    }
    if (!super.equals(object)) {
      return false;
    }
    QueryResponse that = (QueryResponse) object;
    Comparator<Pair<Integer,byte[]>> comparator = Comparator
        .comparing(p -> (new String(p.getValue())));
    List<Pair<Integer,byte[]>> users = getUsers();
    List<Pair<Integer,byte[]>> usersThat = that.getUsers();
    users.sort(comparator);
    usersThat.sort(comparator);

    return Objects.equals(numOfUsers, that.numOfUsers) &&
        (getUsers().size()==that.getUsers().size() &&
            IntStream.range(0, users.size())
                .allMatch(i -> users.get(i).getKey().equals(that.getUsers().get(i).getKey())
                    && Arrays.equals(getUsers().get(i).getValue(), that.getUsers().get(i).getValue())));
  }

  /**
   * Hash method.
   *
   * @return int hash value
   */
  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), numOfUsers, getUsers());
  }

  @Override
  public Integer getResponseSize() {
    return this.numOfUsers;
  }
}
