CREATE TABLE operateur(
   idOperateur int AUTO_INCREMENT,
   nomOperateur VARCHAR(20) NOT NULL,
   PRIMARY KEY(idOperateur)
);

CREATE TABLE produit(
   idProduit int AUTO_INCREMENT,
   nomProduit VARCHAR(30) NOT NULL,
   PRIMARY KEY(idProduit)
);

CREATE TABLE image(
   idImage int AUTO_INCREMENT,
   nomImage VARCHAR(100) NOT NULL,
   largeurPx DECIMAL(8,2) NOT NULL,
   hauteurPx DECIMAL(8,2) NOT NULL,
   grossissement DECIMAL(6,2) NOT NULL,
   largeurReelle DECIMAL(6,2) NOT NULL,
   idOperateur INT NOT NULL,
   idProduit INT NOT NULL,
   PRIMARY KEY(idImage),
   FOREIGN KEY(idOperateur) REFERENCES operateur(idOperateur),
   FOREIGN KEY(idProduit) REFERENCES produit(idProduit)
);

CREATE TABLE particule(
   idParticule int AUTO_INCREMENT,
   surfaceParticulePx INT NOT NULL,
   coCoinHautGaucheX INT NOT NULL,
   coCoinHautGaucheY INT NOT NULL,
   coCoinBasDroitX INT NOT NULL,
   coCoinBasDroitY INT NOT NULL,
   coCentreX DOUBLE NOT NULL,
   coCentreY DOUBLE NOT NULL,
   orientation DOUBLE NOT NULL,
   longueurAxeMajeur DOUBLE NOT NULL,
   longueurAxeMineur DOUBLE NOT NULL,
   diametreEquivalent DOUBLE NOT NULL,
   idImage INT NOT NULL,
   PRIMARY KEY(idParticule),
   FOREIGN KEY(idImage) REFERENCES image(idImage)
);

CREATE TABLE statistique(
   idStatistique int AUTO_INCREMENT,
   nbParticulesTrouvees INT NOT NULL,
   ratioSurfaceCouverte DOUBLE NOT NULL,
   moyenneAires DOUBLE NOT NULL,
   moyenneDiametresEquivalents DOUBLE NOT NULL,
   ecartTypeAires DOUBLE NOT NULL,
   ecartTypeDiametreEquivalent DOUBLE NOT NULL,
   idImage INT NOT NULL,
   PRIMARY KEY(idStatistique),
   UNIQUE(idImage),
   FOREIGN KEY(idImage) REFERENCES image(idImage)
); 
