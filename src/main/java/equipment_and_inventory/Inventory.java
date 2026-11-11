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


            IO.println("1. Attack drink: " + strengthPotions);

            IO.println("2. Defence drink: " + defencePotions);

            IO.println("3. Small recovery drink: " + smallHealthPotions);

            IO.println("4. Medium recovery drink: " + mediumHealthPotions);

            IO.println("5. Large recovery drink: " + largeHealthPotions);

        IO.println();

    }


    //------------------------------------------------------------------------------------------------------------
    public String printPotionsWithSelection(){
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


        IO.println("1. Attack drink: " + strengthPotions);

        IO.println("2. Defence drink: " + defencePotions);

        IO.println("3. Small recovery drink: " + smallHealthPotions);

        IO.println("4. Medium recovery drink: " + mediumHealthPotions);

        IO.println("5. Large recovery drink: " + largeHealthPotions);

        IO.println("6. Return");
        IO.println();

        boolean running = true;
        int nav = 0;
        while(running) {
            try {
                IO.readln("Pick an option");
            } catch (NumberFormatException e) {
                nav = 0;
            }
            if(nav < 1 || nav > 6) {
                IO.readln("Please enter a valid number");
            }

            switch(nav ){
                case 1:
                    usedPotion(allPotions.get(1));
                    return allPotions.get(1).effect();
                case 2:
                    usedPotion(allPotions.get(2));
                    return allPotions.get(2).effect();
                case 3:
                    usedPotion(allPotions.get(3));
                    return allPotions.get(3).effect();
                case 4:
                    usedPotion(allPotions.get(4));
                    return allPotions.get(4).effect();
                case 5:
                    usedPotion(allPotions.get(5));
                    return allPotions.get(5).effect();
                case 6:
                    running = false;
                    break;
            }
        }
        return "";
    }

    public void usedPotion(Records.Potion potion){

        for (Records.Potion pot : potionBag){
            if(pot == potion){
                potionBag.remove(potion);
                break;
            }
        }

        IO.println("You dont have any of those potions");
    }



    //----------------------------------------------------------
    public static void takeEquipment(Records.Weapon newWeapon) {
        int nav = 0;

        if (equippedWeapon != newWeapon){
            while (true) {
                IO.println("Do you want to replace " + equippedWeapon.name() + " with " + newWeapon.name() + "?");
                IO.println("1. Yes");
                IO.println("2. no");
                try {
                    nav = Integer.parseInt(IO.readln());
                } catch (NumberFormatException e) {
                    nav = 0;
                }
                if (nav == 1 || nav == 2) {
                    break;
                }
                IO.println("Not a valid input");
            }

            if (nav == 1) {
                equippedWeapon = newWeapon;
            }
        }
    }

    public static void takeEquipment(Records.Armor newArmor){
        int nav = 0;

        if(equippedArmor != newArmor) {
            while (true) {
                IO.println("Do you want to replace " + equippedArmor.name() + " with " + newArmor.name() + "?");
                IO.println("1. Yes");
                IO.println("2. no");
                try {
                    nav = Integer.parseInt(IO.readln());
                } catch (NumberFormatException e) {
                    nav = 0;
                }
                if (nav == 1 || nav == 2) {
                    break;
                }
                IO.println("Not a valid input");
            }

            if (nav == 1) {
                equippedArmor = newArmor;
            }
        }
    }

    public static void takeEquipment(Records.Potion newPotion){
        potionBag.add(newPotion);
    }
}
