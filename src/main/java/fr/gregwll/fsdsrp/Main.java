package fr.gregwll.fsdsrp;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import fr.gregwll.fsdsrp.api.SimbriefAPI;
import fr.gregwll.fsdsrp.api.SimbriefUtils;
import fr.gregwll.fsdsrp.utils.Logger;
import fr.gregwll.fsdsrp.window.MainFrame;

public class Main {

    private static Logger logger = new Logger("FsDsRichpresence");

    public static void main(String[] args) throws Exception {
        System.out.println(SimbriefUtils.getflightnum("chifou"));

        MainFrame.display();

    }

    public static Logger getLogger() {
        return logger;
    }
}
