package com.sc.studentcorner.api;

import com.sc.studentcorner.model.Article;
import com.sc.studentcorner.model.ArticleCategory;
import com.sc.studentcorner.model.exception.ArticleNotFoundException;
import com.sc.studentcorner.service.ArticleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/public/article")
public class ArticleController {

    private final ArticleService service;

    public ArticleController(ArticleService service) {
        this.service = service;
    }


    @GetMapping
    public List<Article> getAllArticles(){
        return service.getAllArticles();
    }

    @GetMapping("/{id}")
    public Article getArticleById(@PathVariable Long id) throws ArticleNotFoundException {
        return service.getArticleById(id);
    }


    @GetMapping("/all/{category}")
    public List<Article> getAllArticlesFromCategory(@PathVariable String category) {
        return service.getArticlesFromCategory(category);
    }

}
