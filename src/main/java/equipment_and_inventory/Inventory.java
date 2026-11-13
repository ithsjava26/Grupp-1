package equipment_and_inventory;

import java.util.ArrayList;

import static equipment_and_inventory.Armor.*;
import static equipment_and_inventory.Potions.*;
import static equipment_and_inventory.Weapons.*;

public class Inventory {

    private static Records.Weapon equippedWeapon;
    private static Records.Armor equippedArmor;

    private static ArrayList <Records.Potion> potionBag = new ArrayList<>();

    void main(){
        initiateAllEquipment();

        printPotions();
        usedPotion(allPotions.get(3));
        printPotions();
    }
    public static void initiateAllEquipment(){
        initiateAllWeapons();
        initiateAllArmors();
        initiatePotions();

        equippedWeapon = allWeapons.get(1);
        equippedArmor = allArmors.get(1);

        potionBag.add(allPotions.get(1));
        potionBag.add(allPotions.get(3));
        potionBag.add(allPotions.get(3));
    }


    public int getHeldWeaponAttack(){
        return equippedWeapon.attack();
    }

    public String getHeldWeaponName(){
        return equippedWeapon.name();
    }



    //-------------------------------------------
    public int getEquippedArmorDefense(){
        return equippedArmor.defence();
    }

    public String getEquippedArmorName(){
        return equippedArmor.name();
    }



    //-------------------------------------------------------

    public void printPotions (){

        int smallHealthPotions = 0;
        int mediumHealthPotions = 0;
        int largeHealthPotions = 0;

        for(Records.Potion potion : potionBag){

            switch (potion.name()){
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



            IO.println("1. Small recovery drink: " + smallHealthPotions);

            IO.println("2. Medium recovery drink: " + mediumHealthPotions);

            IO.println("3. Large recovery drink: " + largeHealthPotions);

        IO.println();

    }


    //------------------------------------------------------------------------------------------------------------
    public int printPotionsWithSelection(){

        int smallHealthPotions = 0;
        int mediumHealthPotions = 0;
        int largeHealthPotions = 0;

        for(Records.Potion potion : potionBag){

            switch (potion.name()){
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




        IO.println("1. Small recovery drink: " + smallHealthPotions);

        IO.println("2. Medium recovery drink: " + mediumHealthPotions);

        IO.println("3. Large recovery drink: " + largeHealthPotions);

        IO.println("4. Return");
        IO.println();

        boolean running = true;
        int nav = 0;
        while(running) {
            try {
                IO.readln("Pick an option");
            } catch (NumberFormatException e) {
                nav = 0;
            }
            if(nav < 1 || nav > 4) {
                IO.readln("Please enter a valid number");
            }

            switch(nav ){
                case 1:
                    usedPotion(allPotions.get(1));
                    return allPotions.get(1).effectStrength();
                case 2:
                    usedPotion(allPotions.get(2));
                    return allPotions.get(2).effectStrength();
                case 3:
                    usedPotion(allPotions.get(3));
                    return allPotions.get(3).effectStrength();
                case 4:
                    running = false;
                    break;
            }
        }
        return 0;
    }

    public int usedPotion(Records.Potion potion){

        for (Records.Potion pot : potionBag){
            if(pot == potion){
                potionBag.remove(potion);
                return potion.effectStrength();
            }
        }

        IO.println("You dont have any of those potions");
        return 0;
    }



    //----------------------------------------------------------
    public static void takeEquipment(Records.Weapon newWeapon) {
        if(equippedWeapon.attack() <  newWeapon.attack()) {
            equippedWeapon = newWeapon;
            IO.println("You got " +  equippedWeapon.name() + ":Attack " +  equippedWeapon.attack());

        }
    }

    public static void takeEquipment(Records.Armor newArmor){

        if(equippedArmor.defence() <  newArmor.defence()) {
            equippedArmor = newArmor;
            IO.println("You got " +  equippedArmor.name() + ": defence " +  equippedArmor.defence());

        }
    }

    public static void takeEquipment(Records.Potion newPotion){
        potionBag.add(newPotion);
    }
}
