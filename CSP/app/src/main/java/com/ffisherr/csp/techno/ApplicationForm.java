package com.ffisherr.csp.techno;

import java.util.Date;

public class ApplicationForm {
    private int id;
    private Date whenCreated;
    private Date whenClosed;
    private int Status;
    private int equipmentId;
    private int userCreatorId;
    private int userSolverId;
    private String phoneNumber;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getWhenCreated() {
        return whenCreated;
    }

    public void setWhenCreated(Date whenCreated) {
        this.whenCreated = whenCreated;
    }

    public Date getWhenClosed() {
        return whenClosed;
    }

    public void setWhenClosed(Date whenClosed) {
        this.whenClosed = whenClosed;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public int getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(int equipmentId) {
        this.equipmentId = equipmentId;
    }

    public int getUserCreatorId() {
        return userCreatorId;
    }

    public void setUserCreatorId(int userCreatorId) {
        this.userCreatorId = userCreatorId;
    }

    public int getUserSolverId() {
        return userSolverId;
    }

    public void setUserSolverId(int userSolverId) {
        this.userSolverId = userSolverId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String toString() {
        String res = "";
        res += id + "\n";
        res += whenCreated + "\n";
        res += whenClosed + "\n";
        return res;
    }

}
