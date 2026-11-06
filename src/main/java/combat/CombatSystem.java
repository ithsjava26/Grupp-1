package combat;

import characters.Player;
import characters.Enemy;

public class CombatSystem {

    private final Player player;
    private final Enemy enemy;

    public CombatSystem(Player player, Enemy enemy) {

        if (player == null || enemy == null) {
            throw new IllegalArgumentException("Player and enemy cannot be null");
        }

        this.player = player;
        this.enemy = enemy;
    }

    public void playerAttack(AttackStrategy strategy) {

        if (strategy == null) {
            throw new IllegalArgumentException("Attack strategy cannot be null");
        }

        if (!player.isAlive() || !enemy.isAlive()) {
            return;
        }

        strategy.attack(player, enemy);
    }

    public void enemyAttack(AttackStrategy strategy) {

        if (strategy == null) {
            throw new IllegalArgumentException("Attack strategy cannot be null");
        }

        if (!enemy.isAlive() || !player.isAlive()) {
            return;
        }

        strategy.attack(enemy, player);
    }

    public boolean hasCombatEnded() {
        return !player.isAlive() || !enemy.isAlive();
    }
}