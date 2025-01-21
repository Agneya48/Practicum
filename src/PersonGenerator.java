import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class PersonGenerator {
    public static void main(String[] args) {

        ArrayList <String>allPersonRecs = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        boolean done = false;

        String personRec = "";
        String ID = "";
        String firstName = "";
        String lastName = "";
        String title = "";
        String yearOfBirth = ""; /* I prefer using RegEx for date of birth, and thus set it to a String
        getRangedInt will also work if it's ever important to have an int value before
        converting to a String for file writing (like doing calculations before saving)
         */
        String div =",";

        do {
            System.out.println();
            System.out.println("New Person Entry");
            ID = SafeInput.getRegExString(in, "Enter the ID [6 digits]", "\\d{6}");
            firstName = SafeInput.getNonZeroLenString(in, "Enter the first name");
            lastName = SafeInput.getNonZeroLenString(in, "Enter the last name");
            title = SafeInput.getNonZeroLenString(in, "Enter the title");
            yearOfBirth = SafeInput.getRegExString(in, "Enter year of birth [4 digits]", "\\d{4}");

            personRec = ID + div + firstName + div + lastName + div + title + div + yearOfBirth;
            System.out.println();
            System.out.println(personRec);
            System.out.println();

            done = SafeInput.getYNConfirm(in, "Data entry complete? [Y/N]");

            allPersonRecs.add(personRec);

        }while(!done);

        in.close();

        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\personData.txt");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file.toFile())))
        {

            for(String rec : allPersonRecs)
            {
                writer.write(rec, 0, rec.length());
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