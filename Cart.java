
/**
 * This class models and implements a cart which carries goods of type String
 *
 */
public class Cart implements Comparable<Cart> {
  private String cargo; // a String representation of the goods carried 
                        // by this cart

  /**
   * Creates a new Cart carrying a given data
   * 
   * @param cargo string representation of the good which are going to be carried by this cart
   */
  public Cart(String cargo) {
    this.cargo = cargo;
  }

  /**
   * Returns String representation of the goods carried by this cart
   * 
   * @return a String representation of the cargo carried by this cart
   */
  @Override
  public String toString() {
    return cargo;
  }

  /**
   * Returns the cargo carried by this cart
   * 
   * @return a the cargo carried by this cart
   */
  public String getCargo() {
    return cargo;
  }

  /**
   * Compares this cart with the specified object for order.
   * 
   * @return a negative integer, zero, or a positive integer as this cart has a cargo which is less
   *         than, equal to, or greater than the specified other cart's cargo.
   */
  @Override
  public int compareTo(Cart other) {
    return cargo.compareTo(other.cargo);
  }

}