package combat;

import characters.Character;

public interface AttackStrategy {

    default String getName() {
        return getClass().getSimpleName();
    }

    void attack(Character attacker, Character target);
}