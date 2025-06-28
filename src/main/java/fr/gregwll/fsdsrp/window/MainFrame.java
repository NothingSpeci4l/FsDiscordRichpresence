package fr.gregwll.fsdsrp.window;

import fr.gregwll.fsdsrp.utils.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame {

    private static JFrame frame = new JFrame("FsDiscordRichPresence - gregwll");

    public static void display() {
        JPanel panel = (JPanel) frame.getContentPane();
        frame.setContentPane(panel);
        panel.setLayout(null);
        panel.setBackground(Color.DARK_GRAY);

        JButton settings = new JButton("Settings");
        settings.setLocation(5, 500);
        settings.setSize(100,50);
        settings.setBackground(Color.GRAY);
        panel.add(settings);

        settings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SettingsFrame.display();
            }
        });

        frame.setVisible(true);
        frame.setSize(400,600);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void hide() {
        frame.setVisible(false);
    }
}
