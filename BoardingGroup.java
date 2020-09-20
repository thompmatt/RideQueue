//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: The Badger Coaster
// Files: BoardingGroup.java, RideQueue.java, QueueADT.java, BGNode.java,
// ThemeParkApp.java
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
// Sources:
// - https://stackoverflow.com/questions/40912280/dequeue-and-enqueue-methods
// -in-queue-implementation
//
///////////////////////////////////////////////////////////////////////////////

/**
 * This class creates a BoardingGroup object, which contains a group name, size, and VIP status.
 * 
 * @author Matt Thompson
 */

public class BoardingGroup {
  private String groupName; // Group's name
  private int groupSize; // Group's size
  private boolean isVIP; // Determines if group is a VIP group or not

  /**
   * Constructs a BoardingGroup, assigning the group's name and size to the specified values, and
   * sets isVIP to false by default.
   * 
   * @param name The name of the group
   * @param size Amount of people in the group
   */
  public BoardingGroup(String name, int size) {
    this.groupName = name;
    this.groupSize = size;
    this.isVIP = false;
  }

  /**
   * Getter method that returns the group's name.
   * 
   * @return The name of the group.
   */
  public String getGroupName() {
    return this.groupName;
  }

  /**
   * Getter method that returns the group's size.
   * 
   * @return The size of the group.
   */
  public int getGroupSize() {
    return this.groupSize;
  }

  /**
   * Getter method that returns the group's VIP condition.
   * 
   * @return true if group is a VIP, false otherwise.
   */
  public boolean getIsVIP() {
    return this.isVIP;
  }

  /**
   * Mutator method that changes the name of the group to the newly specified name.
   * 
   * @param name The new name for the group.
   */
  public void setGroupName(String name) {
    this.groupName = name;
  }

  /**
   * Mutator method that changes the size of the group to the newly specified size.
   * 
   * @param size The new amount of people in the group.
   */
  public void setGroupSize(int size) {
    this.groupSize = size;
  }

  /**
   * Mutator method that sets the group's VIP condition to true always.
   */
  public void setIsVIP() {
    this.isVIP = true;
  }
}
