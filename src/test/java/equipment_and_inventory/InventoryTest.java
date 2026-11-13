package equipment_and_inventory;

import org.junit.jupiter.api.Test;

import static equipment_and_inventory.Armor.allArmors;
import static equipment_and_inventory.Potions.allPotions;
import static equipment_and_inventory.Weapons.allWeapons;
import static org.junit.jupiter.api.Assertions.*;

class InventoryTest {



    @Test
    void takeWeaponWithBetterStats() {
        var inventory = new Inventory();
        inventory.initiateAllEquipment();

        inventory.takeEquipment(allWeapons.get(6));

        assertEquals(allWeapons.get(6), inventory.getHeldWeapon());
    }

    @Test
    void dontTakeWeaponWithWorseStats() {
        var inventory = new Inventory();

        inventory.initiateAllEquipment();

        inventory.takeEquipment(allWeapons.get(6));
        inventory.takeEquipment(allWeapons.get(3));

        assertEquals(allWeapons.get(6), inventory.getHeldWeapon());
    }

    @Test
    void takeArmorWithBetterStats() {
        var inventory = new Inventory();
        inventory.initiateAllEquipment();
        inventory.takeEquipment(allArmors.get(6));

        assertEquals(allArmors.get(6), inventory.GetHeldArmor());
    }

    @Test
    void daontTakeArmorWithWorseStats() {
        var inventory = new Inventory();
        inventory.initiateAllEquipment();
        inventory.takeEquipment(allArmors.get(6));
        inventory.takeEquipment(allArmors.get(3));

        assertEquals(allArmors.get(6), inventory.GetHeldArmor());
    }

    @Test
    void testTakePotion() {
        var inventory = new Inventory();

        inventory.potionBag.clear();
        inventory.takeEquipment(allPotions.get(2));
        assertEquals(1, inventory.potionBag.size());


    }

    @Test
    void usePotion() {
        var inventory = new Inventory();
        inventory.initiateAllEquipment();
        inventory.potionBag.clear();

        inventory.takeEquipment(allPotions.get(1));
        inventory.takeEquipment(allPotions.get(2));
        inventory.takeEquipment(allPotions.get(2));

        inventory.usePotion(allPotions.get(1));

        assertEquals(2, inventory.potionBag.size());
    }

    void unownedPotionDoesntReduceAmount() {
        var inventory = new Inventory();
        inventory.initiateAllEquipment();
        inventory.potionBag.clear();

        inventory.takeEquipment(allPotions.get(1));
        inventory.takeEquipment(allPotions.get(2));
        inventory.takeEquipment(allPotions.get(2));

        inventory.usePotion(allPotions.get(3));

        assertEquals(3, inventory.potionBag.size());
    }
}