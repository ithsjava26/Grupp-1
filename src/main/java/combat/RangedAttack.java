package combat;

import characters.Character;
import java.util.Random;

public class RangedAttack implements AttackStrategy {

    private final String name;
    private final int minDamage;
    private final int maxDamage;
    private final int missChance;
    private final int backlashChance;
    private final Random random;

    public RangedAttack() {
        this("Ranged Attack",
                15, 25, 15, 0);
    }


    RangedAttack(Random random) {
        this("Ranged Attack",
                15, 25, 15, 0, random);
    }

    public RangedAttack(
            String name,
            int minDamage,
            int maxDamage,
            int missChance,
            int backlashChance) {

        this(name, minDamage, maxDamage, missChance,
                backlashChance, new Random());
    }

    RangedAttack(
            String name,
            int minDamage,
            int maxDamage,
            int missChance,
            int backlashChance,
            Random random) {

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null");
        }

        if (random == null) {
            throw new IllegalArgumentException("Random cannot be null");
        }

        if (minDamage < 0 || maxDamage < minDamage) {
            throw new IllegalArgumentException("Invalid damage range");
        }

        if (missChance < 0 || missChance > 100 ||
                backlashChance < 0 || backlashChance > 100) {
            throw new IllegalArgumentException("Invalid attack chance");
        }

        this.name = name;
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
        this.missChance = missChance;
        this.backlashChance = backlashChance;
        this.random = random;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void attack(Character attacker, Character target) {

        int finalMissChance = Math.min(
                100,
                missChance + attacker.getMissChancePenalty()
        );

        DamageCalculator.performAttack(
                attacker,
                target,
                minDamage,
                maxDamage,
                finalMissChance,
                backlashChance,
                random
        );
    }
}