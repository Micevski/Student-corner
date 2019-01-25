package com.sc.studentcorner.service;

import com.sc.studentcorner.model.Article;
import com.sc.studentcorner.model.ArticleCategory;
import com.sc.studentcorner.model.exception.ArticleNotFoundException;
import com.sc.studentcorner.repository.ArticleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class ArticleService {

    private static final Logger logger = LoggerFactory.getLogger(ArticleService.class);

    private final ArticleRepository repository;

    public ArticleService(ArticleRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Article saveArticle(Article article) {
        logger.info("Saving article [{}]", article.getTitle());
        return repository.save(article);
    }

    public List<Article> getArticlesFromCategory(ArticleCategory category) {
        logger.info("Retrieving all articles from db");
        return repository.findAllByCategory(category);
    }

    public Article getArticleById(Long id) throws ArticleNotFoundException {
        return repository.findById(id)
                .orElseThrow(() -> new ArticleNotFoundException(String.format("Article with id [{%d}] was not found", id)));
    }
}
