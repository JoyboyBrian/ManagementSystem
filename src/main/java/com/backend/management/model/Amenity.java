package com.backend.management.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "Amenity")
@JsonDeserialize(builder = Amenity.Builder.class)
public class Amenity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int amenity_id;
    private String amenity_name;
    private int amenity_capacity;
    private String amenity_image;



    private Amenity(Builder builder){

        this.amenity_id = amenity_id;
        this.amenity_name = amenity_name;
        this.amenity_capacity = amenity_capacity;
        this.amenity_image = amenity_image;
    }

    public Amenity() {

    }

    public int getAmenityID(){
        return amenity_id;
    }
    public String getAmenityName(){
        return amenity_name;
    }
    public int getAmenityCapacity(){
        return amenity_capacity;
    }
    public String getAmenityImage(){
        return amenity_image;
    }

    public static class Builder{
        @JsonProperty("amenityId")
        private int amenity_id;
        @JsonProperty("amenityName")
        private String amenity_name;
        @JsonProperty("amenityCapacity")
        private int amenity_capacity;
        @JsonProperty("amenityImage")
        private String amenity_image;

        public Builder setAmenityId(int amenity_id){
            this.amenity_id = amenity_id;
            return this;
        }
        public Builder setAmenityName(String amenity_name){
            this.amenity_name = amenity_name;
            return this;
        }
        public Builder setAmenityCapacity(int amenity_capacity){
            this.amenity_capacity = amenity_capacity;
            return this;
        }
        public Builder setAmenityImage(String amenity_image){
            this.amenity_image = amenity_image;
            return this;
        }
        public Amenity build() {
            return new Amenity(this);
        }

    }



}
