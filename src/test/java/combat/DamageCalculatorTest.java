
package combat;

import characters.Player;
import characters.Enemy;
import characters.EnemyType;
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

        @Override
        public long nextLong(long bound) {
            int value = values[index++];

            if (value < 0 || value >= bound) {
                throw new IllegalArgumentException("Invalid random value");
            }

            return value;
        }
    }

    @Test
    void performAttackRejectsNullArguments() {

        Player attacker = new Player("Player");
        Enemy target = new Enemy(EnemyType.STREET_DWELLER);

        assertThrows(IllegalArgumentException.class,
                () -> DamageCalculator.performAttack(
                        null, target, 10, 20, 0, 0,
                        new Random()));

        assertThrows(IllegalArgumentException.class,
                () -> DamageCalculator.performAttack(
                        attacker, null, 10, 20, 0, 0,
                        new Random()));

        assertThrows(IllegalArgumentException.class,
                () -> DamageCalculator.performAttack(
                        attacker, target, 10, 20, 0, 0,
                        null));

    }

    @Test
    void performAttackRejectsInvalidDamageRange() {

        Player attacker = new Player("Player");
        Enemy target = new Enemy(EnemyType.STREET_DWELLER);

        assertThrows(IllegalArgumentException.class,
                () -> DamageCalculator.performAttack(
                        attacker, target, -1, 20, 0, 0,
                        new Random()));

        assertThrows(IllegalArgumentException.class,
                () -> DamageCalculator.performAttack(
                        attacker, target, 20, 10, 0, 0,
                        new Random()));

    }

    @Test
    void performAttackRejectsInvalidChances() {

        Player attacker = new Player("Player");
        Enemy target = new Enemy(EnemyType.STREET_DWELLER);

        assertThrows(IllegalArgumentException.class,
                () -> DamageCalculator.performAttack(
                        attacker, target, 10, 20, -1, 0,
                        new Random()));

        assertThrows(IllegalArgumentException.class,
                () -> DamageCalculator.performAttack(
                        attacker, target, 10, 20, 101, 0,
                        new Random()));

        assertThrows(IllegalArgumentException.class,
                () -> DamageCalculator.performAttack(
                        attacker, target, 10, 20, 0, 101,
                        new Random()));

    }

    @Test
    void performAttackDealsCorrectDamage() {

        Player attacker = new Player("Player", 100, 0);
        Enemy target = new Enemy("Enemy", 100, 0, EnemyType.STREET_DWELLER);

        DamageCalculator.performAttack(
                attacker, target, 10, 20, 0, 0,
                new SequenceRandom(0, 0, 99)
        );

        assertEquals(90, target.getHealth());
        assertEquals(100, attacker.getHealth());

    }

    @Test
    void performAttackCanDealCriticalDamage() {

        Player attacker = new Player("Player", 100, 0);
        Enemy target = new Enemy("Enemy", 100, 0, EnemyType.STREET_DWELLER);

        DamageCalculator.performAttack(
                attacker, target, 10, 20, 0, 0,
                new SequenceRandom(0, 0, 0)
        );

        assertEquals(85, target.getHealth());

    }

    @Test
    void performAttackCanMiss() {

        Player attacker = new Player("Player", 100, 0);
        Enemy target = new Enemy("Enemy", 100, 0, EnemyType.STREET_DWELLER);

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
        Enemy target = new Enemy("Enemy", 100, 0, EnemyType.STREET_DWELLER);

        DamageCalculator.performAttack(
                attacker, target, 10, 20, 0, 100,
                new SequenceRandom(0, 0, 99, 99)
        );

        assertEquals(90, target.getHealth());
        assertEquals(95, attacker.getHealth());

    }

    @Test
    void performAttackCanCauseCriticalAndBacklash() {

        Player attacker = new Player("Player", 100, 0);
        Enemy target = new Enemy("Enemy", 100, 0, EnemyType.STREET_DWELLER);

        DamageCalculator.performAttack(
                attacker, target, 10, 20, 0, 100,
                new SequenceRandom(0, 0, 0, 99)
        );

        assertEquals(85, target.getHealth());
        assertEquals(92, attacker.getHealth());

    }

    @Test
    void performAttackRespectsTargetDefense() {

        Player attacker = new Player("Player", 100, 0);
        Enemy target = new Enemy("Enemy", 100, 5, EnemyType.STREET_DWELLER);

        DamageCalculator.performAttack(
                attacker, target, 10, 20, 0, 0,
                new SequenceRandom(0, 0, 99)
        );

        assertEquals(95, target.getHealth());

    }

    @Test
    void performAttackDoesNotCauseBacklashWhenBacklashChanceIsZero() {

        Player attacker = new Player("Player", 100, 0);
        Enemy target = new Enemy("Enemy", 100, 0, EnemyType.STREET_DWELLER);

        DamageCalculator.performAttack(
                attacker, target, 10, 20, 0, 0,
                new SequenceRandom(0, 0, 99)
        );

        assertEquals(100, attacker.getHealth());

    }

    @Test
    void performAttackCanDealMinimumDamage() {

        Player attacker = new Player("Player", 100, 0);
        Enemy target = new Enemy("Enemy", 100, 0, EnemyType.STREET_DWELLER);

        DamageCalculator.performAttack(
                attacker, target, 10, 20, 0, 0,
                new SequenceRandom(0, 0, 99)
        );

        assertEquals(90, target.getHealth());

    }

    @Test
    void performAttackCanDealMaximumDamage() {

        Player attacker = new Player("Player", 100, 0);
        Enemy target = new Enemy("Enemy", 100, 0, EnemyType.STREET_DWELLER);

        DamageCalculator.performAttack(
                attacker, target, 10, 20, 0, 0,
                new SequenceRandom(0, 10, 99)
        );

        assertEquals(80, target.getHealth());

    }

    @Test
    void missChancePenaltyCanCauseMeleeAttackToMiss() {

        Player attacker = new Player("Player", 100, 0);
        Enemy target = new Enemy("Enemy", 100, 0, EnemyType.STREET_DWELLER);

        attacker.applyMissChancePenalty(25);

        MeleeAttack attack = new MeleeAttack(
                "Test Attack",
                10, 20,
                5, 0,
                new SequenceRandom(29)
        );

        attack.attack(attacker, target);
        assertEquals(100, target.getHealth());

    }

    @Test
    void missChancePenaltyCanCauseRangedAttackToMiss() {

        Player attacker = new Player("Player", 100, 0);
        Enemy target = new Enemy("Enemy", 100, 0, EnemyType.STREET_DWELLER);

        attacker.applyMissChancePenalty(25);

        RangedAttack attack = new RangedAttack(
                "Test Attack",
                10, 20,
                15, 0,
                new SequenceRandom(39)
        );

        attack.attack(attacker, target);
        assertEquals(100, target.getHealth());

    }

    @Test
    void missedAttackDoesNotCauseBacklash() {

        Player attacker = new Player("Player", 100, 0);
        Enemy target = new Enemy("Enemy", 100, 0, EnemyType.STREET_DWELLER);

        DamageCalculator.performAttack(
                attacker,
                target,
                10,
                20,
                100,
                100,
                new SequenceRandom(0)
        );

        assertEquals(100, target.getHealth());
        assertEquals(100, attacker.getHealth());

    }
}
