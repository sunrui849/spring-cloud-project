CREATE TABLE `teacher` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) COLLATE utf8_bin NOT NULL,
  `age` int COLLATE utf8_bin DEFAULT NULL,
  `email` varchar(50) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin

INSERT INTO config_jdbc.teacher (id,user_name,age,email) VALUES
	 (3,'哈哈哈哈',0,'hh@email.com');

CREATE TABLE `student` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `user_name` varchar(50) COLLATE utf8_bin NOT NULL,
    `age` int COLLATE utf8_bin DEFAULT NULL,
    `email` varchar(50) COLLATE utf8_bin NOT NULL,
    `teacher` varchar(50) COLLATE utf8_bin NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

INSERT INTO config_jdbc.student
(user_name, age, email, teacher)
VALUES('学生', 0, 'xuesheng@ww.com', '哈哈哈');

	INSERT INTO config_jdbc.student
(user_name, age, email, teacher)
VALUES('学生2', 0, 'xuesheng@ww.com2', '哈哈哈2');
	INSERT INTO config_jdbc.student
(user_name, age, email, teacher)
VALUES('学生3', 0, 'xuesheng@ww.com3', '哈哈哈3');