package com.example.mqtt_backend.service;

import com.example.mqtt_backend.constant.ResourcePath;
import com.example.mqtt_backend.entity.SoundBoxDetails;
import com.example.mqtt_backend.enumeration.BankCode;
import com.example.mqtt_backend.enumeration.SoundBoxStatus;
import com.example.mqtt_backend.repository.SoundBoxRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SoundBoxService {

    private final Logger logger = LoggerFactory.getLogger(UsersService.class);

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

    /**
     * Add SoundBoxDetails
     * @param soundBoxDetails The SoundBoxDetails to add
     */
    public void saveSoundBoxDetails(SoundBoxDetails soundBoxDetails) {
        if(soundBoxDetails == null) {
            logger.warn("SoundBoxDetails is null : SoundBoxDetails not saved");
            throw new IllegalArgumentException("SoundBoxDetails is null");
        }
        soundBoxDetails.setTopic(ResourcePath.SOUNDBOX_SERIAL_HEADER + soundBoxDetails.getSerialNumber());
        soundBoxRepository.save(soundBoxDetails);
        logger.info("SoundBoxDetails saved");
    }

    public SoundBoxDetails getSoundBoxDetails(Long id) {
        return soundBoxRepository.findById(id).orElse(null);
    }

    public void deleteSoundBoxDetails(Long id) {
        if(isSoundBoxDetailsExist(id)) {
            soundBoxRepository.deleteById(id);
            logger.info("SoundBoxDetails deleted");
        }
    }

    /**
     * Update SoundBoxDetails
     * @param id The id of the SoundBoxDetails to update
     * @param soundBoxDetails The SoundBoxDetails to update
     */
    public void updateSoundBoxDetails(Long id, SoundBoxDetails soundBoxDetails) {
        SoundBoxDetails updateSoundBox = soundBoxRepository.findById(id).orElse(null);
        System.out.println(updateSoundBox);
        System.out.println(soundBoxDetails);
        if(updateSoundBox == null) {
            logger.warn("SoundBoxDetails not found");
            throw new IllegalArgumentException("SoundBoxDetails not found");
        }
        if(soundBoxDetails == null) {
            logger.warn("SoundBoxDetails is null : SoundBoxDetails not updated");
            throw new IllegalArgumentException("SoundBoxDetails is null");
        }
        if(soundBoxDetails.equals(updateSoundBox)) {
            logger.warn("SoundBoxDetails and updateSoundBox are same : SoundBoxDetails not updated");
            throw new IllegalArgumentException("SoundBoxDetails not updated");
        }
        if(!soundBoxDetails.getSerialNumber().isEmpty() && !soundBoxDetails.getSerialNumber().equals(updateSoundBox.getSerialNumber())){
            updateSoundBox.setSerialNumber(soundBoxDetails.getSerialNumber());
        }
        if(!soundBoxDetails.getMName().isEmpty() && !soundBoxDetails.getMName().equals(updateSoundBox.getMName())){
            updateSoundBox.setMName(soundBoxDetails.getMName());
        }
        if(!soundBoxDetails.getMAddress().isEmpty() && !soundBoxDetails.getMAddress().equals(updateSoundBox.getMAddress())){
            updateSoundBox.setMAddress(soundBoxDetails.getMAddress());
        }
        if(!soundBoxDetails.getMid().isEmpty() && !soundBoxDetails.getMid().equals(updateSoundBox.getMid())){
            updateSoundBox.setMid(soundBoxDetails.getMid());
        }
        if(!soundBoxDetails.getTid().isEmpty() && !soundBoxDetails.getTid().equals(updateSoundBox.getTid())){
            updateSoundBox.setTid(soundBoxDetails.getTid());
        }
        if(soundBoxDetails.getDateAdded() != null && !soundBoxDetails.getDateAdded().equals(updateSoundBox.getDateAdded())){
            updateSoundBox.setDateAdded(soundBoxDetails.getDateAdded());
        }
        if(!(soundBoxDetails.getBankCode() == null) && !soundBoxDetails.getBankCode().equals(updateSoundBox.getBankCode())){
            updateSoundBox.setBankCode(soundBoxDetails.getBankCode());
        }
        if(!(soundBoxDetails.getSoundBoxStatus() == null) && !soundBoxDetails.getSoundBoxStatus().equals(updateSoundBox.getSoundBoxStatus())){
            updateSoundBox.setSoundBoxStatus(soundBoxDetails.getSoundBoxStatus());
        }
        System.out.println(updateSoundBox);
        soundBoxRepository.save(updateSoundBox);
        logger.info("SoundBoxDetails updated");
    }

    public int getSoundBoxCount() {
        return soundBoxRepository.findAll().size();
    }

    /**
     * Get SoundBoxDetails page
     * @param pageable The pageable to get page
     * @return The SoundBoxDetails page
     */
    public Page<SoundBoxDetails> getSoundBoxDetailsPage(Pageable pageable, BankCode bankCode, SoundBoxStatus soundBoxStatus) {
        if(bankCode == BankCode.ALL && soundBoxStatus == SoundBoxStatus.ALL) {
            return soundBoxRepository.findAll(pageable);
        }
        if(bankCode == BankCode.ALL) {
            return soundBoxRepository.findAllBySoundBoxStatus(soundBoxStatus, pageable);
        }
        if(soundBoxStatus == SoundBoxStatus.ALL) {
            return soundBoxRepository.findAllByBankCode(bankCode, pageable);
        }
        return soundBoxRepository.findAllByBankCodeAndSoundBoxStatus(bankCode, soundBoxStatus, pageable);
    }
}
