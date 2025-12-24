package com.example.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "locations")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    
    @Column(nullable = false)
    private Double latitude;
    
    @Column(nullable = false)
    private Double longitude;
    
    // Constructors
    public Location() {
    }
    
    public Location(Long id, String name, Double latitude, Double longitude) {
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }
    
    // Builder pattern method
    public static LocationBuilder builder() {
        return new LocationBuilder();
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Double getLatitude() {
        return latitude;
    }
    
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
    
    public Double getLongitude() {
        return longitude;
    }
    
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
    
    // Builder class
    public static class LocationBuilder {
        private Long id;
        private String name;
        private Double latitude;
        private Double longitude;
        
        public LocationBuilder id(Long id) {
            this.id = id;
            return this;
        }
        
        public LocationBuilder name(String name) {
            this.name = name;
            return this;
        }
        
        public LocationBuilder latitude(Double latitude) {
            this.latitude = latitude;
            return this;
        }
        
        public LocationBuilder longitude(Double longitude) {
            this.longitude = longitude;
            return this;
        }
        
        public Location build() {
            return new Location(id, name, latitude, longitude);
        }
    }
}