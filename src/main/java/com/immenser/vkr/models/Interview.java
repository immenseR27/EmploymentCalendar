package com.immenser.vkr.models;

import java.sql.Date;
import java.sql.Time;

public class Interview {
    private Date interview_date;
    private Time interview_time;

    public Interview() {
    }

    public Interview(Date interview_date, Time interview_time) {
        this.interview_date = interview_date;
        this.interview_time = interview_time;
    }

    public Date getInterview_date() {
        return interview_date;
    }

    public void setInterview_date(Date interview_date) {
        this.interview_date = interview_date;
    }

    public Time getInterview_time() {
        return interview_time;
    }

    public void setInterview_time(Time interview_time) {
        this.interview_time = interview_time;
    }
}
