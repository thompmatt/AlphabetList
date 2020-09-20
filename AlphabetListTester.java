//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Alphabet List Tester
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
 * This class implements unit test methods to check the correctness of LinkedCart and AlphabetList
 * classes defined in the CS300 Spring 2020 - P06 Alphabet Train programming assignment.
 * 
 * @author Matt Thompson
 */
public class AlphabetListTester {

  /**
   * This method should test and make use of at least the LinkedCart constructor, an accessor
   * (getter) method, and a mutator (setter) method defined in the LinkedCart class.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testLinkedCart() {
    Cart cart = new Cart("a"); // Creates current cart
    Cart previous = new Cart("b"); // Creates the previous
    Cart next = new Cart("c"); // Creates the next
    LinkedCart previousCart = new LinkedCart(previous);
    LinkedCart nextCart = new LinkedCart(next);
    LinkedCart linkedCart = new LinkedCart(cart, previousCart, nextCart); // Calls constructor

    // Test 1: LinkedCart constructor
    if (linkedCart.getCart().toString() != "a") {
      System.out.println("Test 1 Failed");
      return false;
    }

    // Test 2: Getter method
    if (linkedCart.getNext() != nextCart) {
      System.out.println("Test 2 Failed");
      return false;
    }

    // Test 3: Setter method
    Cart newNext = new Cart("d"); // Creates another next cart
    LinkedCart newNextCart = new LinkedCart(newNext);
    linkedCart.setNext(newNextCart); // Sets the new next cart

    if (linkedCart.getNext() != newNextCart) {
      System.out.println("Test 3 Failed");
      return false;
    }

    return true;
  }

  /**
   * This method checks for the correctness of both AlphabetList constructors and the instance
   * method isEmpty() when called on an empty alphabet list object.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testAlphabetListConstructorIsEmpty() {
    AlphabetList list = new AlphabetList();

    // Test 1: Empty list
    if (!list.isEmpty()) {
      System.out.println("Test 1 Failed");
      return false;
    }

    // Test 2: Empty constructor
    if (list.capacity() != 26 || list.size() != 0) {
      System.out.println("Test 2 Failed");
      return false;
    }

    // Test 3: Constructor with capacity
    int capacity = 10;
    AlphabetList list2 = new AlphabetList(capacity);

    if (list2.capacity() != capacity || list2.size() != 0) {
      System.out.println("Test 3 Failed");
      return false;
    }

    return true;
  }

  /**
   * This method checks for the correctness of the AlphabetList(int) constructor when passed a
   * negative int value.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testAlphabetListConstructorBadInput() {
    // Test 1: Negative number
    try {
      int capacity = -2;
      AlphabetList list = new AlphabetList(capacity);
      list.size();
      System.out.println("Test 1 Failed");
      return false;
    } catch (IllegalArgumentException e) {
    }

    // Test 2: Zero
    try {
      int capacity = -0;
      AlphabetList list = new AlphabetList(capacity);
      list.size();
      System.out.println("Test 2 Failed");
      return false;
    } catch (IllegalArgumentException e) {
    }

    return true;
  }


  /**
   * This method checks for the correctness of the AlphabetList.add() method when it is passed bad
   * inputs. This method must consider at least the test scenarios provided in the detailed
   * description of these javadocs. (1) Try adding a null to the list; (2) Try adding a cart which
   * carries a non upper-case alphabet letter, for instance "Hello" or "1" or "a". (3) Try adding a
   * duplicate cart to the list.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testAlphabetListAddBadInput() {
    // Test 1: Null to list
    try {
      AlphabetList alphabet = new AlphabetList();
      Cart cart = new Cart(null);
      alphabet.add(cart);
      System.out.println("Test 1 Failed");
      return false;
    } catch (NullPointerException e) {
    }

    // Test 2: Non-uppercase
    try {
      AlphabetList alphabet = new AlphabetList();
      Cart cart = new Cart("hello");
      alphabet.add(cart);
      System.out.println("Test 2 Failed");
      return false;
    } catch (IllegalArgumentException e) {
    }

    // Test 3: Duplicate cart
    try {
      AlphabetList alphabet = new AlphabetList();
      Cart cart = new Cart("a");
      alphabet.add(cart);
      Cart cartBad = new Cart("a");
      alphabet.add(cartBad);
      System.out.println("Test 3 Failed");
      return false;
    } catch (IllegalArgumentException e) {
    }

    // Test 4: Capacity is maxed
    try {
      AlphabetList alphabet = new AlphabetList(3);
      Cart cart = new Cart("A");
      Cart cart2 = new Cart("B");
      Cart cart3 = new Cart("C");
      Cart cart4 = new Cart("D");
      alphabet.add(cart);
      alphabet.add(cart2);
      alphabet.add(cart3);
      alphabet.add(cart4);
      System.out.println("Test 4 Failed");
      return false;
    } catch (IllegalStateException e) {
    }

    return true;
  }

  /**
   * This method checks for the correctness of the AlphabetList.add() considering at least the test
   * scenarios provided in the detailed description of these javadocs. (1) Try adding a cart to an
   * empty list; (2) Try adding a cart which is expected to be added at the head of a non-empty
   * alphabet list; (3) Try adding a cart which is expected to be added at the end of a non-empty
   * alphabet list; (4) Try adding a cart which is expected to be added at the middle of a non-empty
   * alphabet list. For each of those scenarios, make sure that the size of the list is
   * appropriately updated after a call without errors of the add() method, and that the contents of
   * the list is as expected whatever if list is read in the forward or backward directions. You can
   * also check the correctness of AlphabetList.get(int), AlphabetList.indexOf(Cart), and
   * AlphabetList.size() in this test method.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testAlphabetListAdd() {
    // Test 1: Cart to empty list
    AlphabetList alphabet = new AlphabetList();
    Cart begin = new Cart("F");
    alphabet.add(begin);

    // Checks size
    if (alphabet.size() != 1) {
      System.out.println("Test 1 Size Failed");
      return false;
    }

    // Checks indexOf
    if (alphabet.indexOf(begin) != 0) {
      System.out.println("Test 1 indexOf() Failed");
      return false;
    }

    // Checks if get is equal to the added Cart
    if (alphabet.get(0) != begin) {
      System.out.println("Test 1 get() Failed");
      return false;
    }

    // Checks if the alphabet read forwards is correct
    if (!(alphabet.readForward().equals("F"))) {
      System.out.println("Test 1 readForward() Failed");
      return false;
    }

    // Checks if the alphabet read backwards is correct
    if (!(alphabet.readBackward().equals("F"))) {
      System.out.println("Test 1 readBackwards() Failed");
      return false;
    }

    // Test 2: New cart that will be the head
    Cart newHead = new Cart("B");
    alphabet.add(newHead);

    // Checks size
    if (alphabet.size() != 2) {
      System.out.println("Test 2 Size Failed");
      return false;
    }

    // Checks indexOf for newHead
    if (alphabet.indexOf(newHead) != 0) {
      System.out.println("Test 2 indexOf() for newHead Failed");
      return false;
    }

    // Checks indexOf for begin
    if (alphabet.indexOf(begin) != 1) {
      System.out.println("Test 2 indexOf() for begin Failed");
      return false;
    }

    // Checks if get is equal to the added Cart
    if (alphabet.get(0) != newHead) {
      System.out.println("Test 2 get() Failed");
      return false;
    }

    // Checks if the alphabet read forwards is correct
    if (!(alphabet.readForward().equals("BF"))) {
      System.out.println("Test 2 readForward() Failed");
      return false;
    }

    // Checks if the alphabet read backwards is correct
    if (!(alphabet.readBackward().equals("FB"))) {
      System.out.println("Test 2 readBackwards() Failed");
      return false;
    }

    // Test 3: New cart that will be the last
    Cart newTail = new Cart("M");
    alphabet.add(newTail);

    // Checks size
    if (alphabet.size() != 3) {
      System.out.println("Test 3 Size Failed");
      return false;
    }

    // Checks indexOf for newHead
    if (alphabet.indexOf(newHead) != 0) {
      System.out.println("Test 3 indexOf() for newHead Failed");
      return false;
    }

    // Checks indexOf for begin
    if (alphabet.indexOf(begin) != 1) {
      System.out.println("Test 3 indexOf() for begin Failed");
      return false;
    }

    // Checks indexOf for newTail
    if (alphabet.indexOf(newTail) != 2) {
      System.out.println("Test 3 indexOf() for newTail Failed");
      return false;
    }

    // Checks if get is equal to the added Cart
    if (alphabet.get(1) != begin) {
      System.out.println("Test 3 get() Failed");
      return false;
    }

    // Checks if the alphabet read forwards is correct
    if (!(alphabet.readForward().equals("BFM"))) {
      System.out.println("Test 3 readForward() Failed");
      return false;
    }

    // Checks if the alphabet read backwards is correct
    if (!(alphabet.readBackward().equals("MFB"))) {
      System.out.println("Test 3 readBackwards() Failed");
      return false;
    }

    // Test 4: New cart in between
    Cart middle = new Cart("J");
    alphabet.add(middle);

    // Checks size
    if (alphabet.size() != 4) {
      System.out.println("Test 4 Size Failed");
      return false;
    }

    // Checks indexOf for newHead
    if (alphabet.indexOf(newHead) != 0) {
      System.out.println("Test 4 indexOf() for newHead Failed");
      return false;
    }

    // Checks indexOf for begin
    if (alphabet.indexOf(begin) != 1) {
      System.out.println("Test 4 indexOf() for begin Failed");
      return false;
    }

    // Checks indexOf for middle
    if (alphabet.indexOf(middle) != 2) {
      System.out.println("Test 4 indexOf() for middle Failed");
      return false;
    }

    // Checks indexOf for newTail
    if (alphabet.indexOf(newTail) != 3) {
      System.out.println("Test 4 indexOf() for newTail Failed");
      return false;
    }

    // Checks if get is equal to the added Cart
    if (alphabet.get(2) != middle) {
      System.out.println("Test 4 get() Failed");
      return false;
    }

    // Checks if the alphabet read forwards is correct
    if (!(alphabet.readForward().equals("BFJM"))) {
      System.out.println("Test 4 readForward() Failed");
      return false;
    }

    // Checks if the alphabet read backwards is correct
    if (!(alphabet.readBackward().equals("MJFB"))) {
      System.out.println("Test 4 readBackwards() Failed");
      return false;
    }

    return true;

  }

  /**
   * This method checks for the correctness of the AlphabetList.remove() considering at least the
   * test scenarios provided in the detailed description of these javadocs. (1) Try removing a cart
   * from an empty list or pass a negative index to AlphabetList.remove() method; (2) Try removing a
   * cart (at position index 0) from a list which contains only one cart; (3) Try to remove a cart
   * (position index 0) from a list which contains at least 2 carts; (4) Try to remove a cart from
   * the middle of a non-empty list containing at least 3 carts; (5) Try to remove the cart at the
   * end of a list containing at least two carts. For each of those scenarios, make sure that the
   * size of the list is appropriately updated after a call without errors of the add() method, and
   * that the contents of the list is as expected whatever if list is read in the forward or
   * backward directions.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testAlphabetListRemove() {
    // Test 1: Empty list
    try {
      AlphabetList alphabet = new AlphabetList();
      alphabet.remove(3);
      System.out.println("TEST 1 FAILED");

      return false;
    } catch (IndexOutOfBoundsException e) {
    }

    // Test 2: Negative index
    try {
      AlphabetList alphabet = new AlphabetList();
      alphabet.remove(-2);
      System.out.println("TEST 2 FAILED");

      return false;
    } catch (IndexOutOfBoundsException e) {
    }

    // Test 3: One cart list
    try {
      AlphabetList alphabet = new AlphabetList();
      Cart cart = new Cart("A");
      alphabet.add(cart);
      if (alphabet.remove(0) != cart) {
        System.out.println("TEST 3 FAILED");

        return false;
      }

      if (alphabet.size() != 0) {
        System.out.println("TEST 3 FAILED");

        return false;
      }
    } catch (IndexOutOfBoundsException e) {
      System.out.println("TEST 3 FAILED");

      return false;
    }

    // Test 4: Two cart list, removes first one
    try {
      AlphabetList alphabet = new AlphabetList();
      Cart cart = new Cart("A");
      Cart cart2 = new Cart("M");
      alphabet.add(cart);
      alphabet.add(cart2);

      if (alphabet.remove(0) != cart) {
        System.out.println("TEST 4A FAILED");

        return false;
      }

      if (alphabet.size() != 1) {
        System.out.println("TEST 4B FAILED");

        return false;
      }

      if (!(alphabet.readForward().equals("M"))) {
        System.out.println("TEST 4C FAILED");

        return false;
      }

      if (!(alphabet.readBackward().equals("M"))) {
        System.out.println("TEST 4D FAILED");

        return false;
      }

    } catch (IndexOutOfBoundsException e) {
      System.out.println("TEST 4E FAILED");

      return false;
    }

    // Test 5: Three cart list, removes second one
    try {
      AlphabetList alphabet = new AlphabetList();
      Cart cart = new Cart("A");
      Cart cart2 = new Cart("M");
      Cart cart3 = new Cart("S");
      alphabet.add(cart);
      alphabet.add(cart2);
      alphabet.add(cart3);

      if (alphabet.remove(1) != cart2) {
        System.out.println("TEST 5A FAILED");

        return false;
      }

      if (alphabet.size() != 2) {
        System.out.println("TEST 5B FAILED");

        return false;
      }

      if (!(alphabet.readForward().equals("AS"))) {
        System.out.println("TEST 5C FAILED");

        return false;
      }

      if (!(alphabet.readBackward().equals("SA"))) {
        System.out.println("TEST 5D FAILED");

        return false;
      }

    } catch (IndexOutOfBoundsException e) {
      System.out.println("TEST 5E FAILED");

      return false;
    }

    // Test 6: Three cart list, remove last one
    try {
      AlphabetList alphabet = new AlphabetList();
      Cart cart = new Cart("A");
      Cart cart2 = new Cart("M");
      Cart cart3 = new Cart("S");
      alphabet.add(cart);
      alphabet.add(cart2);
      alphabet.add(cart3);

      if (alphabet.remove(2) != cart3) {
        System.out.println("TEST 6A FAILED");

        return false;
      }

      if (alphabet.size() != 2) {
        System.out.println("TEST 6B FAILED");

        return false;
      }

      if (!(alphabet.readForward().equals("AM"))) {
        System.out.println("TEST 6C FAILED");

        return false;
      }

      if (!(alphabet.readBackward().equals("MA"))) {
        System.out.println("TEST 6D FAILED");

        return false;
      }
    } catch (IndexOutOfBoundsException e) {
      System.out.println("TEST 6E FAILED");

      return false;
    }
    return true;
  }


  /**
   * This method calls all the test methods defined and implemented in your AlphabetListTester
   * class.
   * 
   * @return true if all the test methods defined in this class pass, and false otherwise.
   */
  public static boolean runAllTests() {
    // Tests the testLinkedCart() method
    if (!testLinkedCart()) {
      System.out.println("testLinkedCart() failed!");
      return false;
    }
    System.out.println("testLinkedCart() passed!");

    // Tests the testAlphabetListConstructorIsEmpty() method
    if (!testAlphabetListConstructorIsEmpty()) {
      System.out.println("testAlphabetListConstructorIsEmpty() failed!");
      return false;
    }
    System.out.println("testAlphabetListConstructorIsEmpty() passed!");

    // Tests the testAlphabetListConstructorBadInput() method
    if (!testAlphabetListConstructorBadInput()) {
      System.out.println("testAlphabetListConstructorBadInput() failed!");
      return false;
    }
    System.out.println("testAlphabetListConstructorBadInput() passed!");

    // Tests the testAlphabetListAddBadInput() method
    if (!testAlphabetListAddBadInput()) {
      System.out.println("testAlphabetListAddBadInput() failed!");
      return false;
    }
    System.out.println("testAlphabetListAddBadInput() passed!");

    // Tests the testAlphabetListAdd() method
    if (!testAlphabetListAdd()) {
      System.out.println("testAlphabetListAdd() failed!");
      return false;
    }
    System.out.println("testAlphabetListAdd() passed!");

    // Tests the testAlphabetListRemove() method
    if (!testAlphabetListRemove()) {
      System.out.println("testAlphabetListRemove() failed!");
      return false;
    }
    System.out.println("testAlphabetListRemove() passed!");

    System.out.println("All Tests Passed!");

    return true;
  }

  /**
   * Driver method defined in this AlphabetListTester class
   * 
   * @param args input arguments if any.
   */
  public static void main(String[] args) {
    runAllTests(); // Runs all tests
    
    // Checks if list matches list given in the PDF file
    AlphabetList letters = new AlphabetList();
    System.out.println(letters);
    letters.add(new Cart("D"));
    System.out.println(letters);
    letters.add(new Cart("B"));
    System.out.println(letters);
    letters.add(new Cart("C"));
    System.out.println(letters);
    letters.add(new Cart("F"));
    System.out.println(letters);
    letters.add(new Cart("A"));
    System.out.println(letters);
    letters.add(new Cart("Z"));
    System.out.println(letters);
    letters.add(new Cart("E"));
    System.out.println(letters);
    System.out.println("Read Forward: " + letters.readForward());
    System.out.println("Read Backward: " + letters.readBackward());
    letters.remove(0);
    System.out.println(letters);
    letters.remove(letters.size() - 1);
    System.out.println(letters);
    letters.remove(3);
    System.out.println(letters);
    System.out.println("Read Forward: " + letters.readForward());
    System.out.println("Read Backward: " + letters.readBackward());
    letters.clear();
    System.out.println(letters);
    System.out.println("Read Forward: " + letters.readForward());
    System.out.println("Read Backward: " + letters.readBackward());
  }
}
