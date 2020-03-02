package com.shop.service;

import com.shop.dao.UserDAO;
import com.shop.exception.ShopException;
import com.shop.exception.ShopFileException;
import com.shop.exception.ShopTechnicalException;
import com.shop.exception.ShopWrongCredentialsException;
import com.shop.model.User;
import com.shop.security.UserContext;

import java.util.List;
import java.util.stream.Collectors;

/** UserService class - connects UI classes to the UserDAO class to modify data in the user database. */

public class UserService {

    private UserDAO userDAO = new UserDAO();

    private boolean doCredentialsMatch(String email, String password, User user) {
        return email.equals(user.getEmail()) && password.equals(user.getPassword());
    }

    public boolean doesUserExist(String email) {
        return userDAO.findAllUsers().stream()
                .anyMatch(user -> email.equals(user.getEmail()));
    }

    public void login(String email, String password) throws ShopException {
        User userLoggingIn = userDAO.findAllUsers().stream()
                .filter(user -> doCredentialsMatch(email, password, user))
                .findFirst().orElseThrow(ShopWrongCredentialsException::new);
        UserContext.setLoggedUser(userLoggingIn);
    }

    public void signUp(String password, String email) throws ShopTechnicalException {
        if (!doesUserExist(email)) {
            User userSigningUp = new User(email, password, "SHOPPER");
            userDAO.addUser(userSigningUp);
            UserContext.setLoggedUser(userSigningUp);
        }
    }

    public List<User> getUsersByPartialEmail(String partialEmail) {
        return userDAO.findAllUsers().stream()
                .filter(user -> user.getEmail().toLowerCase().matches(".*" + partialEmail.toLowerCase() + ".*"))
                .collect(Collectors.toList());
    }
    public List<User> getUsersByFullEmail(String email) {
        return userDAO.findAllUsers().stream()
                .filter(user -> user.getEmail().toLowerCase().equals(email.toLowerCase()))
                .collect(Collectors.toList());
    }
    public void deleteUser(User user) throws ShopFileException {
        userDAO.deleteUser(user.getEmail());
    }

    public void replaceUserData(User user) throws ShopException {
        userDAO.deleteUser(user.getEmail());
        userDAO.addUser(user);
    }
}