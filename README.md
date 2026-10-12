
├── src/main/java/
│   ├── dao/
│   │   ├── IDao.java          # Contrat d'accès aux données
│   │   └── DaoImpl.java       # Implémentation concrète (retourne 100.0)
│   ├── metier/
│   │   ├── IMetier.java       # Contrat métier
│   │   └── MetierImpl.java    # Logique de calcul (dépend uniquement de IDao)
│   └── presentation/
│       ├── Presentation.java  # Version 1 : instanciation statique (couplage fort)
│       └── Presentation2.java # Version 2 : mini-conteneur IoC dynamique
├── config.txt                 # Noms complets des classes à charger
└── pom.xml
Cette trace valide le bon fonctionnement du conteneur IoC:   Chargement dynamique : DaoImpl et MetierImplont été instanciés par réflexion à partir de config.txt, sans aucun opérateur newdans le code.   Injection réussie : Le DAO a bien été injecté dans la couche Métier via la méthodesetDao.   Calcul validé : La méthode calcul()a récupéré la valeur du DAO ($100.0$) et renvoyé le résultat attendu : 200.0 ($100.0 \times 2$).   Succès ( exit code 0) : Le programme s'est terminé sans exception ( ClassNotFoundExceptionou NullPointerException) : le principe du couplage faible est respecté. 
<img width="1307" height="297" alt="image" src="https://github.com/user-attachments/assets/7b38c223-3924-4882-a2fa-12f6b9d4be56" />
Cette capture valide la testabilité unitaire de la couche métier viaMetierImplTest:   Tests réussis : Les 2 tests unitaires ( testCalculet testCalculWit...) passent avec succès ( 2 tests passed, 0 échec).   Couplage faible prouvé : La classe MetierImpla pu être testée de manière isolée en lui injectant une dépendance de test/mock (donnant ici le résultat attendu de 100.0 ) sans dépendre de la vraie base de données.   Validation de l'architecture : Cela prouve l'intérêt majeur de l'injection par setter : isoler parfaitement la logique métier pour vérifier son comportement par assertions.Succès global ( exit code 0) : Aucune exception levée et toutes les assertions du test sont conformes.
<img width="1151" height="388" alt="image" src="https://github.com/user-attachments/assets/1fb69147-812a-4db4-ba71-387c4b38a26b" />
## Configuration de la classe Presentation2
L'injection dynamique est pilotée par le fichier externe `config.txt` situé à la racine :
- **Ligne 1 :** `dao.DaoImpl` (implémentation de `IDao`)
- **Ligne 2 :** `metier.MetierImpl` (implémentation de `IMetier`)

La classe `Presentation2` lit ces deux lignes avec un `Scanner`, charge dynamiquement les classes via `Class.forName()`, les instancie avec `getDeclaredConstructor().newInstance()`, puis injecte la dépendance en invoquant la méthode `setDao` par réflexion via `Method.invoke()`.

## Preuves d'Exécution

### 1. Exécution de Presentation2
<!-- Glissez-déposez ici votre capture console (Résultats = 200.0) -->

### 2. Validation des Tests Unitaires
<!-- Glissez-déposez ici votre capture des tests réussis (2 tests passed) -->

