package combat;

import characters.Character;
import java.util.Random;

public class SpecialAttack implements AttackStrategy {

    private final Random random;

    public SpecialAttack() {
        this(new Random());
    }

    SpecialAttack(Random random) {
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
                30,
                0,
                40,
                random
        );
    }
}