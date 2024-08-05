package com.backend.management.service;

import com.backend.management.exception.AnnouncementNotExistException;
import com.backend.management.model.Announcement;
import com.backend.management.model.User;
import com.backend.management.repository.AnnouncementRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AnnouncementService {

    private final AnnouncementRepository announcementRepository;

    public AnnouncementService(AnnouncementRepository announcementRepository) {
        this.announcementRepository = announcementRepository;
    }


    //1.add announcement
    public void add(Announcement announcement) {
        announcementRepository.save(announcement);
    }

    //2.announcements by manager
    public List<Announcement> announcementsByManager(String username) {
        User.Builder userBuilder = new User.Builder();
        userBuilder.setUsername(username);
        User user = userBuilder.build();
        return announcementRepository.findByManagerId(user);
    }

    //3.get announcement by id
    public Announcement findByIdAndManager(Long announcementId, String username) throws AnnouncementNotExistException {
        Announcement announcement = announcementRepository.findByIdAndManagerId(announcementId, new User.Builder().setUsername(username).build());

        if(announcement == null) {
            throw new AnnouncementNotExistException("Announcement doesn't exist");
        }
        return announcement;
    }

    //4.delete announcement by id
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void delete(Long announcementId, String username) throws AnnouncementNotExistException {
        Announcement announcement = announcementRepository.findByIdAndManagerId(announcementId, new User.Builder().setUsername(username).build());

        if(announcement == null) {
            throw new AnnouncementNotExistException("Announcement doesn't exist");
        }
        announcementRepository.deleteById(announcementId);
    }

    //5.get all announcements
    public List<Announcement> allAnnouncements(String username) {
        return announcementRepository.findAll();
    }

}
