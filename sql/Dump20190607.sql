-- MySQL dump 10.13  Distrib 8.0.16, for Win64 (x86_64)
--
-- Host: localhost    Database: bulky
-- ------------------------------------------------------
-- Server version	5.7.26-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `account` (
  `aid` int(11) NOT NULL AUTO_INCREMENT,
  `acredit` int(11) DEFAULT NULL,
  `aname` varchar(255) DEFAULT NULL,
  `alogo` varchar(255) DEFAULT NULL,
  `astatus` int(11) DEFAULT NULL,
  `adtmod` datetime DEFAULT NULL,
  PRIMARY KEY (`aid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `act_booking`
--

DROP TABLE IF EXISTS `act_booking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `act_booking` (
  `bid` int(11) NOT NULL AUTO_INCREMENT,
  `baccount` int(11) DEFAULT NULL,
  `bfkactivity` int(11) DEFAULT NULL,
  `bfkcatg` int(11) DEFAULT NULL,
  `bqty` int(11) DEFAULT NULL,
  `bdate` datetime DEFAULT NULL,
  `bfkaddress` int(11) DEFAULT NULL,
  `bdtmod` datetime DEFAULT NULL,
  `bstatus` int(11) DEFAULT NULL,
  `bdescr` varchar(255) DEFAULT NULL,
  `bnote` varchar(500) DEFAULT NULL,
  `bfkcustomer` int(11) DEFAULT NULL,
  `bfkplandetail` int(11) DEFAULT NULL,
  PRIMARY KEY (`bid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `act_booking`
--

LOCK TABLES `act_booking` WRITE;
/*!40000 ALTER TABLE `act_booking` DISABLE KEYS */;
/*!40000 ALTER TABLE `act_booking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `activity`
--

DROP TABLE IF EXISTS `activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `activity` (
  `aid` int(11) NOT NULL AUTO_INCREMENT,
  `aaccount` int(11) DEFAULT NULL,
  `afktype` int(11) DEFAULT NULL,
  `aowner` int(11) DEFAULT NULL,
  `aassign` int(11) DEFAULT NULL,
  `adtmod` datetime DEFAULT NULL,
  `astatus` int(11) DEFAULT NULL,
  `adescr` varchar(255) DEFAULT NULL,
  `atitle` varchar(255) DEFAULT NULL,
  `asubject` varchar(255) DEFAULT NULL,
  `afkcustomer` int(11) DEFAULT NULL,
  `afklead` int(11) DEFAULT NULL,
  `alead_from_status` int(11) DEFAULT NULL,
  `alead_to_status` int(11) DEFAULT NULL,
  PRIMARY KEY (`aid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity`
--

LOCK TABLES `activity` WRITE;
/*!40000 ALTER TABLE `activity` DISABLE KEYS */;
/*!40000 ALTER TABLE `activity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `address` (
  `adid` int(11) NOT NULL AUTO_INCREMENT,
  `adfkaccount` int(11) NOT NULL,
  `adfkcustomer` int(11) NOT NULL,
  `adcomune` varchar(250) DEFAULT NULL,
  `adprov` varchar(250) DEFAULT NULL,
  `adsiglaprov` varchar(20) DEFAULT NULL,
  `adaddress` varchar(500) DEFAULT NULL,
  `adnum` varchar(50) DEFAULT NULL,
  `adcap` varchar(20) DEFAULT NULL,
  `adfkzona` int(11) DEFAULT NULL,
  `addtins` datetime DEFAULT NULL,
  `addtmod` datetime DEFAULT NULL,
  `adusermod` int(11) DEFAULT NULL,
  `adnormalizzato` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`adid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `address_zone`
--

DROP TABLE IF EXISTS `address_zone`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `address_zone` (
  `azid` int(11) NOT NULL AUTO_INCREMENT,
  `azfkaccount` int(11) NOT NULL,
  `azfkzona` int(11) NOT NULL,
  `azcomune` varchar(250) DEFAULT NULL,
  `azprov` varchar(250) DEFAULT NULL,
  `azsiglaprov` varchar(20) DEFAULT NULL,
  `azcap` varchar(20) DEFAULT NULL,
  `azaddress` varchar(500) DEFAULT NULL,
  `azdtmod` datetime DEFAULT NULL,
  `azdtins` datetime DEFAULT NULL,
  `azusermod` int(11) DEFAULT NULL,
  PRIMARY KEY (`azid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address_zone`
--

LOCK TABLES `address_zone` WRITE;
/*!40000 ALTER TABLE `address_zone` DISABLE KEYS */;
/*!40000 ALTER TABLE `address_zone` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `catg_action`
--

DROP TABLE IF EXISTS `catg_action`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `catg_action` (
  `caid` int(11) NOT NULL AUTO_INCREMENT,
  `cafkaccount` int(11) NOT NULL,
  `cafktlead` int(11) DEFAULT NULL,
  `castatus` int(11) DEFAULT NULL,
  `cadescr` varchar(250) DEFAULT NULL,
  `catitle` varchar(250) DEFAULT NULL,
  `canote` varchar(500) DEFAULT NULL,
  `caenabled` bit(1) DEFAULT NULL,
  `cadtins` datetime DEFAULT NULL,
  `cadtmod` datetime DEFAULT NULL,
  `causermod` int(11) DEFAULT NULL,
  `cacusenable` bit(1) DEFAULT NULL,
  `cadoctype` varchar(100) DEFAULT NULL,
  `ca_lead_from_status` int(11) DEFAULT NULL,
  `ca_lead_to_status` int(11) DEFAULT NULL,
  `caviewlevel` int(11) DEFAULT NULL,
  PRIMARY KEY (`caid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `catg_action`
--

LOCK TABLES `catg_action` WRITE;
/*!40000 ALTER TABLE `catg_action` DISABLE KEYS */;
INSERT INTO `catg_action` VALUES (1,1,1,1,'','Ingombranti/Sfalci-Potature','',_binary '',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(2,1,1,1,'','Smaltimento Rifiuti Speciali','',_binary '',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(3,1,1,1,'','Richiesta Informazione','',_binary '',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `catg_action` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `catg_rifiuti`
--

DROP TABLE IF EXISTS `catg_rifiuti`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `catg_rifiuti` (
  `crid` int(11) NOT NULL AUTO_INCREMENT,
  `craccount` int(11) DEFAULT NULL,
  `crname` varchar(250) DEFAULT NULL,
  `crtype` varchar(50) DEFAULT NULL,
  `crqtymin` int(11) DEFAULT NULL,
  `crqtymax` int(11) DEFAULT NULL,
  `crudm` varchar(50) DEFAULT NULL,
  `crincombro` int(11) DEFAULT NULL,
  `crnote` varchar(500) DEFAULT NULL,
  `crdtmod` datetime DEFAULT NULL,
  `crusermod` int(11) DEFAULT NULL,
  PRIMARY KEY (`crid`)
) ENGINE=InnoDB AUTO_INCREMENT=280 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `catg_rifiuti`
--

LOCK TABLES `catg_rifiuti` WRITE;
/*!40000 ALTER TABLE `catg_rifiuti` DISABLE KEYS */;
INSERT INTO `catg_rifiuti` VALUES (1,NULL,'Acquari in plastica ','INGOMBRANTI',1,2,'pezzi',1,'',NULL,NULL),(2,NULL,'Acquari in vetro ','INGOMBRANTI',1,2,'pezzi',1,'',NULL,NULL),(3,NULL,'Albero di Natale finto ','INGOMBRANTI',1,2,'pezzi',1,'',NULL,NULL),(4,NULL,'Alimentatore e/o Stabilizzatore','R.A.E.E',1,4,'pezzi',1,'',NULL,NULL),(5,NULL,'Altalena (giocattolo)','GIOCATTOLO',1,4,'pezzi',1,'smontare',NULL,NULL),(6,NULL,'Altalena in metallo','INGOMBRANTI',1,4,'pezzi',1,'smontare',NULL,NULL),(7,NULL,'Amplificatore ','R.A.E.E',1,4,'pezzi',1,'',NULL,NULL),(8,NULL,'Angoliera','MOBILI',1,4,'pezzi',1,'',NULL,NULL),(9,NULL,'Ante armadio in legno','MOBILI',1,3,'pezzi',1,'',NULL,NULL),(10,NULL,'Ante armadio in metallo','MOBILI',1,3,'pezzi',1,'',NULL,NULL),(11,NULL,'Ante armadio in vimini','MOBILI',1,3,'pezzi',1,'',NULL,NULL),(12,NULL,'Anta Box doccia','INGOMBRANTI',1,4,'pezzi',1,'',NULL,NULL),(13,NULL,'Antenna ','R.A.E.E',1,4,'pezzi',1,'',NULL,NULL),(14,NULL,'Antenna parabolica','R.A.E.E',1,2,'pezzi',1,'',NULL,NULL),(15,NULL,'Appendiabiti ','INGOMBRANTI',1,2,'pezzi',1,'',NULL,NULL),(16,NULL,'Armadi in Legno/Plastica','MOBILI',1,2,'pezzi',1,'smontare',NULL,NULL),(17,NULL,'Armadio 1 anta metallico','MOBILI',1,2,'pezzi',1,'smontare',NULL,NULL),(18,NULL,'Armadio 2 ante metallico','MOBILI',1,2,'pezzi',2,'smontare',NULL,NULL),(19,NULL,'Armadio 1 anta legno ','MOBILI',1,2,'pezzi',1,'smontare',NULL,NULL),(20,NULL,'Armadio 2 ante legno ','MOBILI',1,1,'pezzi',2,'smontare',NULL,NULL),(21,NULL,'Armadio 3 ante legno ','MOBILI',1,1,'pezzi',3,'smontare',NULL,NULL),(22,NULL,'Armadio 4 ante legno ','MOBILI',1,1,'pezzi',4,'smontare',NULL,NULL),(23,NULL,'Armadio 5 ante e superiore legno ','MOBILI',1,1,'pezzi',5,'smontare',NULL,NULL),(24,NULL,'Armadio a  ponte legno ','MOBILI',1,1,'pezzi',3,'smontare',NULL,NULL),(25,NULL,'Asciugacapelli ','R.A.E.E',1,4,'pezzi',1,'',NULL,NULL),(26,NULL,'Asciugatrice','R.A.E.E',1,2,'pezzi',1,'',NULL,NULL),(27,NULL,'Aspirapolvere ','R.A.E.E',1,2,'pezzi',1,'',NULL,NULL),(28,NULL,'Asse da stiro ','INGOMBRANTI',1,2,'pezzi',1,'',NULL,NULL),(29,NULL,'Aste di ferro da 50 cm. ','INGOMBRANTI',1,10,'pezzi',1,'',NULL,NULL),(30,NULL,'Attrezzi da giardino','INGOMBRANTI',1,6,'pezzi',1,'',NULL,NULL),(31,NULL,'Attrezzi da ginnastica grandi','INGOMBRANTI',1,1,'pezzi',1,'',NULL,NULL),(32,NULL,'Attrezzi da ginnastica piccoli','INGOMBRANTI',1,4,'pezzi',1,'',NULL,NULL),(33,NULL,'Auricolari e Cuffie ','R.A.E.E',1,6,'pezzi',1,'',NULL,NULL),(34,NULL,'Avvolgibili','INGOMBRANTI',1,1,'pezzi',1,'legare ',NULL,NULL),(35,NULL,'Bacheca','INGOMBRANTI',1,2,'pezzi',1,'',NULL,NULL),(36,NULL,'Bacinella ','INGOMBRANTI',1,4,'pezzi',1,'',NULL,NULL),(37,NULL,'Bambole e giocattoli ','GIOCATTOLO',1,1,'busta/sacchetto',1,'',NULL,NULL),(38,NULL,'Bambole e giocattoli con componenti elettrici','R.A.E.E',1,1,'busta/sacchetto',1,'',NULL,NULL),(39,NULL,'Bancali/Pedane ','LEGNO',1,3,'pezzi',1,'',NULL,NULL),(40,NULL,'Banco scuola','INGOMBRANTI',1,2,'pezzi',1,'',NULL,NULL),(41,NULL,'Barbecue in metallo','METALLI',1,2,'pezzi',1,'',NULL,NULL),(42,NULL,'Base ombrellone in plastica','INGOMBRANTI',1,3,'pezzi',1,'',NULL,NULL),(43,NULL,'Base tavolo','INGOMBRANTI',1,2,'pezzi',1,'',NULL,NULL),(44,NULL,'Bastone scopa','INGOMBRANTI',1,10,'pezzi',1,'',NULL,NULL),(45,NULL,'Bastoni tenda in ferro e/o legno','INGOMBRANTI',1,5,'pezzi',1,'',NULL,NULL),(46,NULL,'Battiscopa in legno ','LEGNO',1,10,'pezzi',1,'in +  pezzi ',NULL,NULL),(47,NULL,'Baule','INGOMBRANTI',1,1,'pezzi',1,'smontare',NULL,NULL),(48,NULL,'Biciclette adulto','INGOMBRANTI',1,1,'pezzi',1,'',NULL,NULL),(49,NULL,'Biciclette bimbi ','INGOMBRANTI',1,3,'pezzi',1,'',NULL,NULL),(50,NULL,' Cyclette','INGOMBRANTI',1,1,'pezzi',1,'',NULL,NULL),(51,NULL,'Bidoni e cestini porta rifiuti','INGOMBRANTI',1,5,'pezzi',1,'',NULL,NULL),(52,NULL,'Bilancia per alimenti','R.A.E.E',1,5,'pezzi',1,'',NULL,NULL),(53,NULL,'Bilancia pesapersone elettrica ','R.A.E.E',1,4,'pezzi',1,'',NULL,NULL),(54,NULL,'Biliardino','GIOCATTOLO',1,1,'pezzi',1,'smontare',NULL,NULL),(55,NULL,'Binarietti per tende ','INGOMBRANTI',1,10,'pezzi',1,'smontare',NULL,NULL),(56,NULL,'Bistecchiera elettrica','R.A.E.E',1,3,'pezzi',1,'',NULL,NULL),(57,NULL,'Bob in plastica','INGOMBRANTI',1,1,'pezzi',1,'',NULL,NULL),(58,NULL,'Box bimbi','INGOMBRANTI',1,1,'pezzi',1,'smontare',NULL,NULL),(59,NULL,'Box doccia','INGOMBRANTI',1,1,'pezzi',1,'smontare',NULL,NULL),(60,NULL,'Brandina','METALLI',1,1,'pezzi',1,'',NULL,NULL),(61,NULL,'Buffet','LEGNO',1,1,'pezzi',1,'smontare',NULL,NULL),(62,NULL,'Calcolatrice','R.A.E.E',1,10,'pezzi',1,'',NULL,NULL),(63,NULL,'Caldaia','R.A.E.E',1,1,'pezzi',1,'',NULL,NULL),(64,NULL,'Canaline passa cavi','INGOMBRANTI',1,10,'pezzi',1,'in +  pezzi ',NULL,NULL),(65,NULL,'Cancelletto protezione','INGOMBRANTI',1,1,'pezzi',1,'',NULL,NULL),(66,NULL,'Canne da irrigazione','PLASTICA',1,5,'pezzi',1,'',NULL,NULL),(67,NULL,'Canoa','INGOMBRANTI',1,1,'pezzi',1,'',NULL,NULL),(68,NULL,'Cappa aspiratrice','R.A.E.E',1,1,'pezzi',1,'',NULL,NULL),(69,NULL,'Cappottine ripara sole','INGOMBRANTI',1,1,'pezzi',1,'smontare',NULL,NULL),(70,NULL,'Carrello porta vivande','INGOMBRANTI',1,2,'pezzi',1,'smontare',NULL,NULL),(71,NULL,'Carriola','INGOMBRANTI',1,1,'pezzi',1,'',NULL,NULL),(72,NULL,'Carrozzella','INGOMBRANTI',1,2,'pezzi',1,'smontare',NULL,NULL),(73,NULL,'Carrozzina','INGOMBRANTI',1,1,'pezzi',1,'smontare',NULL,NULL),(74,NULL,'Casco per moto e bici','INGOMBRANTI',1,3,'pezzi',1,'',NULL,NULL),(75,NULL,'Cassaforte piccola (da muro)','INGOMBRANTI',1,3,'pezzi',1,'',NULL,NULL),(76,NULL,'Cassapanca','MOBILI',1,5,'pezzi',1,'smontare',NULL,NULL),(77,NULL,'Casse audio','R.A.E.E',1,4,'pezzi',1,'',NULL,NULL),(78,NULL,'Cassetti','MOBILI',1,4,'pezzi',1,'',NULL,NULL),(79,NULL,'Cassettiera','MOBILI',1,1,'pezzi',1,'smontare',NULL,NULL),(80,NULL,'Cassettone letto','MOBILI',1,1,'pezzi',1,'smontare',NULL,NULL),(81,NULL,'Catalogatore','MOBILI',1,1,'pezzi',1,'smontare',NULL,NULL),(82,NULL,'Cavalletto','INGOMBRANTI',1,5,'pezzi',1,'',NULL,NULL),(83,NULL,'Cavi elettrici','R.A.E.E',1,10,'pezzi',1,'',NULL,NULL),(84,NULL,'Celetto (cassettone di finestra e/o balcone)','INGOMBRANTI',1,2,'pezzi',1,'smontare',NULL,NULL),(85,NULL,'Cellulare (Apparecchi telefonia mobile) ','R.A.E.E',1,10,'pezzi',1,'',NULL,NULL),(86,NULL,'Centralina telefonica','R.A.E.E',1,3,'pezzi',1,'smontare',NULL,NULL),(87,NULL,'Cesta in vimini','LEGNO',1,6,'pezzi',1,'',NULL,NULL),(88,NULL,'Como\'','MOBILI',1,1,'pezzi',1,'smontare',NULL,NULL),(89,NULL,'Comodino','MOBILI',1,2,'pezzi',1,'',NULL,NULL),(90,NULL,'Computer (monitor + tastiera + case + tower)','R.A.E.E',1,1,'pezzi',1,'',NULL,NULL),(91,NULL,'Condizionatore ','R.A.E.E',1,1,'pezzi',1,'',NULL,NULL),(92,NULL,'Congelatore ','R.A.E.E',1,1,'pezzi',1,'',NULL,NULL),(93,NULL,'Consolle videogiochi ','R.A.E.E',1,4,'pezzi',1,'',NULL,NULL),(94,NULL,'Copri caldaia','INGOMBRANTI',1,2,'pezzi',1,'smontare',NULL,NULL),(95,NULL,'Copri termosifone','MOBILI',1,2,'pezzi',1,'',NULL,NULL),(96,NULL,'Cornici','INGOMBRANTI',1,10,'pezzi',1,'',NULL,NULL),(97,NULL,'Credenza','MOBILI',1,1,'pezzi',3,'smontare',NULL,NULL),(98,NULL,'Cristalliera','MOBILI',1,1,'pezzi',3,'smontare',NULL,NULL),(99,NULL,'Cubo legno','',1,1,'pezzi',1,'',NULL,NULL),(100,NULL,'Cuccia per animali','INGOMBRANTI',1,1,'pezzi',1,'smontare',NULL,NULL),(101,NULL,'Culla','MOBILI',1,1,'pezzi',1,'smontare',NULL,NULL),(102,NULL,'Deambulatore','INGOMBRANTI',1,1,'pezzi',1,'',NULL,NULL),(103,NULL,'Decespugliatore','INGOMBRANTI',1,1,'pezzi',1,'',NULL,NULL),(104,NULL,'Decoder ','R.A.E.E',1,5,'pezzi',1,'',NULL,NULL),(105,NULL,'Divano (2 posti)','MOBILI',1,1,'pezzi',2,'',NULL,NULL),(106,NULL,'Divano (3 posti)','MOBILI',1,1,'pezzi',3,'',NULL,NULL),(107,NULL,'Divano (4 posti)','MOBILI',1,1,'pezzi',4,'',NULL,NULL),(108,NULL,'Divano (con isola)','MOBILI',1,1,'pezzi',6,'',NULL,NULL),(109,NULL,'Doghe (letto singolo)','INGOMBRANTI',1,1,'pezzi',1,'',NULL,NULL),(110,NULL,'Doghe (letto matrimoniale)','INGOMBRANTI',1,1,'pezzi',2,'',NULL,NULL),(111,NULL,'Dondolo','INGOMBRANTI',1,1,'pezzi',2,'smontare',NULL,NULL),(112,NULL,'Dondolo (bimbi o gioco)','INGOMBRANTI',1,1,'pezzi',1,'smontare',NULL,NULL),(113,NULL,'Espositori ','INGOMBRANTI',1,4,'pezzi',1,'',NULL,NULL),(114,NULL,'Fasciatoio','INGOMBRANTI',1,1,'pezzi',1,'',NULL,NULL),(115,NULL,'Fax (apparecchio)','R.A.E.E',1,1,'pezzi',1,'',NULL,NULL),(116,NULL,'Ferro da stiro (con o senza caldaia)','R.A.E.E',1,2,'pezzi',1,'',NULL,NULL),(117,NULL,'Finestra con telaio','INGOMBRANTI',1,1,'pezzi',1,'',NULL,NULL),(118,NULL,'Fioriere','INGOMBRANTI',1,4,'pezzi',1,'',NULL,NULL),(119,NULL,'Forni e fornelli elettrici ','R.A.E.E',1,1,'pezzi',1,'',NULL,NULL),(120,NULL,'Forno microonde ','R.A.E.E',1,2,'pezzi',1,'',NULL,NULL),(121,NULL,'Fotocopiatrici uso domestico','R.A.E.E',1,1,'pezzi',1,'',NULL,NULL),(122,NULL,'Friggitrice ','R.A.E.E',1,2,'pezzi',1,'',NULL,NULL),(123,NULL,'Frigorifero ','R.A.E.E',1,1,'pezzi',1,'',NULL,NULL),(124,NULL,'Frullatore ','R.A.E.E',1,3,'pezzi',1,'',NULL,NULL),(125,NULL,'Fusti','INGOMBRANTI',1,1,'pezzi',1,'',NULL,NULL),(126,NULL,'Gabbietta per animali ','INGOMBRANTI',1,2,'pezzi',1,'',NULL,NULL),(127,NULL,'Gazebo ','INGOMBRANTI',1,1,'pezzi',1,'smontare',NULL,NULL),(128,NULL,'Giocattoli  (sacco 110x40 trasparente) ','INGOMBRANTI',1,1,'pezzi',1,'',NULL,NULL),(129,NULL,'Girello per bambini','INGOMBRANTI',1,1,'pezzi',1,'smontare',NULL,NULL),(130,NULL,'Gomma per l\'orto','INGOMBRANTI',1,2,'pezzi',1,'',NULL,NULL),(131,NULL,'Grata (per porte e/o finestre)','INGOMBRANTI',1,2,'pezzi',1,'',NULL,NULL),(132,NULL,'Grattugia elettrica ','R.A.E.E',1,2,'pezzi',1,'',NULL,NULL),(133,NULL,'Gruppo di continuità','R.A.E.E',1,1,'pezzi',1,'',NULL,NULL),(134,NULL,'Hard disk','R.A.E.E',1,3,'pezzi',1,'',NULL,NULL),(135,NULL,'Impianto HI-FI','R.A.E.E',1,1,'pezzi',1,'',NULL,NULL),(136,NULL,'Incannuciata','INGOMBRANTI',1,1,'pezzi',1,'',NULL,NULL),(137,NULL,'Infissi legno/metallo','INGOMBRANTI',1,1,'pezzi',1,'',NULL,NULL),(138,NULL,'Lampade','R.A.E.E',1,4,'pezzi',1,'',NULL,NULL),(139,NULL,'Lampadari ','R.A.E.E',1,2,'pezzi',1,'',NULL,NULL),(140,NULL,'Lastre  di vetro ','VETRO',1,1,'pezzi',1,'',NULL,NULL),(141,NULL,'Lavagna','INGOMBRANTI',1,1,'pezzi',1,'',NULL,NULL),(142,NULL,'Lavastoviglie ','R.A.E.E',1,1,'pezzi',1,'',NULL,NULL),(143,NULL,'Lavatrice ','R.A.E.E',1,1,'pezzi',1,'',NULL,NULL),(144,NULL,'Lavello cucina','MOBILI',1,1,'pezzi',1,'',NULL,NULL),(145,NULL,'Lavabo da esterno (legno/plastica)','MOBILI',1,1,'pezzi',1,'',NULL,NULL),(146,NULL,'Letto ','MOBILI',1,1,'pezzi',1,'',NULL,NULL),(147,NULL,'Letto a castello','MOBILI',1,1,'pezzi',2,'smontare',NULL,NULL),(148,NULL,'Lettore dvd/cd  e/o videoregistratori','R.A.E.E',1,2,'pezzi',1,'smontare',NULL,NULL),(149,NULL,'Libreria','MOBILI',1,1,'pezzi',3,'smontare',NULL,NULL),(150,NULL,'Lucidatrice','R.A.E.E',1,2,'pezzi',1,'',NULL,NULL),(151,NULL,'Macchina da cucire','INGOMBRANTI',1,1,'pezzi',1,'',NULL,NULL),(152,NULL,'Macchina da cucire con mobile','INGOMBRANTI',1,1,'pezzi',2,'',NULL,NULL),(153,NULL,'Macchina da scrivere ','INGOMBRANTI',1,2,'pezzi',1,'',NULL,NULL),(154,NULL,'Macchina del caffè','R.A.E.E',1,2,'pezzi',1,'',NULL,NULL),(155,NULL,'Macchina del Gas','R.A.E.E',1,1,'pezzi',1,'',NULL,NULL),(156,NULL,'Macchina per maglieria','R.A.E.E',1,1,'pezzi',1,'',NULL,NULL),(157,NULL,'Mantovana','MOBILI',1,3,'pezzi',1,'',NULL,NULL),(158,NULL,'Materassino mare','INGOMBRANTI',1,4,'pezzi',1,'',NULL,NULL),(159,NULL,'Materasso (letto singolo)','INGOMBRANTI',1,1,'pezzi',1,'',NULL,NULL),(160,NULL,'Materasso (letto matrimoniale)','INGOMBRANTI',1,1,'pezzi',2,'',NULL,NULL),(161,NULL,'Materiale elettrico (busta grande 100x70) ','R.A.E.E',1,1,'pezzi',1,'',NULL,NULL),(162,NULL,'Materiale elettrico (busta piccola 70x40) ','R.A.E.E',1,2,'pezzi',1,'',NULL,NULL),(163,NULL,'Mensole','MOBILI',1,6,'pezzi',1,'',NULL,NULL),(164,NULL,'Misuratori di pressione digitali RAEE','R.A.E.E',1,4,'pezzi',1,'',NULL,NULL),(165,NULL,'Mobile bagno','MOBILI',1,1,'pezzi',1,'',NULL,NULL),(166,NULL,'Mobile letto','MOBILI',1,1,'pezzi',1,'',NULL,NULL),(167,NULL,'Mobile o Colonna frigo','MOBILI',1,1,'pezzi',1,'',NULL,NULL),(168,NULL,'Mobile per HI-FI','MOBILI',1,1,'pezzi',1,'',NULL,NULL),(169,NULL,'Mobile porta TV','MOBILI',1,1,'pezzi',1,'',NULL,NULL),(170,NULL,'Mobile sala','MOBILI',1,1,'pezzi',3,'smontare',NULL,NULL),(171,NULL,'Monitor ','R.A.E.E',1,2,'pezzi',1,'',NULL,NULL),(172,NULL,'Morsa','METALLI',1,1,'pezzi',1,'',NULL,NULL),(173,NULL,'Mostre in legno','INGOMBRANTI',1,10,'pezzi',1,'',NULL,NULL),(174,NULL,'Mouse ','R.A.E.E',1,10,'pezzi',1,'',NULL,NULL),(175,NULL,'Navigatore ','R.A.E.E',1,6,'pezzi',1,'',NULL,NULL),(176,NULL,'Notebook ','R.A.E.E',1,2,'pezzi',1,'',NULL,NULL),(177,NULL,'Ombrelli','INGOMBRANTI',1,10,'pezzi',1,'',NULL,NULL),(178,NULL,'Ombrellone','INGOMBRANTI',1,1,'pezzi',1,'',NULL,NULL),(179,NULL,'Orologio a muro','R.A.E.E',1,3,'pezzi',1,'',NULL,NULL),(180,NULL,'Pallet','INGOMBRANTI',1,1,'pezzi',1,'',NULL,NULL),(181,NULL,'Panca','MOBILI',1,1,'pezzi',1,'',NULL,NULL),(182,NULL,'Panca fitness','INGOMBRANTI',1,1,'pezzi',1,'',NULL,NULL),(183,NULL,'Pannelli in truciolato ','INGOMBRANTI',1,3,'pezzi',1,'',NULL,NULL),(184,NULL,'Paralume','INGOMBRANTI',1,3,'pezzi',1,'',NULL,NULL),(185,NULL,'Passeggino','INGOMBRANTI',1,1,'pezzi',1,'',NULL,NULL),(186,NULL,'Peluche (busta grande 100x70) ','GIOCATTOLO',1,1,'pezzi',1,'',NULL,NULL),(187,NULL,'Pensili','MOBILI',1,1,'pezzi',1,'',NULL,NULL),(188,NULL,'Persiane','INGOMBRANTI',1,2,'pezzi',1,'',NULL,NULL),(189,NULL,'Piano cottura','R.A.E.E',1,1,'pezzi',1,'',NULL,NULL),(190,NULL,'Piante finte','INGOMBRANTI',1,4,'pezzi',1,'',NULL,NULL),(191,NULL,'Piccoli elettrodomestici (phon, frullatori, ecc. ...)','R.A.E.E',1,4,'pezzi',1,'',NULL,NULL),(192,NULL,'Poltrone ','MOBILI',1,1,'pezzi',1,'',NULL,NULL),(193,NULL,'Pompa idraulica','INGOMBRANTI',1,1,'pezzi',1,'',NULL,NULL),(194,NULL,'Porta ombrelli','INGOMBRANTI',1,4,'pezzi',1,'',NULL,NULL),(195,NULL,'Porta vasi','INGOMBRANTI',1,4,'pezzi',1,'',NULL,NULL),(196,NULL,'Portabagagli cesto','INGOMBRANTI',1,4,'pezzi',1,'',NULL,NULL),(197,NULL,'Portabagagli Box','INGOMBRANTI',1,1,'pezzi',1,'',NULL,NULL),(198,NULL,'Porte con telaio','INGOMBRANTI',1,1,'pezzi',1,'',NULL,NULL),(199,NULL,'Porte a soffietto ','INGOMBRANTI',1,1,'pezzi',1,'',NULL,NULL),(200,NULL,'Porta blindata','INGOMBRANTI',1,1,'pezzi',3,'',NULL,NULL),(201,NULL,'Portoncino','INGOMBRANTI',1,1,'pezzi',3,'',NULL,NULL),(202,NULL,'Puff','MOBILI',1,2,'pezzi',1,'',NULL,NULL),(203,NULL,'Quadri/cornici ','INGOMBRANTI',1,6,'pezzi',1,'',NULL,NULL),(204,NULL,'Quadro elettrico ','R.A.E.E',1,1,'pezzi',1,'',NULL,NULL),(205,NULL,'Racchette da tennis','INGOMBRANTI',1,4,'pezzi',1,'',NULL,NULL),(206,NULL,'Radio o radiolina','R.A.E.E',1,6,'pezzi',1,'',NULL,NULL),(207,NULL,'Radio registratore','R.A.E.E',1,6,'pezzi',1,'',NULL,NULL),(208,NULL,'Ram, schede di rete, schede video','R.A.E.E',1,20,'pezzi',1,'',NULL,NULL),(209,NULL,'Registratore di cassa','R.A.E.E',1,2,'pezzi',1,'',NULL,NULL),(210,NULL,'Remi (barca/canoa)','INGOMBRANTI',1,4,'pezzi',1,'',NULL,NULL),(211,NULL,'Rete (letto) 1 piazza','MOBILI',1,1,'pezzi',1,'',NULL,NULL),(212,NULL,'Rete (letto) 2  piazze','MOBILI',1,1,'pezzi',2,'',NULL,NULL),(213,NULL,'Rete (recinzione)','INGOMBRANTI',1,2,'pezzi',1,'rotoli',NULL,NULL),(214,NULL,'Rilegatrice ','INGOMBRANTI',1,3,'pezzi',1,'',NULL,NULL),(215,NULL,'Robot (da cucina)','R.A.E.E',1,2,'pezzi',1,'',NULL,NULL),(216,NULL,'Rubinetteria (busta piccola 70x40) ','INGOMBRANTI',1,2,'pezzi',1,'',NULL,NULL),(217,NULL,'Ruote biciclette','INGOMBRANTI',1,4,'pezzi',1,'',NULL,NULL),(218,NULL,'Scaffalatura','INGOMBRANTI',1,1,'pezzi',1,'smontare',NULL,NULL),(219,NULL,'Scala pieghevole','INGOMBRANTI',1,1,'pezzi',1,'',NULL,NULL),(220,NULL,'Scaldabagno','R.A.E.E',1,1,'pezzi',1,'',NULL,NULL),(221,NULL,'Scarpiera','INGOMBRANTI',1,1,'pezzi',1,'smontare',NULL,NULL),(222,NULL,'Scarponi da sci','INGOMBRANTI',1,3,'pezzi',1,'paia',NULL,NULL),(223,NULL,'Schermi tv ','R.A.E.E',1,2,'pezzi',1,'',NULL,NULL),(224,NULL,'Schermo computer ','R.A.E.E',1,3,'pezzi',1,'',NULL,NULL),(225,NULL,'Sci ','INGOMBRANTI',1,5,'pezzi',1,'paia',NULL,NULL),(226,NULL,'Scivolo grande','INGOMBRANTI',1,1,'pezzi',3,'smontare',NULL,NULL),(227,NULL,'Scivolo piccolo ','GIOCATTOLO',1,1,'pezzi',1,'smontare',NULL,NULL),(228,NULL,'Scrivania','MOBILI',1,1,'pezzi',1,'smontare',NULL,NULL),(229,NULL,'Sdraio ','INGOMBRANTI',1,3,'pezzi',1,'',NULL,NULL),(230,NULL,'Secchi','INGOMBRANTI',1,5,'pezzi',1,'',NULL,NULL),(231,NULL,'Sedia ','MOBILI',1,6,'pezzi',1,'',NULL,NULL),(232,NULL,'Sedia a rotelle','INGOMBRANTI',1,1,'pezzi',1,'smontare',NULL,NULL),(233,NULL,'Sedia a dondolo','MOBILI',1,1,'pezzi',1,'',NULL,NULL),(234,NULL,'Sedia ufficio','MOBILI',1,2,'pezzi',1,'',NULL,NULL),(235,NULL,'Sedia in vimini','MOBILI',1,4,'pezzi',1,'',NULL,NULL),(236,NULL,'Seggiolone per bambini ','INGOMBRANTI',1,2,'pezzi',1,'',NULL,NULL),(237,NULL,'Seggiolino per auto','INGOMBRANTI',1,2,'pezzi',1,'',NULL,NULL),(238,NULL,'Serranda','INGOMBRANTI',1,1,'pezzi',1,'legare',NULL,NULL),(239,NULL,'Sgabello','MOBILI',1,3,'pezzi',1,'',NULL,NULL),(240,NULL,'Slittino','INGOMBRANTI',1,4,'pezzi',1,'',NULL,NULL),(241,NULL,'Sottolavello','MOBILI',1,1,'pezzi',1,'',NULL,NULL),(242,NULL,'Specchi ','INGOMBRANTI',1,2,'pezzi',1,'',NULL,NULL),(243,NULL,'Sponde letto ','MOBILI',1,2,'pezzi',1,'',NULL,NULL),(244,NULL,'Sportello','INGOMBRANTI',1,4,'pezzi',1,'',NULL,NULL),(245,NULL,'Stampanti ','R.A.E.E',1,1,'pezzi',1,'',NULL,NULL),(246,NULL,'Stendibiancheria ','INGOMBRANTI',1,4,'pezzi',1,'',NULL,NULL),(247,NULL,'Strumenti musicali (no pianoforte)','INGOMBRANTI',1,2,'pezzi',1,'',NULL,NULL),(248,NULL,'Struttura letto','MOBILI',1,1,'pezzi',1,'smontare',NULL,NULL),(249,NULL,'Stufe a gas e legna','INGOMBRANTI',1,2,'pezzi',1,'smontare',NULL,NULL),(250,NULL,'Supporto TV in metallo','METALLI',1,4,'pezzi',1,'',NULL,NULL),(251,NULL,'Sveglia','R.A.E.E',1,10,'pezzi',1,'',NULL,NULL),(252,NULL,'Tablet ','R.A.E.E',1,10,'pezzi',1,'smontare',NULL,NULL),(253,NULL,'Tagliaerba','R.A.E.E',1,1,'pezzi',1,'',NULL,NULL),(254,NULL,'Tanica in metallo e/o plastica','INGOMBRANTI',1,5,'pezzi',1,'',NULL,NULL),(255,NULL,'Tapis roulant','INGOMBRANTI',1,1,'pezzi',1,'',NULL,NULL),(256,NULL,'Tappeto','INGOMBRANTI',1,3,'pezzi',1,'',NULL,NULL),(257,NULL,'Tavola surf','INGOMBRANTI',1,2,'pezzi',1,'',NULL,NULL),(258,NULL,'Tavolo','MOBILI',1,1,'pezzi',1,'smontare',NULL,NULL),(259,NULL,'Tavolo da disegno','MOBILI',1,1,'pezzi',1,'smontare',NULL,NULL),(260,NULL,'Telaio porte','INGOMBRANTI',1,4,'pezzi',1,'smontare',NULL,NULL),(261,NULL,'Telefono ','R.A.E.E',1,5,'pezzi',1,'',NULL,NULL),(262,NULL,'Televisore da 14\" a 43\"\"\"','R.A.E.E',1,1,'pezzi',1,'',NULL,NULL),(263,NULL,'Televisore da 49\" a 58\"\"\"','R.A.E.E',1,1,'pezzi',2,'',NULL,NULL),(264,NULL,'Televisore oltre 58\"\"','R.A.E.E',1,1,'pezzi',3,'',NULL,NULL),(265,NULL,'Teli nylon ','INGOMBRANTI',1,1,'pezzi',1,'',NULL,NULL),(266,NULL,'Tenda da sole piccola','INGOMBRANTI',1,2,'pezzi',1,'smontare',NULL,NULL),(267,NULL,'Tenda da sole grande','INGOMBRANTI',1,1,'pezzi',3,'smontare',NULL,NULL),(268,NULL,'Termosifone','INGOMBRANTI',1,2,'pezzi',1,'',NULL,NULL),(269,NULL,'Testata o spalliera letto','INGOMBRANTI',1,1,'pezzi',1,'',NULL,NULL),(270,NULL,'Top cucina','MOBILI',1,1,'pezzi',3,'smontare',NULL,NULL),(271,NULL,'Treppiedi','MOBILI',1,1,'pezzi',1,'',NULL,NULL),(272,NULL,'Triciclo o cavalcabile','GIOCATTOLO',1,1,'pezzi',1,'',NULL,NULL),(273,NULL,'Trita documenti','R.A.E.E',1,2,'pezzi',1,'',NULL,NULL),(274,NULL,'Tubi da irrigazione (da provenienza domestica)','INGOMBRANTI',1,5,'pezzi',1,'smontare',NULL,NULL),(275,NULL,'Valigie varie tipologie','',1,4,'pezzi',1,'',NULL,NULL),(276,NULL,'Ventilatore','R.A.E.E',1,3,'pezzi',1,'smontare',NULL,NULL),(277,NULL,'Videoregistratore','R.A.E.E',1,3,'pezzi',1,'',NULL,NULL),(278,NULL,'Zanzariere ','INGOMBRANTI',1,3,'pezzi',1,'smontare',NULL,NULL),(279,NULL,'Zappa ','INGOMBRANTI',1,4,'pezzi',1,'',NULL,NULL);
/*!40000 ALTER TABLE `catg_rifiuti` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `catgzone`
--

DROP TABLE IF EXISTS `catgzone`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `catgzone` (
  `zid` int(11) NOT NULL AUTO_INCREMENT,
  `zfkaccount` int(11) NOT NULL,
  `zdescr` varchar(100) NOT NULL,
  `zdtmod` datetime DEFAULT NULL,
  `zdtins` datetime DEFAULT NULL,
  `zusermod` int(11) DEFAULT NULL,
  PRIMARY KEY (`zid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `catgzone`
--

LOCK TABLES `catgzone` WRITE;
/*!40000 ALTER TABLE `catgzone` DISABLE KEYS */;
/*!40000 ALTER TABLE `catgzone` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `customer` (
  `cuid` int(11) NOT NULL AUTO_INCREMENT,
  `cufkaccount` int(11) NOT NULL,
  `cuname` varchar(250) DEFAULT NULL,
  `cusurname` varchar(250) NOT NULL,
  `cucf` varchar(20) DEFAULT NULL,
  `cupiva` varchar(20) DEFAULT NULL,
  `cufktype` int(11) DEFAULT NULL,
  `cudtmod` datetime DEFAULT NULL,
  `cudtins` datetime DEFAULT NULL,
  `cuusermod` int(11) DEFAULT NULL,
  `cuusername` varchar(250) DEFAULT NULL,
  `cupassword` varchar(250) DEFAULT NULL,
  `cuenabled` bit(1) DEFAULT NULL,
  `cunote` varchar(500) DEFAULT NULL,
  `cucode01` varchar(255) DEFAULT NULL,
  `cucode02` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`cuid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,1,'Name','Surname','bbbb',NULL,NULL,'2019-06-07 09:28:18','2019-06-07 09:28:17',NULL,'nando.zicarelli@gmail.com','$2a$10$Su2kwV2jBBE/1PIJKXpDCe5JOdgj57yw85QlJo315N2cUEDSSG7KK',_binary '\0','Cliente non trovato in anagrafica',NULL,NULL);
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lead`
--

DROP TABLE IF EXISTS `lead`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `lead` (
  `lid` int(11) NOT NULL AUTO_INCREMENT,
  `laccount` int(11) DEFAULT NULL,
  `ltype` int(11) DEFAULT NULL,
  `lowner` int(11) DEFAULT NULL,
  `lassign` int(11) DEFAULT NULL,
  `ldtmod` datetime DEFAULT NULL,
  `lstatus` int(11) DEFAULT NULL,
  `ldescr` varchar(255) DEFAULT NULL,
  `lfkcustomer` int(11) DEFAULT NULL,
  `ledtins` datetime DEFAULT NULL,
  `lefkmediaarrivo` int(11) DEFAULT NULL,
  `annullabile` bit(1) DEFAULT NULL,
  PRIMARY KEY (`lid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lead`
--

LOCK TABLES `lead` WRITE;
/*!40000 ALTER TABLE `lead` DISABLE KEYS */;
/*!40000 ALTER TABLE `lead` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lead_catg_mediaarrivo`
--

DROP TABLE IF EXISTS `lead_catg_mediaarrivo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `lead_catg_mediaarrivo` (
  `lcmaid` int(11) NOT NULL AUTO_INCREMENT,
  `lcmafkaccount` int(11) DEFAULT NULL,
  `lcmadescrizione` varchar(250) DEFAULT NULL,
  `lcmafktipolead` int(11) DEFAULT NULL,
  `lcmaorder` int(11) DEFAULT NULL,
  `lcmavisibleuser` bit(1) DEFAULT NULL,
  `lcmavisiblecustomer` bit(1) DEFAULT NULL,
  `lcmaisweb` bit(1) DEFAULT NULL,
  PRIMARY KEY (`lcmaid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lead_catg_mediaarrivo`
--

LOCK TABLES `lead_catg_mediaarrivo` WRITE;
/*!40000 ALTER TABLE `lead_catg_mediaarrivo` DISABLE KEYS */;
INSERT INTO `lead_catg_mediaarrivo` VALUES (1,1,'Web',1,1,_binary '',_binary '',_binary ''),(2,1,'Telefono',1,2,_binary '',_binary '',_binary '\0'),(3,1,'Web',2,1,_binary '',_binary '',_binary ''),(4,1,'Telefono',2,2,_binary '',_binary '',_binary '\0'),(5,1,'Web',3,1,_binary '',_binary '',_binary ''),(6,1,'Telefono',3,2,_binary '',_binary '',_binary '\0');
/*!40000 ALTER TABLE `lead_catg_mediaarrivo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lead_catg_stato`
--

DROP TABLE IF EXISTS `lead_catg_stato`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `lead_catg_stato` (
  `lcsid` int(11) NOT NULL AUTO_INCREMENT,
  `lcsfkaccount` int(11) DEFAULT NULL,
  `lcsdescrizione` varchar(250) DEFAULT NULL,
  `lcsfktipolead` int(11) DEFAULT NULL,
  `lcsorder` int(11) DEFAULT NULL,
  `lcsvisibleuser` bit(1) DEFAULT NULL,
  `lcsvisiblecustomer` bit(1) DEFAULT NULL,
  PRIMARY KEY (`lcsid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lead_catg_stato`
--

LOCK TABLES `lead_catg_stato` WRITE;
/*!40000 ALTER TABLE `lead_catg_stato` DISABLE KEYS */;
/*!40000 ALTER TABLE `lead_catg_stato` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lead_catg_tipo`
--

DROP TABLE IF EXISTS `lead_catg_tipo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `lead_catg_tipo` (
  `lctid` int(11) NOT NULL AUTO_INCREMENT,
  `lctfkaccount` int(11) DEFAULT NULL,
  `lctdescrizione` varchar(250) DEFAULT NULL,
  `lctorder` int(11) DEFAULT NULL,
  `lctvisibleuser` bit(1) DEFAULT NULL,
  `lctvisiblecustomer` bit(1) DEFAULT NULL,
  PRIMARY KEY (`lctid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lead_catg_tipo`
--

LOCK TABLES `lead_catg_tipo` WRITE;
/*!40000 ALTER TABLE `lead_catg_tipo` DISABLE KEYS */;
/*!40000 ALTER TABLE `lead_catg_tipo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `plan_detail`
--

DROP TABLE IF EXISTS `plan_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `plan_detail` (
  `pldid` int(11) NOT NULL AUTO_INCREMENT,
  `plddatefrom` datetime DEFAULT NULL,
  `plddateto` datetime DEFAULT NULL,
  `plddescr` varchar(255) DEFAULT NULL,
  `plddtins` datetime DEFAULT NULL,
  `pldfill` int(11) DEFAULT NULL,
  `pldfkaccount` int(11) DEFAULT NULL,
  `pldfkplannig` int(11) DEFAULT NULL,
  `pldtmod` datetime DEFAULT NULL,
  `pldusrmod` int(11) DEFAULT NULL,
  PRIMARY KEY (`pldid`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `plan_detail`
--

LOCK TABLES `plan_detail` WRITE;
/*!40000 ALTER TABLE `plan_detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `plan_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `planning`
--

DROP TABLE IF EXISTS `planning`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `planning` (
  `plnid` int(11) NOT NULL AUTO_INCREMENT,
  `plndescr` varchar(255) DEFAULT NULL,
  `plndtins` datetime DEFAULT NULL,
  `plndtmod` datetime DEFAULT NULL,
  `plnfkaccount` int(11) DEFAULT NULL,
  `plnfkzona` int(11) DEFAULT NULL,
  `plnowner` int(11) DEFAULT NULL,
  `plnusrmod` int(11) DEFAULT NULL,
  PRIMARY KEY (`plnid`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `planning`
--

LOCK TABLES `planning` WRITE;
/*!40000 ALTER TABLE `planning` DISABLE KEYS */;
/*!40000 ALTER TABLE `planning` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `users` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `uname` varchar(255) DEFAULT NULL,
  `upasswd` varchar(200) DEFAULT NULL,
  `uemail` varchar(255) DEFAULT NULL,
  `ucode01` varchar(50) DEFAULT NULL,
  `ucode02` varchar(50) DEFAULT NULL,
  `uaccount` int(11) DEFAULT NULL,
  `utype` int(11) DEFAULT NULL,
  `udtmod` datetime DEFAULT NULL,
  `urole` varchar(50) DEFAULT NULL,
  `uenable` bit(1) DEFAULT NULL,
  `umod` int(11) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'admin','$2a$10$655aRmSaNLJjkNdu2tBMxOUi9qju2Pig5Xjs2uumOE0PpSfDoRBEC','admin@bulky.com',NULL,NULL,0,NULL,'2019-06-07 08:44:25','ROLE_ADMIN',NULL,NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-06-07 16:21:38
