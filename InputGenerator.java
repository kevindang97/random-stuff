import java.util.*;
import java.io.*;

public class InputGenerator {

  public static void main(String[] args) throws FileNotFoundException {
    // args num min max
    int n = Integer.parseInt(args[0]);
    int min = Integer.parseInt(args[1]);
    int max = Integer.parseInt(args[2]);

    Random rand = new Random();
    PrintWriter output = new PrintWriter("data");

    for (int i = 0; i < n; i++) {
      output.println(rand.nextInt(max - min + 1) + min);
    }

    output.close();
  }
}