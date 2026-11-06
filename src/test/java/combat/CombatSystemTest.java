package combat;

import characters.Player;
import characters.Enemy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CombatSystemTest {

    @Test
    void meleeAttackDealsCorrectDamage() {
        Player player = new Player("Player", 100, 10);
        Enemy enemy = new Enemy("Enemy", 50, 5);

        CombatSystem combat = new CombatSystem(player, enemy);
        combat.playerAttack(new MeleeAttack());

        assertEquals(45, enemy.getHealth());
    }

    @Test
    void rangedAttackDealsCorrectDamage() {
        Player player = new Player("Player", 100, 10);
        Enemy enemy = new Enemy("Enemy", 50, 5);

        CombatSystem combat = new CombatSystem(player, enemy);
        combat.playerAttack(new RangedAttack());

        assertEquals(35, enemy.getHealth());
    }

    @Test
    void specialAttackDealsCorrectDamage() {
        Player player = new Player("Player", 100, 10);
        Enemy enemy = new Enemy("Enemy", 50, 5);

        CombatSystem combat = new CombatSystem(player, enemy);
        combat.playerAttack(new SpecialAttack());

        assertEquals(25, enemy.getHealth());
    }

    @Test
    void combatEndsWhenEnemyIsDefeated() {
        Player player = new Player("Player", 100, 10);
        Enemy enemy = new Enemy("Enemy", 10, 0);

        CombatSystem combat = new CombatSystem(player, enemy);

        combat.playerAttack(new MeleeAttack());

        assertTrue(combat.hasCombatEnded());
    }

    @Test
    void combatEndsWhenPlayerIsDefeated() {
        Player player = new Player("Player", 10, 0);
        Enemy enemy = new Enemy("Enemy", 100, 10);

        CombatSystem combat = new CombatSystem(player, enemy);

        combat.enemyAttack(new MeleeAttack());

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

        combat.playerAttack(new SpecialAttack());
        combat.enemyAttack(new MeleeAttack());

        assertEquals(100, player.getHealth());
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