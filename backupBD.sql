CREATE DATABASE  IF NOT EXISTS `plataformasalud` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `plataformasalud`;
-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: plataformasalud
-- ------------------------------------------------------
-- Server version	8.0.31

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
-- Table structure for table `cargo_user`
--

DROP TABLE IF EXISTS `cargo_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cargo_user` (
  `idcarguser` bigint NOT NULL AUTO_INCREMENT,
  `datecreatcarguser` date DEFAULT NULL,
  `detcarguser` varchar(45) DEFAULT NULL,
  `nomcarg` varchar(10) DEFAULT NULL,
  `estado_carguser_idstatus` bigint DEFAULT NULL,
  `roluser_idrol` bigint DEFAULT NULL,
  PRIMARY KEY (`idcarguser`),
  UNIQUE KEY `UK_56tkr5xui3dfct08k3jqluqa1` (`detcarguser`),
  UNIQUE KEY `UK_fgkb67yispkte9gsuk6nwgosv` (`nomcarg`),
  KEY `FK72dbpb1v7lnq127q4nekuqf75` (`estado_carguser_idstatus`),
  KEY `FKe7vu85dga2l9nngf70gjtm400` (`roluser_idrol`),
  CONSTRAINT `FK72dbpb1v7lnq127q4nekuqf75` FOREIGN KEY (`estado_carguser_idstatus`) REFERENCES `status` (`idstatus`),
  CONSTRAINT `FKe7vu85dga2l9nngf70gjtm400` FOREIGN KEY (`roluser_idrol`) REFERENCES `rol` (`idrol`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cargo_user`
--

LOCK TABLES `cargo_user` WRITE;
/*!40000 ALTER TABLE `cargo_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `cargo_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ciudades`
--

DROP TABLE IF EXISTS `ciudades`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ciudades` (
  `idciu` bigint NOT NULL AUTO_INCREMENT,
  `codciu` bigint NOT NULL,
  `datecreatciu` date DEFAULT NULL,
  `nomciu` varchar(30) NOT NULL,
  `depciu_fk_iddep` bigint DEFAULT NULL,
  `estciu_fk_idstatus` bigint DEFAULT NULL,
  PRIMARY KEY (`idciu`),
  UNIQUE KEY `UK_rov3t1becjx5b58nperrphikt` (`codciu`),
  UNIQUE KEY `UK_hv43qmed6vlk8rlun5phc4wwu` (`nomciu`),
  KEY `FK25kjuw4cpyo3eoro6ob6tspid` (`depciu_fk_iddep`),
  KEY `FKpg32nb0o374necjgfg85q6ldp` (`estciu_fk_idstatus`),
  CONSTRAINT `FK25kjuw4cpyo3eoro6ob6tspid` FOREIGN KEY (`depciu_fk_iddep`) REFERENCES `departamentos` (`iddep`),
  CONSTRAINT `FKpg32nb0o374necjgfg85q6ldp` FOREIGN KEY (`estciu_fk_idstatus`) REFERENCES `status` (`idstatus`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ciudades`
--

LOCK TABLES `ciudades` WRITE;
/*!40000 ALTER TABLE `ciudades` DISABLE KEYS */;
INSERT INTO `ciudades` VALUES (1,73001,'2024-04-11','IBAGUE',1,1),(2,73002,'2024-04-11','ESPINAL',1,1),(3,11001,'2024-04-11','BOGOTA, D.C.',2,1),(4,11002,'2024-04-11','FUSA',2,1),(5,5001,'2024-04-11','MEDELLIN',3,1),(6,5002,'2024-04-11','ENVIGADO',3,1);
/*!40000 ALTER TABLE `ciudades` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contratos`
--

DROP TABLE IF EXISTS `contratos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contratos` (
  `idcontrato` bigint NOT NULL AUTO_INCREMENT,
  `detcontrato` varchar(500) NOT NULL,
  `entidad_ideapb` bigint DEFAULT NULL,
  PRIMARY KEY (`idcontrato`),
  KEY `FKrggngmm8ecnn2u33ll2ypq0hq` (`entidad_ideapb`),
  CONSTRAINT `FKrggngmm8ecnn2u33ll2ypq0hq` FOREIGN KEY (`entidad_ideapb`) REFERENCES `eapb` (`ideapb`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contratos`
--

LOCK TABLES `contratos` WRITE;
/*!40000 ALTER TABLE `contratos` DISABLE KEYS */;
/*!40000 ALTER TABLE `contratos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `departamentos`
--

DROP TABLE IF EXISTS `departamentos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `departamentos` (
  `iddep` bigint NOT NULL AUTO_INCREMENT,
  `coddep` bigint NOT NULL,
  `datecreatdep` date DEFAULT NULL,
  `nomdep` varchar(30) NOT NULL,
  `estdep_fk_idstatus` bigint DEFAULT NULL,
  PRIMARY KEY (`iddep`),
  UNIQUE KEY `UK_d0yhv1ewyq0ckn7c1vtfj2uu4` (`coddep`),
  UNIQUE KEY `UK_2xd2ywkw43smmq4fhs9ce7q00` (`nomdep`),
  KEY `FKf1ij1ltfydn1cmedahwowpfxr` (`estdep_fk_idstatus`),
  CONSTRAINT `FKf1ij1ltfydn1cmedahwowpfxr` FOREIGN KEY (`estdep_fk_idstatus`) REFERENCES `status` (`idstatus`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `departamentos`
--

LOCK TABLES `departamentos` WRITE;
/*!40000 ALTER TABLE `departamentos` DISABLE KEYS */;
INSERT INTO `departamentos` VALUES (1,73,'2024-04-11','TOLIMA',1),(2,11,'2024-04-11','CUNDINAMARCA',1),(3,5,'2024-04-11','ANTIOQUIA',1);
/*!40000 ALTER TABLE `departamentos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `desqx`
--

DROP TABLE IF EXISTS `desqx`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `desqx` (
  `idqx` bigint NOT NULL AUTO_INCREMENT,
  `complicqx` varchar(255) DEFAULT NULL,
  `conducqx` varchar(255) NOT NULL,
  `descqx` varchar(2000) NOT NULL,
  `fechaprocqx` date NOT NULL,
  `hallaqx` varchar(255) DEFAULT NULL,
  `horafinprocqx` datetime(6) NOT NULL,
  `horainicioprocqx` datetime(6) NOT NULL,
  `matprot` varchar(255) DEFAULT NULL,
  `muespato` varchar(255) DEFAULT NULL,
  `timeqx` bigint DEFAULT NULL,
  `tipoherida` varchar(20) NOT NULL,
  `typqx` varchar(255) DEFAULT NULL,
  `anestesia_fk_idtipanest` bigint DEFAULT NULL,
  `eventpxqx_fk_idevent` bigint DEFAULT NULL,
  PRIMARY KEY (`idqx`),
  KEY `FKj76qcbg6ca803rrsrxunhhk3g` (`anestesia_fk_idtipanest`),
  KEY `FK90sr9nqniwsidj902jqfgj5a6` (`eventpxqx_fk_idevent`),
  CONSTRAINT `FK90sr9nqniwsidj902jqfgj5a6` FOREIGN KEY (`eventpxqx_fk_idevent`) REFERENCES `eventhcpac` (`idevent`),
  CONSTRAINT `FKj76qcbg6ca803rrsrxunhhk3g` FOREIGN KEY (`anestesia_fk_idtipanest`) REFERENCES `tipanest` (`idtipanest`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `desqx`
--

LOCK TABLES `desqx` WRITE;
/*!40000 ALTER TABLE `desqx` DISABLE KEYS */;
/*!40000 ALTER TABLE `desqx` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dx`
--

DROP TABLE IF EXISTS `dx`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dx` (
  `clavedx` bigint NOT NULL AUTO_INCREMENT,
  `capdx` varchar(3) NOT NULL,
  `creatdatedx` date DEFAULT NULL,
  `descdx` varchar(255) NOT NULL,
  `edadmaxdx` bigint NOT NULL,
  `edadmindx` bigint NOT NULL,
  `iddx` varchar(255) DEFAULT NULL,
  `nomdx` varchar(255) NOT NULL,
  `sexdx` varchar(10) NOT NULL,
  `estdx_fk_idstatus` bigint DEFAULT NULL,
  PRIMARY KEY (`clavedx`),
  UNIQUE KEY `UK_mc1uyojln5jktfco4l70dbgs7` (`nomdx`),
  KEY `FKknru1fllgvfbbvavddaxxglr5` (`estdx_fk_idstatus`),
  CONSTRAINT `FKknru1fllgvfbbvavddaxxglr5` FOREIGN KEY (`estdx_fk_idstatus`) REFERENCES `status` (`idstatus`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dx`
--

LOCK TABLES `dx` WRITE;
/*!40000 ALTER TABLE `dx` DISABLE KEYS */;
INSERT INTO `dx` VALUES (1,'A09','2024-05-01','DIARREA Y GASTROENTERITIS DE PRESUNTO ORIGEN INFECCIOSO',999,0,'A09X','DIARREA Y GASTROENTERITIS DE PRESUNTO ORIGEN INFECCIOSO','AMBOS',1),(2,'D35','2024-05-01','TUMOR BENIGNO DE OTRAS GLANDULAS ENDOCRINAS Y DE LAS NO ESPECIFICADAS',999,0,'D355','TUMOR BENIGNO DEL CUERPO CAROTIDEO','AMBOS',1),(3,'D40','2024-05-01','TUMOR DE COMPORTAMIENTO INCIERTO O DESCONOCIDO DE LOS ORGANOS GENITALES MASCULINOS',120,10,'D400','TUMOR DE COMPORTAMIENTO INCIERTO O DESCONOCIDO DE LA PROSTATA','MASCULINO',1),(4,'D06','2024-05-01','CARCINOMA IN SITU DEL CUELLO DEL UTERO',999,0,'D061','CARCINOMA IN SITU DEL EXOCERVIX','FEMENINO',1);
/*!40000 ALTER TABLE `dx` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dxatehcpac`
--

DROP TABLE IF EXISTS `dxatehcpac`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dxatehcpac` (
  `iddxathcpac` bigint NOT NULL AUTO_INCREMENT,
  `creatdxathcpac` date DEFAULT NULL,
  `editdxathcpac` date DEFAULT NULL,
  `origdx` varchar(50) NOT NULL,
  `dxatehcpac_fk_clavedx` bigint DEFAULT NULL,
  `estdxatehcpac_idstatus` bigint DEFAULT NULL,
  `eventdxate_fk_idevent` bigint DEFAULT NULL,
  `typdxatehcpac_fk_idtypdx` bigint DEFAULT NULL,
  PRIMARY KEY (`iddxathcpac`),
  UNIQUE KEY `UK_fcg09p53dvktylfy3sgmr714n` (`origdx`),
  KEY `FK2dxykojw1cd7hwrohxejvk4o0` (`dxatehcpac_fk_clavedx`),
  KEY `FKsgxdoijorg3gl47q62d0l6qpw` (`estdxatehcpac_idstatus`),
  KEY `FK4m0nbono1pufm9eihi8b1tv8l` (`eventdxate_fk_idevent`),
  KEY `FKbuyrgix2mkyo7001wxbt8dplh` (`typdxatehcpac_fk_idtypdx`),
  CONSTRAINT `FK2dxykojw1cd7hwrohxejvk4o0` FOREIGN KEY (`dxatehcpac_fk_clavedx`) REFERENCES `dx` (`clavedx`),
  CONSTRAINT `FK4m0nbono1pufm9eihi8b1tv8l` FOREIGN KEY (`eventdxate_fk_idevent`) REFERENCES `eventhcpac` (`idevent`),
  CONSTRAINT `FKbuyrgix2mkyo7001wxbt8dplh` FOREIGN KEY (`typdxatehcpac_fk_idtypdx`) REFERENCES `typdx` (`idtypdx`),
  CONSTRAINT `FKsgxdoijorg3gl47q62d0l6qpw` FOREIGN KEY (`estdxatehcpac_idstatus`) REFERENCES `status` (`idstatus`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dxatehcpac`
--

LOCK TABLES `dxatehcpac` WRITE;
/*!40000 ALTER TABLE `dxatehcpac` DISABLE KEYS */;
/*!40000 ALTER TABLE `dxatehcpac` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `eapb`
--

DROP TABLE IF EXISTS `eapb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `eapb` (
  `ideapb` bigint NOT NULL AUTO_INCREMENT,
  `contaceapb` varchar(15) NOT NULL,
  `datecreateapb` date DEFAULT NULL,
  `direapb` varchar(50) NOT NULL,
  `doceapb` bigint NOT NULL,
  `emaileapb` varchar(50) NOT NULL,
  `gerenteapb` varchar(45) NOT NULL,
  `nomeapb` varchar(50) NOT NULL,
  `esteapb_fk_idstatus` bigint DEFAULT NULL,
  `tipdoceapb_fk_idtipdoc` bigint DEFAULT NULL,
  `tipent_idtipeapb` bigint DEFAULT NULL,
  PRIMARY KEY (`ideapb`),
  UNIQUE KEY `UK_sx54nmih57sv44np4k0vyb2a` (`doceapb`),
  UNIQUE KEY `UK_hu9legjru6rb24fwq9w5bp4ly` (`nomeapb`),
  KEY `FKo9obv6aiertd2quabuaoua25i` (`esteapb_fk_idstatus`),
  KEY `FK8ihg5pwmlxx32n5elt83kwfg7` (`tipdoceapb_fk_idtipdoc`),
  KEY `FKmy4sf5hsjteo1re3kyte69oyf` (`tipent_idtipeapb`),
  CONSTRAINT `FK8ihg5pwmlxx32n5elt83kwfg7` FOREIGN KEY (`tipdoceapb_fk_idtipdoc`) REFERENCES `tipdoc` (`idtipdoc`),
  CONSTRAINT `FKmy4sf5hsjteo1re3kyte69oyf` FOREIGN KEY (`tipent_idtipeapb`) REFERENCES `tipeapb` (`idtipeapb`),
  CONSTRAINT `FKo9obv6aiertd2quabuaoua25i` FOREIGN KEY (`esteapb_fk_idstatus`) REFERENCES `status` (`idstatus`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `eapb`
--

LOCK TABLES `eapb` WRITE;
/*!40000 ALTER TABLE `eapb` DISABLE KEYS */;
INSERT INTO `eapb` VALUES (1,'3108610210','2024-04-11','EL PAPAYO',123456789,'SANITAS@SANITAS.COM','MARIA DE LOS ANGELES','EPS SANITAS',1,2,1),(2,'3108610210','2024-04-11','CARRERA 5 CALLE 42',789456123,'FAMISANAR@FAMISANAR.COM','MARIA DE L OS ANGELES','FAMISANAR',1,2,2),(3,'3108610210','2024-04-11','CENTRO',159753456,'PEPITOPEREZ@HOTMAIL.COM','PEPITO PEREZ','PEPITO PEREZ',1,1,4);
/*!40000 ALTER TABLE `eapb` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `eqqx`
--

DROP TABLE IF EXISTS `eqqx`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `eqqx` (
  `ideqqx` bigint NOT NULL AUTO_INCREMENT,
  `datecreateqqx` date DEFAULT NULL,
  `dateediteqqx` date DEFAULT NULL,
  `inteqqx_iduser` bigint DEFAULT NULL,
  `pxqx_fk_idqx` bigint DEFAULT NULL,
  PRIMARY KEY (`ideqqx`),
  KEY `FKmu28uiu76fg0a43rbxqa9vmj` (`inteqqx_iduser`),
  KEY `FK5665j1ax5c84nurou63ch8j8p` (`pxqx_fk_idqx`),
  CONSTRAINT `FK5665j1ax5c84nurou63ch8j8p` FOREIGN KEY (`pxqx_fk_idqx`) REFERENCES `desqx` (`idqx`),
  CONSTRAINT `FKmu28uiu76fg0a43rbxqa9vmj` FOREIGN KEY (`inteqqx_iduser`) REFERENCES `user` (`iduser`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `eqqx`
--

LOCK TABLES `eqqx` WRITE;
/*!40000 ALTER TABLE `eqqx` DISABLE KEYS */;
/*!40000 ALTER TABLE `eqqx` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estpac`
--

DROP TABLE IF EXISTS `estpac`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `estpac` (
  `idestpac` bigint NOT NULL AUTO_INCREMENT,
  `fechafinest` datetime(6) DEFAULT NULL,
  `fechainiest` datetime(6) DEFAULT NULL,
  `eventestpac_idevent` bigint DEFAULT NULL,
  `statusestanciapac_fk_idstatus` bigint DEFAULT NULL,
  `ubicapac_fk_idubica` bigint DEFAULT NULL,
  PRIMARY KEY (`idestpac`),
  KEY `FKed29bucie14w9gnl8sw9fyj1g` (`eventestpac_idevent`),
  KEY `FK4t7adejv69c2g7gs0f7pmlxqn` (`statusestanciapac_fk_idstatus`),
  KEY `FKkhva8uc4ubnqmnma93sbix0pl` (`ubicapac_fk_idubica`),
  CONSTRAINT `FK4t7adejv69c2g7gs0f7pmlxqn` FOREIGN KEY (`statusestanciapac_fk_idstatus`) REFERENCES `status` (`idstatus`),
  CONSTRAINT `FKed29bucie14w9gnl8sw9fyj1g` FOREIGN KEY (`eventestpac_idevent`) REFERENCES `eventhcpac` (`idevent`),
  CONSTRAINT `FKkhva8uc4ubnqmnma93sbix0pl` FOREIGN KEY (`ubicapac_fk_idubica`) REFERENCES `ubicaciones` (`idubica`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estpac`
--

LOCK TABLES `estpac` WRITE;
/*!40000 ALTER TABLE `estpac` DISABLE KEYS */;
/*!40000 ALTER TABLE `estpac` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `eventhcpac`
--

DROP TABLE IF EXISTS `eventhcpac`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `eventhcpac` (
  `idevent` bigint NOT NULL AUTO_INCREMENT,
  `conseventpac` bigint NOT NULL,
  `detevent` varchar(255) NOT NULL,
  `fechafinevent` date DEFAULT NULL,
  `fechainievent` date DEFAULT NULL,
  `estevent_fk_idstatus` bigint DEFAULT NULL,
  `pacevent_fk_idpac` bigint DEFAULT NULL,
  PRIMARY KEY (`idevent`),
  KEY `FKhtkoqgy2qdsakck0h7oe7lxux` (`estevent_fk_idstatus`),
  KEY `FKblv5k3w5ip4ixgu2j0c8ts4mf` (`pacevent_fk_idpac`),
  CONSTRAINT `FKblv5k3w5ip4ixgu2j0c8ts4mf` FOREIGN KEY (`pacevent_fk_idpac`) REFERENCES `pac` (`idpac`),
  CONSTRAINT `FKhtkoqgy2qdsakck0h7oe7lxux` FOREIGN KEY (`estevent_fk_idstatus`) REFERENCES `status` (`idstatus`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `eventhcpac`
--

LOCK TABLES `eventhcpac` WRITE;
/*!40000 ALTER TABLE `eventhcpac` DISABLE KEYS */;
INSERT INTO `eventhcpac` VALUES (1,1,'CONSULTA',NULL,'2024-05-01',1,2),(2,1,'CONSULTA',NULL,'2024-05-04',1,3),(3,2,'PROCEDIMIENTO',NULL,'2024-05-05',1,2),(4,1,'CONSULTA',NULL,'2024-05-05',1,1);
/*!40000 ALTER TABLE `eventhcpac` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `evoevent`
--

DROP TABLE IF EXISTS `evoevent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `evoevent` (
  `idevol` bigint NOT NULL AUTO_INCREMENT,
  `datecreatevol` datetime(6) DEFAULT NULL,
  `dateditevol` datetime(6) DEFAULT NULL,
  `detevol` varchar(10000) DEFAULT NULL,
  `estnotaevol_fk_idstatus` bigint DEFAULT NULL,
  `eventevo_fk_idevent` bigint DEFAULT NULL,
  `notaevol_fk_idtypnot` bigint DEFAULT NULL,
  PRIMARY KEY (`idevol`),
  KEY `FKeoj8af8h78nhpwvi7wx1d9g98` (`estnotaevol_fk_idstatus`),
  KEY `FK1p74muirs9hvegtqd5diuysqe` (`eventevo_fk_idevent`),
  KEY `FKscbgr8lsvmpuatxog66d4f0pa` (`notaevol_fk_idtypnot`),
  CONSTRAINT `FK1p74muirs9hvegtqd5diuysqe` FOREIGN KEY (`eventevo_fk_idevent`) REFERENCES `eventhcpac` (`idevent`),
  CONSTRAINT `FKeoj8af8h78nhpwvi7wx1d9g98` FOREIGN KEY (`estnotaevol_fk_idstatus`) REFERENCES `status` (`idstatus`),
  CONSTRAINT `FKscbgr8lsvmpuatxog66d4f0pa` FOREIGN KEY (`notaevol_fk_idtypnot`) REFERENCES `tynote` (`idtypnot`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `evoevent`
--

LOCK TABLES `evoevent` WRITE;
/*!40000 ALTER TABLE `evoevent` DISABLE KEYS */;
/*!40000 ALTER TABLE `evoevent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `habcami`
--

DROP TABLE IF EXISTS `habcami`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `habcami` (
  `idhab` bigint NOT NULL AUTO_INCREMENT,
  `dethab` varchar(100) DEFAULT NULL,
  `fechacreahab` date DEFAULT NULL,
  `nomhab` varchar(10) DEFAULT NULL,
  `esthab_fk_idstatus` bigint DEFAULT NULL,
  `ubicahab_idubica` bigint DEFAULT NULL,
  PRIMARY KEY (`idhab`),
  UNIQUE KEY `UK_ohj29cthd0hmya2nj5jmlr580` (`nomhab`),
  KEY `FK96p599t8em6li18kje0en2ywm` (`esthab_fk_idstatus`),
  KEY `FKe3b1b1k09nc1f6cfu94990vcp` (`ubicahab_idubica`),
  CONSTRAINT `FK96p599t8em6li18kje0en2ywm` FOREIGN KEY (`esthab_fk_idstatus`) REFERENCES `status` (`idstatus`),
  CONSTRAINT `FKe3b1b1k09nc1f6cfu94990vcp` FOREIGN KEY (`ubicahab_idubica`) REFERENCES `ubicaciones` (`idubica`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `habcami`
--

LOCK TABLES `habcami` WRITE;
/*!40000 ALTER TABLE `habcami` DISABLE KEYS */;
INSERT INTO `habcami` VALUES (1,'CAMILLA 1 RECUPERACION QUIROFANO','2024-04-15','REC1',1,1),(2,'CAMILLA 2 RECUPERACION QUIROFANO','2024-04-15','REC2',1,1),(3,'SALA DE QUIROFANO 1','2024-04-15','SQX1',1,2),(4,'SALA DE QUIROFANO 2','2024-04-15','SQX2',1,2),(5,'CAMA 1 HOSPITALIZACION PISO 1','2024-04-15','HOS1CAM1',1,3),(6,'CAMA 2 HOSPITALIZACION PISO 1','2024-04-15','HOS1CAM2',1,3);
/*!40000 ALTER TABLE `habcami` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hcpac`
--

DROP TABLE IF EXISTS `hcpac`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hcpac` (
  `idhcpac` bigint NOT NULL AUTO_INCREMENT,
  `analisishcpac` varchar(2000) NOT NULL,
  `antalerhcpac` varchar(200) NOT NULL,
  `antfamyhcpac` varchar(200) NOT NULL,
  `antfarmhcpac` varchar(200) NOT NULL,
  `antpathcpac` varchar(200) NOT NULL,
  `antqxhcpac` varchar(200) NOT NULL,
  `anttxhcpac` varchar(200) NOT NULL,
  `datecreathcpac` date DEFAULT NULL,
  `datedithcpac` date DEFAULT NULL,
  `enfacthcpac` varchar(200) NOT NULL,
  `estaturahcpac` bigint NOT NULL,
  `fchcpac` varchar(3) NOT NULL,
  `frhcpac` varchar(3) NOT NULL,
  `motconshcpac` varchar(200) NOT NULL,
  `objhcpac` varchar(2000) NOT NULL,
  `pesohcpac` bigint NOT NULL,
  `planmanejhcpac` varchar(5000) NOT NULL,
  `tahcpac` varchar(7) NOT NULL,
  `temphcpac` varchar(3) NOT NULL,
  `esthcpac_fk_idstatus` bigint DEFAULT NULL,
  `eventpac_fk_idevent` bigint DEFAULT NULL,
  `medico_iduser` bigint DEFAULT NULL,
  PRIMARY KEY (`idhcpac`),
  KEY `FK3k3vlihv06uvf2fr2h7cnlf6x` (`esthcpac_fk_idstatus`),
  KEY `FKss8unshnkte9a9vl9eiioj2jv` (`eventpac_fk_idevent`),
  KEY `FK9i37v5w0xc6whu9gmsp7uxd0u` (`medico_iduser`),
  CONSTRAINT `FK3k3vlihv06uvf2fr2h7cnlf6x` FOREIGN KEY (`esthcpac_fk_idstatus`) REFERENCES `status` (`idstatus`),
  CONSTRAINT `FK9i37v5w0xc6whu9gmsp7uxd0u` FOREIGN KEY (`medico_iduser`) REFERENCES `user` (`iduser`),
  CONSTRAINT `FKss8unshnkte9a9vl9eiioj2jv` FOREIGN KEY (`eventpac_fk_idevent`) REFERENCES `eventhcpac` (`idevent`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hcpac`
--

LOCK TABLES `hcpac` WRITE;
/*!40000 ALTER TABLE `hcpac` DISABLE KEYS */;
INSERT INTO `hcpac` VALUES (2,'cirugia','cirugia','cirugia','cirugia','cirugia','cirugia','cirugia','2024-05-01',NULL,'cirugia',150,'60','80','cirugia','cirugia',70,'cirugia','110/80','37',1,1,NULL),(3,'cirugia','na','na','na','naNA','na','na','2024-05-01',NULL,'cirugia',150,'80','80','cirugia','cirugia',50,'cirugia','110/80','37',1,1,NULL);
/*!40000 ALTER TABLE `hcpac` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ordprocexam`
--

DROP TABLE IF EXISTS `ordprocexam`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ordprocexam` (
  `idordprocexam` bigint NOT NULL AUTO_INCREMENT,
  `cantprocexam` bigint NOT NULL,
  `fechaordprocexam` date NOT NULL,
  `obsprocexam` varchar(500) DEFAULT NULL,
  `procexamord_idpxex` bigint DEFAULT NULL,
  `user_iduser` bigint DEFAULT NULL,
  PRIMARY KEY (`idordprocexam`),
  KEY `FKhein8gdf86dfquqwqpmo3kxsj` (`procexamord_idpxex`),
  KEY `FK6rh5ibgtn18b4dhmgvbo2c3f5` (`user_iduser`),
  CONSTRAINT `FK6rh5ibgtn18b4dhmgvbo2c3f5` FOREIGN KEY (`user_iduser`) REFERENCES `user` (`iduser`),
  CONSTRAINT `FKhein8gdf86dfquqwqpmo3kxsj` FOREIGN KEY (`procexamord_idpxex`) REFERENCES `procexam` (`idpxex`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ordprocexam`
--

LOCK TABLES `ordprocexam` WRITE;
/*!40000 ALTER TABLE `ordprocexam` DISABLE KEYS */;
/*!40000 ALTER TABLE `ordprocexam` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pac`
--

DROP TABLE IF EXISTS `pac`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pac` (
  `idpac` bigint NOT NULL AUTO_INCREMENT,
  `acudientepac` varchar(30) NOT NULL,
  `contactoacudientepac` varchar(15) NOT NULL,
  `contactopac` varchar(15) NOT NULL,
  `contactrespac` varchar(255) DEFAULT NULL,
  `direccionpac` varchar(40) NOT NULL,
  `emailpac` varchar(40) NOT NULL,
  `estadocivilpac` varchar(255) NOT NULL,
  `fechacreacionpac` date DEFAULT NULL,
  `fechaedipac` date DEFAULT NULL,
  `fechanacpac` date DEFAULT NULL,
  `numdocpac` varchar(255) NOT NULL,
  `primerapepac` varchar(15) NOT NULL,
  `primernompac` varchar(15) NOT NULL,
  `resppac` varchar(255) DEFAULT NULL,
  `segundoapepac` varchar(255) DEFAULT NULL,
  `segundonompac` varchar(255) DEFAULT NULL,
  `sexopac` varchar(10) NOT NULL,
  `ciudad_idciu` bigint DEFAULT NULL,
  `entidad_ideapb` bigint DEFAULT NULL,
  `estpac_fk_idstatus` bigint DEFAULT NULL,
  `tipac_idtipac` bigint DEFAULT NULL,
  `typdocpac_idtipdoc` bigint DEFAULT NULL,
  PRIMARY KEY (`idpac`),
  UNIQUE KEY `UK_9in249oqplqd2hpl7k87ilo3p` (`numdocpac`),
  KEY `FKsjdovaujyg5omc4b65gj3whew` (`ciudad_idciu`),
  KEY `FKrh2ndjnl04as62uj4etidtjqe` (`entidad_ideapb`),
  KEY `FK2gtkpsjjokvf5fpihv1fyqi0f` (`estpac_fk_idstatus`),
  KEY `FKbfak8787niw284uyk9spdpcrl` (`tipac_idtipac`),
  KEY `FKoktvehnlkcsjeedkcdxw9un7a` (`typdocpac_idtipdoc`),
  CONSTRAINT `FK2gtkpsjjokvf5fpihv1fyqi0f` FOREIGN KEY (`estpac_fk_idstatus`) REFERENCES `status` (`idstatus`),
  CONSTRAINT `FKbfak8787niw284uyk9spdpcrl` FOREIGN KEY (`tipac_idtipac`) REFERENCES `tipac` (`idtipac`),
  CONSTRAINT `FKoktvehnlkcsjeedkcdxw9un7a` FOREIGN KEY (`typdocpac_idtipdoc`) REFERENCES `tipdoc` (`idtipdoc`),
  CONSTRAINT `FKrh2ndjnl04as62uj4etidtjqe` FOREIGN KEY (`entidad_ideapb`) REFERENCES `eapb` (`ideapb`),
  CONSTRAINT `FKsjdovaujyg5omc4b65gj3whew` FOREIGN KEY (`ciudad_idciu`) REFERENCES `ciudades` (`idciu`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pac`
--

LOCK TABLES `pac` WRITE;
/*!40000 ALTER TABLE `pac` DISABLE KEYS */;
INSERT INTO `pac` VALUES (1,'SANDRA','3133692213','3108610210','3133692213','MZ D CASA 10 BRISAS DEL PEDREGAL','sistemas@grupoayc.com.co','CASADO','2024-04-11',NULL,'1979-11-03','93414046','SALCEDO','JULIO','SANDRA','PELAEZ','ERNESTO','MASCULINO',1,1,1,1,1),(2,'JULIO','3108610210','3133692213','3108610210','MZ D CASA 10 BRISAS DEL PEDREGAL','SSANDRAPATRIDIAZ@HOTMAIL.COM','CASADO','2024-04-11',NULL,'1970-03-30','65748200','DIAZ','SANDRA','JULIO','GUTIERREZ','PATRICIA','FEMENINO',1,2,1,2,1),(3,'JULIO','3108610210','3133692213','3108610210','MZ D CASA 10 BRISAS DEL PEDREGAL','SINDATOS@SINDATOS.COM','MENOR DE EDAD','2024-04-11',NULL,'2016-11-03','1105476026','SALCEDO','ADRIANA','JULIO','DIAZ','LUCIA','FEMENINO',1,3,1,3,4);
/*!40000 ALTER TABLE `pac` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permission`
--

DROP TABLE IF EXISTS `permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `permission` (
  `idperm` bigint NOT NULL AUTO_INCREMENT,
  `datecreatperm` date DEFAULT NULL,
  `detperm` varchar(100) DEFAULT NULL,
  `namperm` varchar(35) DEFAULT NULL,
  `estperm_fk_idstatus` bigint DEFAULT NULL,
  PRIMARY KEY (`idperm`),
  UNIQUE KEY `UK_lo8tb892lsfc0fi8oncphxb0d` (`namperm`),
  KEY `FKoig4anrkx61n8s7k54tt2ybg9` (`estperm_fk_idstatus`),
  CONSTRAINT `FKoig4anrkx61n8s7k54tt2ybg9` FOREIGN KEY (`estperm_fk_idstatus`) REFERENCES `status` (`idstatus`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permission`
--

LOCK TABLES `permission` WRITE;
/*!40000 ALTER TABLE `permission` DISABLE KEYS */;
/*!40000 ALTER TABLE `permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permrol`
--

DROP TABLE IF EXISTS `permrol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `permrol` (
  `idpermrol` bigint NOT NULL AUTO_INCREMENT,
  `datecreatidpermrol` date DEFAULT NULL,
  `estpermrol_fk_idstatus` bigint DEFAULT NULL,
  `permrol_fk_idperm` bigint DEFAULT NULL,
  `rolpermrol_fk_idrol` bigint DEFAULT NULL,
  PRIMARY KEY (`idpermrol`),
  KEY `FKrmmr1d7h75ojjgw5ihkk58vo1` (`estpermrol_fk_idstatus`),
  KEY `FKfbhehlc6m406db4kjrc9hp5x` (`permrol_fk_idperm`),
  KEY `FKlbre5cey19uddjincfw36gp7d` (`rolpermrol_fk_idrol`),
  CONSTRAINT `FKfbhehlc6m406db4kjrc9hp5x` FOREIGN KEY (`permrol_fk_idperm`) REFERENCES `permission` (`idperm`),
  CONSTRAINT `FKlbre5cey19uddjincfw36gp7d` FOREIGN KEY (`rolpermrol_fk_idrol`) REFERENCES `rol` (`idrol`),
  CONSTRAINT `FKrmmr1d7h75ojjgw5ihkk58vo1` FOREIGN KEY (`estpermrol_fk_idstatus`) REFERENCES `status` (`idstatus`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permrol`
--

LOCK TABLES `permrol` WRITE;
/*!40000 ALTER TABLE `permrol` DISABLE KEYS */;
/*!40000 ALTER TABLE `permrol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prestserv`
--

DROP TABLE IF EXISTS `prestserv`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prestserv` (
  `idprestserv` bigint NOT NULL AUTO_INCREMENT,
  `dirprestserv` varchar(50) NOT NULL,
  `docprestserv` bigint NOT NULL,
  `emailprestserv` varchar(30) NOT NULL,
  `fechacreaprestserv` date DEFAULT NULL,
  `nomprestserv` varchar(45) DEFAULT NULL,
  `telprestserv` varchar(10) NOT NULL,
  `ciuprestserv_fk_idciu` bigint DEFAULT NULL,
  `estadoprestserv_fk_idstatus` bigint DEFAULT NULL,
  `tipdocprestserv_fk_idtipdoc` bigint DEFAULT NULL,
  PRIMARY KEY (`idprestserv`),
  UNIQUE KEY `UK_e33d0jq0l1ras6s6m4r6j4oou` (`docprestserv`),
  UNIQUE KEY `UK_ecv4r6xpv9xm2xc6mrt5qnsj1` (`nomprestserv`),
  KEY `FKax8rqnj6gbk5cr6ir9eq4xerp` (`ciuprestserv_fk_idciu`),
  KEY `FKt2ilhf9cdpuikn3vmeim18xx0` (`estadoprestserv_fk_idstatus`),
  KEY `FKgdlbq8q6qrk2v3vrcj7n2rw00` (`tipdocprestserv_fk_idtipdoc`),
  CONSTRAINT `FKax8rqnj6gbk5cr6ir9eq4xerp` FOREIGN KEY (`ciuprestserv_fk_idciu`) REFERENCES `ciudades` (`idciu`),
  CONSTRAINT `FKgdlbq8q6qrk2v3vrcj7n2rw00` FOREIGN KEY (`tipdocprestserv_fk_idtipdoc`) REFERENCES `tipdoc` (`idtipdoc`),
  CONSTRAINT `FKt2ilhf9cdpuikn3vmeim18xx0` FOREIGN KEY (`estadoprestserv_fk_idstatus`) REFERENCES `status` (`idstatus`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prestserv`
--

LOCK TABLES `prestserv` WRITE;
/*!40000 ALTER TABLE `prestserv` DISABLE KEYS */;
/*!40000 ALTER TABLE `prestserv` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `procexam`
--

DROP TABLE IF EXISTS `procexam`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `procexam` (
  `idpxex` bigint NOT NULL AUTO_INCREMENT,
  `codpxex` varchar(6) NOT NULL,
  `datecreatpxex` date DEFAULT NULL,
  `dateeditpxex` date DEFAULT NULL,
  `nompxex` varchar(255) NOT NULL,
  `sexopxex` varchar(255) NOT NULL,
  `estadopxex_fk_idstatus` bigint DEFAULT NULL,
  `tpxex_idtproc` bigint DEFAULT NULL,
  PRIMARY KEY (`idpxex`),
  UNIQUE KEY `UK_3ysj6ep1o873ypeyjq88qj1it` (`codpxex`),
  KEY `FKt0q63pecj7lv8337675qkewy2` (`estadopxex_fk_idstatus`),
  KEY `FKc3o86unt0clm70gc6po3bwrk1` (`tpxex_idtproc`),
  CONSTRAINT `FKc3o86unt0clm70gc6po3bwrk1` FOREIGN KEY (`tpxex_idtproc`) REFERENCES `tproc` (`idtproc`),
  CONSTRAINT `FKt0q63pecj7lv8337675qkewy2` FOREIGN KEY (`estadopxex_fk_idstatus`) REFERENCES `status` (`idstatus`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `procexam`
--

LOCK TABLES `procexam` WRITE;
/*!40000 ALTER TABLE `procexam` DISABLE KEYS */;
/*!40000 ALTER TABLE `procexam` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `procqx`
--

DROP TABLE IF EXISTS `procqx`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `procqx` (
  `idprocqx` bigint NOT NULL AUTO_INCREMENT,
  `descqx_fk_idqx` bigint DEFAULT NULL,
  `procqx_fk_idpxex` bigint DEFAULT NULL,
  PRIMARY KEY (`idprocqx`),
  KEY `FK3e60xi61yty5cm52f5j8cbwvx` (`descqx_fk_idqx`),
  KEY `FK3ktfsljs1uyyac1kl5c693d6f` (`procqx_fk_idpxex`),
  CONSTRAINT `FK3e60xi61yty5cm52f5j8cbwvx` FOREIGN KEY (`descqx_fk_idqx`) REFERENCES `desqx` (`idqx`),
  CONSTRAINT `FK3ktfsljs1uyyac1kl5c693d6f` FOREIGN KEY (`procqx_fk_idpxex`) REFERENCES `procexam` (`idpxex`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `procqx`
--

LOCK TABLES `procqx` WRITE;
/*!40000 ALTER TABLE `procqx` DISABLE KEYS */;
/*!40000 ALTER TABLE `procqx` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `regest`
--

DROP TABLE IF EXISTS `regest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `regest` (
  `idregest` bigint NOT NULL AUTO_INCREMENT,
  `fechafin` date DEFAULT NULL,
  `fechaini` date DEFAULT NULL,
  `estregest_fk_idstatus` bigint DEFAULT NULL,
  `eventregest_fk_idevent` bigint DEFAULT NULL,
  `regcama_fk_idhab` bigint DEFAULT NULL,
  PRIMARY KEY (`idregest`),
  KEY `FK5iesk210j1rkbnw2wpxfhlr4d` (`estregest_fk_idstatus`),
  KEY `FK9mhkh9dwucu4iv0pyeo3lmr8o` (`eventregest_fk_idevent`),
  KEY `FKr5oyqsbjaq9t83f076rc3vejx` (`regcama_fk_idhab`),
  CONSTRAINT `FK5iesk210j1rkbnw2wpxfhlr4d` FOREIGN KEY (`estregest_fk_idstatus`) REFERENCES `status` (`idstatus`),
  CONSTRAINT `FK9mhkh9dwucu4iv0pyeo3lmr8o` FOREIGN KEY (`eventregest_fk_idevent`) REFERENCES `eventhcpac` (`idevent`),
  CONSTRAINT `FKr5oyqsbjaq9t83f076rc3vejx` FOREIGN KEY (`regcama_fk_idhab`) REFERENCES `habcami` (`idhab`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `regest`
--

LOCK TABLES `regest` WRITE;
/*!40000 ALTER TABLE `regest` DISABLE KEYS */;
/*!40000 ALTER TABLE `regest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `repleg`
--

DROP TABLE IF EXISTS `repleg`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `repleg` (
  `idrepleg` bigint NOT NULL AUTO_INCREMENT,
  `datecreaterepleg` date DEFAULT NULL,
  `docrepleg` bigint NOT NULL,
  `emailrepleg` varchar(40) NOT NULL,
  `paperepleg` varchar(20) NOT NULL,
  `pnomrepleg` varchar(20) NOT NULL,
  `saperepleg` varchar(20) NOT NULL,
  `snomrepleg` varchar(20) NOT NULL,
  `idprestservrepleg_fk_idprestserv` bigint DEFAULT NULL,
  `statusrepleg_fk_idstatus` bigint DEFAULT NULL,
  `tipdocrepleg_fk_idtipdoc` bigint DEFAULT NULL,
  PRIMARY KEY (`idrepleg`),
  UNIQUE KEY `UK_84v2wcc5inttrn2jjqeo6mix4` (`docrepleg`),
  KEY `FK7y7cfx2gcsfhcihkh0uoos6qq` (`idprestservrepleg_fk_idprestserv`),
  KEY `FKrcx8qcu94j6jacehttg2xuc9v` (`statusrepleg_fk_idstatus`),
  KEY `FKth5fo47mqn5q2mq0gesa9xl9v` (`tipdocrepleg_fk_idtipdoc`),
  CONSTRAINT `FK7y7cfx2gcsfhcihkh0uoos6qq` FOREIGN KEY (`idprestservrepleg_fk_idprestserv`) REFERENCES `prestserv` (`idprestserv`),
  CONSTRAINT `FKrcx8qcu94j6jacehttg2xuc9v` FOREIGN KEY (`statusrepleg_fk_idstatus`) REFERENCES `status` (`idstatus`),
  CONSTRAINT `FKth5fo47mqn5q2mq0gesa9xl9v` FOREIGN KEY (`tipdocrepleg_fk_idtipdoc`) REFERENCES `tipdoc` (`idtipdoc`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `repleg`
--

LOCK TABLES `repleg` WRITE;
/*!40000 ALTER TABLE `repleg` DISABLE KEYS */;
/*!40000 ALTER TABLE `repleg` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rol`
--

DROP TABLE IF EXISTS `rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rol` (
  `idrol` bigint NOT NULL AUTO_INCREMENT,
  `creatrol` date DEFAULT NULL,
  `desrol` varchar(50) DEFAULT NULL,
  `nomrol` varchar(20) DEFAULT NULL,
  `estrol_fk_idstatus` bigint DEFAULT NULL,
  PRIMARY KEY (`idrol`),
  UNIQUE KEY `UK_8f5cj7w4mh0ew4kb1omotbfpk` (`desrol`),
  UNIQUE KEY `UK_fcypk446sbxoty8vnvqujjw4g` (`nomrol`),
  KEY `FKglajh5ycf66pouquf0k0uuyad` (`estrol_fk_idstatus`),
  CONSTRAINT `FKglajh5ycf66pouquf0k0uuyad` FOREIGN KEY (`estrol_fk_idstatus`) REFERENCES `status` (`idstatus`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rol`
--

LOCK TABLES `rol` WRITE;
/*!40000 ALTER TABLE `rol` DISABLE KEYS */;
/*!40000 ALTER TABLE `rol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `status`
--

DROP TABLE IF EXISTS `status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `status` (
  `idstatus` bigint NOT NULL AUTO_INCREMENT,
  `datecreatstatus` date DEFAULT NULL,
  `detstatus` varchar(20) NOT NULL,
  `nomstatus` varchar(4) NOT NULL,
  PRIMARY KEY (`idstatus`),
  UNIQUE KEY `UK_iuh3i634h092l0xlybxoq3xki` (`detstatus`),
  UNIQUE KEY `UK_qu824ukxhb5xl8e1och0jg610` (`nomstatus`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status`
--

LOCK TABLES `status` WRITE;
/*!40000 ALTER TABLE `status` DISABLE KEYS */;
INSERT INTO `status` VALUES (1,'2024-04-11','CREADO','CRE'),(2,'2024-04-11','ACTUALIZADO','ACT'),(3,'2024-04-11','INACTIVO','INA');
/*!40000 ALTER TABLE `status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `template`
--

DROP TABLE IF EXISTS `template`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `template` (
  `idtemp` bigint NOT NULL AUTO_INCREMENT,
  `creatdatetemp` date DEFAULT NULL,
  `dettemp` varchar(5000) DEFAULT NULL,
  `edittemp` date DEFAULT NULL,
  `nametemp` varchar(6) DEFAULT NULL,
  `esttemp_fk_idstatus` bigint DEFAULT NULL,
  `typtemp_fk_idtytemp` bigint DEFAULT NULL,
  PRIMARY KEY (`idtemp`),
  UNIQUE KEY `UK_2nx94vpwunxu329jaefajkcg3` (`nametemp`),
  KEY `FKsleg66x13nsgrfrglgn5cbv9a` (`esttemp_fk_idstatus`),
  KEY `FK9cu1l4d7vtrq6b1030phho05m` (`typtemp_fk_idtytemp`),
  CONSTRAINT `FK9cu1l4d7vtrq6b1030phho05m` FOREIGN KEY (`typtemp_fk_idtytemp`) REFERENCES `tytemp` (`idtytemp`),
  CONSTRAINT `FKsleg66x13nsgrfrglgn5cbv9a` FOREIGN KEY (`esttemp_fk_idstatus`) REFERENCES `status` (`idstatus`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `template`
--

LOCK TABLES `template` WRITE;
/*!40000 ALTER TABLE `template` DISABLE KEYS */;
/*!40000 ALTER TABLE `template` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipac`
--

DROP TABLE IF EXISTS `tipac`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipac` (
  `idtipac` bigint NOT NULL AUTO_INCREMENT,
  `datecreatipac` date DEFAULT NULL,
  `dettipac` varchar(15) NOT NULL,
  `nomtipac` varchar(6) NOT NULL,
  `esttippac_idstatus` bigint DEFAULT NULL,
  PRIMARY KEY (`idtipac`),
  UNIQUE KEY `UK_539kmwgddn606y7ye94giajcv` (`nomtipac`),
  KEY `FK5eovfvssic0iqgelv3yltqpcj` (`esttippac_idstatus`),
  CONSTRAINT `FK5eovfvssic0iqgelv3yltqpcj` FOREIGN KEY (`esttippac_idstatus`) REFERENCES `status` (`idstatus`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipac`
--

LOCK TABLES `tipac` WRITE;
/*!40000 ALTER TABLE `tipac` DISABLE KEYS */;
INSERT INTO `tipac` VALUES (1,'2024-04-11','COTIZANTE','C',1),(2,'2024-04-11','BENEFICIARIO','B',1),(3,'2024-04-11','NO AFILIADO','NA',1);
/*!40000 ALTER TABLE `tipac` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipanest`
--

DROP TABLE IF EXISTS `tipanest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipanest` (
  `idtipanest` bigint NOT NULL AUTO_INCREMENT,
  `datecreatanest` time DEFAULT NULL,
  `dateeditanest` time DEFAULT NULL,
  `desctipanest` varchar(30) DEFAULT NULL,
  `nomtipanest` varchar(6) DEFAULT NULL,
  `esttypanest_idstatus` bigint DEFAULT NULL,
  PRIMARY KEY (`idtipanest`),
  UNIQUE KEY `UK_5amu8tcsayp2e68yl2efrvo0j` (`desctipanest`),
  UNIQUE KEY `UK_n0tnekvhat3pnuvk1luc7n6sa` (`nomtipanest`),
  KEY `FK52qmbscobh22jj15h1yel24uh` (`esttypanest_idstatus`),
  CONSTRAINT `FK52qmbscobh22jj15h1yel24uh` FOREIGN KEY (`esttypanest_idstatus`) REFERENCES `status` (`idstatus`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipanest`
--

LOCK TABLES `tipanest` WRITE;
/*!40000 ALTER TABLE `tipanest` DISABLE KEYS */;
/*!40000 ALTER TABLE `tipanest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipdoc`
--

DROP TABLE IF EXISTS `tipdoc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipdoc` (
  `idtipdoc` bigint NOT NULL AUTO_INCREMENT,
  `datecreatetipdoc` date NOT NULL,
  `detatipdoc` varchar(45) NOT NULL,
  `tipdoc` varchar(5) NOT NULL,
  PRIMARY KEY (`idtipdoc`),
  UNIQUE KEY `UK_t4sky2q7dd9l5d0lfuutu35b` (`tipdoc`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipdoc`
--

LOCK TABLES `tipdoc` WRITE;
/*!40000 ALTER TABLE `tipdoc` DISABLE KEYS */;
INSERT INTO `tipdoc` VALUES (1,'2024-04-11','CEDULA DE CIUDADANIA','CC'),(2,'2024-04-11','NUMERO DE IDENTIFICACION TRIBUTARIA','NIT'),(3,'2024-04-11','REGISTRO CIVIL','RC'),(4,'2024-04-11','TARJETA DE IDENTIDAD','TI'),(5,'2024-04-11','PERMISO DE PERMANENCIA TEMPORAL','ppt'),(6,'2024-04-11','CEDULA DE EXTRANJERIA','CE'),(7,'2024-04-11','PASAPORTE','PS'),(8,'2024-04-11','PERMISO ESPECIAL DE PERMANENCIA','PEP');
/*!40000 ALTER TABLE `tipdoc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipeapb`
--

DROP TABLE IF EXISTS `tipeapb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipeapb` (
  `idtipeapb` bigint NOT NULL AUTO_INCREMENT,
  `datecreatypeapb` date DEFAULT NULL,
  `detipeapb` varchar(255) NOT NULL,
  `nomtipeapb` varchar(255) NOT NULL,
  `estyeapb_fk_idstatus` bigint DEFAULT NULL,
  PRIMARY KEY (`idtipeapb`),
  UNIQUE KEY `UK_gimtxd281kd056b09fhjtye8q` (`detipeapb`),
  UNIQUE KEY `UK_p4hqlpe3e3wkmdgt1emd8jmrg` (`nomtipeapb`),
  KEY `FKfh26jsd31qr0iqsjpihbuiy3t` (`estyeapb_fk_idstatus`),
  CONSTRAINT `FKfh26jsd31qr0iqsjpihbuiy3t` FOREIGN KEY (`estyeapb_fk_idstatus`) REFERENCES `status` (`idstatus`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipeapb`
--

LOCK TABLES `tipeapb` WRITE;
/*!40000 ALTER TABLE `tipeapb` DISABLE KEYS */;
INSERT INTO `tipeapb` VALUES (1,'2024-04-11','CONTRIBUTIVO','CONT',1),(2,'2024-04-11','SUBSIDIADA','SUB',1),(3,'2024-04-11','REGIMEN ESPECIAL','RE',1),(4,'2024-04-11','PARTICULAR','PART',1);
/*!40000 ALTER TABLE `tipeapb` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tproc`
--

DROP TABLE IF EXISTS `tproc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tproc` (
  `idtproc` bigint NOT NULL AUTO_INCREMENT,
  `datecreatypx` date DEFAULT NULL,
  `dateeditypx` date DEFAULT NULL,
  `detproc` varchar(255) NOT NULL,
  `nomtproc` varchar(6) NOT NULL,
  `estypx_fk_idstatus` bigint DEFAULT NULL,
  PRIMARY KEY (`idtproc`),
  UNIQUE KEY `UK_g9d910kiev7h137uj0j5yu54u` (`detproc`),
  UNIQUE KEY `UK_rkhshswvjvnm6s86rqf26jhis` (`nomtproc`),
  KEY `FKomsv4qbspsqijcb1wikqvljsm` (`estypx_fk_idstatus`),
  CONSTRAINT `FKomsv4qbspsqijcb1wikqvljsm` FOREIGN KEY (`estypx_fk_idstatus`) REFERENCES `status` (`idstatus`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tproc`
--

LOCK TABLES `tproc` WRITE;
/*!40000 ALTER TABLE `tproc` DISABLE KEYS */;
/*!40000 ALTER TABLE `tproc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tynote`
--

DROP TABLE IF EXISTS `tynote`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tynote` (
  `idtypnot` bigint NOT NULL AUTO_INCREMENT,
  `datecreatypnot` date DEFAULT NULL,
  `dettypnot` varchar(255) NOT NULL,
  `nametypnot` varchar(6) NOT NULL,
  `estypnot_fk_idstatus` bigint DEFAULT NULL,
  PRIMARY KEY (`idtypnot`),
  UNIQUE KEY `UK_q339n672doyhxtgo9wvy3y9a4` (`nametypnot`),
  KEY `FKhkjsdhxc3c1eo8snctqfswp32` (`estypnot_fk_idstatus`),
  CONSTRAINT `FKhkjsdhxc3c1eo8snctqfswp32` FOREIGN KEY (`estypnot_fk_idstatus`) REFERENCES `status` (`idstatus`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tynote`
--

LOCK TABLES `tynote` WRITE;
/*!40000 ALTER TABLE `tynote` DISABLE KEYS */;
INSERT INTO `tynote` VALUES (1,'2024-05-09','HISTORIA CLINICA','HC',1),(2,'2024-05-09','NOTA ACLARATORIA','NA',1),(3,'2024-05-09','DESCRIPCION QUIRURGICA','DQ',1),(4,'2024-05-09','EVOLUCION MEDICA','EM',1),(5,'2024-05-09','NOTA ENFERMERIA','NE',1);
/*!40000 ALTER TABLE `tynote` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `typdx`
--

DROP TABLE IF EXISTS `typdx`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `typdx` (
  `idtypdx` bigint NOT NULL AUTO_INCREMENT,
  `datecreatypdx` date DEFAULT NULL,
  `detypdx` varchar(255) DEFAULT NULL,
  `namtypdx` varchar(6) DEFAULT NULL,
  `estyodx_fx_idstatus` bigint DEFAULT NULL,
  PRIMARY KEY (`idtypdx`),
  UNIQUE KEY `UK_ouy91byh7kvj7dcqwvvk92d80` (`namtypdx`),
  KEY `FK5jshbtx6fgyu51dxjurj0b8pq` (`estyodx_fx_idstatus`),
  CONSTRAINT `FK5jshbtx6fgyu51dxjurj0b8pq` FOREIGN KEY (`estyodx_fx_idstatus`) REFERENCES `status` (`idstatus`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `typdx`
--

LOCK TABLES `typdx` WRITE;
/*!40000 ALTER TABLE `typdx` DISABLE KEYS */;
INSERT INTO `typdx` VALUES (1,NULL,'PRINCIPAL','PR',1),(2,NULL,'SECUNDARIO','SEC',1),(3,NULL,'DIAGNOSTICO PREQUIRURGICO','PREQX',1),(4,NULL,'DIAGNOSTICO CONFIRMADO','DXCON',1);
/*!40000 ALTER TABLE `typdx` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tytemp`
--

DROP TABLE IF EXISTS `tytemp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tytemp` (
  `idtytemp` bigint NOT NULL AUTO_INCREMENT,
  `creadatetytemp` date DEFAULT NULL,
  `detytemp` varchar(255) NOT NULL,
  `nomtytemp` varchar(10) NOT NULL,
  `esttytemp_fk_idstatus` bigint DEFAULT NULL,
  PRIMARY KEY (`idtytemp`),
  UNIQUE KEY `UK_aku1hc91kkg1xjh08hdqfu9vf` (`detytemp`),
  UNIQUE KEY `UK_1a7fje2o9m9a74w2vl15ni3wk` (`nomtytemp`),
  KEY `FKot3huybv7e0iutn8dq6euduwa` (`esttytemp_fk_idstatus`),
  CONSTRAINT `FKot3huybv7e0iutn8dq6euduwa` FOREIGN KEY (`esttytemp_fk_idstatus`) REFERENCES `status` (`idstatus`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tytemp`
--

LOCK TABLES `tytemp` WRITE;
/*!40000 ALTER TABLE `tytemp` DISABLE KEYS */;
/*!40000 ALTER TABLE `tytemp` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ubicaciones`
--

DROP TABLE IF EXISTS `ubicaciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ubicaciones` (
  `idubica` bigint NOT NULL AUTO_INCREMENT,
  `datecreatubic` date DEFAULT NULL,
  `detubica` varchar(255) DEFAULT NULL,
  `nomubicaciones` varchar(6) NOT NULL,
  `estubica_fk_idstatus` bigint DEFAULT NULL,
  PRIMARY KEY (`idubica`),
  UNIQUE KEY `UK_hu2k98yfug5yeyvtmwnp3jy2s` (`nomubicaciones`),
  KEY `FK4j1q84tmdrueqcbhon271lk2b` (`estubica_fk_idstatus`),
  CONSTRAINT `FK4j1q84tmdrueqcbhon271lk2b` FOREIGN KEY (`estubica_fk_idstatus`) REFERENCES `status` (`idstatus`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ubicaciones`
--

LOCK TABLES `ubicaciones` WRITE;
/*!40000 ALTER TABLE `ubicaciones` DISABLE KEYS */;
INSERT INTO `ubicaciones` VALUES (1,'2024-04-15','RECUPERACION CIRUGIA','REC',1),(2,'2024-04-15','SALA QUIROFANO','SQX',1),(3,'2024-04-15','HOSPITALIZACION PISO 1','HOS1',1);
/*!40000 ALTER TABLE `ubicaciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `iduser` bigint NOT NULL AUTO_INCREMENT,
  `creatdateuser` date DEFAULT NULL,
  `docuser` bigint NOT NULL,
  `emailuser` varchar(45) DEFAULT NULL,
  `firma` varchar(255) DEFAULT NULL,
  `firstname` varchar(255) NOT NULL,
  `lastname` varchar(30) DEFAULT NULL,
  `password` varchar(60) NOT NULL,
  `username` varchar(20) DEFAULT NULL,
  `carguser_fk_idcarguser` bigint DEFAULT NULL,
  `estuser_fk_idstatus` bigint DEFAULT NULL,
  `typeiduser_fk_idtipdoc` bigint DEFAULT NULL,
  PRIMARY KEY (`iduser`),
  UNIQUE KEY `UK_fkkfbbdme1t9a70k0f9kye4xq` (`docuser`),
  UNIQUE KEY `UK_sb8bbouer5wak8vyiiy4pf2bx` (`username`),
  KEY `FKo8ibry4e6touoqapoml339g4r` (`carguser_fk_idcarguser`),
  KEY `FKq0tddp08h8dhjg9p1tijurudc` (`estuser_fk_idstatus`),
  KEY `FKg1y1g0rlx4r9bf4dr4bp753bw` (`typeiduser_fk_idtipdoc`),
  CONSTRAINT `FKg1y1g0rlx4r9bf4dr4bp753bw` FOREIGN KEY (`typeiduser_fk_idtipdoc`) REFERENCES `tipdoc` (`idtipdoc`),
  CONSTRAINT `FKo8ibry4e6touoqapoml339g4r` FOREIGN KEY (`carguser_fk_idcarguser`) REFERENCES `cargo_user` (`idcarguser`),
  CONSTRAINT `FKq0tddp08h8dhjg9p1tijurudc` FOREIGN KEY (`estuser_fk_idstatus`) REFERENCES `status` (`idstatus`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'plataformasalud'
--

--
-- Dumping routines for database 'plataformasalud'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-09 23:14:14
