package com.example.myapplication5;

public class Note {
    private long id;
    private String text;
    private long timestamp;
    private int priority;
    private int textColor;

    public Note(String text, int priority, int textColor) {
        this.text = text;
        this.priority = priority;
        this.textColor = textColor;
        this.timestamp = System.currentTimeMillis();
    }

    public Note(long id, String text, long timestamp, int priority, int textColor) {
        this.id = id;
        this.text = text;
        this.timestamp = timestamp;
        this.priority = priority;
        this.textColor = textColor;
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    public String getText() { return text; }
    public void setText(String text) { this.text = text; }
    public long getTimestamp() { return timestamp; }
    public void setTimestamp(long timestamp) { this.timestamp = timestamp; }
    public int getPriority() { return priority; }
    public void setPriority(int priority) { this.priority = priority; }
    public int getTextColor() { return textColor; }
    public void setTextColor(int textColor) { this.textColor = textColor; }
}