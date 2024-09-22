CREATE TABLE IF NOT EXISTS articles (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    content TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS tag (
    article_id INT,
    tag VARCHAR(255),
    PRIMARY KEY (article_id, tag),
    FOREIGN KEY (article_id) REFERENCES articles(id)
);



INSERT INTO articles (id, title, content) VALUES
(1, 'Introduction to Spring Boot', 'This is a tutorial on Spring Boot basics.'),
(2, 'Understanding REST APIs', 'This article explains RESTful web services in detail.'),
(3, 'Getting Started with Hibernate', 'A guide to setting up Hibernate ORM with Spring.'),
(4, 'Guide to Spring Security', 'Learn how to secure your applications with Spring Security.'),
(5, 'Spring Boot with H2 Database', 'This tutorial covers how to integrate H2 database with Spring Boot.');

INSERT INTO tag (article_id, tag) VALUES
(1, 'Spring'),
(1, 'Java'),
(1, 'Backend'),
(2, 'REST'),
(2, 'API'),
(2, 'HTTP'),
(3, 'Hibernate'),
(3, 'ORM'),
(3, 'Database'),
(4, 'Security'),
(4, 'Authentication'),
(4, 'Authorization'),
(5, 'H2'),
(5, 'Database'),
(5, 'Spring');
