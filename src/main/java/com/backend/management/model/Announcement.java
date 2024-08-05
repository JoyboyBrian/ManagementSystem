package com.backend.management.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "announcements")
@JsonDeserialize(builder = Announcement.Builder.class)
public class Announcement implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "manager")
    private User managerId;

    @JsonProperty("title")
    private String announcementTitle;
    private String content;
    private LocalDate time;
    private Boolean priority;


    public Announcement() {}
    private Announcement(Builder builder) {
        this.id = builder.id;
        this.managerId = builder.managerId;
        this.announcementTitle = builder.announcementTitle;;
        this.content = builder.content;
        this.time = builder.time;
        this.priority = builder.priority;
    }

    public Long getId() {
        return id;
    }

    public User getManagerId() {
        return managerId;
    }

    public String getAnnouncementTitle() {
        return announcementTitle;
    }

    public String getContent() {
        return content;
    }

    public LocalDate getTime() {
        return time;
    }

    public Boolean getPriority() {
        return priority;
    }

    public static class Builder {

        @JsonProperty("id")
        private Long id;

        @JsonProperty("manager")
        private User managerId;

        @JsonProperty("title")
        private String announcementTitle;

        @JsonProperty("content")
        private String content;

        @JsonProperty("time")
        private LocalDate time;

        @JsonProperty("priority")
        private Boolean priority;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setManagerId(User managerId) {
            this.managerId = managerId;
            return this;
        }

        public Builder setAnnouncementTitle(String announcementTitle) {
            this.announcementTitle = announcementTitle;
            return this;
        }

        public Builder setContent(String content) {
            this.content = content;
            return this;
        }

        public Builder setTime(LocalDate time) {
            this.time = time;
            return this;
        }

        public Builder setPriority(Boolean priority) {
            this.priority = priority;
            return this;
        }

        public Announcement build() {
            return new Announcement(this);
        }
    }
}
