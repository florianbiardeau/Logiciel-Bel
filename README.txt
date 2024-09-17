--------------IMPORTANT--------------

L'application utilise des scripts Python et une base de données, il est donc impératif de mettre à jour les bibliothèques Python
et de mettre en place la base de données.

Il se peut que certains fichiers JAR (javafx.fxml.jar, javafx.base.jar, javafx.controls.jar, javafx.graphics.jar, javafx.swing.jar) 
doivent être remplacés afin que les chemins correspondent à la machine de l'utilisateur.

Il se peut aussi que les arguments de la machine virtuelle (VM) dans la configuration de l'exécution d'Eclipse aient besoin d'être réinitialisés.
--module-path "C:\Program Files\javafx-sdk\lib" --add-modules javafx.controls,javafx.fxml,javafx.swing

----------------- Installer/Mettre à jour Python 3 -------------------

/// COMMANDES À EXÉCUTER DANS UN SHELL

- pip install --upgrade pip
- pip install opencv-python
- python -m pip install --upgrade Pillow
- python -m pip install -U scikit-image
- python -m pip install -U matplotlib
- pip install pandas

----------------- Configurer la base de données ---------------------

- Ouvrir un serveur local, par exemple sous XAMPP.
- Ouvrir PhpMyAdmin et créer une nouvelle base de données.
- Nommer la "beldb" en utf8mb4_general_ci.
- Importer le fichier SQL (cela crée toutes les tables nécessaires à la base de données).

Une fois toutes ces étapes faites, il est maintenant possible de lancer l'application sans risque.