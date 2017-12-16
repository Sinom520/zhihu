package com.example.zhihu;

/**
 * Created by hp on 2017/12/10.
 */

public class Main6_page {
    private String author6;
    private String content_6;
    private String likes6;
    private String time6;
    private String M6_image;

    public Main6_page(String author6,String likes6,String time6,String content_6,String M6_image) {
        this.author6 = author6;
        this.content_6 = content_6;
        this.likes6 = likes6;
        this.time6 = time6;
        this.M6_image = M6_image;
    }

    public String getAuthor6() {
        return author6;
    }

    public String getContent_6() {
        return content_6;
    }

    public String getLikes6() {
        return likes6;
    }

    public String getTime6() {
        return time6;
    }

    public String getM6_image() {
        return M6_image;
    }
}
