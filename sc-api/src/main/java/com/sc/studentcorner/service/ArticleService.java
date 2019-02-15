package com.sc.studentcorner.service;

import com.sc.studentcorner.model.Article;
import com.sc.studentcorner.model.ArticleCategory;
import com.sc.studentcorner.model.exception.ArticleNotFoundException;
import com.sc.studentcorner.model.request.ArticleRequest;
import com.sc.studentcorner.repository.ArticleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class ArticleService {

    private static final Logger logger = LoggerFactory.getLogger(ArticleService.class);

    private final ArticleRepository repository;

    public ArticleService(ArticleRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Article saveArticle(ArticleRequest request) {
        logger.info("Saving request [{}]", request.title);
        ArticleCategory category = ArticleCategory.valueOf(request.articleCategory);
        LocalDateTime lcd = LocalDateTime.now();
        Article article = new Article(request.title, category, request.content, request.thumbnail, lcd);
        return repository.save(article);
    }

    public List<Article> getAllArticles() {
        logger.info("Retrieving all articles from db");
        return repository.findAll();
    }

    public List<Article> getArticlesFromCategory(String category) {
        logger.info("Retrieving all articles from db");
        ArticleCategory articleCategory = ArticleCategory.valueOf(category);
        return repository.findAllByCategory(articleCategory);
    }

    public Article getArticleById(Long id) throws ArticleNotFoundException {
        return repository.findById(id)
                .orElseThrow(() -> new ArticleNotFoundException(String.format("Article with id [{%d}] was not found", id)));
    }
}
