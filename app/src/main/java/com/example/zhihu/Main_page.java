package com.example.zhihu;

/**
 * Created by hp on 2017/12/7.
 */

public class Main_page {
    private String main_name;
    private String description;
    private String main_id;
    private String images;

    public Main_page(String main_name,String description,String main_id,String images) {
        this.main_name = main_name;
        this.description = description;
        this.main_id = main_id;
        this.images = images;
    }

    public String getMain_name(){
        return main_name;
    }

    public String getDescription() {
        return description;
    }

    public String getMain_id() {
        return main_id;
    }

    public String getImages() {
        return images;
    }
}
