package com.shop.dao;

import com.shop.exception.ShopFileException;
import com.shop.exception.ShopTechnicalException;
import java.io.*;
import java.util.Scanner;
import java.util.regex.*;

/** FileEdit class - allows manipulation of any file in the resources folder. */

class FileEdit {

/** Method for adding a new line of content to text files: */
    static void write(String fileName, String newContent) throws ShopTechnicalException {

//      Defining a String for the file path according to the given fileName:
        String resourceFile = "./src/main/resources/" + fileName;

        try {
//          Writing the given content to the given file:
            BufferedWriter bufferedwriter = new BufferedWriter(new FileWriter(resourceFile, true));
            //Creating a new line before writing:
            bufferedwriter.newLine();
            bufferedwriter.write(String.valueOf(newContent));
//          Closing the output stream:
            bufferedwriter.close();

//      If file doesn't exist:
        } catch (IOException e) {
            throw new ShopFileException("File not found", e);
        }
    }

/** Method for reading a file and returning a line of text matching a regex pattern: */
    static String findLine(String filename, String regex) {

//      Defining a String for the resource file path according to the given fileName:
        String resourceFile = "./src/main/resources/"+filename;
//      Initializing a String where the data will be stored:
        String lineData = null;

        try {
//          Reading content from the given file:
            BufferedReader bufferedReader = new BufferedReader(new FileReader(resourceFile));
//          Defining regex pattern from the corresponding method parameter (can be empty):
            Pattern pattern = Pattern.compile(regex);

//          Defining regex matcher (text to be searched) as the current line being read:
            while ((lineData = bufferedReader.readLine()) != null) {
                Matcher matcher = pattern.matcher(lineData);
//              If match is found, line is returned:
                if (matcher.find()) {
                    return lineData;
                }
            }
//          Closing the input stream:
            bufferedReader.close();

//      If the file can't be read:
        } catch (IOException e) {
            e.printStackTrace();
        }
//      The method's end result - a line of data:
        return lineData;

    }

/** Method for targeting a line of data from a text and replacing any part of its content: */
    static void replace(String filename, String identifier, String oldData, String newData) throws NullPointerException {

        try {
//          Reading the resource file and storing its content in a string:
//          Pointing to the file to be read:
            File resourceFile = new File("./src/main/resources/" + filename);
//          Initializing a way to read the file:
            Scanner resourceScanner = new Scanner(resourceFile);
//          Temporarily converting text content to a String:
            StringBuilder contentBuilder = new StringBuilder();
            while (resourceScanner.hasNextLine()) {
                contentBuilder.append(resourceScanner.nextLine() + System.lineSeparator());
            }
            String resourceFileContent = contentBuilder.toString();
//          The file scanner can be closed now:
            resourceScanner.close();

//          Finding the line of text containing the given identifier:
            String targetLine = FileEdit.findLine(filename,"\\b" + identifier + "\\b");

//          Updating the target line of data with the new data:
            String newLine = targetLine.replaceAll(oldData, newData);

//          Updating the file content string with the new data:
            String newFileContent = resourceFileContent.replace(targetLine, newLine);
//          Updating the resource file with the new content:
//          Initializing a way to overwrite the file's content:
            BufferedWriter bufferedwriter = new BufferedWriter(new FileWriter(resourceFile, false));
            bufferedwriter.write(newFileContent);
//          Closing the output stream:
            bufferedwriter.close();

//      Managing possible exceptions:
        } catch (IOException e) {
            System.out.println("File not found.");
        } catch (NullPointerException e) {
            System.out.println("Identifier returned no results.");
        }

    }
}