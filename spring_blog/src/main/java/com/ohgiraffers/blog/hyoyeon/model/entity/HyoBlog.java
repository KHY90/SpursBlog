package com.ohgiraffers.blog.hyoyeon.model.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "hyoyeon_table")
public class HyoBlog {

    @Id
    @Column(name = "blog_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int blogNo;

    @Column(name = "blog_title", unique = true, nullable = false)
    private String blogTitle;

    @Column(name = "blog_content", nullable = false, length = 5000 )
    private String blogContet;

    @Column(name = "creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    public HyoBlog( ) {

    }

    public int getBlogNo() {
        return blogNo;
    }

    public void setBlogNo(int blogNo) {
        this.blogNo = blogNo;
    }

    public String getBlogTitle() {
        return blogTitle;
    }

    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }

    public String getBlogContet() {
        return blogContet;
    }

    public void setBlogContet(String blogContet) {
        this.blogContet = blogContet;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public HyoBlog(int blogNo, String blogTitle, String blogContet, Date createDate) {
        this.blogNo = blogNo;
        this.blogTitle = blogTitle;
        this.blogContet = blogContet;
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "HyoBlog{" +
                "blogNo=" + blogNo +
                ", blogTitle='" + blogTitle + '\'' +
                ", blogContet='" + blogContet + '\'' +
                ", createDate=" + createDate +
                '}';
    }
}
