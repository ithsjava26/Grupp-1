package equipment_and_inventory;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WeaponsTest {


    @Test
    void initiateAllWeaponsWorks() {
        var weapons = new Weapons();
        weapons.initiateAllWeapons();

        assertFalse(weapons.allWeapons.isEmpty());
    }

    @Test
    void getWeaponById() {
        var weapons = new Weapons();
        weapons.initiateAllWeapons();

        Records.Weapon savedWeapon = weapons.getWeaponById(3);
        assertEquals(weapons.allWeapons.get(3), savedWeapon);
    }

    @Test
    void getWeaponsWorksEvenIfMapIsNotInitiated() {
        var weapons = new Weapons();

        Records.Weapon savedWeapon = weapons.getWeaponById(5);
        assertEquals(weapons.allWeapons.get(5), savedWeapon);
    }

    @Test
    void getRandomWeaponInitiatesMapIfNeeded() {
        var weapons = new Weapons();

        Records.Weapon savedWeapon = weapons.getRandomWeapon();
        assertFalse(weapons.allWeapons.isEmpty());
    }
}