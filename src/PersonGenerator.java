import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class PersonGenerator {
    public static void main(String[] args) {

        ArrayList <String>recs = new ArrayList<>();
        boolean done = false;
        String id = "";
        String firstName = "";
        String lastName = "";
        String title = "";
        String yearOfBirth = ""; //entry will be converted from an int to String for file writing
        String separator =",";

        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\data.txt");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file.toFile())))
        {

            // Finally can write the file LOL!

            for(String rec : recs)
            {
                writer.write(rec, 0, rec.length());  // stupid syntax for write rec
                // 0 is where to start (1st char) the write
                // rec. length() is how many chars to write (all)
                writer.newLine();  // adds the new line

            }
            writer.close(); // must close the file to seal it and flush buffer
            System.out.println("Data file written!");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}