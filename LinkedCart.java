//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Linked Cart
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
 * This class creates a LinkedCart object, which is the object inside the linked list for the
 * AlphabetList class. Contains the following methods:
 * 
 * - LinkedCart constructors
 * 
 * - getCart()
 * 
 * - getPrevious() and setPrevious()
 * 
 * - getNext() and setNext()
 * 
 * @author Matt Thompson
 */
public class LinkedCart {
  private final Cart CART; // Data field of this linked Cart
  private LinkedCart previous; // Reference to the previous linked cart in a list of carts
  private LinkedCart next; // Reference to the next linked cart in a list of carts

  /**
   * Creates a new LinkedCart object with specific data cart and null for both previous and next
   * linked carts
   * 
   * @param cart Data of this specific linked cart
   */
  LinkedCart(Cart cart) {
    this.CART = cart;
    this.previous = null;
    this.next = null;
  }

  /**
   * Creates a new LinkedCart object with specific data cart and null for both previous and next
   * linked carts
   * 
   * @param cart     Data of this specific linked cart
   * @param previous Reference to the previous linked cart
   * @param next     Reference to the next linked cart
   */
  LinkedCart(Cart cart, LinkedCart previous, LinkedCart next) {
    this.CART = cart;
    this.previous = previous;
    this.next = next;
  }

  /**
   * Returns a reference to the data cart of this linked cart
   */
  public Cart getCart() {
    return this.CART;
  }

  /**
   * Returns a reference to the previous LinkedCart attached to this linked cart
   */
  public LinkedCart getPrevious() {
    return this.previous;
  }

  /**
   * Sets the previous LinkedCart attached to this LinkedCart
   * 
   * @param previous The previous to set
   */
  public void setPrevious(LinkedCart previous) {
    this.previous = previous;
  }

  /**
   * Returns a reference to the next LinkedCart attached to this linked cart
   */
  public LinkedCart getNext() {
    return this.next;
  }

  /**
   * Sets the next LinkedCart attached to this LinkedCart
   * 
   * @param next The next to set
   */
  public void setNext(LinkedCart next) {
    this.next = next;
  }
}
