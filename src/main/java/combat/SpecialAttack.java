package combat;

import characters.Character;
import java.util.Random;

public class SpecialAttack implements AttackStrategy {

    private final String name;
    private final int minDamage;
    private final int maxDamage;
    private final int backlashChance;
    private final int accuracyDebuff;
    private final Random random;

    public SpecialAttack() {
        this("Special Attack",
                15, 30, 40, 0);
    }

    SpecialAttack(Random random) {
        this("Special Attack",
                15, 30, 40, 0, random);
    }

    public SpecialAttack(
            String name,
            int minDamage,
            int maxDamage,
            int backlashChance,
            int accuracyDebuff) {

        this(name, minDamage, maxDamage, backlashChance,
                accuracyDebuff, new Random());
    }

    SpecialAttack(
            String name,
            int minDamage,
            int maxDamage,
            int backlashChance,
            int accuracyDebuff,
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

        if (backlashChance < 0 || backlashChance > 100 ||
                accuracyDebuff < 0 || accuracyDebuff > 100) {
            throw new IllegalArgumentException("Invalid attack chance");
        }

        this.name = name;
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
        this.backlashChance = backlashChance;
        this.accuracyDebuff = accuracyDebuff;
        this.random = random;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void attack(Character attacker, Character target) {

        boolean hit = DamageCalculator.performAttack(
                attacker,
                target,
                minDamage,
                maxDamage,
                0,
                backlashChance,
                random
        );

        if (hit && accuracyDebuff > 0) {
            target.applyMissChancePenalty(accuracyDebuff);
        }
    }
}