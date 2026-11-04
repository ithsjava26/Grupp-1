package characters;

public abstract class Character {

    private final String name;
    private int health;
    private int maxHealth;
    private int defense;

    public Character(String name, int maxHealth, int defense) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        if (maxHealth <= 0) {
            throw new IllegalArgumentException("Max health must be positive");
        }
        if (defense < 0) {
            throw new IllegalArgumentException("Defense cannot be negative");
        }

        this.name = name;
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.defense = defense;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getDefense() {
        return defense;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public void takeDamage(int damage) {
        if (damage < 0) {
            throw new IllegalArgumentException("Damage cannot be negative");
        }

        int actualDamage = Math.max(0, damage - defense);
        health = Math.max(0, health - actualDamage);
    }

    public void heal(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Healing amount must be positive");
        }

        health = Math.min(maxHealth, health + amount);
    }

    public void increaseMaxHealth(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Health increase must be positive");
        }

        boolean wasAtFullHealth = health == maxHealth;
        maxHealth += amount;

        if (wasAtFullHealth) {
            health = maxHealth;
        }
    }

    public void increaseDefense(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Defense increase must be positive");
        }

        defense += amount;
    }
}
