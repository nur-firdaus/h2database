package com.codility.tasks.hibernate.crud.solution;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;
@Entity
@Table(name = "articles")
@Getter
@Setter
@NoArgsConstructor
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;

    @ElementCollection
    @CollectionTable(name = "tag", joinColumns = @JoinColumn(name = "article_id"))
    @Column(name = "tag")
    private List<String> tag;

}