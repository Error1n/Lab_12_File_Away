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

public class Main
{
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        String message = "";
        String fileName = "";
        ArrayList<String> recs = new ArrayList<>();
        boolean done = false;

        fileName = SafeInput.getNonZeroLenString(scan, "Enter the name of your file");

        try
        {
            FileWriter myWriter = new FileWriter(fileName + ".txt");

            do
            {
                message = SafeInput.getNonZeroLenString(scan, "Enter text for your file");
                recs.add(message);

                done = SafeInput.getYNConfirm(scan, "Are you finished typing?");
            }while(!done);
            
            myWriter.write(message);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e)
        {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


    }
}