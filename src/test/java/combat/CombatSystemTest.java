
package combat;

import characters.Player;
import characters.Enemy;
import org.junit.jupiter.api.Test;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class CombatSystemTest {

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
    void playerStartsFirst() {
        Player player = new Player("Player", 100, 10);
        Enemy enemy = new Enemy("Enemy", 50, 5);

        CombatSystem combat = new CombatSystem(player, enemy);

        assertTrue(combat.isPlayerTurn());
    }

    @Test
    void playerCannotAttackTwiceInARow() {
        Player player = new Player("Player", 100, 10);
        Enemy enemy = new Enemy("Enemy", 50, 5);

        CombatSystem combat = new CombatSystem(player, enemy);

        combat.playerAttack(new MeleeAttack());

        assertThrows(IllegalStateException.class,
                () -> combat.playerAttack(new MeleeAttack()));
    }

    @Test
    void enemyCannotAttackTwiceInARow() {
        Player player = new Player("Player", 100, 10);
        Enemy enemy = new Enemy("Enemy", 50, 5);

        CombatSystem combat = new CombatSystem(player, enemy);

        combat.playerAttack(new MeleeAttack());
        combat.enemyAttack(new MeleeAttack());

        assertThrows(IllegalStateException.class,
                () -> combat.enemyAttack(new MeleeAttack()));
    }

    @Test
    void turnsAlternateBetweenPlayerAndEnemy() {
        Player player = new Player("Player", 100, 10);
        Enemy enemy = new Enemy("Enemy", 50, 5);

        CombatSystem combat = new CombatSystem(player, enemy);

        assertTrue(combat.isPlayerTurn());

        combat.playerAttack(new MeleeAttack());
        assertFalse(combat.isPlayerTurn());

        combat.enemyAttack(new MeleeAttack());
        assertTrue(combat.isPlayerTurn());
    }

    @Test
    void combatEndsWhenEnemyIsDefeated() {
        Player player = new Player("Player", 100, 10);
        Enemy enemy = new Enemy("Enemy", 10, 0);

        CombatSystem combat = new CombatSystem(player, enemy);

        combat.playerAttack(new MeleeAttack(
                new SequenceRandom(99, 0, 99)
        ));

        assertTrue(combat.hasCombatEnded());
    }

    @Test
    void combatEndsWhenPlayerIsDefeated() {
        Player player = new Player("Player", 10, 0);
        Enemy enemy = new Enemy("Enemy", 100, 10);

        CombatSystem combat = new CombatSystem(player, enemy);

        combat.playerAttack(new MeleeAttack(
                new SequenceRandom(99, 0, 99)
        ));

        combat.enemyAttack(new MeleeAttack(
                new SequenceRandom(99, 0, 99)
        ));

        assertTrue(combat.hasCombatEnded());
    }

    @Test
    void turnDoesNotChangeWhenCombatEnds() {
        Player player = new Player("Player", 100, 0);
        Enemy enemy = new Enemy("Enemy", 10, 0);

        CombatSystem combat = new CombatSystem(player, enemy);

        combat.playerAttack(new SpecialAttack(
                new SequenceRandom(0, 99, 99)
        ));

        assertTrue(combat.isPlayerTurn());
        assertTrue(combat.hasCombatEnded());
    }

    @Test
    void combatContinuesWhileBothCharactersAreAlive() {
        Player player = new Player("Player", 100, 10);
        Enemy enemy = new Enemy("Enemy", 50, 5);

        CombatSystem combat = new CombatSystem(player, enemy);

        assertFalse(combat.hasCombatEnded());
    }

    @Test
    void defeatedCharacterCannotAttack() {
        Player player = new Player("Player", 100, 10);
        Enemy enemy = new Enemy("Enemy", 10, 0);

        CombatSystem combat = new CombatSystem(player, enemy);

        combat.playerAttack(new SpecialAttack(
                new SequenceRandom(0, 99, 99)
        ));

        assertThrows(IllegalStateException.class,
                () -> combat.enemyAttack(new MeleeAttack()));

        assertEquals(100, player.getHealth());
    }

    @Test
    void enemyCannotAttackAfterPlayerIsDefeated() {
        Player player = new Player("Player", 10, 0);
        Enemy enemy = new Enemy("Enemy", 100, 0);

        CombatSystem combat = new CombatSystem(player, enemy);

        combat.playerAttack(new MeleeAttack(
                new SequenceRandom(99, 0, 99)
        ));

        combat.enemyAttack(new SpecialAttack(
                new SequenceRandom(15, 0, 0)
        ));

        assertThrows(IllegalStateException.class,
                () -> combat.enemyAttack(new MeleeAttack()));
    }

    @Test
    void playerCannotAttackAfterEnemyIsDefeated() {
        Player player = new Player("Player", 100, 0);
        Enemy enemy = new Enemy("Enemy", 10, 0);

        CombatSystem combat = new CombatSystem(player, enemy);

        combat.playerAttack(new MeleeAttack(
                new SequenceRandom(99, 0, 99)
        ));

        assertThrows(IllegalStateException.class,
                () -> combat.playerAttack(new MeleeAttack()));
    }

    @Test
    void constructorRejectsNullPlayerOrEnemy() {
        Enemy enemy = new Enemy("Enemy", 50, 5);
        Player player = new Player("Player", 100, 10);

        assertThrows(IllegalArgumentException.class,
                () -> new CombatSystem(null, enemy));

        assertThrows(IllegalArgumentException.class,
                () -> new CombatSystem(player, null));
    }

    @Test
    void attacksRejectNullStrategy() {
        Player player = new Player("Player", 100, 10);
        Enemy enemy = new Enemy("Enemy", 50, 5);

        CombatSystem combat = new CombatSystem(player, enemy);

        assertThrows(IllegalArgumentException.class,
                () -> combat.playerAttack(null));

        assertThrows(IllegalArgumentException.class,
                () -> combat.enemyAttack(null));
    }
}
