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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Driver for RideQueue. Reads commands from a text files and executes them accordingly.
 * 
 * @author Michelle Jensen
 * @author Matt Thompson
 */
public class ThemeParkApp {
  public static void main(String[] args) throws IOException {
    List<String> fileLines = Files.readAllLines(Paths.get("sample.txt"));
    String command = "";
    String[] commandParts;

    // Default queue capacity and ride capacity. Can change values if desired.
    RideQueue coaster = new RideQueue(50);
    int trainCapacity = 24;

    // Process each line in the text file.
    for (int i = 0; i < fileLines.size(); i++) {
      commandParts = fileLines.get(i).split(" ");
      command = commandParts[0].toUpperCase();

      // ENTER Command
      if (command.equals("E")) {
        enter(coaster, commandParts);
      }

      // BREAKDOWN Command
      if (command.equals("B")) {
        breakdown(coaster);
      }

      // PREVIEW Command
      if (command.equals("P")) {
        preview(coaster);
      }

      // RIDE Command
      if (command.equals("R")) {
        ride(coaster, trainCapacity);
      }

      // STATUS Command
      if (command.equals("S")) {
        status(coaster);
      }
    }
  }

  /**
   * Retrieves the status of the RideQueue, prints out all groups in the queue.
   * 
   * @param coaster The RideQueue holding the queue.
   */
  private static void status(RideQueue coaster) {
    System.out.println("Retrieving Status...");
    System.out.println(coaster.toString());
    System.out.println("------------------------------------");
  }

  /**
   * Allows a group to enter into the queue.
   * 
   * @param coaster The RideQueue holding the queue.
   * @param commandParts String array that parses commands
   */
  private static void enter(RideQueue coaster, String[] commandParts) {
    System.out.println("Entering into ride line...");
    String groupName = commandParts[1];
    int groupSize = Integer.parseInt(commandParts[2]);

    BoardingGroup newGroup = new BoardingGroup(groupName, groupSize);

    if (commandParts.length == 4) {
      if (commandParts[3].toUpperCase().equals("V")) {
        newGroup.setIsVIP();
      }
    }

    try {
      coaster.enqueue(newGroup);
      System.out.println(
          groupName + "'s group of " + groupSize + " has entered the line for Badger Coaster.");
    } catch (IllegalStateException e) {
      System.out.println("Cannot fit group of that size into queue.");
    }

    System.out.println("------------------------------------");
  }
  
  /**
   * Breaks down the ride, kicking out all groups and clearing the queue.
   * 
   * @param coaster The RideQueue holding the queue.
   */
  private static void breakdown(RideQueue coaster) {
    System.out.println("Ride Breakdown...");
    System.out.println("The ride has broken down. All " + coaster.size()
        + " group(s) have been removed from the line.");
    coaster.clear();
    System.out.println("------------------------------------");
  }

  /**
   * Retrieves the first group in the queue.
   * 
   * @param coaster The RideQueue holding the queue.
   */
  private static void preview(RideQueue coaster) {
    System.out.println("Previewing the front of the line...");

    try {
      BoardingGroup peeked = coaster.peek();
      int peekedSize = peeked.getGroupSize();
      String peekedName = peeked.getGroupName();
      System.out
          .println(peekedName + "'s group of " + peekedSize + " is at the front of the line.");
    } catch (NoSuchElementException e) {
      System.out.println("Cannot look at a group from an empty queue.");
    }

    System.out.println("------------------------------------");
  }

  /**
   * Allows groups to ride the Badger Coaster, according to the order of the queue.
   * 
   * @param coaster The RideQueue holding the queue.
   * @param trainCapacity Number of people that can fit in train.
   */
  private static void ride(RideQueue coaster, int trainCapacity) {
    System.out.println("Boarding and Running the Ride...");
    int ridingTrain = 0;

    while (!coaster.isEmpty()) {
      BoardingGroup peeked = coaster.peek();
      int peekedSize = peeked.getGroupSize();

      if (ridingTrain + peekedSize > trainCapacity) {
        break;
      }

      try {
        BoardingGroup removed = coaster.dequeue();
        String removedName = removed.getGroupName();
        int removedSize = removed.getGroupSize();

        System.out.println(
            removedName + "'s group of " + removedSize + " has boarded the Badger Coaster train.");

        ridingTrain += removedSize;
      } catch (NoSuchElementException e) {
        System.out.println("Cannot remove a group from an empty queue.");
      }
    }

    if (ridingTrain == 0) {
      System.out.println("There is no one on the train to ride.");
    } else {
      System.out.println("Train of " + ridingTrain + " people has left the ride station.");
    }

    System.out.println("------------------------------------");
  }
}

