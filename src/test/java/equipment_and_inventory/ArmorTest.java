package equipment_and_inventory;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArmorTest {

    @Test
    void initiateAllArmorsWorks() {
        var armor = new Armor();
        armor.initiateAllArmors();

        assertFalse(armor.allArmors.isEmpty());
    }

    @Test
    void getArmorById() {
        var armor = new Armor();
        armor.initiateAllArmors();

        Records.Armor savedArmor = armor.getArmorById(3);
        assertEquals(armor.allArmors.get(3), savedArmor);
    }

    @Test
    void getWeaponsWorksEvenIfMapIsNotInitiated() {
        var armor = new Armor();

        Records.Armor savedArmor = armor.getArmorById(5);
        assertEquals(armor.allArmors.get(5), savedArmor);
    }

    @Test
    void getRandomWeaponWorkEvenIfMapIsNotInitiated() {
        var armor = new Armor();

        Records.Armor savedArmor = armor.getRandomArmor();
        assertFalse(armor.allArmors.isEmpty());
    }
}