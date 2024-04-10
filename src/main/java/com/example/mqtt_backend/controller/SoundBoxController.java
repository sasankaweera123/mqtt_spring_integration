package com.example.mqtt_backend.controller;

import com.example.mqtt_backend.entity.SoundBoxDetails;
import com.example.mqtt_backend.enumeration.MqttProcess;
import com.example.mqtt_backend.enumeration.SoundBoxStatus;
import com.example.mqtt_backend.modal.dto.MqttForm;
import com.example.mqtt_backend.modal.dto.SoundBoxForm;
import com.example.mqtt_backend.service.MqttService;
import com.example.mqtt_backend.service.SoundBoxService;
import org.springframework.ui.Model;
import com.example.mqtt_backend.constant.ResourcePath;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Controller
public class SoundBoxController {

    private final SoundBoxService soundBoxService;
    private final MqttService mqttService;

    public SoundBoxController(SoundBoxService soundBoxService, MqttService mqttService) {
        this.soundBoxService = soundBoxService;
        this.mqttService = mqttService;
    }

    @GetMapping("/")
    public String home() {
        return ResourcePath.HOME_PAGE_URL;
    }

    @GetMapping(ResourcePath.DASHBOARD)
    public String dashboard(Model model) {
        List<String> labels = Stream.of(SoundBoxStatus.values()).map(Enum::name).toList();
        List<SoundBoxDetails> soundBoxDetails = soundBoxService.getAllSoundBoxDetails();
        List<Integer> data = new ArrayList<>();
        List<MqttProcess> processes = Arrays.asList(MqttProcess.values());
        MqttForm mqttForm = new MqttForm();
        // get the count of each sound box status
        for (SoundBoxStatus status : SoundBoxStatus.values()) {
            int count = (int) soundBoxDetails.stream().filter(soundBox -> Objects.equals(soundBox.getSoundBoxStatus(), status)).count();
            data.add(count);
        }

        System.out.println(labels);
        System.out.println(data);

        model.addAttribute("count_labels", labels);
        model.addAttribute("count_data", data);
        model.addAttribute("count_title", "Sound Box Dashboard");
        model.addAttribute("total_sound_boxes", 450);
        model.addAttribute("mqtt_form", mqttForm);
        model.addAttribute("processes", processes);
        model.addAttribute("sound_box_details", soundBoxDetails);
//        model.addAttribute("message", "dashboard");

        return ResourcePath.DASHBOARD_PAGE;
    }

    @GetMapping(ResourcePath.SOUND_BOX)
    public String soundBox(Model model) {
        List<SoundBoxDetails> soundBoxDetails = soundBoxService.getAllSoundBoxDetails();
        SoundBoxForm soundBoxForm = new SoundBoxForm();
        System.out.println(Arrays.toString(SoundBoxStatus.values()));
        model.addAttribute("sound_box_details", soundBoxDetails);
        model.addAttribute("sound_box_form", soundBoxForm);
        model.addAttribute("sound_box_status", SoundBoxStatus.values());
        return ResourcePath.SOUND_BOX_PAGE;
    }



    @PostMapping(ResourcePath.SOUND_BOX_SAVE)
    public String saveSoundBox(SoundBoxDetails soundBoxDetails,RedirectAttributes model) {
        try {
            soundBoxService.saveSoundBoxDetails(soundBoxDetails);
            model.addFlashAttribute("message", "Successfully saved sound box details");
        }catch (Exception e){
            model.addFlashAttribute("message", "Failed to save sound box details");
        }
        return "redirect:/" + ResourcePath.SOUND_BOX;
    }

    @GetMapping(ResourcePath.SOUND_BOX_DELETE + "/{soundBoxId}")
    public String deleteSoundBox(@PathVariable("soundBoxId") long id, RedirectAttributes model) {
        try {
            soundBoxService.deleteSoundBoxDetails(id);
            model.addFlashAttribute("message", "Successfully deleted sound box details");
        }catch (Exception e){
            model.addFlashAttribute("message", "Failed to delete sound box details");
        }
        return "redirect:/" + ResourcePath.SOUND_BOX;
    }

    @PostMapping(ResourcePath.SOUND_BOX_UPDATE + "/{id}")
    public String updateSoundBox(@PathVariable long id, @ModelAttribute SoundBoxForm soundBoxDetails, RedirectAttributes model){
        try {
            SoundBoxDetails soundBox = soundBoxService.getSoundBoxDetails(id);
            soundBox.setSerialNumber(soundBoxDetails.getUpdateSerialNumber());
            soundBox.setSoundBoxStatus(soundBoxDetails.getUpdateStatus());
            soundBoxService.updateSoundBoxDetails(id, soundBox);
            model.addFlashAttribute("message", "Successfully updated sound box details");
        }catch (Exception e){
            model.addFlashAttribute("message", "Failed to update sound box details");
        }
        return "redirect:/" + ResourcePath.SOUND_BOX;
    }

    @PostMapping(ResourcePath.TESTING_MQTT_SEND)
    public String sendMqttMessage(@ModelAttribute MqttForm mqttForm , RedirectAttributes model){
        try{
            mqttService.mqttPublishViaPortal(mqttForm.getMessage(),mqttForm.getTopic());
            model.addFlashAttribute("message", "Successfully sent message to MQTT broker ,Topic: "+mqttForm.getTopic());
        }catch (Exception e){
            System.out.println("Broker not connected");
            model.addFlashAttribute("message", "Failed to send message to MQTT broker");
            System.out.println(e.getMessage());
        }
        return ResourcePath.HOME_PAGE_URL;
    }


}
