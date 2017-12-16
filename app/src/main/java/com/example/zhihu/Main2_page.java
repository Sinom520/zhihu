package com.example.zhihu;

/**
 * Created by hp on 2017/12/9.
 */

public class Main2_page {
    private String main2_title;
    private String main2_data;
    private String display_date;
    private String main2_id;
    private String image;

    public Main2_page(String main2_title,String display_date,String main2_id,String main2_data,String image) {
        this.main2_data = main2_data;
        this.display_date = display_date;
        this.main2_id = main2_id;
        this.main2_title = main2_title;
        this.image = image;
    }

    public String getMain2_title() {
        return main2_title;
    }

    public String getMain2_data() {
        return main2_data;
    }

    public String getDisplay_date() {
        return display_date;
    }

    public String getMain2_id() {
        return main2_id;
    }

    public String getImage() {
        return image;
    }
}
