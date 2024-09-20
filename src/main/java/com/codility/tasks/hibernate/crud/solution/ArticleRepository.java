package com.codility.tasks.hibernate.crud.solution;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    // Method to find an article by title
    Optional<Article> findByTitle(String title);

    List<Article> findByTitleContaining(String title);
}
