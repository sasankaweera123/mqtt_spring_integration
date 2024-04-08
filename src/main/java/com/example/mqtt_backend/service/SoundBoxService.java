package com.example.mqtt_backend.service;

import com.example.mqtt_backend.constant.ResourcePath;
import com.example.mqtt_backend.entity.SoundBoxDetails;
import com.example.mqtt_backend.repository.SoundBoxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SoundBoxService {

    private final SoundBoxRepository soundBoxRepository;

    @Autowired
    public SoundBoxService(SoundBoxRepository soundBoxRepository) {
        this.soundBoxRepository = soundBoxRepository;
    }

    public boolean isSoundBoxDetailsExist(Long id) {
        return soundBoxRepository.existsById(id);
    }

    public List<SoundBoxDetails> getAllSoundBoxDetails() {
        return soundBoxRepository.findAll();
    }

    public void saveSoundBoxDetails(SoundBoxDetails soundBoxDetails) {
        if(soundBoxDetails == null) {
            throw new IllegalArgumentException("SoundBoxDetails is null");
        }
        soundBoxDetails.setTopic(ResourcePath.SOUNDBOX_SERIAL_HEADER + soundBoxDetails.getSerialNumber());
        soundBoxRepository.save(soundBoxDetails);
    }

    public SoundBoxDetails getSoundBoxDetails(Long id) {
        return soundBoxRepository.findById(id).orElse(null);
    }

    public void deleteSoundBoxDetails(Long id) {
        if(isSoundBoxDetailsExist(id)) {
            soundBoxRepository.deleteById(id);
        }
    }

    public void updateSoundBoxDetails(Long id, SoundBoxDetails soundBoxDetails) {
        SoundBoxDetails soundBox = soundBoxRepository.findById(id).orElse(null);
        if(soundBox == null) {
            throw new IllegalArgumentException("SoundBoxDetails not found");
        }
        if(soundBoxDetails == null) {
            throw new IllegalArgumentException("SoundBoxDetails is null");
        }
        if(!soundBoxDetails.getSerialNumber().isEmpty() && !soundBoxDetails.getSerialNumber().equals(soundBox.getSerialNumber())){
            soundBox.setSerialNumber(soundBoxDetails.getSerialNumber());
            soundBoxDetails.setTopic(ResourcePath.SOUNDBOX_SERIAL_HEADER + soundBoxDetails.getSerialNumber());
        }
        if(!soundBoxDetails.getSoundBoxStatus().equals(soundBox.getSoundBoxStatus())){
            soundBox.setSoundBoxStatus(soundBoxDetails.getSoundBoxStatus());
        }
        soundBoxRepository.save(soundBox);

    }

    public int getSoundBoxCount() {
        return soundBoxRepository.findAll().size();
    }
}
