package com.example.zhihu;

/**
 * Created by hp on 2017/12/9.
 */

public class Main3_page {
    private String main3_title;
    private String main3_id;
    private String M3_image;

    public Main3_page(String main3_title,String main3_id,String M3_image) {
        this.main3_id = main3_id;
        this.main3_title = main3_title;
        this.M3_image = M3_image;
    }

    public String getMain3_title() {
        return main3_title;
    }

    public String getMain3_id() {
        return main3_id;
    }

    public String getM3_image() {
        return M3_image;
    }
}
