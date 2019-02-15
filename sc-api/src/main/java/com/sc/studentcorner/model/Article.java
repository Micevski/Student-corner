package com.sc.studentcorner.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Enumerated(EnumType.STRING)
    private ArticleCategory category;

    @Lob
    @Column(name = "article_text")
    private String articleText;

    @Column(name = "thumbnail")
    private String thumbnail;

    @Column(name = "date_created")
    private LocalDateTime dateCreated;

    public Article() {
    }

    public Article(String title, ArticleCategory category,  String articleText, String thumbnail, LocalDateTime dateCreated) {
        this.title = title;
        this.category = category;
        this.articleText = articleText;
        this.thumbnail = thumbnail;
        this.dateCreated = dateCreated;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArticleCategory getCategory() {
        return category;
    }

    public void setCategory(ArticleCategory category) {
        this.category = category;
    }

    public String getArticleText() {
        return articleText;
    }

    public void setArticleText(String articleText) {
        this.articleText = articleText;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }
}
