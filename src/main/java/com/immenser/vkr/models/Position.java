package com.immenser.vkr.models;

public class Position {
    private int position_id;
    private String position;

    public Position(){

    }

    public Position(int position_id, String position) {
        this.position_id = position_id;
        this.position = position;
    }

    public int getPosition_id() {
        return position_id;
    }

    public void setPosition_id(int position_id) {
        this.position_id = position_id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
