import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class PersonReader {
    public static void main(String[] args) {

        JFileChooser chooser = new JFileChooser();
        File selectedFile;
        String lineRecord = "";

        try {

            File workingDirectory = new File(System.getProperty("user.dir"));

            chooser.setCurrentDirectory(workingDirectory);

            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                selectedFile = chooser.getSelectedFile();
                Path file = selectedFile.toPath();

                try (InputStream in = new BufferedInputStream(Files.newInputStream(file));
                     BufferedReader reader = new BufferedReader(new InputStreamReader(in)))
                {
                    int headerWidth = 44;

                    //print the header of the table displaying the read file
                    System.out.println();
                    System.out.printf("%-8s%-12s%-12s%-6s%6s%n", "ID#", "Firstname", "Lastname", "Title", "YOB");
                    for (int i = 0; i < headerWidth; i++) {
                        System.out.print("=");
                    }
                    System.out.println();

                    /*print each line; splits into an array of String objects using the comma as the splitter
                    for easier formatting
                     */
                    while (reader.ready()) {
                        lineRecord = reader.readLine();
                        String[] splitRecs = lineRecord.split(",");
                        // formated line output, make sure width values match those in header
                        System.out.printf("%-8s%-12s%-12s%-6s%6s%n", splitRecs[0], splitRecs[1], splitRecs[2],
                                splitRecs[3], splitRecs[4]);
                    }
                    System.out.println("\n\nData file read!");
                }
            } else  // User closed the chooser without selecting a file
            {
                System.out.println("No file selected!!! ... exiting.\nRun the program again and select a file.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!!!");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
