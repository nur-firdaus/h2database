package com.codility.tasks.hibernate.crud.solution;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class THMLController {

    @Autowired
    ArticleService articleService;

    @GetMapping("/")
    public String viewHomePage(Model model) {

        List<ArticleDTO> articles = articleService.findAll();
        model.addAttribute("articles", articles);
        return "index";
    }
}
