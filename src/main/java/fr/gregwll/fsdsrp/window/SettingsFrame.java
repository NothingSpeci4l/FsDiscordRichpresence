package fr.gregwll.fsdsrp.window;

import fr.gregwll.fsdsrp.Main;
import fr.gregwll.fsdsrp.files.FilesUtils;
import fr.gregwll.fsdsrp.files.objects.Settings;
import fr.gregwll.fsdsrp.files.serialization.SettingsSerializationManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;

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

        final SettingsSerializationManager settingsSerializationManager = Main.getSettingsSerializationManager();
        final String SettingsJsonExport = FilesUtils.loadContent(Main.getSettingsFile());
        final Settings settings = settingsSerializationManager.deserialize(SettingsJsonExport);

        JTextField sbusernameTF = new JTextField(10);
        sbusernameTF.setSize(100,20);
        sbusernameTF.setLocation(1, 40);
        sbusernameTF.setForeground(Color.BLACK);
        sbusernameTF.setText(settings.getSbUsername());
        panel.add(sbusernameTF);

        JButton saveButton = new JButton("Save & Close");
        saveButton.setSize(100,40);
        saveButton.setLocation(1, 90);
        panel.add(saveButton);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                settings.setSbUsername(sbusernameTF.getText());
                final SettingsSerializationManager settingsSerializationManager = Main.getSettingsSerializationManager();
                final String jsonSettings = settingsSerializationManager.serialize(settings);

                FilesUtils.save(Main.getSettingsFile(),jsonSettings);
                frame.hide();
            }
        });


        frame.setVisible(true);
        frame.setSize(300 ,400);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(1);
    }

    public static void hide() {
        frame.setVisible(false);
    }
}
