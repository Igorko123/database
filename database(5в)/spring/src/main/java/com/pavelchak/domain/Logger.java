package com.pavelchak.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "logger")
public class Logger {
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "logger_id", nullable = false)
    private Long id;
    @Column(name = "director", nullable = false, length = 50)
    private String director;
    @Column(name = "company", nullable = false, length = 90)
    private String company;
    @Column(name = "Action", nullable = false, length = 10)
    private String action;
    @Column(name = "time_stamp", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeStamp;
    @Column(name = "user", nullable = true, length = 50)
    private String user;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getDirector() {
        return director;
    }
    public void setDirector(String director) {
        this.director = director;
    }

    public String getCompany() {
        return company;
    }
    public void setCompany(String company) {
        this.company = company;
    }

    public String getAction() {
        return action;
    }
    public void setAction(String action) {
        this.action = action;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }
    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Logger that = (Logger) o;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (director != null ? !director.equals(that.director) : that.director != null) return false;
        if (company != null ? !company.equals(that.company) : that.company != null) return false;
        if (action != null ? !action.equals(that.action) : that.action != null) return false;
        if (timeStamp != null ? !timeStamp.equals(that.timeStamp) : that.timeStamp != null) return false;
        if (user != null ? !user.equals(that.user) : that.user != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (director != null ? director.hashCode() : 0);
        result = 31 * result + (company != null ? company.hashCode() : 0);
        result = 31 * result + (action != null ? action.hashCode() : 0);
        result = 31 * result + (timeStamp != null ? timeStamp.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }
}
