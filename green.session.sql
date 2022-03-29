
SHOW TABLES;

DROP TABLE Post;
DROP TABLE User;

SELECT * FROM User;
INSERT INTO User(username, password, email, addr) VALUES('ssar', '1234','ssar@nate.com', 'busan');
INSERT INTO User(username, password, email, addr) VALUES('cos', '1234','cos@nate.com', 'seoul');

SELECT * FROM Post;
INSERT INTO Post(title, content, user) VALUES('title1', 'content1', 1);
INSERT INTO Post(title, content, user) VALUES('title2', 'content2', 1);
INSERT INTO Post(title, content, user) VALUES('title3', 'content3', 2);

SELECT * FROM Comment;
INSERT INTO Comment(content, userId, postId) VALUES('ssar of comment -content1', 1, 1);
INSERT INTO Comment(content, userId, postId) VALUES('ssar of comment -content1', 1, 1);
INSERT INTO Comment(content, userId, postId) VALUES('cos of comment -content1', 2, 1);
INSERT INTO Comment(content, userId, postId) VALUES('cos of comment -content2', 2, 1);

COMMIT;

-- 3개를 조인해서 뿌리는데 1번 게시글을 select해서
-- user정보와 댓글정보를 함께 출력하시오.
SELECT *
FROM post p
INNER JOIN user u ON p.userId = u.id
INNER JOIN comment c ON p.id = c.postId
WHERE p.id = 1;
