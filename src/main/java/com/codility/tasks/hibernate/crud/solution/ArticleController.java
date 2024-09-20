package com.codility.tasks.hibernate.crud.solution;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/articles")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/{id}")
    public ArticleDTO getArticleById(@PathVariable Long id) {
        return articleService.findById(id).get();
    }

    @PostMapping
    public Long createArticle(@RequestBody ArticleDTO articleDTO) {
        return articleService.create(articleDTO);
    }

    @PutMapping("/{id}")
    public void updateArticle(@PathVariable Long id, @RequestBody ArticleDTO articleDTO) {
        articleService.update(id, articleDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteArticle(@PathVariable Long id) {
        articleService.delete(id);
    }

    @GetMapping("/title/{title}")
    public List<ArticleDTO> deleteArticle(@PathVariable String title) {
        return articleService.findByTitle(title);
    }
}
