package combat;

import characters.Character;

public class SpecialAttack implements AttackStrategy {

    private static final int DAMAGE = 30;

    @Override
    public void attack(Character attacker, Character target) {
        target.takeDamage(DAMAGE);
    }

}