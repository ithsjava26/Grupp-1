package characters;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CharacterTest {

    private static class TestCharacter extends Character {
        TestCharacter(String name, int maxHealth, int defense) {
            super(name, maxHealth, defense);
        }
    }

    @Test
    void characterStartsWithFullHealth() {
        Character character = new TestCharacter("Player", 100, 10);
        assertEquals(100, character.getHealth());
        assertEquals(100, character.getMaxHealth());
        assertTrue(character.isAlive());
    }

    @Test
    void takeDamageReducesHealthByDamageMinusDefense() {
        Character character = new TestCharacter("Player", 100, 10);
        character.takeDamage(30);
        assertEquals(80, character.getHealth());
    }

    @Test
    void takeDamageDoesNotReduceHealthWhenDefenseIsHigherThanDamage() {
        Character character = new TestCharacter("Player", 100, 20);

        character.takeDamage(10);

        assertEquals(100, character.getHealth());
    }

    @Test
    void takeDamageCannotReduceHealthBelowZero() {
        Character character = new TestCharacter("Player", 20, 5);
        character.takeDamage(100);
        assertEquals(0, character.getHealth());
        assertFalse(character.isAlive());
    }

    @Test
    void healCannotExceedMaxHealth() {
        Character character = new TestCharacter("Player", 100, 0);
        character.takeDamage(40);
        character.heal(100);
        assertEquals(100, character.getHealth());
    }

    @Test
    void increaseMaxHealthKeepsFullHealthCharacterAtFullHealth() {
        Character character = new TestCharacter("Player", 100, 10);
        character.increaseMaxHealth(20);
        assertEquals(120, character.getMaxHealth());
        assertEquals(120, character.getHealth());
    }

    @Test
    void increaseMaxHealthKeepsCurrentHealthWhenAlreadyInjured() {
        Character character = new TestCharacter("Hero", 100, 0);
        character.takeDamage(40);
        character.increaseMaxHealth(20);
        assertEquals(120, character.getMaxHealth());
        assertEquals(60, character.getHealth());
    }

    @Test
    void increaseDefenseRaisesDefenseValue() {
        Character character = new TestCharacter("Player", 100, 10);
        character.increaseDefense(5);
        assertEquals(15, character.getDefense());
    }

    @Test
    void constructorRejectsInvalidValues() {
        assertThrows(IllegalArgumentException.class,
                () -> new TestCharacter("", 100, 10));
        assertThrows(IllegalArgumentException.class,
                () -> new TestCharacter("Player", 0, 10));
        assertThrows(IllegalArgumentException.class,
                () -> new TestCharacter("Player", 100, -1));
    }

    @Test
    void methodsRejectInvalidAmounts() {
        Character character = new TestCharacter("Player", 100, 10);
        assertThrows(IllegalArgumentException.class,
                () -> character.takeDamage(-1));

        assertThrows(IllegalArgumentException.class,
                () -> character.heal(0));

        assertThrows(IllegalArgumentException.class,
                () -> character.increaseMaxHealth(-1));

        assertThrows(IllegalArgumentException.class,
                () -> character.increaseDefense(0));
    }
}
