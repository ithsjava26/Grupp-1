package combat;

import characters.Character;
import java.util.Random;

public class RangedAttack implements AttackStrategy {

    private final Random random;

    public RangedAttack() {
        this(new Random());
    }

    RangedAttack(Random random) {
        if (random == null) {
            throw new IllegalArgumentException("Random cannot be null");
        }

        this.random = random;
    }

    @Override
    public void attack(Character attacker, Character target) {

        DamageCalculator.performAttack(
                attacker,
                target,
                15,
                25,
                15,
                0,
                random
        );
    }
}