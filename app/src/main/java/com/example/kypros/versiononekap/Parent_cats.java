package com.example.kypros.versiononekap;

public class Parent_cats {

    private String parent_category_name;
    private String image_parent;

    public Parent_cats(){

    }

    public Parent_cats(String parent_category_name, String image_parent) {
        this.parent_category_name = parent_category_name;
        this.image_parent = image_parent;
    }

    public String getParent_category_name() {
        return parent_category_name;
    }

    public void setParent_category_name(String parent_category_name) {
        this.parent_category_name = parent_category_name;
    }

    public String getImage_parent() {
        return image_parent;
    }

    public void setImage_parent(String image_parent) {
        this.image_parent = image_parent;
    }


}
