package org.uni2biz.API;

public class RegistrationRequest {
    private String user, password, firstname, lastname, phone, city, email;
    private int lang_id, sex_id, subscribe, user_type;

    public RegistrationRequest(String user, String password, String firstname, String lastname, String phone, String city, String email, int lang_id, int sex_id, int subscribe, int user_type) {
        this.user = user;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
        this.city = city;
        this.email = email;
        this.lang_id = lang_id;
        this.sex_id = sex_id;
        this.subscribe = subscribe;
        this.user_type = user_type;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getLang_id() {
        return lang_id;
    }

    public void setLang_id(int lang_id) {
        this.lang_id = lang_id;
    }

    public int getSex_id() {
        return sex_id;
    }

    public void setSex_id(int sex_id) {
        this.sex_id = sex_id;
    }

    public int getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(int subscribe) {
        this.subscribe = subscribe;
    }

    public int getUser_type() {
        return user_type;
    }

    public void setUser_type(int user_type) {
        this.user_type = user_type;
    }
}
