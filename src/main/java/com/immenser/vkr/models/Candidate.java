package com.immenser.vkr.models;

import java.math.BigDecimal;
import java.sql.Date;

public class Candidate {

    private int candidate_id;
    private int desired_position_id;
    private String surname;
    private String name;
    private String patronymic;
    private Date date_of_birthday;
    private int experience;
    private String mail;
    private String phone;
    private String photo;
    private BigDecimal extraversion;
    private BigDecimal agreebleness;
    private BigDecimal conscientiousness;
    private BigDecimal openness;
    private BigDecimal neuroticism;

    public Candidate(int candidate_id, int desired_position_id, String surname, String name, String patronymic, Date date_of_birthday, int experience, String mail, String phone, String photo, BigDecimal extraversion, BigDecimal agreebleness, BigDecimal conscientiousness, BigDecimal openness, BigDecimal neuroticism) {
        this.candidate_id = candidate_id;
        this.desired_position_id = desired_position_id;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.date_of_birthday = date_of_birthday;
        this.experience = experience;
        this.mail = mail;
        this.phone = phone;
        this.photo = photo;
        this.extraversion = extraversion;
        this.agreebleness = agreebleness;
        this.conscientiousness = conscientiousness;
        this.openness = openness;
        this.neuroticism = neuroticism;
    }

    public Candidate(){}

    public int getCandidate_id() {
        return candidate_id;
    }

    public void setCandidate_id(int candidate_id) {
        this.candidate_id = candidate_id;
    }

    public int getDesired_position_id() {
        return desired_position_id;
    }

    public void setDesired_position_id(int desired_position_id) {
        this.desired_position_id = desired_position_id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public Date getDate_of_birthday() {
        return date_of_birthday;
    }

    public void setDate_of_birthday(Date date_of_birthday) {
        this.date_of_birthday = date_of_birthday;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public BigDecimal getExtraversion() {
        return extraversion;
    }

    public void setExtraversion(BigDecimal extraversion) {
        this.extraversion = extraversion;
    }

    public BigDecimal getAgreebleness() {
        return agreebleness;
    }

    public void setAgreebleness(BigDecimal agreebleness) {
        this.agreebleness = agreebleness;
    }

    public BigDecimal getConscientiousness() {
        return conscientiousness;
    }

    public void setConscientiousness(BigDecimal conscientiousness) {
        this.conscientiousness = conscientiousness;
    }

    public BigDecimal getOpenness() {
        return openness;
    }

    public void setOpenness(BigDecimal openness) {
        this.openness = openness;
    }

    public BigDecimal getNeuroticism() {
        return neuroticism;
    }

    public void setNeuroticism(BigDecimal neuroticism) {
        this.neuroticism = neuroticism;
    }
}
