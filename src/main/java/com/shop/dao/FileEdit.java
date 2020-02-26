package com.shop.dao;

import com.shop.exception.ShopFileException;
import com.shop.exception.ShopTechnicalException;
import com.shop.model.Writable;

import java.io.*;
import java.util.Scanner;
import java.util.regex.*;

/** FileEdit class - allows manipulation of any file in the resources folder. */

class FileEdit<T extends Writable> {
    void write(String fileName, T newContent) throws ShopTechnicalException {
        String resourceFile = "./src/main/resources/" + fileName;
        try {
            BufferedWriter bufferedwriter = new BufferedWriter(new FileWriter(resourceFile, true));
            bufferedwriter.newLine();
            bufferedwriter.write(newContent.toDb());
            bufferedwriter.close();

        } catch (IOException e) {
            throw new ShopFileException("File not found", e);
        }
    }

    static String findLine(String filename, String regex) {
        String resourceFile = "./src/main/resources/"+filename;
        String lineData = null;

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(resourceFile));
            Pattern pattern = Pattern.compile(regex);
            while ((lineData = bufferedReader.readLine()) != null) {
                Matcher matcher = pattern.matcher(lineData);
                if (matcher.find()) {
                    return lineData;
                }
            }
            bufferedReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return lineData;

    }

/** Method for targeting a line of data from a text and replacing any part of its content: */
    void replace(String filename, String identifier, String oldData, String newData) throws ShopFileException {
        try {
            File resourceFile = new File("./src/main/resources/" + filename);
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
            throw new ShopFileException("File Not Found", e);
        }

    }
}