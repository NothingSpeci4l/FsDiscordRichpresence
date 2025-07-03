package fr.gregwll.fsdsrp;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import fr.gregwll.fsdsrp.api.SimbriefAPI;
import fr.gregwll.fsdsrp.api.SimbriefUtils;
import fr.gregwll.fsdsrp.files.FilesUtils;
import fr.gregwll.fsdsrp.files.objects.Settings;
import fr.gregwll.fsdsrp.files.serialization.SettingsSerializationManager;
import fr.gregwll.fsdsrp.utils.Logger;
import fr.gregwll.fsdsrp.window.MainFrame;

import java.io.File;

public class Main {

    private static Logger logger = new Logger("FsDsRichpresence");

    private static SettingsSerializationManager settingsSerializationManager;

    private static Main instance;

    //dirs and file
    private static File saveDir = new File(System.getProperty("user.home"), "/fsdsrp/");
    private static final File settingsFile = new File(saveDir, "settings.json");

    public static void main(String[] args) throws Exception {
        settingsSerializationManager = new SettingsSerializationManager();
        System.out.println(SimbriefUtils.getflightnum("chifou"));

        saveDir.mkdirs();

        if(!settingsFile.exists()) {
            Settings settings = new Settings();

            final String jsonSettings = settingsSerializationManager.serialize(settings);

            FilesUtils.save(settingsFile,jsonSettings);


        } else {
            MainFrame.display();
        }
    }

    public static Logger getLogger() {
        return logger;
    }

    public static SettingsSerializationManager getSettingsSerializationManager() {
        return settingsSerializationManager;
    }

    public static File getSaveDir() {
        return saveDir;
    }

    public static File getSettingsFile() {
        return settingsFile;
    }

}
