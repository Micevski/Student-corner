package com.sc.studentcorner.repository;

import com.sc.studentcorner.model.Article;
import com.sc.studentcorner.model.ArticleCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

     List<Article> findAllByCategory(ArticleCategory category);
     List<Article> findAll();
}
