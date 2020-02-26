package com.shop.ui.handlers;

import javax.swing.*;
import java.awt.*;
import java.util.Optional;

public class InputPopUps extends JFrame {

    public static final String CANCELLED = "Cancelled";
    private static InputPopUps inputPopUps = new InputPopUps(Color.white, Color.white, Color.white, Color.black);

    public static String input(String message) {

        JTextArea textArea = new JTextArea(message);
        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setEditable(true);

        UIManager.put("OptionPane.background", inputPopUps.getPaneBG());
        UIManager.put("Panel.background", inputPopUps.getPanelBG());
        textArea.setBackground(inputPopUps.getTxtAreaBG());
        textArea.setForeground(inputPopUps.getTxtAreaFG());

        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        scrollPane.setPreferredSize(new Dimension(560, 600));
        scrollPane.setWheelScrollingEnabled(true);

        Optional<String> result = Optional.ofNullable(JOptionPane.showInputDialog(null, scrollPane, null));
        return result.map(InputPopUps::emptyDefaultValue).orElse(CANCELLED);
    }

    private static String emptyDefaultValue(String s) {
        if (s.isEmpty()) {
            return ".";
        }
        return s;
    }

    public static InputPopUps get() {
        return inputPopUps;
    }


    //From this point on, only constructors and getter/setter
    public InputPopUps (Color paneBG, Color panelBG, Color txtAreaBG, Color txtAreaFG){
        this.paneBG = paneBG;
        this.panelBG = panelBG;
        this.txtAreaBG = txtAreaBG;
        this.txtAreaFG = txtAreaFG;
    }

    private Color paneBG;
    private Color panelBG;
    private Color txtAreaBG;
    private Color txtAreaFG;

    public void setPaneBG(Color paneBG) {
        this.paneBG = paneBG;
    }

    public void setPanelBG(Color panelBG) {
        this.panelBG = panelBG;
    }

    public void setTxtAreaBG(Color txtAreaBG) {
        this.txtAreaBG = txtAreaBG;
    }

    public void setTxtAreaFG(Color txtAreaFG) {
        this.txtAreaFG = txtAreaFG;
    }

    private Color getPaneBG() {
        return paneBG;
    }

    private Color getPanelBG() {
        return panelBG;
    }

    private Color getTxtAreaBG() {
        return txtAreaBG;
    }

    private Color getTxtAreaFG() {
        return txtAreaFG;
    }

}