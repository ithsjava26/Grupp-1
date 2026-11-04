package com.grupparbete.java27.equipment_and_inventory;

import static com.grupparbete.java27.equipment_and_inventory.Armor.*;
import static com.grupparbete.java27.equipment_and_inventory.Weapons.*;

public class Inventory {

    private Records.Weapon equipedWeapon;
    private Records.Armor equipedArmor;

    private Records.Potion[] potionBag;

    public static void initiateAllEquipment(){
        initiateAllWeapons();
        initiateAllArmors();
    }
}
