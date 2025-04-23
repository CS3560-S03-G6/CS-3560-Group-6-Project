CREATE DATABASE  IF NOT EXISTS `spacecraft_mission_monitoring_system` 
/*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `spacecraft_mission_monitoring_system`;
-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: localhost    Database: spacecraft mission monitoring system
-- ------------------------------------------------------
-- Server version	8.0.41

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
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `employeeID` int NOT NULL,
  `name` varchar(100) NOT NULL,
  `role` varchar(100) NOT NULL,
  `workEmail` varchar(255) NOT NULL,
  `phoneNumber` varchar(20) DEFAULT NULL,
  `location` int DEFAULT NULL,
  PRIMARY KEY (`employeeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `flightdirector`
--

DROP TABLE IF EXISTS `flightdirector`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `flightdirector` (
  `employeeID` int NOT NULL,
  `yearsOfExperience` int NOT NULL,
  PRIMARY KEY (`employeeID`),
  CONSTRAINT `flightdirector_ibfk_1` FOREIGN KEY (`employeeID`) REFERENCES `employee` (`employeeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `issue`
--

DROP TABLE IF EXISTS `issue`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `issue` (
  `issueID` int NOT NULL,
  `missionID` int DEFAULT NULL,
  `issueType` varchar(100) DEFAULT NULL,
  `detectionTime` varchar(50) DEFAULT NULL,
  `severityLevel` int DEFAULT NULL,
  `alertTriggered` tinyint(1) DEFAULT NULL,
  `resolutionStatus` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`issueID`),
  KEY `missionID` (`missionID`),
  CONSTRAINT `issue_ibfk_1` FOREIGN KEY (`missionID`) REFERENCES `mission` (`missionID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `maneuver`
--

DROP TABLE IF EXISTS `maneuver`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `maneuver` (
  `maneuverID` int NOT NULL,
  `missionID` int DEFAULT NULL,
  `employeeID` int DEFAULT NULL,
  `crewID` int DEFAULT NULL,
  `systemID` int DEFAULT NULL,
  `maneuverType` varchar(100) DEFAULT NULL,
  `maneuverDetails` text,
  `executionTime` int DEFAULT NULL,
  `fuelCost` int DEFAULT NULL,
  `locationChange` varchar(100) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  `loggedTime` varchar(50) DEFAULT NULL,
  `loggedBy` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`maneuverID`),
  KEY `missionID` (`missionID`),
  KEY `employeeID` (`employeeID`),
  KEY `crewID` (`crewID`),
  KEY `systemID` (`systemID`),
  CONSTRAINT `maneuver_ibfk_1` FOREIGN KEY (`missionID`) REFERENCES `mission` (`missionID`),
  CONSTRAINT `maneuver_ibfk_2` FOREIGN KEY (`employeeID`) REFERENCES `flightdirector` (`employeeID`),
  CONSTRAINT `maneuver_ibfk_3` FOREIGN KEY (`crewID`) REFERENCES `spacecraftcrew` (`crewID`),
  CONSTRAINT `maneuver_ibfk_4` FOREIGN KEY (`systemID`) REFERENCES `spacecraftcomputersystem` (`systemID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `mission`
--

DROP TABLE IF EXISTS `mission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mission` (
  `missionID` int NOT NULL AUTO_INCREMENT,
  `employeeID` int DEFAULT NULL,
  `missionName` varchar(100) NOT NULL,
  `missionType` varchar(100) DEFAULT NULL,
  `launchDate` varchar(50) DEFAULT NULL,
  `missionStatus` varchar(50) DEFAULT NULL,
  `missionObjective` text,
  `initialFuelLevel` int DEFAULT NULL,
  `initialLocation` varchar(100) DEFAULT NULL,
  `terminationDate` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`missionID`),
  KEY `employeeID` (`employeeID`),
  CONSTRAINT `mission_ibfk_1` FOREIGN KEY (`employeeID`) REFERENCES `missioncontroller` (`employeeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `missioncontroller`
--

DROP TABLE IF EXISTS `missioncontroller`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `missioncontroller` (
  `employeeID` int NOT NULL,
  `authorityLevel` int NOT NULL,
  PRIMARY KEY (`employeeID`),
  CONSTRAINT `missioncontroller_ibfk_1` FOREIGN KEY (`employeeID`) REFERENCES `employee` (`employeeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `missionreport`
--

DROP TABLE IF EXISTS `missionreport`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `missionreport` (
  `missionReportID` int NOT NULL,
  `missionID` int DEFAULT NULL,
  `dateGenerated` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`missionReportID`),
  KEY `missionID` (`missionID`),
  CONSTRAINT `missionreport_ibfk_1` FOREIGN KEY (`missionID`) REFERENCES `mission` (`missionID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `reportissue`
--

DROP TABLE IF EXISTS `reportissue`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reportissue` (
  `missionReportID` int NOT NULL,
  `issueID` int NOT NULL,
  PRIMARY KEY (`missionReportID`,`issueID`),
  KEY `issueID` (`issueID`),
  CONSTRAINT `reportissue_ibfk_1` FOREIGN KEY (`missionReportID`) REFERENCES `missionreport` (`missionReportID`),
  CONSTRAINT `reportissue_ibfk_2` FOREIGN KEY (`issueID`) REFERENCES `issue` (`issueID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `reportmaneuver`
--

DROP TABLE IF EXISTS `reportmaneuver`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reportmaneuver` (
  `missionReportID` int NOT NULL,
  `maneuverID` int NOT NULL,
  PRIMARY KEY (`missionReportID`,`maneuverID`),
  KEY `maneuverID` (`maneuverID`),
  CONSTRAINT `reportmaneuver_ibfk_1` FOREIGN KEY (`missionReportID`) REFERENCES `missionreport` (`missionReportID`),
  CONSTRAINT `reportmaneuver_ibfk_2` FOREIGN KEY (`maneuverID`) REFERENCES `maneuver` (`maneuverID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `spacecraft`
--

DROP TABLE IF EXISTS `spacecraft`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `spacecraft` (
  `spacecraftID` int NOT NULL,
  `missionID` int DEFAULT NULL,
  `spacecraftName` varchar(100) NOT NULL,
  `spacecraftType` varchar(100) DEFAULT NULL,
  `manufacturer` varchar(100) DEFAULT NULL,
  `capacity` int DEFAULT NULL,
  `maxFuelCapacity` int DEFAULT NULL,
  `thrustPower` int DEFAULT NULL,
  `weight` int DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`spacecraftID`),
  KEY `missionID` (`missionID`),
  CONSTRAINT `spacecraft_ibfk_1` FOREIGN KEY (`missionID`) REFERENCES `mission` (`missionID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `spacecraftcomputersystem`
--

DROP TABLE IF EXISTS `spacecraftcomputersystem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `spacecraftcomputersystem` (
  `systemID` int NOT NULL,
  `spacecraftID` int DEFAULT NULL,
  `communicationStatus` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`systemID`),
  KEY `spacecraftID` (`spacecraftID`),
  CONSTRAINT `spacecraftcomputersystem_ibfk_1` FOREIGN KEY (`spacecraftID`) REFERENCES `spacecraft` (`spacecraftID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `spacecraftcrew`
--

DROP TABLE IF EXISTS `spacecraftcrew`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `spacecraftcrew` (
  `crewID` int NOT NULL,
  `missionID` int DEFAULT NULL,
  `crewSize` int DEFAULT NULL,
  `commander` varchar(100) DEFAULT NULL,
  `crewHealthStatus` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`crewID`),
  KEY `missionID` (`missionID`),
  CONSTRAINT `spacecraftcrew_ibfk_1` FOREIGN KEY (`missionID`) REFERENCES `mission` (`missionID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-04-18 15:02:03