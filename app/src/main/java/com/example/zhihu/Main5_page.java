package com.example.zhihu;

/**
 * Created by hp on 2017/12/10.
 */

public class Main5_page {
    private String author;
    private String content_1;
    private String likes;
    private String time;
    private String M5_image;

    public Main5_page(String author,String likes,String time,String content_1,String M5_image) {
        this.author = author;
        this.content_1 = content_1;
        this.likes = likes;
        this.time = time;
        this.M5_image = M5_image;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent_1() {
        return content_1;
    }

    public String getLikes() {
        return likes;
    }

    public String getTime() {
        return time;
    }

    public String getM5_image() {
        return M5_image;
    }
}
