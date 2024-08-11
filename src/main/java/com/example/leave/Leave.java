package com.example.leave;

import java.util.Date;

public class Leave {
    private Integer id;
    private String name;
    private String from_date;
    private String to_date;
    private String subject;
    private String reason;
    private Integer statuss;

    private Integer leave_id;

    public Leave(Integer id, String name, String from_date, String to_date, String subject, String reason, Integer statuss, Integer leave_id) {
        this.id = id;
        this.name = name;
        this.from_date = from_date;
        this.to_date = to_date;
        this.subject = subject;
        this.reason = reason;
        this.statuss = statuss;
        this.leave_id = leave_id;
    }
    public Leave (){}
    public Integer getLeave_id() {
        return leave_id;
    }

    public void setLeave_id(Integer leave_id) {
        this.leave_id = leave_id;
    }

    public Leave(Integer id, String name, String from_date, String to_date, String subject, String reason) {
        this.id = id;
        this.name = name;
        this.from_date = from_date;
        this.to_date = to_date;
        this.subject = subject;
        this.reason = reason;
    }

    public Leave(Integer id, String name, String from_date, String to_date, String subject, String reason, Integer statuss) {
        this.id = id;
        this.name = name;
        this.from_date = from_date;
        this.to_date = to_date;
        this.subject = subject;
        this.reason = reason;
        this.statuss = statuss;
    }


    public Integer getStatuss() {
        return statuss;
    }

    public void setStatuss(Integer statuss) {
        this.statuss = statuss;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFrom_date() {
        return from_date;
    }

    public void setFrom_date(String from_date) {
        this.from_date = from_date;
    }

    public String getTo_date() {
        return to_date;
    }

    public void setTo_date(String to_date) {
        this.to_date = to_date;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "Leave{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", from_date='" + from_date + '\'' +
                ", to_date='" + to_date + '\'' +
                ", subject='" + subject + '\'' +
                ", reason='" + reason + '\'' +
                '}';
    }
}
