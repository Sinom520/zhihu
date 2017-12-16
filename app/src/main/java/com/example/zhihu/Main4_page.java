package com.example.zhihu;

/**
 * Created by hp on 2017/12/9.
 */

public class Main4_page {
    private String main4_name;
    private String main4_id;
    private String main4_thumbnail;
    private String main4_description;

    public Main4_page(String main4_name,String main4_id,String main4_description,String main4_thumbnail) {
        this.main4_id = main4_id;
        this.main4_name = main4_name;
        this.main4_description = main4_description;
        this.main4_thumbnail = main4_thumbnail;
    }

   public String getMain4_name() {
        return main4_name;
   }

   public String getMain4_id() {
        return main4_id;
   }

   public String getMain4_description() {
        return main4_description;
   }

   public String getMain4_thumbnail() {
        return main4_thumbnail;
   }
}
