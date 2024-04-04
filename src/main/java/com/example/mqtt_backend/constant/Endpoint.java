package com.example.mqtt_backend.constant;

import java.security.SecureRandom;

public interface Endpoint {

    // Rest API
    String BASE_URL = "/api/v1";
    String MQTT = BASE_URL + "/mqtt";

    // Web UI
    String APP_ROOT = "soundbox";
    String HOME_PAGE = APP_ROOT + "/home";
}
