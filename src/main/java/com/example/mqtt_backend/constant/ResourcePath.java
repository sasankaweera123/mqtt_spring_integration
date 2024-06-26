package com.example.mqtt_backend.constant;

public interface ResourcePath {

    String SOUNDBOX_SERIAL_HEADER = "Aiken-S";
    String MQTT_BROKER_CONNECTION = "tcp://124.43.65.114:1883";
    String MQTT_COMMON_TOPIC = "myTopic";

    // Rest API
    String BASE_URL = "/api/v1";
    String MQTT = BASE_URL + "/mqtt";

    // Web UI
    String APP_ROOT = "soundbox";
    String LOGIN = APP_ROOT + "/login";
    String LOGIN_FAILED = "redirect:/" + LOGIN + "?error";
    String LOGOUT = APP_ROOT + "/logout";
    String LOGOUT_SUCCESS = "redirect:/" + LOGIN;
    String HOME_PAGE = APP_ROOT + "/home";
    String DASHBOARD = APP_ROOT + "/dashboard";
    String USERHANDLING = APP_ROOT + "/user-handling";
    String HOME_PAGE_URL = "redirect:/" + DASHBOARD;
    String SOUND_BOX = APP_ROOT + "/sound-box";
    String SOUND_BOX_SAVE = SOUND_BOX + "/save";
    String SOUND_BOX_UPDATE = SOUND_BOX + "/update";
    String SOUND_BOX_DELETE = SOUND_BOX + "/delete";
    String SOUND_BOX_IMPORT = SOUND_BOX + "/import";
    String TESTING_MQTT_SEND = "soundbox/testing/mqtt/send";
    String TESTING_MQTT_RECEIVE = "soundbox/testing/mqtt/receive";

    // User Handling

    String USER_ADD = USERHANDLING + "/add";
    String USER_UPDATE = USERHANDLING + "/update";
    String USER_DELETE = USERHANDLING + "/delete";

    // Web Pages
    String LOGIN_PAGE = "pages/login-user";
    String DASHBOARD_PAGE = "pages/dashboard";
    String SOUND_BOX_PAGE = "pages/sound-box";
    String USERHANDLING_PAGE = "pages/users-handling";

    // Login
    String LOGIN_SUCCESS = "Login Success";
    String LOGIN_FAILED_MSG = "Login Failed";
    String LOGIN_EMAIL_NOT_FOUND = "Email not found";
    String LOGIN_PASSWORD_INCORRECT = "Password incorrect";



}
