-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: ipmcourseproject
-- ------------------------------------------------------
-- Server version	8.0.34

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `ipmcourseproject`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `ipmcourseproject` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `ipmcourseproject`;

--
-- Table structure for table `add_conditional_block`
--

DROP TABLE IF EXISTS `add_conditional_block`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `add_conditional_block` (
  `block` int NOT NULL,
  `additional_condition` int NOT NULL,
  KEY `block_condition` (`block`),
  KEY `condition_block` (`additional_condition`),
  CONSTRAINT `block_condition` FOREIGN KEY (`block`) REFERENCES `block` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `condition_block` FOREIGN KEY (`additional_condition`) REFERENCES `additional_condition` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `add_conditional_block`
--

LOCK TABLES `add_conditional_block` WRITE;
/*!40000 ALTER TABLE `add_conditional_block` DISABLE KEYS */;
INSERT INTO `add_conditional_block` VALUES (1,3),(1,1),(2,7),(2,5),(3,3),(4,5),(4,1),(5,7),(28,7),(28,9),(28,3),(28,5),(28,1),(30,9),(31,7),(31,9),(31,3),(31,5),(31,1),(32,9),(32,3),(33,9),(34,7),(34,9),(34,3),(34,5),(34,1);
/*!40000 ALTER TABLE `add_conditional_block` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `add_conditional_dormitory`
--

DROP TABLE IF EXISTS `add_conditional_dormitory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `add_conditional_dormitory` (
  `dormitory` int NOT NULL,
  `additional_condition` int NOT NULL,
  KEY `condition_dormitory` (`additional_condition`),
  KEY `dormitory_condition` (`dormitory`),
  CONSTRAINT `condition_dormitory` FOREIGN KEY (`additional_condition`) REFERENCES `additional_condition` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `dormitory_condition` FOREIGN KEY (`dormitory`) REFERENCES `dormitory` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `add_conditional_dormitory`
--

LOCK TABLES `add_conditional_dormitory` WRITE;
/*!40000 ALTER TABLE `add_conditional_dormitory` DISABLE KEYS */;
INSERT INTO `add_conditional_dormitory` VALUES (1,6),(1,10),(1,4),(1,8),(1,1),(4,4),(3,6),(3,10),(3,4),(3,8),(3,1);
/*!40000 ALTER TABLE `add_conditional_dormitory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `add_conditional_floor`
--

DROP TABLE IF EXISTS `add_conditional_floor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `add_conditional_floor` (
  `floor` int NOT NULL,
  `additional_condition` int NOT NULL,
  KEY `condition_floor` (`additional_condition`),
  KEY `floor_condition` (`floor`),
  CONSTRAINT `condition_floor` FOREIGN KEY (`additional_condition`) REFERENCES `additional_condition` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `floor_condition` FOREIGN KEY (`floor`) REFERENCES `floor` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `add_conditional_floor`
--

LOCK TABLES `add_conditional_floor` WRITE;
/*!40000 ALTER TABLE `add_conditional_floor` DISABLE KEYS */;
INSERT INTO `add_conditional_floor` VALUES (6,2),(6,7),(6,9),(6,3),(8,2),(8,7),(9,2),(9,7),(1,2),(1,7),(1,3),(2,2),(2,7),(2,3),(4,2),(4,7),(4,3),(4,5),(3,2),(3,7),(3,3),(5,2),(5,7),(5,3),(10,2),(10,7),(10,9),(10,3),(10,5),(11,2),(11,7),(11,9),(11,3),(11,5),(12,2),(12,7),(12,9),(12,3),(12,5);
/*!40000 ALTER TABLE `add_conditional_floor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `add_conditional_room`
--

DROP TABLE IF EXISTS `add_conditional_room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `add_conditional_room` (
  `room` int NOT NULL,
  `additional_condition` int NOT NULL,
  KEY `room_condition` (`room`),
  KEY `condition_room` (`additional_condition`),
  CONSTRAINT `condition_room` FOREIGN KEY (`additional_condition`) REFERENCES `additional_condition` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `room_condition` FOREIGN KEY (`room`) REFERENCES `room` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `add_conditional_room`
--

LOCK TABLES `add_conditional_room` WRITE;
/*!40000 ALTER TABLE `add_conditional_room` DISABLE KEYS */;
INSERT INTO `add_conditional_room` VALUES (51,6),(51,2),(1,2),(1,4),(2,6),(2,2),(2,4),(3,6),(4,2),(4,4),(5,4),(53,6),(53,2),(53,10),(53,4),(53,8),(54,6),(54,2),(54,10),(54,4),(54,8),(57,2),(58,6),(58,2),(58,10),(58,4),(58,8);
/*!40000 ALTER TABLE `add_conditional_room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `additional_condition`
--

DROP TABLE IF EXISTS `additional_condition`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `additional_condition` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKsfco3yvidm2hrq6b15d68hr8o` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `additional_condition`
--

LOCK TABLES `additional_condition` WRITE;
/*!40000 ALTER TABLE `additional_condition` DISABLE KEYS */;
INSERT INTO `additional_condition` VALUES (6,'Библиотека'),(2,'Душ'),(7,'Комната отдыха'),(9,'Конференц-зал'),(3,'Кухня'),(10,'Медицинский пункт'),(4,'Прачечная'),(5,'Спортзал'),(8,'Столовая'),(1,'Туалет');
/*!40000 ALTER TABLE `additional_condition` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `additional_condition_places`
--

DROP TABLE IF EXISTS `additional_condition_places`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `additional_condition_places` (
  `additional_condition` int NOT NULL,
  `place` enum('BLOCK','DORMITORY','FLOOR','ROOM') DEFAULT NULL,
  KEY `additional_condition` (`additional_condition`),
  CONSTRAINT `additional_condition` FOREIGN KEY (`additional_condition`) REFERENCES `additional_condition` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `additional_condition_places`
--

LOCK TABLES `additional_condition_places` WRITE;
/*!40000 ALTER TABLE `additional_condition_places` DISABLE KEYS */;
INSERT INTO `additional_condition_places` VALUES (1,'BLOCK'),(1,'DORMITORY'),(3,'BLOCK'),(3,'FLOOR'),(4,'ROOM'),(4,'DORMITORY'),(5,'BLOCK'),(5,'FLOOR'),(6,'DORMITORY'),(6,'ROOM'),(7,'BLOCK'),(7,'FLOOR'),(8,'DORMITORY'),(8,'ROOM'),(9,'BLOCK'),(9,'FLOOR'),(10,'ROOM'),(10,'DORMITORY'),(2,'ROOM'),(2,'BLOCK'),(2,'FLOOR');
/*!40000 ALTER TABLE `additional_condition_places` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin` (
  `access_key` varchar(255) NOT NULL,
  `id` int NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK1ja8rua032fgnk9jmq7du3b3a` FOREIGN KEY (`id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES ('ZUH|x]L/%D,@4',4),('4i.?6rw@XQ:cX',9);
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `block`
--

DROP TABLE IF EXISTS `block`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `block` (
  `id` int NOT NULL AUTO_INCREMENT,
  `designed_for` enum('FEMALE','MALE','NOT_EXIST') DEFAULT NULL,
  `number` int DEFAULT NULL,
  `floor` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKlfi3uhta64ta6nynsy1bmoeaf` (`floor`),
  CONSTRAINT `FKlfi3uhta64ta6nynsy1bmoeaf` FOREIGN KEY (`floor`) REFERENCES `floor` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `block`
--

LOCK TABLES `block` WRITE;
/*!40000 ALTER TABLE `block` DISABLE KEYS */;
INSERT INTO `block` VALUES (1,'MALE',101,1),(2,'FEMALE',102,1),(3,'NOT_EXIST',103,1),(4,'FEMALE',104,1),(5,'MALE',105,1),(6,'NOT_EXIST',201,2),(7,'MALE',202,2),(8,'FEMALE',203,2),(9,'NOT_EXIST',204,2),(10,'FEMALE',205,2),(11,'MALE',301,3),(12,'NOT_EXIST',302,3),(13,'MALE',303,3),(14,'FEMALE',304,3),(15,'NOT_EXIST',305,3),(16,'FEMALE',401,4),(17,'MALE',402,4),(18,'NOT_EXIST',403,4),(19,'MALE',404,4),(20,'FEMALE',405,4),(21,'NOT_EXIST',501,5),(22,'FEMALE',502,5),(23,'MALE',503,5),(24,'NOT_EXIST',504,5),(25,'MALE',505,5),(28,'NOT_EXIST',601,6),(30,'FEMALE',602,6),(31,'NOT_EXIST',601,10),(32,'MALE',602,10),(33,'NOT_EXIST',603,10),(34,'MALE',1116,12);
/*!40000 ALTER TABLE `block` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dormitory`
--

DROP TABLE IF EXISTS `dormitory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dormitory` (
  `id` int NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `number` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKiklgwaw6o3o48v7fq3y3iefkg` (`address`),
  UNIQUE KEY `UKiqoeyqn26be4xirswxruse9jv` (`number`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dormitory`
--

LOCK TABLES `dormitory` WRITE;
/*!40000 ALTER TABLE `dormitory` DISABLE KEYS */;
INSERT INTO `dormitory` VALUES (1,'ул. Пушкина, д. 10',1),(3,'wewe we we',3),(4,'ул. Леонида Беды 4',2);
/*!40000 ALTER TABLE `dormitory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dormitory_responsible_persons`
--

DROP TABLE IF EXISTS `dormitory_responsible_persons`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dormitory_responsible_persons` (
  `dormitory` int NOT NULL,
  `responsible_persons` varchar(255) NOT NULL,
  KEY `dormitory_person` (`dormitory`),
  KEY `person_dormitory` (`responsible_persons`),
  CONSTRAINT `dormitory_person` FOREIGN KEY (`dormitory`) REFERENCES `dormitory` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `person_dormitory` FOREIGN KEY (`responsible_persons`) REFERENCES `responsible_person` (`fio`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dormitory_responsible_persons`
--

LOCK TABLES `dormitory_responsible_persons` WRITE;
/*!40000 ALTER TABLE `dormitory_responsible_persons` DISABLE KEYS */;
INSERT INTO `dormitory_responsible_persons` VALUES (1,'Евдокимов Сергей Викторович'),(1,'Иванов Иван Иванович'),(1,'Козлов Алексей Владимирович'),(1,'Лебедев Андрей Валерьевич'),(1,'Михайлов Михаил Петрович'),(1,'Петров Петр Петрович'),(1,'Романов Роман Анатольевич'),(4,'Евдокимов Сергей Викторович'),(3,'Лебедев Андрей Валерьевич');
/*!40000 ALTER TABLE `dormitory_responsible_persons` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fan_club_entity`
--

DROP TABLE IF EXISTS `fan_club_entity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fan_club_entity` (
  `person` varchar(255) NOT NULL,
  `reason` varchar(255) NOT NULL,
  PRIMARY KEY (`person`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fan_club_entity`
--

LOCK TABLES `fan_club_entity` WRITE;
/*!40000 ALTER TABLE `fan_club_entity` DISABLE KEYS */;
/*!40000 ALTER TABLE `fan_club_entity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `floor`
--

DROP TABLE IF EXISTS `floor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `floor` (
  `id` int NOT NULL AUTO_INCREMENT,
  `number` int DEFAULT NULL,
  `dormitory` int DEFAULT NULL,
  `responsible_person` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKgiigv0mb44t18xom8tddo8cua` (`dormitory`),
  KEY `person_floor` (`responsible_person`),
  CONSTRAINT `FKgiigv0mb44t18xom8tddo8cua` FOREIGN KEY (`dormitory`) REFERENCES `dormitory` (`id`),
  CONSTRAINT `person_floor` FOREIGN KEY (`responsible_person`) REFERENCES `responsible_person` (`fio`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `floor`
--

LOCK TABLES `floor` WRITE;
/*!40000 ALTER TABLE `floor` DISABLE KEYS */;
INSERT INTO `floor` VALUES (1,1,1,'Евдокимов Сергей Викторович'),(2,2,1,'Иванов Иван Иванович'),(3,3,1,'Иванов Иван Иванович'),(4,4,1,'Козлов Алексей Владимирович'),(5,5,1,'Козлов Алексей Владимирович'),(6,0,NULL,'Романов Роман Анатольевич'),(8,0,NULL,'Михайлов Михаил Петрович'),(9,1,3,'Козлов Алексей Владимирович'),(10,6,1,'Романов Роман Анатольевич'),(11,11,NULL,'Дима Дима'),(12,11,4,'Дима Дима');
/*!40000 ALTER TABLE `floor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flyway_schema_history`
--

DROP TABLE IF EXISTS `flyway_schema_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `flyway_schema_history` (
  `installed_rank` int NOT NULL,
  `version` varchar(50) DEFAULT NULL,
  `description` varchar(200) NOT NULL,
  `type` varchar(20) NOT NULL,
  `script` varchar(1000) NOT NULL,
  `checksum` int DEFAULT NULL,
  `installed_by` varchar(100) NOT NULL,
  `installed_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `execution_time` int NOT NULL,
  `success` tinyint(1) NOT NULL,
  PRIMARY KEY (`installed_rank`),
  KEY `flyway_schema_history_s_idx` (`success`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flyway_schema_history`
--

LOCK TABLES `flyway_schema_history` WRITE;
/*!40000 ALTER TABLE `flyway_schema_history` DISABLE KEYS */;
INSERT INTO `flyway_schema_history` VALUES (1,'1','<< Flyway Baseline >>','BASELINE','<< Flyway Baseline >>',NULL,'root','2024-11-20 15:12:03',0,1),(2,'2','insert position','SQL','V2__insert_position.sql',-1636503029,'root','2024-11-20 15:12:03',8,1),(3,'3.1','insert responsible person','SQL','V3_1__insert_responsible_person.sql',-1826867359,'root','2024-11-20 15:12:03',16,1),(4,'3.2','insert person position','SQL','V3_2__insert_person_position.sql',1581582244,'root','2024-11-20 15:12:04',12,1),(5,'3.3','insert person position part2','SQL','V3_3__insert_person_position_part2.sql',501010788,'root','2024-11-20 15:12:04',7,1),(6,'3.4','add FK to person position','SQL','V3_4__add_FK_to_person_position.sql',802020649,'root','2024-11-20 15:12:04',105,1),(7,'4.1','insert resident','SQL','V4_1__insert_resident.sql',1207971851,'root','2024-11-20 15:12:04',9,1),(8,'4.2','add FK to resident','SQL','V4_2__add_FK_to_resident.sql',1806034189,'root','2024-11-20 15:12:04',62,1),(9,'5.1','insert lease contract','SQL','V5_1__insert_lease_contract.sql',641383782,'root','2024-11-20 15:12:04',10,1),(10,'5.2','add FK to lease contract','SQL','V5_2__add_FK_to_lease_contract.sql',264731571,'root','2024-11-20 15:12:04',45,1),(11,'6.1','insert medical report','SQL','V6_1__insert_medical_report.sql',-1540234856,'root','2024-11-20 15:12:04',11,1),(12,'6.2','add FK to medical report','SQL','V6_2__add_FK_to_medical_report.sql',1457816266,'root','2024-11-20 15:12:04',46,1),(13,'7.1','insert addition condition','SQL','V7_1__insert_addition_condition.sql',-2082290090,'root','2024-11-20 15:12:04',7,1),(14,'7.2','add FK to additional condition','SQL','V7_2__add_FK_to_additional_condition.sql',1428070805,'root','2024-11-20 15:12:04',43,1),(15,'8.1','insert dormitory','SQL','V8_1__insert_dormitory.sql',1570112373,'root','2024-11-20 15:12:04',11,1),(16,'8.2','add FK to dormitory','SQL','V8_2__add_FK_to_dormitory.sql',-958214043,'root','2024-11-20 15:12:04',99,1),(17,'9.1','insert floor','SQL','V9_1__insert_floor.sql',-836979172,'root','2024-11-20 15:12:04',9,1),(18,'9.2','add FK to floor','SQL','V9_2__add_FK_to_floor.sql',677159970,'root','2024-11-20 15:12:04',49,1),(19,'10.1','insert block','SQL','V10_1__insert_block.sql',-2099826396,'root','2024-11-20 15:12:04',8,1),(20,'10.2','add FK to block','SQL','V10_2__add_FK_to_block.sql',-300287101,'root','2024-11-20 15:12:04',51,1),(21,'11.1','insert room','SQL','V11_1__insert_room.sql',-1814805063,'root','2024-11-20 15:12:04',9,1),(22,'11.2','insert room resident','SQL','V11_2__insert_room_resident.sql',650402116,'root','2024-11-20 15:12:05',29,1),(23,'11.3','add FK to room','SQL','V11_3__add_FK_to_room.sql',897141530,'root','2024-11-20 15:12:05',51,1);
/*!40000 ALTER TABLE `flyway_schema_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lease_contract`
--

DROP TABLE IF EXISTS `lease_contract`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lease_contract` (
  `lease_contract_number` int NOT NULL AUTO_INCREMENT,
  `contract_date` date DEFAULT NULL,
  `lessee` varchar(255) DEFAULT NULL,
  `valid_until` date DEFAULT NULL,
  `tenant` varchar(255) NOT NULL,
  PRIMARY KEY (`lease_contract_number`),
  UNIQUE KEY `UKonak1s8gktce99hnocnmydvyg` (`tenant`),
  CONSTRAINT `tenet` FOREIGN KEY (`tenant`) REFERENCES `resident` (`fio`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lease_contract`
--

LOCK TABLES `lease_contract` WRITE;
/*!40000 ALTER TABLE `lease_contract` DISABLE KEYS */;
INSERT INTO `lease_contract` VALUES (2,'2023-01-01','Иванов Иван Иванович','2025-01-01','Петрова Анна Сергеевна'),(3,'2023-01-01','Иванов Иван Иванович','2025-01-01','Сидоров Алексей Викторович'),(4,'2023-01-01','Иванов Иван Иванович','2025-01-01','Козлова Мария Дмитриевна'),(5,'2023-01-01','Иванов Иван Иванович','2025-01-01','Михайлова Екатерина Валентиновна'),(6,'2023-01-01','Иванов Иван Иванович','2025-01-01','Новиков Дмитрий Викторович'),(7,'2023-01-01','Иванов Иван Иванович','2025-01-01','Лебедев Артем Павлович'),(8,'2023-01-01','Иванов Иван Иванович','2025-01-01','Шевченко Ирина Александровна'),(9,'2023-01-01','Иванов Иван Иванович','2025-01-01','Попова Ольга Викторовна'),(10,'2023-01-01','Иванов Иван Иванович','2025-01-01','Романова Татьяна Вячеславовна'),(12,'2024-11-01','Иванов Иван Иванович','2024-11-01','Иванов Иван Иванович'),(13,'2024-11-22','Иванчик Антон Александрович','2025-05-22','Иванова Марина Владимировна'),(14,'2024-11-06','Дмитрий Сергеевич Уважаемый','2024-11-07','Дмитриев Аркадий Анатольевич'),(15,'2024-12-03','Я','2025-12-09','Данилова Екатерина Михайловна');
/*!40000 ALTER TABLE `lease_contract` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medical_report`
--

DROP TABLE IF EXISTS `medical_report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `medical_report` (
  `certificate_number` varchar(255) NOT NULL,
  `doctor` varchar(255) DEFAULT NULL,
  `fit` bit(1) NOT NULL,
  `type` enum('DERMATOLOGIST','FLUOROGRAPHY','INFECTIOUS_DISEASES') NOT NULL,
  `valid_until` date DEFAULT NULL,
  `patient` varchar(255) NOT NULL,
  PRIMARY KEY (`certificate_number`),
  KEY `patient` (`patient`),
  CONSTRAINT `patient` FOREIGN KEY (`patient`) REFERENCES `resident` (`fio`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medical_report`
--

LOCK TABLES `medical_report` WRITE;
/*!40000 ALTER TABLE `medical_report` DISABLE KEYS */;
INSERT INTO `medical_report` VALUES ('123456','Уважаемый Александр Пушкин',_binary '','INFECTIOUS_DISEASES','2024-11-07','Дмитриев Аркадий Анатольевич'),('12412413','Я',_binary '','DERMATOLOGIST','2025-12-02','Данилова Екатерина Михайловна'),('a','Доктор Дима 2',_binary '','DERMATOLOGIST','2024-11-26','Иванова Марина Владимировна'),('ID100123','Доктор Дима',_binary '','FLUOROGRAPHY','2024-11-27','Иванова Марина Владимировна'),('ID100124','Доктор Петров',_binary '','FLUOROGRAPHY','2024-06-30','Иванов Иван Иванович'),('ID100125','Доктор Иванова',_binary '\0','DERMATOLOGIST','2023-12-31','Иванов Иван Иванович'),('ID100126','Доктор Смирнова',_binary '','INFECTIOUS_DISEASES','2024-12-31','Петрова Анна Сергеевна'),('ID100127','Доктор Козлов',_binary '','FLUOROGRAPHY','2024-06-30','Петрова Анна Сергеевна'),('ID100128','Доктор Николаева',_binary '','DERMATOLOGIST','2024-12-31','Петрова Анна Сергеевна'),('ID100129','Доктор Михайлов',_binary '\0','INFECTIOUS_DISEASES','2023-12-31','Сидоров Алексей Викторович'),('ID100130','Доктор Павлов',_binary '','FLUOROGRAPHY','2024-06-30','Сидоров Алексей Викторович'),('ID100131','Доктор Андреева',_binary '\0','DERMATOLOGIST','2023-12-31','Сидоров Алексей Викторович'),('ID100132','Доктор Попов',_binary '','INFECTIOUS_DISEASES','2024-12-31','Козлова Мария Дмитриевна'),('ID100133','Доктор Романова',_binary '\0','FLUOROGRAPHY','2023-06-30','Козлова Мария Дмитриевна'),('ID100134','Доктор Евгеньев',_binary '','DERMATOLOGIST','2024-12-31','Козлова Мария Дмитриевна'),('ID100135','Доктор Светлова',_binary '','INFECTIOUS_DISEASES','2024-12-31','Михайлова Екатерина Валентиновна'),('ID100136','Доктор Сергеев',_binary '','FLUOROGRAPHY','2024-06-30','Михайлова Екатерина Валентиновна'),('ID100137','Доктор Сидоров',_binary '\0','DERMATOLOGIST','2023-12-31','Михайлова Екатерина Валентиновна');
/*!40000 ALTER TABLE `medical_report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `person_position`
--

DROP TABLE IF EXISTS `person_position`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `person_position` (
  `person` varchar(255) NOT NULL,
  `position_i` int NOT NULL,
  KEY `FK_person` (`person`),
  KEY `FK_position` (`position_i`),
  CONSTRAINT `FK_person` FOREIGN KEY (`person`) REFERENCES `responsible_person` (`fio`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_position` FOREIGN KEY (`position_i`) REFERENCES `position_t` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `person_position`
--

LOCK TABLES `person_position` WRITE;
/*!40000 ALTER TABLE `person_position` DISABLE KEYS */;
INSERT INTO `person_position` VALUES ('Петров Петр Петрович',10),('Козлов Алексей Владимирович',3),('Михайлов Михаил Петрович',6),('Романов Роман Анатольевич',2),('Лебедев Андрей Валерьевич',5),('Евдокимов Сергей Викторович',7),('Иванов Иван Иванович',2),('Петров Петр Петрович',2),('Дима Дима',12),('Матышев Дмитрий Сергеевич',12);
/*!40000 ALTER TABLE `person_position` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `position_t`
--

DROP TABLE IF EXISTS `position_t`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `position_t` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK67t0wh73mnukxo9h3m521kp8j` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `position_t`
--

LOCK TABLES `position_t` WRITE;
/*!40000 ALTER TABLE `position_t` DISABLE KEYS */;
INSERT INTO `position_t` VALUES (12,'Дима-1'),(3,'Заведующий'),(6,'Комендант'),(2,'Паспортист'),(10,'Повар'),(5,'Сантехник'),(8,'Сторож'),(7,'Уборщик'),(4,'Электрик');
/*!40000 ALTER TABLE `position_t` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `resident`
--

DROP TABLE IF EXISTS `resident`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `resident` (
  `fio` varchar(255) NOT NULL,
  `benefit` enum('IMPORTANT','NOT_IMPORTANT','USUAL','VERY_IMPORTANT') NOT NULL,
  `birthday` date DEFAULT NULL,
  `gender` enum('FEMALE','MALE','NOT_EXIST') NOT NULL,
  `status` enum('EXPECTING','SETTLED') NOT NULL,
  `room` int DEFAULT NULL,
  PRIMARY KEY (`fio`),
  KEY `room` (`room`),
  CONSTRAINT `room` FOREIGN KEY (`room`) REFERENCES `room` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resident`
--

LOCK TABLES `resident` WRITE;
/*!40000 ALTER TABLE `resident` DISABLE KEYS */;
INSERT INTO `resident` VALUES ('Болотова Ирина Васильевна','IMPORTANT','1986-11-04','FEMALE','EXPECTING',NULL),('Борисов Дмитрий Валерьевич','VERY_IMPORTANT','1980-10-25','MALE','EXPECTING',NULL),('Васильева Наталья Павловна','USUAL','1987-02-04','FEMALE','EXPECTING',NULL),('Воронин Владимир Евгеньевич','IMPORTANT','1970-02-10','MALE','EXPECTING',NULL),('Гончаров Андрей Николаевич','VERY_IMPORTANT','1982-05-25','MALE','EXPECTING',NULL),('Гордеев Виктор Олегович','IMPORTANT','1985-11-08','MALE','EXPECTING',NULL),('Громова Дарина Вячеславовна','NOT_IMPORTANT','1990-08-21','FEMALE','EXPECTING',NULL),('Данилова Екатерина Михайловна','IMPORTANT','1990-05-14','FEMALE','SETTLED',28),('Дмитриев Аркадий Анатольевич','IMPORTANT','1978-09-30','MALE','SETTLED',37),('Жданов Алексей Борисович','USUAL','1986-07-02','MALE','EXPECTING',NULL),('Жукова Алёна Дмитриевна','IMPORTANT','1991-03-13','FEMALE','EXPECTING',NULL),('Жукова Людмила Петровна','VERY_IMPORTANT','1992-01-30','FEMALE','SETTLED',43),('Иванов Иван Иванович','NOT_IMPORTANT','1980-01-15','MALE','EXPECTING',NULL),('Иванова Марина Владимировна','USUAL','1982-08-25','FEMALE','EXPECTING',NULL),('Калугина Светлана Викторовна','USUAL','1982-06-29','FEMALE','EXPECTING',NULL),('Ковалев Александр Дмитриевич','VERY_IMPORTANT','1980-12-15','MALE','EXPECTING',NULL),('Козлов Артем Сергеевич','VERY_IMPORTANT','1986-09-13','MALE','EXPECTING',NULL),('Козлова Мария Дмитриевна','VERY_IMPORTANT','1985-11-11','FEMALE','SETTLED',2),('Кузнецова Светлана Игоревна','NOT_IMPORTANT','1983-07-19','FEMALE','EXPECTING',NULL),('Кузнецова Татьяна Борисовна','USUAL','1990-09-12','FEMALE','EXPECTING',NULL),('Лебедев Артем Павлович','IMPORTANT','1994-12-25','MALE','SETTLED',5),('Логинова Ольга Фёдоровна','NOT_IMPORTANT','1994-02-02','FEMALE','EXPECTING',NULL),('Мартынов Кирилл Васильевич','USUAL','1991-04-10','MALE','EXPECTING',NULL),('Матышев Дмитрий Сергеевич','IMPORTANT','2024-12-16','MALE','SETTLED',58),('Мельникова Светлана Романовна','VERY_IMPORTANT','1989-03-18','FEMALE','EXPECTING',NULL),('Михайлова Екатерина Валентиновна','NOT_IMPORTANT','1992-02-03','FEMALE','SETTLED',3),('Морозова Анастасия Семёновна','NOT_IMPORTANT','1984-05-17','FEMALE','EXPECTING',NULL),('Никитина Вера Александровна','NOT_IMPORTANT','1984-07-07','FEMALE','EXPECTING',NULL),('Новиков Дмитрий Викторович','USUAL','1988-08-19','MALE','SETTLED',5),('Павлова Ирина Васильевна','IMPORTANT','1993-07-03','FEMALE','EXPECTING',NULL),('Павловская Татьяна Леонидовна','IMPORTANT','1983-01-13','FEMALE','EXPECTING',NULL),('Петров Иван Романович','VERY_IMPORTANT','1983-08-20','MALE','EXPECTING',NULL),('Петрова Анна Сергеевна','USUAL','1990-07-22','FEMALE','SETTLED',1),('Попова Ольга Викторовна','NOT_IMPORTANT','1995-09-08','FEMALE','SETTLED',4),('Прокофьева Алёна Викторовна','VERY_IMPORTANT','1989-01-22','FEMALE','EXPECTING',NULL),('Романов Андрей Анатольевич','NOT_IMPORTANT','1983-03-15','MALE','EXPECTING',NULL),('Романова Татьяна Вячеславовна','USUAL','1990-11-11','FEMALE','SETTLED',4),('Руденко Павел Игоревич','IMPORTANT','1978-04-12','MALE','EXPECTING',NULL),('Сергеев Игорь Юрьевич','USUAL','1981-01-25','MALE','EXPECTING',NULL),('Сидоров Алексей Викторович','IMPORTANT','1975-03-30','MALE','SETTLED',2),('Сидорова Лариса Николаевна','NOT_IMPORTANT','1985-06-22','FEMALE','EXPECTING',NULL),('Смирнов Виктор Павлович','USUAL','1992-09-28','MALE','SETTLED',13),('Смирнов Николай Сергеевич','USUAL','1978-10-30','MALE','SETTLED',13),('Смирнов Павел Валентинович','IMPORTANT','1991-06-17','MALE','EXPECTING',NULL),('Соколова Наталья Константиновна','NOT_IMPORTANT','1994-04-06','FEMALE','EXPECTING',NULL),('Степанова Ирина Михайловна','NOT_IMPORTANT','1988-12-11','FEMALE','EXPECTING',NULL),('Тимофеева Светлана Андреевна','IMPORTANT','1985-03-06','FEMALE','EXPECTING',NULL),('Тимурова Анна Валентиновна','IMPORTANT','1993-08-03','FEMALE','EXPECTING',NULL),('Трофимов Сергей Владимирович','VERY_IMPORTANT','1991-02-14','MALE','EXPECTING',NULL),('Федорова Елена Вячеславовна','VERY_IMPORTANT','1987-04-22','FEMALE','EXPECTING',NULL),('Флекс Алек Димитрович','IMPORTANT','2024-11-07','MALE','EXPECTING',NULL),('Фролов Игорь Александрович','VERY_IMPORTANT','1982-05-30','MALE','EXPECTING',NULL),('Чеботарев Иван Георгиевич','VERY_IMPORTANT','1986-06-21','MALE','EXPECTING',NULL),('Чистякова Екатерина Николаевна','NOT_IMPORTANT','1980-11-19','FEMALE','EXPECTING',NULL),('Шевцов Артем Иванович','USUAL','1983-05-16','MALE','EXPECTING',NULL),('Шевченко Ирина Александровна','VERY_IMPORTANT','1981-04-16','FEMALE','SETTLED',3);
/*!40000 ALTER TABLE `resident` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `responsible_person`
--

DROP TABLE IF EXISTS `responsible_person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `responsible_person` (
  `fio` varchar(255) NOT NULL,
  `mail` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`fio`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `responsible_person`
--

LOCK TABLES `responsible_person` WRITE;
/*!40000 ALTER TABLE `responsible_person` DISABLE KEYS */;
INSERT INTO `responsible_person` VALUES ('Дима Дима','Дима','Дима'),('Евдокимов Сергей Викторович',NULL,NULL),('Иванов Иван Иванович','ivanov@example.com','+7 (123) 456-78-90'),('Козлов Алексей Владимирович','kozlov@example.com',NULL),('Лебедев Андрей Валерьевич',NULL,NULL),('Матышев Дмитрий Сергеевич','211500','Есть'),('Михайлов Михаил Петрович',NULL,'+7 (567) 890-12-34'),('Петров Петр Петрович','petrov@example.com','+7 (234) 567-89-01'),('Романов Роман Анатольевич',NULL,'+7 (678) 901-23-45');
/*!40000 ALTER TABLE `responsible_person` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room`
--

DROP TABLE IF EXISTS `room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room` (
  `id` int NOT NULL AUTO_INCREMENT,
  `designed_for` enum('FEMALE','MALE','NOT_EXIST') DEFAULT NULL,
  `number` int DEFAULT NULL,
  `number_of_available_places` int DEFAULT NULL,
  `number_of_places` int DEFAULT NULL,
  `block` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKagwup627n43hdwhu2keht5dy` (`block`),
  CONSTRAINT `FKagwup627n43hdwhu2keht5dy` FOREIGN KEY (`block`) REFERENCES `block` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room`
--

LOCK TABLES `room` WRITE;
/*!40000 ALTER TABLE `room` DISABLE KEYS */;
INSERT INTO `room` VALUES (1,'MALE',1011,3,4,1),(2,'MALE',1012,2,4,1),(3,'FEMALE',1021,2,4,2),(4,'FEMALE',1022,2,4,2),(5,'MALE',1031,2,4,3),(6,'MALE',1032,4,4,3),(7,'FEMALE',1041,4,4,4),(8,'FEMALE',1042,4,4,4),(9,'MALE',1051,4,4,5),(10,'MALE',1052,4,4,5),(11,'FEMALE',2011,4,4,6),(12,'FEMALE',2012,4,4,6),(13,'MALE',2021,2,4,7),(14,'MALE',2022,4,4,7),(15,'FEMALE',2031,4,4,8),(16,'FEMALE',2032,4,4,8),(17,'MALE',2041,4,4,9),(18,'MALE',2042,4,4,9),(19,'FEMALE',2051,4,4,10),(20,'FEMALE',2052,4,4,10),(21,'MALE',3011,4,4,11),(22,'MALE',3012,4,4,11),(23,'FEMALE',3021,4,4,12),(24,'FEMALE',3022,4,4,12),(25,'MALE',3031,4,4,13),(26,'MALE',3032,4,4,13),(27,'FEMALE',3041,4,4,14),(28,'FEMALE',3042,3,4,14),(29,'MALE',3051,4,4,15),(30,'MALE',3052,4,4,15),(31,'FEMALE',4011,4,4,16),(32,'FEMALE',4012,4,4,16),(33,'MALE',4021,4,4,17),(34,'MALE',4022,4,4,17),(35,'FEMALE',4031,4,4,18),(36,'FEMALE',4032,4,4,18),(37,'MALE',4041,3,4,19),(38,'MALE',4042,4,4,19),(39,'FEMALE',4051,4,4,20),(40,'FEMALE',4052,4,4,20),(41,'MALE',5011,4,4,21),(42,'MALE',5012,4,4,21),(43,'FEMALE',5021,3,4,22),(44,'FEMALE',5022,4,4,22),(45,'MALE',5031,4,4,23),(46,'MALE',5032,4,4,23),(47,'FEMALE',5041,4,4,24),(48,'FEMALE',5042,4,4,24),(49,'MALE',5051,4,4,25),(50,'MALE',5052,4,4,25),(51,'MALE',5053,2,2,NULL),(53,'MALE',6011,1,1,31),(54,'MALE',6012,1,1,31),(57,'FEMALE',6031,22,22,33),(58,'MALE',11161,0,1,34);
/*!40000 ALTER TABLE `room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `password` varchar(255) DEFAULT NULL,
  `login` varchar(255) DEFAULT NULL,
  `sold` varbinary(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKew1hvam8uwaknuaellwhqchhb` (`login`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (3,'vZ2QL5zrKQ85d3bXE6rO2SDNX4rHhHP5cXI07Glo7Hg=','user',_binary 'Ca�0\�/��h�\��\�0'),(4,'ognfei+po4a9sfl4rL9cewalfmooAKJeb9SPPyTyBoE=','admin',_binary '��9�\�\�\0�\�\�Ҹ�'),(9,'zVpnM//5CqK8LPktmYxtx3nDx3zB+g5E/j9jSQvN/84=','toha',_binary '1\�4\�pR��^a�)\�\�');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-12-05  2:14:36
