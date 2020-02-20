package com.shop.service;

import com.shop.dao.UserDAO;
import com.shop.ui.ui_handlers.PrintUI;
import com.shop.exception.ShopException;
import com.shop.exception.ShopTechnicalException;
import com.shop.exception.ShopWrongCredentialsException;
import com.shop.model.User;
import com.shop.security.UserContext;

import java.util.List;
import java.util.stream.Collectors;

/** User Service class - allows login and sign up. */

public class UserService {
    private UserDAO userDAO = new UserDAO();

//     * Login method - checks given credentials against user database:
    public void login(String email, String password) throws ShopException {
        User userLogged = userDAO.findAllUsers().stream()
                .filter(user -> areCredentialsEqual(email, password, user))
                .findFirst().orElseThrow(ShopWrongCredentialsException::new);
        UserContext.setLoggedUser(userLogged);
    }

    private boolean areCredentialsEqual(String email, String password, User user) {
        return email.equals(user.getEmail()) && password.equals(user.getPassword());
    }


/** SignUp method - tries to add given credentials to the user database: */
    public boolean signUp(String password, String email) throws ShopTechnicalException {
        boolean userExists = userDAO.findAllUsers().stream()
                .anyMatch(user -> email.equals(user.getEmail()));

        if (!userExists) {
            User user = new User(password, email, "Shopper");
            userDAO.addUser(user.dbPrint());
        }
        return userExists;
    }


    public List<User> getUsersbyEmail(String email) throws ShopException {
        return userDAO.findAllUsers().stream()
                .filter(user -> user.getEmail().matches(".*" + email + ".*"))
                .collect(Collectors.toList());
    }

    public void replaceUserData (User user) throws ShopException {
            userDAO.deleteUser(user.getEmail());
            userDAO.addUser(user.dbPrint());
        }
    public void deleteUser (User user) {
        userDAO.deleteUser(user.getEmail());
    }
}