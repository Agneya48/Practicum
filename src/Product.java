import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Product {
    public static void main(String[] args) {

        ArrayList <String> allProductRecs = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        boolean done = false;

        String productRec = "";
        String ID = "";
        String name = "";
        String description = "";
        double cost = 0.0;
        String div =",";

        do {
            System.out.println();
            System.out.println("New Product Entry");
            ID = SafeInput.getRegExString(in, "Enter the ID [6 digits]", "\\d{6}");
            name = SafeInput.getNonZeroLenString(in, "Enter product name");
            description = SafeInput.getNonZeroLenString(in, "Enter description");
            cost = SafeInput.getDouble(in, "Enter cost");

            productRec = ID + div + name + div + description + div + cost;
            System.out.println();
            System.out.println(productRec);
            System.out.println();

            done = SafeInput.getYNConfirm(in, "Data entry complete? [Y/N]");

            allProductRecs.add(productRec);

        }while(!done);

        in.close();

        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\productTestData.txt");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file.toFile())))
        {

            for(String rec : allProductRecs)
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
