package com.shop.ui;

import com.shop.exception.ShopException;
import com.shop.exception.ShopFileException;
import com.shop.model.User;
import com.shop.security.UserContext;
import com.shop.service.UserService;
import com.shop.ui.handlers.InputPopUps;

import static com.shop.ui.handlers.InputPopUps.CANCELLED;

class AdminManageAccountsUI {
	private UserService userService = new UserService();
	private UserAccountUI userAccountUI = new UserAccountUI();

	private static final String FIND_USERS = "1";
    private static final String EDIT_USERS = "2";
    private static final String DELETE_USERS = "3";
    private static final String CHANGE_ROLE = "1";
    private static final String RESET_PASSWORD = "2";
    private static final String CLEAN_FILTER = "4";
    private static final String PERSONAL_ACCOUNT = "5";

    private static String userInput = null;
    private static String result = "";
    private static String userList = "";


    void manageAccounts() throws ShopException {

		do {
			userInput = InputPopUps.input("1 : FIND USERS\n2 : EDIT USERS\n3 : DELETE USERS\n4 : CLEAR FILTER\n5 : MANAGE PERSONAL ACCOUNT" + result + "\n\n" + userList);
//			result = "";
			switch (userInput) {
				case FIND_USERS: {
					userList = viewUsers();
					break;
				}
				case DELETE_USERS: {
					result = deleteUsers(userList);
					break;
				}
                case EDIT_USERS : {
                    editUsers(userList);
                    break;
                }
                case CLEAN_FILTER : {
                    userList = "";
                    break;
                }
				case PERSONAL_ACCOUNT : {
					userAccountUI.manageAccount(UserContext.getLoggedUser());
					break;
				}
				default:
					result = "PLEASE ENTER A VALID OPTION.";
			}

		} while (!userInput.equals(CANCELLED));
	}

	private String viewUsers() {
		StringBuilder userList = new StringBuilder();

		String email = InputPopUps.input("FILTER USERS BY EMAIL:");
		if (!email.equals(CANCELLED)) {
			for (User user : userService.getUsersByPartialEmail(email)) {
				userList.append(user.toString()).append("\n");
			}
		}
		if (userList.length() > 0) {
			return userList.toString();
		} else {
			return "NO USERS MATCHED THAT EMAIL.";
		}
	}

	private String deleteUsers(String userList) throws ShopFileException {
		String email = InputPopUps.input("ENTER FULL EMAIL OF THE USER TO BE DELETED:\n\n" + userList);
		if (!email.equals(CANCELLED) && userService.doesUserExist(email)){
			for (User user : userService.getUsersByFullEmail(email)){
				userService.deleteUser(user);
			}
			return "USER DELETED.";
		}
		else return "USER NOT FOUND.";
	}

	private void editUsers(String filteredUserList) throws ShopException {
		String email = InputPopUps.input("ENTER FULL EMAIL OF THE USER TO BE EDITED:\n\n" + filteredUserList);
		if (!email.equals(CANCELLED) && userService.doesUserExist(email)){
			User userToBeEdited = userService.getUsersByFullEmail(email).get(0);
            userInput = "";
            do {
                userInput  = InputPopUps.input("1 : CHANGE ROLE\n2 : RESET PASSWORD\n" + userToBeEdited.toString());
                switch (userInput) {
                    case CHANGE_ROLE : {
                        result = changeRole(userToBeEdited);
                        break;
                    }
                    case RESET_PASSWORD : {
                        result = resetUserPassword(userToBeEdited);
                    }
                }
			} while(!userInput.equals(CANCELLED));
		}

	}

	private String changeRole(User user) throws ShopException {
        String newRole  = InputPopUps.input("ENTER USER'S NEW ROLE (ADMIN/SHOPPER):\n\n" + user.toString());
        if (!newRole.equals(CANCELLED)){
            user.setRole(newRole);
            userService.replaceUserData(user);
            return "USER UPDATED.";

        } else {return "";}
    }

    private String resetUserPassword (User user) throws ShopException {
        String newPassword = InputPopUps.input("ENTER A NEW PASSWORD FOR THE SELECTED USER (CANNOT BE EMPTY):\n\n" + user.toString());
        if (!newPassword.equals(CANCELLED) && !newPassword.equals(".")){
            user.setPassword(newPassword);
            userService.replaceUserData(user);
            return "PASSWORD SUCCESSFULLY RESET.";
        }
        if (newPassword.equals(".")) {
            return "PLEASE ENTER A VALID PASSWORD.";
        }
        else return "";
    }
}