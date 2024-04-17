package com.example.mqtt_backend.controller;

import com.example.mqtt_backend.entity.SoundBoxDetails;
import com.example.mqtt_backend.enumeration.BankCode;
import com.example.mqtt_backend.enumeration.MqttProcess;
import com.example.mqtt_backend.enumeration.SoundBoxStatus;
import com.example.mqtt_backend.modal.dto.MqttForm;
import com.example.mqtt_backend.modal.dto.MqttReceive;
import com.example.mqtt_backend.modal.dto.SoundBoxForm;
import com.example.mqtt_backend.modal.util.LoginUserUtil;
import com.example.mqtt_backend.service.MqttService;
import com.example.mqtt_backend.service.SoundBoxService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.ui.Model;
import com.example.mqtt_backend.constant.ResourcePath;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Controller
public class SoundBoxController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final SoundBoxService soundBoxService;
    private final MqttService mqttService;

    public SoundBoxController(SoundBoxService soundBoxService, MqttService mqttService) {
        this.soundBoxService = soundBoxService;
        this.mqttService = mqttService;
    }

    /**
     * Redirect to Dashboard page
     * @return dashboard page
     */
    @GetMapping("/")
    public String home() {
        logger.info("load home page");
        return ResourcePath.HOME_PAGE_URL;
    }

    /**
     * Load dashboard page
     * @param model model
     * @return dashboard page
     */
    @GetMapping(ResourcePath.DASHBOARD)
    public String dashboard(Model model){

        logger.info("load dashboard page");

        List<String> labels = Stream.of(SoundBoxStatus.values()).map(Enum::name).toList();
        List<SoundBoxDetails> soundBoxDetails = soundBoxService.getAllSoundBoxDetails();
        List<Integer> data = new ArrayList<>();
        List<MqttProcess> processes = Arrays.asList(MqttProcess.values());

        MqttForm mqttForm = new MqttForm();
        MqttReceive mqttReceive = new MqttReceive();

        // get the count of each sound box status
        for (SoundBoxStatus status : SoundBoxStatus.values()) {
            int count = (int) soundBoxDetails.stream().filter(soundBox -> Objects.equals(soundBox.getSoundBoxStatus(), status)).count();
            data.add(count);
        }
        logger.info("Load login user");
        LoginUserUtil.loadLoginUser(model);

        model.addAttribute("count_labels", labels);
        model.addAttribute("count_data", data);
        model.addAttribute("count_title", "Sound Box Dashboard");
        model.addAttribute("total_sound_boxes", soundBoxService.getSoundBoxCount());
        model.addAttribute("mqtt_form", mqttForm);
        model.addAttribute("mqtt_receive", mqttReceive);
        model.addAttribute("processes", processes);
        model.addAttribute("sound_box_details", soundBoxDetails);


        return ResourcePath.DASHBOARD_PAGE;
    }

    /**
     * Load sound box page
     * @param model model
     * @param page page number: page number to load
     * @param size page size: number of records per page
     * @return sound box page
     */
    @GetMapping(ResourcePath.SOUND_BOX)
    public String soundBox(Model model,
                           @RequestParam(defaultValue = "0") int page,
                           @RequestParam(defaultValue = "10") int size,
                           @RequestParam(defaultValue = "ALL") String bankCode,
                           @RequestParam(defaultValue = "ALL") String soundBoxStatus
                           ) {
        logger.info("load sound box page");
        Page<SoundBoxDetails> soundBoxDetailsPage = soundBoxService.getSoundBoxDetailsPage(PageRequest.of(page, size), BankCode.valueOf(bankCode), SoundBoxStatus.valueOf(soundBoxStatus));
        SoundBoxForm soundBoxForm = new SoundBoxForm();

        LoginUserUtil.loadLoginUser(model);
        model.addAttribute("sound_box_details", soundBoxDetailsPage);
        model.addAttribute("sound_box_form", soundBoxForm);
        model.addAttribute("sound_box_status", SoundBoxStatus.values());
        model.addAttribute("bank_codes", BankCode.values());

        return ResourcePath.SOUND_BOX_PAGE;
    }



    @PostMapping(ResourcePath.SOUND_BOX_SAVE)
    public String saveSoundBox(SoundBoxDetails soundBoxDetails,RedirectAttributes model) {
        try {
            soundBoxService.saveSoundBoxDetails(soundBoxDetails);
            logger.warn("Save sound box details");
            model.addFlashAttribute("message", "Successfully saved sound box details");
        }catch (Exception e){
            logger.error("Failed to save sound box details");
            model.addFlashAttribute("message", "Failed to save sound box details");
        }
        return "redirect:/" + ResourcePath.SOUND_BOX;
    }

    @GetMapping(ResourcePath.SOUND_BOX_DELETE + "/{soundBoxId}")
    public String deleteSoundBox(@PathVariable("soundBoxId") long id, RedirectAttributes model) {
        try {
            soundBoxService.deleteSoundBoxDetails(id);
            logger.warn("Delete sound box details");
            model.addFlashAttribute("message", "Successfully deleted sound box details");
        }catch (Exception e){
            logger.error("Failed to delete sound box details");
            model.addFlashAttribute("message", "Failed to delete sound box details");
        }
        return "redirect:/" + ResourcePath.SOUND_BOX;
    }

    /**
     * Load sound box update page
     * @param model model
     * @param id sound box id: id of the sound box to update
     * @return sound box update page
     */

    @PostMapping(ResourcePath.SOUND_BOX_UPDATE + "/{id}")
    public String updateSoundBox(@PathVariable long id, @ModelAttribute SoundBoxForm soundBoxDetails, RedirectAttributes model){
        System.out.println(soundBoxDetails);
        try {
            SoundBoxDetails soundBox = getSoundBoxDetails(soundBoxDetails);
            System.out.println(soundBoxDetails);
            soundBoxService.updateSoundBoxDetails(id, soundBox);
            logger.warn("Update sound box details");
            model.addFlashAttribute("message", "Successfully updated sound box details");
        }catch (Exception e){
            logger.error("Failed to update sound box details");
            model.addFlashAttribute("message", "Failed to update sound box details");
        }
        return "redirect:/" + ResourcePath.SOUND_BOX;
    }

    private static SoundBoxDetails getSoundBoxDetails(SoundBoxForm soundBoxDetails) {
        SoundBoxDetails soundBox = new SoundBoxDetails();
        soundBox.setSerialNumber(soundBoxDetails.getSerialNumber());
        soundBox.setSoundBoxStatus(soundBoxDetails.getSoundBoxStatus());
        soundBox.setMName(soundBoxDetails.getMName());
        soundBox.setMAddress(soundBoxDetails.getMAddress());
        soundBox.setMid(soundBoxDetails.getMid());
        soundBox.setTid(soundBoxDetails.getTid());
        soundBox.setBankCode(soundBoxDetails.getBankCode());
        soundBox.setDateAdded(LocalDate.parse(soundBoxDetails.getDateAdded()));
        return soundBox;
    }

    /**
     * MQTT Testing Component: Send message to MQTT broker via portal
     * @param model model
     * @return dashboard page
     */
    @PostMapping(ResourcePath.TESTING_MQTT_SEND)
    public String sendMqttMessage(@ModelAttribute MqttForm mqttForm , RedirectAttributes model){
        try{
            mqttService.mqttPublishViaPortal(mqttForm.getTopic(),mqttForm.getMessage());
            logger.warn("Send message to MQTT broker");
            model.addFlashAttribute("message", "Successfully sent message to MQTT broker ,Topic: "+mqttForm.getTopic());
        }catch (Exception e){
            logger.error("Failed to send message to MQTT broker");
            model.addFlashAttribute("message", "Failed to send message to MQTT broker");
        }
        return ResourcePath.HOME_PAGE_URL;
    }

    @PostMapping(ResourcePath.TESTING_MQTT_RECEIVE)
    public String receiveMqttMessage(@ModelAttribute MqttReceive mqttReceive, RedirectAttributes model){
        String message = mqttReceive.getTopic() + " | " + mqttReceive.getReferenceId();
        try{
            mqttService.mqttMessageReceived(message);
            logger.warn("Receive message from MQTT broker");
            model.addFlashAttribute("message", "Successfully received message from MQTT broker");
        }catch (Exception e){
            logger.error("Failed to receive message from MQTT broker");
            model.addFlashAttribute("message", "Failed to receive message from MQTT broker");
        }
        return ResourcePath.HOME_PAGE_URL;
    }


}
