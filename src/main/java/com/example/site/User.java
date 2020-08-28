package com.example.site;

import java.util.List;

public class User {
    private String name;
    private String age;
    private String gender;
    private String phone;
    private String compesation;
    private String oboMne;
    private List<String>navik;

    public User() {
    }

    public User(String name, String age, String gender, String phone, String compesation, String oboMne, List<String> navik) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.phone = phone;
        this.compesation = compesation;
        this.oboMne = oboMne;
        this.navik = navik;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCompesation() {
        return compesation;
    }

    public void setCompesation(String compesation) {
        this.compesation = compesation;
    }

    public String getOboMne() {
        return oboMne;
    }

    public void setOboMne(String oboMne) {
        this.oboMne = oboMne;
    }

    public List<String> getNavik() {
        return navik;
    }

    public void setNavik(List<String> navik) {
        this.navik = navik;
    }
}
