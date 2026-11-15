package combat;

public record AttackSet(
        AttackStrategy melee,
        AttackStrategy ranged,
        AttackStrategy special) {

    public AttackSet {
        if (melee == null || ranged == null || special == null) {
            throw new IllegalArgumentException(
                    "Attacks cannot be null"
            );
        }
    }
}