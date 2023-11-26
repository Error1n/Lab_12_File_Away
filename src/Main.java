import java.io.File;  // Import the File class
import java.io.FileWriter;
import java.io.IOException;  // Import the IOException class to handle errors
import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import static java.nio.file.StandardOpenOption.CREATE;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String message = "";
        String fileName = "";
        ArrayList<String> recs = new ArrayList<>();
        boolean done = false;
        int characterLength = 0;
        int lines = 0;


        fileName = SafeInput.getNonZeroLenString(scan, "Enter the name of your file");

        try {
            FileWriter myWriter = new FileWriter(fileName + ".txt");

            /*
            recs.add("Sample data for lab 12 file away.");
            lines++;
            recs.add("Sample data Line 2.");
            lines++;
            recs.add("Sample data Line 3.");
            lines++;
            recs.add("Sample data Line 4.");
            lines++;
            recs.add("Sample data Line 5.");
            lines++;

             */




            do
            {
                message = SafeInput.getNonZeroLenString(scan, "Enter text for your file");
                recs.add(message);
                lines++;

                done = SafeInput.getYNConfirm(scan, "Are you finished typing?");
            }while(!done);



            for (String line : recs) {
                myWriter.write(line + "\n");
            }

            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e)
        {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }




        // display part


            JFileChooser chooser = new JFileChooser();
            File selectedFile;
            String rec = "";

            try {

                File workingDirectory = new File(System.getProperty("user.dir"));
                chooser.setCurrentDirectory(workingDirectory);

                if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    selectedFile = chooser.getSelectedFile();
                    Path file = selectedFile.toPath();

                    InputStream in =
                            new BufferedInputStream(Files.newInputStream(file, CREATE));
                    BufferedReader reader =
                            new BufferedReader(new InputStreamReader(in));


                    int line = 0;
                    while (reader.ready())
                    {
                        rec = reader.readLine();
                        line++;

                        System.out.printf("\nLine %4d %-60s ", line, rec);

                        int spaceCount = 0;
                        for (char c : rec.toCharArray()) {
                            if (c == ' ') {
                                spaceCount++;
                            }
                        }
                        int wordCount = spaceCount + 1;
                        characterLength += rec.length();

                        System.out.println("The name of your text file is: " + fileName);
                        System.out.println("The number of lines in your text file is: " + lines);
                        System.out.println("The number of words in your text file is: 23" + wordCount);
                        System.out.println("The number of characters in your text file is: " + characterLength);
                    }
                    reader.close();
                    System.out.println("\n\nData file read!");


                } else  // User closed the chooser without selecting a file
                {
                    System.out.println("You didn't select a file. Exiting program.\nRun the program again and select a file.");
                }
            } catch (FileNotFoundException e) {
                System.out.println("File not found!!!");
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }




        }
    }

