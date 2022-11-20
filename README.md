# PC_persistence

## Mode d'emploi

Pour grouper des figures, cocher la case "Group mode", puis cliquer sur toutes les figures à grouper. Une fois terminé, décocher la case "Group mode". Toutes les figures groupées seront mises en évidence par un contour coloré.

Pour dégrouper des figures, cocher la case "Disassemble group", puis cliquer sur toutes les figures à dégrouper. Une fois terminé, décocher la case "Disassemble group".

Le bouton "Undo" annule la dernière action faite. Ces actions comprennent:
- création d'une figure
- déplacement d'une figure
- groupage ou dégroupage d'une figure

Le bouton "Redo" annule la dernière commande "Undo" faite

Le bouton "Reset" remet la zone de dessin à zéro, sans possibilité de revenir en arrière (pas de Undo possible".

## Tests

En ce qui concerne les tests, nous avons fait le choix d'ignorer dans sonar les fichiers JDrawingFrame, App, GUIHelper et Drawer.
Toutes ces classes étant dédiées à l'affichage de l'application et aux dessins, il aurait fallu des tests plus adaptés comme des test d'IHM. De plus, ils ne concernent pas la partie métier de l'application, il n'apparaît donc pas pertinent de les tester.

Ainsi, notre coverage couvre l'intégralité de l'application hors partie graphique.

## Points importants

Nous avons supprimé l'utilisation du patron Visiteur, car avec nos modifications des gestions d'import/export, il n'avait plus aucune utilité.

Les DC et DS se trouvent à la racine du projet.
