package com.example.kypros.versiononekap;

public class services_model {

    private String address;
    private String description;
    private String email;
    private String fax;
    private String id_child_category;
    private String id_district;
    private String id_timetable;
    private String image;
    private String latitude;
    private String longitude;
    private String name;
    private String parent_category;
    private String phone;
    private String phone2;
    private String postalcode;
    private String price;
    private String rating;
    private String title;
    private String logo_image;

    public services_model(){

    }

    public services_model(String address, String description, String email, String fax, String id_child_category, String id_district, String id_timetable, String image, String latitude, String longitude, String name, String parent_category, String phone, String phone2, String postalcode, String price, String rating, String title, String logo_image) {
        this.address = address;
        this.description = description;
        this.email = email;
        this.fax = fax;
        this.id_child_category = id_child_category;
        this.id_district = id_district;
        this.id_timetable = id_timetable;
        this.image = image;
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
        this.parent_category = parent_category;
        this.phone = phone;
        this.phone2 = phone2;
        this.postalcode = postalcode;
        this.price = price;
        this.rating = rating;
        this.title = title;
        this.logo_image = logo_image;
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

    public String getId_timetable() {
        return id_timetable;
    }

    public void setId_timetable(String id_timetable) {
        this.id_timetable = id_timetable;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public String getParent_category() {
        return parent_category;
    }

    public void setParent_category(String parent_category) {
        this.parent_category = parent_category;
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

    public String getLogo_image() {
        return logo_image;
    }

    public void setLogo_image(String logo_image) {
        this.logo_image = logo_image;
    }
}
