package com.example.zhihu;

/**
 * Created by hp on 2017/12/16.
 */

public class Main8_page {
    private String main8_title;
    private String main8_id;
    private String main8_thumbnail;

    public Main8_page(String main8_title,String main8_id,String main8_thumbnail) {
        this.main8_id = main8_id;
        this.main8_title = main8_title;
        this.main8_thumbnail = main8_thumbnail;
    }

    public String getMain8_title() {
        return main8_title;
    }

    public String getMain8_id() {
        return main8_id;
    }

    public String getMain8_thumbnail() {
        return main8_thumbnail;
    }
}
