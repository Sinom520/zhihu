package com.example.zhihu;

/**
 * Created by hp on 2017/12/16.
 */

public class Main9_page {
    private String main9_name;
    private String main9_id;
    private String main9_thumbnail;
    private String main9_description;

    public Main9_page(String main9_name,String main9_id,String main9_description,String main9_thumbnail) {
        this.main9_id = main9_id;
        this.main9_name = main9_name;
        this.main9_description = main9_description;
        this.main9_thumbnail = main9_thumbnail;
    }

    public String getMain9_name() {
        return main9_name;
    }

    public String getMain9_id() {
        return main9_id;
    }

    public String getMain9_description() {
        return main9_description;
    }

    public String getMain9_thumbnail() {
        return main9_thumbnail;
    }
}
