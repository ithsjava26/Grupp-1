package com.grupparbete.java27.equipment_and_inventory;

import java.util.TreeMap;

public class Potions {

    static TreeMap<Object, Records.Potion> allPotions = new TreeMap<>();

    public static void initiatePotions(){
        allPotions.put(1, new Records.Potion("Attack drink", "+ Attack", 5));
        allPotions.put(2, new Records.Potion("Defence drink", "+ Defence", 5));
        allPotions.put(3, new Records.Potion("Small recovery drink", "+ recovery small", 25));
        allPotions.put(4, new Records.Potion("Medium recovery drink", "+ recovery medium", 50));
        allPotions.put(5, new Records.Potion("Large recovery drink", "+ recovery large", 100));
    }

    public static Records.Potion getRandomPotion(){
        return allPotions.get(((int) (Math.random() * (5)) + 1));
    }

    public static Records.Potion getPotionById(int id){
        if(allPotions.containsKey(id)){
            return allPotions.get(id);
        }else{
            IO.println("Could not find Potion");
        }

        return null;
    }


}
