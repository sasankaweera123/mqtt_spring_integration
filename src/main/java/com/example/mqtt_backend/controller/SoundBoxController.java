package com.example.mqtt_backend.controller;

import com.example.mqtt_backend.entity.SoundBoxDetails;
import com.example.mqtt_backend.enumeration.Process;
import com.example.mqtt_backend.enumeration.SoundBoxStatus;
import com.example.mqtt_backend.service.SoundBoxService;
import org.springframework.ui.Model;
import com.example.mqtt_backend.constant.Endpoint;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
public class SoundBoxController {

    private final SoundBoxService soundBoxService;

    public SoundBoxController(SoundBoxService soundBoxService) {
        this.soundBoxService = soundBoxService;
    }

    @GetMapping("/")
    public String home() {
        return Endpoint.HOME_PAGE_URL;
    }

    @GetMapping(Endpoint.DASHBOARD)
    public String dashboard(Model model) {
        List<String> labels = Arrays.asList("Red", "Blue", "Yellow");
        List<Integer> data = Arrays.asList(300, 50, 100);

        model.addAttribute("count_labels", labels);
        model.addAttribute("count_data", data);
        model.addAttribute("count_title", "Sound Box Dashboard");
        model.addAttribute("total_sound_boxes", 450);

        return Endpoint.DASHBOARD_PAGE;
    }

    @GetMapping(Endpoint.SOUND_BOX)
    public String soundBox(Model model) {
        List<SoundBoxDetails> soundBoxDetails = soundBoxService.getAllSoundBoxDetails();
        System.out.println(soundBoxDetails);

        model.addAttribute("sound_box_details", soundBoxDetails);

        model.addAttribute("sound_box_status", SoundBoxStatus.values());
        return Endpoint.SOUND_BOX_PAGE;
    }

    @PostMapping(Endpoint.SOUND_BOX_SAVE)
    public String saveSoundBox(SoundBoxDetails soundBoxDetails) {
        System.out.println(soundBoxDetails);
        soundBoxService.saveSoundBoxDetails(soundBoxDetails);
        return "redirect:/" + Endpoint.SOUND_BOX;
    }

    @GetMapping(Endpoint.SOUND_BOX_DELETE + "/{soundBoxId}")
    public String deleteSoundBox(@PathVariable("soundBoxId") long id) {
        System.out.println(id);
        soundBoxService.deleteSoundBoxDetails(id);
        System.out.println("Done");
        return "redirect:/" + Endpoint.SOUND_BOX;
    }

    @PutMapping(Endpoint.SOUND_BOX_UPDATE + "/{id}")
    public String updateSoundBox(@PathVariable long id, SoundBoxDetails soundBoxDetails) {
        soundBoxService.updateSoundBoxDetails(id, soundBoxDetails);
        return "redirect:/" + Endpoint.SOUND_BOX;
    }


}
