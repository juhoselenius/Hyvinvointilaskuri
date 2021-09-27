package fi.mobts.hyvinvointilaskuri;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class UserClass {
    private String name;
    private int weight;
    private int height;
    private String gender;

    public UserClass(String name, int weight, int height, String gender) {
    this.name = name;
    this.weight = weight;
    this.height = height;
    this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}