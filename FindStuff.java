import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This program finds a character in a string 
 * and the min in a set of numbers.
 *
 * @author Ioana Marinescu
 * @version 1.0
 * @since 2024-05-07
 */

public final class FindStuff {
  /** Constructor for the ReverseString class. */
  private FindStuff() {
    // empty constructor
  }

  /**
   * Reads input from a file, reverses string, and write to an output file.
   *
   * @param args Command line arguments (unused).
   */
  public static void main(final String[] args) throws FileNotFoundException {
    try {
      // File paths
      String in = "Assign/Assign-03/Assign-03-Java-FindStuff/input.txt";
      String out = "Assign/Assign-03/Assign-03-Java-FindStuff/output.txt";
      File inputFile = new File(in);
      File outputFile = new File(out);

      // Initializing scanner and writer
      Scanner scanner = new Scanner(inputFile);
      FileWriter writer = new FileWriter(outputFile);

      // variables
      String firstLine;
      String secondLine;
      int index;
      int min;

      // loops until there is nothing to read
      while (scanner.hasNextLine()) {
        // reads a single line
        firstLine = scanner.nextLine();
        secondLine = scanner.nextLine();

        // call the recursive function for finding index
        String[] test = firstLine.split(",");
        index = findIndex(test[0], firstLine.charAt(firstLine.length() - 1), 0);

        // split input by spaces
        String[] secondLineArrayStr = secondLine.split(" ");
        // parse each element from String to int
        int[] secondLineArray = new int[secondLineArrayStr.length];
        for (int i = 0; i < secondLineArrayStr.length; i++) {
          secondLineArray[i] = Integer.parseInt(secondLineArrayStr[i]);
        }
        // second function call
        min = findMin(secondLineArray, 0, secondLineArray[0]);

        // write to file
        writer.write("Index of first instance: " + index + "\n");
        writer.write("Min: " + min + "\n");
      }
      // message to console confirming the process finished with no errors
      System.out.println("Successfully completed task!");

      // close scanner and writer
      scanner.close();
      writer.close();

      // file could not be found
    } catch (FileNotFoundException e) {
      System.out.println("The file does not exists.");
    } catch (InputMismatchException e) {
      System.out.println("Please enter an integer value.");
    } catch (Exception e) {
      System.out.println("Your input is wrong.");
    }
  }

  /**
   * Recursive function that returns index of the fist instance of a character.
   *
   * @param someString input string that will searched for ch
   * @param ch character to search through someString for
   * @param n index value
   * @return int n if ch in someString, else -1
   */
  public static int findIndex(
      // params
      final String someString,
      final char ch,
      final int n) {
    // Check if n is out of bounds
    if (n >= someString.length()) {
      return -1; // Base case: character not found in the string
    } else if (someString.charAt(n) == ch) {
      return n; // Base case: character found at index n
    } else {
      // Recursive case: move to the next character
      return findIndex(someString, ch, n + 1);
    }
  }

  /**
   * Finds the minimum value in an integer array recursively.
   *
   * @param numArray the integer array in which to find the minimum value
   * @param n the current index being evaluated
   * @param min the current minimum value found so far
   * @return the minimum value in the array
   */
  public static int findMin(final int[] numArray, final int n, int min) {
    // base case, if n longer that array
    if (n < numArray.length - 1) {
      // element at index n is less than min
      if (numArray[n] < min) {
        min = numArray[n];
      }
      // recursively call findMin() to process the next element in the array
      return findMin(numArray, n + 1, min);
    }
    // return the minimum value
    return min;
  }
}
