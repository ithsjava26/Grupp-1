package com.grupparbete.java27.equipment_and_inventory;

import java.util.ArrayList;

import static com.grupparbete.java27.equipment_and_inventory.Armor.*;
import static com.grupparbete.java27.equipment_and_inventory.Weapons.*;

public class Inventory {

    private Records.Weapon equippedWeapon;
    private Records.Armor equippedArmor;

    private ArrayList <Records.Potion> potionBag = new ArrayList<>();

    public static void initiateAllEquipment(){
        initiateAllWeapons();
        initiateAllArmors();
    }


    public int getHeldWeaponAttack(){
        return equippedWeapon.attack();
    }

    public String getHeldWeaponName(){
        return equippedWeapon.name();
    }



    //-------------------------------------------
    public int getEquippedArmorDefense(){
        return equippedArmor.def();
    }

    public String getEquippedArmorName(){
        return equippedArmor.name();
    }



    //-------------------------------------------------------
    public void collectPotion(Records.Potion potion){
        potionBag.add(potion);
    }

    public void printPotions (){
        int strengthPotions = 0;
        int defencePotions = 0;
        int smallHealthPotions = 0;
        int mediumHealthPotions = 0;
        int largeHealthPotions = 0;

        for(Records.Potion potion : potionBag){

            switch (potion.name()){
                case "Attack drink":
                    strengthPotions++;
                    break;
                case "Defence drink":
                    defencePotions++;
                    break;
                case "Small recovery drink":
                    smallHealthPotions++;
                    break;
                case "Medium recovery drink":
                    mediumHealthPotions++;
                    break;
                case "Large recovery drink":
                    largeHealthPotions++;
                    break;
            }
        }

        if (strengthPotions > 0){
            IO.println("Attack drink: " + strengthPotions);
        }
        if (defencePotions > 0){
            IO.println("Defence drink: " + defencePotions);
        }
        if (smallHealthPotions > 0){
            IO.println("Small recovery drink: " + smallHealthPotions);
        }
        if (mediumHealthPotions > 0){
            IO.println("Medium recovery drink: " + mediumHealthPotions);
        }
        if (largeHealthPotions > 0){
            IO.println("Large recovery drink: " + largeHealthPotions);
        }

    }

    public int usePotions(Records.Potion potion){

        potionBag.remove(potion);

        return potion.effectStrength();
    }



    //----------------------------------------------------------
    public void setEquippedWeapon(Records.Weapon newWeapon){
        equippedWeapon = newWeapon;
    }

    public void setEquippedArmor(Records.Armor newArmor){
        equippedArmor = newArmor;
    }


}
