# AM_spring_25_05 데이터 베이스 추가
DROP DATABASE IF EXISTS AM_spring_25_05;
CREATE DATABASE AM_spring_25_05;
USE AM_spring_25_05;

# 게시물 테이블 추가
CREATE TABLE article(
    id INT(100) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    title VARCHAR(100) NOT NULL,
    `body` TEXT NOT NULL
);

# 게시물 테이블 데이터 추가
INSERT INTO article
SET regDate = NOW(),
    updateDate = NOW(),
    title = CONCAT('제목', SUBSTRING(RAND() *1000 FROM 1 FOR 2)),
    `body` = CONCAT('내용', SUBSTRING(RAND() *1000 FROM 1 FOR 2));
    
# 게시물 테이블 조회
SELECT * FROM article ORDER BY DESC;

# 회원 테이블 추가
CREATE TABLE `member`(
    id INT(100) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    loginId VARCHAR(100) NOT NULL UNIQUE,
    loginPw VARCHAR(100) NOT NULL,
    `name` VARCHAR(10) NOT NULL
);

# 회원 테이블 추가
CREATE TABLE `member`(
    id INT(100) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    loginId VARCHAR(100) NOT NULL UNIQUE,
    loginPw VARCHAR(100) NOT NULL,
    `authLevel` SMALLINT(2) UNSIGNED DEFAULT 3 COMMENT '권한 레벨 (3=일반/7=관리자)',
    `name` VARCHAR(10) NOT NULL,
    nickName VARCHAR(10) NOT NULL,
    cellPhone VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    delStatus TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '탈퇴 여부(0=존재/1=탈퇴)',
    delDate DATETIME COMMENT '탈퇴날짜'
);

# admin 회원 데이터 추가
INSERT INTO `member`
SET regDate = NOW(),
    loginId = 'admin',
    loginPw = 'admin',
    `authLevel` = 7,
    `name` = 'admin',
    nickName = 'admin',
    cellPhone = '01012345678',
    email = 'admin@spring.com'; 


# 회원 테이블 데이터 추가
INSERT INTO `member`
SET regDate = NOW(),
    loginId = CONCAT('id', SUBSTRING(RAND() *1000 FROM 1 FOR 2)),
    loginPw = CONCAT('pw', SUBSTRING(RAND() *1000 FROM 1 FOR 2)),
    `name` = CONCAT('이름', SUBSTRING(RAND() *1000 FROM 1 FOR 2)),
    nickName = CONCAT('닉네임', SUBSTRING(RAND() *1000 FROM 1 FOR 2)),
    cellPhone = CONCAT('010123456', SUBSTRING(RAND() *1000 FROM 1 FOR 2)),
    email = CONCAT(SUBSTRING(RAND() *1000 FROM 1 FOR 2), 'mail@gmail.com'); 
    
# 회원 테이블 데이터 조회
SELECT * FROM `member` ORDER BY id DESC;

# http://localhost:8080/usr/member/doJoin?loginId=keroro&loginPw=keroro&name=keroro&nickName=keroro&cellPhone=01032165498&email=keroro@mail.com
