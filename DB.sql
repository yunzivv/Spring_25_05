DROP DATABASE IF EXISTS `AM_spring_25_05`;
CREATE DATABASE `AM_spring_25_05`;
USE `AM_spring_25_05`;

# 게시글 테이블 생성
CREATE TABLE article(
    id INT(100) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    title VARCHAR(100) NOT NULL,
    `body` TEXT NOT NULL
);

# 게시글 데이터 대량 생성
INSERT INTO article
SET regDate = NOW(),
    updateDate = NOW(),
    title = CONCAT('제목', SUBSTRING(RAND() *1000 FROM 1 FOR 2)),
    `body` = CONCAT('내용', SUBSTRING(RAND() *1000 FROM 1 FOR 2));

# 게시글 데이터 조회
SELECT *
FROM article
ORDER BY id DESC;
