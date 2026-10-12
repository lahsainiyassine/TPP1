
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
<img width="1307" height="297" alt="image" src="https://github.com/user-attachments/assets/7b38c223-3924-4882-a2fa-12f6b9d4be56" />
<img width="1151" height="388" alt="image" src="https://github.com/user-attachments/assets/1fb69147-812a-4db4-ba71-387c4b38a26b" />

