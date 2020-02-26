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
			userInput = InputPopUps.input("Find Users : 1\nEdit Users : 2\nDelete Users: 3\nClean Filter : 4\nManage Personal Account : 5" + result + "\n\n" + userList);
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
					result = "Please Insert a valid option:";
			}

		} while (!userInput.equals(CANCELLED));
	}

	private String viewUsers() {
		StringBuilder userList = new StringBuilder();

		String email = InputPopUps.input("Please filter users by email");
		if (!email.equals(CANCELLED)) {
			for (User user : userService.getUsersByPartialEmail(email)) {
				userList.append(user.toString()).append("\n");
			}
		}
		if (userList.length() > 0) {
			return userList.toString();
		} else {
			return "No Users Found for this email";
		}
	}

	private String deleteUsers(String userList) throws ShopFileException {
		String email = InputPopUps.input("Insert full user email to delete user: \n\n" + userList);
		if (!email.equals(CANCELLED) && userService.doesUserExist(email)){
			for (User user : userService.getUsersByFullEmail(email)){
				userService.deleteUser(user);
			}
			return "Deleted User";
		}
		else return "User not found";
	}

	private void editUsers(String filteredUserList) throws ShopException {
		String email = InputPopUps.input("Please insert full email of the user edit: \n\n" + filteredUserList);
		if (!email.equals(CANCELLED) && userService.doesUserExist(email)){
			User userToBeEdited = userService.getUsersByFullEmail(email).get(0);
            userInput = "";
            do {
                userInput  = InputPopUps.input("Change role : 1\nReset Password : 2\n" + userToBeEdited.toString());
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
        String newRole  = InputPopUps.input("New Role for User: \n\n" + user.toString());
        if (!newRole.equals(CANCELLED)){
            user.setRole(newRole);
            userService.replaceUserData(user);
            return "User Updated";

        } else {return "";}
    }

    private String resetUserPassword (User user) throws ShopException {
        String newPassword = InputPopUps.input("New password for User: \n\n" + user.toString());
        if (!newPassword.equals(CANCELLED) && !newPassword.equals(".")){
            user.setPassword(newPassword);
            userService.replaceUserData(user);
            return "Password reset";
        }
        if (newPassword.equals(".")) {
            return "Please insert a valid Password";
        }
        else return "";
    }
}