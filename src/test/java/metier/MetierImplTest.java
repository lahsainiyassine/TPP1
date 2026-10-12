package metier;

import dao.IDao;
import org.junit.Assert;
import org.junit.Test;

public class MetierImplTest {

    @Test
    public void testCalcul() {
        // Mock ou implémentation factice pour le test unitaire
        IDao fakeDao = new IDao() {
            @Override
            public double getValue() {
                return 50.0;
            }
        };

        MetierImpl metier = new MetierImpl();
        metier.setDao(fakeDao);

        double expected = 100.0;
        double actual = metier.calcul();

        Assert.assertEquals(expected, actual, 0.0001);
    }

    @Test(expected = IllegalStateException.class)
    public void testCalculWithoutDaoThrowsException() {
        MetierImpl metier = new MetierImpl();
        metier.calcul();
    }
}
