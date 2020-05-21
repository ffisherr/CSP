package com.ffisherr.csp.users;

import java.util.Date;

public class User {
    private Integer id;	//
    private Integer roleId;	// Роль
    private String  passsword;
    private String  CompanyName;
    private Integer ChiefId;
    private String  firstName;  // Имя
    private String  surName;    // Фамилия
    private String  secondName; // Отчество
    private Date    birthDate;  // Дата рождения
    private Date    endOfContract; // Дата окончания договора
    private String  email;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Date getEndOfContract() {
        return endOfContract;
    }

    public void setEndOfContract(Date endOfContract) {
        this.endOfContract = endOfContract;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasssword() {
        return passsword;
    }

    public void setPasssword(String passsword) {
        this.passsword = passsword;
    }


    public Integer getChiefId() {
        return ChiefId;
    }

    public void setChiefId(Integer chiefId) {
        ChiefId = chiefId;
    }

    public String toString() {
        String s = firstName+" "+secondName+" "+surName;
        return s;
    }

}
