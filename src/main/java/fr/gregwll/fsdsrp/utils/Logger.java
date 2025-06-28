package fr.gregwll.fsdsrp.utils;

import javax.swing.*;

public class Logger {

    String prefix;

    public Logger(String prefix) {
        this.prefix = prefix;
    }

    public void sendLog(String mes) {
        System.out.println(prefix + " " + mes);
    }

    public void sendError(String content) {
        System.err.println(prefix + " " + content);
    }

    public void sendWindowError(String mes, JFrame frame) {
        JOptionPane.showMessageDialog(frame, mes, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
