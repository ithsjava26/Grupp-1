package combat;

import characters.Character;

public class RangedAttack implements AttackStrategy {

    private static final int DAMAGE = 20;

    @Override
    public void attack(Character attacker, Character target) {
        target.takeDamage(DAMAGE);
    }

}