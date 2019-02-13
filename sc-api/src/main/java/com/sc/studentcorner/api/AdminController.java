package com.sc.studentcorner.api;

import com.sc.studentcorner.model.Admin;
import com.sc.studentcorner.model.Article;
import com.sc.studentcorner.model.request.UserRequest;
import com.sc.studentcorner.service.AdminService;
import com.sc.studentcorner.service.ArticleService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService service;
    private final ArticleService articleService;

    public AdminController(AdminService service, ArticleService articleService) {
        this.service = service;
        this.articleService = articleService;
    }

    @PostMapping("/register")
    public Admin saveAdmin(@RequestBody UserRequest request) {
        return service.saveAdmin(request.email, request.password);
    }

    @GetMapping("/loggedAdmin")
    public Admin getLoggedAdmin(Authentication authentication) {
        return (Admin) authentication.getPrincipal();
    }

    @PostMapping("/article/save")
    public Article saveArticle(@RequestBody Article article){
        return articleService.saveArticle(article);
    }

}
