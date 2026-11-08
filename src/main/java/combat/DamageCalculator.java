package combat;

import characters.Character;
import java.util.Random;

public class DamageCalculator {

    public static void performAttack(
            Character attacker,
            Character target,
            int minDamage,
            int maxDamage,
            int missChance,
            int backlashChance,
            Random random ){

        if (attacker == null || target == null || random == null) {
            throw new IllegalArgumentException("Arguments cannot be null");
        }

        if (minDamage < 0 || maxDamage < minDamage) {
            throw new IllegalArgumentException("Invalid damage range");
        }

        if (missChance < 0 || missChance > 100 ||
                backlashChance < 0 || backlashChance > 100) {
            throw new IllegalArgumentException("Chances must be between 0 and 100");
        }

        if (missChance > 0 && random.nextInt(100) < missChance) {
            return;
        }

        int damage = minDamage + random.nextInt(maxDamage - minDamage + 1);

        boolean critical = random.nextInt(100) < 10;

        if (critical) {
            damage = (int) Math.ceil(damage * 1.5);
        }

        target.takeDamage(damage);

        if (backlashChance > 0 && random.nextInt(100) < backlashChance) {
            int recoilDamage = (int) Math.ceil(damage * 0.5);
            attacker.takeDamage(recoilDamage);
        }
    }
}