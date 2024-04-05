package com.example.mqtt_backend.constant;

import java.security.SecureRandom;

public interface Endpoint {

    String SOUNDBOX_SERIAL_HEADER = "Aiken-S";

    // Rest API
    String BASE_URL = "/api/v1";
    String MQTT = BASE_URL + "/mqtt";

    // Web UI
    String APP_ROOT = "soundbox";
    String HOME_PAGE = APP_ROOT + "/home";
    String DASHBOARD = APP_ROOT + "/dashboard";
    String HOME_PAGE_URL = "redirect:/" + DASHBOARD;
    String SOUND_BOX = APP_ROOT + "/sound-box";
    String SOUND_BOX_SAVE = SOUND_BOX + "/save";
    String SOUND_BOX_UPDATE = SOUND_BOX + "/update";
    String SOUND_BOX_DELETE = SOUND_BOX + "/delete";

    // Web Pages
    String DASHBOARD_PAGE = "pages/dashboard";
    String SOUND_BOX_PAGE = "pages/sound-box";


}
