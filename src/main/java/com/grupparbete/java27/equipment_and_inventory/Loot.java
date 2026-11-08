package com.grupparbete.java27.equipment_and_inventory;


import java.util.ArrayList;

import static com.grupparbete.java27.equipment_and_inventory.Armor.*;
import static com.grupparbete.java27.equipment_and_inventory.Inventory.initiateAllEquipment;
import static com.grupparbete.java27.equipment_and_inventory.Potions.getRandomPotion;
import static com.grupparbete.java27.equipment_and_inventory.Weapons.*;

public class Loot{

    public ArrayList<Object> getLoot(){

        int amount = lootAmount();

        ArrayList<Object> loot = new ArrayList<>();

        for (int i = 0; i < amount; i++){
            int typeRoll = (int) (Math.random() * 3 + 1);

            switch (typeRoll){
                case 1:
                    Records.Weapon fetchedWeapon = getRandomWeapon();
                    loot.add(fetchedWeapon);
                    break;
                case 2: Records.Armor fethedArmor = getRandomArmor();
                        loot.add(fethedArmor);
                    break;
                case 3:
                    Records.Potion fetchedPotion = getRandomPotion();
                    loot.add(fetchedPotion);
                    break;
            }
        }
        return loot;
    }

    void main(){
        initiateAllEquipment();
        ArrayList<Object> newArray = getLoot();

        IO.println(getRandomArmor());
        IO.println(getRandomWeapon());

        for (int i = 0; i < newArray.size(); i++){
            IO.println(newArray.get(i));
        }
    }


    //Kan öka mängd och göra så att starter mängden är högre än 1
    public static int lootAmount(){
        int amount = 1;

        for(int i = 0; i < 2; i++){
            int random = (int) (Math.random() * (3) + 1);
            if(random == 1){
                amount++;
            }
        }
        return amount;
    }





}
