-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: prueba
-- ------------------------------------------------------
-- Server version	8.0.32

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
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin` (
  `id` int NOT NULL AUTO_INCREMENT,
  `contraseña` varchar(255) DEFAULT NULL,
  `imagenurl` varchar(255) DEFAULT NULL,
  `usuario` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (1,'Edwin_2003','https://media.istockphoto.com/id/1335941248/es/foto/foto-de-un-joven-guapo-de-pie-sobre-un-fondo-gris.jpg?s=612x612&w=0&k=20&c=UK8BGVVCSmHen0sJAgxoZ6sBLrs0ld4TuCwczZZ89AY=','edwin'),(2,'karina_2028','https://th.bing.com/th/id/OIP.2p61mBX-Fk__9pd7pvGVBQHaFW?w=282&h=203&c=7&r=0&o=5&dpr=1.7&pid=1.7','karina ');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `audio`
--

DROP TABLE IF EXISTS `audio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `audio` (
  `id` int NOT NULL AUTO_INCREMENT,
  `especificaciones` varchar(255) DEFAULT NULL,
  `marca` varchar(255) DEFAULT NULL,
  `modelo` varchar(255) DEFAULT NULL,
  `precio` varchar(255) DEFAULT NULL,
  `imagenurl` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `audio`
--

LOCK TABLES `audio` WRITE;
/*!40000 ALTER TABLE `audio` DISABLE KEYS */;
INSERT INTO `audio` VALUES (1,'Resistente al agua, 12 horas de reproducción','JBL','Lip 6','2900.99','https://http2.mlstatic.com/D_NQ_NP_605322-MLU74192226411_012024-O.webp'),(6,'Es manos libres, prueba de agua, voltaje 127 v ','SONY','XB13','849','https://cdn1.coppel.com/images/catalog/mkp/4288/3000/42881051-1.jpg'),(7,'Sonido con tecnología merida ','LG','XBOOM ','1300','https://m.media-amazon.com/images/I/71lG4TDOGkL.__AC_SX300_SY300_QL70_ML2_.jpg'),(8,'Calidad de sonido, conexión bluetooth, gran volumen','GENGXIN','GX-B02','160','https://m.media-amazon.com/images/I/41ZN7EEUA1L._AC_UF1000,1000_QL80_.jpg'),(9,'Sonido de alta fidelidad, hasta 12 horas de duración ','BOSE ','SoundLink Flex','3249','https://assets.bose.com/content/dam/cloudassets/Bose_DAM/Web/consumer_electronics/global/products/speakers/SLMAX-SPEAKERWIRELESS/product_silo_images/aem_pdp_SLMPS_gallery_black_600x511_x2_3.png/jcr:content/renditions/cq5dam.web.1920.1920.png'),(10,'Sonido nítido y potente que da la vida a tu música','Aiwa ','AWPOK9T','1509','https://ss024.suburbia.com.mx/xl/5010574562.jpg');
/*!40000 ALTER TABLE `audio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `laptop`
--

DROP TABLE IF EXISTS `laptop`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `laptop` (
  `id` int NOT NULL AUTO_INCREMENT,
  `almacenamiento` varchar(255) DEFAULT NULL,
  `especificaciones` varchar(255) DEFAULT NULL,
  `marca` varchar(255) DEFAULT NULL,
  `modelo` varchar(255) DEFAULT NULL,
  `precio` varchar(255) DEFAULT NULL,
  `ram` varchar(255) DEFAULT NULL,
  `tamaño` varchar(255) DEFAULT NULL,
  `imagenurl` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `laptop`
--

LOCK TABLES `laptop` WRITE;
/*!40000 ALTER TABLE `laptop` DISABLE KEYS */;
INSERT INTO `laptop` VALUES (1,'512GB','Pantalla 13.4\" FHD, Intel Core i7','Dell','XPS 13','1299.99','16GB','15 Pulgadas','https://m.media-amazon.com/images/I/91MXLpouhoL.jpg'),(2,'1TB','Chip M2 Pro, Pantalla Liquid Retina XDR','Apple','MacBook Pro 14\"','1999','16GB','13 Pulgadas','https://m.media-amazon.com/images/I/61Dz0+Y6NdL.jpg'),(3,'512GB','14\" 2K, Intel Core i5, Convertible','HP','Spectre x360','1099.5','12GB','12','https://m.media-amazon.com/images/I/51Qsuz2jKwL.jpg'),(4,'T TB','barato','Asus','Rog Strix G16','28000','32GB','16 pulgadas','https://m.media-amazon.com/images/I/61ojUr5xncL.jpg'),(6,'1TB','Chip M2 Pro, Pantalla Liquid Retina XDR, tarjeta grafica RTX4060','Lenovo','Legion 7 pro','32000','32 GB','13 Pulgadas','https://m.media-amazon.com/images/I/71SE23kVtjL._AC_UF350,350_QL80_.jpg');
/*!40000 ALTER TABLE `laptop` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `smartphone`
--

DROP TABLE IF EXISTS `smartphone`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `smartphone` (
  `id` int NOT NULL AUTO_INCREMENT,
  `almacenamiento` varchar(255) DEFAULT NULL,
  `especificaciones` varchar(255) DEFAULT NULL,
  `imagen` varchar(255) DEFAULT NULL,
  `marca` varchar(255) DEFAULT NULL,
  `modelo` varchar(255) DEFAULT NULL,
  `precio` varchar(255) DEFAULT NULL,
  `ram` varchar(255) DEFAULT NULL,
  `imagenurl` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `smartphone`
--

LOCK TABLES `smartphone` WRITE;
/*!40000 ALTER TABLE `smartphone` DISABLE KEYS */;
INSERT INTO `smartphone` VALUES (1,'256 GB','Buena oferta',NULL,'Sansumg','J4','25000','6GB','https://images.samsung.com/is/image/samsung/ar-galaxy-j4-j400-sm-j400mzvlaro-backviolet-153502065?$720_576_JPG$'),(2,'256 GB','Buena oferta en este temporada vacacional',NULL,'Phone','Pro max','28000','8 GB','https://cdsassets.apple.com/live/7WUAS350/images/tech-specs/iphone-15-pro-max.png'),(3,'256 GB','Resistencia grado militar ',NULL,'OPPO','A40','3506','4 GB','https://www.celex.com/cdn/shop/files/Oppo_A40_1000x1000_4.jpg?v=1723752292'),(4,'256 GB','Fotografía de alta resolución ',NULL,'Huawei ','Nova 13i','5145','8 GB','https://http2.mlstatic.com/D_NQ_NP_618456-MLA81436621296_122024-O.webp'),(5,'256 GB','Sistema operativo MIUI 14, en Android 13 ',NULL,'Xiaomi','12S','4199','8 GB','https://i02.appmifile.com/233_item_mx/15/06/2023/9d9bf4ad60dda37b81fc23bceeb34530!400x400!85.jpg'),(6,'512 GB','Incluye lapix touch',NULL,'Sansumg','Galaxy S24','24999','12 GB','https://s3-us-west-1.amazonaws.com/calzzapato/zoom/09J45K-1.jpg');
/*!40000 ALTER TABLE `smartphone` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tablet`
--

DROP TABLE IF EXISTS `tablet`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tablet` (
  `id` int NOT NULL AUTO_INCREMENT,
  `almacenamiento` varchar(255) DEFAULT NULL,
  `especificaciones` varchar(255) DEFAULT NULL,
  `marca` varchar(255) DEFAULT NULL,
  `modelo` varchar(255) DEFAULT NULL,
  `precio` varchar(255) DEFAULT NULL,
  `ram` varchar(255) DEFAULT NULL,
  `tamaño` varchar(255) DEFAULT NULL,
  `imagenurl` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tablet`
--

LOCK TABLES `tablet` WRITE;
/*!40000 ALTER TABLE `tablet` DISABLE KEYS */;
INSERT INTO `tablet` VALUES (1,'256GB','Pantalla Liquid Retina XDR, Chip M2','Apple','iPad Pro 12.9\"','1099','8GB','5.5 pulgadas','https://macstoreonline.com.mx/img/sku/IPADAPP628_FZ.jpg'),(2,'128GB','Pantalla 11\" 144Hz, Snapdragon 870','Xiaomi','Pad 6','399.99','6GB','13 pulgadas','https://i5.walmartimages.com/asr/b2526347-e7da-4097-8f03-476bc25d06d2.cb3e1e8b9ef343f2b269dcd9c4d36a94.jpeg?odnHeight=612&odnWidth=612&odnBg=FFFFFF'),(3,'256GB','Pantalla 12.7\" 2K, MediaTek Dimensity 7050','Lenovo','Tab P12','350.5','8GB','16 Pulgadas','https://m.media-amazon.com/images/I/71uoqeXNx6L._AC_UF894,1000_QL80_.jpg'),(6,'256 GB','Permite grabar conferencia, reuniones y muchos más ','SAMSUNG ','Galaxy','15999','12 GB ','14,6','https://m.media-amazon.com/images/I/41LEJycnrwL._AC_SX679_.jpg'),(7,'128 GB','Tecnología de protección ocular ','HONOR ','Pad X8a','3076','4 GB ','11.0','https://m.media-amazon.com/images/I/61a-HHMKFnL.jpg');
/*!40000 ALTER TABLE `tablet` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `televisor`
--

DROP TABLE IF EXISTS `televisor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `televisor` (
  `id` int NOT NULL AUTO_INCREMENT,
  `especificaciones` varchar(255) DEFAULT NULL,
  `imagenurl` varchar(255) DEFAULT NULL,
  `marca` varchar(255) DEFAULT NULL,
  `modelo` varchar(255) DEFAULT NULL,
  `precio` varchar(255) DEFAULT NULL,
  `tamaño` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `televisor`
--

LOCK TABLES `televisor` WRITE;
/*!40000 ALTER TABLE `televisor` DISABLE KEYS */;
INSERT INTO `televisor` VALUES (1,'4K OLED, Dolby Vision, WebOS 23','https://i5-mx.walmartimages.com/samsmx/images/product-images/img_large/981012452l.jpg','LG','OLED C3','1799.99','65 pulgadas'),(2,'4k Crystal UHD','https://m.media-amazon.com/images/I/61Q+fn3lcJL._AC_SY300_SX300_.jpg','Samsung','Smart TV ','6999','55'),(3,'Clase A4 Serie HD 720p con compatibilidad ','https://m.media-amazon.com/images/I/81PQsxaQyLL.__AC_SY300_SX300_QL70_ML2_.jpg','Hisens ','Roku','2499','32 '),(4,'Alta definición, con el tamaño, tecnología LED ','https://cdn1.coppel.com/images/catalog/pm/2998113-1.jpg?iresize=width:846,height:676','Daewoo','HD LED  DAW32H','1995','32'),(5,'Google TV QLED 4K Google Assistant ','https://m.media-amazon.com/images/I/61RVWdfs8NL._AC_UF1000,1000_QL80_.jpg','TCL ','Smart TV 2025','6790','55');
/*!40000 ALTER TABLE `televisor` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-06-05 12:23:07
