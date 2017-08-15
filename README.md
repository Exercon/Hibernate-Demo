# Hibernate-Demo
A simple console application to demonstrate usage of Hibernate with Spring configure.

### Prerequisites

What things you need to install

```
Compatible IDE, Intellij IDEA recommended for this project.
Hibernate 5.2.10-5.2.10
Spring-4.3.10.RELEASE
mysql-connector-java-6.0.6

```

### MySQL Script

```
CREATE DATABASE  IF NOT EXISTS `student_demo`;
USE `student_demo`;

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

```

*I did not upload library files so you need to download them.
