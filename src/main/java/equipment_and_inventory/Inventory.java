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

        if (strengthPotions > 0){
            IO.println("1. Attack drink: " + strengthPotions);
        }
        if (defencePotions > 0){
            IO.println("2. Defence drink: " + defencePotions);
        }
        if (smallHealthPotions > 0){
            IO.println("3. Small recovery drink: " + smallHealthPotions);
        }
        if (mediumHealthPotions > 0){
            IO.println("4. Medium recovery drink: " + mediumHealthPotions);
        }
        if (largeHealthPotions > 0){
            IO.println("5. Large recovery drink: " + largeHealthPotions);
        }
        IO.println();

    }

    public int usedPotion(Records.Potion potion){

        int position = 0;
        for(int i = 0; i < potionBag.size(); i++){
            if (potion == potionBag.get(i)){
             position =  i;
                break;
            }

        }

        potionBag.remove(potion);

        return potion.effectStrength();
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
