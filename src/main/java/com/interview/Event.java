package com.interview;

public class Event {

    private String id;
    private State state;
    private String type;
    private String host;
    private long timeStamp;
    private long duration;
    private boolean alert;

    public Event(String id,State state, long timeStamp) {
        this(id, state, "", "", timeStamp);
    }
    public Event(String id,State state, String type,String host,long timeStamp) {
        this.id = id;
        this.state = state;
        this.type = type;
        this.host = host;
        this.timeStamp = timeStamp;
    }


    public String getId() {
        return id;
    }
    public State getState() {
        return state;
    }
    public String getType() {
        return type;
    }
    public String getHost() {
        return host;
    }
    public long getTimeStamp() {
        return timeStamp;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public long getDuration(){
        return duration;
    }

    public boolean isAlert() {
        return alert;
    }

    public void setAlert(boolean alert) {
        this.alert = alert;
    }


    enum State{
        STARTED,
        FINISHED
    }
}
