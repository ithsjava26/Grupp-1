package characters;

import combat.AttackSet;
import combat.MeleeAttack;
import combat.RangedAttack;
import combat.SpecialAttack;

public class Player extends Character {

    private static final int DEFAULT_HEALTH = 100;
    private static final int DEFAULT_DEFENSE = 10;

    private final AttackSet attacks;

    public Player(String name) {
        this(name, DEFAULT_HEALTH, DEFAULT_DEFENSE);
    }

    public Player(String name, int maxHealth, int defense) {

        super(name, maxHealth, defense);

        attacks = new AttackSet(

                new MeleeAttack(
                        "Knuckle Sandwich",
                        10, 20,
                        5, 0
                ),

                new RangedAttack(
                        "Projectile Vomit",
                        15, 25,
                        15, 0
                ),

                new SpecialAttack(
                        "Deadly Stench",
                        15, 30,
                        40, 0
                )
        );
    }

    public AttackSet getAttacks() {
        return attacks;
    }
}