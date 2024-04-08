package com.example.mqtt_backend.constant;

public interface ResourcePath {

    String SOUNDBOX_SERIAL_HEADER = "Aiken-S";

    // Rest API
    String BASE_URL = "/api/v1";
    String MQTT = BASE_URL + "/mqtt";

    // Web UI
    String APP_ROOT = "soundbox";
    String LOGIN = APP_ROOT + "/login";
    String LOGIN_FAILED = APP_ROOT + "/login";
    String HOME_PAGE = APP_ROOT + "/home";
    String DASHBOARD = APP_ROOT + "/dashboard";
    String TESTING = APP_ROOT + "/testing";
    String HOME_PAGE_URL = "redirect:/" + DASHBOARD;
    String SOUND_BOX = APP_ROOT + "/sound-box";
    String SOUND_BOX_SAVE = SOUND_BOX + "/save";
    String SOUND_BOX_UPDATE = SOUND_BOX + "/update";
    String SOUND_BOX_DELETE = SOUND_BOX + "/delete";
    String TESTING_MQTT_SEND = TESTING + "/mqtt/send";

    // Web Pages
    String LOGIN_PAGE = "pages/login-user";
    String DASHBOARD_PAGE = "pages/dashboard";
    String SOUND_BOX_PAGE = "pages/sound-box";
    String TESTING_PAGE = "pages/testing";

    // Login
    String LOGIN_SUCCESS = "Login Success";
    String LOGIN_FAILED_MSG = "Login Failed";
    String LOGIN_EMAIL_NOT_FOUND = "Email not found";
    String LOGIN_PASSWORD_INCORRECT = "Password incorrect";



}