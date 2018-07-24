package com.example.kypros.versiononekap;

public class Cld_cats {

    private String child_category_name;
    private String id_parent_category;
    private String image;

    public Cld_cats(String child_category_name, String id_parent_category, String image) {
        this.child_category_name = child_category_name;
        this.id_parent_category = id_parent_category;
        this.image = image;
    }

    public String getChild_category_name() {
        return child_category_name;
    }

    public void setChild_category_name(String child_category_name) {
        this.child_category_name = child_category_name;
    }

    public String getId_parent_category() {
        return id_parent_category;
    }

    public void setId_parent_category(String id_parent_category) {
        this.id_parent_category = id_parent_category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    public Cld_cats(){

    }

}
