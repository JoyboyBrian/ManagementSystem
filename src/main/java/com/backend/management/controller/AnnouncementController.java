package com.backend.management.controller;

import com.backend.management.exception.InvalidAnnouncementDateException;
import com.backend.management.model.Announcement;
import com.backend.management.model.User;
import com.backend.management.service.AnnouncementService;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
public class AnnouncementController {

    private final AnnouncementService announcementService;

    public AnnouncementController(AnnouncementService announcementService) {
        this.announcementService = announcementService;
    }

    //1.add announcement
    @PostMapping(value = "/announcement")
    public void addAnnouncement(@RequestBody Announcement announcement, Principal principal) {

        LocalDate announcementDate = LocalDate.now();

        Announcement announce = new Announcement.Builder()
                .setAnnouncementTitle(announcement.getAnnouncementTitle())
                .setContent(announcement.getContent())
                .setTime(announcementDate)
                .setPriority(announcement.getPriority())
                .setManagerId(new User.Builder().setUsername(principal.getName()).build())
                .build();

        announcementService.add(announce);
    }


    //2.get announcement by manager
    @GetMapping(value = "/announcement")
    public List<Announcement> listAnnouncements(Principal principal) {
        return announcementService.announcementsByManager(principal.getName());
    }

    //3.get announcement by id
    @GetMapping(value = "/announcement/{announcementId}")
    public Announcement getAnnouncement(@PathVariable Long announcementId, Principal principal) {
        return announcementService.findByIdAndManager(announcementId, principal.getName());
    }

    //4.detele announcement by id
    @DeleteMapping(value = "/announcement/{announcementId}")
    public void deleteAnnouncement(@PathVariable Long announcementId, Principal principal) {
        announcementService.delete(announcementId, principal.getName());
    }

    //5.get all announcements
    @GetMapping(value = "announcements")
    public List<Announcement> getAllAnnouncements(Principal principal) {
        return announcementService.allAnnouncements(principal.getName());
    }


}