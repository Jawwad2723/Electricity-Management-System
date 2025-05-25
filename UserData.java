package com.mycompany.project;
import java.io.Serializable;

public class UserData implements Serializable {
    private String name;
    private String cnic;
    private String phone;
    private String address;
    private String password;
    private String selectedCity;
    private boolean alreadyConsumer;
    private boolean agreedToTC;

    public String getName() {
        return name;
    }

    public String getCnic() {
        return cnic;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getPassword() {
        return password;
    }

    public String getSelectedCity() {
        return selectedCity;
    }

    public boolean isAlreadyConsumer() {
        return alreadyConsumer;
    }

    public boolean hasAgreedToTC() {
        return agreedToTC;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCnic(String cnic) {
        this.cnic = cnic;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSelectedCity(String selectedCity) {
        this.selectedCity = selectedCity;
    }

    public void setAlreadyConsumer(boolean alreadyConsumer) {
        this.alreadyConsumer = alreadyConsumer;
    }

    public void setAgreedToTC(boolean agreedToTC) {
        this.agreedToTC = agreedToTC;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "name='" + name + '\'' +
                ", cnic='" + cnic + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", selectedCity='" + selectedCity + '\'' +
                ", alreadyConsumer=" + alreadyConsumer +
                ", agreedToTC=" + agreedToTC +
                '}';
    }
}