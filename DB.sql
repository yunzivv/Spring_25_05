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
    `body` TEXT NOT NULL,
    memberId INT(100) UNSIGNED NOT NULL,
    boardId INT(10) UNSIGNED NOT NULL,
    hits INT(100) UNSIGNED NOT NULL DEFAULT 0
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

# board 테이블 추가		
CREATE TABLE board(
	id INT(100) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
	`code` VARCHAR(100) NOT NULL UNIQUE COMMENT 'Notice(공지사항), Free(자유게시판) QnA(질의응답)',    
    `name` VARCHAR(100) NOT NULL UNIQUE COMMENT '게시판 이름',
    delStatus TINYINT(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '삭제 여부(0=존재/1=삭제)',
    delDate DATETIME COMMENT '삭제일'
);


# like 테이블 추가
CREATE TABLE `like` (
	id INT(100) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
	memberId INT(100) UNSIGNED NOT NULL,
	articleId INT(100) UNSIGNED NOT NULL
);


# reactionPoint 테이블 추가
CREATE TABLE reactionPoint (
	id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
	regDate DATETIME NOT NULL,
	updateDate DATETIME NOT NULL,
	memberId INT(100) UNSIGNED NOT NULL,
	relTypeCode CHAR(50) NOT NULL COMMENT '관련 데이터 코드',
	relId INT(10) NOT NULL COMMENT '관련 데이터 id',
	`point` INT(10) NOT NULL
);

# reactionPoint 테이블 제약조건 추가
ALTER TABLE reactionPoint
ADD UNIQUE KEY uniq_member_type_rel (memberId, relTypeCode, relId);


# 댓글 테이블 추가
CREATE TABLE `comment` (
	id INT(100) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
	regDate DATETIME NOT NULL,
	updateDate DATETIME NOT NULL,
	memberId INT(100) UNSIGNED NOT NULL,
	articleId INT(100) UNSIGNED NOT NULL,
	`body` TEXT NOT NULL
);


# 게시물 테이블 데이터 추가
INSERT INTO article
SET regDate = NOW(),
    updateDate = NOW(),
    title = CONCAT('제목', SUBSTRING(RAND() *1000 FROM 1 FOR 2)),
    `body` = CONCAT('내용', SUBSTRING(RAND() *1000 FROM 1 FOR 2)),
    memberId = FLOOR(1 + RAND() * 5),
    boardId = FLOOR(1 + RAND() * 3);


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


# board 테이블 데이터 추가
INSERT INTO board SET 
	regDate = NOW(),
	updateDate = NOW(),
	`code` = 'notice',
	`name` = '공지사항';
	
INSERT INTO board SET 
	regDate = NOW(),
	updateDate = NOW(),
	`code` = 'free',
	`name` = '자유게시판';

INSERT INTO board SET 
	regDate = NOW(),
	updateDate = NOW(),
	`code` = 'QnA',
	`name` = '질의응답';


# like 테이블 데이터 추가
INSERT INTO `like` SET 
	memberId = FLOOR(1 + RAND() * 4), 
	articleId = FLOOR(1 + RAND() * 5);

# comment 테이블 데이터 추가
INSERT INTO `COMMENT` SET
	regDate = NOW(),
	updateDate = NOW(),
	memberId = FLOOR(1 + RAND() * 4),
	articleId = FLOOR(1 + RAND() * 64),
	`body` = 'hoho';


# reactionPoint 데이터 추가
INSERT INTO reactionPoint SET
regDate = NOW(),
updateDate = NOW(),
memberID = FLOOR(RAND() * 8) + 1,
relTypeCode = 'article',
relId = FLOOR(RAND() * 64) + 1,
`point` = (2 * FLOOR(RAND() * 2)) - 1;

# reactionPoint 테이블 중복 제거
DELETE rp1
FROM reactionPoint rp1
JOIN reactionPoint rp2
  ON rp1.memberId = rp2.memberId
 AND rp1.relId = rp2.relId
 AND rp1.id > rp2.id;

SELECT * FROM article;
SELECT * FROM `member`;
SELECT * FROM board;
SELECT * FROM `like`;
SELECT * FROM `comment`;
SELECT * FROM reactionPoint;