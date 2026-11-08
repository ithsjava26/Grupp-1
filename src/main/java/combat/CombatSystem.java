package combat;

import characters.Player;
import characters.Enemy;

public class CombatSystem {

    private final Player player;
    private final Enemy enemy;
    private boolean playerTurn = true;

    public CombatSystem(Player player, Enemy enemy) {

        if (player == null || enemy == null) {
            throw new IllegalArgumentException("Player and enemy cannot be null");
        }

        this.player = player;
        this.enemy = enemy;
    }

    public boolean isPlayerTurn() {
        return playerTurn;
    }

    public void playerAttack(AttackStrategy strategy) {

        if (strategy == null) {
            throw new IllegalArgumentException("Attack strategy cannot be null");
        }

        if (hasCombatEnded()) {
            return;
        }

        if (!playerTurn) {
            throw new IllegalStateException("It is not the player's turn");
        }

        strategy.attack(player, enemy);

        if (!hasCombatEnded()) {
            playerTurn = false;
        }
    }

    public void enemyAttack(AttackStrategy strategy) {

        if (strategy == null) {
            throw new IllegalArgumentException("Attack strategy cannot be null");
        }

        if (hasCombatEnded()) {
            return;
        }

        if (playerTurn) {
            throw new IllegalStateException("It is not the enemy's turn");
        }

        strategy.attack(enemy, player);

        if (!hasCombatEnded()) {
            playerTurn = true;
        }
    }

    public boolean hasCombatEnded() {
        return !player.isAlive() || !enemy.isAlive();
    }
}