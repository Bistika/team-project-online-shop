package com.shop.dao;

        import com.shop.exception.ShopFileException;
        import com.shop.exception.ShopTechnicalException;
        import com.shop.model.Product;
        import com.shop.model.User;
        import java.io.File;
        import java.io.FileNotFoundException;
        import java.util.ArrayList;
        import java.util.List;
        import java.util.Scanner;

/** UserDAO class - allows user database manipulation. */

public class UserDAO {
    private FileEdit<User> userEditor = new FileEdit<>();
/** Method to identify all users in the database and store them as objects in an ArrayList: */
    public List<User> findAllUsers() {

//      Building a List of user data Strings from the users database:
        List<String> userDataList = new ArrayList<>();
        try {
//          Pointing to the user database file:
            File users = new File("./src/main/resources/users.txt");
//          Scanning the database line by line and adding those lines to the user data List:
            Scanner userScanner = new Scanner(users);
            while (userScanner.hasNextLine()) {
                userDataList.add(userScanner.nextLine());
            }
//      If access to the user database is interrupted:
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }

//      Converting the List of user data Strings into a List of User objects:
        List<User> userList = new ArrayList<>();
        for (String userData : userDataList) {
            if (userData.length()>=5) {
                String[] userDataValues = userData.split("\\|");
                userList.add(new User(userDataValues[0], userDataValues[1], userDataValues[2], userDataValues[3], userDataValues[4]));
            }
        }

//      End result of the method - returning the User object ArrayList:
        return userList;
    }

/** Method to add new users to the database: */
    public void addUser(User user) throws ShopTechnicalException {
        userEditor.write("users.txt", user);
    }

/** Method to remove users from the database: */
    public void deleteUser(String email) throws ShopFileException {
        userEditor.replace("users.txt", email, ".*", "");
    }

}