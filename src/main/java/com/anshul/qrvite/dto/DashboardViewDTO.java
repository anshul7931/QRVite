package com.anshul.qrvite.dto;

public class DashboardViewDTO {

	private String title;
    private String description;
    private String link;
    private boolean newTab; // true if target="_blank"

    // Constructors
    public DashboardViewDTO() {}

    public DashboardViewDTO(String title, String description, String link, boolean newTab) {
        this.title = title;
        this.description = description;
        this.link = link;
        this.newTab = newTab;
    }

    // Getters and setters
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getLink() { return link; }
    public void setLink(String link) { this.link = link; }

    public boolean isNewTab() { return newTab; }
    public void setNewTab(boolean newTab) { this.newTab = newTab; }

}
