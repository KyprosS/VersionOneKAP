package com.example.kypros.versiononekap;

public class services_model {

    private String address;
    private String description;
    private String email;
    private String fax;
    private String id_child_category;
    private String id_district;
    private String id_parent_category;
    private String id_timetable;
    private String id_user;
    private String latitude;
    private String longitude;
    private String name;
    private String phone;
    private String phone2;
    private String postalcode;
    private String price;
    private String rating;
    private String title;

    public services_model(){

    }

    public services_model(String address, String description, String email, String fax, String id_child_category, String id_district, String id_parent_category, String id_timetable, String id_user, String latitude, String longitude, String name, String phone, String phone2, String postalcode, String price, String rating, String title) {
        this.address = address;
        this.description = description;
        this.email = email;
        this.fax = fax;
        this.id_child_category = id_child_category;
        this.id_district = id_district;
        this.id_parent_category = id_parent_category;
        this.id_timetable = id_timetable;
        this.id_user = id_user;
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
        this.phone = phone;
        this.phone2 = phone2;
        this.postalcode = postalcode;
        this.price = price;
        this.rating = rating;
        this.title = title;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getId_child_category() {
        return id_child_category;
    }

    public void setId_child_category(String id_child_category) {
        this.id_child_category = id_child_category;
    }

    public String getId_district() {
        return id_district;
    }

    public void setId_district(String id_district) {
        this.id_district = id_district;
    }

    public String getId_parent_category() {
        return id_parent_category;
    }

    public void setId_parent_category(String id_parent_category) {
        this.id_parent_category = id_parent_category;
    }

    public String getId_timetable() {
        return id_timetable;
    }

    public void setId_timetable(String id_timetable) {
        this.id_timetable = id_timetable;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
