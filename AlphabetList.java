//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Alphabet List
// Files: AlphabetList.java, AlphabetListTester.java, LinkedCart.java,
// Cart.java, SortedListADT.java
// Course: CS 300, Spring 2020
//
// Author: Matt Thompson
// Email: mathompson23@wisc.edu
// Lecturer's Name: Hobbes LeGault
//
//////////// PAIR PROGRAMMING (MAY SKIP WHEN WORKING INDIVIDUALLY) ////////////
//
// Partner Name: (name of your pair programming partner)
// Partner Email: (email address of your programming partner)
// Partner Lecturer's Name: (name of your partner's lecturer)
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// ___ Write-up states that pair programming is allowed for this assignment.
// ___ We have both read and understood the course Pair Programming Policy.
// ___ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Students who get help from sources other than their partner and the course
// staff must fully acknowledge and credit those sources here. If you did not
// receive any help of any kind from outside sources, explicitly indicate NONE
// next to each of the labels below.
//
// Persons: NONE
// Sources: - https://stackoverflow.com/questions/25392047/deleting-first-node
// -in-doubly-linked-list
// - https://stackoverflow.com/questions/26553889/comparing-2-strings-by-ascii
// -values-in-java
//
///////////////////////////////////////////////////////////////////////////////

/**
 * This class creates a utilizes a linked list to create an AlphabetList, a series of LinkedCart's
 * that contains letters in sequential order. Contains the following methods:
 * 
 * - AlphabetList() constructors
 * 
 * - isEmpty()
 * 
 * - size() and capacity()
 * 
 * - add() and remove()
 * 
 * - indexOf() and get()
 * 
 * - clear()
 * 
 * - toString()
 * 
 * - readForward() and readBackward()
 * 
 * @author Matt Thompson
 */
public class AlphabetList implements SortedListADT<Cart> {
  private static final Cart MIN_CART = new Cart("A"); // The smallest cart that
  // can be added to this sorted list
  private static final Cart MAX_CART = new Cart("Z"); // The largest cart that
  // can be added to this sorted list
  private LinkedCart head; // Head of this doubly linked list
  private LinkedCart tail; // Tail of this doubly linked list
  private int size; // Size of this list
  private int capacity; // Maximum number of carts which can be stored in this list

  /**
   * Creates an empty doubly linked list of carts with a capacity equals to 26
   */
  AlphabetList() {
    this.head = null;
    this.tail = null;
    this.size = 0;
    this.capacity = 26;
  }

  /**
   * Creates an empty doubly linked list of carts with a given capacity
   * 
   * @param capacity Maximum number of carts to be connected in this list of carts
   * @throws IllegalArgumentException if capacity is zero or negative
   */
  AlphabetList(int capacity) {
    if (capacity <= 0) {
      throw new IllegalArgumentException(
          "The capacity of this list must be a non-zero a positive integer.");
    }

    this.head = null;
    this.tail = null;
    this.size = 0;
    this.capacity = capacity;
  }

  /**
   * Checks whether this list is empty.
   * 
   * @return true if the list is empty, false otherwise
   */
  @Override
  public boolean isEmpty() {
    if (size == 0) {
      return true;
    }

    return false;
  }

  /**
   * Returns the size of this sorted list
   * 
   * @return the number of elements stored in this sorted list
   */
  @Override
  public int size() {
    return this.size;
  }

  /**
   * Returns the capacity of this sorted list
   * 
   * @return the capacity of this list
   */
  public int capacity() {
    return this.capacity;
  }

  /**
   * Adds a new cart to this sorted list
   * 
   * @param newCart New cart to add to this list
   * @throws IllegalArgumentException if newCart does not carry one upper-case letter in the range
   *                                  "A"-"Z" or if this list contains already a cart carrying the
   *                                  same letter
   * @throws IllegalStateException    if this list reached its capacity
   */
  public void add(Cart newCart) {
    LinkedCart addedCart = new LinkedCart(newCart, null, null); // Cart to be added
    LinkedCart current = head; // Temporary cart to iterate through list
    int ascii = newCart.toString().charAt(0); // ASCII value to look for cart's cargo
    int min = MIN_CART.getCargo().charAt(0);
    int max = MAX_CART.getCargo().charAt(0);

    // If newCart's value is not a letter between A-Z (capital)
    if (ascii < min || ascii > max) {
      throw new IllegalArgumentException(
          "Can only add carts carrying one upper-case alphabetic letter in the range A .. Z.");
    }

    // If size is more or equal to the capacity
    if (size >= capacity) {
      throw new IllegalStateException("This list is full. Cannot add another cart.");
    }

    // If size is 0, add the cart
    if (size == 0) {
      addedCart = new LinkedCart(newCart);
      this.head = addedCart;
      this.tail = addedCart;
      this.size++;

      return;
    }

    // While loop that iterates through the list
    while (current != null) {
      // If the letters of the cart are the same, throw exception
      if (newCart.compareTo(current.getCart()) == 0) {
        throw new IllegalArgumentException("Cannot duplicate letters or carts in this list.");
      } else if (newCart.compareTo(current.getCart()) < 0) { // If letter is less than next one
        // If previous is null, make the new cart the head
        if (current.getPrevious() == null) {
          this.head = addedCart;
          addedCart.setNext(current);
          addedCart.getNext().setPrevious(addedCart);
          size++;

          return;
        } else { // If cart is in between two carts
          addedCart.setPrevious(current.getPrevious());
          addedCart.setNext(current);
          addedCart.getNext().setPrevious(addedCart);
          addedCart.getPrevious().setNext(addedCart);
          size++;

          return;
        }
      } else {
        // If it is the last cart in the list, set it to be the tail
        if (current.getNext() == null) {
          addedCart.setPrevious(current);
          addedCart.getPrevious().setNext(addedCart);
          this.tail = addedCart;
          size++;

          return;
        } else { // If cart carries cargo bigger than previous letter, move to the next one
          current = current.getNext();
          continue;
        }
      }
    }

  }

  /**
   * Returns the index of the cart carrying data within this list
   * 
   * @param findCart Cart to find in this list
   * @return the index of findCart within this list or -1 if this list does not contain that cart
   */
  @Override
  public int indexOf(Cart findCart) {
    LinkedCart current = head; // Iterates through list
    int index = 0; // Index of cart

    // While loop iterates through list. Keeps incrementing index until cart is equal to current
    // cart, then returns the index.
    while (current != null) {
      if (current.getCart() == findCart) {
        return index;
      }
      current = current.getNext();
      index++;
    }

    return -1; // If nothing is found/error
  }

  /**
   * Removes all the carts from this list. This list must be empty after this method returns
   */
  @Override
  public void clear() {
    this.head = null; // Sets head to null
    this.tail = null; // Sets tail to null
    this.size = 0; // Sets list to size 0

    return;
  }

  /**
   * Returns and removes the cart from this sorted list at the given index position
   * 
   * @param index The index of the cart to be removed
   * @return The removed cart
   * @throws IndexOutOfBoundsException if index is less than 0 or is greater or equal to size()
   */
  @Override
  public Cart remove(int index) {
    // If index is less than 0 or greater or equal to size, throws IndexOutOfBoundsException
    if (index < 0 || index >= this.size) {
      throw new IndexOutOfBoundsException("Invalid index.");
    }

    Cart returned = new Cart(""); // Cart to be returned
    LinkedCart current = head; // Iterates through list
    int indexCart = 0; // Keeps track of the index in the list, compared to index

    // While loops that iterates through the list
    while (current != null) {
      if (indexCart == index) { // If the cart with specified index is found
        if (current.getPrevious() == null && current.getNext() == null) { // If there is only one
                                                                          // cart in the list
          returned = current.getCart();
          this.head = null; // Sets head to null
          this.tail = null; // Sets tail to null
          this.size--;

          return returned;
        }

        // If the removed LinkedCart is the head
        if (current.getPrevious() == null) {
          returned = current.getCart();
          current.getNext().setPrevious(null); // Sets next item's previous to be null
          this.head = current.getNext(); // Sets the next item in list to be the head
          this.size--;

          return returned;
        }

        // If the removed LinkedCart is the tail
        if (current.getNext() == null) {
          returned = current.getCart();
          current.getPrevious().setNext(null); // Sets the previous item's next to null
          this.tail = current.getPrevious(); // Sets the previous to be the tail
          this.size--;

          return returned;
        }

        // If removed cart is between two carts
        returned = current.getCart();
        current.getNext().setPrevious(current.getPrevious()); // Sets the next's previous to the
                                                              // current's previous
        current.getPrevious().setNext(current.getNext()); // Sets the previous' next to be the
                                                          // current's next
        this.size--;

        return returned;
      } else { // Move onto next element
        current = current.getNext();
        indexCart++;
      }
    }

    return null;
  }

  /**
   * Returns the cart at position index of this list without removing it
   * 
   * @param index The index of the cart to return
   * @return The cart of this sorted list at the given index
   * @throws IndexOutOfBoundsException if index is less than 0 or is greater or equal to size()
   */
  @Override
  public Cart get(int index) {
    Cart returned = new Cart(""); // Cart to be returned
    LinkedCart current = head; // Iterates through list
    int indexCart = 0; // Index of the cart, compared to given index

    // Loops through list, until the indexCart matches index. Gets the cart and returns it.
    // Otherwise, moves to next item and increments indexCart.
    while (current != null) {
      if (indexCart == index) {
        returned = current.getCart();
        return returned;
      }
      current = current.getNext();
      indexCart++;
    }

    return null;
  }

  /**
   * Returns a String representation of this list
   * 
   * @return a String representation of the list
   */
  public String toString() {
    String string = "This list contains " + size + " cart(s)";

    if (size == 0) { // If size is zero
      return string;
    }

    string += ": [ ";
    LinkedCart currentCart = head;

    // Gets the cargo of all the cart's and puts them into the list
    while (currentCart != null) {
      string += currentCart.getCart().toString() + " ";
      currentCart = currentCart.getNext();
    }

    string += "]";

    return string;
  }

  /**
   * Reads the contents of this list in the forward direction starting from its head
   * 
   * @return a String representation of the contents of this list read in the forward direction or
   *         an empty string if this list is empty
   */
  public String readForward() {
    String forward = ""; // String to be returned
    LinkedCart current = head; // Sets current to be the head

    // Loops through list, adding the current's cargo to the string
    while (current != null) {
      forward += current.getCart().getCargo();
      current = current.getNext(); // Moves forward
    }

    return forward;
  }

  /**
   * Reads the contents of this list in the backward direction starting from its tail
   * 
   * @return a String representation of the contents of this list read in the backward direction or
   *         an empty string if this list is empty
   */
  public String readBackward() {
    String backward = ""; // String to be returned
    LinkedCart current = tail; // Sets current to start at tail

    // Loops through list, adding the current's cargo to the string
    while (current != null) {
      backward += current.getCart().getCargo();
      current = current.getPrevious(); // Moves backward
    }

    return backward;
  }
}
