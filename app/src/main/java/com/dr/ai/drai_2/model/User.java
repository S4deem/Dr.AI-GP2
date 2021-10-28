package com.dr.ai.drai_2.model;

public class User {
    String id;
    String name;
    String email;
    String personal_id;
    byte[] certificate;
    String gender;
    String city;
    String phone;
    String password;
    String iban;
    String approved;
    String type;
    String status;

    public User() {
    }

    public User(String id, String name, String email, String personal_id, byte[] certificate, String gender, String city, String phone, String password, String iban, String approved, String type, String status) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.personal_id = personal_id;
        this.certificate = certificate;
        this.gender = gender;
        this.city = city;
        this.phone = phone;
        this.password = password;
        this.iban = iban;
        this.approved = approved;
        this.type = type;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPersonal_id() {
        return personal_id;
    }

    public void setPersonal_id(String personal_id) {
        this.personal_id = personal_id;
    }

    public byte[] getCertificate() {
        return certificate;
    }

    public void setCertificate(byte[] certificate) {
        this.certificate = certificate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setApproved(String approved) {
        this.approved = approved;
    }

    public String getApproved() {
        return approved;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
