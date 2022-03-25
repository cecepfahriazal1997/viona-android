package com.viona.mobile.model;

public class NotificationModel {
    public String id, title, description, date;
    public boolean newNotif;

    public NotificationModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isNewNotif() {
        return newNotif;
    }

    public void setNewNotif(boolean newNotif) {
        this.newNotif = newNotif;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
