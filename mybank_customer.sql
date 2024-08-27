-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: mybank
-- ------------------------------------------------------
-- Server version	8.0.34

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `customer_id` int NOT NULL AUTO_INCREMENT,
  `account_number` int DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `customer_address` varchar(255) DEFAULT NULL,
  `nominee_name` varchar(255) DEFAULT NULL,
  `balance` int NOT NULL DEFAULT '1000',
  `account_open_date` datetime(6) DEFAULT NULL,
  `identification_number` int DEFAULT NULL,
  `is_active` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`customer_id`),
  UNIQUE KEY `account_number` (`account_number`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,3,'Aman','Yadav','amanyadav23421@gmail.com','Village-matora','Aryan',0,'2024-08-10 09:18:46.825864',0,1),(3,5,'Aman','Yadav','amanyadav23421@gmail.com','Village-matora','Aryan',0,'2024-08-10 09:46:08.236904',0,1),(4,6,'Aman','Yadav','amanyadav23421@gmail.com','Village-matora','Aryan',60,'2024-08-10 09:48:49.054866',0,1),(5,7,'Aman','Yadav','amanyadav23421@gmail.com','Village-matora','Aryan',0,'2024-08-10 09:50:18.674515',0,0),(6,9,'Aman','Yadav','amanyadav23421@gmail.com','Village-matora','Aryan',0,'2024-08-10 09:53:42.574078',2024,1),(11,20,'abc','d','nobi@gmail.com','abc','a',9998,'2024-08-11 01:02:27.612215',2002,1),(12,21,'jiyan','jiyan','jiyan@gmail.com','tokeyo','doremon',1,'2024-08-11 01:18:36.044891',1001,1),(13,25,'yara','yara','aman.2024csit1089@kiet.edu','yara','para',1000,'2024-08-12 09:25:21.723969',101010,1),(14,26,'yara','yara','aman.2024csit1089@kiet.edu','yara','para',1000,'2024-08-12 09:26:47.144879',101010,1),(16,28,'sunil1','kumar1','amanyadav23421@gmail.com','meerut','jyoti yadav1',1000,'2024-08-12 17:05:44.796849',1234,1),(17,29,'sunil','kumar','anm9542@gmail.com','meerut','jyoti yadav',1010,'2024-08-12 17:27:23.103246',1234,1),(18,30,'sunil','kumar','anm9542@gmail.com','meerut','jyoti yadav',1007,'2024-08-12 17:30:43.625727',1234,1),(23,31,'sunil','kumar','anm9542@gmail.com','meerut','jyoti yadav',994,'2024-08-12 17:31:53.670845',1234,1),(24,32,'jyoti','yadav','amanyadav23421@gmail.com','matora','sunil kumar',1030,'2024-08-12 19:14:01.153964',121,1),(25,33,'Shiva shetty','kumar','amanyadav23421@gmail.com','meerut','aman',960,'2024-08-13 17:05:53.551835',120,1),(26,27,'sunil','kumar','amanyadav23421@gmail.com','meerut','jyoti yadav',1000,'2024-08-12 13:35:39.309342',1234,0),(27,34,'abd','abd','aman.2024csit1089@kiet.edu','meerut','abd',940,'2024-08-14 11:10:33.673868',101010,1),(28,35,'yara','yara','aman.2024csit1089@kiet.edu','yara','para',1000,'2024-08-14 11:16:26.067500',101010,1),(29,36,'abhay','yadav','aju@swabhavtechlabs.com','delhi','aman',1000,'2024-08-14 12:15:23.944230',1111,1),(30,37,'abhay','yadav','yadavabhahay30092000@gmail.com','Village- matora ,post- meewa, tehsil- mawana','aman',1000,'2024-08-27 14:57:37.930004',12340000,1);
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-08-27 22:57:36
