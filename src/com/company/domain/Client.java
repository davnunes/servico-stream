package com.company.domain;

public class Client {
    private String name;
    private String surname;
    private String birthDate;
    private String phoneNumber;
    private String email;
    private String cpf;

    public Client(String name, String surname, String birthDate, String phoneNumber, String email, String cpf) {
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.cpf = cpf;
    }

    public String getName() {
        return name + surname;
    }

    public void setName(String name) {
        this.name = name.split(" ")[0];
        this.surname = name.split(" ")[1];
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        return "Client{" +
                "name=" + name +
                ", surname=" + surname +
                ", email=" + email +
                '}';
    }
}