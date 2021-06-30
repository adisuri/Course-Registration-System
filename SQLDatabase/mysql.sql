/*
MySQL Data Transfer
Source Host: localhost
Source Database: crs
Target Host: localhost
Target Database: crs
Date: 27-05-2021 10:43:10 AM
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `userId` varchar(100) NOT NULL default '',
  PRIMARY KEY  (`userId`),
  CONSTRAINT `admin_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `user` (`userName`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `cCode` varchar(100) NOT NULL default '',
  `cName` varchar(100) default NULL,
  `isOffered` binary(255) default NULL,
  `instructor` varchar(100) default NULL,
  `courseSeats` int(11) default NULL,
  PRIMARY KEY  (`cCode`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for notification
-- ----------------------------
DROP TABLE IF EXISTS `notification`;
CREATE TABLE `notification` (
  `notificationId` varchar(100) NOT NULL,
  `message` varchar(300) NOT NULL,
  `studentId` varchar(100) NOT NULL,
  `referenceId` varchar(100) default NULL,
  PRIMARY KEY  (`notificationId`),
  KEY `studentId_constraint_notification` (`studentId`),
  KEY `ref_constraint` (`referenceId`),
  CONSTRAINT `ref_constraint` FOREIGN KEY (`referenceId`) REFERENCES `payment` (`referenceId`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `studentId_constraint_notification` FOREIGN KEY (`studentId`) REFERENCES `student` (`userId`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for payment
-- ----------------------------
DROP TABLE IF EXISTS `payment`;
CREATE TABLE `payment` (
  `referenceId` varchar(100) NOT NULL default '',
  `studentId` varchar(100) default NULL,
  `amount` int(11) default NULL,
  `status` tinyint(4) default NULL,
  `paymentType` varchar(100) default NULL,
  PRIMARY KEY  (`referenceId`),
  KEY `studentId` (`studentId`),
  CONSTRAINT `studentId` FOREIGN KEY (`studentId`) REFERENCES `student` (`userId`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for professor
-- ----------------------------
DROP TABLE IF EXISTS `professor`;
CREATE TABLE `professor` (
  `department` varchar(100) default NULL,
  `designation` varchar(100) default NULL,
  `proffId` varchar(100) NOT NULL default '',
  PRIMARY KEY  (`proffId`),
  CONSTRAINT `professor_ibfk_3` FOREIGN KEY (`proffId`) REFERENCES `user` (`userName`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for registered_courses
-- ----------------------------
DROP TABLE IF EXISTS `registered_courses`;
CREATE TABLE `registered_courses` (
  `studentId` varchar(100) default NULL,
  `semester` int(11) default NULL,
  `courseCode` varchar(100) default NULL,
  `grade` varchar(10) default NULL,
  KEY `stud_id_constraint` (`studentId`),
  KEY `course_code_constraint` (`courseCode`),
  CONSTRAINT `stud_id_constraint` FOREIGN KEY (`studentId`) REFERENCES `student` (`userId`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `course_code_constraint` FOREIGN KEY (`courseCode`) REFERENCES `course` (`cCode`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `userId` varchar(100) NOT NULL default '',
  `branch` varchar(100) default NULL,
  `approved` tinyint(1) default NULL,
  PRIMARY KEY  (`userId`),
  CONSTRAINT `student_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `user` (`userName`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userId` varchar(100) NOT NULL default '',
  `userName` varchar(100) NOT NULL default '',
  `passwordHash` varchar(100) default NULL,
  `createDate` date default NULL,
  `role` varchar(100) default NULL,
  PRIMARY KEY  (`userName`),
  KEY `userName` (`userName`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `admin` VALUES ('admin');
INSERT INTO `course` VALUES ('AB', 'Physics', '1', 'Raj', '5');
INSERT INTO `course` VALUES ('BC', 'Chemsitry ', '1', 'Ram', '4');
INSERT INTO `course` VALUES ('CA', 'Maths ', '1', 'Alex', '5');
INSERT INTO `course` VALUES ('CD', 'Biology', '1', 'Kiran', '8');
INSERT INTO `course` VALUES ('DE', 'Accounts', '1', 'Rajesh', '8');
INSERT INTO `course` VALUES ('DF', 'Trigonometry', '1', 'Rajeev', '9');
INSERT INTO `course` VALUES ('LF', 'Commerce', '1', 'Ranveer', '9');
INSERT INTO `professor` VALUES ('Mathematics', 'Senior', 'Alex');
INSERT INTO `professor` VALUES ('BioSciences', 'Associate', 'Kiran');
INSERT INTO `professor` VALUES ('CSE', 'Associate', 'Raj');
INSERT INTO `professor` VALUES ('ECE', 'Visting', 'Rajeev');
INSERT INTO `professor` VALUES ('Commerce', 'Assistant', 'Rajesh');
INSERT INTO `professor` VALUES ('Chemical', 'Assistant', 'Ram');
INSERT INTO `professor` VALUES ('Mathematics', 'Senior', 'Ranveer');
INSERT INTO `student` VALUES ('Aditya', 'ECE', '0');
INSERT INTO `student` VALUES ('Ankit', 'CSE', '0');
INSERT INTO `student` VALUES ('Harsh', 'ECE', '0');
INSERT INTO `student` VALUES ('Keshav', 'CSE', '0');
INSERT INTO `student` VALUES ('Kunal', 'ECE', '0');
INSERT INTO `student` VALUES ('Praful', 'CSE', '0');
INSERT INTO `user` VALUES ('a2e2f2df-2486-4e00-9c7f-1b1f85a2b6da', 'Aditya', 'Aditya', '2021-05-27', 'STUDENT');
INSERT INTO `user` VALUES ('21feb725-3e17-4960-b495-990ceeadea1e', 'admin', 'admin', '2021-05-26', 'ADMIN');
INSERT INTO `user` VALUES ('9ade4d52-07e8-4a58-980a-0297493158d8', 'Alex', 'Alex', '2021-05-27', 'PROFESSOR');
INSERT INTO `user` VALUES ('bcad48c5-2ac3-4915-8c6d-0a6f6301a023', 'Ankit', 'Ankit', '2021-05-27', 'STUDENT');
INSERT INTO `user` VALUES ('b1e7c519-549b-4305-ac13-18fca931f31b', 'Harsh', 'Harsh', '2021-05-27', 'STUDENT');
INSERT INTO `user` VALUES ('7bbaecc8-1fb8-4c73-a5c2-9e5d7d0866ee', 'Keshav', 'Keshav', '2021-05-27', 'STUDENT');
INSERT INTO `user` VALUES ('234f20ec-9a93-4c73-bb3b-debc35bdc166', 'Kiran', 'Kiran', '2021-05-27', 'PROFESSOR');
INSERT INTO `user` VALUES ('0c534356-2e96-49fc-acb5-3a161bae2f23', 'Kunal', 'Kunal', '2021-05-27', 'STUDENT');
INSERT INTO `user` VALUES ('ab29cef3-171d-4022-beee-e0f74b79328c', 'Praful', 'Praful', '2021-05-27', 'STUDENT');
INSERT INTO `user` VALUES ('1043a795-0976-4fc6-9363-a464d237c3d2', 'Raj', 'Raj', '2021-05-27', 'PROFESSOR');
INSERT INTO `user` VALUES ('217628a3-f207-407e-a5ed-0306c38bb92b', 'Rajeev', 'Rajeev', '2021-05-27', 'PROFESSOR');
INSERT INTO `user` VALUES ('bcbee203-9ef0-4b48-a0cc-58a7d7a291e5', 'Rajesh', 'Rajesh', '2021-05-27', 'PROFESSOR');
INSERT INTO `user` VALUES ('65551603-a6e6-4e32-be95-ebb28b040430', 'Ram', 'Ram', '2021-05-27', 'PROFESSOR');
INSERT INTO `user` VALUES ('875c6fbe-a5d8-4dae-bd4a-bef55f577b2e', 'Ranveer', 'Ranveer', '2021-05-27', 'PROFESSOR');
