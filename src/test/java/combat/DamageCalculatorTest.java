
package combat;

import characters.Player;
import characters.Enemy;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class DamageCalculatorTest {

    private static class SequenceRandom extends Random {

        private final int[] values;
        private int index = 0;

        SequenceRandom(int... values) {
            this.values = values;
        }

        @Override
        public int nextInt(int bound) {
            int value = values[index++];

            if (value < 0 || value >= bound) {
                throw new IllegalArgumentException("Invalid random value");
            }

            return value;
        }
    }

    @Test
    void performAttackDealsCorrectDamage() {
        Player attacker = new Player("Player", 100, 0);
        Enemy target = new Enemy("Enemy", 100, 0);

        DamageCalculator.performAttack(
                attacker, target, 10, 20, 0, 0,
                new SequenceRandom(0, 99)
        );

        assertEquals(90, target.getHealth());
        assertEquals(100, attacker.getHealth());
    }

    @Test
    void performAttackCanDealCriticalDamage() {
        Player attacker = new Player("Player", 100, 0);
        Enemy target = new Enemy("Enemy", 100, 0);

        DamageCalculator.performAttack(
                attacker, target, 10, 20, 0, 0,
                new SequenceRandom(0, 0)
        );

        assertEquals(85, target.getHealth());
    }

    @Test
    void performAttackCanMiss() {
        Player attacker = new Player("Player", 100, 0);
        Enemy target = new Enemy("Enemy", 100, 0);

        DamageCalculator.performAttack(
                attacker, target, 10, 20, 50, 0,
                new SequenceRandom(0)
        );

        assertEquals(100, target.getHealth());
        assertEquals(100, attacker.getHealth());
    }

    @Test
    void performAttackCanCauseBacklash() {
        Player attacker = new Player("Player", 100, 0);
        Enemy target = new Enemy("Enemy", 100, 0);

        DamageCalculator.performAttack(
                attacker, target, 10, 20, 0, 100,
                new SequenceRandom(0, 99, 99)
        );

        assertEquals(90, target.getHealth());
        assertEquals(95, attacker.getHealth());
    }

    @Test
    void performAttackCanCauseCriticalAndBacklash() {
        Player attacker = new Player("Player", 100, 0);
        Enemy target = new Enemy("Enemy", 100, 0);

        DamageCalculator.performAttack(
                attacker, target, 10, 20, 0, 100,
                new SequenceRandom(0, 0, 99)
        );

        assertEquals(85, target.getHealth());
        assertEquals(92, attacker.getHealth());
    }

    @Test
    void performAttackRespectsTargetDefense() {
        Player attacker = new Player("Player", 100, 0);
        Enemy target = new Enemy("Enemy", 100, 5);

        DamageCalculator.performAttack(
                attacker, target, 10, 20, 0, 0,
                new SequenceRandom(0, 99)
        );

        assertEquals(95, target.getHealth());
    }

    @Test
    void performAttackDoesNotCauseBacklashWhenBacklashChanceIsZero() {
        Player attacker = new Player("Player", 100, 0);
        Enemy target = new Enemy("Enemy", 100, 0);

        DamageCalculator.performAttack(
                attacker, target, 10, 20, 0, 0,
                new SequenceRandom(0, 99)
        );

        assertEquals(100, attacker.getHealth());
    }

    @Test
    void performAttackCanDealMinimumDamage() {
        Player attacker = new Player("Player", 100, 0);
        Enemy target = new Enemy("Enemy", 100, 0);

        DamageCalculator.performAttack(
                attacker, target, 10, 20, 0, 0,
                new SequenceRandom(0, 99)
        );

        assertEquals(90, target.getHealth());
    }

    @Test
    void performAttackCanDealMaximumDamage() {
        Player attacker = new Player("Player", 100, 0);
        Enemy target = new Enemy("Enemy", 100, 0);

        DamageCalculator.performAttack(
                attacker, target, 10, 20, 0, 0,
                new SequenceRandom(10, 99)
        );

        assertEquals(80, target.getHealth());
    }
}
