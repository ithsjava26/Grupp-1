package combat;

import characters.Character;

public class MeleeAttack implements AttackStrategy {

    private static final int DAMAGE = 10;

    @Override
    public void attack(Character attacker, Character target) {
        target.takeDamage(DAMAGE);
    }

}