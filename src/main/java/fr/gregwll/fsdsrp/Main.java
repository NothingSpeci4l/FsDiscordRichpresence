package fr.gregwll.fsdsrp;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import fr.gregwll.fsdsrp.api.SimbriefAPI;
import fr.gregwll.fsdsrp.api.SimbriefUtils;
import fr.gregwll.fsdsrp.files.serialization.SettingsSerializationManager;
import fr.gregwll.fsdsrp.utils.Logger;
import fr.gregwll.fsdsrp.window.MainFrame;

import java.io.File;

public class Main {

    private static Logger logger = new Logger("FsDsRichpresence");

    private static SettingsSerializationManager settingsSerializationManager;

    public static void main(String[] args) throws Exception {
        System.out.println(SimbriefUtils.getflightnum("chifou"));

        MainFrame.display();

        new File(System.getProperty("user.home"), "/fsdsrp/").mkdirs();
        File saveDir = new File(System.getProperty("user.dir"), "/fsdsrp/");
        saveDir.mkdir();

        File settingsFile = new File(saveDir, "settings.json");
        if(!settingsFile.exists()) {
            settingsFile.createNewFile();
        }


    }

    public static Logger getLogger() {
        return logger;
    }

    public static SettingsSerializationManager getSettingsSerializationManager() {
        return settingsSerializationManager;
    }
}
