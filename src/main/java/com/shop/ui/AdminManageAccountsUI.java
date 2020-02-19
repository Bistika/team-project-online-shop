package com.shop.ui;

import com.shop.exception.ShopException;
import com.shop.model.User;
import com.shop.service.UserService;
import com.shop.ui.ui_handlers.InputPopUps;
import com.shop.ui.ui_handlers.PrintUI;

class AdminManageAccountsUI {
    private UserService userService = new UserService();

    void manageAccounts() throws ShopException {
        String userInput = null;
        final String VIEW_USERS = "1";
        final String EDIT_USERS = "2";
        final String DELETE_USERS = "3";
        final String EXIT = "0";
        do {
            PrintUI.printBox("View Users : 1", "Edit Users : 2", "Delete Users: 3", "Exit : 0");
            userInput = InputPopUps.input("Option");
            switch (userInput) {
                case VIEW_USERS: {
                    PrintUI.printBox("Please insert user email (blanks accepted)");
                    String email = InputPopUps.input("Email");
                    try {
                        for (User user : userService.getUsersbyEmail(email)){
                            PrintUI.printBox(user.toString());
                        }
                    } catch (ShopException e) {
                        e.printStackTrace();
                    }
                    break;
                }
                case EDIT_USERS: {
                    PrintUI.printBox("Please insert full user email");
                    String email = InputPopUps.input("Email");
                    for (User user : userService.getUsersbyEmail(email)) {
                        PrintUI.printBox(user.toString());
                        final String CHANGEROLE = "1";
                        final String RESETPASSWORD = "2";
                        PrintUI.printBox("Change Role : 1", "Reset password : 2");
                        String adminChoice = InputPopUps.input("Option: ");
                        switch (adminChoice) {
                            case CHANGEROLE: {
                                user.setRole(InputPopUps.input("New Role"));
                                userService.replaceUserData(user);
                                break;
                            }
                            case RESETPASSWORD: {
                                user.setPassword(InputPopUps.input("New Password"));
                                userService.replaceUserData(user);
                                break;
                            }
                            case EXIT: {
                                break;
                            }
                            default: {
                                PrintUI.printBox("Please insert a valid option");
                            }
                        }

                    }
                }
                case DELETE_USERS: {
                    PrintUI.printBox("Please insert full user email to delete user");
                    String email = InputPopUps.input("Email to DELETE User");
                    for (User user : userService.getUsersbyEmail(email)) {
                        PrintUI.printBox(user.toString());
                        userService.deleteUser(user);
                    }
                }
                case EXIT: {
                    break;
                }
                default: {
                    PrintUI.printBox("Please insert a valid option");
                }
            }

        }while(!userInput.equals(EXIT));
    }

}
