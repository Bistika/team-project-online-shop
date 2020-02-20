package com.shop.ui.ui_handlers;

import javax.swing.*;

public class InputPopUps extends JFrame {

    public static String input (String message) {
        try {JFrame frame = new JFrame();
            String result = JOptionPane.showInputDialog(frame, message);
            if (result.isEmpty()){
                result = ".";
            }return result;
        } catch (NullPointerException e){
            return "NullPointerExceptionFound";
        }
    }
}