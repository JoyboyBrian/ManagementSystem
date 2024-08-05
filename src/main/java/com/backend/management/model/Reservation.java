package com.backend.management.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.boot.autoconfigure.condition.ConditionMessage;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "reservation")
@JsonDeserialize(builder = Reservation.Builder.class)
public class Reservation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int reservation_id;
    @JsonProperty("reservationName")
    private String reservation_name;
    @ManyToOne
    @JoinColumn(name = "amenity")
    private Amenity amenity;
    @ManyToOne
    @JoinColumn(name = "user")
    private User requester_id;

    @JsonProperty("userId")
    private String user_id;

    @JsonProperty("amenityId")
    private String amenity_id;

    @JsonProperty("guestCount")
    private int guest_count;
    private String date;
    @JsonProperty("startTime")
    private String start_time;
    @JsonProperty("endTime")
    private String end_time;

    public Reservation() {
    }


    private Reservation(Builder builder) {
        this.reservation_id = builder.reservation_id;
        this.reservation_name = builder.reservation_name;
        this.amenity = builder.amenity;
        this.requester_id = builder.requester_id;
        this.guest_count = builder.guest_count;
        this.date = builder.date;
        this.start_time = builder.start_time;
        this.end_time = builder.end_time;
        this.user_id = builder.userId;
        this.amenity_id = builder.amenityId;

    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getAmenity_id() {
        return amenity_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public int getReservationID() {
        return reservation_id;
    }

    public String getReservationName() {
        return reservation_name;
    }

    public Amenity getAmenityID() {
        return amenity;
    }
//     public User setUser(User username){
//        this.username = username;
//        return this;
//    }

    public int getGuestCount() {
        return guest_count;
    }

    public String getDate() {
        return date;
    }

    public String getStartTime() {
        return start_time;
    }

    public String getEndTime() {
        return end_time;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public static class Builder {
        @JsonProperty("reservationId")
        private int reservation_id;
        @JsonProperty("reservationName")
        private String reservation_name;
        @JsonProperty("amenity")
        private Amenity amenity;
        @JsonProperty("requesterId")
        private User requester_id;
        @JsonProperty("guestCount")
        private int guest_count;
        @JsonProperty("date")
        private String date;
        @JsonProperty("startTime")
        private String start_time;
        @JsonProperty("endTime")
        private String end_time;
        @JsonProperty("userId")
        private String userId;
        @JsonProperty("amenityId")
        private String amenityId;

        public Builder setReservationName(int reservation_id) {
            this.reservation_id = reservation_id;
            return this;
        }

        public Builder setReservationName(String reservation_name) {
            this.reservation_name = reservation_name;
            return this;
        }

        public Builder setRequesterID(User requester_id) {
            this.requester_id = requester_id;
            return this;
        }

        public Builder setUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder setAmenityId(String amenityId) {
            this.amenityId = amenityId;
            return this;
        }

        public Builder setAmenityID(Amenity amenity_id) {
            this.amenity = amenity_id;
            return this;
        }

        public Builder setGuestCount(int guest_count) {
            this.guest_count = guest_count;
            return this;
        }

        public Builder setDate(String date) {
            this.date = date;
            return this;
        }

        public Builder setStartTime(String start_time) {
            this.start_time = start_time;
            return this;
        }

        public Builder setEndTime(String end_time) {
            this.end_time = end_time;
            return this;
        }

        public Reservation build() {
            return new Reservation(this);
        }
    }

}
