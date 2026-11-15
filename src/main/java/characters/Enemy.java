package characters;

import combat.AttackSet;

public class Enemy extends Character {

    private final EnemyType enemyType;
    private final AttackSet attacks;

    public Enemy(EnemyType enemyType) {
        super(
                getName(enemyType),
                getHealth(enemyType),
                getDefense(enemyType)
        );

        this.enemyType = enemyType;
        this.attacks = enemyType.getAttacks();
    }

    public Enemy(String name, int health, int defense, EnemyType enemyType) {
        super(name, health, defense);

        if (enemyType == null) {
            throw new IllegalArgumentException("Enemy type cannot be null");
        }

        this.enemyType = enemyType;
        this.attacks = enemyType.getAttacks();
    }

    private static String getName(EnemyType type) {
        if (type == null) {
            throw new IllegalArgumentException("Enemy type cannot be null");
        }
        return type.getName();
    }

    private static int getHealth(EnemyType type) {
        if (type == null) {
            throw new IllegalArgumentException("Enemy type cannot be null");
        }
        return type.getMaxHealth();
    }

    private static int getDefense(EnemyType type) {
        if (type == null) {
            throw new IllegalArgumentException("Enemy type cannot be null");
        }
        return type.getDefense();
    }

    public EnemyType getEnemyType() {
        return enemyType;
    }

    public AttackSet getAttacks() {
        return attacks;
    }

    public boolean isBoss() {
        return enemyType == EnemyType.POLICEMAN_BOB;
    }
}