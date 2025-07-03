package fr.gregwll.fsdsrp.window;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import fr.gregwll.fsdsrp.Main;
import fr.gregwll.fsdsrp.api.SimbriefAPI;
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
        panel.setBackground(new Color(15,15,15));
        frame.setSize(300 ,400);

        JLabel sbusernamelabel = new JLabel("Simbrief Username");
        sbusernamelabel.setSize(150,50);
        sbusernamelabel.setLocation(frame.getWidth()/2 - sbusernamelabel.getWidth()/2, 5);
        sbusernamelabel.setFont(new Font("arial", Font.BOLD, 16));
        sbusernamelabel.setForeground(Color.WHITE);
        panel.add(sbusernamelabel);

        final SettingsSerializationManager settingsSerializationManager = Main.getSettingsSerializationManager();
        final String SettingsJsonExport = FilesUtils.loadContent(Main.getSettingsFile());
        final Settings settings = settingsSerializationManager.deserialize(SettingsJsonExport);

        JTextField sbusernameTF = new JTextField(10);
        sbusernameTF.setSize(140,30);
        sbusernameTF.setLocation(frame.getWidth()/2 - sbusernameTF.getWidth()/2, 40);
        sbusernameTF.setFont(new Font("arial", Font.ITALIC, 16));
        sbusernameTF.setBackground(new Color(25,25,25));
        sbusernameTF.setForeground(Color.WHITE);
        panel.add(sbusernameTF);

        JButton saveButton = new JButton("Save & Close");
        saveButton.setSize(150, 30);
        saveButton.setLocation(frame.getWidth()/ 2 - saveButton.getWidth() /2, 75);
        saveButton.setBackground(new Color(25,25,25));
        saveButton.setForeground(Color.WHITE);
        saveButton.setSelected(false);
        panel.add(saveButton);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String json = null;
                try {
                    json = SimbriefAPI.fetchFlightData(sbusernameTF.getText());
                    JsonObject obj = JsonParser.parseString(json).getAsJsonObject();

                    JsonObject general = obj.getAsJsonObject("params");
                    String userID = general.get("user_id").getAsString();

                    Settings settings = new Settings();
                    settings.setSbUsername(sbusernameTF.getText());

                    final String jsonSettings = Main.getSettingsSerializationManager().serialize(settings);

                    System.out.println(userID + " " + sbusernameTF.getText());

                    FilesUtils.save(Main.getSettingsFile(),jsonSettings);
                    SettingsFrame.hide();
                } catch (Exception ex) {
                    Main.getLogger().sendWindowError("Sorry, this username dosn't exists", frame);
                    throw new RuntimeException(ex);
                }

                /*settings.setSbUsername(sbusernameTF.getText());
                final SettingsSerializationManager settingsSerializationManager = Main.getSettingsSerializationManager();
                final String jsonSettings = settingsSerializationManager.serialize(settings);

                FilesUtils.save(Main.getSettingsFile(),jsonSettings);
                SettingsFrame.hide();*/
            }
        });


        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(1);
    }

    public static void hide() {
        frame.setVisible(false);
    }
}
