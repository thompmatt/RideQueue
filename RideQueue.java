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

import java.util.NoSuchElementException;

/**
 * This class creates a RideQueue, which simulates the queue for the Badger Coaster.
 * 
 * @author Matt Thompson
 */
public class RideQueue implements QueueADT<BoardingGroup> {
  private BGNode front; // Head of the linked list (queue)
  private BGNode back; // Tail of the linked list (queue)
  private int capacity; // Maximum capacity of individuals that can be in the queue
  private int numOfPeople; // Number of people in queue
  private int numOfGroups; // Number of groups in queue

  /**
   * Constructs an empty queue with the designated capacity.
   * 
   * @param capacity The capacity of people to be assigned for the Ride Queue.
   */
  public RideQueue(int capacity) {
    this.capacity = capacity;
    this.numOfPeople = 0;
    this.numOfGroups = 0;
    this.front = null;
    this.back = null;
  }

  /**
   * Determines whether or not this queue is empty.
   * 
   * @return true if numOfPeople or numOfGroups is 0, false otherwise.
   */
  @Override
  public boolean isEmpty() {
    if (this.numOfPeople == 0 || this.numOfGroups == 0) {
      return true;
    }

    return false;
  }

  /**
   * Gives the number of BoardingGroup nodes in this queue.
   * 
   * @return The current number of nodes in this queue.
   */
  @Override
  public int size() {
    return this.numOfGroups;
  }

  /**
   * Adds a newGroup to the queue.
   * 
   * @param newGroup The BoardingGroup to be added to the queue.
   * @throws IllegalStateException if newGroup cannot fit into the queue.
   */
  @Override
  public void enqueue(BoardingGroup newGroup) {
    // If newGroup's size will exceed the capacity of people
    if (newGroup.getGroupSize() + this.numOfPeople >= this.capacity) {
      throw new IllegalStateException(
          "The capacity would be full if this group enters. Cannot enter.");
    }

    // If there are no groups in the queue
    if (isEmpty()) {
      BGNode group = new BGNode(newGroup);
      this.front = group;
      this.back = group;
      this.numOfPeople += newGroup.getGroupSize();
      this.numOfGroups++;
      return;
    }

    // If group is a VIP
    if (newGroup.getIsVIP()) {
      BGNode group = new BGNode(newGroup);
      group.setNext(this.front);
      this.front = group;
      this.numOfGroups++;
      this.numOfPeople += newGroup.getGroupSize();
      return;
    }

    BGNode group = new BGNode(newGroup);
    this.back.setNext(group);
    this.back = group;
    this.numOfPeople += newGroup.getGroupSize();
    this.numOfGroups++;
  }

  /**
   * Returns the BoardingGroup at the front of this queue without removing it.
   * 
   * @return the group at the front of the line.
   * @throws NoSuchElementException If this queue is empty.
   */
  @Override
  public BoardingGroup peek() {
    if (isEmpty()) {
      throw new NoSuchElementException("The queue is empty, no next group.");
    }

    return this.front.getGroup();
  }

  /**
   * Returns the BoardingGroup at the front of this queue and removes it.
   * 
   * @return The BoardingGroup that was removed from this queue.
   * @throws NoSuchElementException If this queue is empty.
   */
  @Override
  public BoardingGroup dequeue() {
    // If there are no groups in queue
    if (isEmpty()) {
      throw new NoSuchElementException("Cannot remove, the queue is empty.");
    }

    // If there is only one group in the queue.
    if (this.front == this.back) {
      BoardingGroup group = this.front.getGroup();
      this.numOfGroups--;
      this.numOfPeople = 0;
      this.front = null;
      this.back = null;
      return group;
    }

    BoardingGroup group = this.front.getGroup();
    this.numOfGroups--;
    this.numOfPeople -= this.front.getGroup().getGroupSize();
    this.front = this.front.getNext();
    return group;
  }

  /**
   * Removes all groups from this queue. This queue will become empty.
   */
  @Override
  public void clear() {
    this.front = null;
    this.back = null;
    this.numOfGroups = 0;
    this.numOfPeople = 0;
  }

  /**
   * Returns a string representation of this queue.
   * 
   * @return This queue represented as a string.
   */
  public String toString() {
    String s = "Number of People in Queue: " + numOfPeople + "\n";
    s += "Number of Groups in Queue: " + numOfGroups + "\n";
    s += "Group Names in Queue: ";
    BGNode current = front;

    while (current != null) {
      String groupName = current.getGroup().getGroupName();
      s += groupName + " ";
      current = current.getNext();
    }

    return s;
  }
}
