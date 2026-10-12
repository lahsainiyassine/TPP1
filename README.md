Dans ce premier TP, l'objectif principal était de comprendre concrètement ce qui se passe sous le capot d'un conteneur d'inversion de contrôle (IoC) comme celui de Spring, en recréant un moteur d'injection de dépendances uniquement avec le mécanisme de réflexion de Java.

Le point de départ part d'un constat classique en conception orientée objet : dès qu'une classe instancie elle-même ses dépendances avec l'opérateur new, on crée un couplage fort. Si notre logique métier instancie directement une classe d'accès aux données comme DaoImpl, le code devient rigide. Impossible de changer la source de données sans réécrire le code source et le recompilateur, et très difficile de tester le métier logique de manière isolée avec un mock.

Pour résoudre ce problème, nous avons appliqué le principe d'inversion des dépendances (le D de SOLID). L'idée est simple : la classe métier ne doit connaître qu'une interface (IDao) et ignorer totalement qui implémente cette interface ni comment les données sont récupérées.

Le projet est découpé en plusieurs éléments simples :

Une interface IDao avec une méthode getValue(), et son implémentation concrète DaoImpl qui renvoie une valeur fixe (100.0).

Une interface IMetier avec une méthode calcul(), et son implémentation MetierImpl qui possède une référence privée vers IDao, un constructeur sans paramètre et un setter setDao(IDao dao). La méthode calcul() appelle la méthode du DAO et multiplie le résultat par deux.

Un fichier texte externe, config.txt, qui contient simplement les deux noms complets des classes à charger : dao.DaoImpl sur la première ligne, puis metier.MetierImpl sur la deuxième.

Tout l'intérêt du TP repose sur la classe Présentation2, qui joue le rôle de mini-conteneur IoC :

Elle commence par lire le fichier config.txt avec un Scanner pour extraire le nom des classes.

Elle charge ces classes en mémoire dynamiquement à l'aide de Class.forName().

Elle crée une instance de chaque classe via leur constructeur par défaut avec getDeclaredConstructor().newInstance().

Elle effectue l'injection de dépendance à chaud : elle recherche dynamiquement la méthode setDao de la classe métier grâce à getMethod(), puis l'exécute avec Ensure() en lui passant l'instance du DAO.

Enfin, elle exécute le métier logique en passant par l'interface IMetier et affiche le résultat final (200.0).

Ce mécanisme montre bien la différence entre l'approche statique et l'approche dynamique. En statique, le compilateur vérifie tout, mais le code est verrouillé. En dynamique, le code devient totalement ouvert à l'extension et fermé à la modification : si demain on crée une classe DaoImplV2 ou un DAO de test, il suffit de changer une ligne dans config.txt sans recompiler une seule ligne de code métier.

Ce travail permet aussi de comprendre pourquoi cette gymnastique par réflexion est cantonnée à la phase de démarrage (bootstrapping) de l'application : inspecter les métadonnées et invoquer des méthodes par réflexion demandant plus de ressources CPU que des appels directs. C'est exactement ce que font les frameworks modernes au lancement pour assembler les briques du projet avant de laisser l'application tourner à pleine vitesse.
<img width="1307" height="297" alt="image" src="https://github.com/user-attachments/assets/7b38c223-3924-4882-a2fa-12f6b9d4be56" />
