# DownFall
DawnFall est un jeu multijoueur local à 2 joueurs. On y joue une licorne présent dans une zone avec des platforms qui descendent en permanence.
Le but est de ne pas tomber en dehors de notre zone. Le joueur à la possibilité de lancer des cupcakes sur l'autre joueur pour essayer
le faire tomber.

## installation
Pour utiliser ce projet nous conseillons d'utiliser intelliJ
- il suffit de ouvrir le dossier DownFall avec intelliJ pour qu'il comprenne que c'est un projet maven
- il faut autorisier l'import automatique des module maven
- toutes les ressources sont comprise dans le projet. Pour que intelliJ les considère comme tel il faut faire un clic droit sur le dossier 'Resources'-> 'mark Directory as' et séléctionner 'Resources Root'
- 

## Lancement
Pour lancer le jeu il vous suffit de lancer la class 'MainGame'
la configuration pour le run doit avoir les options comme suis:
- Main Class: ch.heigv.cc.downfall.MainGame
- Use classpath of module: DownFall

## Tests Unitaires
les différentes class test sont dans le dossier test '\src\test\java\ch\heigvd\cc\downfall'
les test doivent être lancer à travers intelliJ, chaque classe de test peut-être lancer indépendamment

## Technologies
language: java version: 1.8
IDE: intelliJ
Type projet: Maven

## TroubleShooting
Si le projet n'est pas bien structuré à la première ouverture, ceci peut être normal. intelliJ prends quelques secondes avant de correctement créer la structure du projet. Si après 2 minutes le projet n'est toujours pas correctement structurez assurez-vous d'avoir bien autorisé l'import automatique des plugin maven. En dernier recours vous pouvez essayer de relancer intelliJ.
