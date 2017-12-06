package com.alienlab.my.entity;

import javax.persistence.*;

/**
 * Created by zhuliang on 2017/12/6.
 */
@Entity
@Table(name = "web_configuration")
public class WebConfiguration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "book_count")
    private String bookCount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBookCount() {
        return bookCount;
    }

    public void setBookCount(String bookCount) {
        this.bookCount = bookCount;
    }
}
