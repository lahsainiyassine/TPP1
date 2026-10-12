package presentation;

import dao.DaoImpl;
import metier.MetierImpl;

/**
 * Démonstration de l'injection statique des dépendances (couplage fort lors de l'instanciation).
 */
public class Presentation {

    public static void main(String[] args) {
        /*
         * Injection de dépendances par instanciation statique :
         * Utilisation de l'opérateur new.
         */
        DaoImpl dao = new DaoImpl();
        MetierImpl metier = new MetierImpl();
        metier.setDao(dao);

        System.out.println("[PRESENTATION STATIQUE] Résultat : " + metier.calcul());
    }
}
