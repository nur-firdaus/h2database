package com.codility.tasks.hibernate.crud.solution;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@Slf4j
public class THMLController {

    @Autowired
    ArticleService articleService;

    @GetMapping("/")
    public String viewHomePage(Model model) {

        List<ArticleDTO> articles = articleService.findAll();
        model.addAttribute("articles", articles);
        return "index";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("article", new ArticleDTO()); // Empty form
        return "create"; // Return to create-article.html
    }

    @PostMapping("/new")
    public String createArticle(@ModelAttribute("article") ArticleDTO articleDTO, RedirectAttributes redirectAttributes) {
        try {
            if (articleDTO.getStringTags() != null && !articleDTO.getStringTags().isBlank()) {
                articleDTO.setTags(Arrays.asList(articleDTO.getStringTags().split("\\s*,\\s*")));
            } else {
                articleDTO.setTags(new ArrayList<>());
            }
            articleService.create(articleDTO);
            redirectAttributes.addFlashAttribute("success", "Article created successfully!");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/"; // Redirect to the list of articles
    }

    @PostMapping("/update/{id}")
    public String updateArticle(@PathVariable Long id,
                                @RequestParam("title") String title,
                                @RequestParam("content") String content,
                                @RequestParam("stringTags") String stringTags,
                                RedirectAttributes redirectAttributes) {
        try {
            ArticleDTO articleDTO = new ArticleDTO();
            articleDTO.setTitle(title);
            articleDTO.setContent(content);
            articleDTO.setTags(List.of(stringTags.split(","))); // Split tags by commas
            log.info("article {}",articleDTO);
            articleService.update(id, articleDTO);
            redirectAttributes.addFlashAttribute("success", "Article updated successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error updating the article.");
        }
        return "redirect:/";
    }

    @PostMapping("/delete/{id}")
    public String deleteArticle(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            articleService.delete(id);
            redirectAttributes.addFlashAttribute("success", "Article deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error deleting the article.");
        }
        return "redirect:/";
    }
}
