package com.example.kypros.versiononekap;

public class Cld_cats {

    private String child_category_name;
    private String parent_category;
    private String image;

    public Cld_cats(){

    }

    public Cld_cats(String child_category_name, String parent_category, String image) {
        this.child_category_name = child_category_name;
        this.parent_category = parent_category;
        this.image = image;
    }

    public String getChild_category_name() {
        return child_category_name;
    }

    public void setChild_category_name(String child_category_name) {
        this.child_category_name = child_category_name;
    }

    public String getParent_category() {
        return parent_category;
    }

    public void setParent_category(String parent_category) {
        this.parent_category = parent_category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
