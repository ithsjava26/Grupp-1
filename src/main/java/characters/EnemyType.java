package characters;

import combat.AttackSet;
import combat.MeleeAttack;
import combat.RangedAttack;
import combat.SpecialAttack;

public enum EnemyType {

    STREET_DWELLER(
            "Street Dweller", 50, 3,
            new AttackSet(
                    new MeleeAttack("Headbutt",
                            5, 15, 5, 0),
                    new RangedAttack("Trash Toss",
                            10, 20, 15, 0),
                    new SpecialAttack("Alleyway Roar",
                            10, 25, 40, 0)
            )
    ),

    DRUNKEN_DRIFTER(
            "Drunken Drifter", 70, 5,
            new AttackSet(
                    new MeleeAttack("Wild Haymaker",
                            10, 20, 5, 0),
                    new RangedAttack("Bottle Toss",
                            15, 25, 15, 0),
                    new SpecialAttack("Toxic Breath",
                            15, 30, 40, 0)
            )
    ),

    TWEAKER(
            "Tweaker", 80, 8,
            new AttackSet(
                    new MeleeAttack("Shove",
                            12, 22, 5, 0),
                    new RangedAttack("Needle Barrage",
                            18, 30, 15, 0),
                    new SpecialAttack("Manic Screech",
                            18, 35, 40, 0)
            )
    ),

    POLICEMAN_BOB(
            "Policeman Bob", 150, 12,
            new AttackSet(
                    new MeleeAttack("Baton Smack",
                            20, 30, 5, 0),
                    new RangedAttack("Taser", 20,
                            35, 15, 0),
                    new SpecialAttack("Blinding Police Lights",
                            20, 40, 40, 25)
            )
    );

    private final String name;
    private final int maxHealth;
    private final int defense;
    private final AttackSet attacks;

    EnemyType(String name, int maxHealth, int defense, AttackSet attacks) {
        this.name = name;
        this.maxHealth = maxHealth;
        this.defense = defense;
        this.attacks = attacks;
    }

    public String getName() {
        return name;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getDefense() {
        return defense;
    }

    public AttackSet getAttacks() {
        return attacks;
    }
}