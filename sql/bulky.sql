create database bulky;

use bulky;
create table account(
	aid int not null primary key auto_increment,
	acredit int null,
	aname varchar(255),
	alogo varchar(255),
	astatus int null,
	adtmod datetime null
);


create table users(
	uid int not null primary key auto_increment,
	uname varchar(255) null,
	upasswd varchar(200) null,
	uemail varchar(255) null,
	ucode01 varchar(50) null,
	ucode02 varchar(50) null,
	uaccount int null,
	utype int null,
	udtmod datetime null,
	urole varchar(50) null,
	uenable bit null,
	umod int null
);

CREATE TABLE catg_rifiuti(
	crid int not null primary key auto_increment,
	craccount int null,
	crname varchar(250) null,
	crtype varchar(50) null,
	crqtymin int null,
	crqtymax int null,
	crudm varchar(50) null,
	crincombro int null,
	crnote varchar(500) null,
	crdtmod datetime null,
	crusermod int null
);

INSERT INTO `catg_rifiuti` VALUES (1,NULL,'Acquari in plastica ','INGOMBRANTI',1,2,'pezzi',1,'',NULL,NULL),(2,NULL,'Acquari in vetro ','INGOMBRANTI',1,2,'pezzi',1,'',NULL,NULL),(3,NULL,'Albero di Natale finto ','INGOMBRANTI',1,2,'pezzi',1,'',NULL,NULL),(4,NULL,'Alimentatore e/o Stabilizzatore','R.A.E.E',1,4,'pezzi',1,'',NULL,NULL),(5,NULL,'Altalena (giocattolo)','GIOCATTOLO',1,4,'pezzi',1,'smontare',NULL,NULL),(6,NULL,'Altalena in metallo','INGOMBRANTI',1,4,'pezzi',1,'smontare',NULL,NULL),(7,NULL,'Amplificatore ','R.A.E.E',1,4,'pezzi',1,'',NULL,NULL),(8,NULL,'Angoliera','MOBILI',1,4,'pezzi',1,'',NULL,NULL),(9,NULL,'Ante armadio in legno','MOBILI',1,3,'pezzi',1,'',NULL,NULL),(10,NULL,'Ante armadio in metallo','MOBILI',1,3,'pezzi',1,'',NULL,NULL),(11,NULL,'Ante armadio in vimini','MOBILI',1,3,'pezzi',1,'',NULL,NULL),(12,NULL,'Anta Box doccia','INGOMBRANTI',1,4,'pezzi',1,'',NULL,NULL),(13,NULL,'Antenna ','R.A.E.E',1,4,'pezzi',1,'',NULL,NULL),(14,NULL,'Antenna parabolica','R.A.E.E',1,2,'pezzi',1,'',NULL,NULL),(15,NULL,'Appendiabiti ','INGOMBRANTI',1,2,'pezzi',1,'',NULL,NULL),(16,NULL,'Armadi in Legno/Plastica','MOBILI',1,2,'pezzi',1,'smontare',NULL,NULL),(17,NULL,'Armadio 1 anta metallico','MOBILI',1,2,'pezzi',1,'smontare',NULL,NULL),(18,NULL,'Armadio 2 ante metallico','MOBILI',1,2,'pezzi',2,'smontare',NULL,NULL),(19,NULL,'Armadio 1 anta legno ','MOBILI',1,2,'pezzi',1,'smontare',NULL,NULL),(20,NULL,'Armadio 2 ante legno ','MOBILI',1,1,'pezzi',2,'smontare',NULL,NULL),(21,NULL,'Armadio 3 ante legno ','MOBILI',1,1,'pezzi',3,'smontare',NULL,NULL),(22,NULL,'Armadio 4 ante legno ','MOBILI',1,1,'pezzi',4,'smontare',NULL,NULL),(23,NULL,'Armadio 5 ante e superiore legno ','MOBILI',1,1,'pezzi',5,'smontare',NULL,NULL),(24,NULL,'Armadio a  ponte legno ','MOBILI',1,1,'pezzi',3,'smontare',NULL,NULL),(25,NULL,'Asciugacapelli ','R.A.E.E',1,4,'pezzi',1,'',NULL,NULL),(26,NULL,'Asciugatrice','R.A.E.E',1,2,'pezzi',1,'',NULL,NULL),(27,NULL,'Aspirapolvere ','R.A.E.E',1,2,'pezzi',1,'',NULL,NULL),(28,NULL,'Asse da stiro ','INGOMBRANTI',1,2,'pezzi',1,'',NULL,NULL),(29,NULL,'Aste di ferro da 50 cm. ','INGOMBRANTI',1,10,'pezzi',1,'',NULL,NULL),(30,NULL,'Attrezzi da giardino','INGOMBRANTI',1,6,'pezzi',1,'',NULL,NULL),(31,NULL,'Attrezzi da ginnastica grandi','INGOMBRANTI',1,1,'pezzi',1,'',NULL,NULL),(32,NULL,'Attrezzi da ginnastica piccoli','INGOMBRANTI',1,4,'pezzi',1,'',NULL,NULL),(33,NULL,'Auricolari e Cuffie ','R.A.E.E',1,6,'pezzi',1,'',NULL,NULL),(34,NULL,'Avvolgibili','INGOMBRANTI',1,1,'pezzi',1,'legare ',NULL,NULL),(35,NULL,'Bacheca','INGOMBRANTI',1,2,'pezzi',1,'',NULL,NULL),(36,NULL,'Bacinella ','INGOMBRANTI',1,4,'pezzi',1,'',NULL,NULL),(37,NULL,'Bambole e giocattoli ','GIOCATTOLO',1,1,'busta/sacchetto',1,'',NULL,NULL),(38,NULL,'Bambole e giocattoli con componenti elettrici','R.A.E.E',1,1,'busta/sacchetto',1,'',NULL,NULL),(39,NULL,'Bancali/Pedane ','LEGNO',1,3,'pezzi',1,'',NULL,NULL),(40,NULL,'Banco scuola','INGOMBRANTI',1,2,'pezzi',1,'',NULL,NULL),(41,NULL,'Barbecue in metallo','METALLI',1,2,'pezzi',1,'',NULL,NULL),(42,NULL,'Base ombrellone in plastica','INGOMBRANTI',1,3,'pezzi',1,'',NULL,NULL),(43,NULL,'Base tavolo','INGOMBRANTI',1,2,'pezzi',1,'',NULL,NULL),(44,NULL,'Bastone scopa','INGOMBRANTI',1,10,'pezzi',1,'',NULL,NULL),(45,NULL,'Bastoni tenda in ferro e/o legno','INGOMBRANTI',1,5,'pezzi',1,'',NULL,NULL),(46,NULL,'Battiscopa in legno ','LEGNO',1,10,'pezzi',1,'in +  pezzi ',NULL,NULL),(47,NULL,'Baule','INGOMBRANTI',1,1,'pezzi',1,'smontare',NULL,NULL),(48,NULL,'Biciclette adulto','INGOMBRANTI',1,1,'pezzi',1,'',NULL,NULL),(49,NULL,'Biciclette bimbi ','INGOMBRANTI',1,3,'pezzi',1,'',NULL,NULL),(50,NULL,' Cyclette','INGOMBRANTI',1,1,'pezzi',1,'',NULL,NULL),(51,NULL,'Bidoni e cestini porta rifiuti','INGOMBRANTI',1,5,'pezzi',1,'',NULL,NULL),(52,NULL,'Bilancia per alimenti','R.A.E.E',1,5,'pezzi',1,'',NULL,NULL),(53,NULL,'Bilancia pesapersone elettrica ','R.A.E.E',1,4,'pezzi',1,'',NULL,NULL),(54,NULL,'Biliardino','GIOCATTOLO',1,1,'pezzi',1,'smontare',NULL,NULL),(55,NULL,'Binarietti per tende ','INGOMBRANTI',1,10,'pezzi',1,'smontare',NULL,NULL),(56,NULL,'Bistecchiera elettrica','R.A.E.E',1,3,'pezzi',1,'',NULL,NULL),(57,NULL,'Bob in plastica','INGOMBRANTI',1,1,'pezzi',1,'',NULL,NULL),(58,NULL,'Box bimbi','INGOMBRANTI',1,1,'pezzi',1,'smontare',NULL,NULL),(59,NULL,'Box doccia','INGOMBRANTI',1,1,'pezzi',1,'smontare',NULL,NULL),(60,NULL,'Brandina','METALLI',1,1,'pezzi',1,'',NULL,NULL),(61,NULL,'Buffet','LEGNO',1,1,'pezzi',1,'smontare',NULL,NULL),(62,NULL,'Calcolatrice','R.A.E.E',1,10,'pezzi',1,'',NULL,NULL),(63,NULL,'Caldaia','R.A.E.E',1,1,'pezzi',1,'',NULL,NULL),(64,NULL,'Canaline passa cavi','INGOMBRANTI',1,10,'pezzi',1,'in +  pezzi ',NULL,NULL),(65,NULL,'Cancelletto protezione','INGOMBRANTI',1,1,'pezzi',1,'',NULL,NULL),(66,NULL,'Canne da irrigazione','PLASTICA',1,5,'pezzi',1,'',NULL,NULL),(67,NULL,'Canoa','INGOMBRANTI',1,1,'pezzi',1,'',NULL,NULL),(68,NULL,'Cappa aspiratrice','R.A.E.E',1,1,'pezzi',1,'',NULL,NULL),(69,NULL,'Cappottine ripara sole','INGOMBRANTI',1,1,'pezzi',1,'smontare',NULL,NULL),(70,NULL,'Carrello porta vivande','INGOMBRANTI',1,2,'pezzi',1,'smontare',NULL,NULL),(71,NULL,'Carriola','INGOMBRANTI',1,1,'pezzi',1,'',NULL,NULL),(72,NULL,'Carrozzella','INGOMBRANTI',1,2,'pezzi',1,'smontare',NULL,NULL),(73,NULL,'Carrozzina','INGOMBRANTI',1,1,'pezzi',1,'smontare',NULL,NULL),(74,NULL,'Casco per moto e bici','INGOMBRANTI',1,3,'pezzi',1,'',NULL,NULL),(75,NULL,'Cassaforte piccola (da muro)','INGOMBRANTI',1,3,'pezzi',1,'',NULL,NULL),(76,NULL,'Cassapanca','MOBILI',1,5,'pezzi',1,'smontare',NULL,NULL),(77,NULL,'Casse audio','R.A.E.E',1,4,'pezzi',1,'',NULL,NULL),(78,NULL,'Cassetti','MOBILI',1,4,'pezzi',1,'',NULL,NULL),(79,NULL,'Cassettiera','MOBILI',1,1,'pezzi',1,'smontare',NULL,NULL),(80,NULL,'Cassettone letto','MOBILI',1,1,'pezzi',1,'smontare',NULL,NULL),(81,NULL,'Catalogatore','MOBILI',1,1,'pezzi',1,'smontare',NULL,NULL),(82,NULL,'Cavalletto','INGOMBRANTI',1,5,'pezzi',1,'',NULL,NULL),(83,NULL,'Cavi elettrici','R.A.E.E',1,10,'pezzi',1,'',NULL,NULL),(84,NULL,'Celetto (cassettone di finestra e/o balcone)','INGOMBRANTI',1,2,'pezzi',1,'smontare',NULL,NULL),(85,NULL,'Cellulare (Apparecchi telefonia mobile) ','R.A.E.E',1,10,'pezzi',1,'',NULL,NULL),(86,NULL,'Centralina telefonica','R.A.E.E',1,3,'pezzi',1,'smontare',NULL,NULL),(87,NULL,'Cesta in vimini','LEGNO',1,6,'pezzi',1,'',NULL,NULL),(88,NULL,'Como\'','MOBILI',1,1,'pezzi',1,'smontare',NULL,NULL),(89,NULL,'Comodino','MOBILI',1,2,'pezzi',1,'',NULL,NULL),(90,NULL,'Computer (monitor + tastiera + case + tower)','R.A.E.E',1,1,'pezzi',1,'',NULL,NULL),(91,NULL,'Condizionatore ','R.A.E.E',1,1,'pezzi',1,'',NULL,NULL),(92,NULL,'Congelatore ','R.A.E.E',1,1,'pezzi',1,'',NULL,NULL),(93,NULL,'Consolle videogiochi ','R.A.E.E',1,4,'pezzi',1,'',NULL,NULL),(94,NULL,'Copri caldaia','INGOMBRANTI',1,2,'pezzi',1,'smontare',NULL,NULL),(95,NULL,'Copri termosifone','MOBILI',1,2,'pezzi',1,'',NULL,NULL),(96,NULL,'Cornici','INGOMBRANTI',1,10,'pezzi',1,'',NULL,NULL),(97,NULL,'Credenza','MOBILI',1,1,'pezzi',3,'smontare',NULL,NULL),(98,NULL,'Cristalliera','MOBILI',1,1,'pezzi',3,'smontare',NULL,NULL),(99,NULL,'Cubo legno','',1,1,'pezzi',1,'',NULL,NULL),(100,NULL,'Cuccia per animali','INGOMBRANTI',1,1,'pezzi',1,'smontare',NULL,NULL),(101,NULL,'Culla','MOBILI',1,1,'pezzi',1,'smontare',NULL,NULL),(102,NULL,'Deambulatore','INGOMBRANTI',1,1,'pezzi',1,'',NULL,NULL),(103,NULL,'Decespugliatore','INGOMBRANTI',1,1,'pezzi',1,'',NULL,NULL),(104,NULL,'Decoder ','R.A.E.E',1,5,'pezzi',1,'',NULL,NULL),(105,NULL,'Divano (2 posti)','MOBILI',1,1,'pezzi',2,'',NULL,NULL),(106,NULL,'Divano (3 posti)','MOBILI',1,1,'pezzi',3,'',NULL,NULL),(107,NULL,'Divano (4 posti)','MOBILI',1,1,'pezzi',4,'',NULL,NULL),(108,NULL,'Divano (con isola)','MOBILI',1,1,'pezzi',6,'',NULL,NULL),(109,NULL,'Doghe (letto singolo)','INGOMBRANTI',1,1,'pezzi',1,'',NULL,NULL),(110,NULL,'Doghe (letto matrimoniale)','INGOMBRANTI',1,1,'pezzi',2,'',NULL,NULL),(111,NULL,'Dondolo','INGOMBRANTI',1,1,'pezzi',2,'smontare',NULL,NULL),(112,NULL,'Dondolo (bimbi o gioco)','INGOMBRANTI',1,1,'pezzi',1,'smontare',NULL,NULL),(113,NULL,'Espositori ','INGOMBRANTI',1,4,'pezzi',1,'',NULL,NULL),(114,NULL,'Fasciatoio','INGOMBRANTI',1,1,'pezzi',1,'',NULL,NULL),(115,NULL,'Fax (apparecchio)','R.A.E.E',1,1,'pezzi',1,'',NULL,NULL),(116,NULL,'Ferro da stiro (con o senza caldaia)','R.A.E.E',1,2,'pezzi',1,'',NULL,NULL),(117,NULL,'Finestra con telaio','INGOMBRANTI',1,1,'pezzi',1,'',NULL,NULL),(118,NULL,'Fioriere','INGOMBRANTI',1,4,'pezzi',1,'',NULL,NULL),(119,NULL,'Forni e fornelli elettrici ','R.A.E.E',1,1,'pezzi',1,'',NULL,NULL),(120,NULL,'Forno microonde ','R.A.E.E',1,2,'pezzi',1,'',NULL,NULL),(121,NULL,'Fotocopiatrici uso domestico','R.A.E.E',1,1,'pezzi',1,'',NULL,NULL),(122,NULL,'Friggitrice ','R.A.E.E',1,2,'pezzi',1,'',NULL,NULL),(123,NULL,'Frigorifero ','R.A.E.E',1,1,'pezzi',1,'',NULL,NULL),(124,NULL,'Frullatore ','R.A.E.E',1,3,'pezzi',1,'',NULL,NULL),(125,NULL,'Fusti','INGOMBRANTI',1,1,'pezzi',1,'',NULL,NULL),(126,NULL,'Gabbietta per animali ','INGOMBRANTI',1,2,'pezzi',1,'',NULL,NULL),(127,NULL,'Gazebo ','INGOMBRANTI',1,1,'pezzi',1,'smontare',NULL,NULL),(128,NULL,'Giocattoli  (sacco 110x40 trasparente) ','INGOMBRANTI',1,1,'pezzi',1,'',NULL,NULL),(129,NULL,'Girello per bambini','INGOMBRANTI',1,1,'pezzi',1,'smontare',NULL,NULL),(130,NULL,'Gomma per l\'orto','INGOMBRANTI',1,2,'pezzi',1,'',NULL,NULL),(131,NULL,'Grata (per porte e/o finestre)','INGOMBRANTI',1,2,'pezzi',1,'',NULL,NULL),(132,NULL,'Grattugia elettrica ','R.A.E.E',1,2,'pezzi',1,'',NULL,NULL),(133,NULL,'Gruppo di continuità','R.A.E.E',1,1,'pezzi',1,'',NULL,NULL),(134,NULL,'Hard disk','R.A.E.E',1,3,'pezzi',1,'',NULL,NULL),(135,NULL,'Impianto HI-FI','R.A.E.E',1,1,'pezzi',1,'',NULL,NULL),(136,NULL,'Incannuciata','INGOMBRANTI',1,1,'pezzi',1,'',NULL,NULL),(137,NULL,'Infissi legno/metallo','INGOMBRANTI',1,1,'pezzi',1,'',NULL,NULL),(138,NULL,'Lampade','R.A.E.E',1,4,'pezzi',1,'',NULL,NULL),(139,NULL,'Lampadari ','R.A.E.E',1,2,'pezzi',1,'',NULL,NULL),(140,NULL,'Lastre  di vetro ','VETRO',1,1,'pezzi',1,'',NULL,NULL),(141,NULL,'Lavagna','INGOMBRANTI',1,1,'pezzi',1,'',NULL,NULL),(142,NULL,'Lavastoviglie ','R.A.E.E',1,1,'pezzi',1,'',NULL,NULL),(143,NULL,'Lavatrice ','R.A.E.E',1,1,'pezzi',1,'',NULL,NULL),(144,NULL,'Lavello cucina','MOBILI',1,1,'pezzi',1,'',NULL,NULL),(145,NULL,'Lavabo da esterno (legno/plastica)','MOBILI',1,1,'pezzi',1,'',NULL,NULL),(146,NULL,'Letto ','MOBILI',1,1,'pezzi',1,'',NULL,NULL),(147,NULL,'Letto a castello','MOBILI',1,1,'pezzi',2,'smontare',NULL,NULL),(148,NULL,'Lettore dvd/cd  e/o videoregistratori','R.A.E.E',1,2,'pezzi',1,'smontare',NULL,NULL),(149,NULL,'Libreria','MOBILI',1,1,'pezzi',3,'smontare',NULL,NULL),(150,NULL,'Lucidatrice','R.A.E.E',1,2,'pezzi',1,'',NULL,NULL),(151,NULL,'Macchina da cucire','INGOMBRANTI',1,1,'pezzi',1,'',NULL,NULL),(152,NULL,'Macchina da cucire con mobile','INGOMBRANTI',1,1,'pezzi',2,'',NULL,NULL),(153,NULL,'Macchina da scrivere ','INGOMBRANTI',1,2,'pezzi',1,'',NULL,NULL),(154,NULL,'Macchina del caffè','R.A.E.E',1,2,'pezzi',1,'',NULL,NULL),(155,NULL,'Macchina del Gas','R.A.E.E',1,1,'pezzi',1,'',NULL,NULL),(156,NULL,'Macchina per maglieria','R.A.E.E',1,1,'pezzi',1,'',NULL,NULL),(157,NULL,'Mantovana','MOBILI',1,3,'pezzi',1,'',NULL,NULL),(158,NULL,'Materassino mare','INGOMBRANTI',1,4,'pezzi',1,'',NULL,NULL),(159,NULL,'Materasso (letto singolo)','INGOMBRANTI',1,1,'pezzi',1,'',NULL,NULL),(160,NULL,'Materasso (letto matrimoniale)','INGOMBRANTI',1,1,'pezzi',2,'',NULL,NULL),(161,NULL,'Materiale elettrico (busta grande 100x70) ','R.A.E.E',1,1,'pezzi',1,'',NULL,NULL),(162,NULL,'Materiale elettrico (busta piccola 70x40) ','R.A.E.E',1,2,'pezzi',1,'',NULL,NULL),(163,NULL,'Mensole','MOBILI',1,6,'pezzi',1,'',NULL,NULL),(164,NULL,'Misuratori di pressione digitali RAEE','R.A.E.E',1,4,'pezzi',1,'',NULL,NULL),(165,NULL,'Mobile bagno','MOBILI',1,1,'pezzi',1,'',NULL,NULL),(166,NULL,'Mobile letto','MOBILI',1,1,'pezzi',1,'',NULL,NULL),(167,NULL,'Mobile o Colonna frigo','MOBILI',1,1,'pezzi',1,'',NULL,NULL),(168,NULL,'Mobile per HI-FI','MOBILI',1,1,'pezzi',1,'',NULL,NULL),(169,NULL,'Mobile porta TV','MOBILI',1,1,'pezzi',1,'',NULL,NULL),(170,NULL,'Mobile sala','MOBILI',1,1,'pezzi',3,'smontare',NULL,NULL),(171,NULL,'Monitor ','R.A.E.E',1,2,'pezzi',1,'',NULL,NULL),(172,NULL,'Morsa','METALLI',1,1,'pezzi',1,'',NULL,NULL),(173,NULL,'Mostre in legno','INGOMBRANTI',1,10,'pezzi',1,'',NULL,NULL),(174,NULL,'Mouse ','R.A.E.E',1,10,'pezzi',1,'',NULL,NULL),(175,NULL,'Navigatore ','R.A.E.E',1,6,'pezzi',1,'',NULL,NULL),(176,NULL,'Notebook ','R.A.E.E',1,2,'pezzi',1,'',NULL,NULL),(177,NULL,'Ombrelli','INGOMBRANTI',1,10,'pezzi',1,'',NULL,NULL),(178,NULL,'Ombrellone','INGOMBRANTI',1,1,'pezzi',1,'',NULL,NULL),(179,NULL,'Orologio a muro','R.A.E.E',1,3,'pezzi',1,'',NULL,NULL),(180,NULL,'Pallet','INGOMBRANTI',1,1,'pezzi',1,'',NULL,NULL),(181,NULL,'Panca','MOBILI',1,1,'pezzi',1,'',NULL,NULL),(182,NULL,'Panca fitness','INGOMBRANTI',1,1,'pezzi',1,'',NULL,NULL),(183,NULL,'Pannelli in truciolato ','INGOMBRANTI',1,3,'pezzi',1,'',NULL,NULL),(184,NULL,'Paralume','INGOMBRANTI',1,3,'pezzi',1,'',NULL,NULL),(185,NULL,'Passeggino','INGOMBRANTI',1,1,'pezzi',1,'',NULL,NULL),(186,NULL,'Peluche (busta grande 100x70) ','GIOCATTOLO',1,1,'pezzi',1,'',NULL,NULL),(187,NULL,'Pensili','MOBILI',1,1,'pezzi',1,'',NULL,NULL),(188,NULL,'Persiane','INGOMBRANTI',1,2,'pezzi',1,'',NULL,NULL),(189,NULL,'Piano cottura','R.A.E.E',1,1,'pezzi',1,'',NULL,NULL),(190,NULL,'Piante finte','INGOMBRANTI',1,4,'pezzi',1,'',NULL,NULL),(191,NULL,'Piccoli elettrodomestici (phon, frullatori, ecc. ...)','R.A.E.E',1,4,'pezzi',1,'',NULL,NULL),(192,NULL,'Poltrone ','MOBILI',1,1,'pezzi',1,'',NULL,NULL),(193,NULL,'Pompa idraulica','INGOMBRANTI',1,1,'pezzi',1,'',NULL,NULL),(194,NULL,'Porta ombrelli','INGOMBRANTI',1,4,'pezzi',1,'',NULL,NULL),(195,NULL,'Porta vasi','INGOMBRANTI',1,4,'pezzi',1,'',NULL,NULL),(196,NULL,'Portabagagli cesto','INGOMBRANTI',1,4,'pezzi',1,'',NULL,NULL),(197,NULL,'Portabagagli Box','INGOMBRANTI',1,1,'pezzi',1,'',NULL,NULL),(198,NULL,'Porte con telaio','INGOMBRANTI',1,1,'pezzi',1,'',NULL,NULL),(199,NULL,'Porte a soffietto ','INGOMBRANTI',1,1,'pezzi',1,'',NULL,NULL),(200,NULL,'Porta blindata','INGOMBRANTI',1,1,'pezzi',3,'',NULL,NULL),(201,NULL,'Portoncino','INGOMBRANTI',1,1,'pezzi',3,'',NULL,NULL),(202,NULL,'Puff','MOBILI',1,2,'pezzi',1,'',NULL,NULL),(203,NULL,'Quadri/cornici ','INGOMBRANTI',1,6,'pezzi',1,'',NULL,NULL),(204,NULL,'Quadro elettrico ','R.A.E.E',1,1,'pezzi',1,'',NULL,NULL),(205,NULL,'Racchette da tennis','INGOMBRANTI',1,4,'pezzi',1,'',NULL,NULL),(206,NULL,'Radio o radiolina','R.A.E.E',1,6,'pezzi',1,'',NULL,NULL),(207,NULL,'Radio registratore','R.A.E.E',1,6,'pezzi',1,'',NULL,NULL),(208,NULL,'Ram, schede di rete, schede video','R.A.E.E',1,20,'pezzi',1,'',NULL,NULL),(209,NULL,'Registratore di cassa','R.A.E.E',1,2,'pezzi',1,'',NULL,NULL),(210,NULL,'Remi (barca/canoa)','INGOMBRANTI',1,4,'pezzi',1,'',NULL,NULL),(211,NULL,'Rete (letto) 1 piazza','MOBILI',1,1,'pezzi',1,'',NULL,NULL),(212,NULL,'Rete (letto) 2  piazze','MOBILI',1,1,'pezzi',2,'',NULL,NULL),(213,NULL,'Rete (recinzione)','INGOMBRANTI',1,2,'pezzi',1,'rotoli',NULL,NULL),(214,NULL,'Rilegatrice ','INGOMBRANTI',1,3,'pezzi',1,'',NULL,NULL),(215,NULL,'Robot (da cucina)','R.A.E.E',1,2,'pezzi',1,'',NULL,NULL),(216,NULL,'Rubinetteria (busta piccola 70x40) ','INGOMBRANTI',1,2,'pezzi',1,'',NULL,NULL),(217,NULL,'Ruote biciclette','INGOMBRANTI',1,4,'pezzi',1,'',NULL,NULL),(218,NULL,'Scaffalatura','INGOMBRANTI',1,1,'pezzi',1,'smontare',NULL,NULL),(219,NULL,'Scala pieghevole','INGOMBRANTI',1,1,'pezzi',1,'',NULL,NULL),(220,NULL,'Scaldabagno','R.A.E.E',1,1,'pezzi',1,'',NULL,NULL),(221,NULL,'Scarpiera','INGOMBRANTI',1,1,'pezzi',1,'smontare',NULL,NULL),(222,NULL,'Scarponi da sci','INGOMBRANTI',1,3,'pezzi',1,'paia',NULL,NULL),(223,NULL,'Schermi tv ','R.A.E.E',1,2,'pezzi',1,'',NULL,NULL),(224,NULL,'Schermo computer ','R.A.E.E',1,3,'pezzi',1,'',NULL,NULL),(225,NULL,'Sci ','INGOMBRANTI',1,5,'pezzi',1,'paia',NULL,NULL),(226,NULL,'Scivolo grande','INGOMBRANTI',1,1,'pezzi',3,'smontare',NULL,NULL),(227,NULL,'Scivolo piccolo ','GIOCATTOLO',1,1,'pezzi',1,'smontare',NULL,NULL),(228,NULL,'Scrivania','MOBILI',1,1,'pezzi',1,'smontare',NULL,NULL),(229,NULL,'Sdraio ','INGOMBRANTI',1,3,'pezzi',1,'',NULL,NULL),(230,NULL,'Secchi','INGOMBRANTI',1,5,'pezzi',1,'',NULL,NULL),(231,NULL,'Sedia ','MOBILI',1,6,'pezzi',1,'',NULL,NULL),(232,NULL,'Sedia a rotelle','INGOMBRANTI',1,1,'pezzi',1,'smontare',NULL,NULL),(233,NULL,'Sedia a dondolo','MOBILI',1,1,'pezzi',1,'',NULL,NULL),(234,NULL,'Sedia ufficio','MOBILI',1,2,'pezzi',1,'',NULL,NULL),(235,NULL,'Sedia in vimini','MOBILI',1,4,'pezzi',1,'',NULL,NULL),(236,NULL,'Seggiolone per bambini ','INGOMBRANTI',1,2,'pezzi',1,'',NULL,NULL),(237,NULL,'Seggiolino per auto','INGOMBRANTI',1,2,'pezzi',1,'',NULL,NULL),(238,NULL,'Serranda','INGOMBRANTI',1,1,'pezzi',1,'legare',NULL,NULL),(239,NULL,'Sgabello','MOBILI',1,3,'pezzi',1,'',NULL,NULL),(240,NULL,'Slittino','INGOMBRANTI',1,4,'pezzi',1,'',NULL,NULL),(241,NULL,'Sottolavello','MOBILI',1,1,'pezzi',1,'',NULL,NULL),(242,NULL,'Specchi ','INGOMBRANTI',1,2,'pezzi',1,'',NULL,NULL),(243,NULL,'Sponde letto ','MOBILI',1,2,'pezzi',1,'',NULL,NULL),(244,NULL,'Sportello','INGOMBRANTI',1,4,'pezzi',1,'',NULL,NULL),(245,NULL,'Stampanti ','R.A.E.E',1,1,'pezzi',1,'',NULL,NULL),(246,NULL,'Stendibiancheria ','INGOMBRANTI',1,4,'pezzi',1,'',NULL,NULL),(247,NULL,'Strumenti musicali (no pianoforte)','INGOMBRANTI',1,2,'pezzi',1,'',NULL,NULL),(248,NULL,'Struttura letto','MOBILI',1,1,'pezzi',1,'smontare',NULL,NULL),(249,NULL,'Stufe a gas e legna','INGOMBRANTI',1,2,'pezzi',1,'smontare',NULL,NULL),(250,NULL,'Supporto TV in metallo','METALLI',1,4,'pezzi',1,'',NULL,NULL),(251,NULL,'Sveglia','R.A.E.E',1,10,'pezzi',1,'',NULL,NULL),(252,NULL,'Tablet ','R.A.E.E',1,10,'pezzi',1,'smontare',NULL,NULL),(253,NULL,'Tagliaerba','R.A.E.E',1,1,'pezzi',1,'',NULL,NULL),(254,NULL,'Tanica in metallo e/o plastica','INGOMBRANTI',1,5,'pezzi',1,'',NULL,NULL),(255,NULL,'Tapis roulant','INGOMBRANTI',1,1,'pezzi',1,'',NULL,NULL),(256,NULL,'Tappeto','INGOMBRANTI',1,3,'pezzi',1,'',NULL,NULL),(257,NULL,'Tavola surf','INGOMBRANTI',1,2,'pezzi',1,'',NULL,NULL),(258,NULL,'Tavolo','MOBILI',1,1,'pezzi',1,'smontare',NULL,NULL),(259,NULL,'Tavolo da disegno','MOBILI',1,1,'pezzi',1,'smontare',NULL,NULL),(260,NULL,'Telaio porte','INGOMBRANTI',1,4,'pezzi',1,'smontare',NULL,NULL),(261,NULL,'Telefono ','R.A.E.E',1,5,'pezzi',1,'',NULL,NULL),(262,NULL,'Televisore da 14\" a 43\"\"\"','R.A.E.E',1,1,'pezzi',1,'',NULL,NULL),(263,NULL,'Televisore da 49\" a 58\"\"\"','R.A.E.E',1,1,'pezzi',2,'',NULL,NULL),(264,NULL,'Televisore oltre 58\"\"','R.A.E.E',1,1,'pezzi',3,'',NULL,NULL),(265,NULL,'Teli nylon ','INGOMBRANTI',1,1,'pezzi',1,'',NULL,NULL),(266,NULL,'Tenda da sole piccola','INGOMBRANTI',1,2,'pezzi',1,'smontare',NULL,NULL),(267,NULL,'Tenda da sole grande','INGOMBRANTI',1,1,'pezzi',3,'smontare',NULL,NULL),(268,NULL,'Termosifone','INGOMBRANTI',1,2,'pezzi',1,'',NULL,NULL),(269,NULL,'Testata o spalliera letto','INGOMBRANTI',1,1,'pezzi',1,'',NULL,NULL),(270,NULL,'Top cucina','MOBILI',1,1,'pezzi',3,'smontare',NULL,NULL),(271,NULL,'Treppiedi','MOBILI',1,1,'pezzi',1,'',NULL,NULL),(272,NULL,'Triciclo o cavalcabile','GIOCATTOLO',1,1,'pezzi',1,'',NULL,NULL),(273,NULL,'Trita documenti','R.A.E.E',1,2,'pezzi',1,'',NULL,NULL),(274,NULL,'Tubi da irrigazione (da provenienza domestica)','INGOMBRANTI',1,5,'pezzi',1,'smontare',NULL,NULL),(275,NULL,'Valigie varie tipologie','',1,4,'pezzi',1,'',NULL,NULL),(276,NULL,'Ventilatore','R.A.E.E',1,3,'pezzi',1,'smontare',NULL,NULL),(277,NULL,'Videoregistratore','R.A.E.E',1,3,'pezzi',1,'',NULL,NULL),(278,NULL,'Zanzariere ','INGOMBRANTI',1,3,'pezzi',1,'smontare',NULL,NULL),(279,NULL,'Zappa ','INGOMBRANTI',1,4,'pezzi',1,'',NULL,NULL);



create table customer (
	cuid int not null primary key auto_increment,
	cufkaccount int not null,
	cuname varchar(250) null,
	cusurname varchar(250) not null,
	cucf varchar(20) null,
	cupiva varchar(20) null,
	cufktype int null,
	cudtmod datetime null,
	cudtins datetime null,
	cuusermod int null,
	cuusername varchar(250) null,
	cupassword varchar(250) null,
	cuenabled bit null,
	cunote varchar(500) null
);

create table address (
	adid int not null primary key auto_increment,
	adfkaccount int not null,
	adfkcustomer int not null,
	adcomune varchar(250) null,
	adprov varchar(250)  null,
	adsiglaprov varchar(20) null,
	adaddress varchar(500) null,
	adnum varchar(50) null,
	adcap varchar(20) null,
	adfkzona int null,
	addtins datetime null,
	addtmod datetime null,
	adusermod int null,
	adnormalizzato varchar(500) null
); 

create table catgzone(
	zid int not null primary key auto_increment,
	zfkaccount int not null,
	zdescr varchar(100) not null,
	zdtmod datetime null,
	zdtins datetime null,
	zusermod int null
);

create table address_zone(
	azid int not null primary key auto_increment,
	azfkaccount int not null,
	azfkzona int not null,
	azcomune varchar(250) null,
	azprov varchar(250) null,
	azsiglaprov varchar(20) null,
	azcap varchar(20) null,
	azaddress varchar(500) null,
	azdtmod datetime null,
	azdtins datetime null,
	azusermod int null
);


CREATE TABLE catg_action(
	caid int not null primary key auto_increment,
	cafkaccount int not null,
	cafktlead int null,
	castatus int null,
	cadescr varchar(250) null,
	catitle varchar(250) null,
	canote varchar(500) null,
	caenabled bit null,
	cadtins datetime null,
	cadtmod datetime null,
	causermod int null
);

insert into catg_action(cafkaccount,cafktlead,castatus,cadescr,catitle,canote,caenabled) values(1,1,1,'','Ingombranti/Sfalci-Potature','',1);
insert into catg_action(cafkaccount,cafktlead,castatus,cadescr,catitle,canote,caenabled) values(1,1,1,'','Smaltimento Rifiuti Speciali','',1);
insert into catg_action(cafkaccount,cafktlead,castatus,cadescr,catitle,canote,caenabled) values(1,1,1,'','Richiesta Informazione','',1);




CREATE TABLE lead (
	lid int not null primary key auto_increment,
	laccount int null,
	ltype int null,
	lowner int null,
	lassign int null,
	ldtmod datetime,
	lstatus int null,
	ldescr varchar(255) null,
	lfkcustomer int null	
);

CREATE TABLE activity (
	aid int not null primary key auto_increment,
	aaccount int null,
	afktype int null,
	aowner int null,
	aassign int null,
	adtmod datetime,
	astatus int null,
	adescr varchar(255) null,
	atitle varchar(255) null,
	asubject varchar(255) null,	
	afkcustomer int null	
);

CREATE TABLE act_booking (
	bid int not null primary key auto_increment,
	baccount int null,
	bfkactivity int null,
	bfkcatg int null,
	bqty int null,
	bdate datetime null,
	bfkaddress int null,
	bdtmod datetime null,
	bstatus int null,
	bdescr varchar(255) null,
	bnote varchar(500) null,
	bfkcustomer int null	
);

alter table catg_action add cacusenable bit null;

alter table catg_action add cadoctype varchar(100) null;

ALTER TABLE lead ADD ledtins datetime null;

ALTER TABLE lead ADD lefkmediaarrivo int null;

CREATE TABLE lead_catg_stato (
	lcsid int not null primary key auto_increment,
	lcsfkaccount int null,
	lcsdescrizione varchar(250) null,
	lcsfktipolead int null,
	lcsorder int null,
	lcsvisibleuser bit null,
	lcsvisiblecustomer bit null
);

CREATE TABLE lead_catg_tipo (
	lctid int not null primary key auto_increment,
	lctfkaccount int null,
	lctdescrizione varchar(250) null,	
	lctorder int null,
	lctvisibleuser bit null,
	lctvisiblecustomer bit null
);

CREATE TABLE lead_catg_mediaarrivo (
	lcmaid int not null primary key auto_increment,
	lcmafkaccount int null,
	lcmadescrizione varchar(250) null,
	lcmafktipolead int null,
	lcmaorder int null,
	lcmavisibleuser bit null,
	lcmavisiblecustomer bit null,
	lcmaisweb bit null
);

INSERT INTO `bulky`.`lead_catg_mediaarrivo` (`lcmafkaccount`, `lcmadescrizione`, `lcmafktipolead`, `lcmaorder`, `lcmavisibleuser`, `lcmavisiblecustomer`, `lcmaisweb`) 
VALUES ('1', 'Web', '1', '1', '1', '1', '1');
INSERT INTO `bulky`.`lead_catg_mediaarrivo` (`lcmafkaccount`, `lcmadescrizione`, `lcmafktipolead`, `lcmaorder`, `lcmavisibleuser`, `lcmavisiblecustomer`, `lcmaisweb`) 
VALUES ('1', 'Telefono', '1', '2', '1', '1', '0');

INSERT INTO `bulky`.`lead_catg_mediaarrivo` (`lcmafkaccount`, `lcmadescrizione`, `lcmafktipolead`, `lcmaorder`, `lcmavisibleuser`, `lcmavisiblecustomer`, `lcmaisweb`) 
VALUES ('1', 'Web', '2', '1', '1', '1', '1');
INSERT INTO `bulky`.`lead_catg_mediaarrivo` (`lcmafkaccount`, `lcmadescrizione`, `lcmafktipolead`, `lcmaorder`, `lcmavisibleuser`, `lcmavisiblecustomer`, `lcmaisweb`) 
VALUES ('1', 'Telefono', '2', '2', '1', '1', '0');

INSERT INTO `bulky`.`lead_catg_mediaarrivo` (`lcmafkaccount`, `lcmadescrizione`, `lcmafktipolead`, `lcmaorder`, `lcmavisibleuser`, `lcmavisiblecustomer`, `lcmaisweb`) 
VALUES ('1', 'Web', '3', '1', '1', '1', '1');
INSERT INTO `bulky`.`lead_catg_mediaarrivo` (`lcmafkaccount`, `lcmadescrizione`, `lcmafktipolead`, `lcmaorder`, `lcmavisibleuser`, `lcmavisiblecustomer`, `lcmaisweb`) 
VALUES ('1', 'Telefono', '3', '2', '1', '1', '0');



create table planning(
	plnid int not null primary key auto_increment,
	plnfkaccount int null,
	plndescr varchar(250) null,
	plnowner int null,
	plndtins datetime null,
	plndtmod datetime null,
	plnusrmod int null
);

alter table planning add plnfkzona int null;

create table plan_detail(
	pldid int not null primary key auto_increment,
	pldfkaccount int null,
	pldfkplannig int null,
	plddescr varchar(250) null,
	plddatefrom datetime null,
	plddateto datetime null,	
	plddtins datetime null,
	pldtmod datetime null,
	pldusrmod int null
);


CREATE TABLE act_status(
	asid int not null PRIMARY KEY auto_increment,
	asfkaccount int null,
	asfkcatgact int null,
	asdescr varchar(200) null,
	asdtmod datetime null,
	asdtins datetime null,
	asusermod int null
);
INSERT INTO act_status(asfkaccount,asfkcatgact,asdescr) VALUES(1,1,'OPEN');
INSERT INTO act_status(asfkaccount,asfkcatgact,asdescr) VALUES(1,1,'PROGRESS');
INSERT INTO act_status(asfkaccount,asfkcatgact,asdescr) VALUES(1,1,'CLOSE');

alter table act_booking add bfkplandetail int null;

