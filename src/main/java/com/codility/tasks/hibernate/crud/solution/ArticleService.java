package com.codility.tasks.hibernate.crud.solution;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.slf4j.SLF4JLogger;
import org.springframework.beans.factory.annotation.*;
import org.springframework.data.repository.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import java.util.*;
import java.util.stream.*;

@Service
@Slf4j
class ArticleService {

    @Value("${articles.blacklist}")
    private String blacklistWords;

    @Autowired
    private ArticleRepository articleRepository;

    private Article convertToEntity(ArticleDTO articleDTO) {
        Article article = new Article();
        article.setTitle(articleDTO.getTitle());
        article.setContent(articleDTO.getContent());
        article.setTag(articleDTO.getTags());
        return article;
    }

    private ArticleDTO convertToDTO(Article article) {
        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.setTitle(article.getTitle());
        articleDTO.setContent(article.getContent());
        articleDTO.setTags(article.getTag());
        return articleDTO;
    }

    public Optional<ArticleDTO> findById(Long id) {
        return Optional.ofNullable(articleRepository.findById(id)
                .map(this::convertToDTO)
                .orElse(null));
    }

    public Optional<ArticleDTO> findById2(Long id) {

        Optional<Article> article = articleRepository.findById(id);
        return article.map(value -> Optional.of(convertToDTO(value))).orElse(null);
    }

    public List<ArticleDTO> findByTitle(String title) {
        return articleRepository.findByTitleContaining(title).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Long create(ArticleDTO articleDTO) {
        if(!isContainsBlacklistWord(articleDTO.getContent())) {
            Article article = convertToEntity(articleDTO);
            Article savedArticle = articleRepository.save(article);
            return savedArticle.getId().longValue();
        }else{
            throw new IllegalArgumentException("Article content contains forbidden words");
        }
    }


    private boolean isContainsBlacklistWord(String words){
        String[] bannedWordsArray = blacklistWords.split(",");
        for (String bannedWord : bannedWordsArray) {
            if (words.contains(bannedWord)) {
                return true;
            }
        }
        return false;
    }

    public void update(Long id, ArticleDTO articleDTO) {
        Article article = articleRepository.findById(id).orElse(null);
        if (article != null) {
            article.setTitle(articleDTO.getTitle());
            article.setContent(articleDTO.getContent());
            log.info(articleDTO.getTags().toString());
            article.setTag(articleDTO.getTags());
            Article updatedArticle = articleRepository.save(article);
        }
    }

    public void delete(Long id) {
        try {
            articleRepository.deleteById(id);
        }catch (Exception e){
            System.out.println("error");
        }

    }
}
