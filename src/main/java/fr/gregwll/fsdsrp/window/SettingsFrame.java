package fr.gregwll.fsdsrp.window;

import javax.swing.*;
import java.awt.*;

public class SettingsFrame {

    private static JFrame frame = new JFrame("Settings - FSDSRP - gregwll");
    public static void display() {
        JPanel panel = (JPanel) frame.getContentPane();
        frame.setContentPane(panel);
        panel.setLayout(null);
        panel.setBackground(Color.DARK_GRAY);

        JLabel sbusernamelabel = new JLabel("Simbrief Username");
        sbusernamelabel.setSize(150,50);
        sbusernamelabel.setLocation(1, 5);
        sbusernamelabel.setForeground(Color.WHITE);
        panel.add(sbusernamelabel);

        JTextField sbusernameTF = new JTextField(10);
        sbusernameTF.setSize(100,20);
        sbusernameTF.setLocation(1, 40);
        sbusernameTF.setForeground(Color.WHITE);
        panel.add(sbusernameTF);


        frame.setVisible(true);
        frame.setSize(300 ,400);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
    }

    public static void hide() {
        frame.setVisible(false);
    }
}
