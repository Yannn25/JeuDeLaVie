# JeuDeLaVie Et Automates Cellulaires
Yann EDIKEU
Ce projet implémente le célèbre "Jeu de la Vie" ainsi que d'autres automates cellulaires. L'objectif est de simuler l'évolution des cellules sur une grille en fonction de règles prédéfinies.

# Compilation
Pour compiler et exécuter le projet, suivez les étapes suivantes :
1.Placez-vous dans le répertoire "demo".
2.Lancez les commandes suivantes :
    ./gradlew init
    ./gradlew build
    ./gradlew run
# Choix techniques
Modélisation : J'ai choisi de modéliser le jeu de la vie et les automates cellulaires en utilisant des classes telles que "Grille", "Cellule" et "Regles" pour représenter les entités du jeu. Cela permet une structure claire et modulaire, facilitant la manipulation et l'évolution de d'automate cellulaire varié.
J'ai utilisé le patron de conception "Builder" pour la création d'automates cellulaires avec des règles spécifiques. Le Builder permet de définir étape par étape la configuration de l'automate cellulaire en fournissant les règles de naissance et de survie personnalisées.

En utilisant le Builder, l'utilisateur peut spécifier les seuils de naissance et de survie en appelant les méthodes appropriées. Cela offre une flexibilité accrue pour créer des automates cellulaires avec des comportements spécifiques en fonction des règles définies.

Le Builder permet également de garantir que l'automate cellulaire est créé de manière cohérente, en s'assurant que toutes les informations nécessaires sont fournies avant la construction finale de l'objet.
# Implémentation effectuée
Le projet comprend les fonctionnalités suivantes :

Le "Jeu de la Vie" est entièrement implémenté, permettant de simuler l'évolution des cellules selon les règles spécifiques du jeu. Une consultation de chaque étape est fournie pour observer les changements.
Les automates cellulaires peuvent prendre différentes règles prédéfinies ou définies par l'utilisateur. Vous avez la possibilité de personnaliser les règles pour chaque automate.
L'implémentation actuelle des automates cellulaires se limite à des cellules ayant uniquement deux états : vivant ou mort.

# Extensions réalisées
Dans le cadre de ce projet, les extensions suivantes ont été mises en place :

Possibilité de créer une grille à partir du terminal : Vous pouvez spécifier la taille de la grille et son état initial en utilisant les commandes fournies par l'interface utilisateur.
Possibilité de configurer des règles spécifiques : Vous pouvez spécifier les règles qui seront utiliser lors des phases du jeu, en choisissant le voisinage et comment sera réguler la naissance et la survie.
Lecture des fichiers .cells

# Piste de réflexion pour ce qui n'a pas été implémenté
- Automate cellulaire a plusieurs états
L'automate cellulaire actuellement implémenté dans le projet prend en charge des cellules ayant uniquement deux états : vivant ou mort. Cependant, il est possible d'élargir cette fonctionnalité pour permettre à chaque cellule de prendre plusieurs "phase/états" distincts.

Une approche pour gérer les différents états des cellules consiste à utiliser une structure de données telle qu'une Map ou un dictionnaire. Cette structure permet de mapper chaque état possible d'une cellule à une valeur booléenne, indiquant si pour la clé(phase) représente un état vivant ou mort. Tandis que les cellules, elles auraient maintenant un état (vivant ou mort) comme précédemment en plus d'une "phase" qui serait affiché sur la grille.

- Parallélisation des calculs
Une des pistes pour améliorer les performances du projet consiste à paralléliser les calculs de simulation sur plusieurs threads. Cela permettrait d'accélérer le traitement des grandes grilles et d'améliorer l'efficacité de l'exécution.

Pour faciliter la parallélisation, on pourrait ajouter des méthodes à la classe Grille permettant d'itérer sur une partie spécifique de la grille. Par exemple, vous pouvez ajouter une méthode obtenirIterateursZone() qui renvoie une liste d'itérateurs de cellules correspondant aux différentes zones de la grille. Chaque thread peut alors traiter une zone spécifique de manière indépendante.

Une autre piste documentée sur Wikipédia concernant L'algorithme Hashlife, conçu par Bill Gosper, qui utilise une table de hachage pour mémoriser les configurations locales du jeu de la vie et accélérer les calculs. Il permet de "tourner" des parties isolées de l'espace du jeu et de sauter des générations en utilisant les résultats précalculés. La parallélisation peut être introduite en répartissant les calculs sur plusieurs threads, ce qui améliore les performances de l'automate cellulaire. On pourrait adapter cela à l'aide d'un pool de thread.

# Plusieurs extensions d'automates cellulaires peuvent être envisagées pour améliorer le projet :
- Automates cellulaires réversibles : Explorer la possibilité d'implémenter des automates cellulaires réversibles, où l'état précédent peut être déterminé à partir de l'état actuel.
- Fourmi de Langton : Ajoutez la possibilité de simuler le comportement de la "Fourmi de Langton", un automate cellulaire célèbre où une fourmi se déplace sur une grille en fonction de certaines règles.



