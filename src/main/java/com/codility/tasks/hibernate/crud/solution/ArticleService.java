package com.codility.tasks.hibernate.crud.solution;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.*;
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
        articleDTO.setId(article.getId());
        articleDTO.setTitle(article.getTitle());
        articleDTO.setContent(article.getContent());
        articleDTO.setTags(article.getTag());
        if(!article.getTag().isEmpty()){
            StringBuilder sTag= new StringBuilder();
            for (String tag: article.getTag()) {
                sTag.append(", ").append(tag);
            }
            articleDTO.setStringTags(sTag.toString().substring(2));
        }
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

    public List<ArticleDTO> findAll() {
        return articleRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<ArticleDTO> findByTitle(String title) {
        return articleRepository.findByTitleContaining(title).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Long create(ArticleDTO articleDTO) {
        if(!isContainsBlacklistWord(articleDTO.getContent())) {
            Article article = convertToEntity(articleDTO);
            article = articleRepository.save(article);
            return article.getId();
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


    @Transactional
    public void update(Long id, ArticleDTO articleDTO) {
        Article article=new Article();
        article.setId(id);
        article.setTitle(articleDTO.getTitle());
        article.setContent(articleDTO.getContent());
        article.setTag(articleDTO.getTags());
        articleRepository.save(article);
    }

    public void delete(Long id) {
        try {
            articleRepository.deleteById(id);
        }catch (Exception e){
            log.error("error {}",e.getMessage());
        }
    }
}
