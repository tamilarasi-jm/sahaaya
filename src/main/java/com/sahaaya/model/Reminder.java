package com.sahaaya.model;

import java.io.Serializable;

public class Reminder implements Serializable {

    private String id;
    private String elderlyName;
    private String type;        
    private String description;
    private String scheduledTime; 
    private String status;      
    private String createdAt;   // changed from LocalDateTime to String

    public Reminder() {
        this.createdAt = java.time.LocalDateTime.now().toString();
        this.status = "Pending";
    }

    public Reminder(String id, String elderlyName, String type,
                    String description, String scheduledTime) {
        this.id = id;
        this.elderlyName = elderlyName;
        this.type = type;
        this.description = description;
        this.scheduledTime = scheduledTime;
        this.status = "Pending";
        this.createdAt = java.time.LocalDateTime.now().toString();
    }

    // Getters and Setters

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getElderlyName() { return elderlyName; }
    public void setElderlyName(String elderlyName) { this.elderlyName = elderlyName; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getScheduledTime() { return scheduledTime; }
    public void setScheduledTime(String scheduledTime) { this.scheduledTime = scheduledTime; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
}