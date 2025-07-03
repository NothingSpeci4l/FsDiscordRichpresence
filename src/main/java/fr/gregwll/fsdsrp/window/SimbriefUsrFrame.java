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

public class SimbriefUsrFrame {
    private static JFrame frame = new JFrame("FsDiscordRichPresence - gregwll");

    public static void Display() {
        frame.setSize(400,500);
        JPanel panel = (JPanel) frame.getContentPane();
        frame.setContentPane(panel);
        panel.setLayout(null);
        panel.setBackground(new Color(15,15,15));

        JLabel welcomeLabel1 = new JLabel("Welcome !");
        welcomeLabel1.setFont(new Font("Arial", Font.BOLD, 25));
        welcomeLabel1.setSize(125, 18);
        welcomeLabel1.setLocation(frame.getWidth() / 2 - welcomeLabel1.getWidth() / 2, 5);
        welcomeLabel1.setForeground(Color.WHITE);
        panel.add(welcomeLabel1);

        JLabel welcomeLabel2 = new JLabel("for use this app, we just need your simbrief username.");
        welcomeLabel2.setFont(new Font("Arial", Font.BOLD, 14));
        welcomeLabel2.setSize(395, 14);
        welcomeLabel2.setLocation(5, 40);
        welcomeLabel2.setForeground(Color.WHITE);
        panel.add(welcomeLabel2);

        JLabel sbusernamelabel = new JLabel("Simbrief Username");
        sbusernamelabel.setSize(150,50);
        sbusernamelabel.setLocation(frame.getWidth()/2 - sbusernamelabel.getWidth()/2, 100);
        sbusernamelabel.setFont(new Font("arial", Font.BOLD, 16));
        sbusernamelabel.setForeground(Color.WHITE);
        panel.add(sbusernamelabel);

        JTextField sbusernameTF = new JTextField(10);
        sbusernameTF.setSize(140,30);
        sbusernameTF.setLocation(frame.getWidth()/2 - sbusernameTF.getWidth()/2, 140);
        sbusernameTF.setFont(new Font("arial", Font.ITALIC, 16));
        sbusernameTF.setBackground(new Color(25,25,25));
        sbusernameTF.setForeground(Color.WHITE);
        panel.add(sbusernameTF);

        JButton submitButton = new JButton("Submit");
        submitButton.setSize(100, 30);
        submitButton.setLocation(frame.getWidth()/ 2 - submitButton.getWidth() /2, 200);
        submitButton.setBackground(new Color(25,25,25));
        submitButton.setForeground(Color.WHITE);
        submitButton.setSelected(false);
        panel.add(submitButton);

        submitButton.addActionListener(new ActionListener() {
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

                    FilesUtils.save(Main.getSettingsFile(),jsonSettings);

                    System.out.println(userID + " " + sbusernameTF.getText());

                    Hide();
                    MainFrame.display();
                } catch (Exception ex) {
                    Main.getLogger().sendWindowError("Sorry, this username dosn't exists", frame);
                    throw new RuntimeException(ex);
                }
            }
        });

        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

    }

    public static void Hide() {
        frame.setVisible(false);
    }
}
