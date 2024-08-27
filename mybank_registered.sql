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
-- Table structure for table `registered`
--

DROP TABLE IF EXISTS `registered`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `registered` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `nominee_name` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `address` varchar(500) DEFAULT NULL,
  `unique_identification_number` varchar(255) DEFAULT NULL,
  `doc_extension` varchar(255) DEFAULT NULL,
  `is_uploaded` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `registered`
--

LOCK TABLES `registered` WRITE;
/*!40000 ALTER TABLE `registered` DISABLE KEYS */;
INSERT INTO `registered` VALUES (17,'Aman','Frontend','Sunil','aman.2024csit1089@kiet.edu','Matora','429569629532',NULL,NULL),(21,'Sunil','Kumar','abc','amanyadav23421@gmail.com','Near main shiv mandir, village-matora','1234432',NULL,NULL),(22,'Sunil','Kumar','aman','amanyadav23421@gmail.com','Near main shiv mandir, village-matora','2222',NULL,NULL),(24,'abhay','yadav','sunill','yadavabhahay30092000@gmail.com','Village- matora ,post- meewa, tehsil- mawana','092123',NULL,NULL),(26,'Sunil','Kumar','aman','amanyadav23421@gmail.com','Near main shiv mandir, village-matora','989823',NULL,NULL),(27,'Sunil','Kumar','aman','amanyadav23421@gmail.com','Near main shiv mandir, village-matora','989823',NULL,NULL),(28,'Sunil','Kumar','aman','amanyadav23421@gmail.com','Near main shiv mandir, village-matora','989823',NULL,NULL),(29,'abhay','yadav','aryan','yadavabhahay30092000@gmail.com','Village- matora ,post- meewa, tehsil- mawana','123123106',NULL,NULL),(30,'abhay','yadav','aryan','yadavabhahay30092000@gmail.com','Village- matora ,post- meewa, tehsil- mawana','124121',NULL,NULL),(31,'abhay','yadav','aryan','yadavabhahay30092000@gmail.com','Village- matora ,post- meewa, tehsil- mawana','124121','.html',_binary '');
/*!40000 ALTER TABLE `registered` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-08-27 22:57:37
