
package combat;

import characters.Player;
import characters.Enemy;
import characters.EnemyType;
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
    void playerStartsFirst() {

        Player player = new Player("Player", 100, 10);
        Enemy enemy = new Enemy("Enemy", 50, 5, EnemyType.STREET_DWELLER);

        CombatSystem combat = new CombatSystem(player, enemy);

        assertTrue(combat.isPlayerTurn());

    }

    @Test
    void playerCannotAttackTwiceInARow() {

        Player player = new Player("Player", 100, 10);
        Enemy enemy = new Enemy("Enemy", 50, 5, EnemyType.STREET_DWELLER);

        CombatSystem combat = new CombatSystem(player, enemy);

        combat.playerAttack(new MeleeAttack());
        assertThrows(IllegalStateException.class,
                () -> combat.playerAttack(new MeleeAttack()));

    }

    @Test
    void enemyCannotAttackTwiceInARow() {

        Player player = new Player("Player", 100, 10);
        Enemy enemy = new Enemy("Enemy", 50, 5, EnemyType.STREET_DWELLER);

        CombatSystem combat = new CombatSystem(player, enemy);

        combat.playerAttack(new MeleeAttack());
        combat.enemyAttack(new MeleeAttack());
        assertThrows(IllegalStateException.class,
                () -> combat.enemyAttack(new MeleeAttack()));

    }

    @Test
    void turnsAlternateBetweenPlayerAndEnemy() {

        Player player = new Player("Player", 100, 10);
        Enemy enemy = new Enemy("Enemy", 50, 5, EnemyType.STREET_DWELLER);

        CombatSystem combat = new CombatSystem(player, enemy);

        assertTrue(combat.isPlayerTurn());
        combat.playerAttack(new MeleeAttack());
        assertFalse(combat.isPlayerTurn());
        combat.enemyAttack(new MeleeAttack());
        assertTrue(combat.isPlayerTurn());

    }

    @Test
    void missedAttackStillConsumesPlayerTurn() {

        Player player = new Player("Player", 100, 0);
        Enemy enemy = new Enemy("Enemy", 100, 0, EnemyType.STREET_DWELLER);

        CombatSystem combat = new CombatSystem(player, enemy);

        combat.playerAttack(new MeleeAttack(
                new SequenceRandom(0)
        ));
        assertFalse(combat.isPlayerTurn());

    }

    @Test
    void combatEndsWhenEnemyIsDefeated() {

        Player player = new Player("Player", 100, 10);
        Enemy enemy = new Enemy("Enemy", 10, 0, EnemyType.STREET_DWELLER);

        CombatSystem combat = new CombatSystem(player, enemy);

        combat.playerAttack(new MeleeAttack(
                new SequenceRandom(99, 0, 99)
        ));
        assertTrue(combat.hasCombatEnded());

    }

    @Test
    void combatEndsWhenPlayerIsDefeated() {

        Player player = new Player("Player", 10, 0);
        Enemy enemy = new Enemy("Enemy", 100, 10, EnemyType.STREET_DWELLER);

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
        Enemy enemy = new Enemy("Enemy", 10, 0, EnemyType.STREET_DWELLER);

        CombatSystem combat = new CombatSystem(player, enemy);

        combat.playerAttack(new SpecialAttack(
                new SequenceRandom(0, 0, 99, 99)
        ));
        assertTrue(combat.isPlayerTurn());
        assertTrue(combat.hasCombatEnded());

    }

    @Test
    void combatContinuesWhileBothCharactersAreAlive() {

        Player player = new Player("Player", 100, 10);
        Enemy enemy = new Enemy("Enemy", 50, 5, EnemyType.STREET_DWELLER);

        CombatSystem combat = new CombatSystem(player, enemy);

        assertFalse(combat.hasCombatEnded());

    }

    @Test
    void defeatedCharacterCannotAttack() {

        Player player = new Player("Player", 100, 10);
        Enemy enemy = new Enemy("Enemy", 10, 0, EnemyType.STREET_DWELLER);

        CombatSystem combat = new CombatSystem(player, enemy);

        combat.playerAttack(new SpecialAttack(
                new SequenceRandom(0, 0, 99, 99)
        ));
        assertThrows(IllegalStateException.class,
                () -> combat.enemyAttack(new MeleeAttack()));
        assertEquals(100, player.getHealth());

    }

    @Test
    void enemyCannotAttackAfterPlayerIsDefeated() {

        Player player = new Player("Player", 10, 0);
        Enemy enemy = new Enemy("Enemy", 100, 0, EnemyType.STREET_DWELLER);

        CombatSystem combat = new CombatSystem(player, enemy);

        combat.playerAttack(new MeleeAttack(
                new SequenceRandom(99, 0, 99)
        ));
        combat.enemyAttack(new SpecialAttack(
                new SequenceRandom(15, 0, 99, 99)
        ));
        assertThrows(IllegalStateException.class,
                () -> combat.enemyAttack(new MeleeAttack()));

    }

    @Test
    void playerCannotAttackAfterEnemyIsDefeated() {

        Player player = new Player("Player", 100, 0);
        Enemy enemy = new Enemy("Enemy", 10, 0, EnemyType.STREET_DWELLER);

        CombatSystem combat = new CombatSystem(player, enemy);

        combat.playerAttack(new MeleeAttack(
                new SequenceRandom(99, 0, 99)
        ));
        assertThrows(IllegalStateException.class,
                () -> combat.playerAttack(new MeleeAttack()));

    }

    @Test
    void bossDebuffLastsForTwoPlayerTurns() {

        Player player = new Player("Player", 110, 0);
        Enemy boss = new Enemy( EnemyType.POLICEMAN_BOB );

        CombatSystem combat = new CombatSystem(player, boss);

        combat.playerAttack(new MeleeAttack());
        combat.enemyAttack(boss.getAttacks().special());
        assertEquals(25, player.getMissChancePenalty());

        combat.playerAttack(new MeleeAttack());
        assertEquals(25, player.getMissChancePenalty());
        combat.enemyAttack(boss.getAttacks().melee());
        combat.playerAttack(new MeleeAttack());
        assertEquals(0, player.getMissChancePenalty());

    }

    @Test
    void playerDebuffDoesNotAffectEnemyAccuracy() {

        Player player = new Player("Player", 110, 0);
        Enemy boss = new Enemy( EnemyType.POLICEMAN_BOB );

        CombatSystem combat = new CombatSystem(player, boss);

        combat.playerAttack(new MeleeAttack());
        combat.enemyAttack(boss.getAttacks().special());
        assertEquals(25, player.getMissChancePenalty());
        assertEquals(0, boss.getMissChancePenalty());

    }

    @Test
    void constructorRejectsNullPlayerOrEnemy() {

        Enemy enemy = new Enemy("Enemy", 50, 5, EnemyType.STREET_DWELLER);
        Player player = new Player("Player", 100, 10);

        assertThrows(IllegalArgumentException.class,
                () -> new CombatSystem(null, enemy));
        assertThrows(IllegalArgumentException.class,
                () -> new CombatSystem(player, null));

    }

    @Test
    void attacksRejectNullStrategy() {

        Player player = new Player("Player", 100, 10);
        Enemy enemy = new Enemy("Enemy", 50, 5, EnemyType.STREET_DWELLER);

        CombatSystem combat = new CombatSystem(player, enemy);

        assertThrows(IllegalArgumentException.class,
                () -> combat.playerAttack(null));
        assertThrows(IllegalArgumentException.class,
                () -> combat.enemyAttack(null));

    }
}
