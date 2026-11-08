package combat;

import characters.Character;
import java.util.Random;

public class MeleeAttack implements AttackStrategy {

    private final Random random;

    public MeleeAttack() {
        this(new Random());
    }

    MeleeAttack(Random random) {
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
                10,
                20,
                5,
                0,
                random
        );
    }
}