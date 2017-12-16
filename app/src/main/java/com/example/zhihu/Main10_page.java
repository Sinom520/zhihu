package com.example.zhihu;

/**
 * Created by hp on 2017/12/16.
 */

public class Main10_page {
    private String main10_title;
    private String main10_id;
    private String main10_thumbnail;

    public Main10_page(String main10_title,String main10_id,String main10_thumbnail) {
        this.main10_id = main10_id;
        this.main10_title = main10_title;
        this.main10_thumbnail = main10_thumbnail;
    }

    public String getMain10_title() {
        return main10_title;
    }

    public String getMain10_id() {
        return main10_id;
    }

    public String getMain10_thumbnail() {
        return main10_thumbnail;
    }
}
