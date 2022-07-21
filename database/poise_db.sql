-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: poise_db
-- ------------------------------------------------------
-- Server version	8.0.29

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
-- Table structure for table `architects`
--

DROP TABLE IF EXISTS `architects`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `architects` (
  `architectNo` int NOT NULL AUTO_INCREMENT,
  `architect_name` varchar(50) DEFAULT NULL,
  `architect_email` varchar(50) DEFAULT NULL,
  `architect_address` varchar(80) DEFAULT NULL,
  `architect_tel` varchar(30) DEFAULT NULL,
  `projectNo` int DEFAULT NULL,
  PRIMARY KEY (`architectNo`),
  KEY `architect_projectNo_FK_idx` (`projectNo`),
  CONSTRAINT `architect_projectNo_FK` FOREIGN KEY (`projectNo`) REFERENCES `projects` (`projectNo`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `architects`
--

LOCK TABLES `architects` WRITE;
/*!40000 ALTER TABLE `architects` DISABLE KEYS */;
INSERT INTO `architects` VALUES (12,'Arsene Mwense','Lupin@fddhh.com','32 jfrvfr rvrv','07145857',2),(27,'Arsene','Arsene@gmail.com','132 main road, Claremont','0714238756',1);
/*!40000 ALTER TABLE `architects` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customers`
--

DROP TABLE IF EXISTS `customers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customers` (
  `customerNo` int NOT NULL AUTO_INCREMENT,
  `customer_name` varchar(50) DEFAULT NULL,
  `customer_email` varchar(50) DEFAULT NULL,
  `customer_address` varchar(50) DEFAULT NULL,
  `customer_tel` varchar(30) DEFAULT NULL,
  `projectNo` int DEFAULT NULL,
  PRIMARY KEY (`customerNo`),
  KEY `customer_projectNo_FK_idx` (`projectNo`),
  CONSTRAINT `customer_projectNo_FK` FOREIGN KEY (`projectNo`) REFERENCES `projects` (`projectNo`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customers`
--

LOCK TABLES `customers` WRITE;
/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
INSERT INTO `customers` VALUES (15,'Billy','Billy@hotmail.com','9 Albion road, Rondebosch','0678946513',1),(27,'Jones','Jones@outlook.com','17 Orange street, Cape Town','0728953651',2);
/*!40000 ALTER TABLE `customers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `managers`
--

DROP TABLE IF EXISTS `managers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `managers` (
  `managerNo` int NOT NULL AUTO_INCREMENT,
  `manager_name` varchar(50) DEFAULT NULL,
  `manager_email` varchar(50) DEFAULT NULL,
  `manager_address` varchar(50) DEFAULT NULL,
  `manager_tel` varchar(30) DEFAULT NULL,
  `projectNo` int DEFAULT NULL,
  PRIMARY KEY (`managerNo`),
  KEY `manager_projectNo_FK_idx` (`projectNo`),
  CONSTRAINT `manager_projectNo_FK` FOREIGN KEY (`projectNo`) REFERENCES `projects` (`projectNo`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `managers`
--

LOCK TABLES `managers` WRITE;
/*!40000 ALTER TABLE `managers` DISABLE KEYS */;
INSERT INTO `managers` VALUES (8,'Barrel','Barrel@gmail.com','18 Bree street, Cape Town','0724658214',1),(11,'erbresbre','erberb','rebrebre','brebreb',2);
/*!40000 ALTER TABLE `managers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `projects`
--

DROP TABLE IF EXISTS `projects`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `projects` (
  `projectNo` int NOT NULL AUTO_INCREMENT,
  `project_name` varchar(50) DEFAULT NULL,
  `building_type` varchar(50) DEFAULT NULL,
  `building_address` varchar(80) DEFAULT NULL,
  `erf_num` varchar(50) DEFAULT NULL,
  `project_price` int DEFAULT NULL,
  `due_date` date DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  `completion_date` date DEFAULT NULL,
  `amount_paid` int DEFAULT NULL,
  PRIMARY KEY (`projectNo`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `projects`
--

LOCK TABLES `projects` WRITE;
/*!40000 ALTER TABLE `projects` DISABLE KEYS */;
INSERT INTO `projects` VALUES (1,'Blue Sky Hotel','Hotel','1 Longstreet, Cape Town','1289',22300000,'2023-08-17','In Progress',NULL,NULL),(2,'The Quadrant','Apartments','31 Wilderness road, Claremont','3256',32900000,'2027-06-29','Finalised','2022-07-20',3280100);
/*!40000 ALTER TABLE `projects` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-07-21 13:20:59
