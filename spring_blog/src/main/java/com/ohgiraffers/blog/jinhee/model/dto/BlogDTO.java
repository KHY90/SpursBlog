package com.ohgiraffers.blog.jinhee.model.dto;

import java.util.Date;

public class BlogDTO {
    private Long id;
    private String blogTitle;
    private String blogContent;
    private Date createDate; // Change from RegistrationDate to CreateDate

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBlogTitle() {
        return blogTitle;
    }

    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }

    public String getBlogContent() {
        return blogContent;
    }

    public void setBlogContent(String blogContent) {
        this.blogContent = blogContent;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    private int likes; // 좋아요 수

    // Getter와 Setter 추가
    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

}
